package com.ayj.chunmiao.activity.mdlx.lxsb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.myinformation.lxshh.LxshActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UnitBean;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.utils.txl.LxsbImageLoader;
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
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*联销申报*/
public class LxsbActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.banner_view)
    Banner bannerView;
    @BindView(R.id.iv_lx_logo)
    ImageView ivLxLogo;
    @BindView(R.id.iv_lx_attes)
    ImageView ivLxAttes;
    @BindView(R.id.iv_lx_quality)
    ImageView ivLxQuality;
    @BindView(R.id.iv_lx_ad)
    ImageView ivLxAd;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.tv_addphoto)
    TextView tvAddphoto;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    @BindView(R.id.et_matname)
    EditText etMatname;
    @BindView(R.id.et_standard)
    EditText etStandard;
    @BindView(R.id.et_shoppurchaseprice)
    EditText etShoppurchaseprice;
    @BindView(R.id.et_standardsaleprice)
    EditText etStandardsaleprice;
    @BindView(R.id.et_applynum)
    EditText etApplynum;
    @BindView(R.id.tv_salebtime)
    TextView tvSalebtime;
    @BindView(R.id.tv_saleetime)
    TextView tvSaleetime;
    @BindView(R.id.et_canarrivearea)
    EditText etCanarrivearea;
    @BindView(R.id.et_minbuynum)
    EditText etMinbuynum;
    @BindView(R.id.et_packway)
    EditText etPackway;
    @BindView(R.id.rb_storageway_leng)
    RadioButton rbStoragewayLeng;
    @BindView(R.id.rb_storageway_bx)
    RadioButton rbStoragewayBx;
    @BindView(R.id.rb_storageway_pt)
    RadioButton rbStoragewayPt;
    @BindView(R.id.et_expirationdate)
    EditText etExpirationdate;
    @BindView(R.id.rb_shipfrom_fw)
    RadioButton rbShipfromFw;
    @BindView(R.id.rb_shipfrom_bd)
    RadioButton rbShipfromBd;
    @BindView(R.id.rb_shipfrom_cc)
    RadioButton rbShipfromCc;
    @BindView(R.id.rb_saleway_zx)
    RadioButton rbSalewayZx;
    @BindView(R.id.rb_saleway_cg)
    RadioButton rbSalewayCg;
    @BindView(R.id.rb_saleway_ft)
    RadioButton rbSalewayFt;
    @BindView(R.id.rg_storageway)
    RadioGroup rgStorageway;
    @BindView(R.id.rg_shipfrom)
    RadioGroup rgShipfrom;
    @BindView(R.id.rg_saleway)
    RadioGroup rgSaleway;
    @BindView(R.id.et_adurl)
    EditText etAdurl;
    @BindView(R.id.iv_s_addphoto)
    ImageView ivSAddphoto;
    @BindView(R.id.tv_sunit)
    TextView tvSunit;
    @BindView(R.id.rb_mf)
    RadioButton rbMf;
    @BindView(R.id.rb_hdfk)
    RadioButton rbHdfk;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private int photoPosition = 0;
    private OperatePopupWindow popupWindow;
    private OperatePopupWindow popupSunitWindow;
    private CropOptions options;
    private String photoLogo, photoAttes, photoQuality, photoAd, photoBanner, unitId;
    private List<UtilityItem> itemList = new ArrayList<>();
    private List<UtilityItem> sunitItemList = new ArrayList<>();
    private List<String> netImages = new ArrayList<>();
    private List<String> bannerPhotoList = new ArrayList<>();
    private List<UnitBean.DataBean> unitList = new ArrayList<>();
    private int bannerPosition = 0;
    private String storageWay = "冷藏", shipFrom = Constant.SHIPFROM_FW, saleWay = Constant.SALEWAY_ZX;
    private String webUrl;
    public static void startActivity(Context context, String times){
        Intent intent = new Intent(context, LxsbActivity.class);
        intent.putExtra("isfirst",times);
        context.startActivity(intent);
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
    public int getLayoutId() {
        return R.layout.activity_lxsb;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("联销申报");
        itemList.add(new UtilityItem("拍照"));
        itemList.add(new UtilityItem("相册"));
        bannerView.setImageLoader(new LxsbImageLoader());
        bannerView.isAutoPlay(false);
        if (getIntent().getStringExtra("isfirst") != null){
            if ("2".equals(getIntent().getStringExtra("isfirst"))){
                webUrl = WebUtils.getRequestUrl(WebUtils.LX_SB_UP);
            }else {
                webUrl = WebUtils.getRequestUrl(WebUtils.LX_SB);
            }
        }else {
            webUrl = WebUtils.getRequestUrl(WebUtils.LX_SB);
        }

        options = new CropOptions.Builder()
                .setAspectX(2)
                .setAspectY(1)
                .setOutputX(300)
                .setOutputY(150)
                .create();
        getUnitIdList();
    }

    /*获取单位列表*/
    private void getUnitIdList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key", "");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.UNIT_LIST))
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
                                    UnitBean unitBean = new Gson().fromJson(response, UnitBean.class);
                                    unitList.addAll(unitBean.getData());
                                    if (unitList.size() > 0) {
                                        for (int i = 0; i < unitList.size(); i++) {
                                            sunitItemList.add(new UtilityItem(unitList.get(i).getUnitname()));
                                        }
                                    }
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
    public void configViews() {
        bannerView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bannerPosition = position - 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rgSaleway.setOnCheckedChangeListener(this);
        rgShipfrom.setOnCheckedChangeListener(this);
        rgStorageway.setOnCheckedChangeListener(this);
    }

    @OnClick({R.id.iv_lx_logo, R.id.iv_lx_attes, R.id.iv_lx_quality, R.id.iv_lx_ad, R.id.tv_addphoto, R.id.iv_back, R.id.iv_delete, R.id.tv_sunit, R.id.tv_salebtime, R.id.tv_saleetime, R.id.btn_confirm, R.id.iv_s_addphoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_lx_logo://产品logo
                photoPosition = 0;
                showPhotoChoose();
                break;
            case R.id.iv_lx_attes://公司资质
                photoPosition = 1;
                showPhotoChoose();
                break;
            case R.id.iv_lx_quality://产品质量
                photoPosition = 2;
                showPhotoChoose();
                break;
            case R.id.iv_lx_ad://广告
                photoPosition = 3;
                showPhotoChoose();
                break;
            case R.id.tv_addphoto://添加轮播图片
                photoPosition = 4;
                showPhotoChoose();
                break;
            case R.id.iv_s_addphoto:
                photoPosition = 4;
                if (netImages.size() < 3) {
                    showPhotoChoose();
                } else {
                    showToast("最多上传3张");
                }
                break;
            case R.id.iv_delete://删除轮播图片
                if (netImages.size() > 1) {
                    netImages.remove(bannerPosition);
                    bannerPhotoList.remove(bannerPosition);
                } else if (netImages.size() == 1) {
                    ivCover.setVisibility(View.VISIBLE);
                    tvAddphoto.setVisibility(View.VISIBLE);
                    ivSAddphoto.setVisibility(View.INVISIBLE);
                    netImages.clear();
                    bannerPhotoList.clear();
                }
                bannerView.setImages(netImages);
                bannerView.start();
                break;
            case R.id.tv_sunit:
                showSunitChoose();
                break;
            case R.id.tv_salebtime://起始时间
                showTimePicker(true);
                break;
            case R.id.tv_saleetime://结束时间
                showTimePicker(false);
                break;
            case R.id.btn_confirm:
                doCheck();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }


    private void doCheck() {
        if (TextUtils.isEmpty(etMatname.getText().toString().trim())) {
            showToast("品名不能为空");
            return;
        }
        if (TextUtils.isEmpty(etStandard.getText().toString().trim())) {
            showToast("规格不能为空");
            return;
        }
        if (TextUtils.isEmpty(etShoppurchaseprice.getText().toString().trim())) {
            showToast("平台采购价格不能为空");
            return;
        }
        if (TextUtils.isEmpty(etStandardsaleprice.getText().toString().trim())) {
            showToast("销售价格不能为空");
            return;
        }
        if (TextUtils.isEmpty(etApplynum.getText().toString().trim())) {
            showToast("上市数量不能为空");
            return;
        }
        if (TextUtils.isEmpty(tvSalebtime.getText().toString().trim())) {
            showToast("请选择上架时间");
            return;
        }
        if (TextUtils.isEmpty(tvSaleetime.getText().toString().trim())) {
            showToast("请选择下架时间");
            return;
        }
        if (TextUtils.isEmpty(etCanarrivearea.getText().toString().trim())) {
            showToast("请填写到达区域");
            return;
        }
        if (TextUtils.isEmpty(etMinbuynum.getText().toString().trim())) {
            showToast("请填写最低批量");
            return;
        }
        if (TextUtils.isEmpty(etPackway.getText().toString().trim())) {
            showToast("请填写包装方式");
            return;
        }
        if (TextUtils.isEmpty(etExpirationdate.getText().toString().trim())) {
            showToast("请填写保质期");
            return;
        }
        if ("".equals(photoLogo) || null == photoLogo) {
            showToast("请上传商品Logo图");
            return;
        }
        if ("".equals(photoAttes) || null == photoAttes) {
            showToast("请上传厂家资质认证图");
            return;
        }
        if ("".equals(photoQuality) || null == photoQuality) {
            showToast("请上传商品资质认证图");
            return;
        }
        if (bannerPhotoList.size() < 1) {
            showToast("请至少上传1张商品图片用于展示");
            return;
        }
        setUnionSale();
    }
    /*提交申报*/
    private void setUnionSale() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONArray jsonArray = new JSONArray();
            JSONObject photoObj = new JSONObject();
            try {
                for (int i = 0; i < bannerPhotoList.size(); i++) {
                    photoObj.put("imgname", bannerPhotoList.get(i));
                    jsonArray.put(photoObj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject object = new JSONObject();
            try {
                object.put("key", "");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("matname", etMatname.getText().toString().trim());
                object.put("standard", etStandard.getText().toString().trim());
                object.put("sunitid", unitId);
                object.put("shoppurchaseprice", etShoppurchaseprice.getText().toString().trim());
                object.put("standardsaleprice", etStandard.getText().toString().trim());
                object.put("applynum", etApplynum.getText().toString().trim());
                object.put("salebtime", tvSalebtime.getText().toString().trim());
                object.put("saleetime", tvSaleetime.getText().toString().trim());
                object.put("canarrivearea", etCanarrivearea.getText().toString().trim());
                object.put("minbuynum", etMinbuynum.getText().toString().trim());
                object.put("packway", etPackway.getText().toString().trim());
                object.put("storageway", storageWay);
                object.put("expirationdate", etExpirationdate.getText().toString().trim());
                object.put("shipfrom", shipFrom);
                object.put("saleway", saleWay);
                object.put("logoimgurl", photoLogo);
                object.put("facqualificationimgurl", photoAttes);
                object.put("prodqualificationimgurl", photoQuality);
                object.put("adimgurl", photoAd);
                object.put("imgdetail", String.valueOf(jsonArray));
                object.put("adurl", etAdurl.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(webUrl)
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
                                    startActivity(new Intent(mContext, LxshActivity.class));
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

    private void showTimePicker(final boolean isBegin) {
        Calendar startDate = Calendar.getInstance();
        startDate.set(2099, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        TimePickerView tp = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (isBegin) {
                    tvSalebtime.setText(CommonUtils.getTime(date, "yyyy-MM-dd"));
                } else {
                    tvSaleetime.setText(CommonUtils.getTime(date, "yyyy-MM-dd"));
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setCancelText("取消").setDate(endDate).setRangDate(endDate, startDate).build();
        tp.show();
    }

    /*图片选择*/
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
        popupWindow.showAtLocation(btnConfirm, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupWindow.update();
    }

    /*单位选择*/
    private void showSunitChoose() {
        popupSunitWindow = new OperatePopupWindow(mContext, "请选择", sunitItemList, new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                unitId = unitList.get(position).getUnitid();
                tvSunit.setText(sunitItemList.get(position).getText());
                popupSunitWindow.dismiss();
            }
        });
        popupSunitWindow.showAtLocation(btnConfirm, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupSunitWindow.update();
    }


    @Override
    public void takeSuccess(TResult result) {
        Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
        switch (photoPosition) {
            case 0:
                ivLxLogo.setImageBitmap(bm);
                upLoadImage(result.getImages(),0);
                break;
            case 1:
                ivLxAttes.setImageBitmap(bm);
                upLoadImage(result.getImages(),1);
                break;
            case 2:
                ivLxQuality.setImageBitmap(bm);
                upLoadImage(result.getImages(),2);
                break;
            case 3:
                ivLxAd.setImageBitmap(bm);
                upLoadImage(result.getImages(),3);
                break;
            case 4:
                upLoadImage(result.getImages(),4);
                netImages.add(result.getImages().get(0).getOriginalPath());
                bannerView.setImages(netImages);
                bannerView.start();
                if (netImages.size() > 0)
                    ivCover.setVisibility(View.GONE);
                tvAddphoto.setVisibility(View.INVISIBLE);
                ivSAddphoto.setVisibility(View.VISIBLE);
                break;
        }
    }

    /*private void startObjAnimation(boolean isZero) {
        if (isZero) {
            AnimatorSet set = new AnimatorSet();
            set.play(ObjectAnimator.ofFloat(tvAddphoto, "scaleX", 0.5f, 1f, 1f))
                    .with(ObjectAnimator.ofFloat(tvAddphoto, "scaleY", 0.5f, 1f, 1f))
                    .with(ObjectAnimator.ofFloat(tvAddphoto, "translationX", -400f, 0f, 0f))
                    .with(ObjectAnimator.ofFloat(tvAddphoto, "translationY", 200f, 0f, 0f));
            set.setDuration(2000);
            set.start();
        } else {
            AnimatorSet set = new AnimatorSet();
            set.play(ObjectAnimator.ofFloat(tvAddphoto, "scaleX", 1f, 0.5f, 0.5f))
                    .with(ObjectAnimator.ofFloat(tvAddphoto, "scaleY", 1f, 0.5f, 0.5f))
                    .with(ObjectAnimator.ofFloat(tvAddphoto, "translationX", 0f, -400f, -400f))
                    .with(ObjectAnimator.ofFloat(tvAddphoto, "translationY", 0f, 200f, 200f));
            set.setDuration(2000);
            set.start();
        }
    }*/

    private void upLoadImage(final ArrayList<TImage> images,final int pos) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.HOST_TWO)
                    .addFile("mFile", images.get(0).getOriginalPath(), new File(images.get(0).getOriginalPath()))
                    .addParams("isrealsys", AyjSwApplication.IS_DEBUG ? "0" : "1")
                    .addParams("folder", "unionsalematerial")
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
                                            photoLogo = check.getData().toString();
                                            break;
                                        case 1:
                                            photoAttes = check.getData().toString();
                                            break;
                                        case 2:
                                            photoQuality = check.getData().toString();
                                            break;
                                        case 3:
                                            photoAd = check.getData().toString();
                                            break;
                                        case 4:
                                            bannerPhotoList.add(check.getData().toString());
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

    @Override
    public void takeFail(TResult result, String msg) {
        showToast("获取图片失败");
    }

    @Override
    public void takeCancel() {
        showToast("获取图片失败");
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

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_storageway_leng:
                storageWay = "冷藏";
                break;
            case R.id.rb_storageway_bx:
                storageWay = "保鲜";
                break;
            case R.id.rb_storageway_pt:
                storageWay = "普通";
                break;
            case R.id.rb_shipfrom_fw:
                shipFrom = Constant.SHIPFROM_FW;
                break;
            case R.id.rb_shipfrom_bd:
                shipFrom = Constant.SHIPFROM_BD;
                break;
            case R.id.rb_shipfrom_cc:
                shipFrom = Constant.SHIPFROM_CC;
                break;
            case R.id.rb_saleway_zx:
                saleWay = Constant.SALEWAY_ZX;
                break;
            case R.id.rb_saleway_cg:
                saleWay = Constant.SALEWAY_CG;
                break;
            case R.id.rb_saleway_ft:
                saleWay = Constant.SALEWAY_FT;
                break;
        }
    }
}
