package com.ayj.chunmiao.activity.kc.cxj;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.kc.CxjAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MdClBean;
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
 * Created by Administrator on 2017/7/29.
 */
public class CxjActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    private int pageNo = 1;
    private int pageSize = 20;
    private List<MdClBean.DataBean> list = new ArrayList<>();
    private CxjAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_cxj;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("促销卷");
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getClList();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo = 1;
                list.clear();
                getClList();
            }
        });
    }

    @Override
    public void configViews() {
        getClList();

    }

    private void getClList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "")
                        .put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid())
                        .put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord())
                        .put("ordertype", Constant.CG_CX)
                        .put("pageno",pageNo)
                        .put("pagesize",pageSize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CL_URL))
                    .addParams("json", String.valueOf(jsonObject))
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
                                    MdClBean clBean = new Gson().fromJson(response,MdClBean.class);
                                    list.addAll(clBean.getData());
                                    if (null == adapter){
                                        adapter = new CxjAdapter(R.layout.item_cxj,list);
                                        recyclerView.setAdapter(adapter);
                                    }else {
                                        adapter.setNewData(list);
                                    }
                                    if (clBean.getData().size()<pageSize){
                                        recyclerView.setHasLoadMore(false);
                                    }else {
                                        recyclerView.setHasLoadMore(true);
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
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
