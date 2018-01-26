package com.ayj.chunmiao.fragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.view.ProgressHUD;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 */
public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    Unbinder unbinder;

    public ProgressHUD mProgressHub = null;

    protected ACache aCache;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        aCache = CommonUtils.getAcache();
        mProgressHub = CommonUtils.createProgressDialog(getActivity(), "正在加载中..", true);
        initDatas();
        configViews();
    }


    public abstract void initDatas();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void configViews();


    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mProgressHub != null && mProgressHub.isShowing()) {
            mProgressHub.dismiss();
        }
        unbinder.unbind();
    }

    protected void showToast(String str) {
        Toast.makeText(getActivity(), str,Toast.LENGTH_SHORT).show();
    }
    protected void showToast(int str) {
        Toast.makeText(getActivity(), str,Toast.LENGTH_SHORT).show();
    }
}
