package com.ayj.chunmiao.activity.cmbz.yy;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.cmbz.yy.ServiceOrderAdapter;
import com.ayj.chunmiao.adapter.cmbz.yy.YyPostHeadAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MoneyLeft;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.MyListView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 约单支付
* */
public class YyPostActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_fwd)
    TextView tv_fwd;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.rlv)
    RecyclerView rlv;
    @BindView(R.id.cb_one)
    CheckBox cbOne;
    @BindView(R.id.rl_dd)
    RelativeLayout rlDd;
    @BindView(R.id.cb_two)
    CheckBox cbTwo;
    @BindView(R.id.rl_dd_two)
    RelativeLayout rlDdTwo;
    String[] details;
    private String matid;
    private String matidshow;
    private String per_money;
    private int[] num;
    private int[] nums;
    private int discount;
    private Integer[] nums_left;
    private Integer[] nums_pay;
    private Double[] fees;

    @BindView(R.id.tv_service_sfmoney)
    TextView tv_service_sfmoney;

    @BindView(R.id.tv_service_smallmoney_left)
    TextView tv_service_smallmoney_left;

    @BindView(R.id.cb_service_order)
    CheckBox cb_service_order;
    private boolean[] checked;
    UserPerson mUserPerson;
    private String smallmoneyuse = "";
    private Double allmoney_pay = 0.00;
    private Double smallmoney_pay = 0.00;
    private Double othermoney_pay = 0.00;
    private Double smallmoney_left = 0.00;

    private static final int SDK_PAY_FLAG = 2;

    private List<UtilityItem> listHead = new ArrayList<>();

    private List<MoneyLeft.DataBean.TicketListBean> mTicketListBeans;

    private String memberticketdetail = "";

    @BindView(R.id.mlv_service_order)
    MyListView mlv_service_order;

    String[] memberticketdetails;

    private ServiceOrderAdapter mServiceOrderAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yy_post;
    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                memberticketdetail = "";
                smallmoney_pay = 0.00;
                othermoney_pay = 0.00;
                checked = (boolean[]) msg.obj;
                for(int i = 0;i<checked.length;i++){
                    int count = 0;
                    if(checked[i]){
                        nums_pay[i] = nums[i] >= nums_left[i] ? nums_left[i]:nums[i];

                        smallmoney_pay  += nums_pay[i]*(Double.parseDouble(per_money.split(",")[i]));
                        smallmoney_pay = Double.parseDouble(new java.text.DecimalFormat("0.00").format(smallmoney_pay).toString());

                        for(int j = 0;j<mTicketListBeans.size();j++){
                            if(mTicketListBeans.get(j).getTickettype().equals(matid.split(",")[i])){
                                memberticketdetail += mTicketListBeans.get(j).getSnid()+",";
                                count ++;
                                if(count == nums_pay[i]) {
                                    break;
                                }
                            }
                        }
                    }
                }

                if(smallmoney_pay != 0) {
                    smallmoney_pay = allmoney_pay - smallmoney_pay;
                    smallmoney_pay = Double.parseDouble(new java.text.DecimalFormat("0.00").format(smallmoney_pay).toString());
                    othermoney_pay = smallmoney_pay;
                } else {
                    smallmoney_pay = allmoney_pay*discount/100;
                    smallmoney_pay = Double.parseDouble(new java.text.DecimalFormat("0.00").format(smallmoney_pay).toString());
                    othermoney_pay = allmoney_pay;
                }

                tv_service_sfmoney.setText(smallmoney_pay + "");

                if(cb_service_order.isChecked()){
                    smallmoneyuse = smallmoney_pay+"";
                    othermoney_pay = 0.00;
                } else {
    //                tv_money_left.setText(othermoney_pay + "");

                    if(smallmoney_pay == 0){
                        othermoney_pay = 0.00;
                    }
                }

                if(smallmoney_pay <= smallmoney_left && smallmoney_pay != 0){
                    cb_service_order.setVisibility(View.VISIBLE);
                    cb_service_order.setOnCheckedChangeListener(
                            new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton,
                                                             boolean b) {
                                    if(b){
                                        smallmoneyuse = smallmoney_pay+"";
                                        othermoney_pay = 0.00;
                                    } else {
                                        smallmoneyuse = "";
                                        othermoney_pay = smallmoney_pay;
                               //         tv_money_left.setText(othermoney_pay + "");
                                    }
                                }
                            });
                } else {
                    cb_service_order.setVisibility(View.GONE);
                }

                memberticketdetails = new String[memberticketdetail.split(",").length];
                for(int i = 0;i<memberticketdetail.split(",").length;i++){
                    memberticketdetails[i] = "{\\\"snid\\\":\\\"" + memberticketdetail.split(",")[i] + "\\\"}";
                }
            }
        }
    };

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    final MoneyLeft moneyLeft = (MoneyLeft) msg.obj;
                    discount = Integer.parseInt(moneyLeft.getData().getUse_smallmoney_discount_rate());
                    smallmoney_left = Double.parseDouble(moneyLeft.getData().getSmallmoney());
                    tv_service_smallmoney_left.setText(moneyLeft.getData().getSmallmoney());

                    tv_service_sfmoney.setText(Double.parseDouble(new java.text.DecimalFormat("0.00").format(allmoney_pay*discount/100).toString())+"");
                    othermoney_pay = allmoney_pay;
     //               tv_money_left.setText(othermoney_pay + "");


                    if(Double.parseDouble(new java.text.DecimalFormat("0.00").format(allmoney_pay*discount/100).toString()) <= smallmoney_left){
                        cb_service_order.setVisibility(View.VISIBLE);
                        cb_service_order.setOnCheckedChangeListener(
                                new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton compoundButton,
                                                                 boolean b) {
                                        if(b){
                                            othermoney_pay = 0.00;
                                            smallmoneyuse = Double.parseDouble(new java.text.DecimalFormat("0.00").format(allmoney_pay*discount/100).toString())+"";
                                        } else {
                                            othermoney_pay = allmoney_pay;
      //                                      tv_money_left.setText(othermoney_pay+"");
                                        }
                                    }
                                });
                    } else {
                        cb_service_order.setVisibility(View.GONE);
                    }

                    for (int i = 0;i<matid.split(",").length;i++){
                        for(int j  = 0;j < mTicketListBeans.size();j++) {
                            if(mTicketListBeans.get(j).getTickettype().equals(matid.split(",")[i])) {
                                nums_left[i]++;
                            }
                        }
                    }
                    mServiceOrderAdapter =new ServiceOrderAdapter(mContext);
                    mServiceOrderAdapter.setList(matid,nums,nums_left,handler);
                    mlv_service_order.setAdapter(mServiceOrderAdapter);
                    break;

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
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 判断resultStatus 为非"9000"则代表可能支付失败
                            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            Toast.makeText(mContext, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
            }
        }
    };



    @Override
    public void initDatas() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        rlv.setLayoutManager(new GridLayoutManager(mContext, 3));
        tvTitle.setText("支付");
        tv_fwd.setText(mUserPerson.getData().get(0).getRegshopidshow());
        matid = getIntent().getStringExtra("matid");
        matidshow = getIntent().getStringExtra("matidshow");
        per_money = getIntent().getStringExtra("money");
        num = getIntent().getIntArrayExtra("nums");
        nums = new int[matid.split(",").length];
        int m = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = m; j < num.length; j++) {
                if (num[j] != 0) {
                    nums[i] = num[j];
                    m = j + 1;
                    break;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            listHead.add(new UtilityItem(matidshow.split(",")[i] + "X" + nums[i]));
        }
        rlv.setAdapter(new YyPostHeadAdapter(listHead));
        nums_left = new Integer[nums.length];
        nums_pay = new Integer[nums.length];
        fees = new Double[nums.length];

        for(int i = 0;i<nums.length;i++){
            nums_left[i] = 0;
            nums_pay[i] = 0;
            if(CommonUtils.isNetworkAvailable(mContext)){
                final int finalI = i;
                OkHttpUtils.post()
                        .url(WebUtils.getRequestUrl(WebUtils.YY_FWF))
                        .addParams("json","{\"key\":\"\",\"shopid\":\""+mUserPerson.getData().get(0).getRegshopid()+"\",\"matid\":\""+matid.split(",")[i]+"\"}")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                showToast(R.string.TheNetIsException);
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Check check = new Gson().fromJson(response,Check.class);
                                switch (check.getErr()){
                                    case 0:
                                        fees[finalI]  = Double.parseDouble(check.getMsg());
                                        allmoney_pay += nums[finalI]*(Double.parseDouble(per_money.split(",")[finalI])+fees[finalI]);
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

        allmoney_pay = Double.parseDouble(new java.text.DecimalFormat("0.00").format(allmoney_pay).toString());
        smallmoney_pay = allmoney_pay;
        othermoney_pay = allmoney_pay;

//        tv_money_left.setText(allmoney_pay+"");

        details = new String[nums.length];

        for(int i  = 0;i<nums.length;i++) {
            details[i] = "{\\\"pid\\\":\\\"" + matid.split(",")[i]
                    + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(0).getRegshopid()
                    + "\\\",\\\"num\\\":" + nums[i] + "}";
        }

        if(CommonUtils.isNetworkAvailable(mContext)){
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.ZH_ZF))
                    .addParams("json","{\"key\":\"\",\"msnid\":\""+mUserPerson.getData().get(0).getSnid()+"\","
                            + "\"pwd\":\""+mUserPerson.getData().get(0).getPassWord()+"\","
                            + "\"shopid\":\""+mUserPerson.getData().get(0).getRegshopid()+"\",\"matid\":\""+matid+"\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }
                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    MoneyLeft moneyleft = new Gson().fromJson(response,MoneyLeft.class);
                                    mTicketListBeans = moneyleft.getData().getTicketList();
                                    Message message = new Message();
                                    message.what = 0;
                                    message.obj = moneyleft;
                                    mHandler.sendMessage(message);
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

    @Override
    public void configViews() {

    }




    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.rl_dd, R.id.rl_dd_two,R.id.iv_back,R.id.cb_one,R.id.cb_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_dd:
                cbOne.setChecked(true);
                break;
            case R.id.rl_dd_two:
                showToast("暂不支持上门服务");
                cbTwo.setChecked(false);
                break;
            case R.id.cb_one:
                cbOne.setChecked(true);
                break;
            case R.id.cb_two:
                showToast("暂不支持上门服务");
                cbTwo.setChecked(false);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
