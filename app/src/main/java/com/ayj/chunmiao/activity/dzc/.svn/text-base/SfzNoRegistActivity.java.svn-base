package com.ayj.chunmiao.activity.dzc;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.XyBean;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 无身份证注册
* */
public class SfzNoRegistActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_sfz)
    EditText mEtSfz;
    @BindView(R.id.et_ma)
    EditText mEtMa;
    @BindView(R.id.et_ma_sure)
    EditText mEtMaSure;
    @BindView(R.id.et_jz)
    EditText mEtJz;
    @BindView(R.id.cb_register_agreement)
    CheckBox mCbRegisterAgreement;
    @BindView(R.id.tv_register_serviceAgreement)
    TextView mTvRegisterServiceAgreement;
    @BindView(R.id.btn_register)
    TextView mBtnRegister;

    XyBean mXyBean;

    Dialog dialog;
    @BindView(R.id.et_name)
    EditText mEtName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sfz_no_regist;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("注册");
        /*check点击事件*/
        mCbRegisterAgreement.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            mBtnRegister.setBackground(
                                    getResources().getDrawable(R.drawable.blue_button_background));
                            mBtnRegister.setEnabled(true);
                        } else {
                            mBtnRegister.setBackground(getResources().getDrawable(
                                    R.drawable.grey_no_check_background));
                            mBtnRegister.setEnabled(false);
                        }
                    }
                });
    }

    @Override
    public void configViews() {

    }


    @OnClick({R.id.iv_back, R.id.btn_register, R.id.tv_register_serviceAgreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_register:
                doCheck();
                break;
            case R.id.tv_register_serviceAgreement:
                /*点击协议*/
                getXz();
                break;
        }
    }

    /*点击协议*/
    private void getXz() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("snid", Constant.NO_ZC_XY);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post().
                    url(WebUtils.getRequestUrl(WebUtils.XY_URL))
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
                                    mXyBean = new Gson().fromJson(response, XyBean.class);
                                    showXyDialog(mXyBean);
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

    /*
    * 协议dialog
    * */
    public void showXyDialog(XyBean xyBean) {
        dialog = new Dialog(mContext, R.style.base_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.xy_item);
    /*设置标题*/
        TextView tv = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tv1 = (TextView) dialog.findViewById(R.id.tv_content);
        tv.setText(xyBean.getData().get(0).getTitle());
        tv1.setText(Html.fromHtml(xyBean.getData().get(0).getAcomment()));
        TextView btn_register = (TextView) dialog.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    /*验证注册*/
    private void doCheck() {
        if (TextUtils.isEmpty(mEtSfz.getText().toString().trim())) {
            showToast("身份证不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEtName.getText().toString().trim())) {
            showToast("真实姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEtMa.getText().toString().trim())) {
            showToast("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEtMaSure.getText().toString().trim())) {
            showToast("确认密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(mEtJz.getText().toString().trim())) {
            showToast("居住地点不能为空");
            return;
        }
        if (!mEtMa.getText().toString().trim().equals(mEtMaSure.getText().toString().trim())) {
            showToast("两次输入的密码不一致");
            return;
        }
        if (!ValidationUtils.checkCard(mEtSfz.getText().toString().trim())) {
            showToast("请输入正确的身份证号码");
            return;
        }
        btnRegister();
    }

    /*注册*/
    private void btnRegister() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("idcard", mEtSfz.getText().toString().trim());
                obj.put("pwd", MD5Utils.getMD5String(mEtMa.getText().toString().trim()));
                obj.put("pwd_original", mEtMa.getText().toString().trim());
                obj.put("homeaddr", mEtJz.getText().toString().trim());
                obj.put("name", mEtName.getText().toString());
                obj.put("shopid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getShopid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.setMsg("正在注册中...");
            mProgressHub.show();
            OkHttpUtils.post().
                    url(WebUtils.getRequestUrl(WebUtils.SFZ_URL))
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
                                    EventBus.getDefault().post(
                                            new FirstEvent("CustomActivityClick"));
                                    showToast(check.getMsg());
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