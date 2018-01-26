package com.ayj.chunmiao.fragment.mdmanage;

import android.support.v7.widget.LinearLayoutManager;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.mdmanage.CgAdapter;
import com.ayj.chunmiao.adapter.mdmanage.ClAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MdCgBean;
import com.ayj.chunmiao.bean.MdClBean;
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
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2017/6/28.
 * 存量
 */
public class ClFragment extends LazyFragment {

    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;

    int pagesize = 12;

    int pageno = 1;

    List<MdClBean.DataBean> list = new ArrayList<>();

    MdClBean mClBean;

    ClAdapter mClAdapter;

    @Override
    public void initDatas() {

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
        return R.layout.fragment_cl;
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
                    .url(WebUtils.getRequestUrl(WebUtils.CL_URL))
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
                                  pageno ++;
                                    mClBean = new Gson().fromJson(response,
                                            MdClBean.class);
                                    list.addAll(mClBean.getData());
                                    if (null == mClAdapter) {
                                        mClAdapter = new ClAdapter(
                                                R.layout.cl_item, list);
                                        mRecyclerView.setAdapter(mClAdapter);
                                    } else {
                                        mClAdapter.setNewData(list);
                                    }
                                    if (mClBean.getData().size() < pagesize) {
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

