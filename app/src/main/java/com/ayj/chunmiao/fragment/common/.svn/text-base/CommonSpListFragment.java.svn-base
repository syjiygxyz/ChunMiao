package com.ayj.chunmiao.fragment.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.common.CommonShoppingDetailActivity;
import com.ayj.chunmiao.adapter.JbdhAdapter;
import com.ayj.chunmiao.adapter.cmxd.CommonSpListAdapter;
import com.ayj.chunmiao.adapter.cmxd.DhListAdapter;
import com.ayj.chunmiao.adapter.cmxd.ZkSpListAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.fragment.base.LazyFragment;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.DividerItemDecoration;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2017/6/7.
 * 通用的商品列表
 */
public class CommonSpListFragment extends LazyFragment {

    FootZqShop footZqShop = new FootZqShop();

    CommonSpListAdapter commonSpAdapter;

    View view;

    String isHot;

    String matclass2;

    String matclass1;

    private Bundle bd;

    String ordertype;

    DhListAdapter dhAdapter;

    ZkSpListAdapter zkSpListAdapter;

    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcf_refresh;//刷新

    @BindView(R.id.recycler_view)
    RecyclerViewFinal recycler_view;//加载更多

    private List<FootZqShop.DataBean> shopList = new ArrayList<>();

    int start = 0;

    int countSize = 10;

    String type1;

    UserPerson mUserPerson;

    JbdhAdapter jbdhAdapter;

    /*跳转参数*/
    public static CommonSpListFragment newInstance(String param1, String param2, String param3,
            String param4, String param5) {
        CommonSpListFragment fragment = new CommonSpListFragment();
        Bundle args = new Bundle();
        args.putString("matclass2", param1);
        args.putString("ishot", param2);
        args.putString("matclass1", param3);
        args.putString("ordertype", param4);
        args.putString("type1", param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_sp_list;
    }

    @Override
    public void fetchData() {
        initView();
        getSp();
        recycler_view.setOnItemClickListener(
                new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                        Intent intent = new Intent();
                        intent.putExtra("type",type1);
                        intent.putExtra("ordertype",ordertype);
                        intent.putExtra("matid", shopList.get(position).getMatid());
                        intent.setClass(getActivity(), CommonShoppingDetailActivity.class);
                        startActivity(intent);
                    }
                });
    }


    private void initView() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        if (getArguments() != null) {
            bd = getArguments();
            isHot = bd.getString("ishot");
            matclass2 = bd.getString("matclass2");
            matclass1 = bd.getString("matclass1");
            ordertype = bd.getString("ordertype");
            type1 = bd.getString("type1");
        }
        pcf_refresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                start = 0;
                shopList.clear();
                getSp();
            }
        });
        recycler_view.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //发起加载更多请求
                getSp();
            }
        });
    }

    private void getSp() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SP_DETAIL_URL))
                    .addParams("json","{\"key\":\"\",\"ordertype\":\"" + ordertype + "\","
                            + "\"matclass1\":\"" + matclass1 + "\","
                            + "\"shopid\":\"" + mUserPerson.getData().get(0).getRegshopid() + "\","
                            + "\"matname\":\"\"," + "\"ishot\":\"" + isHot + "\","
                            + "\"startrow\":\"" + start + "\",\"endrow\":\"" + (start + countSize)
                            + "\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            Toast.makeText(getActivity(), "获取商品信息失败", Toast.LENGTH_SHORT).show();
                            pcf_refresh.onRefreshComplete();
                            recycler_view.onLoadMoreComplete();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcf_refresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    start = start + countSize + 1;
                                    footZqShop = new Gson().fromJson(response, FootZqShop.class);
                                    if (footZqShop.getData().size() != 0) {
                                        shopList.addAll(footZqShop.getData());
                                        if ("dhzx".equals(type1)) {
                                            /*兑换中心*/
                                            if (dhAdapter == null) {
                                                dhAdapter = new DhListAdapter(
                                                        shopList);
                                                recycler_view.setAdapter(dhAdapter);
                                            } else {
                                                dhAdapter.setNewData(shopList);
                                            }
                                        } else if ("zkp".equals(type1)) {
                                            /*折扣铺*/
                                            if (zkSpListAdapter == null) {
                                                zkSpListAdapter = new ZkSpListAdapter(
                                                        shopList);
                                                recycler_view.setAdapter(zkSpListAdapter);
                                            } else {
                                                zkSpListAdapter.setNewData(shopList);
                                            }
                                        } else if("jbdh".equals(type1)||"aebdh".equals(type1)||"wjdh".equals(type1)){
                                            if (jbdhAdapter == null) {
                                                jbdhAdapter = new JbdhAdapter(
                                                        shopList,type1);
                                                recycler_view.setAdapter(jbdhAdapter);
                                            } else {
                                                jbdhAdapter.setNewData(shopList);
                                            }
                                        }else {
                                            if (commonSpAdapter == null) {
                                                commonSpAdapter = new CommonSpListAdapter(
                                                        shopList);
                                                recycler_view.setAdapter(commonSpAdapter);
                                            } else {
                                                commonSpAdapter.setNewData(shopList);
                                            }
                                        }
                                    }
                                    if (footZqShop.getData().size() < countSize) {
                                        recycler_view.setHasLoadMore(false);
                                    } else {
                                        recycler_view.setHasLoadMore(true);
                                    }
                                    break;
                                default:
                                    Toast.makeText(getActivity(), "获取商品信息失败",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
}
