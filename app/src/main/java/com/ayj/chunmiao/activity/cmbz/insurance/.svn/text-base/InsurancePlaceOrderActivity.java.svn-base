package com.ayj.chunmiao.activity.cmbz.insurance;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.InsurancePriceBean;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
/*
* 下单支付
* */

public class InsurancePlaceOrderActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_icsnidshow)
    TextView tvIcsnidshow;
    @BindView(R.id.tv_issnidshow)
    TextView tvIssnidshow;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.cb_dzbd)
    CheckBox cbDzbd;
    @BindView(R.id.ed_emailaddress)
    EditText edEmailaddress;
    @BindView(R.id.ll_email)
    LinearLayout llEmail;
    @BindView(R.id.cb_zzbd)
    CheckBox cbZzbd;
    @BindView(R.id.ed_tbrname)
    EditText edTbrname;
    @BindView(R.id.ll_lin1)
    LinearLayout llLin1;
    @BindView(R.id.ed_detail)
    EditText edDetail;
    @BindView(R.id.ll_lin3)
    LinearLayout llLin3;
    @BindView(R.id.ed_mobile)
    EditText edMobile;
    @BindView(R.id.ll_lin4)
    LinearLayout llLin4;
    @BindView(R.id.cb_1)
    CheckBox cb1;
    @BindView(R.id.cb_2)
    CheckBox cb2;
    @BindView(R.id.tv_ensure)
    TextView tvEnsure;
    InsurancePriceBean.DataBean dataBean;
    private  String total,email,addr,mobil,name,userId,userpwd,bdsendtype ="BDSENDTYPE001";
    private UserPerson userPerson;


    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_pay;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("下单支付");
        dataBean = (InsurancePriceBean.DataBean)getIntent().getSerializableExtra("dataBean");
        total=getIntent().getStringExtra("total");
        userPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        userpwd = userPerson.getData().get(0).getPassWord();
        userId = userPerson.getData().get(0).getSnid();
    }

    @Override
    public void configViews() {
        tvPrice.setText("¥"+total);
        tvIcsnidshow.setText(dataBean.getIcsnidshow());
        tvIssnidshow.setText(dataBean.getIssnidshow());
        cbDzbd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        cbZzbd.setChecked(false);
                        bdsendtype ="BDSENDTYPE001";
                        llLin1.setVisibility(View.GONE);
                        llLin3.setVisibility(View.GONE);
                        llLin4.setVisibility(View.GONE);
                        llEmail.setVisibility(View.VISIBLE);
                    }
            }
        });
        cbZzbd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    cbDzbd.setChecked(false);
                    bdsendtype ="BDSENDTYPE002";
                    llLin1.setVisibility(View.VISIBLE);
                    llLin3.setVisibility(View.VISIBLE);
                    llLin4.setVisibility(View.VISIBLE);
                    llEmail.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_ensure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_ensure:
                email = edEmailaddress.getText().toString().trim();
                name = edTbrname.getText().toString().trim();
                addr = edDetail.getText().toString().trim();
                mobil = edMobile.getText().toString().trim();

                if(cbDzbd.isChecked()){
                        if(!TextUtils.isEmpty(email)){
                            sendorder();
                        }else {
                            showToast("请填写电子邮箱");
                        }
                }else if(cbZzbd.isChecked()){
                        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(addr)&&!TextUtils.isEmpty(mobil)){
                            sendorder();
                        }else {
                            showToast("请填写完整信息");
                        }
                }
                break;
        }
    }

    private void sendorder() {
        if(cb1.isChecked()&&cb2.isChecked()){
            if(CommonUtils.isNetworkAvailable(mContext)){
                JSONObject obj = new JSONObject();
                try {
                    obj.put("key", "");
                    obj.put("msnid",userId);
                    obj.put("pwd",userpwd);
                    obj.put("insorderid",dataBean.getSnid());
                    obj.put("bdsendtype",bdsendtype);
                    obj.put("bdsjrname",name);
                    obj.put("bdsjraddr",addr);
                    obj.put("bdsjrmobile",mobil);
                    obj.put("inscompid","33");

                }catch (JSONException e){
                    e.printStackTrace();
                }
                OkHttpUtils.post()
                        .url(WebUtils.getRequestUrl(WebUtils.UPDATE_SENDTYPE_URL))
                        .addParams("json",String.valueOf(obj))
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                showToast(R.string.TheNetIsException);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Check check = new Gson().fromJson(response,Check.class);
                                switch (check.getErr()){
                                    case 0 :
                                        Intent intent = new Intent(mContext,InsurancePaytypeActivity.class);
                                        intent.putExtra("money",total);
                                        intent.putExtra("orderid",dataBean.getSnid());
                                        intent.putExtra("type","insurance");
                                        startActivity(intent);
                                        break;
                                    default:
                                        showToast(check.getMsg());
                                }
                            }
                        });

            }else {
                showToast(R.string.TheNetIsUnAble);
            }
        }else{
            showToast("请仔细阅读《保险协议》和《经纪人委托协议》并同意");
        }
    }
}
