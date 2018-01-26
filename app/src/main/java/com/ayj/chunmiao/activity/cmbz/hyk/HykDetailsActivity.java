package com.ayj.chunmiao.activity.cmbz.hyk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MemberCard;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.CommonUtils;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 会员卡details界面
* 绿色是富利卡
* */
public class HykDetailsActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_zm)
    TextView tv_zm;
    @BindView(R.id.tv_fm)
    TextView tv_fm;
    @BindView(R.id.tv_member_name)
    TextView tvMemberName;
    @BindView(R.id.tv_member_time)
    TextView tvMemberTime;
    @BindView(R.id.tv_member_price)
    TextView tvMemberPrice;
    @BindView(R.id.relativeLayout_member)
    RelativeLayout relativeLayoutMember;

    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_post)
    TextView tvPost;

    private MemberCard.DataBean mMemberCard;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    private OperatePopupWindow pop;// 弹出的popwindow

    List<UtilityItem> itemList = new ArrayList<>();

    boolean isZm = true;

    CropOptions options;

    List<TImage> listZm = new ArrayList();

    List<TImage> listFm = new ArrayList();

    String img1;

    String img2;

    public static void jumpActivity(Context context, MemberCard.DataBean item, String type) {
        Intent intent = new Intent(context, HykDetailsActivity.class);
        intent.putExtra("membercard", item);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hyk_details;
    }


    @Override
    public void initDatas() {
        tvTitle.setText("会员购买");
        UtilityItem item = new UtilityItem();
        item.setText("拍照");
        itemList.add(item);
        UtilityItem item1 = new UtilityItem();
        item1.setText("相册");
        itemList.add(item1);
        mMemberCard = (MemberCard.DataBean) getIntent().getSerializableExtra("membercard");
        if ("康乐会员A卡".equals(getIntent().getStringExtra("type"))) {
            /*需要拍照*/
            relativeLayoutMember.setBackgroundResource(R.mipmap.klhyak);
            ll.setVisibility(View.VISIBLE);
        } else if ("康乐会员B卡".equals(getIntent().getStringExtra("type"))) {
            relativeLayoutMember.setBackgroundResource(R.mipmap.klhybka);
            ll.setVisibility(View.GONE);
        }
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
        tvMemberName.setText(mMemberCard.getMatidshow());
        tvMemberTime.setText("有效期：" + mMemberCard.getValiddays() + "天");
        tvMemberPrice.setText("¥" + mMemberCard.getNowprice());

    }

    @OnClick({R.id.iv_back, R.id.tv_post, R.id.tv_zm, R.id.tv_fm})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post:
                if ("康乐会员A卡".equals(getIntent().getStringExtra("type"))) {
                    if(listFm.size()!=0&&listZm.size()!=0){
                        doCheck((ArrayList<TImage>) listZm);
                    }else{
                        showToast("请上传身份证正反面");
                    }
                } else if ("康乐会员B卡".equals(getIntent().getStringExtra("type"))) {
                    HykZfActivity.jumpActivity(mContext, mMemberCard.getMatidshow(), mMemberCard.getNowprice(),
                            mMemberCard.getMatid(), "MEMBERORDERTYPE011", "1", "", "", "");
                }
                break;
            case R.id.tv_zm:
                isZm = true;
                showPop();
                break;
            case R.id.tv_fm:
                isZm = false;
                showPop();
                break;
        }
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
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
    public void takeCancel() {
        showToast("拍照取消");
    }

    @Override
    public void takeFail(TResult result, String msg) {
        showToast("拍照失败");
    }

    @Override
    public void takeSuccess(TResult result) {
        showImg(result.getImages());
    }

    private void showImg(ArrayList<TImage> images) {
        if (isZm) {
            listZm.clear();
            Bitmap bm = BitmapFactory.decodeFile(images.get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            tv_zm.setCompoundDrawables(null, drawable, null, null);
            listZm = images;
        } else {
            /*反面*/
            listFm.clear();
            Bitmap bm = BitmapFactory.decodeFile(images.get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            tv_fm.setCompoundDrawables(null, drawable, null, null);
            listFm = images;
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
        pop.showAtLocation(tvPost, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        pop.update();
    }

    private void doCheck(ArrayList<TImage> images) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .addFile("mFile", images.get(0).getOriginalPath(), new File(images.get(0).getOriginalPath()))
                    .url(WebUtils.HOST_TWO)
                    .addParams("isrealsys", AyjSwApplication.IS_DEBUG ? "0" : "1")
                    .addParams("folder", "memberidcard")
                    .addParams("filetype", CommonUtils.getType(images.get(0).getOriginalPath()))
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
                                    img1 = check.getData().toString();
                                    doCheck();
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

    private void doCheck() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .addFile("mFile", listFm.get(0).getOriginalPath(), new File(listFm.get(0).getOriginalPath()))
                    .url(WebUtils.HOST_TWO)
                    .addParams("isrealsys", AyjSwApplication.IS_DEBUG ? "0" : "1")
                    .addParams("folder", "memberidcard")
                    .addParams("filetype", CommonUtils.getType(listFm.get(0).getOriginalPath()))
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
                                    img2 = check.getData().toString();
                                    HykZfActivity.jumpActivity(mContext,mMemberCard.getMatidshow(),mMemberCard.getNowprice(),mMemberCard.getMatid(),
                                            "MEMBERORDERTYPE011","1",img1,img2,"");
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
}