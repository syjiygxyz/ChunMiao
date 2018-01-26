package com.ayj.chunmiao.activity.cg.xhj;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.myinformation.shoporder.StockOrderDetailActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.cg.XhjConfirmCgAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.bean.myinformation.ShopStockBean;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.PayWayPopupWindow;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*确认订单*/
public class XhjBuyNowActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.rl_time)
    RelativeLayout rl_time;
    @BindView(R.id.tv_jh)
    TextView tv_jh;

    private static final int SDK_PAY_FLAG = 1;

    JSONArray wlArray = new JSONArray();

    double totalPrice;
    private  String matidShow,orderId,orderType;

    private PayWayPopupWindow payWayPopupWindow;

    private List<Shoppingmall.DataBean> goods = new ArrayList<>();
    private XhjConfirmCgAdapter adapter;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showToast("支付成功");
                        showOrderDetail("支付宝");
                        finish();
                    } else {
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showToast("支付确认中");
                        } else {
                            // 判断resultStatus 为非"9000"则代表可能支付失败
                            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            showToast("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    private String date;

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("WxZf")) {
            showOrderDetail("小金库");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    private void showOrderDetail(String payType) {
        ShopStockBean.DataBean order = new ShopStockBean.DataBean();
        order.setPaytypeshow(payType);
        order.setTotalmoney(String.valueOf(totalPrice));
        order.setSnid(orderId);
        order.setStatusshow("待发货");
        order.setShopidshow(AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getShopidshow());
        order.setCreatedate(date);
        Intent intent = new Intent();
        intent.putExtra("order",(Serializable) order);
        intent.setClass(mContext, StockOrderDetailActivity.class);
        startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_xhj_buy_now;
    }

    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        tvTitle.setText("确认订单");
        goods = (List<Shoppingmall.DataBean>) getIntent().getSerializableExtra("goods");
        orderType = getIntent().getStringExtra("orderType");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        if (adapter == null) {
            adapter = new XhjConfirmCgAdapter(R.layout.item_xhj_cg_buynow, goods,getIntent().getStringExtra("type"));
        } else {
            adapter.setNewData(goods);
        }
        recyclerView.setAdapter(adapter);
        computeTotalPrice();
        for (int i = 0; i < goods.size(); i++) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("matid", goods.get(i).getMatid());
                obj.put("num", goods.get(i).getNum());
                wlArray.put(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void configViews() {

    }

    private void computeTotalPrice() {
        totalPrice = 0.00;
        for (int i = 0; i < goods.size(); i++) {
            int numb = Integer.parseInt(goods.get(i).getNum());
            Double price = Double.parseDouble(goods.get(i).getShoppurchaseprice());
            Double itemPrice = numb * price;
            totalPrice += itemPrice;
            matidShow = goods.get(i).getMatidshow();
        }
    }


    @OnClick({R.id.iv_back, R.id.tv_confirm, R.id.rl_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                if ("请选择交货日期".equals(tv_jh.getText().toString().trim())) {
                   showToast("请选择交货日期");
                    return;
                }
                if (null == orderId){
                    setOrder();
                }else {
                    showPayUpWindow();
                }
                break;
            case R.id.rl_time:
                showDayPickView();
                break;
        }
    }

    private void setOrder() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                jsonObject.put("ordertype", orderType);
                jsonObject.put("requestdate", tv_jh.getText().toString().trim());
                jsonObject.put("detail", wlArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CG_XD))
                    .addParams("json", String.valueOf(jsonObject))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    orderId = check.getData().toString();
                                    date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis());
                                    if (orderType.equals(Constant.CG_CX)){
                                        showOrderDetail("自动扣款");
                                        finish();
                                    }else {
                                        showPayUpWindow();
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else{
            showToast(R.string.TheNetIsUnAble);
        }

    }


    private void showPayUpWindow() {
        payWayPopupWindow = new PayWayPopupWindow(mContext, "选择支付方式","小金库", new DecimalFormat("#0.00").format(totalPrice), UtilityItem.getPayWayList(), new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        paybyali(new DecimalFormat("#0.00").format(totalPrice),orderId,matidShow,Constant.CG);
                        payWayPopupWindow.dismiss();
                        break;
                    case 1:
                        CommonUtils.paybywx(mContext,orderId,Constant.CG);
                        payWayPopupWindow.dismiss();
                        break;
                    case 2:
                        inputPaypwd(orderId,Constant.CG,null);
                        payWayPopupWindow.dismiss();
                        break;
                }
            }
        },null,3);
        payWayPopupWindow.showAtLocation(tvConfirm, Gravity.BOTTOM,0,0);
        payWayPopupWindow.update();
    }

    /*显示年月日,dateStyle*/
    private void showDayPickView() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2099, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                tv_jh.setText(CommonUtils.getTime(date, "yyyy-MM-dd"));
                orderId = null;
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setCancelText("取消").setDate(endDate).setRangDate(endDate, startDate).build();
        ;//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    @Override
    protected void postZfb(final String payInfo) {
        super.postZfb(payInfo);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构建PayTask对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
