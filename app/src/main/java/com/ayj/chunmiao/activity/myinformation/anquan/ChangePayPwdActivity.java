package com.ayj.chunmiao.activity.myinformation.anquan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.MD5Utils;
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

public class ChangePayPwdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_old)
    EditText etOld;
    @BindView(R.id.et_new)
    EditText etNew;
    @BindView(R.id.et_sure)
    EditText etSure;
    @BindView(R.id.tv_post)
    TextView tvPost;
    @BindView(R.id.rl_old)
    RelativeLayout rlOld;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.btn_get_yzm)
    Button btnGetYzm;
    @BindView(R.id.ll_vcode)
    LinearLayout llVcode;
    private String type;
    private String webUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_pay_pwd;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("支付密码修改");
        type = getIntent().getStringExtra("type");
        if (type.equals("updata")){
            rlOld.setVisibility(View.VISIBLE);
            llVcode.setVisibility(View.GONE);
            webUrl = WebUtils.getRequestUrl(WebUtils.UPD_SHOP_PAY_PWD);
        }else if (type.equals("reset")){
            webUrl = WebUtils.getRequestUrl(WebUtils.RESET_SHOP_PAY_PWD);
            rlOld.setVisibility(View.GONE);
            llVcode.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_post,R.id.btn_get_yzm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post:
                if (etOld.getText().length() < 6 | etNew.getText().length() < 6 | etSure.getText().length() < 6) {
                    showToast("支付密码为6位数字");
                } else if (!etNew.getText().toString()
                        .equals(etSure.getText().toString())) {
                    showToast("新密码两次输入不一致");
                } else {
                    setPayPwd();
                }
                break;
            case R.id.btn_get_yzm:
                btnCountDown();
                getYzm();
                break;
        }
    }

    private void getYzm() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("mobile", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getMobile());
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

    private void setPayPwd() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("paypwdold", MD5Utils.getMD5String(etOld.getText().toString().trim()));
                object.put("paypwdnew", MD5Utils.getMD5String(etNew.getText().toString().trim()));
                object.put("paypwd", MD5Utils.getMD5String(etNew.getText().toString().trim()));
                object.put("vcode", etYzm.getText().toString().trim());
                object.put("mobile",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getMobile());

            }catch (JSONException e ){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(webUrl)
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
                                    showToast("支付密码设置成功");
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
}

