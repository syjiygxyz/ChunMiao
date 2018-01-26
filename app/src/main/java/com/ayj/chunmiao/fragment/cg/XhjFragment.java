package com.ayj.chunmiao.fragment.cg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.cg.xhj.XhjBuyNowActivity;
import com.ayj.chunmiao.activity.cg.xhj.XhjDetailActivity;
import com.ayj.chunmiao.adapter.cg.XhjHroAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.fragment.base.LazyFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/21.
 * 采购现货架
 */
public class XhjFragment extends LazyFragment {

    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;

    int pagesize = 20;

    int pageno = 1;

    List<Shoppingmall.DataBean> list = new ArrayList<>();
    List<Shoppingmall.DataBean> goods = new ArrayList<>();
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    private Map<Integer, Integer> numMap = new HashMap<>();
    private Map<Integer, Boolean> isSelectMap = new HashMap<>();

    Shoppingmall mClBean;

    XhjHroAdapter mClAdapter;

    String orderType;

    private Bundle bd;
    private String matClass;

    /*跳转参数*/
    public static XhjFragment newInstance(String ordertype,String matclass) {
        XhjFragment fragment = new XhjFragment();
        Bundle args = new Bundle();
        args.putString("ordertype", ordertype);
        args.putString("matclass",matclass);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void fetchData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (getArguments() != null) {
            bd = getArguments();
            orderType = bd.getString("ordertype");
            matClass = bd.getString("matclass");
        }
        getList();
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageno = 1;
                list.clear();
                if (null != mClAdapter){
                    mClAdapter.initMap();
                }
                getList();
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                getList();
            }
        });

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_xhj;
    }

    private void getList() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("ordertype", orderType);
                obj.put("matclass1",matClass);
                obj.put("pageno",pageno);
                obj.put("pagesize",pagesize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_SHOPPING_MALL_LIST))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            mRecyclerView.onLoadMoreComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            mRecyclerView.onLoadMoreComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    pageno++;
                                    mClBean = new Gson().fromJson(response,
                                            Shoppingmall.class);
                                    list.addAll(mClBean.getData());
                                    if (null == mClAdapter) {
                                        mClAdapter = new XhjHroAdapter(R.layout.item_cg_xhj_hor, list);
                                        mRecyclerView.setAdapter(mClAdapter);
                                    } else {
                                        mClAdapter.setNewData(list);
                                    }
                                    mClAdapter.setOnItemClickListener(new XhjHroAdapter.RecyclerOnItemClickListener() {
                                        @Override
                                        public void onItemClickListener(int position) {
                                            Intent intent = new Intent();
                                            intent.putExtra("item", (Serializable) list.get(position));
                                            intent.setClass(getActivity(), XhjDetailActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                    if (mClBean.getData().size()<pagesize){
                                        mRecyclerView.setHasLoadMore(false);
                                    }else {
                                        mRecyclerView.setHasLoadMore(true);
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
    /*获取选中的商品列表*/
    private void arrangeData() {
        goods.clear();
        isSelectMap = mClAdapter.getisSelectMap();
        numMap = mClAdapter.getNumMap();
        for (int i = 0; i < isSelectMap.size(); i++) {
            if (isSelectMap.get(i) && numMap.get(i)>0) {
                Shoppingmall.DataBean good = list.get(i);
                good.setNum(String.valueOf(numMap.get(i)));
                goods.add(good);
            }
        }
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        arrangeData();
        if (goods.size() > 0) {
            Intent intent = new Intent();
            intent.putExtra("goods", (Serializable) goods);
            intent.putExtra("orderType", orderType);
            intent.putExtra("type", "1");
            intent.setClass(getActivity(), XhjBuyNowActivity.class);
            startActivity(intent);
        }else{
            showToast("请选择想要购买的商品");
        }
    }
}
