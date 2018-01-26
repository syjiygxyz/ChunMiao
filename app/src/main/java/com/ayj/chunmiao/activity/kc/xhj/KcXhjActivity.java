package com.ayj.chunmiao.activity.kc.xhj;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.tab.CommonPagerAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.ScTitleBean;
import com.ayj.chunmiao.fragment.kc.KcXhjFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.magicindicator.MagicIndicator;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 库存现货价
* */
public class KcXhjActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tp_ly)
    MagicIndicator mTpLy;
    @BindView(R.id.ly_pages)
    ViewPager mLyPages;

    private ScTitleBean scTitleBean;

    private List<Fragment> listFragment = new ArrayList<>();

    List<String> titles = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_qq_pd;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("现货架");
        getTitleList();
    }

    @Override
    public void configViews() {

    }

    private void setFragments() {
        listFragment.add(new KcXhjFragment().newInstance(Constant.CG_CX2,null));
        for (int i=0 ; i<scTitleBean.getData().size() ; i++){
            listFragment.add(new KcXhjFragment().newInstance(Constant.XHJ,scTitleBean.getData().get(i).getDictid()));
        }
    }

    private void getTitleList() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject json = new JSONObject();
            try {
                json.put("key","");
                json.put("dicttypeid",Constant.XHJ);
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CL_FX))
                    .addParams("json",String.valueOf(json))
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
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    scTitleBean = new Gson().fromJson(response,ScTitleBean.class);
                                    titles.clear();
                                    titles.add("促销商品");
                                    for (int i=0 ; i<scTitleBean.getData().size() ; i++){
                                        titles.add(scTitleBean.getData().get(i).getParamname());
                                    }
                                    initPage();
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }
    }

    private void initPage() {
        setFragments();
        mLyPages.setOffscreenPageLimit(3);
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), titles,
                listFragment);
        mLyPages.setAdapter(adapter);
        CommonUtils.initJfMagicIndicator(mTpLy, titles, mLyPages, mContext);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}