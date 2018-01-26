package com.ayj.chunmiao.activity.cg.xhj;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.shopping.JinmaoShopDetial;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubView;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class XhjDetailActivity extends BaseActivity {
    String matId, orderType;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.banner_main)
    Banner bannerMain;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.addandsub_shopcart)
    MyAddAndSubView addandsubShopcart;
    @BindView(R.id.iv_cp_detail)
    ImageView ivCpDetail;
    @BindView(R.id.btn_add_shopcart)
    Button btnAddShopcart;
    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;
    @BindView(R.id.tv_gw_count)
    TextView tvGwCount;

    int num = 1;


    private List<String> networkImages = new ArrayList<>();
    private List<Shoppingmall.DataBean> goods = new ArrayList<>();


    String toTalnum;
    Shoppingmall.DataBean good;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xhj_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("商品详情");
        good = (Shoppingmall.DataBean)getIntent().getSerializableExtra("item");
        matId = good.getMatid();
        orderType = good.getOrdertype();
        //toTalnum = good.getTotalnum();
        CommonUtils.setBannerAttribute(mContext, bannerMain);
        addandsubShopcart.setOnPosNegListener(
                new MyAddAndSubView.OnPosNegListener() {
                    @Override
                    public void onMyPositiveListener(int count) {
                        num = count;
                    }

                    @Override
                    public void onMyNegativeListener(int count) {
                        num = count;
                    }
                });
        addandsubShopcart.setOnClickAddAndSubListener(
                new MyAddAndSubView.OnClickAddAndSubListener() {
                    @Override
                    public void clickAdd(int count) {
                        num = count;
                    }

                    @Override
                    public void clickSub(int count) {
                        num = count;
                    }
                });
    }

    @Override
    public void configViews() {
        getInfo();
        getShopcartNum();
    }
    /*商品详情信息*/
    public void getInfo() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("ordertype", orderType);
                jsonObject.put("matid", matId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_DETAIL))
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
                                    JinmaoShopDetial jinmaoShopDetial = new Gson().fromJson(response, JinmaoShopDetial.class);

                                    String[] datas = jinmaoShopDetial.getData().get(
                                            0).getImgurlshow().split(",");
                                    for (int i = 0; i < datas.length; i++) {
                                        networkImages.add(datas[i]);
                                    }
                                    bannerMain.setImages(networkImages);
                                    bannerMain.start();
                                    if (!TextUtils.isEmpty(jinmaoShopDetial.getData().get(
                                            0).getIntroducemediaidcompressshow())) {
                                        Picasso.with(mContext).load(
                                                jinmaoShopDetial.getData().get(
                                                        0).getIntroducemediaidshow()).placeholder(R.mipmap.banner_loading).error(
                                                R.mipmap.banner_error).into(
                                                ivCpDetail);
                                    }
                                    toTalnum = jinmaoShopDetial.getData().get(0).getTotalnum() == null ? "0" : jinmaoShopDetial.getData().get(0).getTotalnum();
                                    tvKucun.setText("库存"+toTalnum);
                                    addandsubShopcart.setMax(
                                            Integer.parseInt(toTalnum));
                                    tvName.setText(jinmaoShopDetial.getData().get(0).getMatidshow());
                                    tvPrice.setText(jinmaoShopDetial.getData().get(0).getNowprice());
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

    /*获取购物车数量*/
    private void getShopcartNum() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                jsonObject.put("carttype", orderType);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CG_GWC))
                    .addParams("json",String.valueOf(jsonObject))
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
                                    tvGwCount.setVisibility(View.VISIBLE);
                                    if (check.getTotal() != 0) {
                                        tvGwCount.setVisibility(View.VISIBLE);
                                        if (check.getTotal() < 99) {
                                            tvGwCount.setText(check.getTotal() + "");
                                        } else {
                                            tvGwCount.setText("99+");
                                        }
                                    } else {
                                        tvGwCount.setVisibility(View.GONE);
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


    @OnClick({R.id.iv_back, R.id.btn_add_shopcart, R.id.btn_buy_now,R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add_shopcart:
                if (Integer.parseInt(toTalnum) < 1){
                    showToast("库存告罄，无法购买");
                }else {
                    addShopcart();
                }
                break;
            /*立即购买*/
            case R.id.btn_buy_now:
                if (Integer.parseInt(toTalnum) < 1){
                    showToast("库存告罄，无法购买");
                }else {
                    Intent intentBuy = new Intent(mContext,XhjBuyNowActivity.class);
                    goods.clear();
                    good.setNum(String.valueOf(num));
                    goods.add(good);
                    intentBuy.putExtra("goods",(Serializable)goods);
                    intentBuy.putExtra("orderType",orderType);
                    intentBuy.putExtra("type","1");
                    startActivity(intentBuy);
                }
                break;
            /*购物车图标*/
            case R.id.iv_right:
                Intent intent = new Intent(mContext, XhjCgDanActivity.class);
                intent.putExtra("orderType",orderType);
                startActivity(intent);
                break;
        }
    }
    /*添加到购物车*/
    private void addShopcart() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                jsonObject.put("carttype", orderType);
                jsonObject.put("matid",matId);
                jsonObject.put("shopid",AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getShopid());
                jsonObject.put("num",num);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.ADD_GWC))
                    .addParams("json",String.valueOf(jsonObject))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    CommonUtils.getSuccessDialog(mContext, "成功", "已加入购物车", new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismiss();
                                            getShopcartNum();
                                        }
                                    }).show();
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

}
