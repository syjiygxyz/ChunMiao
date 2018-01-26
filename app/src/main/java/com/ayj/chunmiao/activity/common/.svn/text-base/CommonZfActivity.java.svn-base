package com.ayj.chunmiao.activity.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.shopping.AddAddressActivity;
import com.ayj.chunmiao.adapter.shopping.CommonGwListAdapter;
import com.ayj.chunmiao.adapter.shopping.DialogAddressAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MoneyLeft;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.shopping.Address;
import com.ayj.chunmiao.bean.shopping.JinmaoShopDetial;
import com.ayj.chunmiao.bean.shopping.ShopCart;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
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

/*
*通用支付界面
* */
public class CommonZfActivity extends BaseActivity {

    @BindView(R.id.btn_sure)
    Button btn_sure; //确认下单

    //名字,电话,地址,应付,商品名字，商品价格，商品数量，会员余额
    TextView tv_name, tv_phone, tv_address, tv_yf, tv_sp_name, tv_price, tv_count, tv_hy_money;

    ImageView iv_logn;//图片图标、

    private LinearLayout ll;//单个的

    private String num;

    private static final int SDK_PAY_FLAG = 1;

    String order_type;

    String money;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private Address.DataBean addressBean = null;

    private String memberaddrid;

    private String matid;

    private JinmaoShopDetial jinmaoShopDetial;

    private RecyclerView recycler_view;

    private CommonGwListAdapter commonGwListAdapter;

    private DialogAddressAdapter dialogAddressAdapter;

    private List<ShopCart.DataBean> list = new ArrayList<>();

    private List<Address.DataBean> addresses = new ArrayList<>();

    private Intent intent;

    private String addUrl;

    private UserPerson mUserPerson;

    @BindView(R.id.cb_lq)
    CheckBox cb_lq;

    @BindView(R.id.cb_zfb)
    CheckBox cb_zfb;

    @BindView(R.id.cb_wx)
    CheckBox cb_wx;

    String jsonobj;

    String jsonobjj;//零钱支付json

    String dzMoney;

    String matidshow;


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
    private String orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_zf;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("确认订单");
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        getYe();
        initView();
        initDate();
        getLqMoney();
        getAddress();
    }

    @Override
    public void configViews() {
        cb_lq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb_zfb.setChecked(false);
                cb_wx.setChecked(false);
                if (cb_lq.isChecked()) {
                    cb_lq.setChecked(true);
                    tv_yf.setText("¥" + dzMoney);
                } else {
                    cb_lq.setChecked(false);
                    tv_yf.setText("¥" + money);
                }
            }
        });
        cb_zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_yf.setText("¥" + money);
                cb_lq.setChecked(false);
                cb_wx.setChecked(false);
                if (cb_zfb.isChecked()) {
                    cb_zfb.setChecked(true);
                } else {
                    cb_zfb.setChecked(false);
                }
            }
        });
        cb_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_yf.setText("¥" + money);
                cb_zfb.setChecked(false);
                cb_lq.setChecked(false);
                if (cb_wx.isChecked()) {
                    cb_wx.setChecked(true);
                } else {
                    cb_wx.setChecked(false);
                }
            }
        });
    }

    /*初始化数据*/
    private void initDate() {
        intent = getIntent();
        addressBean = (Address.DataBean) intent.getSerializableExtra("address");
        money = intent.getStringExtra("money");
        matid = intent.getStringExtra("matid");
        matidshow = intent.getStringExtra("shop_matid_show");
        order_type = intent.getStringExtra("order_type");
        num = intent.getStringExtra("num");
        /*商品详情*/
        if (intent.getStringExtra("type").equals("spDetail")) { /*从商品详情界面你点击过来的*/
            ll.setVisibility(View.VISIBLE);
            tv_count.setText("X" + " " + num);

            Picasso.with(mContext).load(getIntent().getStringExtra("img_url")).placeholder(R.mipmap.zhanweitu).error(
                    R.mipmap.jiazaishibia).into(
                    iv_logn);
            tv_sp_name.setText(getIntent().getStringExtra("shop_matid_show"));
            tv_price.setText("¥" + getIntent().getStringExtra("now_price"));
            addUrl = "{" + "\\\"pid\\\":\\\"" + getIntent().getStringExtra("shop_matid")
                    + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(
                    0).getRegshopid() + "\\\",\\\"num\\\":\\\""
                    + num + "\\\"" + "}";
        } else if (intent.getStringExtra("type").equals("gwc")) {//购物车跳转过来的
            recycler_view.setVisibility(View.VISIBLE);
            list = (List<ShopCart.DataBean>) intent.getSerializableExtra("list");
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
            recycler_view.setLayoutManager(layoutManager);
            /*固定高度提交性能*/
            recycler_view.setHasFixedSize(true);
            commonGwListAdapter = new CommonGwListAdapter(
                    list);
            recycler_view.setAdapter(commonGwListAdapter);
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    addUrl = "{" + "\\\"pid\\\":\\\"" + list.get(i).getMatid()
                            + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(
                            0).getRegshopid() + "\\\",\\\"num\\\":\\\""
                            + list.get(i).getNum() + "\\\"" + "}";
                } else {
                    addUrl = addUrl + "," + "{" + "\\\"pid\\\":\\\"" + list.get(i).getMatid()
                            + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(
                            0).getRegshopid() + "\\\",\\\"num\\\":\\\""
                            + list.get(i).getNum() + "\\\"" + "}";
                }
            }
        }
        tv_name.setText("收货人：" + addressBean.getReceivename());
        tv_address.setText(
                "收货地址：" + addressBean.getProvinceid() + addressBean.getCityid()
                        + addressBean.getAreaid() + addressBean.getDetailaddr());
        tv_phone.setText(addressBean.getMobile());
        tv_yf.setText("¥" + money);
        memberaddrid = addressBean.getSnid();
    }

    private void initView() {
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        ll = (LinearLayout) findViewById(R.id.ll);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_yf = (TextView) findViewById(R.id.tv_yf);
        tv_sp_name = (TextView) findViewById(R.id.tv_sp_name);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_hy_money = (TextView) findViewById(R.id.tv_hy_money);
        iv_logn = (ImageView) findViewById(R.id.iv_logn);
    }

    /*获取余额*/
    private void getYe() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.ZH_ZF))
                    .addParams("json",
                            "{\"key\":\"\",\"msnid\":\"" + mUserPerson.getData().get(
                                    0).getSnid() + "\","
                                    + "\"pwd\":\"" + mUserPerson.getData().get(
                                    0).getPassWord()
                                    + "\","
                                    + "\"shopid\":\"" + mUserPerson.getData().get(
                                    0).getRegshopid() + "\",\"matid\":\"" + matid + "\"}")
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
                                    MoneyLeft moneyleft = new Gson().fromJson(response,
                                            MoneyLeft.class);
                                    final Double smallmoney_left = Double.parseDouble(
                                            moneyleft.getData().getSmallmoney());
                                    tv_hy_money.setText("¥" + smallmoney_left);
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }

                    });
        }
    }

    /*获取零钱支付打折后的余额*/
    private void getLqMoney() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.DZ_LQ_PAY))
                    .addParams("json", "{\"key\":\"\",\"msnid\":\""
                            + mUserPerson.getData().get(0).getSnid()
                            + "\",\"pwd\":\"" + mUserPerson.getData().get(
                            0).getPassWord() + "\",\"ordertype\":\"" + order_type
                            + "\",\"orderdetail\":\"" + "[" + addUrl + "]" + "\"}")
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
                                    dzMoney = check.getData().toString();
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void setOrders(final String payType) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            if ("1".equals(payType)){
                jsonobj = "{\"key\":\"\",\"msnid\":\""
                        + mUserPerson.getData().get(0).getSnid()
                        + "\",\"pwd\":\"" + mUserPerson.getData().get(
                        0).getPassWord() + "\",\"paytype\":\"" + "PAYTYPE001" + "\",\"ordertype\":\"" + order_type
                        + "\",\"memberaddrid\":\"" + memberaddrid
                        + "\",\"smallmoneyuse\":\"" + dzMoney
                        + "\",\"membermoneybagusemoney\":\"" + ""
                        + "\",\"servicebooksnid\":\"" + ""
                        + "\",\"memberticketdetail\":\"" + ""
                        + "\",\"membercarddetail\":\"" + ""
                        + "\",\"orderdetail\":\"" + "[" + addUrl + "]" + "\"}";
            }else {
                jsonobj = "{\"key\":\"\",\"msnid\":\""
                        + mUserPerson.getData().get(0).getSnid()
                        + "\",\"pwd\":\"" + mUserPerson.getData().get(
                        0).getPassWord() + "\",\"paytype\":\"" + "PAYTYPE999" + "\",\"ordertype\":\"" + order_type
                        + "\",\"memberaddrid\":\"" + memberaddrid
                        + "\",\"smallmoneyuse\":\"" + ""
                        + "\",\"membermoneybagusemoney\":\"" + ""
                        + "\",\"servicebooksnid\":\"" + ""
                        + "\",\"memberticketdetail\":\"" + ""
                        + "\",\"membercarddetail\":\"" + ""
                        + "\",\"orderdetail\":\"" + "[" + addUrl + "]" + "\"}";
            }

            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SEND_DD))
                    .addParams("json", jsonobj)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    orderId = check.getData().toString();
                                    if ("1".equals(payType)){
                                        showToast("购买成功");
                                    }else if ("2".equals(payType)){
                                        paybyali(money,orderId,matidshow,Constant.HY);
                                    }else if ("3".equals(payType)){
                                        CommonUtils.paybywx(mContext,orderId,Constant.HY);
                                    }
                             //       finish();
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    /*显示密码输入键盘*/
    private void dialogShow() {
        new PassWordDialog(this).setTitle("请输入交易密码").setCompleteListener(
                new DialogCompleteListener() {
                    @Override
                    public void dialogCompleteListener(String money, String pwd) {
                        yzPassWord(pwd);
                    }
                }).show();
    }

    /*验证密码*/
    private void yzPassWord(String pwd) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.YZ_MA))
                    .addParams("json", "{\"key\":\"\",\"msnid\":\""
                            + mUserPerson.getData().get(0).getSnid() + "\","
                            + "\"pwd\":\"" + mUserPerson.getData().get(
                            0).getPassWord() + "\",\"paypwd\":\""
                            + MD5Utils.getMD5String(pwd)
                            + "\"}")
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
                                    setOrders("1");
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

    @OnClick({R.id.iv_back, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_sure:
                if (cb_wx.isChecked() || cb_zfb.isChecked() || cb_lq.isChecked()) {
                       if (intent.getStringExtra("type").equals("spDetail")) {
                          showAddressDialog();
                        } else {
                             /*零钱支付*/
                           if (cb_lq.isChecked()) {
                               dialogShow();
                           } else if (cb_zfb.isChecked()) {
                        /*支付宝*/
                               setOrders("2");
                           }else if (cb_wx.isChecked()){
                        /*微信*/
                               setOrders("3");
                           }
                        }

                } else {
                    showToast("请选择支付方式");
                }

                break;
        }
    }

    private void showAddressDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(mContext,
                R.style.DialogTheme).create();
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_zq_address);
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.7);
        window.setAttributes(p);

        final ImageView iv_back = (ImageView) dialog.findViewById(R.id.iv_zq_address_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        final ListView lv_dialog_address = (ListView) dialog.findViewById(R.id.lv_dialog_address);
        final Button btn_dialog_addaddress = (Button) dialog.findViewById(
                R.id.btn_dialog_addaddress);
        dialogAddressAdapter = new DialogAddressAdapter(
                mContext, addresses);
        lv_dialog_address.setAdapter(dialogAddressAdapter);
        lv_dialog_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addressBean = addresses.get(i);
                tv_address.setText(
                        "收货地址：" + addressBean.getProvinceid() + addressBean.getCityid()
                                + addressBean.getAreaid() + addressBean.getDetailaddr());
                memberaddrid = addressBean.getSnid();
                   /*零钱支付*/
                if (cb_lq.isChecked()) {
                    dialogShow();
                } else if (cb_zfb.isChecked()) {
                        /*支付宝*/
                    setOrders("2");
                }else if (cb_wx.isChecked()){
                        /*微信*/
                    setOrders("3");
                }
                dialog.dismiss();
            }
        });

        btn_dialog_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    /*获取地址*/
    private void getAddress() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_DETAIL_ADDRESS))
                    .addParams("json",
                            "{\"key\":\"\",\"userid\":\"" + mUserPerson.getData().get(
                                    0).getSnid() + "\",\"pwd\":\""
                                    + mUserPerson.getData().get(0).getPassWord() + "\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    Address add = new Gson().fromJson(response, Address.class);
                                    for (int i = 0; i < add.getData().size(); i++) {
                                        addresses.add(add.getData().get(i));
                                    }
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
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
