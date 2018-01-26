package com.ayj.chunmiao.activity.menbermanage;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.menbermanage.MyMenberDetailadapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MyYgDetailsBean;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.utils.CircleTransform;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
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

/*
* 店员管理点击店员的详情界面
* */
public class MyDyDetailsActivity extends BaseActivity {
    MyYgDetailsBean mMyYgDetailsBean = new MyYgDetailsBean();

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.imgUserIcon)
    ImageView mImgUserIcon;
    @BindView(R.id.tvUserName)
    TextView mTvUserName;
    @BindView(R.id.ivUserVip)
    TextView mIvUserVip;//冻结账号
    @BindView(R.id.tvUserPhoneNumber)
    TextView mTvUserPhoneNumber;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;

    int pageSize = 20;

    int pageNo = 1;

    String staffId;

    MyMenberDetailadapter adapter;

    List<MyYgDetailsBean.DataBean> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_dy_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initDatas() {
        String imgUrl = getIntent().getStringExtra("img");
        if ("".equals(imgUrl)) {
            mImgUserIcon.setImageResource(R.mipmap.menber_loading);
        } else {
            Picasso.with(mContext).load(imgUrl).placeholder(R.mipmap.menber_loading).error(
                    R.mipmap.menber_error).transform(new CircleTransform()).into(
                    mImgUserIcon);
        }
        staffId = getIntent().getStringExtra("staffId");
        mTvUserPhoneNumber.setText("账号:" + getIntent().getStringExtra("mobile"));
        mTvUserName.setText(getIntent().getStringExtra("name"));
        mTvTitle.setText("我的员工");
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
                ++pageNo;
                getList();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ivUserVip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ivUserVip://冻结账号
                djMenber();
                break;
        }
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
                obj.put("chunmiao_userid", staffId);
                obj.put("pageno", pageNo);
                obj.put("pagesize", pageSize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XX_YY))
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
                                    mMyYgDetailsBean = new Gson().fromJson(response,
                                            MyYgDetailsBean.class);
                                    list.addAll(mMyYgDetailsBean.getData());
                                    if (null == adapter) {
                                        adapter = new MyMenberDetailadapter(
                                                R.layout.my_menber_detail_item, list);
                                        mRecyclerView.setAdapter(adapter);
                                    } else {
                                        adapter.setNewData(list);
                                    }
                                    if (mMyYgDetailsBean.getData().size() < pageSize) {
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

    /*冻结员工账号*/
    private void djMenber() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("freezeaccount", staffId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.DJ_YG_URL))
                    .addParams("json",String.valueOf(obj))
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
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    EventBus.getDefault().post(
                                            new FirstEvent("MyDyDetailsActivityClick"));
                                    finish();
                                    showToast(check.getMsg());
                                    break;
                                case -1:
                                    showToast(check.getMsg());
                                    break;
                                default:
                                    break;
                            }

                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
