package com.ayj.chunmiao.fragment.mdmanage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.mdmanage.CgAdapter;
import com.ayj.chunmiao.adapter.mdmanage.XsAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MdCgBean;
import com.ayj.chunmiao.bean.MdXsBean;
import com.ayj.chunmiao.fragment.base.LazyFragment;
import com.ayj.chunmiao.utils.CommonUtils;
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
import butterknife.Unbinder;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2017/6/28.
 * 销售
 */
public class XsFragment extends LazyFragment {

    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;

    int pagesize = 12;

    int pageno = 1;
    MdXsBean xsBean;

    XsAdapter mXsAdapter;

    List<MdXsBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.tv_cgno)
    TextView mTvCgno;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_static)
    TextView mTvStatic;

    @Override
    public void initDatas() {
        mTvCgno.setText("物料名称");
        mTvTime.setText("数量");
        mTvStatic.setText("使用时间");
    }

    @Override
    public void configViews() {
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageno = 1;
                list.clear();
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cg;
    }

    @Override
    public void fetchData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getList();
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
                obj.put("pageno",pageno);
                obj.put("pagesize",pagesize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XS_URL))
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
                            mRecyclerView.onLoadMoreComplete();
                            mPcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    pageno ++;
                                    xsBean = new Gson().fromJson(response,
                                            MdXsBean.class);
                                    list.addAll(xsBean.getData());
                                    if (null == mXsAdapter) {
                                        mXsAdapter = new XsAdapter(
                                                R.layout.jh_item, list);
                                        mRecyclerView.setAdapter(mXsAdapter);
                                    } else {
                                        mXsAdapter.setNewData(list);
                                    }
                                    if (xsBean.getData().size() < pagesize) {
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

