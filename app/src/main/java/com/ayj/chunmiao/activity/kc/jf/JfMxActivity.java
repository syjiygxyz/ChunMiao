package com.ayj.chunmiao.activity.kc.jf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.kc.JfPlatAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.kc.JfPlatBean;
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
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

public class JfMxActivity extends BaseActivity {

    List<JfPlatBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_date)
    TextView tvTitleDate;
    @BindView(R.id.tv_title_snid)
    TextView tvTitleSnid;
    @BindView(R.id.tv_title_num)
    TextView tvTitleNum;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    private int pageNo = 1;
    private int pageSize = 30;
    private JfPlatAdapter adapter;
    private String webUrl,type;


    public static void startActivity(Context context,String type){
        Intent intent = new Intent(context,JfMxActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_jf_mx;
    }

    @Override
    public void initDatas() {
        if (null != getIntent().getStringExtra("type")){
            type = getIntent().getStringExtra("type");
            if ("jf".equals(type)){
                webUrl = WebUtils.getRequestUrl(WebUtils.EMONEY_HIS_FORM);
                tvTitle.setText("积分平台结算明细");
                tvTitleNum.setText("爱e币量");
            }else if ("jb".equals(type)){
                webUrl = WebUtils.getRequestUrl(WebUtils.GOlD_HIS_FORM);
                tvTitle.setText("金币平台结算明细");
                tvTitleNum.setText("金币量");
            }else if (Constant.CHANGE_TYPE_XS.equals(type)){
                webUrl = WebUtils.getRequestUrl(WebUtils.EMONEY_HIS_DH);
                tvTitle.setText("积分线上兑换明细");
                tvTitleNum.setText("爱e币量");
            }else if (Constant.CHANGE_TYPE_XX.equals(type)){
                webUrl = WebUtils.getRequestUrl(WebUtils.EMONEY_HIS_DH);
                tvTitle.setText("积分线下兑换明细");
                tvTitleNum.setText("爱e币量");
            }else if ("jbdh".equals(type)){
                webUrl = WebUtils.getRequestUrl(WebUtils.GOLD_HIS_DH);
                tvTitle.setText("金币兑换明细");
                tvTitleNum.setText("金币量");
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getHisList();
            }
        });
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo = 1;
                list.clear();
                getHisList();
            }
        });
    }

    @Override
    public void configViews() {
        getHisList();

    }

    private void getHisList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("changetype",type);
                obj.put("pageno", pageNo);
                obj.put("pagesize", pageSize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GOlD_HIS_FORM))
                    .addParams("json", String.valueOf(obj))
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
                                    JfPlatBean jfPlatBean = new Gson().fromJson(response, JfPlatBean.class);
                                    list.addAll(jfPlatBean.getData());
                                    if (null == adapter) {
                                        adapter = new JfPlatAdapter(R.layout.item_jf_plat, list);
                                        recyclerView.setAdapter(adapter);
                                    }else {
                                        adapter.setNewData(list);
                                    }
                                    if (jfPlatBean.getData().size() < pageSize){
                                        recyclerView.setHasLoadMore(false);
                                    }else {
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
