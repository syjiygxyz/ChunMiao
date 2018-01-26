package com.ayj.chunmiao.activity.xsjl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.mdmanage.CgAdapter;
import com.ayj.chunmiao.adapter.xsls.XslsAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MdCgBean;
import com.ayj.chunmiao.bean.xsls.XslsBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/*
* 调理服务日流水
* */
public class XslsDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_date)
    TextView mTvDate;//时间选择控件
    @BindView(R.id.recycler_view)
    RecyclerViewFinal mRecyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout mPcfRefresh;
    int pagesize = 12;
    int pageno = 1;
    XslsBean cgBean;
    List<XslsBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.iv_right)
    ImageView mIvRight;

    XslsAdapter xslsAdapter;
    @BindView(R.id.tv_right_head)
    TextView tv_right_head;

    String type;

    boolean []isHaveDay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_xsls_detail;
    }

    public static void startActivity(Context cxt,String ordertype,String title){
        Intent intent = new Intent(cxt,XslsDetailActivity.class);
        intent.putExtra("ordertype",ordertype);
        intent.putExtra("title",title);
        cxt.startActivity(intent);
    }

    @Override
    public void initDatas() {
        mTvTitle.setText(getIntent().getStringExtra("title"));
        type = getIntent().getStringExtra("ordertype");
        tv_right_head.setVisibility(View.VISIBLE);
        tv_right_head.setText("月流水");
        mTvDate.setText(CommonUtils.getTime(new Date(System.currentTimeMillis()),"yyyy-MM-dd"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getList();
    }

    @Override
    public void configViews() {
        mPcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageno = 1;
                list.clear();
                getList();
            }
        });
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                //发起加载更多请求
                getList();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.rl,R.id.tv_right_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl:
                if("日流水".equals(tv_right_head.getText().toString())){
                    /*这边月流水*/
                    showDayPickView("yyyy-MM");
                }else if(("月流水".equals(tv_right_head.getText().toString()))){
                    /*这边日流水*/
                    showDayPickView("yyyy-MM-dd");
                }
                break;
            case R.id.tv_right_head:
                /*切换日流水月流水*/
                if("日流水".equals(tv_right_head.getText().toString())){
                    /*这边变成月流水*/
                    tv_right_head.setText("月流水");
                    mTvDate.setText(CommonUtils.getTime(new Date(System.currentTimeMillis()),"yyyy-MM-dd"));
                    pageno = 1;
                    list.clear();
                    getList();
                }else if(("月流水".equals(tv_right_head.getText().toString()))){
                    /*这边变成日流水*/
                    tv_right_head.setText("日流水");
                    mTvDate.setText(CommonUtils.getTime(new Date(System.currentTimeMillis()),"yyyy-MM"));
                    pageno = 1;
                    list.clear();
                    getList();
                }
                break;
        }
    }

    private void getList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("ordertype",type);
                if("月流水".equals(tv_right_head.getText().toString())){
                    obj.put("etime",mTvDate.getText().toString());
                    obj.put("btime",mTvDate.getText().toString());
                }else if("日流水".equals(tv_right_head.getText().toString())){
                    obj.put("etime",CommonUtils.getLastDayOfMonth(Integer.parseInt(mTvDate.getText().toString().split("-")[0]),Integer.parseInt(mTvDate.getText().toString().split("-")[1])));
                    obj.put("btime",mTvDate.getText().toString()+"-01");
                }
                obj.put("pageno",pageno);
                obj.put("pagesize",pagesize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XSLU_URL))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            mRecyclerView.onLoadMoreComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            mPcfRefresh.onRefreshComplete();
                            mRecyclerView.onLoadMoreComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    cgBean = new Gson().fromJson(response,
                                            XslsBean.class);
                                    if(pageno ==1){
                                        if(cgBean.getData().size()==0){
                                            showToast("暂无数据");
                                        }
                                    }
                                    pageno ++;
                                    list.addAll(cgBean.getData());
                                    if (null == xslsAdapter) {
                                        xslsAdapter = new XslsAdapter(
                                                R.layout.xsls_detail_item, list);
                                        mRecyclerView.setAdapter(xslsAdapter);
                                    } else {
                                        xslsAdapter.setNewData(list);
                                    }
                                    if (cgBean.getData().size() < pagesize) {
                                        mRecyclerView.setHasLoadMore(false);
                                    } else {
                                        mRecyclerView.setHasLoadMore(true);
                                    }
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
    /*显示年月日,dateStyle*/
    private void showDayPickView(final String dateStyle){
        if("日流水".equals(tv_right_head.getText().toString())){
            /*年月*/
            isHaveDay = new boolean[]{true, true, false, false, false, false};
        }else if("月流水".equals(tv_right_head.getText().toString())){
            /*年月日*/
            isHaveDay = new boolean[]{true, true, true, false, false, false};
        }
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date,View v) {//选中事件回调
                mTvDate.setText(CommonUtils.getTime(date,dateStyle));
                pageno = 1;
                list.clear();
                getList();
            }
        }).setType(isHaveDay).setCancelText("取消").setDate(CommonUtils.StringToCalendar(mTvDate.getText().toString(),dateStyle)).setRangDate(startDate, endDate).build();;//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }
}
