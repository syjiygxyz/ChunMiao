package com.ayj.chunmiao.activity.myinformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.myinformation.anquan.ZhangHaoAnQuanActivity;
import com.ayj.chunmiao.activity.myinformation.bzj.MyBzjActivity;
import com.ayj.chunmiao.activity.myinformation.dashang.DashActivity;
import com.ayj.chunmiao.activity.myinformation.jinku.XJKActivity;
import com.ayj.chunmiao.activity.myinformation.lxd.LxdActivity;
import com.ayj.chunmiao.activity.myinformation.lxshh.LxshActivity;
import com.ayj.chunmiao.activity.myinformation.mymoney.MoneyLsActivity;
import com.ayj.chunmiao.activity.myinformation.setting.SettingActivity;
import com.ayj.chunmiao.activity.myinformation.shoporder.ShopOrderActivity;
import com.ayj.chunmiao.activity.myinformation.wujuan.WjActivity;
import com.ayj.chunmiao.activity.myinformation.xiaoxi.XiaoXiActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.bean.myinformation.ShopMoneyBagBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.RoundImageView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 个人中心
* */
public class MyCenterActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.img_head)
    RoundImageView img_head;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_xiaojinku)
    TextView tvXiaojinku;
    @BindView(R.id.tv_aiyibi)
    TextView tvAiyibi;
    @BindView(R.id.tv_jinbi)
    TextView tvJinbi;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.iv_right_left)
    ImageView ivRighLeft;
    @BindView(R.id.tv_msg_count)
    TextView tvMsgCount;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    private CommonGridAdapter commonGridAdapter;
    List<UtilityItem> list = new ArrayList<>();
    private String total;


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_center;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("门店中心");
        ivRighLeft.setVisibility(View.VISIBLE);
        ivRighLeft.setImageResource(R.mipmap.shezhi);
        rlRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.xiaoxi);
        ivRight.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        if ("".equals(AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getHeadimgurlshow())) {
            img_head.setImageResource(R.mipmap.menber_loading);
        } else {
            Picasso.with(mContext).load(AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getHeadimgurlshow()).placeholder(R.mipmap.menber_loading).error(
                    R.mipmap.menber_error).into(
                    img_head);
        }
        getShopMoneyBag();
    }


    private void getShopMoneyBag() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_MONEY_BAG))
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
                            switch (check.getErr()) {
                                case 0:
                                    ShopMoneyBagBean shopMoneyBagBean = new Gson().fromJson(response, ShopMoneyBagBean.class);
                                    for (int i = 0; i < shopMoneyBagBean.getData().size(); i++) {
                                        if (shopMoneyBagBean.getData().get(i).getBagtype().equals(Constant.BAG_TYPE_IEB)) {
                                            tvAiyibi.setText(shopMoneyBagBean.getData().get(i).getNum());
                                        } else if (shopMoneyBagBean.getData().get(i).getBagtype().equals(Constant.BAG_TYPE_JINB)) {
                                            tvJinbi.setText(shopMoneyBagBean.getData().get(i).getNum());
                                        } else if (shopMoneyBagBean.getData().get(i).getBagtype().equals(Constant.BAG_TYPE_JINK)) {
                                            tvXiaojinku.setText(shopMoneyBagBean.getData().get(i).getNum());
                                            total = shopMoneyBagBean.getData().get(i).getNum();
                                        }
                                    }
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

    @Override
    public void configViews() {
        EventBus.getDefault().register(this);
        list = UtilityItem.getShopCenterList();
        getUnPayOrderNum();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (list.get(position).getType()) {
                    case 1://门店订单
                        startActivity(new Intent(mContext, ShopOrderActivity.class));
                        break;
                    case 2://店长打赏
                        startActivity(new Intent(mContext, DashActivity.class));
                        break;
                    case 3://联销审核
                        startActivity(new Intent(mContext, LxshActivity.class));
                        break;
                    case 4://保证金
                        startActivity(new Intent(mContext, MyBzjActivity.class));
                        break;
                    case 5://联销单
                        startActivity(new Intent(mContext, LxdActivity.class));
                        break;
                    case 6://物卷
                        startActivity(new Intent(mContext, WjActivity.class));
                        break;
                    case 7://账号安全
                        startActivity(new Intent(mContext, ZhangHaoAnQuanActivity.class));
                        break;
                }
            }
        });
    }

    private void getUnPayOrderNum() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("key", "");
                jsonObject.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.UN_PAY))
                    .addParams("json",String.valueOf(jsonObject))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            commonGridAdapter = new CommonGridAdapter(R.layout.cmbz_item, list);
                            recyclerView.setAdapter(commonGridAdapter);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    if (check.getTotal() > 0){
                                        list.get(0).setRedPoint("show");
                                    }
                                    commonGridAdapter = new CommonGridAdapter(R.layout.cmbz_item, list);
                                    recyclerView.setAdapter(commonGridAdapter);
                                    break;
                                default:
                                    commonGridAdapter = new CommonGridAdapter(R.layout.cmbz_item, list);
                                    recyclerView.setAdapter(commonGridAdapter);
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("MyCenterClick")) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    protected void onResume() {
        super.onResume();
        getShopMoneyBag();
    }


    @OnClick({R.id.tv_xiaojinku, R.id.tv_aiyibi, R.id.tv_jinbi, R.id.iv_right, R.id.iv_back, R.id.img_head, R.id.iv_right_left})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_xiaojinku:
                Intent intent = new Intent(mContext, XJKActivity.class);
                intent.putExtra("total", total);
                startActivity(intent);
                break;
            case R.id.tv_aiyibi:
                MoneyLsActivity.startActivity(mContext, Constant.BAG_TYPE_IEB);
                break;
            case R.id.tv_jinbi:
                MoneyLsActivity.startActivity(mContext, Constant.BAG_TYPE_JINB);
                break;
            case R.id.iv_right:
                startActivity(new Intent(mContext, XiaoXiActivity.class));
                break;
            case R.id.iv_right_left:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.img_head:
                startActivity(new Intent(mContext, MyInformationDetailsActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
