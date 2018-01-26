package com.ayj.chunmiao.activity.cmbz.insurance.clbx;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.cmbz.bx.InsuranceCompanyAdapater;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.InsuranceBrandBean;
import com.ayj.chunmiao.bean.cmbz.InsuranceCompanyBean;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/29.
 */
public class InsuranceCompanyShowActivity  extends BaseActivity {

    List<InsuranceBrandBean.DataBean> companyList = new ArrayList<>();
    String brokerId;  //经纪公司
    String insuranceBrand; //保险品牌
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv_company_show)
    RecyclerView rlvCompanyShow;
    private InsuranceCompanyAdapater adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_company_show;
    }

    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        tvTitle.setText("选择保险品牌");
        rlvCompanyShow.setLayoutManager(new GridLayoutManager(mContext,3));
        rlvCompanyShow.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext,InsuranceBrokerShowActivity.class);
                intent.putExtra("brokerid",brokerId);
                intent.putExtra("state", (Serializable) companyList.get(position));
                intent.putExtra("insurancebrand",companyList.get(position).getInsurancebrand());
                intent.putExtra("city_id",getIntent().getStringExtra("city_id"));
                intent.putExtra("istransfercar",getIntent().getStringExtra("istransfercar"));
                intent.putExtra("transferdate",getIntent().getStringExtra("transferdate"));
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("sfz",getIntent().getStringExtra("sfz"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void configViews() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("insfrom", "INSFROM003");
                jsonObject.put("levelfrom", "1");
                jsonObject.put("levelto", "1");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_FGS_URL))
                    .addParams("json", String.valueOf(jsonObject))
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
                                    InsuranceCompanyBean company = new Gson().fromJson(response, InsuranceCompanyBean.class);
                                    brokerId = company.getData().get(0).getAid();
                                    getInsuranceBrand(brokerId);
                                    break;
                                default:
                                    showToast(check.getMsg());
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void getInsuranceBrand(String brokerId) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("brokerid", brokerId);
                jsonObject.put("areaid", getIntent().getStringExtra("city_id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_SIGNEDBRAND_URL))
                    .addParams("json", String.valueOf(jsonObject))
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
                                    InsuranceBrandBean insuranceBrandBean = new Gson().fromJson(response,InsuranceBrandBean.class);
                                    companyList.addAll(insuranceBrandBean.getData());
                                    adapter = new InsuranceCompanyAdapater(companyList);
                                    rlvCompanyShow.setAdapter(adapter);
                                    break;
                                default:showToast(check.getMsg());
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if(event.getMsg().equals("carsetbeforefinish")){
            finish();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

