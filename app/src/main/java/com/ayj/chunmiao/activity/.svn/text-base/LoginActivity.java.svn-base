package com.ayj.chunmiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UserBean;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 登录界面
* */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_login_ok)
    TextView mTvLoginOk;
    @BindView(R.id.ed_login_1)
    EditText mEdLogin1;//账号
    @BindView(R.id.ed_login_2)
    EditText mEdLogin2;//密码
    @BindView(R.id.tv_get_pwd)
    TextView tvGetPwd;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }


    @OnClick({R.id.tv_login_ok,R.id.tv_get_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_login_ok:
                submit();
                break;
            case R.id.tv_get_pwd:
                startActivity(new Intent(mContext,GetPwdActivity.class));
                break;
        }

    }

    private void submit() {
        if (TextUtils.isEmpty(mEdLogin1.getText().toString().trim())) {
            showToast("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(mEdLogin2.getText().toString().trim())) {
            showToast("请输入密码");
            return;
        }
        doLogin();
    }


    private void doLogin() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("mobile", mEdLogin1.getText().toString().trim());
                obj.put("loginpwd", MD5Utils.getMD5String(mEdLogin2.getText().toString().trim()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.LOGN_URL))
                    .addParams("json", String.valueOf(obj))
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
                                    UserBean userBean = new Gson().fromJson(response, UserBean.class);
                                    userBean.getData().get(0).setPassWord(MD5Utils.getMD5String(mEdLogin2.getText().toString().trim()));
                                    AyjSwApplication.getsInstance().setUserInfo(userBean);
                                    aCache.put(ACache.USER_INFO_KEY, userBean);
                                    showToast("登录成功");
                                    startActivity(new Intent(mContext, MainActivity.class));
                                    finish();
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
}
