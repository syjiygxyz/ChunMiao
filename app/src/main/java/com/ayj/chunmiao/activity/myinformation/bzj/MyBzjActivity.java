package com.ayj.chunmiao.activity.myinformation.bzj;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.myinformation.BzjAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.DepositBean;
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
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import okhttp3.Call;
/**我的保证金*/
public class MyBzjActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_total_no)
    TextView tvTotalNo;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    private List<DepositBean.DataBean> list = new ArrayList<>();
    private BzjAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_bzj;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("保证金");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void configViews() {
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                list.clear();
                getList();
            }
        });
        getList();
    }

    private void getList() {
        mProgressHub.show();
        JSONObject object = new JSONObject();
        try{
            object.put("key","");
            object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
            object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
        }catch (JSONException e){
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.BZJ_LIST))
                .addParams("json",String.valueOf(object))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mProgressHub.dismiss();
                        showToast(R.string.TheNetIsException);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mProgressHub.dismiss();
                        Check check = new Gson().fromJson(response,Check.class);
                        switch (check.getErr()){
                            case 0:
                                DepositBean depositBean = new Gson().fromJson(response,DepositBean.class);
                                list.addAll(depositBean.getData());
                                if (null == adapter){
                                    adapter = new BzjAdapter(R.layout.item_lx_sh,list);
                                    recyclerView.setAdapter(adapter);
                                }else {
                                    adapter.setNewData(list);
                                }
                                break;
                            default:
                                showToast(check.getMsg());
                                break;
                        }
                    }
                });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
