package com.ayj.chunmiao.activity.myinformation.shoporder;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.tab.CommonPagerAdapter;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.fragment.myinfo.ShopOrderFragment;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.ayj.chunmiao.view.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/*门店订单*/
public class ShopOrderActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.order_indicator)
    MagicIndicator orderIndicator;
    @BindView(R.id.order_pager)
    ViewPager orderPager;

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    List<UtilityItem> itemList = new ArrayList<>();
    CommonPagerAdapter pagerAdapter;
    OperatePopupWindow popupWindow;
    ShopOrderFragment fragment;

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showToast("支付成功");
                    } else {
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showToast("支付确认中");
                        } else {
                            // 判断resultStatus 为非"9000"则代表可能支付失败
                            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            showToast("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_order;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("我的采购订单");
        ivRight.setImageResource(R.drawable.menu_sandian);
        ivRight.setVisibility(View.VISIBLE);
        itemList = UtilityItem.getShopStockOrder();
        titleList.add("全部");titleList.add("待付款");titleList.add("待发货");titleList.add("待收货");titleList.add("已完成");
        getFragments();
        orderPager.setOffscreenPageLimit(4);
        orderPager.setAdapter(pagerAdapter = new CommonPagerAdapter(getSupportFragmentManager(),titleList,fragmentList));
        CommonUtils.initJfMagicIndicator(orderIndicator,titleList,orderPager,mContext);
    }

    private void getFragments() {
        fragmentList.add(ShopOrderFragment.newInstance(""));
        fragmentList.add(ShopOrderFragment.newInstance(Constant.STOCKIN_STATUS_DFK));
        fragmentList.add(ShopOrderFragment.newInstance(Constant.STOCKIN_STATUS_DFH));
        fragmentList.add(ShopOrderFragment.newInstance(Constant.STOCKIN_STATUS_DSH));
        fragmentList.add(ShopOrderFragment.newInstance(Constant.STOCKIN_STATUS_QR));
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showPopWindow();
                break;
        }
    }

    private void showPopWindow() {
        popupWindow = new OperatePopupWindow(mContext, "选择订单类型", itemList, new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        fragment = (ShopOrderFragment) pagerAdapter.getFragment();
                        fragment.getTypeOrder(Constant.XHJ);
                        popupWindow.dismiss();
                        break;
                    case 1:
                        fragment = (ShopOrderFragment) pagerAdapter.getFragment();
                        fragment.getTypeOrder(Constant.CG_CX);
                        popupWindow.dismiss();
                        break;
                    case 2:
                        fragment = (ShopOrderFragment) pagerAdapter.getFragment();
                        fragment.getTypeOrder(Constant.XHJ_HC);
                        popupWindow.dismiss();
                        break;
                    case 3:
                        fragment = (ShopOrderFragment) pagerAdapter.getFragment();
                        fragment.getTypeOrder(Constant.LX_CG);
                        popupWindow.dismiss();
                        break;
                    case 4:
                        fragment = (ShopOrderFragment) pagerAdapter.getFragment();
                        fragment.getTypeOrder(Constant.LX_FT);
                        popupWindow.dismiss();
                        break;
                    /*case 3:
                        fragment = (ShopOrderFragment) pagerAdapter.getFragment();
                        fragment.getTypeOrder(Constant.CG_JF);
                        popupWindow.dismiss();
                        break;*/
                }
            }
        });
        popupWindow.showAsDropDown(ivRight);
        popupWindow.update();
    }

    @Override
    protected void postZfb(final String payInfo) {
        super.postZfb(payInfo);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构建PayTask对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
