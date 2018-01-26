package com.ayj.chunmiao.activity.myinformation.mymoney;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.myinformation.MoneyChangeAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.ChangeMoneyBean;
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
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
* 门店中心爱e币和金币流水
* */
public class MoneyLsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;

    ChangeMoneyBean bean;

    List<ChangeMoneyBean.DataBean>list = new ArrayList<>();

    MoneyChangeAdapter adapter;

    int pageSize = 12;

    int pageNo = 1;

    String type;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;

    public static void startActivity(Context cxt, String type) {
        Intent intent = new Intent(cxt, MoneyLsActivity.class);
        intent.putExtra("type", type);
        cxt.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_money_ls;
    }

    @Override
    public void initDatas() {
        type = getIntent().getStringExtra("type");
        if (type.equals(Constant.BAG_TYPE_IEB)) {
            tvTitle.setText("爱e币流水");
            tvTitle2.setText("爱e币量");
        }  else if (type.equals(Constant.BAG_TYPE_JINB)) {
            tvTitle.setText("金币流水");
            tvTitle2.setText("金币量");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void configViews() {
        getList();
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo = 1;
                list.clear();
                getList();
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //发起加载更多请求
                ++pageNo;
                getList();
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
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
                obj.put("bagtype",type);
                obj.put("pageno", pageNo);
                obj.put("pagesize", pageSize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.MONEY_CHANGE))
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
                                    //pageno++;
                                    bean = new Gson().fromJson(response,
                                            ChangeMoneyBean.class);
                                    list.addAll(bean.getData());
                                    if (null == adapter){
                                        adapter = new MoneyChangeAdapter(R.layout.item_money_change,list);
                                        mRecyclerView.setAdapter(adapter);
                                    }else{
                                        adapter.setNewData(list);
                                    }
                                    if (bean.getData().size()<pageSize){
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
}
