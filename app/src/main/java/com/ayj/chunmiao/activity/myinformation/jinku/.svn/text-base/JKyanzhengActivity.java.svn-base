package com.ayj.chunmiao.activity.myinformation.jinku;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.CountDownButtonHelper;
import com.ayj.chunmiao.view.PayWayPopupWindow;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
/**提现验证*/
public class JKyanzhengActivity extends BaseActivity {

    @BindView(R.id.iv_pay_logo)
    ImageView ivPayLogo;
    @BindView(R.id.tv_pay_way)
    TextView tvPayWay;
    @BindView(R.id.ll_pay_way)
    LinearLayout llPayWay;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_yz_content)
    TextView tvYzContent;
    @BindView(R.id.tv_yz_money)
    TextView tvYzMoney;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.btn_get_yzm)
    Button btnGetYzm;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.tv_phonenum)
    TextView tvPhonenum;

    int payWay = 0 ;
    private String phoneNum, phoneBm,money;

    PayWayPopupWindow hoperatePopupWindow;
    List<UtilityItem> itemList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_jkyanzheng;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("短信验证");
        phoneNum = AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getMobile().toString();
        money = getIntent().getStringExtra("money");
        tvYzMoney.setText("￥" + money);
        phoneBm = phoneNum.replace(phoneNum.substring(3, 7), "*****");


        itemList.add(new UtilityItem("支付宝",R.mipmap.zhifufangshi_zhifubao));
        itemList.add(new UtilityItem("银行卡",R.mipmap.zhifufangshi_yinlian));
    }

    @Override
    public void configViews() {
        etYzm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(etYzm.getText().toString().trim())) {
                    btnConfirm.setEnabled(false);
                    btnConfirm.setBackgroundResource(R.drawable.bg_button_notclick_radius);
                } else {
                    btnConfirm.setEnabled(true);
                    btnConfirm.setBackgroundResource(R.drawable.blue_button_background);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.iv_back, R.id.btn_get_yzm, R.id.btn_confirm,R.id.ll_pay_way})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_get_yzm:
                tvPhonenum.setText("验证码已发送到密保手机" + phoneBm + ",请查收");
                getYanzm();
                break;
            case R.id.btn_confirm:
                switch (payWay){
                    case 0 :
                        Intent zfbIntent = new Intent(mContext,TxAcountActivity.class);
                        zfbIntent.putExtra("vcode",etYzm.getText().toString().trim());
                        zfbIntent.putExtra("withdrawnum",money);
                        startActivity(zfbIntent);
                        break;
                    case 1 :
                        Intent ylintent = new Intent(mContext, BankAcountActivity.class);
                        ylintent.putExtra("vcode",etYzm.getText().toString().trim());
                        ylintent.putExtra("withdrawnum",money);
                        startActivity(ylintent);
                        break;
                }
                break;
            case R.id.ll_pay_way:
                showPaywayPop();
                break;
        }
    }

    private void showPaywayPop() {
        hoperatePopupWindow = new PayWayPopupWindow(mContext, "选择提现方式","小金库",money, itemList, new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0 :
                        payWay = 0;
                        ivPayLogo.setImageResource(R.mipmap.zhifufangshi_zhifubao);
                        tvPayWay.setText("支付宝");
                        hoperatePopupWindow.dismiss();
                        break;

                    case 1 :
                        payWay = 1;
                        ivPayLogo.setImageResource(R.mipmap.zhifufangshi_yinlian);
                        tvPayWay.setText("银行卡");
                        hoperatePopupWindow.dismiss();
                        break;
                }
            }
        },null,2);
        hoperatePopupWindow.showAtLocation(btnConfirm, Gravity.BOTTOM,0,0);
        hoperatePopupWindow.update();
    }

    private void getYanzm() {
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
}
