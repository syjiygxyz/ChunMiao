package com.ayj.chunmiao.activity.cg.xhj;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.tab.CommonPagerAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.ScTitleBean;
import com.ayj.chunmiao.fragment.cg.XhjFragment;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class XhjCgActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tp_ly)
    MagicIndicator tpLy;
    @BindView(R.id.ly_pages)
    ViewPager lyPages;


    private List<Fragment> listFragment = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    private ScTitleBean scTitleBean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_xhj_cg;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("采购");
        getTitleList();
    }

    private void initPage() {
        lyPages.setOffscreenPageLimit(titles.size()+1);
        setFragments();
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), titles,
                listFragment);
        lyPages.setAdapter(adapter);
        CommonUtils.initJfMagicIndicator(tpLy, titles, lyPages, mContext);
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

    @Override
    public void configViews() {

    }

    private void setFragments() {
        listFragment.add(XhjFragment.newInstance(Constant.CG_CX2,null));
        for (int i = 0 ; i< scTitleBean.getData().size() ; i++){
            listFragment.add(XhjFragment.newInstance(Constant.XHJ,scTitleBean.getData().get(i).getDictid()));
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
