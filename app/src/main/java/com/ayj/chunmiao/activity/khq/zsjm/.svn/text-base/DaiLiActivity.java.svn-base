package com.ayj.chunmiao.activity.khq.zsjm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.OperatePopupWindow;
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
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class DaiLiActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_bank_no)
    EditText etBankNo;
    @BindView(R.id.et_bank_addr)
    EditText etBankAddr;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_carno)
    EditText etCarno;
    @BindView(R.id.et_yaoqing)
    EditText etYaoqing;
    @BindView(R.id.et_aaccount)
    EditText etAaccount;
    @BindView(R.id.et_baccount)
    EditText etBaccount;
    @BindView(R.id.tv_zm)
    TextView tvZm;
    @BindView(R.id.tv_fm)
    TextView tvFm;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    List<UtilityItem> itemList = new ArrayList<>();
    List<UtilityItem> itemList1 = new ArrayList<>();
    OperatePopupWindow operatePopupWindow, operatePhoto;
    boolean isZm = true;
    TakePhoto takePhoto;
    String photoZm,photoFm;
    private InvokeParam invokeParam;
    CropOptions options;
    private File file;
    String userPersonPwd;
    String userId;



    @Override
    public int getLayoutId() {
        return R.layout.activity_dai_li;
    }

    @Override
    public void initDatas() {
        UserPerson userPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        userPersonPwd = userPerson.getData().get(0).getPassWord();
        userId = userPerson.getData().get(0).getSnid();
        tvTitle.setText("申请代理");
        UtilityItem item = new UtilityItem();
        item.setText("男");
        itemList.add(item);
        UtilityItem item1 = new UtilityItem();
        item1.setText("女");
        itemList.add(item1);


        UtilityItem item2 = new UtilityItem();
        item2.setText("拍照");
        itemList1.add(item2);
        UtilityItem item3 = new UtilityItem();
        item3.setText("相册");
        itemList1.add(item3);

        options = new CropOptions.Builder()
                .setAspectX(2)
                .setAspectY(1)
                .setOutputX(300)
                .setOutputY(150)
                .create();

    }

    @Override
    public void configViews() {
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        btnConfirm.setEnabled(true);
                        btnConfirm.setBackgroundResource(R.drawable.common_bt_no_corner);
                    }else{
                        btnConfirm.setEnabled(false);
                        btnConfirm.setBackgroundResource(R.drawable.grey_no_check_background);
                    }
                }
            });
    }

    @OnClick({R.id.iv_back, R.id.tv_sex, R.id.tv_zm, R.id.tv_fm,R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_sex:
                showChoose();
                break;
            case R.id.tv_zm:
                isZm = true;
                showOperatePhoto();
                break;
            case R.id.tv_fm:
                isZm = false;
                showOperatePhoto();
                break;
            case R.id.btn_confirm:
                doCheck();
                break;

        }
    }

    private void doCheck() {
        if (TextUtils.isEmpty(etName.getText().toString().trim())){
            showToast("姓名不能为空");
            etName.requestFocus();
            return;
        }
        if ("点击选择性别".equals(tvSex.getText().toString().trim())){
            showToast("请选择性别");
            return;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString().trim())){
            showToast("邮箱不能为空");
            etEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etBankNo.getText().toString().trim())){
            showToast("银行卡号不能为空");
            etBankNo.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etBankAddr.getText().toString().trim())){
            showToast("开户银行不能为空");
            etBankAddr.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etPhone.getText().toString().trim())){
            showToast("联系方式不能为空");
            etPhone.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(etCarno.getText().toString().trim())){
            showToast("身份证号不能为空");
            etCarno.requestFocus();
            return;
        }
        if (!ValidationUtils.checkTelPhone(etPhone.getText().toString().trim())){
            showToast("请填写正确手机号码");
            etPhone.requestFocus();
            return;
        }
        if (!ValidationUtils.checkEmail(etEmail.getText().toString().trim())){
            showToast("请填写正确邮箱地址");
            etEmail.requestFocus();
            return;
        }
        if (!ValidationUtils.checkCard(etCarno.getText().toString().trim())){
            showToast("请填写正确身份证号码");
            etCarno.requestFocus();
            return;
        }
        sendApplication();
    }

    private void sendApplication() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "")
                        .put("msnid", userId)
                        .put("pwd", userPersonPwd)
                        .put("email", etEmail.getText().toString().trim())
                        .put("bankid", "BANK001")
                        .put("bankno", etBankNo.getText().toString().trim())
                        .put("bankdetail", etBankAddr.getText().toString().trim())
                        .put("mobile", etPhone.getText().toString().trim())
                        .put("idcard", etCarno.getText().toString().trim())
                        .put("pmobile",etAaccount.getText().toString().trim()+etBaccount.getText().toString().trim())
                        .put("photo1", photoZm)
                        .put("photo2", photoFm);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.PERSON_AGENCY_APPLY_URL))
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
                                    showToast("申请成功，等待审核");
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
    private void showOperatePhoto() {
        file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        final Uri imageUri = Uri.fromFile(file);
        operatePhoto = new OperatePopupWindow(mContext, "请选择", itemList1,
                new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (position) {
                            case 0:
                                takePhoto.onPickFromCaptureWithCrop(imageUri,options);
                                break;
                            case 1:
                                takePhoto.onPickMultiple(1);
                                break;
                        }
                        operatePhoto.dismiss();
                    }
                });
        operatePhoto.showAtLocation(btnConfirm, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        operatePhoto.update();
    }

    private void showChoose() {
        operatePopupWindow = new OperatePopupWindow(mContext, "请选择", itemList,
                new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (0 == position) {
                            tvSex.setText("男");
                        } else if (1 == position) {
                            tvSex.setText("女");
                        }
                        operatePopupWindow.dismiss();
                    }
                });
        operatePopupWindow.showAtLocation(tvSex, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        operatePopupWindow.update();
    }


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
    public void takeSuccess(TResult result) {
        //Picasso.with(mContext).load(result.getImage().getCompressPath()).resize(300,300).into(image);
        if (isZm) {
            Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            tvZm.setCompoundDrawables(null, drawable, null, null);
            upLoadImage(result.getImages(),true);
        } else {
            /*反面*/
            Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            tvFm.setCompoundDrawables(null, drawable, null, null);
            upLoadImage(result.getImages(),false);
        }
    }

    private void upLoadImage(ArrayList<TImage> images, final boolean isZm) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            OkHttpUtils.post()
                    .url(WebUtils.HOST_TWO)
                    .addFile("mFile",images.get(0).getOriginalPath(),new File(images.get(0).getOriginalPath()))
                    .addParams("isrealsys", AyjSwApplication.IS_DEBUG?"0":"1")
                    .addParams("filetype", images.get(0).getOriginalPath().substring(images.get(0).getOriginalPath().lastIndexOf(".")))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast("图片上传失败");
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    if (isZm){
                                        photoZm = check.getData().toString();
                                    }else {
                                        photoFm = check.getData().toString();
                                    }
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

    @Override
    public void takeFail(TResult result, String msg) {
        showToast("拍照失败");
    }

    @Override
    public void takeCancel() {
        showToast("拍照取消");
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

}
