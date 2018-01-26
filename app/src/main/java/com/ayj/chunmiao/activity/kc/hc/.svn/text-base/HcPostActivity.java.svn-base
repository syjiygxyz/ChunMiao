package com.ayj.chunmiao.activity.kc.hc;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.kc.HcPostAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MdXsBean;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/29.
 */
public class HcPostActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_bt)
    TextView tvBt;

    int pagesize = 12;

    int pageno = 1;
    MdXsBean xsBean;

    HcPostAdapter mXsAdapter;

    List<MdXsBean.DataBean> list = new ArrayList<>();

    private Map<Integer, Integer> map = new HashMap<>();

    private Map<Integer, String> chooseMap = new HashMap<>();

    private List<UtilityItem> itemList = new ArrayList<>();

    OperatePopupWindow opw;

    JSONArray wlArray;

    RecyclerView.LayoutManager lm;

    TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hc_post;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("上报");
        itemList.add(new UtilityItem("试用"));
        itemList.add(new UtilityItem("失败耗损"));
        itemList.add(new UtilityItem("保管耗损"));
        itemList.add(new UtilityItem("按标准量多用"));
        itemList.add(new UtilityItem("按标准量少用"));
    }

    @Override
    public void configViews() {
        lm = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(lm);
        getList();
//        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                pageno = 1;
//                list.clear();
//                getList();
//            }
//        });
    }

    @OnClick({R.id.iv_back, R.id.tv_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_bt:
                postBd();
                break;
        }
    }

    private void getList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("pageno",pageno);
                obj.put("pagesize",100);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XS_URL))
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
                                    xsBean = new Gson().fromJson(response,
                                            MdXsBean.class);
                                    list.addAll(xsBean.getData());
                                    if (null == mXsAdapter) {
                                        mXsAdapter = new HcPostAdapter(
                                                R.layout.hc_post_item, list);
                                        mRecyclerView.setAdapter(mXsAdapter);
                                    } else {
                                        mXsAdapter.setNewData(list);
                                    }
                                    mXsAdapter.setOnitemClickListener(new HcPostAdapter.ChooseOnItemClickListener() {
                                        @Override
                                        public void onItemClickListener(View v, int position) {
                                            tv = (TextView) lm.findViewByPosition(position).findViewById(R.id.tv_choose);
                                            showOpw(position);
                                        }
                                    });
                                    chooseMap = mXsAdapter.getChooseMap();
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

    private void postBd() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            map = mXsAdapter.getNumMap();
            chooseMap = mXsAdapter.getChooseMap();
            wlArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                JSONObject obj = new JSONObject();
                if (map.get(i) !=0){
                    if (!"请选择".equals(chooseMap.get(i).toString())){
                        try {
                            obj.put("matid", list.get(i).getMatid());
                            obj.put("num", map.get(i));
                            obj.put("diffreason",chooseMap.get(i).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        wlArray.put(obj);
                    }else {
                        showToast("请选择原因");
                        return;
                    }
                }
            }
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("ordertype", Constant.XHJ_HC);
                obj.put("detail", wlArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.HC_POST))
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
                                    showToast("提交成功");
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
    private void showOpw(final int po){
        opw = new OperatePopupWindow(mContext, "选择原因", itemList, new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0 :
                        tv.setText("试用");
                        break;
                    case 1 :
                        tv.setText("失败损耗");
                        break;
                    case 2 :
                        tv.setText("保管损耗");
                        break;
                    case 3 :
                        tv.setText("按标准量多用");
                        break;
                    case 4 :
                        tv.setText("按标准量少用");
                        break;
                }
                chooseMap.put(po,tv.getText().toString());
                mXsAdapter.setChooseMap(chooseMap);
                opw.dismiss();
            }
        });
        opw.showAtLocation(tvBt, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        opw.update();
    }
}

