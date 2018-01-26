package com.ayj.chunmiao.activity.khq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.cz.CzActivity;
import com.ayj.chunmiao.activity.cmbz.czjr.CzAddActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.InsuranceMainActivity;
import com.ayj.chunmiao.activity.cmbz.yy.YdActivity;
import com.ayj.chunmiao.activity.common.CommonSpListActivity;
import com.ayj.chunmiao.activity.khq.yyb.FootYybActivity;
import com.ayj.chunmiao.activity.khq.zsjm.PartnerActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.khq.ShopInfo;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import okhttp3.Call;

/*
* 客户圈
* */
public class KhqActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_d_bh)
    TextView tvDBh;
    @BindView(R.id.tv_d_name)
    TextView tvDName;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.rlv_a)
    RecyclerView rlvA;
    @BindView(R.id.rlv_b)
    RecyclerView rlvB;
    @BindView(R.id.rlv_package)
    RecyclerView rlvPackage;
    @BindView(R.id.rlv_water)
    RecyclerView rlvWater;
    @BindView(R.id.rlv_hc)
    RecyclerView rlv_hc;
    @BindView(R.id.rlv_sb)
    RecyclerView rlv_sb;
    @BindView(R.id.rlv_yyb)
    RecyclerView rlv_yyb;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;
    @BindView(R.id.iv)
    ImageView iv;

    UserPerson mUserPerson;

    CommonGridAdapter mCommonGridAdapter;

    List<UtilityItem> gridLists = new ArrayList<>();

    ShopInfo shopInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_khq;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("健康的家");
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        gridLists = UtilityItem.getKhqMain();
        rlv.setLayoutManager(new GridLayoutManager(mContext, 4));
        rlvA.setLayoutManager(new GridLayoutManager(mContext, 3));
        rlvB.setLayoutManager(new GridLayoutManager(mContext, 3));
        rlvWater.setLayoutManager(new GridLayoutManager(mContext, 2));
        rlvPackage.setLayoutManager(new GridLayoutManager(mContext, 4));
        rlv_yyb.setLayoutManager(new GridLayoutManager(mContext, 4));
        rlv_hc.setLayoutManager(new LinearLayoutManager(mContext));
        rlv_sb.setLayoutManager(new LinearLayoutManager(mContext));
        mCommonGridAdapter = new CommonGridAdapter(R.layout.common_grid_item, gridLists);
        rlv.setAdapter(mCommonGridAdapter);
        rlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                switch (gridLists.get(position).getType()) {
                    case 1:
                        /*保健预约*/
                        startActivity(new Intent(mContext,
                                YdActivity.class));
                        break;
                    case 2:
                       /*保险服务*/
                        startActivity(new Intent(mContext,
                                InsuranceMainActivity.class));
                        break;
                    case 3:
                      /*邻家小店*/
                        CommonSpListActivity.startActivity(mContext, "have_hot", Constant.ORDERTYPE_LJXW, Constant.MAT_CLASS1_LJXW, "ljxd");
                        break;
                    case 4:
                     /*折扣商铺*/
                        CommonSpListActivity.startActivity(mContext, "have_hot", Constant.ORDERTYPE_ZKSP, Constant.MAT_CLASS1_LJXW, "zkp");
                        break;
                    case 5:
                      /*春苗兑换*/
                        CommonSpListActivity.startActivity(mContext, "no_hot", Constant.ORDERTYPE_DH, Constant.MAT_CLASS1_LJXW, "dhzx");
                        break;
                    case 6:
                       /*充值加入*/
                        startActivity(new Intent(mContext, CzAddActivity.class));
                        break;
                    case 7:
                      /*春苗热柜*/
                        CommonSpListActivity.startActivity(mContext, "no_hot", Constant.ORDERTYPE_CMRG, Constant.MAT_CLASS1_CMRG, "cmrg");
                        break;
                    case 8:
                        /*春苗分享*/
                        showToast("敬请期待");
                        break;
                    case 9:
                        /*招商加盟*/
                        startActivity(new Intent(mContext, PartnerActivity.class));
                        break;
                    case 10:
                        /*营养吧*/
                        Intent mIntent = new Intent(mContext, FootYybActivity.class);
                        intent.putExtra("shopid", shopInfo.getData().getSid());
                        intent.putExtra("iscm", "1");
                        startActivity(mIntent);
                        break;
                }
            }
        });
    }

    @Override
    public void configViews() {
        getInfo();
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getInfo();

            }
        });
    }

    /*获取数据*/
    private void getInfo() {
        getshopinfo();
        CommonUtils.getCard(mContext, Constant.CARD_A_KL, rlvA, "康乐会员A卡", R.mipmap.hyk_zs);
        CommonUtils.getCard(mContext, Constant.CARD_B_KL, rlvB, "康乐会员B卡", R.mipmap.hyk_zs);
        CommonUtils.getCard(mContext, Constant.CARD_WATER_KL, rlvWater, "百姓富人水", R.mipmap.ho2);
        CommonUtils.getTc(mContext, rlvPackage, "健康包购买", R.mipmap.yhqqqq);
        CommonUtils.getShoppingList(mContext, rlv_yyb,"营养吧", R.mipmap.hcfwww, "MEMBERORDERTYPE013", "MEMBERORDERTYPE013001", "");
        CommonUtils.getShoppingList(mContext, rlv_hc,"买耗材送服务", R.mipmap.hcfwww, "MEMBERORDERTYPE019", "MEMBERORDERTYPE019001", "");
        CommonUtils.getShoppingList(mContext, rlv_sb,"设备仪器购买", R.mipmap.sbyqqqq, "MEMBERORDERTYPE019", "MEMBERORDERTYPE019002", "");
    }


    private void getshopinfo() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.JK_SHOP))
                    .addParams("json", "{\"key\":\"\",\"shopid\":\"" + mUserPerson.getData().get(0).getRegshopid() + "\"}")
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
                            pcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    shopInfo = new Gson().fromJson(response, ShopInfo.class);
                                    int width = CommonUtils.getScreenWidth(mContext);
                                    int bannerHeigth = (width * 9) / 16;
                                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
                                    if (params == null) {
                                        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                bannerHeigth);
                                        iv.setLayoutParams(params);
                                    } else {
                                        params.height = bannerHeigth;
                                        iv.setLayoutParams(params);
                                    }
                                    Picasso.with(mContext).load(
                                            shopInfo.getData().getIntroductionimgurlshow()).placeholder(R.mipmap.banner_loading).error(
                                            R.mipmap.banner_error).into(
                                            iv);
                                    tvDBh.setText("No." + shopInfo.getData().getSid());
                                    tvDName.setText(shopInfo.getData().getShopname());
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

    @OnClick({R.id.iv_back, R.id.iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv:
                startActivity(new Intent(mContext,KhqHeadActivity.class));
                break;
        }
    }
}
