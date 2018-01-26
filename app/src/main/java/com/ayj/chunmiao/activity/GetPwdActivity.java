package com.ayj.chunmiao.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.CountDownButtonHelper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class GetPwdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.get_verificationCode)
    Button getVerificationCode;
    @BindView(R.id.et_ma)
    EditText etMa;
    @BindView(R.id.et_ma_sure)
    EditText etMaSure;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pwd);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_get_pwd;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("找回密码");
    }

    @Override
    public void configViews() {

    }

    /*获取验证码*/
    private void getYzNumber(){
        if(TextUtils.isEmpty(etPhone.getText().toString().trim())){
            showToast("手机号不能为空");
            return;
        }
        if(!ValidationUtils.checkTelPhone(etPhone.getText().toString().trim())){
            showToast("手机号非法");
            return;
        }
        if(!ValidationUtils.checkTelPhone(etPhone.getText().toString().trim())){
            showToast("请输入正确的验证码");
            return;
        }
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("mobile",etPhone.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.setMsg("请求验证码中...");
            mProgressHub.show();
            OkHttpUtils.post().
                    url(WebUtils.getRequestUrl(WebUtils.HQ_YZ))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    btnCountDown();
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
     * 按钮倒计时
     */
    private void btnCountDown() {
        CountDownButtonHelper helper = new CountDownButtonHelper(getVerificationCode, "",
                60, 1);

        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

            @Override
            public void finish() {

            }
        });
        helper.start();
    }

    private void doCheck(){
        if(TextUtils.isEmpty(etPhone.getText().toString().trim())){
            showToast("手机号不能为空");
            return;
        }
        if(TextUtils.isEmpty(etYzm.getText().toString().trim())){
            showToast("验证码不能为空");
            return;
        }
        if(TextUtils.isEmpty(etMa.getText().toString().trim())){
            showToast("密码不能为空");
            return;
        }
        if(TextUtils.isEmpty(etMaSure.getText().toString().trim())){
            showToast("确认密码不能为空");
            return;
        }
        if(!etMa.getText().toString().trim().equals(etMaSure.getText().toString().trim())){
            showToast("两次输入的密码不一致");
            return;
        }
        if(!ValidationUtils.checkTelPhone(etPhone.getText().toString().trim())){
            showToast("请输入正确的手机号");
            return;
        }
        getPwd();
    }

    private void getPwd() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("mobile",etPhone.getText().toString().trim());
                obj.put("newpwd", MD5Utils.getMD5String(etMa.getText().toString().trim()));
                obj.put("vcode",etYzm.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.setMsg("正在找回中...");
            mProgressHub.show();
            OkHttpUtils.post().
                    url(WebUtils.getRequestUrl(WebUtils.RESET_PWD))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    showToast(check.getMsg());
                                    finish();
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

    @OnClick({R.id.iv_back, R.id.get_verificationCode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.get_verificationCode:
                getYzNumber();
                break;
            case R.id.btn_register:
                doCheck();
                break;
        }
    }
}
