package com.ayj.chunmiao.activity.cmbz.hyk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.MemberCard;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.Constant;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class HykZfActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.zf_content)
    TextView zfContent;
    @BindView(R.id.zf_money)
    TextView zfMoney;
    @BindView(R.id.zf_wx)
    TextView zfWx;
    @BindView(R.id.zf_zfb)
    TextView zfZfb;

    UserPerson mUserPerson;

    private String[] orderattachmentdetail;

    String jsonobj;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
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
                        finish();
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

    public static void jumpActivity(Context context, String matidShow, String price, String matid, String id, String num, String img1, String img2, String img3) {
        Intent intent = new Intent(context, HykZfActivity.class);
        intent.putExtra("matid_show", matidShow);
        intent.putExtra("id", id);
        intent.putExtra("num", num);
        intent.putExtra("price", price);
        intent.putExtra("matid", matid);
        intent.putExtra("img1", img1);
        intent.putExtra("img2", img2);
        intent.putExtra("img3", img3);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hyk_zf;
    }

    @Override
    public void initDatas() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        tvTitle.setText("卡购买");
    }

    @Override
    public void configViews() {
        zfContent.setText(getIntent().getStringExtra("matid_show"));
        zfMoney.setText("¥" + getIntent().getStringExtra("price") + "元");
        orderattachmentdetail = new String[3];
        orderattachmentdetail[0] = "{\\\"title\\\":\\\"" + "身份证正面"
                + "\\\",\\\"imgurl\\\":\\\"" + getIntent().getStringExtra("img1") + "\\\"}";
        orderattachmentdetail[1] = "{\\\"title\\\":\\\"" + "身份证反面"
                + "\\\",\\\"imgurl\\\":\\\"" + getIntent().getStringExtra("img2") + "\\\"}";
        orderattachmentdetail[2] = "{\\\"title\\\":\\\"" + "低保证明"
                + "\\\",\\\"imgurl\\\":\\\"" + getIntent().getStringExtra("img3") + "\\\"}";
        jsonobj = "{\"key\":\"\",\"msnid\":\""
                + mUserPerson.getData().get(0).getSnid()
                + "\",\"pwd\":\"" + mUserPerson.getData().get(
                0).getPassWord() + "\",\"ordertype\":\"" + getIntent().getStringExtra("id") + "\",\"paytype\":\"" + "PAYTYPE999" + "\",\"memberaddrid\":\"" + "" + "\",\"rechargesmallmoney\":\"" + getIntent().getStringExtra("price")
                + "\",\"smallmoneyuse\":\"" + "" + "\",\"membermoneybagusemoney\":\"" + ""
                + "\",\"orderattachmentdetail\":\"" + Arrays.toString(orderattachmentdetail)
                + "\",\"servicebooksnid\":\"" + "" + "\",\"memberticketdetail\":\"" + "" + "\",\"membercarddetail\":\"" + ""
                + "\",\"orderdetail\":\"[{\\\"pid\\\":\\\"" + getIntent().getStringExtra("matid") + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(0).getRegshopid() + "\\\",\\\"num\\\":\\\"" + getIntent().getStringExtra("num") + "\\\"}]\"}";
    }


    @OnClick({R.id.iv_back, R.id.zf_wx, R.id.zf_zfb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.zf_wx:
                showToast("暂不开放");
                break;
            case R.id.zf_zfb:
                postZf(mContext, jsonobj, "2", Constant.HY, getIntent().getStringExtra("price"), getIntent().getStringExtra("matid"));
                break;
        }
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
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
