package com.ayj.chunmiao.activity.myinformation.lxd;

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
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.myinformation.UnionShareHisAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.UnionShareHisBean;
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
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.HeaderAndFooterRecyclerViewAdapter;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

public class UnionShareHisActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    private List<UnionShareHisBean.DataBean> list = new ArrayList<>();
    private UnionShareHisAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_union_share_his;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("分享记录");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                list.clear();
                getShareList();
            }
        });

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, UnionShareDetailActivity.class);
                intent.putExtra("info",(Serializable) list.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void configViews() {
        getShareList();
    }

    private void getShareList() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHARE_UNION_HIS))
                    .addParams("json",String.valueOf(object))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    UnionShareHisBean unionShareHisBean = new Gson().fromJson(response,UnionShareHisBean.class);
                                    list.addAll(unionShareHisBean.getData());
                                    if (null == adapter){
                                        adapter = new UnionShareHisAdapter(R.layout.item_shop_order,list);
                                        recyclerView.setAdapter(adapter);
                                    }else{
                                        adapter.setNewData(list);
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else{
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
