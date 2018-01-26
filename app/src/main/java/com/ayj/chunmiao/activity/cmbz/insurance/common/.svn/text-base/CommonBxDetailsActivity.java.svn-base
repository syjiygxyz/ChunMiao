package com.ayj.chunmiao.activity.cmbz.insurance.common;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.cmbz.bx.CommonBxDetailsAdapter;
import com.ayj.chunmiao.adapter.cmbz.bx.CommonTimeAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.bx.BxXqBean;
import com.ayj.chunmiao.bean.bx.BzfwBean;
import com.ayj.chunmiao.bean.bx.CommonBxListBean;
import com.ayj.chunmiao.bean.bx.TimeIsCheckBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.DividerItemDecoration;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 通用的保险详情界面
* */
public class CommonBxDetailsActivity extends BaseActivity {

    CommonBxListBean.DataBean item;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_bxtype)
    TextView tvBxtype;
    @BindView(R.id.iv_biaozhi)
    ImageView ivBiaozhi;
    @BindView(R.id.tv_person)
    TextView tvPerson;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.rlv)
    RecyclerView rlv;

    @BindView(R.id.rlv1)
    RecyclerView rlv1;

    CommonBxDetailsAdapter commonBxDetailsAdapter;

    public LayoutInflater mInflater;

    BzfwBean bxFaBean;

    BxXqBean bxxqbean;

    CommonTimeAdapter timeAdapter;

    List<TimeIsCheckBean> list = new ArrayList<>();

    int firstXz;

    int SecondXz;
    int countTime;
    @BindView(R.id.tv_lp)
    TextView tvLp;
    @BindView(R.id.tv_tb)
    TextView tvTb;
    @BindView(R.id.tv_post)
    TextView tvPost;

    String dw;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_bx_details;
    }

    @Override
    public void initDatas() {
        rlv1.setLayoutManager(new GridLayoutManager(mContext, 5));
        rlv.setLayoutManager(new LinearLayoutManager(mContext));
        rlv.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        item = (CommonBxListBean.DataBean) getIntent().getSerializableExtra("info");
        Picasso.with(mContext).load(item.getImgurlshow()).placeholder(R.mipmap.zhanweitu).error(
                R.mipmap.jiazaishibia).into(ivPic);
        tvBxtype.setText(item.getMatidshow());
        tvPerson.setText(item.getFitpeople());
        tvPrice.setText(item.getMinpremium());
        if (item.getBzqxmax() == null) {
            tvTime.setText("");
        } else {
            if (item.getBzqxstart().equals(item.getBzqxmax())) {
                tvTime.setText(item.getBzqxstart());
            } else {
                tvTime.setText(item.getBzqxstart() + item.getBzqxunitshow() + "-" + item.getBzqxmax() + item.getBzqxunitshow());
            }
        }
        mInflater = LayoutInflater.from(mContext);
        getInfo();
        getNextInfo();
        rlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < bxFaBean.getData().size(); i++) {
                    bxFaBean.getData().get(i).setTrue(false);
                }
                bxFaBean.getData().get(position).setTrue(true);
                commonBxDetailsAdapter.setNewData(bxFaBean.getData());
                commonBxDetailsAdapter.notifyDataSetChanged();
                firstXz = position;
            }
        });
        rlv1.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
                timeAdapter.setNewData(list);
                timeAdapter.notifyDataSetChanged();
                SecondXz = position;
            }
        });
    }

    @Override
    public void configViews() {

    }

    private void getInfo() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.BX_POST))
                    .addParams("json", "{\"key\":\"\",\"icsnid\":\"" + item.getSnid() + "\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.show();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    bxFaBean = new Gson().fromJson(response, BzfwBean.class);
                                    for (int i = 0; i < bxFaBean.getData().size(); i++) {
                                        if (0 == i) {
                                            bxFaBean.getData().get(i).setTrue(true);
                                        } else {
                                            bxFaBean.getData().get(i).setTrue(false);
                                        }
                                    }
                                    if (null == commonBxDetailsAdapter) {
                                        commonBxDetailsAdapter = new CommonBxDetailsAdapter(R.layout.item_ljbj1, bxFaBean.getData());
                                        rlv.setAdapter(commonBxDetailsAdapter);
                                    } else {
                                        commonBxDetailsAdapter.setNewData(bxFaBean.getData());
                                    }
//                                    for (BzfwBean.DataBean item : bxFaBean.getData()) {//获取首页整个下方栏目新闻数据
//                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, CommonUtils.dip2px(mContext, 34));
//                                    View view = mInflater.inflate(R.layout.item_ljbj1, null);
//                                    TextView tv = (TextView) view.findViewById(R.id.tv_fangan);
//                                    tv.setText(item.getEcomment());
//                                    view.setLayoutParams(params);
//                                    if (item.getContentList().size() > 0) {//如果数据集合不为0
//                                        //添加头部布局
//                                        ll.addView(view);
//                                        for (BzfwBean.DataBean.ContentListBean model : item.getContentList()) {//获取每一个新闻实体类
//                                            View newsView = mInflater.inflate(R.layout.item_bxcontentlist, null);
//                                            TextView tv1 = (TextView) newsView.findViewById(R.id.tv_protectcontent);
//                                            TextView tv2 = (TextView) newsView.findViewById(R.id.tv_protectlimit);
//                                            tv1.setText(model.getProtectcontent());
//                                            tv2.setText(model.getProtectlimit()+"万年");
//                                            if (newsView != null) {
//                                                ll.addView(newsView);//将每一个新闻视图加入布局
//                                                view.setOnClickListener(new View.OnClickListener() {
//                                                    @Override
//                                                    public void onClick(View v) {
//
//                                                    }
//                                                });
//                                                }
//                                            }
//                                        }
//                                    }

                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void getNextInfo() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.BD_DETAIL))
                    .addParams("json", "{\"key\":\"\",\"matid\":\"" + item.getMatid() + "\"}")
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
                                    bxxqbean = new Gson().fromJson(response, BxXqBean.class);
                                    dw = bxxqbean.getData().get(0).getBzqxunitshow();
                                    if (bxxqbean.getData().get(0).getBzqxstart().equals(bxxqbean.getData().get(0).getBzqxmax())) {
                                        list.add(new TimeIsCheckBean(bxxqbean.getData().get(0).getBzqxmax(), true));
                                    } else {
                                        list.add(new TimeIsCheckBean(bxxqbean.getData().get(0).getBzqxstart(), true));
                                        countTime = Integer.parseInt(bxxqbean.getData().get(0).getBzqxstart());
                                        while (countTime <= Integer.parseInt(bxxqbean.getData().get(0).getBzqxmax())) {
                                            countTime = countTime + Integer.parseInt(bxxqbean.getData().get(0).getBzqxstep());
                                            list.add(new TimeIsCheckBean(countTime + "", false));
                                        }
                                    }
                                    if (null == timeAdapter) {
                                        timeAdapter = new CommonTimeAdapter(R.layout.bx_sj_item, list,bxxqbean.getData().get(0).getBzqxunitshow());
                                        rlv1.setAdapter(timeAdapter);
                                    } else {
                                        timeAdapter.setNewData(list);
                                    }
                                    tvLp.setText(Html.fromHtml(bxxqbean.getData().get(0).getProductdescription()));
                                    tvTb.setText(Html.fromHtml(bxxqbean.getData().get(0).getInsuranceinformation()));
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }



    @OnClick({R.id.iv_back, R.id.tv_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_post:
                CommonBxPostOne.jumpActivity(item,mContext,bxFaBean.getData().get(firstXz).getPriceList().get(SecondXz).getPrice(),
                        bxFaBean.getData().get(firstXz),list.get(SecondXz).getTimee(),dw,item.getMatidshow(),bxxqbean.getData().get(0).getSnid());
                break;
        }
    }
}
