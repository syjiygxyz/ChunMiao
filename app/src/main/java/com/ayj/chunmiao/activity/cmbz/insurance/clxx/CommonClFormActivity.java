package com.ayj.chunmiao.activity.cmbz.insurance.clxx;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.ProvinceBean;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.bx.Carbelong;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.cmbz.VehicleBean;
import com.ayj.chunmiao.bean.cmbz.cx.Carbrand;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
*车辆信息填写界面1为修改0为新增
* */
public class CommonClFormActivity extends BaseActivity {

    private OptionsPickerView pvOptions;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_sfz)
    EditText etSfz;
    @BindView(R.id.et_cph)
    EditText etCph;
    @BindView(R.id.et_pp)
    TextView etPp;
    @BindView(R.id.et_sb)
    EditText etSb;
    @BindView(R.id.et_fd)
    EditText etFd;
    @BindView(R.id.tv_post)
    TextView tvPost;

    private String carbelong = "";//车牌所属地
    private String carno = "";//车牌号码

    private List<UtilityItem>carbelongs = new ArrayList<>();

    UserPerson mUserPerson;

    @BindView(R.id.tv_gs)
    TextView tv_gs;

    String carbrand;

    String carlines;

    private OperatePopupWindow pop;// 弹出的popwindow

    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<ProvinceBean>> options2Items = new ArrayList<>();
    VehicleBean.DataBean item;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_cl_form;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("添加车辆信息");
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        getCarno();
        getHj();
        if(getIntent().getStringExtra("type").equals("1")){
            item = (VehicleBean.DataBean) getIntent().getSerializableExtra("info");
            etName.setText(item.getName());
            etPhone.setText(item.getMobile());
            etSfz.setText(item.getIdcard());
            etCph.setText(item.getCarno().substring(1));
            etPp.setText(item.getCarbrandshow()+item.getCarlines());
            tv_gs.setText(item.getCarbelongshow());
            etSb.setText(item.getVin());
            etFd.setText(item.getEngineno());
            tv_gs.setTag(item.getCarbelong());
            carbrand = item.getCarbrand();
            carlines = item.getCarlines();
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_post, R.id.et_pp, R.id.tv_gs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post:
                doCheck();
                break;
            case R.id.et_pp:
                initOptionPicker();
                pvOptions.show();
                break;
            case R.id.tv_gs:
                pop = new OperatePopupWindow(mContext,
                        "请选择", carbelongs,
                        new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(
                                    BaseQuickAdapter adapter,
                                    View view, int position) {
                                tv_gs.setText(carbelongs.get(position).getText());
                                tv_gs.setTag(carbelongs.get(position).getTextId());
                                pop.dismiss();
                            }
                        });
                pop.showAtLocation(tvPost, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
                pop.update();
                break;
        }
    }

    private void doCheck() {
        if (TextUtils.isEmpty(etName.getText().toString().trim())) {
            showToast("车主姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())) {
            showToast("车主手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(etSfz.getText().toString().trim())) {
            showToast("车主身份证号不能为空");
            return;
        }
        if (TextUtils.isEmpty(etCph.getText().toString().trim())) {
            showToast("车牌号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(etPp.getText().toString().trim())) {
            showToast("品牌型号不能为空");
            return;
        }
        if (TextUtils.isEmpty(etSb.getText().toString().trim())) {
            showToast("车辆识别码不能为空");
            return;
        }
        if (TextUtils.isEmpty(etFd.getText().toString().trim())) {
            showToast("车主发动机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(tv_gs.getText().toString().trim())) {
            showToast("车牌归属地不能为空");
            return;
        }
        if (!ValidationUtils.checkTelPhone(etPhone.getText().toString().trim())) {
            showToast("请输入正确手机号");
            return;
        }
        if (!ValidationUtils.checkCard(etSfz.getText().toString().trim())) {
            showToast("请输入正确身份证号");
            return;
        }
        if(getIntent().getStringExtra("type").equals("1")){
            /*修改*/
            doXg();
        }else{
            doPost();
        }

    }

    private void getHj() {
        mProgressHub.show();
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.CL_FX))
                .addParams("json", "{\"key\":\"\",\"dicttypeid\":\"CARBELONG\"}")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showToast(R.string.TheNetIsException);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mProgressHub.dismiss();
                        Check check = new Gson().fromJson(response, Check.class);
                        switch (check.getErr()) {
                            case 0:
                                Carbelong carbelong = new Gson().fromJson(response, Carbelong.class);
                                for (int i = 0; i <carbelong.getData().size() ; i++) {
                                    carbelongs.add(new UtilityItem(carbelong.getData().get(i).getParamname(),carbelong.getData().get(i).getDictid()));
                                }
                                break;
                            default:
                                showToast(check.getMsg());
                                break;
                        }
                    }
                });
    }

    /*提交车辆信息*/
    private void doPost() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XZ_Cl))
                    .addParams("json", "{\"key\":\"\",\"msnid\":\""
                            + mUserPerson.getData().get(0).getSnid()
                            + "\",\"pwd\":\""
                            + mUserPerson.getData().get(
                            0).getPassWord()
                            + "\",\"name\":\"" + etName.getText().toString().trim() + "\",\"mobile\":\"" + etPhone.getText().toString().trim()
                            + "\",\"idcard\":\"" + etSfz.getText().toString().trim() + "\",\"carbelong\":\""
                            + tv_gs.getTag()
                            + "\",\"carno\":\""+tv_gs.getText().toString()+etCph.getText().toString() + "\",\"carbrand\":\""
                            + carbrand + "\",\"carlines\":\"" + carlines
                            + "\",\"engineno\":\"" + etFd.getText().toString().trim() + "\",\"vin\":\""
                            + etSb.getText().toString().trim()
                            + "\"}")
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
                            Check check = new Gson().fromJson(response,
                                    Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    showToast("添加成功");
                                    EventBus.getDefault().post(
                                            new FirstEvent("commonClform"));
                                    finish();
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

    private void getCarno(){
        mProgressHub.show();
        JSONObject obj = new JSONObject();
        try {
            obj.put("key","");
            obj.put("dicttypeid","CARBRAND");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.CL_XX))
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
                        Check check = new Gson().fromJson(response, Check.class);
                        switch (check.getErr()) {
                            case 0:
                                Carbrand carbelong = new Gson().fromJson(response,Carbrand.class);
                                for (int i = 0; i <carbelong.getData().size() ; i++) {
                                    ArrayList<ProvinceBean> list = new ArrayList<ProvinceBean>();
                                    options1Items.add(new ProvinceBean(carbelong.getData().get(i).getParamname(),carbelong.getData().get(i).getDictid()));
                                    for (int j = 0; j <carbelong.getData().get(i).getCarlinesList().size() ; j++) {
                                        list.add(new ProvinceBean(carbelong.getData().get(i).getCarlinesList().get(j).getCarlines()));
                                    }
                                    options2Items.add(list);
                                }
                                break;
                            default:
                                showToast(check.getMsg());
                                break;
                        }
                    }
                });
    }

    private void initOptionPicker() {//条件选择器初始化
        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2)
                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                etPp.setText(tx);
                carbrand = options1Items.get(options1).getId();
                carlines = options2Items.get(options1).get(options2)+"";
            }
        })
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("品牌类型")
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "","")
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .build();
        pvOptions.setPicker(options1Items, options2Items);//二级选择器
    }

    /*修改车辆信息*/
    private void doXg() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XG_XL))
                    .addParams("json","{\"key\":\"\",\"msnid\":\""
                            + mUserPerson.getData().get(0).getSnid()
                            + "\",\"pwd\":\""
                            + mUserPerson.getData().get(
                            0).getPassWord()
                            + "\",\"snid\":\"" + item.getSnid()+ "\",\"name\":\"" + etName.getText().toString().trim() + "\",\"mobile\":\"" + etPhone.getText().toString().trim()
                            + "\",\"idcard\":\"" + etSfz.getText().toString().trim() + "\",\"carbelong\":\""
                            + tv_gs.getTag()
                            + "\",\"carno\":\""+tv_gs.getText().toString()+etCph.getText().toString() + "\",\"carbrand\":\""
                            + carbrand + "\",\"carlines\":\"" + carlines
                            + "\",\"engineno\":\"" + etFd.getText().toString().trim() + "\",\"vin\":\""
                            + etSb.getText().toString().trim()
                            + "\"}")
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
                            Check check = new Gson().fromJson(response,
                                    Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    showToast("修改成功");
                                    EventBus.getDefault().post(
                                            new FirstEvent("commonClform"));
                                    finish();
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
