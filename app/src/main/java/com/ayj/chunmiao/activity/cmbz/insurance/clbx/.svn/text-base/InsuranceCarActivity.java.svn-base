package com.ayj.chunmiao.activity.cmbz.insurance.clbx;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.AreaInfo;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.InsuranceBrandBean;
import com.ayj.chunmiao.bean.cmbz.InsuranceCompanyBean;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.TimePicker1.TimePickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/25.
 * 车险购买
 */
public class InsuranceCarActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_kaiguan)
    ImageView ivKaiguan;
    @BindView(R.id.ll_date)
    LinearLayout llDate;
    @BindView(R.id.et_carno)
    EditText etCarno;
    @BindView(R.id.cb_isshanpai)
    CheckBox cbIsshanpai;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_idno)
    EditText etIdno;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.cb_hasread)
    CheckBox cbHasread;
    @BindView(R.id.spin_bxcx_province)
    Spinner sp_pro;
    @BindView(R.id.spin_bxcx_city)
    Spinner sp_city;
    private TimePickerView timePickerView;
    private String istransfercar = "SFPD002";

    private String province_id = null;

    private String city_id = null;


    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_car;
    }

    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        tvTitle.setText("购买车险");
        sp_pro.setPrompt("省份");
        sp_city.setPrompt("城市");
        initSpPro();
    }

    @Override
    public void configViews() {
        cbIsshanpai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etCarno.setVisibility(View.INVISIBLE);
                } else if (!isChecked) {
                    etCarno.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @OnClick({R.id.tv_date, R.id.iv_back, R.id.iv_kaiguan, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*过户时间选择*/
            case R.id.tv_date:
                timePickerView = new TimePickerView(mContext, TimePickerView.Type.YEAR_MONTH_DAY, 3);
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        tvDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                timePickerView.show();
                break;
            /*是否是过户车辆的开关*/
            case R.id.iv_kaiguan:
                if (llDate.getVisibility() == View.GONE) {
                    ivKaiguan.setImageResource(R.mipmap.switchon);
                    llDate.setVisibility(View.VISIBLE);
                    istransfercar = "SFPD001";
                } else {
                    ivKaiguan.setImageResource(R.mipmap.switchoff);
                    llDate.setVisibility(View.GONE);
                    istransfercar = "SFPD002";
                }
                break;
            case R.id.btn_next:
                doCheck();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
    }

    private void initSpPro() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
                    .addParams("json", "{\"key\":\"\",\"pid\":\"0001\"}")
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
                                    final AreaInfo areainfo_pro = new Gson().fromJson(response, AreaInfo.class);
                                    String[] strs = new String[areainfo_pro.getData().size()];
                                    for (int i = 0; i < areainfo_pro.getData().size(); i++) {
                                        strs[i] = areainfo_pro.getData().get(i).getName();
                                    }
                                    ArrayAdapter adapter = new ArrayAdapter<String>(mContext,
                                            android.R.layout.simple_spinner_item, strs);
                                    adapter.setDropDownViewResource(android.
                                            R.layout.simple_spinner_dropdown_item);
                                    sp_pro.setAdapter(adapter);
                                    sp_pro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            province_id = areainfo_pro.getData().get(i).getAid();
                                            initSpCity(areainfo_pro.getData().get(i).getAid());
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {

                                        }
                                    });
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    public void initSpCity(String pcode) {

        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
                    .addParams("json", "{\"key\":\"\",\"pid\":\"" + pcode + "\"}")
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
                                    final AreaInfo areainfo_city = new Gson().fromJson(response, AreaInfo.class);
                                    String[] strs = new String[areainfo_city.getData().size()];
                                    for (int i = 0; i < areainfo_city.getData().size(); i++) {
                                        strs[i] = areainfo_city.getData().get(i).getName();
                                    }
                                    ArrayAdapter adapter = new ArrayAdapter<String>(mContext,
                                            android.R.layout.simple_spinner_item, strs);
                                    adapter.setDropDownViewResource(android.
                                            R.layout.simple_spinner_dropdown_item);
                                    sp_city.setAdapter(adapter);
                                    sp_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                            city_id = areainfo_city.getData().get(i).getAid();
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {
                                        }
                                    });
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void doCheck() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            showToast("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(etIdno.getText().toString())) {
            showToast("请填写身份证号码");
            return;
        }
        if (!ValidationUtils.checkCard(etIdno.getText().toString())) {
            showToast("请填写身份证号码");
            return;
        }
        if (!cbHasread.isChecked()) {
            showToast("请同意协议内容");
            return;
        }
        doPost();
    }

    private void doPost() {
        getNext();

    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("carsetbeforefinish")) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getNext() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("insfrom", "INSFROM003");
                jsonObject.put("levelfrom", "1");
                jsonObject.put("levelto", "1");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_FGS_URL))
                    .addParams("json", String.valueOf(jsonObject))
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
                                    InsuranceCompanyBean company = new Gson().fromJson(response, InsuranceCompanyBean.class);
                                    getInsuranceBrand(company.getData().get(0).getAid());
                                    break;
                                default:
                                    showToast(check.getMsg());
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void getInsuranceBrand(String brokerId) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("brokerid", brokerId);
                jsonObject.put("areaid", city_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_SIGNEDBRAND_URL))
                    .addParams("json", String.valueOf(jsonObject))
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
                                    InsuranceBrandBean insuranceBrandBean = new Gson().fromJson(response, InsuranceBrandBean.class);
                                    if (insuranceBrandBean.getTotal().equals("0")) {
                                        showToast("本地区暂未有保险公司");
                                    } else {
                                        Intent intent = new Intent(mContext, InsuranceCompanyShowActivity.class);
                                        intent.putExtra("city_id", city_id);
                                        intent.putExtra("name", etName.getText().toString());
                                        intent.putExtra("sfz", etIdno.getText().toString());
                                        intent.putExtra("istransfercar", istransfercar);
                                        intent.putExtra("name", etName.getText().toString());
                                        if (istransfercar.equals("SFPD001")) {
                                            intent.putExtra("transferdate", tvDate.getText().toString());
                                        } else {
                                            intent.putExtra("transferdate", "");
                                        }
                                        startActivity(intent);
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
}

