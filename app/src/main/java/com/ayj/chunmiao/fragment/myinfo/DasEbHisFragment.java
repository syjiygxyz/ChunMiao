package com.ayj.chunmiao.fragment.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.myinformation.dashang.DaswDetailActivity;
import com.ayj.chunmiao.adapter.myinformation.AwardEbHisAdapter;
import com.ayj.chunmiao.adapter.myinformation.AwardHisAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.AwardListBean;
import com.ayj.chunmiao.fragment.base.BaseFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
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
 * Created by zht-pc-04 on 2017/8/30.
 */

public class DasEbHisFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    private List<AwardListBean.DataBean> list = new ArrayList<>();
    private AwardEbHisAdapter adapter;
    private int pageNo = 1;
    private int pageSize = 20;

    @Override
    public void initDatas() {
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getAwardList();
            }
        });
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo = 1;
                list.clear();
                getAwardList();
            }
        });
    }

    @Override
    public void configViews() {
        getAwardList();
    }

    private void getAwardList() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            if (CommonUtils.isNetworkAvailable(getActivity())) {
                mProgressHub.show();
                JSONObject object = new JSONObject();
                try {
                    object.put("key", "");
                    object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                    object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                    object.put("awardtype","SHOPAWARDTYPE002");
                    object.put("pageno", pageNo);
                    object.put("pagesize", pageSize);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OkHttpUtils.post()
                        .url(WebUtils.getRequestUrl(WebUtils.AWARD_HIS))
                        .addParams("json", String.valueOf(object))
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
                                Check check = new Gson().fromJson(response, Check.class);
                                switch (check.getErr()) {
                                    case 0:
                                        AwardListBean awardListBean = new Gson().fromJson(response, AwardListBean.class);
                                        list.addAll(awardListBean.getData());
                                        if (null == adapter) {
                                            adapter = new AwardEbHisAdapter(R.layout.item_awardeb_his, list);
                                            recyclerView.setAdapter(adapter);
                                        } else {
                                            adapter.setNewData(list);
                                        }
                                        if (awardListBean.getData().size() < pageSize) {
                                            recyclerView.setHasLoadMore(false);
                                        } else {
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daseb_his;
    }

}
