package com.ayj.chunmiao.activity.myinformation.lxd;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.LxdBean;
import com.ayj.chunmiao.fragment.myinfo.LxdFragment;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class LxdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.rb_ftx)
    RadioButton rbFtx;
    @BindView(R.id.rb_cgx)
    RadioButton rbCgx;
    @BindView(R.id.rb_zx)
    RadioButton rbZx;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    @BindView(R.id.tv_right_head)
    TextView tvRightHead;

    List<Fragment> fragmentList = new ArrayList<>();
    private int mIndex;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showToast("支付成功");
                    } else {
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showToast("支付确认中");
                        } else {
                            // 判断resultStatus 为非"9000"则代表可能支付失败
                            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            showToast("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_lxd;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("联销单");
        tvRightHead.setText("分享记录");
        tvRightHead.setVisibility(View.VISIBLE);
        initFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content,fragmentList.get(0)).commit();
        setIndexSelected(0);
    }

    private void setIndexSelected(int index) {
        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.hide(fragmentList.get(mIndex));
        if (!fragmentList.get(index).isAdded()) {
            ft.add(R.id.content, fragmentList.get(index)).show(fragmentList.get(index));
        } else {
            ft.show(fragmentList.get(index));
        }
        ft.commit();
        mIndex = index;
    }

    private void initFragment() {
        fragmentList.add(LxdFragment.newInstance(Constant.LX_FT));
        fragmentList.add(LxdFragment.newInstance(Constant.LX_CG));
        fragmentList.add(LxdFragment.newInstance(Constant.LX_ZX));
    }

    @Override
    public void configViews() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_ftx:
                        setIndexSelected(0);
                        break;
                    case R.id.rb_cgx:
                        setIndexSelected(1);
                        break;
                    case R.id.rb_zx:
                        setIndexSelected(2);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_right_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_head:
                startActivity(new Intent(mContext,UnionShareHisActivity.class));
                break;
        }
    }

    @Override
    protected void postZfb(final String payInfo) {
        super.postZfb(payInfo);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构建PayTask对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
