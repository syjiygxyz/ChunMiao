package com.ayj.chunmiao.fragment.khq;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.khq.KhqHeadDetailAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.khq.ShopInfo;
import com.ayj.chunmiao.fragment.base.LazyFragment;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/20.
 * 实景
 */
public class SjFragment extends LazyFragment {


    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;
    @BindView(R.id.tv_detail)
    TextView tv_detail;
    UserPerson mUserPerson;

    KhqHeadDetailAdapter adapter;

    @Override
    public void fetchData() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getShopImg();
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getShopImg();
            }
        });
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sj;
    }

    private void getShopImg() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SP_DETAIL))
                    .addParams("json", "{\"key\":\"\",\"shopid\":\"" + mUserPerson.getData().get(0).getRegshopid() + "\"}")
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
                            Check check = new Gson().fromJson(response, Check.class);

                            switch (check.getErr()) {
                                case 0:
                                    ShopInfo shopInfo = new Gson().fromJson(response, ShopInfo.class);
                                    List<String> list = new ArrayList<String>();
                                    tv_detail.setText("店铺地址:"+shopInfo.getData().getAddr());
                                    if(!"".equals(shopInfo.getData().getImgurlshow())){
                                        list = Arrays.asList(shopInfo.getData().getImgurlshow().split(","));
                                    }
                                    if(null==adapter){
                                        adapter = new KhqHeadDetailAdapter(R.layout.khq_head_detail,list);
                                        recyclerView.setAdapter(adapter);
                                    }else{
                                        adapter.setNewData(list);
                                    }
                                    break;
                                default:
                                    Toast.makeText(getActivity(), check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }

    }

}
