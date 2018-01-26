package com.ayj.chunmiao.activity.kc.jf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.kc.JfAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.kc.KcJfBean;
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
import okhttp3.Call;

/*
* 积分
* */
public class JfActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;


    JfAdapter adapter;
    List<KcJfBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.tv_content_yl)
    TextView tvContentYl;
    @BindView(R.id.btn_mx_yl)
    Button btnMxYl;
    @BindView(R.id.tv_content_xs)
    TextView tvContentXs;
    @BindView(R.id.btn_mx_xs)
    Button btnMxXs;
    @BindView(R.id.tv_content_xx)
    TextView tvContentXx;
    @BindView(R.id.btn_mx_xx)
    Button btnMxXx;
    @BindView(R.id.tv_title_xxzl)
    TextView tvTitleXxzl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jf;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("积分");

    }

    @Override
    public void configViews() {
        getInfo();
    }

    private void getInfo() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.JF_XI))
                    .addParams("json", String.valueOf(obj))
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
                                    KcJfBean kcJfBean = new Gson().fromJson(response, KcJfBean.class);
                                    tvTitleXxzl.setText("库存余量:"+kcJfBean.getData().getInventoryBalance());
                                    tvContentYl.setText("平台产生量:"+kcJfBean.getData().getPlatformThroughput());
                                    tvContentXs.setText("线上兑换量:"+kcJfBean.getData().getStoreTurnoverOnLine());
                                    tvContentXx.setText("线下兑换量:"+kcJfBean.getData().getStoreTurnoverLine());
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


    @OnClick({R.id.iv_back, R.id.btn_mx_yl, R.id.btn_mx_xs, R.id.btn_mx_xx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_mx_yl:
                JfMxActivity.startActivity(mContext,"jf");
                break;
            case R.id.btn_mx_xs:
                JfMxActivity.startActivity(mContext, Constant.CHANGE_TYPE_XS);
                break;
            case R.id.btn_mx_xx:
                JfMxActivity.startActivity(mContext, Constant.CHANGE_TYPE_XX);
                break;
        }
    }
}
