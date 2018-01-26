package com.ayj.chunmiao.fragment.myinfo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.myinformation.XjkAdapter;
import com.ayj.chunmiao.adapter.myinformation.XjkTxAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.ChangeMoneyBean;
import com.ayj.chunmiao.bean.myinformation.DrawApplyBean;
import com.ayj.chunmiao.fragment.base.LazyFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by zht-pc-04 on 2017/8/17.
 */

public class XjkFragment extends LazyFragment {

    String changeType;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;
    private String webUrl;
    private int pageNo = 1;
    private int pageSize = 20;
    private List<ChangeMoneyBean.DataBean> list = new ArrayList<>();
    private List<DrawApplyBean.DataBean> txList = new ArrayList<>();
    private List beanList = new ArrayList<>();
    private XjkAdapter adapter;
    private XjkTxAdapter txAdapter;


    public static XjkFragment newInstance(String changeType) {
        XjkFragment xjkFragment = new XjkFragment();
        Bundle bundle = new Bundle();
        bundle.putString("changetype", changeType);
        xjkFragment.setArguments(bundle);
        return xjkFragment;
    }

    @Override
    public void fetchData() {
        if (null != getArguments()) {
            changeType = getArguments().getString("changetype");
            webUrl = changeType.equals(Constant.CHANGE_TYPE_TX)? WebUtils.getRequestUrl(WebUtils.SHOP_WITHDRAW_HIS) : WebUtils.getRequestUrl(WebUtils.MONEY_CHANGE);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo = 1;
                list.clear();
                getShopMoney();
            }
        });
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getShopMoney();
            }
        });
        getShopMoney();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xjk;
    }

    private void getShopMoney() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("pageno",pageNo);
                object.put("pagesize",pageSize);
                object.put("bagtype", Constant.BAG_TYPE_JINK);
                object.put("changetype", changeType);
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
                            pcfRefresh.onRefreshComplete();
                            recyclerView.onLoadMoreComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            recyclerView.onLoadMoreComplete();
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:

                                    if (changeType.equals(Constant.CHANGE_TYPE_TX)){
                                        DrawApplyBean drawApplyBean = new Gson().fromJson(response,DrawApplyBean.class);
                                        beanList = drawApplyBean.getData();
                                        txList.addAll(beanList);
                                        if (null == txAdapter){
                                            txAdapter = new XjkTxAdapter(R.layout.item_jink,txList);
                                            recyclerView.setAdapter(txAdapter);
                                        }else{
                                            txAdapter.setNewData(txList);
                                        }
                                    }else {
                                        ChangeMoneyBean bean = new Gson().fromJson(response,ChangeMoneyBean.class);
                                        beanList = bean.getData();
                                        list.addAll(bean.getData());
                                        if (null == adapter){
                                            adapter = new XjkAdapter(R.layout.item_jink,list);
                                            recyclerView.setAdapter(adapter);
                                        }else{
                                            adapter.setNewData(list);
                                        }
                                    }
                                    if (beanList.size()<pageSize){
                                        recyclerView.setHasLoadMore(false);
                                    }else {
                                        recyclerView.setHasLoadMore(true);
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
}
