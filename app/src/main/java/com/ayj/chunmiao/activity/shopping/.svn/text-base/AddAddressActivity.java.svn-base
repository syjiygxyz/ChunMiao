package com.ayj.chunmiao.activity.shopping;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


/*
*编辑收货地址
* */
public class AddAddressActivity extends BaseActivity {

    UserPerson mUserPerson;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.tv_dq)
    TextView mTvDq;
    @BindView(R.id.et_xq)
    EditText mEtXq;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_yx)
    EditText mEtYx;
    @BindView(R.id.tv_post)
    TextView mTvPost;


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initDatas() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        mTvTitle.setText("填写收货地址");
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_dq, R.id.tv_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_dq:
                CommonUtils.getDqPickerView(mContext, mTvDq);
                break;
            case R.id.tv_post:
                doClick();
                break;
        }
    }

    private void doClick() {
        if (TextUtils.isEmpty(mEtName.getText().toString())) {
            showToast("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(mTvDq.getText().toString())) {
            showToast("请选择地区");
            return;
        }
        if (TextUtils.isEmpty(mEtXq.getText().toString())) {
            showToast("请填写你的详细地址");
            return;
        }
        if (TextUtils.isEmpty(mEtPhone.getText().toString())) {
            showToast("请填写你的手机号");
            return;
        }
        if (TextUtils.isEmpty(mEtPhone.getText().toString())) {
            showToast("请填写你的邮编");
            return;
        }
        if (!ValidationUtils.checkTelPhone(mEtPhone.getText().toString())) {
            showToast("输入的手机号有误");
            return;
        }
        if (!ValidationUtils.checkYb(mEtYx.getText().toString())) {
            showToast("请填写你的邮编");
            return;
        }
        doPost();
    }

    private void doPost() {

        if (CommonUtils.isNetworkAvailable(AddAddressActivity.this)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.ADD_NEW_ADDRESS))
                    .addParams("json","{\"key\":\"\",\"userid\":\"" + mUserPerson.getData().get(0).getSnid() + "\",\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()
                            + "\",\"provinceid\":\"" + mTvDq.getText().toString().split(" ")[0] + "\",\"cityid\":\"" + mTvDq.getText().toString().split(" ")[1] + "\",\"areaid\":\"" + mTvDq.getText().toString().split(" ")[2]+"\",\"receivename\":\"" + mEtName.getText().toString().trim()
                            + "\",\"detailaddr\":\"" + mEtXq.getText().toString().trim() + "\",\"postcode\":\"" + mEtYx.getText().toString().trim() + "\",\"mobile\":\"" + mEtPhone.getText().toString().trim()
                            + "\",\"tel\":\"" + "" + "\",\"isdefaultaddr\":\"" + "SFPD001" + "\"}")
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
                                    Toast.makeText(AddAddressActivity.this, "添加成功",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                    break;
                                default:
                                    Toast.makeText(AddAddressActivity.this, "添加失败",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
}

