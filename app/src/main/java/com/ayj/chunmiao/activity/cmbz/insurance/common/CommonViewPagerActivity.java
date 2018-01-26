package com.ayj.chunmiao.activity.cmbz.insurance.common;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.tab.CommonPagerAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.bx.Classification;
import com.ayj.chunmiao.fragment.bx.CommonBxListFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.magicindicator.MagicIndicator;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 通用的viewpager+fragment
* */
public class CommonViewPagerActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tp_ly)
    MagicIndicator mTpLy;
    @BindView(R.id.ly_pages)
    ViewPager mLyPages;



    private List<Fragment> listFragment = new ArrayList<>();

    List<String> titles = new ArrayList<>();

    Classification classification_xd;

    public static void startActivity(Context cxt,String title,String insurancebrand){
        Intent intent = new Intent(cxt,CommonViewPagerActivity.class);
        intent.putExtra("insurancebrand", insurancebrand);
        intent.putExtra("title", title);
        cxt.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_view_pager;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText(getIntent().getStringExtra("title"));
    }

    @Override
    public void configViews() {
        getInfo();
    }

    private void setFragments() {
        for (int i = 0; i <classification_xd.getData().size() ; i++) {
            listFragment.add(CommonBxListFragment.newInstance(classification_xd.getData().get(i).getDictid(),"","",getIntent().getStringExtra("insurancebrand")));
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    private void getInfo() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("dicttypeid", "INSURANCECLSONE");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CL_FX))
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
                                    classification_xd = new Gson().fromJson(response, Classification.class);
                                    for (int i = 0; i < classification_xd.getData().size(); i++) {
                                               titles.add(classification_xd.getData().get(i).getParamname());
                                    }
                                    setFragments();
                                    mLyPages.setOffscreenPageLimit(titles.size());
                                    CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), titles,
                                            listFragment);
                                    mLyPages.setAdapter(adapter);
                                    CommonUtils.initMagicIndicator(mTpLy, titles, mLyPages, mContext);
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
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

