package com.ayj.chunmiao.activity.myinformation.jinku;

import android.os.Bundle;
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
import com.ayj.chunmiao.bean.myinformation.ShopBankBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**银行卡提现*/
public class BankAcountActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_bank_no)
    EditText etBankNo;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    String bankId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_acount;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("银行卡提现");
        etName.addTextChangedListener(this);
        etBankNo.addTextChangedListener(this);
    }

    @Override
    public void configViews() {
        //getBankCardInfo();
    }

    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                showPassWordDialog();
                break;
        }
    }
    private void showPassWordDialog() {
        PassWordDialog passWordDialog = new PassWordDialog(mContext);
        passWordDialog.setTitle("输入支付密码");
        passWordDialog.setCompleteListener(new DialogCompleteListener() {
            @Override
            public void dialogCompleteListener(String money, String pwd) {
                setShopWithdrawApply(pwd);
            }
        });
        passWordDialog.show();
    }

    private void setShopWithdrawApply(String pwd) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("paypwd",pwd);
                object.put("vcode",getIntent().getStringExtra("vcode"));
                object.put("withdrawnum",getIntent().getStringExtra("withdrawnum"));
                object.put("withdrawtype","PAYTYPE009");
                object.put("bankid",etBankNo.getText().toString().trim());
                object.put("bankno",etBankNo.getText().toString().trim());
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_WITHDRAW))
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

    private void getBankCardInfo() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
            }catch (JSONException e ){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_BANK_INFO))
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
                                    ShopBankBean shopBankBean = new Gson().fromJson(response,ShopBankBean.class);
                                    bankId = shopBankBean.getData().get(0).getBankno()==null ? "" : shopBankBean.getData().get(0).getBankno();
                                    etBankNo.setText(bankId);
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


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(etName.getText().toString().trim()) || TextUtils.isEmpty(etBankNo.getText().toString().trim())){
            btnConfirm.setBackgroundResource(R.drawable.bg_button_notclick_radius);
            btnConfirm.setEnabled(false);
        }else {
            btnConfirm.setBackgroundResource(R.drawable.blue_button_background);
            btnConfirm.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
