package com.ayj.chunmiao.activity.myinformation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.MainActivity;
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
* 修改密码
* */
public class XgPassWordActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_old)
    EditText mEtOld;
    @BindView(R.id.et_new)
    EditText mEtNew;
    @BindView(R.id.et_sure)
    EditText mEtSure;
    @BindView(R.id.tv_post)
    TextView mTvPost;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xg_pass_word;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("修改密码");
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post:
                doClick();
                break;
        }
    }

    private void doClick() {
        if (TextUtils.isEmpty(mEtOld.getText().toString().trim())) {
            showToast("请输入原密码");
            return;
        }
        if (TextUtils.isEmpty(mEtNew.getText().toString().trim())) {
            showToast("请输如新密码");
            return;
        }
        if (TextUtils.isEmpty(mEtSure.getText().toString())) {
            showToast("请输入确定的密码");
            return;
        }
        if (!mEtNew.getText().toString().trim().equals(mEtSure.getText().toString())) {
            showToast("两次输入的密码不相同，请重新输入");
            return;
        }
        postPassWord();
    }

    private void postPassWord() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("oldpwd",MD5Utils.getMD5String(mEtOld.getText().toString().trim()));
                obj.put("newpwd",MD5Utils.getMD5String(mEtNew.getText().toString().trim()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XG_URL))
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
                                    showToast(check.getMsg());
                                    UserBean user =
                                            (UserBean) aCache.getAsObject(ACache.USER_INFO_KEY);
                                    user.getData().get(0).setPassWord(MD5Utils.getMD5String(mEtNew.getText().toString().trim()));
                                    aCache.put(ACache.USER_INFO_KEY,user);
                                    AyjSwApplication.getsInstance().setUserInfo(user);
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
