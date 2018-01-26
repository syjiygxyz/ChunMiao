package com.ayj.chunmiao.activity.ykfx;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.mdmanage.CgAdapter;
import com.ayj.chunmiao.adapter.ykfx.XseAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MdCgBean;
import com.ayj.chunmiao.bean.ykfx.XseDetailBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.DividerItemDecoration;
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
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/*
* 盈亏分析详情界面
* */
public class YkfxDetailActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_static)
    TextView tvStatic;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;

    int pagesize = 12;

    int pageno = 1;

    List<XseDetailBean.DataBean> xseList = new ArrayList<>();

    XseAdapter xseAdapter;

    public static void jumpActivity(Context context, String title, String secondTitle, String url, String btime, String etime) {
        Intent intent = new Intent(context, YkfxDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("secondTitle", secondTitle);
        intent.putExtra("url", url);
        intent.putExtra("btime", btime);
        intent.putExtra("etime", etime);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ykfx_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvStatic.setText(getIntent().getStringExtra("secondTitle"));
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    @Override
    public void configViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getList();
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageno = 1;
                xseList.clear();
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
    }

    private void getList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("btime", getIntent().getStringExtra("btime"));
                obj.put("etime", getIntent().getStringExtra("etime"));
                obj.put("pageno", pageno);
                obj.put("pagesize", pagesize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(getIntent().getStringExtra("url")))
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
                                    XseDetailBean xseDetailBean = new Gson().fromJson(response, XseDetailBean.class);
                                    xseList.addAll(xseDetailBean.getData());
                                    if (null == xseAdapter) {
                                        xseAdapter = new XseAdapter(R.layout.jh_item, xseList);
                                        mRecyclerView.setAdapter(xseAdapter);
                                    } else {
                                        xseAdapter.setNewData(xseList);
                                    }
                                    if (xseDetailBean.getData().size() < pagesize) {
                                        mRecyclerView.setHasLoadMore(false);
                                    } else {
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

}
