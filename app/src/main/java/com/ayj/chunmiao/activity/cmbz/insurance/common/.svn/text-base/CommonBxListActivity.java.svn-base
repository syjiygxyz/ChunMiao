package com.ayj.chunmiao.activity.cmbz.insurance.common;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.cmbz.bx.CommonBxListAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.bx.CommonBxListBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.DividerItemDecoration;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/*
* 通用的保险列表
* */
public class CommonBxListActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recycler_view;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcf_refresh;

    int start = 0;

    int countSize = 10;

    CommonBxListBean footZqShop;

    CommonBxListAdapter commonBxListAdapter;

    private List<CommonBxListBean.DataBean> shopList = new ArrayList<>();

    public static void jumpActivity(Context cxt, String title,String classid, String classid2, String matname,
                                    String insurancebrand){
        Intent mIntent = new Intent(cxt, CommonBxListActivity.class);
        mIntent.putExtra("title", title);
        mIntent.putExtra("classid", classid);
        mIntent.putExtra("classid2", classid2);
        mIntent.putExtra("matname", matname);
        mIntent.putExtra("insurancebrand", insurancebrand);
        cxt.startActivity(mIntent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_bx_list;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        tvTitle.setText(getIntent().getStringExtra("title"));
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_view.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        getSp();
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
        recycler_view.setOnItemClickListener(
                new HeaderAndFooterRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                        Intent intent = new Intent();
                        intent.putExtra("info", (Serializable) shopList.get(position));
                        intent.setClass(mContext, CommonBxDetailsActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    private void getSp() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("classid",getIntent().getStringExtra("classid"));
                obj.put("classid2",getIntent().getStringExtra("classid2"));
                obj.put("matname",getIntent().getStringExtra("matname"));
                obj.put("insurancebrand",getIntent().getStringExtra("insurancebrand"));
                obj.put("startrow",start);
                obj.put("endrow",start + countSize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.BX_LIST))
                    .addParams("json",String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            pcf_refresh.onRefreshComplete();
                            recycler_view.onLoadMoreComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcf_refresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    start = start + countSize + 1;
                                    footZqShop = new Gson().fromJson(response, CommonBxListBean.class);
                                    if (footZqShop.getData().size() != 0) {
                                        shopList.addAll(footZqShop.getData());
                                    }
                                    if(null==commonBxListAdapter){
                                        commonBxListAdapter = new CommonBxListAdapter(R.layout.item_bxcsxz,shopList);
                                        recycler_view.setAdapter(commonBxListAdapter);
                                    }else{
                                        commonBxListAdapter.setNewData(shopList);
                                    }
                                    if (footZqShop.getData().size()<countSize){
                                        recycler_view.setHasLoadMore(false);
                                    }else {
                                        recycler_view.setHasLoadMore(true);
                                    }
                                    break;
                                default:
                                    Toast.makeText(mContext, "获取信息失败",
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
