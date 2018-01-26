package com.ayj.chunmiao.activity.cmbz.insurance.common;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.bx.CommonBxListBean;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.ayj.chunmiao.view.TimePicker1.TimePickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zht-pc-04 on 2016/9/27.
 * 投保日期二
 */
public class BxLjtb2Activity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ed_bbr_name)
    EditText edBbrName;
    @BindView(R.id.ed_bbr_idcard)
    EditText edBbrIdcard;
    @BindView(R.id.tv_relationship)
    TextView tvRelationship;
    @BindView(R.id.tv_btime)
    TextView tvBtime;
    @BindView(R.id.tv_etime)
    TextView tvEtime;
    @BindView(R.id.tv_bbr_sex)
    TextView tvBbrSex;
    @BindView(R.id.tv_bbr_birth)
    TextView tvBbrBirth;
    @BindView(R.id.ed_tbr_name)
    EditText edTbrName;
    @BindView(R.id.tv_tbr_sex)
    TextView tvTbrSex;
    @BindView(R.id.ed_tbr_idcard)
    EditText edTbrIdcard;
    @BindView(R.id.ed_tbr_email)
    EditText edTbrEmail;
    @BindView(R.id.ed_tbr_phonenum)
    EditText edTbrPhonenum;
    @BindView(R.id.tv_tbr_birth)
    TextView tvTbrBirth;
    @BindView(R.id.cb_dzbd)
    CheckBox cbDzbd;//电子
    @BindView(R.id.cb_zzbd)
    CheckBox cbZzbd;//纸质
    @BindView(R.id.ed_bdsjrname)
    EditText edBdsjrname;
    @BindView(R.id.ed_basjraddr_detail)
    EditText edBasjraddrDetail;
    @BindView(R.id.ed_bdsjrmobile)
    EditText edBdsjrmobile;
    @BindView(R.id.ll_lin4)
    LinearLayout llLin4;
    @BindView(R.id.cb_ljtb2)
    CheckBox cbLjtb2;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv_dz)
    TextView tv_dz;

    List<UtilityItem> itemList = new ArrayList<>();

    private OperatePopupWindow pop;// 弹出的popwindow

    private TimePickerView timePickerView;

    @BindView(R.id.ll)
    LinearLayout ll;

    UserPerson mUserPerson;

    CommonBxListBean.DataBean list;

    String bdsendtype;

    String money;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bx_ljtb2;
    }

    @Override
    public void initDatas() {
        bdsendtype="BDSENDTYPE001";
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        tvBtime.setText(getIntent().getStringExtra("btime"));
        tvEtime.setText(getIntent().getStringExtra("etime"));
        list = (CommonBxListBean.DataBean) getIntent().getSerializableExtra("info");
        tvTitle.setText("在线投保");
        money = getIntent().getStringExtra("money");
    }

    @Override
    public void configViews() {
        cbDzbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbZzbd.isChecked()){
                    cbDzbd.setChecked(true);
                    cbZzbd.setChecked(false);
                }else{
                    cbDzbd.setChecked(true);

                }
                ll.setVisibility(View.GONE);
                bdsendtype="BDSENDTYPE001";
            }
        });
        cbZzbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbDzbd.isChecked()){
                    cbDzbd.setChecked(false);
                    cbZzbd.setChecked(true);
                }else{
                    cbZzbd.setChecked(true);
                }
                /*显示布局*/
                ll.setVisibility(View.VISIBLE);
                bdsendtype="BDSENDTYPE002";
            }
        });
    }

    //申请体验用户什么的
    private void addData(int type) {
        itemList.clear();
        if (type == 1) {
            //非体验用户
            itemList.add(new UtilityItem("本人","RELATIONSHIP001"));
            itemList.add(new UtilityItem("配偶","RELATIONSHIP003"));
            itemList.add(new UtilityItem("父母","RELATIONSHIP002"));
            itemList.add(new UtilityItem("子女","RELATIONSHIP004"));
            itemList.add(new UtilityItem("其他","RELATIONSHIP005"));
        } else if (type == 2) {
            //体验用户
            itemList.add(new UtilityItem("男","SEX001"));
            itemList.add(new UtilityItem("女","SEX002"));
        }
    }

    private void doCheck() {
        if (TextUtils.isEmpty(edBbrName.getText().toString().trim())) {
            showToast("请填写被保人的名字");
            return;
        }
        if (TextUtils.isEmpty(edTbrName.getText().toString().trim())) {
            showToast("请填写投保人的名字");
            return;
        }
        if (TextUtils.isEmpty(edBbrIdcard.getText().toString().trim())) {
            showToast("请填写被保人的身份证号");
            return;
        }
        if(cbZzbd.isChecked()){
            if (TextUtils.isEmpty(edBdsjrname.getText().toString().trim())) {
                showToast("请填写收件人姓名");
                return;
            }
            if (TextUtils.isEmpty(tv_dz.getText().toString().trim())) {
                showToast("请填写收件人地址");
                return;
            }
            if (TextUtils.isEmpty(edBasjraddrDetail.getText().toString().trim())) {
                showToast("请填写收件人详细地址");
                return;
            }
            if (TextUtils.isEmpty(edBdsjrmobile.getText().toString().trim())) {
                showToast("请填写收件人手机号");
                return;
            }
            if (!ValidationUtils.checkTelPhone(edBdsjrmobile.getText().toString().trim())) {
                showToast("请检查收件人手机号");
                return;
            }
        }

        if (!ValidationUtils.checkCard(edBbrIdcard.getText().toString().trim())) {
            showToast("请检查被保人的身份证号");
            return;
        }
        if (TextUtils.isEmpty(edTbrIdcard.getText().toString().trim())) {
            showToast("请填写投保人的身份证号");
            return;
        }
        if (!ValidationUtils.checkCard(edTbrIdcard.getText().toString().trim())) {
            showToast("请检查投保人的身份证号");
            return;
        }
        if (TextUtils.isEmpty(tvRelationship.getText().toString().trim())) {
            showToast("请选择被保人与你关系");
            return;
        }
        if (TextUtils.isEmpty(tvBbrSex.getText().toString().trim())) {
            showToast("请选择被保人的性别");
            return;
        }
        if (TextUtils.isEmpty(tvTbrSex.getText().toString().trim())) {
            showToast("请选择投保人的性别");
            return;
        }
        if (TextUtils.isEmpty(tvBbrBirth.getText().toString().trim())) {
            showToast("请选择被保人的出生日期");
            return;
        }
        if (TextUtils.isEmpty(tvTbrBirth.getText().toString().trim())) {
            showToast("请选择投保人的出生日期");
            return;
        }
        if (TextUtils.isEmpty(edTbrEmail.getText().toString().trim())) {
            showToast("请填写投保人的邮箱");
            return;
        }
        if (!ValidationUtils.checkEmail(edTbrEmail.getText().toString().trim())) {
            showToast("请检查投保人的邮箱");
            return;
        }
        if (TextUtils.isEmpty(edTbrPhonenum.getText().toString().trim())) {
            showToast("请填写投保人的手机号");
            return;
        }
        if (!ValidationUtils.checkTelPhone(edTbrPhonenum.getText().toString().trim())) {
            showToast("请检查投保人的手机号");
            return;
        }
        if(cbLjtb2.isChecked()){
            doPost();
        }else{
            showToast("请同意挨一家经纪人委托协议");
        }
    }

    private void doPost() {
        if(CommonUtils.isNetworkAvailable(BxLjtb2Activity.this)){
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd-HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());//获取当前时间
            String curtime = formatter.format(curDate);
            JSONObject obj = new JSONObject();
            try {
                obj.put("key","");
                obj.put("msnid",mUserPerson.getData().get(0).getSnid());
                obj.put("pwd",mUserPerson.getData().get(0).getPassWord());
                obj.put("icsnid",getIntent().getStringExtra("snid"));
                obj.put("issnid",list.getSnid());
                obj.put("btime",tvBtime.getText().toString().trim());
                obj.put("etime",tvEtime.getText().toString().trim());
                obj.put("tbrname",edTbrName.getText().toString().trim());
                obj.put("tbrcardtype","IDCARDTYPE001");
                obj.put("tbrcardno",edTbrIdcard.getText().toString().trim());
                obj.put("tbrmobile",edTbrPhonenum.getText().toString().trim());
                obj.put("tbremail",edTbrEmail.getText().toString().trim());
                obj.put("tbrsex",tvTbrSex.getTag().toString().trim());
                obj.put("tbrbirthday",tvTbrBirth.getText().toString().trim());
                obj.put("relationship",tvRelationship.getTag().toString().trim());
                obj.put("bbrname",edBbrName.getText().toString().trim());
                obj.put("bbrcardtype","IDCARDTYPE001");
                obj.put("bbrcardno",edBbrIdcard.getText().toString().trim());
                obj.put("bbrsex",tvBbrSex.getTag().toString().trim());
                obj.put("bbrbirthday",tvBbrBirth.getText().toString().trim());
                obj.put("bbrcardno",edBbrIdcard.getText().toString().trim());
                obj.put("buynum","1");
                obj.put("paytype","999");
                obj.put("yjmoneytotal",money);
                obj.put("yhmoneytotal",money);
                obj.put("sfmoneytotal",money);
                obj.put("insordertype","INSORDERTYPE002");
                obj.put("insfrom","INSFROM001");
                obj.put("remark","xxx");
                obj.put("createdate",curtime);
                obj.put("shopid",mUserPerson.getData().get(0).getRegshopid());
                obj.put("bdsendtype",bdsendtype);
                if(cbZzbd.isChecked()){
                    obj.put("bdsjrname",edBdsjrname.getText().toString().trim());
                    obj.put("bdsjraddr",tv_dz.getText().toString().trim()+edBasjraddrDetail.getText().toString().trim());
                    obj.put("bdsjrmobile",edBdsjrmobile.getText().toString().trim());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.BX_FCX))
                    .addParams("json",String.valueOf(obj))
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
                                    showToast(check.getMsg());
                                    Intent intent=new Intent(BxLjtb2Activity.this,CommonBxPostDetailActivity.class);
                                    intent.putExtra("list",list);
                                    intent.putExtra("tname",edTbrName.getText().toString());
                                    intent.putExtra("tsex",tvTbrSex.getText().toString());
                                    intent.putExtra("tsfz",edTbrIdcard.getText().toString());
                                    intent.putExtra("tyx",edTbrEmail.getText().toString());
                                    intent.putExtra("tlx",edTbrPhonenum.getText().toString());
                                    intent.putExtra("tbirth",tvTbrBirth.getText().toString());
                                    intent.putExtra("bgx",tvRelationship.getText().toString().trim());
                                    intent.putExtra("bname",edBbrName.getText().toString());
                                    intent.putExtra("bsfz",edBbrIdcard.getText().toString());
                                    intent.putExtra("btime",tvBtime.getText().toString());
                                    intent.putExtra("etime",tvEtime.getText().toString());
                                    intent.putExtra("bsex",tvBbrSex.getText().toString());
                                    intent.putExtra("bbirth",tvBbrBirth.getText().toString());
                                    intent.putExtra("money",money);
                                    intent.putExtra("ms",getIntent().getStringExtra("ms"));
                                    intent.putExtra("date",check.getData()+"");
                                    startActivity(intent);
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

    @OnClick({R.id.iv_back, R.id.tv_relationship, R.id.tv_bbr_sex, R.id.tv_next, R.id.tv_bbr_birth, R.id.tv_tbr_sex, R.id.tv_tbr_birth, R.id.tv_dz})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_relationship:
                addData(1);
                pop = new OperatePopupWindow(mContext,
                        "请选择", itemList,
                        new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(
                                    BaseQuickAdapter adapter,
                                    View view, int position) {
                                tvRelationship.setText(itemList.get(position).getText());
                                tvRelationship.setTag(itemList.get(position).getTextId());
                                pop.dismiss();
                            }
                        });
                pop.showAtLocation(tvNext, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
                pop.update();
                break;
            case R.id.tv_bbr_sex:
                addData(2);
                pop = new OperatePopupWindow(mContext,
                        "请选择", itemList,
                        new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(
                                    BaseQuickAdapter adapter,
                                    View view, int position) {
                                tvBbrSex.setText(itemList.get(position).getText());
                                tvBbrSex.setTag(itemList.get(position).getTextId());
                                pop.dismiss();
                            }
                        });
                pop.showAtLocation(tvNext, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
                pop.update();
                break;
            case R.id.tv_tbr_sex:
                addData(2);
                pop = new OperatePopupWindow(mContext,
                        "请选择", itemList,
                        new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(
                                    BaseQuickAdapter adapter,
                                    View view, int position) {
                                tvTbrSex.setText(itemList.get(position).getText());
                                tvTbrSex.setTag(itemList.get(position).getTextId());
                                pop.dismiss();
                            }
                        });
                pop.showAtLocation(tvNext, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
                pop.update();
                break;
            case R.id.tv_next:
                doCheck();
                break;
            case R.id.tv_bbr_birth:
                timePickerView = new TimePickerView(mContext, TimePickerView.Type.YEAR_MONTH_DAY, 3);
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        tvBbrBirth.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_tbr_birth:
                timePickerView = new TimePickerView(mContext, TimePickerView.Type.YEAR_MONTH_DAY, 3);
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        tvTbrBirth.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_dz:
                CommonUtils.getDqPickerView(mContext, tv_dz);
                break;
        }
    }
}
