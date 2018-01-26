package com.ayj.chunmiao.activity.cmbz.insurance.clbx;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.cmbz.bx.InsuranceBrokerAdapater;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.InsuranceBrokerBean;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/29.
 * 获取报价
 */
public class InsuranceBrokerShowActivity  extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rlv_broker)
    RecyclerView rlvBroker;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String brokerId, insuranceBrand;
    private List<InsuranceBrokerBean.DataBean> list = new ArrayList<>();
    private List<String> state = new ArrayList<>();
    private Map<Integer, Boolean> map = new HashMap<>();
    private InsuranceBrokerAdapater adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_broker_show;
    }

    @Override
    public void initDatas() {
        EventBus.getDefault().register(this);
        tvTitle.setText("选择对接保险公司");
        brokerId = getIntent().getStringExtra("brokerid");
        insuranceBrand = getIntent().getStringExtra("insurancebrand");
        rlvBroker.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @Override
    public void configViews() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("brokerid", brokerId);
                jsonObject.put("insurancebrand", insuranceBrand);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_SIGNEDFGS_URL))
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
                                    InsuranceBrokerBean insuranceBrokerBean = new Gson().fromJson(response, InsuranceBrokerBean.class);
                                    list.addAll(insuranceBrokerBean.getData());
                                    adapter = new InsuranceBrokerAdapater(list, mContext);
                                    rlvBroker.setAdapter(adapter);
                                    adapter.setRecyclerViewOnItemClickListener(new InsuranceBrokerAdapater.RecyclerViewOnItemClickListener() {
                                        @Override
                                        public void onItemClickListener(View view, int position) {
                                            adapter.setSelectItem(position);
                                        }
                                    });
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


    @OnClick({R.id.btn_next, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                getStateDate();
                if (state.size()>0){
                    Intent intent = new Intent(mContext,InsuranceCarSetOrderActivity.class);
                    intent.putExtra("state",getIntent().getSerializableExtra("state"));
                    intent.putExtra("brokerid", brokerId);
                    intent.putExtra("city_id",getIntent().getStringExtra("city_id"));
                    intent.putExtra("istransfercar",getIntent().getStringExtra("istransfercar"));
                    intent.putExtra("transferdate",getIntent().getStringExtra("transferdate"));
                    intent.putExtra("name",getIntent().getStringExtra("name"));
                    intent.putExtra("sfz",getIntent().getStringExtra("sfz"));
                    startActivity(intent);
                }else{
                    showToast("请至少选择一家公司");
                }
                break;
            case R.id.iv_back:
                finish();

        }
    }


    /*根据CheckBox状态获取state数据*/
    private void getStateDate() {
        state.clear();
        map = adapter.getMap();
        for(int i=0 ; i<list.size() ;i++){
            if(map.get(i)){
                state.add(list.get(i).getInsurancebrand());
            }
        }
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
