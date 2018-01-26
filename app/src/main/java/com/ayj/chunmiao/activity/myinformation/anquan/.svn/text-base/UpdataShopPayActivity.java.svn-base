package com.ayj.chunmiao.activity.myinformation.anquan;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.CountDownButtonHelper;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class UpdataShopPayActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_phonenum)
    TextView tvPhonenum;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.btn_get_yzm)
    Button btnGetYzm;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    private String phoneNum, phoneBm,payPassWord;
    PassWordDialog passWordDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_updata_shop_pay;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("短信验证");
        phoneNum = AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getTel().toString();
        phoneBm = phoneNum.replace(phoneNum.substring(3, 7), "*****");
    }

    @Override
    public void configViews() {
        etYzm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etYzm.getText().toString().trim())){
                    btnConfirm.setEnabled(false);
                    btnConfirm.setBackgroundResource(R.drawable.bg_button_notclick_radius);
                }else {
                    btnConfirm.setEnabled(true);
                    btnConfirm.setBackgroundResource(R.drawable.blue_button_background);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.btn_get_yzm, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_get_yzm:
                tvPhonenum.setText("验证码已发送到密保手机" + phoneBm + ",请查收");
                getYzm();
                break;
            case R.id.btn_confirm:
                showPwdDialog("输入新的支付密码");
                break;
        }
    }

    private void getYzm() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("mobile", phoneNum);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.HQ_YZ))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    tvPhonenum.setVisibility(View.VISIBLE);
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
    private void btnCountDown() {
        CountDownButtonHelper helper = new CountDownButtonHelper(btnGetYzm, "",
                60, 1);

        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

            @Override
            public void finish() {

            }
        });
        helper.start();
    }
    private void showPwdDialog(String title) {
        passWordDialog = new PassWordDialog(mContext);
        passWordDialog.setTitle(title);
        passWordDialog.setCompleteListener(new DialogCompleteListener() {
            @Override
            public void dialogCompleteListener(String money, String pwd) {
                if (payPassWord == null){
                    payPassWord = pwd;
                    showPwdDialog("再次输入");
                }else if(payPassWord.equals(pwd)){
                    payPassWord = null;
                    updataPaypwd(pwd);

                }else{
                    payPassWord = null;
                    showToast("两次输入不一致,设置失败");
                }

            }
        });
        passWordDialog.show();
    }

    private void updataPaypwd(String pwd) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("paypwdold","d6b69363deb6c8aabb796ebc9fd1e3eb");
                object.put("paypwdnew", MD5Utils.getMD5String(pwd));
            }catch (JSONException e ){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.UPD_SHOP_PAY_PWD))
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
                                case 0 :
                                    finish();
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
}
