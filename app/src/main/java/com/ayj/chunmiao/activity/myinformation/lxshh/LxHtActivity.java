package com.ayj.chunmiao.activity.myinformation.lxshh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.ayj.chunmiao.view.PayWayPopupWindow;
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

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;


/**联销提交合同**/
public class LxHtActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_cmptht)
    ImageView ivCmptht;
    @BindView(R.id.iv_cmghfht)
    ImageView ivCmghfht;
    @BindView(R.id.iv_ghfbzh)
    ImageView ivGhfbzh;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.confirm_button)
    Button confirmButton;

    private int photoPosition;
    private String cmpthtPhoto,cmghfhtPhoto,ghfPhoto,deposit,snid;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private OperatePopupWindow popupWindow;
    private CropOptions options;
    private List<UtilityItem> itemList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_lx_ht;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("提交合同");
        itemList.add(new UtilityItem("拍照"));
        itemList.add(new UtilityItem("相册"));
        deposit = getIntent().getStringExtra("deposit") == null ? "" : CommonUtils.douFormat(getIntent().getStringExtra("deposit"));
        snid = getIntent().getStringExtra("snid");
        tvMoney.setText("￥"+deposit);
        options = new CropOptions.Builder()
                .setAspectX(1)
                .setAspectY(1)
                .setOutputX(300)
                .setOutputY(300)
                .create();
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.iv_cmptht, R.id.iv_cmghfht, R.id.iv_ghfbzh, R.id.confirm_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cmptht:
                photoPosition = 0;
                showPhotoChoose();
                break;
            case R.id.iv_cmghfht:
                photoPosition = 1;
                showPhotoChoose();
                break;
            case R.id.iv_ghfbzh:
                photoPosition = 2;
                showPhotoChoose();
                break;
            case R.id.confirm_button:
                sendContract();
                break;
        }
    }

    private void sendContract() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("snid",snid);
                object.put("guarantycontract",cmpthtPhoto);
                object.put("purchasecontract",cmghfhtPhoto);
                object.put("supplierguarantee",ghfPhoto);
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.LX_SB_STEP2))
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
                                    Intent intent = new Intent(mContext, BzjZfActivity.class);
                                    intent.putExtra("snid",snid);
                                    intent.putExtra("deposit",deposit);
                                    startActivity(intent);

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
            case 0:
                ivCmptht.setImageBitmap(bm);
                upLoadImage(result.getImages(),0);
                break;
            case 1:
                ivCmghfht.setImageBitmap(bm);
                upLoadImage(result.getImages(),1);
                break;
            case 2:
                ivGhfbzh.setImageBitmap(bm);
                upLoadImage(result.getImages(),2);
                break;
        }
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
        popupWindow.showAtLocation(confirmButton, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.update();
    }

    private void upLoadImage(final ArrayList<TImage> images,final int pos) {
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
                                    switch (pos) {
                                        case 0:
                                            cmpthtPhoto = check.getData().toString();
                                            break;
                                        case 1:
                                            cmghfhtPhoto = check.getData().toString();
                                            break;
                                        case 2:
                                            ghfPhoto = check.getData().toString();
                                            break;
                                    }

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
