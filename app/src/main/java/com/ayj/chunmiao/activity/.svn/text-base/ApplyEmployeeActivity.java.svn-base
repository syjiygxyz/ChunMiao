package com.ayj.chunmiao.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.ValueIterator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.EducationBean;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class ApplyEmployeeActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.rl_photo)
    RelativeLayout rlPhoto;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_born_date)
    TextView tvBornDate;
    @BindView(R.id.rl_birthday)
    RelativeLayout rlBirthday;
    @BindView(R.id.tv_qualification)
    TextView tvQualification;
    @BindView(R.id.rl_qualification)
    RelativeLayout rlQualification;
    @BindView(R.id.tv_next)
    Button tvNext;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_techlv)
    TextView tvTechlv;
    @BindView(R.id.rl_techlv)
    RelativeLayout rlTechlv;
    @BindView(R.id.tv_zwlx)
    TextView tvZwlx;
    @BindView(R.id.rl_zwlx)
    RelativeLayout rlZwlx;

    private List<UtilityItem> itemList = new ArrayList<>();
    private List<EducationBean.DataBean> educationList = new ArrayList<>();
    private List<EducationBean.DataBean> sexList = new ArrayList<>();
    private List<EducationBean.DataBean> techLvList = new ArrayList<>();
    private List<EducationBean.DataBean> zwList = new ArrayList<>();
    private OperatePopupWindow popupWindow;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private CropOptions options;
    private int pos;
    private String photo;
    private String educationId, sexId, techLv, zwlx;
    private ArrayList<TImage> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_apply_employee;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("账号申请");
        options = new CropOptions.Builder()
                .setAspectX(1)
                .setAspectY(1)
                .setOutputX(300)
                .setOutputY(300)
                .create();
    }

    @Override
    public void configViews() {
        getDictListList("EDUCATION");
        getDictListList("SEX");
        getDictListList("USERTECHLEVEL");
        getDictListList("ZWLX");
    }

    private void getDictListList(final String dicttypeid) {
        mProgressHub.show();
        JSONObject object = new JSONObject();
        try {
            object.put("key", "");
            object.put("dicttypeid", dicttypeid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.CL_FX))
                .addParams("json", String.valueOf(object))
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
                                EducationBean educationBean = new Gson().fromJson(response, EducationBean.class);
                                if (dicttypeid.equals("EDUCATION")) {
                                    educationList.addAll(educationBean.getData());
                                } else if (dicttypeid.equals("SEX")) {
                                    sexList.addAll(educationBean.getData());
                                } else if (dicttypeid.equals("USERTECHLEVEL")) {
                                    techLvList.addAll(educationBean.getData());
                                } else if (dicttypeid.equals("ZWLX")) {
                                    zwList.addAll(educationBean.getData());
                                }

                                break;
                            default:
                                showToast(check.getMsg());
                                break;
                        }
                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.rl_photo, R.id.rl_sex, R.id.rl_birthday, R.id.rl_qualification, R.id.tv_next,R.id.rl_techlv,R.id.rl_zwlx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_photo:
                itemList.clear();
                itemList.add(new UtilityItem("拍照"));
                itemList.add(new UtilityItem("相册"));
                showPhotoChoose();
                break;
            case R.id.rl_sex:
                itemList.clear();
                for (int i = 0; i < sexList.size(); i++) {
                    itemList.add(new UtilityItem(sexList.get(i).getParamname()));
                }
                pos = 0;
                showPopupWindow();
                break;
            case R.id.rl_birthday:
                showTimePicker();
                break;
            case R.id.rl_qualification:
                itemList.clear();
                for (int i = 0; i < educationList.size(); i++) {
                    itemList.add(new UtilityItem(educationList.get(i).getParamname()));
                }
                pos = 1;
                showPopupWindow();
                break;
            case R.id.rl_techlv:
                itemList.clear();
                for (int i = 0; i < techLvList.size(); i++) {
                    itemList.add(new UtilityItem(techLvList.get(i).getParamname()));
                }
                pos = 2;
                showPopupWindow();
                break;
            case R.id.rl_zwlx:
                itemList.clear();
                for (int i = 0; i < zwList.size(); i++) {
                    itemList.add(new UtilityItem(zwList.get(i).getParamname()));
                }
                pos = 3;
                showPopupWindow();
                break;
            case R.id.tv_next:
                doCheck();
                break;
        }
    }

    private void doCheck(){
        if (TextUtils.isEmpty(etName.getText().toString().trim())){
            showToast("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(tvSex.getText())){
            showToast("请选择性别");
            return;
        }
        if (TextUtils.isEmpty(etIdcard.getText().toString().trim())){
            showToast("请填写员工身份证号");
            return;
        }
        if (!ValidationUtils.checkCard(etIdcard.getText().toString().trim())){
            showToast("请填写正确的身份证号");
            return;
        }
        if (TextUtils.isEmpty(tvBornDate.getText())){
            showToast("请选择出生日期");
            return;
        }
        if (TextUtils.isEmpty(etMobile.getText().toString().trim())){
            showToast("请填写手机号码");
            return;
        }
        if (null == educationId ||TextUtils.isEmpty(educationId)){
            showToast("请选择学历");
            return;
        }
        if (null == images || images.size()<1){
            showToast("请上传头像");
            return;
        }
        if (null == techLv || TextUtils.isEmpty(techLv)){
            showToast("请选择技术等级");
            return;
        }
        if (null == zwlx || TextUtils.isEmpty(zwlx)){
            showToast("请选择申请职位");
            return;
        }
        upLoadImage(images);
    }
    private void applyEmployee() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key", "");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("name", etName.getText().toString().trim());
                object.put("sex", sexId);
                object.put("idcard", etIdcard.getText().toString().trim());
                object.put("birthday", tvBornDate.getText());
                object.put("mobile", etMobile.getText().toString().trim());
                object.put("education", educationId);
                object.put("headimgurl", photo);
                object.put("techlevel",techLv);
                object.put("zw",zwlx);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.APPLY_EMP))
                    .addParams("json", String.valueOf(object))
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

    @Override
    public void takeSuccess(TResult result) {
        Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
        ivPhoto.setImageBitmap(bm);
        images = result.getImages();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        showToast("获取图片失败");
    }

    @Override
    public void takeCancel() {
        showToast("获取图片中止");
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showPhotoChoose() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        final Uri imageUri = Uri.fromFile(file);

        popupWindow = new OperatePopupWindow(mContext, "请选择", itemList, new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (0 == position) {
                    takePhoto.onPickFromCaptureWithCrop(imageUri, options);
                } else if (1 == position) {
                    takePhoto.onPickMultiple(1);
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(tvNext, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.update();
    }

    private void showPopupWindow() {
        popupWindow = new OperatePopupWindow(mContext, "请选择", itemList, new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (pos) {
                    case 0:
                        tvSex.setText(itemList.get(position).getText());
                        sexId = sexList.get(position).getDictid();
                        popupWindow.dismiss();
                        break;
                    case 1:
                        tvQualification.setText(itemList.get(position).getText());
                        educationId = educationList.get(position).getDictid();
                        popupWindow.dismiss();
                        break;
                    case 2:
                        tvTechlv.setText(itemList.get(position).getText());
                        techLv = techLvList.get(position).getDictid();
                        popupWindow.dismiss();
                        break;
                    case 3:
                        tvZwlx.setText(itemList.get(position).getText());
                        zwlx = zwList.get(position).getDictid();
                        popupWindow.dismiss();
                        break;
                }
            }
        });
        popupWindow.showAtLocation(tvNext, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.update();
    }

    private void showTimePicker() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH),startDate.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(1900,0,1);
        TimePickerView tp = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvBornDate.setText(CommonUtils.getTime(date, "yyyy-MM-dd"));
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setCancelText("取消").setDate(startDate).setRangDate(endDate,startDate).gravity(Gravity.TOP).build();
        tp.show(tvBornDate);
    }

    private void upLoadImage(final ArrayList<TImage> images) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.IMGAE_UPLOAD)
                    .addFile("mFile", images.get(0).getOriginalPath(), new File(images.get(0).getOriginalPath()))
                    .addParams("isrealsys", AyjSwApplication.IS_DEBUG ? "1" : "0")
                    .addParams("filetype", images.get(0).getOriginalPath().substring(images.get(0).getOriginalPath().lastIndexOf(".")))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast("图片上传失败");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    photo = check.getData().toString();
                                    applyEmployee();
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
