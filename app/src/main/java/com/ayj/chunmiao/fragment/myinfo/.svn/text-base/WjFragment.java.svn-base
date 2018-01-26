package com.ayj.chunmiao.fragment.myinfo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.myinformation.WjAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.TicketBean;
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
 * Created by zht-pc-04 on 2017/8/30.
 */

public class WjFragment extends LazyFragment {

    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;
    private String type;
    private int pageNo = 1;
    private int pageSize = 10;
    private WjAdapter adapter;
    private List<TicketBean.DataBean> list = new ArrayList<>();

    public static WjFragment newInstance(String type) {
        WjFragment wjFragment = new WjFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        wjFragment.setArguments(bundle);
        return wjFragment;
    }

    @Override
    public void fetchData() {
        if (null != getArguments()){
            type = getArguments().getString("type");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getTicketList();
            }
        });
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo = 1;
                list.clear();
                getTicketList();
            }
        });
        getTicketList();
    }

    private void getTicketList() {
        if (CommonUtils.isNetworkAvailable(getActivity())){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("status",type);
                object.put("pageno",pageNo);
                object.put("pagesize",pageSize);
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_TICKET))
                    .addParams("json",String.valueOf(object))
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
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    TicketBean ticketBean = new Gson().fromJson(response,TicketBean.class);
                                    list.addAll(ticketBean.getData());
                                    if (null == adapter){
                                        adapter = new WjAdapter(R.layout.item_ticket,list);
                                        recyclerView.setAdapter(adapter);
                                    }else {
                                        adapter.setNewData(list);
                                    }
                                    if (ticketBean.getData().size()<pageSize){
                                        recyclerView.setHasLoadMore(false);
                                    }else{
                                        recyclerView.setHasLoadMore(true);
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else {
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
        return R.layout.fragment_wj;
    }
}
