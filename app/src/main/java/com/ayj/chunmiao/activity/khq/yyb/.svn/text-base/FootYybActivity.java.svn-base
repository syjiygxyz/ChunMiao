package com.ayj.chunmiao.activity.khq.yyb;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.khq.FootYybAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.Classification;
import com.ayj.chunmiao.bean.ScTitleBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FootYybActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private ListView lv_foot_yyb;
    private List<ScTitleBean.DataBean> mClassification_yyb;
    private FootYybAdapter mFootYybAdapter;

    private String shopid;
    private String iscm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_foot_yyb;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("营养吧");
        findViews();
        initData();
    }

    @Override
    public void configViews() {

    }

    private void findViews() {
        lv_foot_yyb = (ListView) findViewById(R.id.lv_foot_yyb);
    }

    private void initData() {
        shopid = getIntent().getStringExtra("shopid");
        iscm = getIntent().getStringExtra("iscm");
        mClassification_yyb = new ArrayList<>();
        mFootYybAdapter = new FootYybAdapter(FootYybActivity.this);
        getDict();
    }

    private void getDict() {
        if (CommonUtils.isNetworkAvailable(FootYybActivity.this)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CL_FX))
                    .addParams("json", "{\"key\":\"\",\"dicttypeid\":\"MEMBERORDERTYPE013\"}")
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
                                    ScTitleBean scTitleBean = new Gson().fromJson(response, ScTitleBean.class);
                                    for (int i = 0; i < scTitleBean.getData().size(); i++) {
                                        mClassification_yyb.add(scTitleBean.getData().get(i));
                                    }
                                    mFootYybAdapter.setList(mClassification_yyb, shopid, iscm);
                                    lv_foot_yyb.setAdapter(mFootYybAdapter);
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

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
