/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ayj.chunmiao.activity.base;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.GetOrderInfo;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.ProgressHUD;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.githang.statusbar.StatusBarCompat;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.ButterKnife;
import okhttp3.Call;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    protected Gson gson;

    protected ACache aCache;

    public ProgressHUD mProgressHub = null;

    private static final int SYSTEM_UI_FLAG_LIGHT_STATUS_BAR = 1 << 13;
    private String webUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#2e5250"), true);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(getLayoutId());
        mContext = this;
        aCache = CommonUtils.getAcache();
        mProgressHub = CommonUtils.createProgressDialog(mContext, "正在加载中..", true);
        ButterKnife.bind(this);
        initDatas();
        configViews();
    }

    @Override
    protected void onPause() {
        if (mProgressHub != null && mProgressHub.isShowing()) {
            mProgressHub.dismiss();
        }
        super.onPause();
    }

    public abstract int getLayoutId();

    public abstract void initDatas();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mProgressHub != null && mProgressHub.isShowing()) {
            mProgressHub.dismiss();
        }
    }

    /*支付订单
   * 上下文
   * 输入解析对象
   * 支付方式，1微信支付，2支付宝
   * */
    public void postZf(final Context mContext, String jsonObj, final String zfType, final int payFor, final String totalMoney,
                         final String matidshow) {

        if (CommonUtils.isNetworkAvailable(mContext)) {
            final ProgressHUD mProgressHub = CommonUtils.createProgressDialog(mContext, "提交信息中...", false);
            if (mProgressHub != null)
                mProgressHub.show();
            switch (payFor) {
                case 0:
                    webUrl = WebUtils.getRequestUrl(WebUtils.SEND_DD);
                    break;
                case 1:
                    webUrl = WebUtils.getRequestUrl(WebUtils.BX_POST);
                    break;
                case 2:
                    webUrl = WebUtils.getRequestUrl(WebUtils.CG_XD);
                    break;
                case 3:
                    webUrl = WebUtils.getRequestUrl(WebUtils.CG_XD);
                    break;
                case 4:
                    webUrl = WebUtils.getRequestUrl(WebUtils.JF_ORDER);
                    break;
            }
            OkHttpUtils.post()
                    .url(webUrl)
                    .addParams("json", jsonObj)
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
                                    String orderId = check.getData().toString();
                                    if ("1".equals(zfType)) {
                                        /*微信*/
                                        CommonUtils.paybywx(mContext, orderId, payFor);
                                    } else if ("2".equals(zfType)) {
                                        /*支付宝*/
                                        paybyali(totalMoney, orderId, matidshow, payFor);
                                    } else if ("3".equals(zfType)) {
                                        inputPaypwd(orderId, payFor,null);
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        } else {
            CommonUtils.showToast(mContext, R.string.TheNetIsUnAble);
        }

    }

    /*支付宝支付*/
    public void paybyali(String totalMoney,String orderId,String matidshow,int payFor) {
        String orderInfo = "";
        String orderInfo_en = "";
        try {
            switch (payFor){
                case 0:
                    orderInfo = GetOrderInfo.getOrderInfo(orderId,
                            URLEncoder.encode(matidshow,
                                    "UTF-8"), orderId, totalMoney,WebUtils.getRequestUrl(WebUtils.ZFB_TZ));
                    break;
                case 1:
                    orderInfo = GetOrderInfo.getOrderInfo(orderId,
                            URLEncoder.encode(matidshow,
                                    "UTF-8"), orderId, totalMoney,WebUtils.getRequestUrl(WebUtils.ZFB_BX));
                    break;
                case 2:
                    orderInfo = GetOrderInfo.getOrderInfo(orderId,
                            URLEncoder.encode(matidshow,
                                    "UTF-8"), orderId, totalMoney,WebUtils.getRequestUrl(WebUtils.ZFB_CG));
                    break;
                case 3:
                    orderInfo = GetOrderInfo.getOrderInfo(orderId,
                            URLEncoder.encode(matidshow,
                                    "UTF-8"), orderId, totalMoney,WebUtils.getRequestUrl(WebUtils.ZFB_LX));
                    break;
                case 4:
                    orderInfo = GetOrderInfo.getOrderInfo(orderId,
                            URLEncoder.encode(matidshow,
                                    "UTF-8"), orderId, totalMoney,WebUtils.getRequestUrl(WebUtils.ZFB_CG));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            /**
             * 仅需对sign 做URL编码
             */
            orderInfo_en = URLEncoder.encode(orderInfo, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (CommonUtils.isNetworkAvailable(mContext)) {
            final String finalOrderInfo = orderInfo;
            OkHttpUtils.get()
                    .url(WebUtils.getRequestUrl(WebUtils.ZFB_URL)+"?json="
                            + orderInfo_en)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }
                        @Override
                        public void onResponse(final String response, int id) {

                            Check check = new Gson().fromJson(response, Check.class);

                            switch (check.getErr()) {
                                case 0:
                                    final String payInfo =
                                            finalOrderInfo + "&sign=\"" + check.getMsg() + "\"&"
                                                    + "sign_type=\"RSA\"";
                                    postZfb(payInfo);
//                                    Runnable payRunnable = new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            // 构建PayTask对象
//                                            PayTask alipay = new PayTask(EnsureOrderActivity.this);
//                                            // 调用支付接口，获取支付结果
//                                            String result = alipay.pay(payInfo, true);
//                                            Message msg = new Message();
//                                            msg.what = SDK_PAY_FLAG;
//                                            msg.obj = result;
//                                            mHandler.sendMessage(msg);
//                                        }
//                                    };
//                                    // 必须异步调用
//                                    Thread payThread = new Thread(payRunnable);
//                                    payThread.start();
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

    /**
     * 提交支付宝订单弹出支付宝框框
     */
    protected void postZfb(String payInfo) {

    }
    public void inputPaypwd(final String orderId, final int payFor,final Handler handler){
        PassWordDialog passWordDialog = new PassWordDialog(mContext);
        passWordDialog.setTitle("输入支付密码");
        passWordDialog.setCompleteListener(new DialogCompleteListener() {
            @Override
            public void dialogCompleteListener(String money, String pwd) {
                validateShopPayPwd(orderId,pwd, payFor,handler);
            }
        });
        passWordDialog.show();
    }


    /**支付密码验证*/
    public void validateShopPayPwd(final String orderId, final String payPwd, final int payFor, final Handler handler){
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
                                    CommonUtils.payByJk(mContext,orderId,payFor,handler);
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


}
