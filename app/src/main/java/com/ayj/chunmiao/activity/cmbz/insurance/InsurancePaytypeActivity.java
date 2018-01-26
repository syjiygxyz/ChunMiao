package com.ayj.chunmiao.activity.cmbz.insurance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.Constant;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import butterknife.BindView;
import butterknife.OnClick;
/*
* 选择支付方式
* */

public class InsurancePaytypeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yfmoney)
    TextView tvYfmoney;
    @BindView(R.id.linearLayout_pay_ali)
    LinearLayout linearLayoutPayAli;
    @BindView(R.id.linearLayout_pay_wx)
    LinearLayout linearLayoutPayWx;
    @BindView(R.id.linearLayout_pay_yl)
    LinearLayout linearLayoutPayYl;
    private static final int SDK_PAY_FLAG = 1;

    private IWXAPI api;
    private String money, orderid, jsonobj;

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
        return R.layout.activity_insurance_paytype;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("选择支付方式");
        money = getIntent().getStringExtra("money");
        orderid = getIntent().getStringExtra("orderid");
        tvYfmoney.setText("¥" + money);
        linearLayoutPayYl.setVisibility(View.GONE);
        linearLayoutPayWx.setVisibility(View.GONE);
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.linearLayout_pay_ali, R.id.linearLayout_pay_wx, R.id.linearLayout_pay_yl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.linearLayout_pay_ali:
                /*支付宝*/
                paybyali(money,orderid,"订单号："+orderid, Constant.BX);
                break;
            case R.id.linearLayout_pay_wx:
                 /*微信支付*/
                break;
            case R.id.linearLayout_pay_yl:
                /*银联*/
                break;
        }
    }

    @OnClick(R.id.iv_back)
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
}


