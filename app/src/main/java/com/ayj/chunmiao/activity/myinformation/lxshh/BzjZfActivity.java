package com.ayj.chunmiao.activity.myinformation.lxshh;

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

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.view.PayWayPopupWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BzjZfActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_snid)
    TextView tvSnid;
    @BindView(R.id.confirm_button)
    Button confirmButton;

    private String snid,deposit;
    private PayWayPopupWindow payWayPopupWindow;

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
        return R.layout.activity_bzj_zf;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("支付保证金");
        snid = getIntent().getStringExtra("snid");
        deposit = getIntent().getStringExtra("deposit");
        tvSnid.setText("订单编号:"+snid);
        tvTotalMoney.setText("￥"+deposit);
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.confirm_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.confirm_button:
                showPayPopWindow();
                break;
        }
    }

    private void showPayPopWindow() {
        payWayPopupWindow = new PayWayPopupWindow(mContext, "选择支付方式","小金库", deposit, UtilityItem.getPayWayList(), new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        paybyali(deposit,snid,"联销申报保证金", Constant.LX);
                        payWayPopupWindow.dismiss();
                        break;
                    case 1:
                        CommonUtils.paybywx(mContext,snid,Constant.LX);
                        payWayPopupWindow.dismiss();
                        break;
                }
            }
        },null,2);
        payWayPopupWindow.showAtLocation(confirmButton, Gravity.BOTTOM,0,0);
        payWayPopupWindow.update();
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
