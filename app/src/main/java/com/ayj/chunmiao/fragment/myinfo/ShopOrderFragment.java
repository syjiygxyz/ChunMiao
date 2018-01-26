package com.ayj.chunmiao.fragment.myinfo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.myinformation.shoporder.ShopOrderActivity;
import com.ayj.chunmiao.activity.myinformation.shoporder.StockOrderDetailActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.myinformation.ShopStockAdapter;
import com.ayj.chunmiao.adapter.myinformation.StockDetailAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.myinformation.ShopStockBean;
import com.ayj.chunmiao.bean.myinformation.StockDetailBean;
import com.ayj.chunmiao.fragment.base.LazyFragment;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.PayWayPopupWindow;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by zht-pc-04 on 2017/8/21.
 */

public class ShopOrderFragment extends LazyFragment {

    private String orderType = "";
    private String status;
    private int pageNo = 1;
    private int pageSize = 12;
    private List<ShopStockBean.DataBean> list = new ArrayList<>();
    private ShopStockAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    SweetAlertDialog confirmDialog;
    PayWayPopupWindow payWayPopupWindow;

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    list.clear();
                    getOrderList();
                    break;
            }
        }
    };
    public void getTypeOrder(String orderType){
        this.orderType = orderType;
        list.clear();
        getOrderList();
    }

    public static ShopOrderFragment newInstance(String status) {
        ShopOrderFragment shopOrderFragment = new ShopOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("status", status);
        shopOrderFragment.setArguments(bundle);
        return shopOrderFragment;
    }

    @Override
    public void fetchData() {
        if (null != getArguments()) {
            status = getArguments().getString("status");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getOrderList();
            }
        });
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                orderType = "";
                pageNo = 1;
                list.clear();
                getOrderList();
            }
        });

        getOrderList();
    }
    /*门店采购单*/
    private void getOrderList() {
        if (CommonUtils.isNetworkAvailable(getActivity())){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("ordertype",orderType);
                object.put("status",status);
                object.put("pageno",pageNo);
                object.put("pagesize",pageSize);
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.MDGL_URL))
                    .addParams("json",String.valueOf(object))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            recyclerView.onLoadMoreComplete();
                            pcfRefresh.onRefreshComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            recyclerView.onLoadMoreComplete();
                            pcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    ShopStockBean shopStockBean = new Gson().fromJson(response,ShopStockBean.class);
                                    list.addAll(shopStockBean.getData());
                                    if (null == mAdapter){
                                        mAdapter = new ShopStockAdapter(R.layout.item_shop_order,list);
                                        recyclerView.setAdapter(mAdapter);
                                    }else{
                                        mAdapter.setNewData(list);
                                    }
                                    if (shopStockBean.getData().size() < pageSize){
                                        recyclerView.setHasLoadMore(false);
                                    }else{
                                        recyclerView.setHasLoadMore(true);
                                    }
                                    mAdapter.setOnItemClickListener(new ShopStockAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClickListener(int pos) {
                                            Intent intent = new Intent();
                                            intent.putExtra("order",(Serializable) list.get(pos));
                                            intent.setClass(getActivity(), StockOrderDetailActivity.class);
                                            startActivity(intent);
                                        }

                                        @Override
                                        public void cancelOrder(int pos) {
                                            showConfirmDialog("确定取消订单？",pos);
                                        }

                                        @Override
                                        public void payOrder(int pos) {
                                            showPayPop(pos);
                                        }

                                        @Override
                                        public void confirmOrder(int pos) {
                                            getOrderDetail(pos);
                                        }
                                    });
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

    private void getOrderDetail(final int pos) {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("snid", list.get(pos).getSnid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_STOCK_ORDER))
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
                            switch (check.getErr()){
                                case 0:
                                    StockDetailBean stockDetailBean = new Gson().fromJson(response,StockDetailBean.class);
                                    JSONObject obj = new JSONObject();
                                    JSONObject jsonObject = new JSONObject();
                                    JSONArray jsonArray = new JSONArray();
                                    try {
                                        for (int i=0; i<stockDetailBean.getData().size() ; i++){
                                            obj.put("detailsnid",stockDetailBean.getData().get(i).getSnid());
                                            obj.put("shnum",stockDetailBean.getData().get(i).getNum());
                                            jsonArray.put(obj);
                                        }
                                        jsonObject.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                                        jsonObject.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                                        jsonObject.put("shopstockinsnid",list.get(pos).getSnid());
                                        jsonObject.put("griddata",String.valueOf(jsonArray));
                                    }catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                    shouHuo(jsonObject);
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

    private void shouHuo(JSONObject obj) {
        if (CommonUtils.isNetworkAvailable(getActivity())){
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.RECEIVE_SHOP_STOCK_ORDER))
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
                                case 0:
                                    list.clear();
                                    getOrderList();
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


    private void showPayPop(final int pos) {
        payWayPopupWindow = new PayWayPopupWindow(getActivity(), "选择支付方式","小金库", CommonUtils.douFormat(list.get(pos).getTotalmoney()), UtilityItem.getPayWayList(), new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        ShopOrderActivity  activity = (ShopOrderActivity)getActivity();
                        activity.paybyali(CommonUtils.douFormat(list.get(pos).getTotalmoney()),list.get(pos).getSnid(),"春苗采购",Constant.CG);
                        payWayPopupWindow.dismiss();
                        break;
                    case 1:
                        CommonUtils.paybywx(getActivity(),list.get(pos).getSnid(),Constant.CG);
                        payWayPopupWindow.dismiss();
                        break;
                    case 2:
                        PassWordDialog passWordDialog = new PassWordDialog(getActivity());
                        passWordDialog.setTitle("输入支付密码");
                        passWordDialog.setCompleteListener(new DialogCompleteListener() {
                                                               @Override
                                                               public void dialogCompleteListener(String money, String pwd) {
                                                                   ShopOrderActivity  activity = (ShopOrderActivity)getActivity();
                                                                   activity.validateShopPayPwd(list.get(pos).getSnid(),pwd,Constant.CG,handler);
                                                               }
                                                           });
                        payWayPopupWindow.dismiss();
                        passWordDialog.show();
                        break;
                }
            }
        }, null,3);
        payWayPopupWindow.showAtLocation(recyclerView, Gravity.BOTTOM,0,0);
        payWayPopupWindow.update();
    }



    private void showConfirmDialog(String content,final int pos) {
       confirmDialog = CommonUtils.getConfirmDialog(getActivity(), "提示", content, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                cancelTheOrder(pos);
                sweetAlertDialog.dismiss();
            }
        });
        confirmDialog.show();
    }
    /*取消订单*/
    private void cancelTheOrder(final int pos) {
        if (CommonUtils.isNetworkAvailable(getActivity())){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("snid",list.get(pos).getSnid());
                object.put("ordertype",list.get(pos).getOrdertype());
            }catch (JSONException e ){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CANCEL_SHOP_STOCK_ORDER))
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
                                    list.remove(pos);
                                    if (null == mAdapter){
                                        mAdapter = new ShopStockAdapter(R.layout.item_shop_order,list);
                                        recyclerView.setAdapter(mAdapter);
                                    }else{
                                        mAdapter.setNewData(list);
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
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_order;
    }


}
