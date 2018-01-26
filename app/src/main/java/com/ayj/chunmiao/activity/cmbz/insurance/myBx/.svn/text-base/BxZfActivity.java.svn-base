package com.ayj.chunmiao.activity.cmbz.insurance.myBx;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.bx.BxBdxqBean;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 我的保险支付
* */
public class BxZfActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_order_paywx)
    TextView tvOrderPaywx;
    @BindView(R.id.tv_order_payali)
    TextView tvOrderPayali;
    @BindView(R.id.tv_order_orderid)
    TextView tvOrderOrderid;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;

    BxBdxqBean.DataBean item;

    UserPerson mUserPerson;

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
        return R.layout.activity_bx_zf;
    }

    @Override
    public void initDatas() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        item = (BxBdxqBean.DataBean) getIntent().getSerializableExtra("list");
        tvTitle.setText("保险支付");
        tvOrderOrderid.setText("保单号："+item.getSnid());
        tvOrderPrice.setText("应付价格："+(null==item.getYjmoneytotal()?"0.00":item.getYjmoneytotal()+""));
        tvOrderName.setText("保险名称："+item.getIcsnidshow());
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_order_paywx, R.id.tv_order_payali, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_order_paywx:
                break;
            case R.id.tv_order_payali:
                changePaytype();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
    private void changePaytype(){

        if(CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.BX_ZF))
                    .addParams("json","{\"key\":\"\",\"msnid\":\""+mUserPerson.getData().get(0).getSnid()+"\","
                            + "\"pwd\":\""+mUserPerson.getData().get(0).getPassWord()+"\","
                            + "\"snid\":\""+item.getSnid()+"\",\"paytype\":\"PAYTYPE004\"}")
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
                                    paybyali(null==item.getYjmoneytotal()?"0.00":item.getYjmoneytotal()+"",(String) check.getData(),item.getIcsnidshow(), Constant.BX);
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
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
