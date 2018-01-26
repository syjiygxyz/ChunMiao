package com.ayj.chunmiao.activity.cmbz.fwq;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.bjfk.InsuranceFeedbackActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.NumberButton;
import com.ayj.chunmiao.view.PayWayPopupWindow;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 服务券详情
* */
public class FwqDetailActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.number_button)
    NumberButton mNumberButton;
    @BindView(R.id.tv_money)
    TextView mTvMoney;

    UserPerson mUserPerson;

    FootZqShop.DataBean shopList;

    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.tv_confirm)
    Button tvConfirm;

    private IWXAPI api;

    String jsonobj;
    private String orderId,totalMoney;
    private PayWayPopupWindow payWayPopupWindow;

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_fwq_detail;
    }

    @Override
    public void initDatas() {
        api = WXAPIFactory.createWXAPI(mContext, null);
        mTvTitle.setText("服务券购买");
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        shopList = (FootZqShop.DataBean) getIntent().getSerializableExtra("info");
        mTvMoney.setText(Float.parseFloat(shopList.getNowprice()) + "");
        mTv.setText(shopList.getMatidshow());
        if (Constant.Wl_AJTL.equals(shopList.getMatid())) {
            mIv.setImageResource(R.mipmap.fuq_dzj);
        } else if (Constant.Wl_SB.equals(shopList.getMatid())) {
            mIv.setImageResource(R.mipmap.fwq_sb);
        } else if (Constant.Wl_FR.equals(shopList.getMatid())) {
            mIv.setImageResource(R.mipmap.fwq_fyr);
        } else if (Constant.Wl_TP.equals(shopList.getMatid())) {
            mIv.setImageResource(R.mipmap.fwq_pp);
        }
    }

    @Override
    public void configViews() {
        mNumberButton.setOnWarnListener(new NumberButton.OnWarnListener() {
            @Override
            public void onWarningForInventory(int inventory) {
                Toast.makeText(mContext, "当前库存:" + inventory, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWarningForBuyMax(int buyMax) {
                Toast.makeText(mContext, "超过最大购买数:" + buyMax, Toast.LENGTH_SHORT).show();
            }
        });
        mNumberButton.setOnNumChangeListener(new NumberButton.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int num) {
                totalMoney = String.valueOf(mNumberButton.getNumber() * Float.parseFloat(shopList.getNowprice()));
                mTvMoney.setText(totalMoney);
            }
        });
    }

    @OnClick()
    public void onViewClicked() {
        finish();
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

    @OnClick({R.id.tv_confirm, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                    showPayUpWindow();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void setOrder(final String payType, final int fPayType) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SEND_DD))
                    .addParams("json",  "{\"key\":\"\",\"msnid\":\""
                            + mUserPerson.getData().get(0).getSnid()
                            + "\",\"pwd\":\"" + mUserPerson.getData().get(
                            0).getPassWord() + "\",\"ordertype\":\"" + shopList.getOrdertype()
                            +"\",\"paytype\":\"" + payType
                            + "\",\"membermoneybagusemoney\":\"" + ""
                            + "\",\"membercarddetail\":\"" + ""
                            + "\",\"orderdetail\":\"[{\\\"pid\\\":\\\"" + shopList.getMatid()
                            + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(0).getShopids() + "\\\",\\\"num\\\":\\\""
                            + mNumberButton.getNumber() + "\\\"}]\"}")
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
                                    if (payType.equals(Constant.HY_LQ)){
                                        showToast("购买成功");
                                        finish();
                                    }else {
                                        if (1 == fPayType){
                                            paybyali(totalMoney,orderId,shopList.getMatidshow(),Constant.HY);
                                        }else if (2 == fPayType){
                                            CommonUtils.paybywx(mContext,orderId,Constant.HY);
                                        }
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
        payWayPopupWindow = new PayWayPopupWindow(mContext, "选择支付方式","零钱支付", totalMoney,
                UtilityItem.getPayWayList(), new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        setOrder(Constant.HY_3F,1);
                        payWayPopupWindow.dismiss();
                        break;
                    case 1:
                        setOrder(Constant.HY_3F,2);
                        payWayPopupWindow.dismiss();
                        break;
                    case 2:
                        dialogShow();
                        payWayPopupWindow.dismiss();
                        break;
                }
            }
        },null,3);
        payWayPopupWindow.showAtLocation(tvConfirm, Gravity.BOTTOM,0,0);
        payWayPopupWindow.update();
    }
    private void dialogShow() {
        new PassWordDialog(this).setTitle("请输入交易密码").setCompleteListener(
                new DialogCompleteListener() {
                    @Override
                    public void dialogCompleteListener(String money, String pwd) {
                        validateShopPayPwd(pwd);
                    }
                }).show();
    }

    public void validateShopPayPwd(String payPwd){
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("paypwd", MD5Utils.getMD5String(payPwd));
            }catch (JSONException e ){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.VAL_SHOP_PAY_PWD))
                    .addParams("json",String.valueOf(object))
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
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    setOrder(Constant.HY_LQ,0);
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
   /* private void payByLq() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("key","");
                jsonObject.put("msnid",mUserPerson.getData().get(0).getSnid());
                jsonObject.put("pwd",mUserPerson.getData().get(0).getPassWord());
                jsonObject.put("snid",orderId);
                jsonObject.put("paytype","PAYTYPE001");
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.WX_XD))
                    .addParams("json",jsonObject.toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0 :
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
    }*/
}
