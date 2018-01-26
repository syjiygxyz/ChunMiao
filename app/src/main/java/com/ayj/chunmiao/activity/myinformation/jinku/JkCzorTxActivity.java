package com.ayj.chunmiao.activity.myinformation.jinku;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.PayWayPopupWindow;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class JkCzorTxActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.ll_illustration)
    LinearLayout llIllustration;

    private String type;
    PayWayPopupWindow popupWindow;
    private JSONObject jsonObject;
    private static final int SDK_PAY_FLAG = 1;
    private String orderId;

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
        return R.layout.activity_jkcz;
    }

    @Override
    public void initDatas() {
        type = getIntent().getStringExtra("type");
        if ("cz".equals(type)) {
            tvTitle.setText("充值");
            btnConfirm.setText("确认充值");
            etMoney.setHint("请输入充值金额");
            llIllustration.setVisibility(View.INVISIBLE);
        } else {
            tvTitle.setText("提现");
            btnConfirm.setText("确认提现");
            etMoney.setHint("请输入提现金额");
            llIllustration.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void configViews() {
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etMoney.getText().toString().trim())) {
                    btnConfirm.setBackgroundResource(R.drawable.bg_button_notclick_radius);
                    btnConfirm.setEnabled(false);


                } else {
                    btnConfirm.setBackgroundResource(R.drawable.blue_button_background);
                    btnConfirm.setEnabled(true);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                if ("cz".equals(type)){
                    setOrder();
                }else{
                    Intent intent = new Intent(mContext,JKyanzhengActivity.class);
                    intent.putExtra("money",etMoney.getText().toString().trim());
                    startActivity(intent);
                }

                break;
        }
    }

    private void setOrder() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            JSONObject obj = new JSONObject();
            try {
                obj.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                obj.put("ordertype",Constant.CZ_TYPE_JK);
                obj.put("totalnum",etMoney.getText().toString().trim());
            }catch (JSONException e){
                e.printStackTrace();
            }
            if (!String.valueOf(jsonObject).equals(String.valueOf(obj))){
                jsonObject = obj;
                mProgressHub.show();
                OkHttpUtils.post()
                        .url(WebUtils.getRequestUrl(WebUtils.JF_ORDER))
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
                                        showPayWayPop();
                                        break;
                                    default:
                                        showToast(check.getMsg());
                                        break;
                                }
                            }
                        });
            }else {
                showPayWayPop();
            }
        }else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    /*支付方式弹窗*/
    private void showPayWayPop() {
        final JSONObject obj = new JSONObject();
        try {
            obj.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
            obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
            obj.put("ordertype",Constant.CZ_TYPE_JK);
            obj.put("totalnum",etMoney.getText().toString().trim());
        }catch (JSONException e){
            e.printStackTrace();
        }
        jsonObject = obj;
        popupWindow = new PayWayPopupWindow(mContext,"选择支付方式","小金库",etMoney.getText().toString().trim(), UtilityItem.getPayWayList(), new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0 ://支付宝
                        paybyali(etMoney.getText().toString().trim(),orderId,"金库充值",Constant.CG);
                        popupWindow.dismiss();
                        break;
                    case 1 ://微信
                        CommonUtils.paybywx(mContext,orderId,Constant.CG);
                        popupWindow.dismiss();
                        break;
                }
            }
        },null,2);
        popupWindow.showAtLocation(btnConfirm, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
        popupWindow.update();
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
