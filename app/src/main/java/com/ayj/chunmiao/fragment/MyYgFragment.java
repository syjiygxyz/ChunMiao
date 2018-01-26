package com.ayj.chunmiao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.ApplyEmployeeActivity;
import com.ayj.chunmiao.activity.menbermanage.MyDyDetailsActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.menbermanage.MyMenberManageAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MyStaff;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.fragment.base.BaseFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2017/6/26.
 * 员工管理界面
 */
public class MyYgFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.clv)
    RecyclerView mClv;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;
    @BindView(R.id.tv_right_head)
    TextView tvRightHead;

    MyStaff mStaff;

    MyMenberManageAdapter mMyMenberManageAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yg_manger;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initDatas() {
        mIvBack.setVisibility(View.GONE);
        mIvRight.setVisibility(View.GONE);
        mTvTitle.setText("我的员工");
        tvRightHead.setText("账号申请");
        tvRightHead.setVisibility(View.VISIBLE);
        mClv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getDate();
            }
        });
        mClv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), MyDyDetailsActivity.class);
                intent.putExtra("mobile", mStaff.getData().get(position).getMobile());
                intent.putExtra("img", mStaff.getData().get(position).getHeadimgurlshow());
                intent.putExtra("name", mStaff.getData().get(position).getName());
                intent.putExtra("staffId", mStaff.getData().get(position).getUserid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void configViews() {
        getDate();
    }

    private void getDate() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            mProgressHub.show();
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("shopid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getShopid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CM_MENBER_URL))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    mStaff = new Gson().fromJson(response, MyStaff.class);
                                    if (null == mMyMenberManageAdapter) {
                                        mMyMenberManageAdapter = new MyMenberManageAdapter(R.layout.item_my_yy, mStaff.getData());
                                        mClv.setAdapter(mMyMenberManageAdapter);
                                    } else {
                                        mMyMenberManageAdapter.setNewData(mStaff.getData());
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

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("MyDyDetailsActivityClick")) {
            getDate();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    @OnClick(R.id.tv_right_head)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), ApplyEmployeeActivity.class));
    }
}
