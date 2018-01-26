package com.ayj.chunmiao.activity.myinformation.shoporder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.myinformation.StockDetailAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.ShopStockBean;
import com.ayj.chunmiao.bean.myinformation.StockDetailBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class StockOrderDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_aplly_time)
    TextView tvApllyTime;
    @BindView(R.id.tv_shop_show)
    TextView tvShopShow;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.rv_stock_detail)
    RecyclerView rvStockDetail;
    private ShopStockBean.DataBean order;
    private List<StockDetailBean.DataBean> list = new ArrayList<>();
    private StockDetailAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock_order_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("订单详情");
        order = (ShopStockBean.DataBean) getIntent().getSerializableExtra("order");
        tvApllyTime.setText("下单时间:" + order.getCreatedate());
        tvOrderId.setText("订单编号:" + order.getSnid());
        tvPayType.setText("支付方式:" + order.getPaytypeshow());
        tvShopShow.setText("下单店铺:" + order.getShopidshow());
        tvState.setText("状态:" + order.getStatusshow());
        tvTotalMoney.setText("￥" + order.getShtotalmoney());
        rvStockDetail.setLayoutManager(new LinearLayoutManager(mContext));
    }

    private void getOrderDetail() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("snid", order.getSnid());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_STOCK_ORDER))
                    .addParams("json", String.valueOf(object))
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
                            switch (check.getErr()){
                                case 0:
                                    StockDetailBean stockDetailBean = new Gson().fromJson(response,StockDetailBean.class);
                                    list.addAll(stockDetailBean.getData());
                                    if (null == adapter){
                                        adapter = new StockDetailAdapter(R.layout.item_stock_detail,list);
                                        rvStockDetail.setAdapter(adapter);
                                    }else{
                                        adapter.setNewData(list);
                                    }
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @Override
    public void configViews() {
        getOrderDetail();
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
