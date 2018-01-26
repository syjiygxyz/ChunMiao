package com.ayj.chunmiao.activity.cmbz.insurance.clbx;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.bjfk.InsuranceFeedbackActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.clxx.VehicleInfoActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.cmbz.bx.CommonBxDetailsAdapter;
import com.ayj.chunmiao.adapter.cmbz.bx.CzyZfAdapter;
import com.ayj.chunmiao.adapter.cmbz.bx.InsurancePlanAdapater;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.bx.BzfwBean;
import com.ayj.chunmiao.bean.cmbz.InsuranceBrandBean;
import com.ayj.chunmiao.bean.cmbz.InsurancePlanBean;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.cmbz.VehicleBean;
import com.ayj.chunmiao.bean.eventbus.ClEvent;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.MyLayoutManager;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/29.
 */
public class InsuranceCarSetOrderActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_load_positive)
    TextView ivLoadPositive;
    @BindView(R.id.iv_load_negative)
    TextView ivLoadNegative;
    @BindView(R.id.iv_xsz_positive)
    TextView iv_xsz_positive;
    @BindView(R.id.iv_xsz_negative)
    TextView iv_xsz_negative;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_carno)
    TextView tv_carno;
    @BindView(R.id.tv_vin)
    TextView tv_vin;

    @BindView(R.id.tv_baojia)
    TextView tvPost;
    private String insbrokerid;
    private String istransfercar;
    private String transferdate, tbrcarno, tbrname;
    List<String> orderdetail = new ArrayList<>();
    InsuranceBrandBean.DataBean itemDataBean;
    List<UtilityItem> itemList = new ArrayList<>();
    List<InsurancePlanBean.DataBean> planList = new ArrayList<>(); //保险方案
    private InsurancePlanAdapater insurancePlanAdapater;
    private Uri imageUri;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private OperatePopupWindow pop;
    private CropOptions options;
    @BindView(R.id.clv)
    RecyclerView rlv;

    UserPerson mUserPerson;

    BzfwBean bxFaBean;

    int firstXz;

    int takePhotoPosition;

    List<VehicleBean.DataBean> vehicleInfoList = new ArrayList<>();

    CommonBxDetailsAdapter commonBxDetailsAdapter;

    String takePhotoOne="", takePhotoTwo="", takePhotoThree="", takePhotoFour="";


    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_car_set_order;
    }

    @Override
    public void initDatas() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        EventBus.getDefault().register(this);
        tvTitle.setText("获取报价");
        UtilityItem item = new UtilityItem();
        item.setText("拍照");
        itemList.add(item);
        UtilityItem item1 = new UtilityItem();
        item1.setText("相册");
        itemList.add(item1);
        insbrokerid = getIntent().getStringExtra("brokerid");
        itemDataBean = (InsuranceBrandBean.DataBean) getIntent().getSerializableExtra("state");
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
        rlv.setLayoutManager(new MyLayoutManager(mContext));
        getInsurancePlan();
        rlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < bxFaBean.getData().size(); i++) {
                    bxFaBean.getData().get(i).setTrue(false);
                }
                bxFaBean.getData().get(position).setTrue(true);
                commonBxDetailsAdapter.setNewData(bxFaBean.getData());
                commonBxDetailsAdapter.notifyDataSetChanged();
                firstXz = position;
                orderdetail.clear();
                for (int j=0;j<bxFaBean.getData().get(position).getContentList().size();j++) {
                    orderdetail.add("{\"insurancebrand\":\""+itemDataBean.getInsurancebrand()+"\",\"contentsnid\":\""+bxFaBean.getData().get(position).getContentList().get(j).getSnid()+"\"}");
                }
            }
        });
    }

    private void getInsurancePlan() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("icsnid", "59");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_BXFA_URL))
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
                                    bxFaBean = new Gson().fromJson(response, BzfwBean.class);
                                    for (int i = 0; i < bxFaBean.getData().size(); i++) {
                                        if (0 == i) {
                                            bxFaBean.getData().get(i).setTrue(true);
                                        } else {
                                            bxFaBean.getData().get(i).setTrue(false);
                                        }
                                    }
                                    if (null == commonBxDetailsAdapter) {
                                        commonBxDetailsAdapter = new CommonBxDetailsAdapter(R.layout.item_ljbj1, bxFaBean.getData());
                                        rlv.setAdapter(commonBxDetailsAdapter);
                                    } else {
                                        commonBxDetailsAdapter.setNewData(bxFaBean.getData());
                                    }
                                    for (int j=0;j<bxFaBean.getData().get(0).getContentList().size();j++) {
                                        orderdetail.add("{\"insurancebrand\":\""+itemDataBean.getInsurancebrand()+"\",\"contentsnid\":\""+bxFaBean.getData().get(0).getContentList().get(j).getSnid()+"\"}");
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

    private void senOrder() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("insordertype", "INSORDERTYPE001");
                jsonObject.put("msnid", mUserPerson.getData().get(0).getSnid());
                jsonObject.put("pwd", mUserPerson.getData().get(0).getPassWord());
                jsonObject.put("insfrom", "INSFROM003");
                jsonObject.put("insbrokerid", insbrokerid);
                jsonObject.put("cityid", getIntent().getStringExtra("city_id"));
                jsonObject.put("istransfercar", getIntent().getStringExtra("istransfercar"));
                if(!"".equals(getIntent().getStringExtra("transferdate"))){
                    jsonObject.put("transferdate", getIntent().getStringExtra("transferdate"));
                }
                jsonObject.put("carsnid",vehicleInfoList.get(0).getSnid());
                jsonObject.put("icsnid", "59");
                jsonObject.put("issnid", bxFaBean.getData().get(firstXz).getSnid());
                jsonObject.put("tbrname", getIntent().getStringExtra("name"));
                jsonObject.put("tbrcardtype", "IDCARDTYPE001");
                jsonObject.put("tbrcardno", getIntent().getStringExtra("sfz"));
                jsonObject.put("imgdetail","[{\"imgurl\":\""+takePhotoOne+"\"},{\"imgurl\":\""+takePhotoTwo+"\"},{\"imgurl\":\""+takePhotoThree+"\"},{\"imgurl\":\""+takePhotoFour+"\"}]");
                jsonObject.put("orderdetail",orderdetail);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SET_CXDD_URL))
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
                                    showToast("下单成功，等待报价");
                                    Intent intent = new Intent(mContext, InsuranceFeedbackActivity.class);
                                    startActivity(intent);
                                    EventBus.getDefault().post(
                                            new FirstEvent("carsetbeforefinish"));
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


    @OnClick({R.id.iv_load_positive, R.id.iv_load_negative, R.id.iv_back, R.id.tv_baojia, R.id.iv_xsz_positive, R.id.iv_xsz_negative, R.id.tv_carno, R.id.tv_vin, R.id.tv_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_load_positive:
                takePhotoPosition = 1;
                showPop();
                break;
            case R.id.iv_load_negative:
                takePhotoPosition = 2;
                showPop();
                break;
            case R.id.iv_xsz_positive:
                takePhotoPosition = 3;
                showPop();
                break;
            case R.id.iv_xsz_negative:
                takePhotoPosition = 4;
                showPop();
                break;
            case R.id.tv_baojia:
                doCheck();
                break;
            case R.id.tv_carno:
            case R.id.tv_vin:
            case R.id.tv_type:
                Intent mIntent = new Intent(mContext, VehicleInfoActivity.class);
                mIntent.putExtra("type", "0");
                mIntent.putExtra("count", 1);
                startActivity(mIntent);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(ClEvent event) {
        if ("czyZf".equals(event.getmMsg())) {
            vehicleInfoList.clear();
            tv_carno.setText(event.getVehicleInfoList().get(0).getCarno());
            tv_vin.setText(event.getVehicleInfoList().get(0).getVin());
            tv_type.setText(event.getVehicleInfoList().get(0).getCarbrandshow() + event.getVehicleInfoList().get(0).getCarlines());
            vehicleInfoList = event.getVehicleInfoList();
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
    public void takeFail(TResult result, String msg) {
        showToast("拍照失败");
    }

    @Override
    public void takeCancel() {
        showToast("拍照取消");
    }

    @Override
    public void takeSuccess(TResult result) {
        if (1 == takePhotoPosition) {
            Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            ivLoadPositive.setCompoundDrawables(null, drawable, null, null);
            doCheck(result.getImages(), takePhotoPosition);
        } else if (2 == takePhotoPosition) {
            Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            ivLoadNegative.setCompoundDrawables(null, drawable, null, null);
            doCheck(result.getImages(), takePhotoPosition);
        } else if (3 == takePhotoPosition) {
            Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            iv_xsz_positive.setCompoundDrawables(null, drawable, null, null);
            doCheck(result.getImages(), takePhotoPosition);
        } else if (4 == takePhotoPosition) {
            Bitmap bm = BitmapFactory.decodeFile(result.getImages().get(0).getOriginalPath());
            Drawable drawable = new BitmapDrawable(bm);
            drawable.setBounds(0, 0, 300, 150);//必须设置图片大小，否则不显示
            iv_xsz_negative.setCompoundDrawables(null, drawable, null, null);
            doCheck(result.getImages(), takePhotoPosition);
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

    private void doCheck(ArrayList<TImage> images,final int position) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .addFile("mFile", images.get(0).getOriginalPath(), new File(images.get(0).getOriginalPath()))
                    .url(WebUtils.HOST_TWO)
                    .addParams("isrealsys", AyjSwApplication.IS_DEBUG ? "0" : "1")
                    .addParams("folder", "insurance")
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
                                    if (1 == position) {
                                        takePhotoOne = check.getData().toString();
                                    } else if (2 == position) {
                                        takePhotoTwo = check.getData().toString();
                                    } else if (3 == position) {
                                        takePhotoThree = check.getData().toString();
                                    } else if (4 == position) {
                                        takePhotoFour = check.getData().toString();
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

    private void doCheck() {
        if (takePhotoOne.equals("")) {
            showToast("需要提交身份证正面");
            return;
        }
        if (takePhotoTwo.equals("")) {
            showToast("需要提交身份证反面");
            return;
        }
        if (takePhotoThree.equals("")) {
            showToast("需要提交驾驶证正面");
            return;
        }
        if (takePhotoFour.equals("")) {
            showToast("需要提交驾驶证反面");
            return;
        }
        if (TextUtils.isEmpty(tv_type.getText().toString())) {
            showToast("请选择车辆信息");
            return;
        }
        senOrder();
    }
}

