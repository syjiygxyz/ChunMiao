package com.ayj.chunmiao.activity.kc.jb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.kc.jf.JfMxActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.kc.KcJfBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class JbActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_pt)
    TextView tvPt;
    @BindView(R.id.btn_pt)
    Button btnPt;
    @BindView(R.id.tv_duihuan)
    TextView tvDuihuan;
    @BindView(R.id.btn_duihuan)
    Button btnDuihuan;


    @Override
    public int getLayoutId() {
        return R.layout.activity_jb;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("金币");
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
                    .url(WebUtils.getRequestUrl(WebUtils.JB_XI))
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
                                    tvPt.setText("平台产生量:"+kcJfBean.getData().getPlatformThroughput());
                                    tvDuihuan.setText("已兑换量:"+kcJfBean.getData().getStoreTurnover());
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

    @OnClick({R.id.iv_back, R.id.btn_pt, R.id.btn_duihuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_pt:
                JfMxActivity.startActivity(mContext,"jb");
                break;
            case R.id.btn_duihuan:
                JfMxActivity.startActivity(mContext,"jbdh");
                break;
        }
    }
}
