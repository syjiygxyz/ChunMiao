package com.ayj.chunmiao.activity.cmbz.fwq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.base.NewsActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.cmbz.tyq.TyqAdapter;
import com.ayj.chunmiao.adapter.cmxd.CommonSpListAdapter;
import com.ayj.chunmiao.adapter.cmxd.DhListAdapter;
import com.ayj.chunmiao.adapter.cmxd.ZkSpListAdapter;
import com.ayj.chunmiao.adapter.mdmanage.CgAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.MdCgBean;
import com.ayj.chunmiao.bean.cmbz.MyMemberCard;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/*
* 服务券
* */
public class FwqListActivity extends BaseActivity {

    int start = 0;

    int countSize = 10;

    TyqAdapter mTyqAdapter;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;

    private List<FootZqShop.DataBean> shopList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_fwq_list;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("服务券");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getList();
    }



    @Override
    public void configViews() {
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                start = 0;
                shopList.clear();
                getList();
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //发起加载更多请求
                getList();
            }
        });
        mRecyclerView.setOnItemClickListener(
                new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                        /*点击跳转*/
                        Intent intent = new Intent(mContext,FwqDetailActivity.class);
                        intent.putExtra("info",shopList.get(position));
                        startActivity(intent);
                    }
                });
    }

    private void getList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("ordertype", Constant.ORDERTYPE_CMRG);
                obj.put("matclass1",Constant.MAT_CLASS1_LJXW);
                obj.put("matclass2",Constant.MAT_CLASS2_CMRG);
                obj.put("startrow",start);
                obj.put("endrow",start + countSize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SP_DETAIL_URL))
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
                                    start = start + countSize + 1;
                                   FootZqShop footZqShop = new Gson().fromJson(response, FootZqShop.class);
                                    if (footZqShop.getData().size() != 0) {
                                        shopList.addAll(footZqShop.getData());
                                    }
                                    if(null ==mTyqAdapter){
                                        mTyqAdapter = new TyqAdapter(R.layout.fwq_item,shopList);
                                        mRecyclerView.setAdapter(mTyqAdapter);
                                    }else{
                                        mTyqAdapter.setNewData(shopList);
                                    }
                                    if (footZqShop.getData().size()<countSize){
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
