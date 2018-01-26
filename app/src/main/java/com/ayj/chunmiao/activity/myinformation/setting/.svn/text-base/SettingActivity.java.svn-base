package com.ayj.chunmiao.activity.myinformation.setting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.LoginActivity;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.BbUpDateBean;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.DataCleanManager;
import com.ayj.chunmiao.utils.UpDateUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_version)
    RelativeLayout rlVersion;
    @BindView(R.id.rl_clean)
    RelativeLayout rlClean;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.tv_ache)
    TextView tvAche;
    @BindView(R.id.tv_quit)
    TextView tvQuit;


    private UpDateUtils upDateUtils = new UpDateUtils(this,false);

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("设置");
        try {
            tvAche.setText(DataCleanManager.getTotalCacheSize(mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.rl_version, R.id.rl_clean, R.id.rl_about,R.id.tv_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_version:
                upDateUtils.update(false);
                break;
            case R.id.rl_clean:
                SweetAlertDialog cleanDialog = CommonUtils.getConfirmDialog(mContext, "提示",
                        "是否清理缓存?",
                        new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                DataCleanManager.clearAllCache(getApplicationContext());
                                showToast("清理成功");
                                tvAche.setText("0KB");
                                sweetAlertDialog.dismiss();
                            }
                        });
                //显示dialog
                cleanDialog.show();
                break;
            case R.id.rl_about:
                startActivity(new Intent(mContext,AboutUsActivity.class));
                break;
            case R.id.tv_quit:
                SweetAlertDialog confirmDialog = CommonUtils.getConfirmDialog(mContext, "提示",
                        "是否退出登录?",
                        new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                EventBus.getDefault().post(
                                        new FirstEvent("MyCenterClick"));
                                startActivity(new Intent(mContext, LoginActivity.class));
                                finish();
                                AyjSwApplication.getsInstance().setUserInfo(null);
                                aCache.remove(ACache.USER_INFO_KEY);
                                sweetAlertDialog.dismiss();
                            }
                        });
                //显示dialog
                confirmDialog.show();
                break;
        }
    }
}
