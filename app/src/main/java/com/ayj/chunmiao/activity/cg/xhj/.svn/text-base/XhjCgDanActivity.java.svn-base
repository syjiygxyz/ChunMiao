package com.ayj.chunmiao.activity.cg.xhj;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.cg.XhjShopCartAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cg.CgGwcBean;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.shopping.ShopCart;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;
/*
* 采购单
* */
public class XhjCgDanActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    Shoppingmall shopCart;

    private List<Shoppingmall.DataBean> shopCartList = new ArrayList<>();

    UserPerson mUserPerson;

    String orderType;

    XhjShopCartAdapter xhjShopCartAdapter;

    Handler mHandler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xhj_cg_dan;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("采购单");
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        orderType = getIntent().getStringExtra("orderType");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                shopCartList.clear();
                getShopCarInfo();
            }
        });
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0 :
                        reviseShopCart(msg.getData().getString("snid"),msg.getData().getString("num"),msg.getData().getInt("position"));
                        break;
                    case 1 :
                        deleteShopCart(msg.getData().getString("snid"),msg.getData().getInt("position"));
                        break;
                    case 2:
                        shopCartList.remove(msg.arg1);
                        xhjShopCartAdapter.notifyDataSetChanged();
                    case 3 :
                        computeTotalPrice();
                        break;
                }
            }
        };
    }
    /*删除购物车商品*/
    private void deleteShopCart(String snid, final int position) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                jsonObject.put("snid", snid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.DELETE_GWC))
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
                                case 0 :
                                    shopCartList.clear();
                                    getShopCarInfo();
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else {
          showToast(R.string.TheNetIsUnAble);
        }
    }

    /*修改购物车*/
    private void reviseShopCart(String snid,final String num , final int position) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                jsonObject.put("snid", snid);
                jsonObject.put("num",num);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.DELETE_GWC))
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
                                    shopCartList.get(position).setNum(num);
                                    computeTotalPrice();
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
    public void configViews() {
        getShopCarInfo();
    }

    private void computeTotalPrice() {
        double totalPrice = 0.00 ;
        for(int i=0 ; i < shopCartList.size() ; i++){
            int numb = Integer.parseInt(shopCartList.get(i).getNum());
            Double price = Double.parseDouble(shopCartList.get(i).getShoppurchaseprice());
            Double itemPrice = numb * price;
            totalPrice += itemPrice;
        }
        tvTotalprice.setText("￥"+new DecimalFormat("#.00").format(totalPrice));
    }

    /*获取购物车*/
    private void getShopCarInfo() {
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
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CG_GWC))
                    .addParams("json",String.valueOf(jsonObject))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    shopCart = new Gson().fromJson(response,Shoppingmall.class);
                                    shopCartList.addAll(shopCart.getData());
                                    if (null == xhjShopCartAdapter){
                                        xhjShopCartAdapter = new XhjShopCartAdapter(R.layout.item_cg_xhj_cgd,shopCartList,mHandler);
                                        mRecyclerView.setAdapter(xhjShopCartAdapter);
                                    }else {
                                        xhjShopCartAdapter.setNewData(shopCartList);
                                    }
                                    mHandler.sendEmptyMessage(3);
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                Intent intentBuy = new Intent(mContext,XhjBuyNowActivity.class);
                intentBuy.putExtra("goods",(Serializable)shopCartList);
                intentBuy.putExtra("orderType",orderType);
                intentBuy.putExtra("type","2");
                startActivity(intentBuy);
                break;
        }
    }
}
