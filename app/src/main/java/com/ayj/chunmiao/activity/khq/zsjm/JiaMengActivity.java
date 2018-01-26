package com.ayj.chunmiao.activity.khq.zsjm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.AreaInfo;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 加盟
* */
public class JiaMengActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    List<UtilityItem> itemList = new ArrayList<>();
    List<AreaInfo.DataBean> addresList = new ArrayList<>();

    @BindView(R.id.tv_title)
    TextView tvTitle;

    TakePhoto takePhoto;
    @BindView(R.id.et_join_qq)
    EditText etJoinQq;
    @BindView(R.id.et_join_weixin)
    EditText etJoinWeixin;
    @BindView(R.id.et_join_shopname)
    EditText etJoinShopname;
    @BindView(R.id.et_join_legalpersonidcard)
    EditText etJoinLegalpersonidcard;

    private InvokeParam invokeParam;

    private OperatePopupWindow pop;// 弹出的popwindow
    @BindView(R.id.btn_join_apply)
    Button btnJoinApply;
    @BindView(R.id.et_join_name)
    EditText etJoinName;
    @BindView(R.id.spin_join_sex)
    TextView spinJoinSex;
    @BindView(R.id.tv_dq)
    TextView tv_dq;
    @BindView(R.id.et_join_detail)
    EditText etJoinDetail;
    @BindView(R.id.et_join_phone)
    EditText etJoinPhone;
    @BindView(R.id.et_join_email)
    EditText etJoinEmail;
    @BindView(R.id.et_join_id)
    EditText etJoinId;

    @BindView(R.id.iv_join_load_positive)
    ImageView ivJoinLoadPositive;
    @BindView(R.id.iv_join_load_negative)
    ImageView ivJoinLoadNegative;

    @BindView(R.id.et_join_invite_name)
    EditText etJoinInviteName;
    @BindView(R.id.et_join_invite_phone)
    EditText etJoinInvitePhone;

    @BindView(R.id.et_join_addr)
    EditText etJoinAddr;

    @BindView(R.id.et_join_quarter)
    EditText etJoinQuarter;

    @BindView(R.id.spin_join_level)
    TextView spinJoinLevel;

    @BindView(R.id.et_join_count)
    EditText etJoinCount;

    @BindView(R.id.et_join_proportion)
    EditText etJoinProportion;
    @BindView(R.id.iv_load_realpic1)
    ImageView ivLoadRealpic1;
    @BindView(R.id.iv_load_realpic2)
    ImageView ivLoadRealpic2;
    @BindView(R.id.iv_load_realpic3)
    ImageView ivLoadRealpic3;
    @BindView(R.id.cb_join_agreement)
    CheckBox cbJoinAgreement;
    @BindView(R.id.tv_join_agreement)
    TextView tvJoinAgreement;
    CropOptions options;

    private File file;

    String type;

    private String photoZm,photoFm,realPic1,realPic2,realPic3,provinceId, cityId ,areaId,addressId,province, city ,area;

    private int photoPosition = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jia_meng;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("加盟");
        /*照片剪裁*/
        options = new CropOptions.Builder()
                .setAspectX(2)
                .setAspectY(1)
                .setOutputX(300)
                .setOutputY(150)
                .create();
    }

    @Override
    public void configViews() {
    }

    @OnClick({R.id.iv_back, R.id.btn_join_apply, R.id.spin_join_sex, R.id.tv_dq, R.id.spin_join_level, R.id.iv_join_load_positive, R.id.iv_join_load_negative,R.id.iv_load_realpic1,R.id.iv_load_realpic2,R.id.iv_load_realpic3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_join_load_positive://正面
                addData(3);
                type = "1";
                showPop();
                photoPosition = 0;
                break;
            case R.id.iv_join_load_negative://反面
                addData(3);
                type = "2";
                showPop();
                photoPosition = 1;
                break;
            case R.id.iv_load_realpic1://实景1
                addData(3);
                type = "1";
                showPop();
                photoPosition = 2;
                break;
            case R.id.iv_load_realpic2://实景2
                addData(3);
                type = "1";
                showPop();
                photoPosition = 3;
                break;
            case R.id.iv_load_realpic3://实景3
                addData(3);
                type = "1";
                showPop();
                photoPosition = 4;
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_join_apply:
                doCheck();
                break;
            case R.id.tv_dq:
                CommonUtils.getDqPickerView(mContext, tv_dq);
                break;
            case R.id.spin_join_sex:
                addData(1);
                pop = new OperatePopupWindow(mContext,
                        "请选择性别", itemList,
                        new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(
                                    BaseQuickAdapter adapter,
                                    View view, int position) {
                                spinJoinSex.setText(itemList.get(position).getText().toString());
                                pop.dismiss();
                            }
                        });
                pop.showAtLocation(btnJoinApply, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
                pop.update();
                break;
            case R.id.spin_join_level:
                addData(2);
                pop = new OperatePopupWindow(mContext,
                        "请选择小区档次", itemList,
                        new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(
                                    BaseQuickAdapter adapter,
                                    View view, int position) {
                                spinJoinLevel.setText(itemList.get(position).getText().toString());
                                pop.dismiss();
                            }
                        });
                pop.showAtLocation(btnJoinApply, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
                pop.update();
                break;
        }
    }
    private void sendApply() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("joinapplytype","JOINAPPLYTYPE002");
                obj.put("name", etJoinName.getText().toString().trim());
                obj.put("sex", spinJoinSex.getText().toString().trim());
                obj.put("email", etJoinEmail.getText().toString().trim());
                obj.put("qq", etJoinQq.getText().toString().trim());
                obj.put("weixin", etJoinWeixin.getText().toString().trim());
                obj.put("provinceid", provinceId);
                obj.put("cityid", cityId);
                obj.put("areaid", areaId);
                obj.put("detailaddr", etJoinDetail.getText().toString().trim());
                obj.put("mobile", etJoinPhone.getText().toString().trim());
                obj.put("idcard", etJoinId.getText().toString().trim());
                obj.put("idcardphoto1", photoZm);
                obj.put("idcardphoto2", photoFm);
                obj.put("ksdd", etJoinAddr.getText().toString().trim());
                obj.put("communityname", etJoinQuarter.getText().toString().trim());
                obj.put("communitylevel", spinJoinLevel.getText().toString().trim());
                obj.put("housenum", etJoinCount.getText().toString().trim());
                obj.put("liverate", etJoinProportion.getText().toString().trim());
                obj.put("yslmshopname",etJoinShopname.getText().toString().trim());
                obj.put("legalpersonidcard",etJoinLegalpersonidcard.getText().toString().trim());
                obj.put("outphoto1", realPic1);
                obj.put("outphoto2", realPic2);
                obj.put("outphoto3", realPic3);
                obj.put("invitername", etJoinInviteName.getText().toString().trim());
                obj.put("invitermobile",etJoinInvitePhone.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SET_JOIN_APPLY))
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
                                case 0 :
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

    private void showPop() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        final Uri imageUri = Uri.fromFile(file);
        pop = new OperatePopupWindow(mContext,
                "请选择", itemList,
                new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(
                            BaseQuickAdapter adapter,
                            View view, int position) {
                                  /*拍照*/
                        if (0 == position) {
                            takePhoto.onPickFromCaptureWithCrop(imageUri, options);
                        } else if (1 == position) {
                            takePhoto.onPickMultiple(1);
                        }
                        pop.dismiss();
                    }
                });
        pop.showAtLocation(btnJoinApply, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        pop.update();
    }

    //申请体验用户什么的
    private void addData(int type) {
        itemList.clear();
        if (type == 1) {
            UtilityItem item = new UtilityItem();
            item.setText("男");
            itemList.add(item);
            UtilityItem item1 = new UtilityItem();
            item1.setText("女");
            itemList.add(item1);
        } else if (type == 2) {
            UtilityItem item = new UtilityItem();
            item.setText("豪华");
            itemList.add(item);
            UtilityItem item1 = new UtilityItem();
            item.setText("高档");
            itemList.add(item1);
            UtilityItem item2 = new UtilityItem();
            item.setText("中档");
            itemList.add(item2);
            UtilityItem item3 = new UtilityItem();
            item.setText("一般");
            itemList.add(item3);
        } else if (type == 3) {
            UtilityItem item = new UtilityItem();
            item.setText("拍照");
            itemList.add(item);
            UtilityItem item1 = new UtilityItem();
            item1.setText("相册");
            itemList.add(item1);
        }
    }

    private void doCheck() {
        if (TextUtils.isEmpty(etJoinName.getText().toString().trim())) {
            showToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(spinJoinSex.getText().toString().trim())) {
            showToast("请选择性别");
            return;
        }
        if (TextUtils.isEmpty(tv_dq.getText().toString().trim())) {
            showToast("请选择地区");
            return;
        }
        if (TextUtils.isEmpty(etJoinDetail.getText().toString().trim())) {
            showToast("请填写详细的地址");
            return;
        }
        if (TextUtils.isEmpty(etJoinPhone.getText().toString().trim())) {
            showToast("请填写你的联系方式");
            return;
        }
        if (TextUtils.isEmpty(etJoinEmail.getText().toString().trim())) {
            showToast("请填写你的邮箱");
            return;
        }
        if (!ValidationUtils.checkEmail(etJoinEmail.getText().toString().trim())) {
            showToast("请检查你的邮箱是否正确");
            return;
        }
        if (TextUtils.isEmpty(etJoinId.getText().toString().trim())) {
            showToast("请填写你的身份证号");
            return;
        }
        if (!ValidationUtils.checkCard(etJoinId.getText().toString().trim())) {
            showToast("请检查你的身份证号是否正确");
            return;
        }
        if (TextUtils.isEmpty(etJoinAddr.getText().toString().trim())) {
            showToast("请填写你的开设地点");
            return;
        }
        if (TextUtils.isEmpty(etJoinQuarter.getText().toString().trim())) {
            showToast("请填写你的小区名字");
            return;
        }
        if (TextUtils.isEmpty(spinJoinLevel.getText().toString().trim())) {
            showToast("请选择你的小区档次");
            return;
        }
        if (!cbJoinAgreement.isChecked()) {
            showToast("请同意协议");
            return;
        }
        StringTokenizer st = new StringTokenizer(tv_dq.getText().toString());
        province = st.nextToken();
        city = st.nextToken();
        area = st.nextToken();
       getAddressId();
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
        Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
        switch (photoPosition){
            case 0 :
                ivJoinLoadPositive.setImageBitmap(bm);
                upLoadImage(result.getImages());
                break;
            case 1 :
                ivJoinLoadNegative.setImageBitmap(bm);
                upLoadImage(result.getImages());
                break;
            case 2 :
                ivLoadRealpic1.setImageBitmap(bm);
                upLoadImage(result.getImages());
                break;
            case 3 :
                ivLoadRealpic2.setImageBitmap(bm);
                upLoadImage(result.getImages());
                break;
            case 4 :
                ivLoadRealpic3.setImageBitmap(bm);
                upLoadImage(result.getImages());
                break;
            default:
                break;
        }

    }

    private void upLoadImage(ArrayList<TImage> images) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.HOST_TWO)
                    .addFile("mFile", images.get(0).getOriginalPath(), new File(images.get(0).getOriginalPath()))
                    .addParams("isrealsys", AyjSwApplication.IS_DEBUG ? "0" : "1")
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
                                    switch (photoPosition){
                                        case 0:
                                            photoZm = check.getData().toString();
                                            break;
                                        case 1:
                                            photoFm = check.getData().toString();
                                            break;
                                        case 2:
                                            realPic1 = check.getData().toString();
                                            break;
                                        case 3:
                                            realPic2 = check.getData().toString();
                                            break;
                                        case 4:
                                            realPic3 = check.getData().toString();
                                            break;
                                    }
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
    private void getAddressId() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("pid","0001");
            }catch (JSONException e){
                e.printStackTrace();
            }

            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
                    .addParams("json",String.valueOf(object))
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
                                    AreaInfo areainfo_pro = new Gson().fromJson(response, AreaInfo.class);
                                    addresList.addAll(areainfo_pro.getData());
                                    for (int i=0;i<addresList.size();i++){
                                        if (province.equals(addresList.get(i).getName())){
                                            provinceId = addresList.get(i).getAid();
                                        }
                                    }
                                    getCityId();
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

    private void getCityId() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("pid",provinceId);
            }catch (JSONException e){
                e.printStackTrace();
            }

            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
                    .addParams("json",String.valueOf(object))
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
                                    AreaInfo areainfo_pro = new Gson().fromJson(response, AreaInfo.class);
                                    addresList.addAll(areainfo_pro.getData());
                                    for (int i=0;i<addresList.size();i++){
                                        if (city.equals(addresList.get(i).getName())){
                                            cityId = addresList.get(i).getAid();
                                        }
                                    }
                                    getAreaId();
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

    private void getAreaId() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("pid",cityId);
            }catch (JSONException e){
                e.printStackTrace();
            }

            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
                    .addParams("json",String.valueOf(object))
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
                                    AreaInfo areainfo_pro = new Gson().fromJson(response, AreaInfo.class);
                                    addresList.addAll(areainfo_pro.getData());
                                    for (int i=0;i<addresList.size();i++){
                                        if (area.equals(addresList.get(i).getName())){
                                            areaId = addresList.get(i).getAid();
                                        }
                                    }
                                    sendApply();
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
