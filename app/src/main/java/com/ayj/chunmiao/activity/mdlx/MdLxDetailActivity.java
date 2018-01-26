package com.ayj.chunmiao.activity.mdlx;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.myinformation.shoporder.ShopOrderActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.bean.shopping.JinmaoShopDetial;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubView;
import com.ayj.chunmiao.view.PayWayPopupWindow;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class MdLxDetailActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.banner_main)
    Banner bannerMain;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.addandsub_shopcart)
    MyAddAndSubView addandsubShopcart;
    @BindView(R.id.iv_cp_detail)
    ImageView ivCpDetail;
    @BindView(R.id.btn_add_shopcart)
    Button btnAddShopcart;
    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;

    int num = 1;
    String matId, orderType,toTalnum;
    Shoppingmall.DataBean good;

    private List<String> networkImages = new ArrayList<>();
    private List<Shoppingmall.DataBean> goods = new ArrayList<>();
    private PayWayPopupWindow payWayPopupWindow;
    private String orderId;
    private static final int SDK_PAY_FLAG = 1;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_md_lx_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("商品详情");
        if (null != getIntent().getSerializableExtra("item")){
            good = (Shoppingmall.DataBean)getIntent().getSerializableExtra("item");
            matId = good.getMatid();
            orderType = good.getOrdertype();
            toTalnum = good.getTotalnum();
        }
        tvKucun.setText("库存"+toTalnum);
        tvName.setText(good.getMatidshow());
        tvPrice.setText("￥"+CommonUtils.douFormat(good.getShoppurchaseprice()));
        CommonUtils.setBannerAttribute(mContext, bannerMain);
        addandsubShopcart.setMax(
                Integer.parseInt(toTalnum));
        addandsubShopcart.setOnPosNegListener(
                new MyAddAndSubView.OnPosNegListener() {
                    @Override
                    public void onMyPositiveListener(int count) {
                        num = count;
                        orderId = null;
                    }

                    @Override
                    public void onMyNegativeListener(int count) {
                        num = count;
                        orderId = null;
                    }
                });
        addandsubShopcart.setOnClickAddAndSubListener(
                new MyAddAndSubView.OnClickAddAndSubListener() {
                    @Override
                    public void clickAdd(int count) {
                        num = count;
                        orderId = null;
                    }

                    @Override
                    public void clickSub(int count) {
                        num = count;
                        orderId = null;
                    }
                });
    }

    @Override
    public void configViews() {
        getInfo();
    }


    @OnClick({R.id.iv_back, R.id.btn_add_shopcart, R.id.btn_buy_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add_shopcart:
                setOrder(false);
                break;
            case R.id.btn_buy_now:
                if (null == orderId){
                    setOrder(true);
                }else {
                    showPayWindow();
                }
                break;
        }
    }

    private void setOrder(final boolean isBuy) {
        if (CommonUtils.isNetworkAvailable(mContext)){
           mProgressHub.show();
            JSONObject jsonObject = new JSONObject();
            JSONObject detail = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {
                detail.put("matid",matId);
                detail.put("num",num);
                jsonArray.put(detail);
                jsonObject.put("key", "");
                jsonObject.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                jsonObject.put("ordertype", orderType);
                jsonObject.put("detail", jsonArray.toString());
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
                                    if (isBuy){
                                        showPayWindow();
                                    }else {
                                        showConfirmDialog();
                                    }

                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void showPayWindow() {
        payWayPopupWindow = new PayWayPopupWindow(mContext, "选择支付方式","小金库",
                new DecimalFormat("0.00").format(Double.parseDouble(good.getShoppurchaseprice()) * num), UtilityItem.getPayWayList(),
                new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                            switch (position){
                                case 0:
                                    paybyali(new DecimalFormat("0.00").format(Double.parseDouble(good.getShoppurchaseprice())*num),orderId,good.getMatidshow(),Constant.CG);
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
        payWayPopupWindow.showAtLocation(btnBuyNow, Gravity.BOTTOM,0,0);
        payWayPopupWindow.update();
    }

    /*商品详情信息*/
    public void getInfo() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("ordertype", orderType);
                jsonObject.put("matid", matId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_DETAIL))
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
                                    JinmaoShopDetial jinmaoShopDetial = new Gson().fromJson(response, JinmaoShopDetial.class);

                                    String[] datas = jinmaoShopDetial.getData().get(
                                            0).getImgurlshow().split(",");
                                    for (int i = 0; i < datas.length; i++) {
                                        networkImages.add(datas[i]);
                                    }
                                    bannerMain.setImages(networkImages);
                                    bannerMain.start();
                                    if (!TextUtils.isEmpty(jinmaoShopDetial.getData().get(
                                            0).getIntroducemediaidcompressshow())) {
                                        Picasso.with(mContext).load(
                                                jinmaoShopDetial.getData().get(
                                                        0).getIntroducemediaidshow()).placeholder(R.mipmap.banner_loading).error(
                                                R.mipmap.banner_error).into(
                                                ivCpDetail);
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void showConfirmDialog() {
        SweetAlertDialog confirmDialog =  CommonUtils.getConfirmDialog(mContext, "提示", "成功加入采购单", "查看采购单", "再看看", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                startActivity(new Intent(mContext, ShopOrderActivity.class));
                sweetAlertDialog.dismiss();
            }
        });
        confirmDialog.show();
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
