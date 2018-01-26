package com.ayj.chunmiao.fragment.khq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.common.CommonShoppingDetailActivity;
import com.ayj.chunmiao.adapter.cmxd.CommonSpListAdapter;
import com.ayj.chunmiao.adapter.cmxd.DhListAdapter;
import com.ayj.chunmiao.adapter.cmxd.ZkSpListAdapter;
import com.ayj.chunmiao.adapter.khq.FwdPlAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.khq.ShopPjBean;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/20.
 */
public class PjFragment extends LazyFragment {
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;
    int start = 0;

    int countSize = 10;

    private List<ShopPjBean.DataBean> shopList = new ArrayList<>();

    UserPerson mUserPerson;

    FwdPlAdapter fwdPlAdapter;
    @Override
    public void fetchData() {
        initView();

    }


    private void initView() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        getPl();
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                start = 0;
                shopList.clear();
                getPl();
            }
        });
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //发起加载更多请求
                getPl();
            }
        });
    }

    private void getPl() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CH_PJ))
                    .addParams("json","{\"key\":\"\",\"shopid\":\"" + mUserPerson.getData().get(0).getRegshopid() + "\",\"startrow\":\"" + start
                            + "\","
                            + "\"endrow\":\"" + (start + countSize) + "\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            Toast.makeText(getActivity(), "获取服务点评价失败", Toast.LENGTH_SHORT).show();
                            pcfRefresh.onRefreshComplete();
                            recyclerView.onLoadMoreComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            recyclerView.onLoadMoreComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    start = start + countSize + 1;
                                    ShopPjBean shopPjBean = new Gson().fromJson(response,
                                            ShopPjBean.class);
                                    if (shopPjBean.getData().size() != 0) {
                                        shopList.addAll(shopPjBean.getData());
                                    }
                                    if(null==fwdPlAdapter){
                                        fwdPlAdapter = new FwdPlAdapter(R.layout.item_pj,shopList);
                                        recyclerView.setAdapter(fwdPlAdapter);
                                    }else{
                                        fwdPlAdapter.setNewData(shopList);
                                    }
                                    if (shopPjBean.getData().size() < countSize) {
                                        recyclerView.setHasLoadMore(false);
                                    } else {
                                        recyclerView.setHasLoadMore(true);
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
}
