package com.ayj.chunmiao.activity.cmbz.yy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.Listener.NoDoubleClickListener;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.cmbz.yy.MemberServiceAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.MemberCardService;
import com.ayj.chunmiao.bean.cmbz.MyMemberCard;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.MyListView;
import com.ayj.chunmiao.view.TimePicker1.TimePickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class CardYyActivity extends BaseActivity {
    UserPerson mUserPerson;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private TextView tv_introduction;
    private MyListView mylistview_service;

    private List<MyMemberCard.DataBean> membercards;
    private MemberServiceAdapter mMemberServiceAdapter;
//    private String mcard = "";

    private boolean[] hasclicked;

    private List<MemberCardService.DataBean> memberCardServices;
//    private int p = 0;
//    private String mcard_p = "";

    private MyMemberCard.DataBean mMyMemberCard;

    private RelativeLayout relativeLayout_viewpager;
    private TextView tv_viewpager_name;
    private TextView tv_viewpager_time;
    private CheckBox cb_service_shop, cb_service_home;
    private TextView tv_service_addr;

    private TimePickerView timePickerView;
    private String servicetype = "SERVICEADDRTYPE001";//服务方式
    private String serviceaddrdetail = "";//服务地点

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    updataService();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_yy;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("卡预约");
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        findViews();
        initData();
        registerListener();
    }

    @Override
    public void configViews() {

    }

    private void findViews() {

        tv_introduction = (TextView) findViewById(R.id.tv_introduction);
        mylistview_service = (MyListView) findViewById(R.id.mylistview_service);

        relativeLayout_viewpager = (RelativeLayout) findViewById(R.id.relativeLayout_viewpager);
        tv_viewpager_name = (TextView) findViewById(R.id.tv_viewpager_name);
        tv_viewpager_time = (TextView) findViewById(R.id.tv_viewpager_time);

        cb_service_shop = (CheckBox) findViewById(R.id.cb_service_shop);
        cb_service_home = (CheckBox) findViewById(R.id.cb_service_home);

        tv_service_addr = (TextView) findViewById(R.id.tv_service_addr);
    }

    private void initData() {

        cb_service_shop.setChecked(true);
        cb_service_home.setChecked(false);

        mMyMemberCard = (MyMemberCard.DataBean) getIntent().getSerializableExtra("mcard");

        timePickerView = new TimePickerView(mContext, TimePickerView.Type.ALL);

        if ("CARDTYPE004".equals(mMyMemberCard.getCardtype())) {
            if(mMyMemberCard.getCardtypeshow().contains("A卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kanglebnhy);
            }else if(mMyMemberCard.getCardtypeshow().contains("B卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.klqnhy);
            }else if(mMyMemberCard.getCardtypeshow().contains("百姓富人水卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kljk);
            }
            tv_introduction.setText(
                    "\n1.会员可以每天任选艾灸，超声，汗蒸进行预约，预约后24"
                            +
                            "小时之内的营业时间到店内即可服务，一天一次，不可叠加；\n2.会员可以每天到康乐小屋领取1500ml的富氢水，领取时自带容器；\n3"
                            + ".会员可以按时按期领取营养茶点。");
        } else if ("CARDTYPE005".equals(mMyMemberCard.getCardtype())) {
            if(mMyMemberCard.getCardtypeshow().contains("A卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kanglebnhy);
            }else if(mMyMemberCard.getCardtypeshow().contains("B卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.klqnhy);
            }else if(mMyMemberCard.getCardtypeshow().contains("百姓富人水卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kljk);
            }
            tv_introduction.setText(
                    "\n会员可以每天到康乐小屋领取1500ml的富氢水，领取时自带容器。");
        } else if ("CARDTYPE006".equals(mMyMemberCard.getCardtype())) {
            if(mMyMemberCard.getCardtypeshow().contains("A卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kanglebnhy);
            }else if(mMyMemberCard.getCardtypeshow().contains("B卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.klqnhy);
            }else if(mMyMemberCard.getCardtypeshow().contains("百姓富人水卡")){
                relativeLayout_viewpager.setBackgroundResource(R.mipmap.kljk);
            }
            tv_introduction.setText(
                    "\n1.会员可以每天任选艾灸，超声，汗蒸进行预约，预约后24"
                            +
                            "小时之内的营业时间到店内即可服务，一天一次，不可叠加；\n2.会员可以每天到康乐小屋领取1500ml的富氢水，领取时自带容器；\n3"
                            + ".会员可以按时按期领取营养茶点。");
        }
        tv_viewpager_name.setText(mMyMemberCard.getCardtypeshow());
        tv_viewpager_time.setText("有效期至" + mMyMemberCard.getValidatetime().split(" ")[0]);
        tv_service_addr.setText(mUserPerson.getData().get(0).getRegshopidshow());
        membercards = new ArrayList<>();
        mMemberServiceAdapter = new MemberServiceAdapter(mContext, mOnClick);
        getServices();
    }

    //匿名内部类监听器
    private NoDoubleClickListener mOnClick = new NoDoubleClickListener() {

        @Override
        protected void onNoDoubleClick(View v) {
            switch (v.getId()) {
                case R.id.tv_memberservice_yy:
                    if (!TextUtils.isEmpty(servicetype)) {
                        int position = (Integer) v.getTag(R.id.tv_memberservice_yy);
                        showWarningDialog(position);
                    } else {
                        showToast("请先选择服务地点");
                    }
                    break;
            }
        }
    };

    private void registerListener() {

        cb_service_shop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb_service_home.setChecked(false);
                    servicetype = "SERVICEADDRTYPE001";
                } else {
                    servicetype = "";
                }
            }
        });

        cb_service_home.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    showToast("目前服务站点繁忙，暂不支持到家服务");
                    cb_service_home.setChecked(false);

                }
            }
        });

    }

    private void showWarningDialog(final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_card_appointment);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showYyDialog(position);
            }
        });
        dialog.findViewById(R.id.image_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void getServices() {

        memberCardServices = new ArrayList<>();
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_MENBER_DETAIL))
                    .addParams("json",
                            "{\"key\":\"\",\"msnid\":\"" + mUserPerson.getData().get(0).getSnid() + "\","
                                    + "\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord() + "\",\"mcard\":\"" + mMyMemberCard.getMcard()
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
                                    MemberCardService memberCardService = new Gson().fromJson(
                                            response, MemberCardService.class);
                                    for (int i = 0; i < memberCardService.getData().size(); i++) {
                                        memberCardServices.add(memberCardService.getData().get(i));
                                    }
                                    mMemberServiceAdapter.setList(memberCardServices,
                                            "[{\\\"mcard\\\":\\\"" + mMyMemberCard.getMcard()
                                                    + "\\\"}]", mHandler);
                                    mylistview_service.setAdapter(mMemberServiceAdapter);
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

    private void updataService() {

        memberCardServices = new ArrayList<>();
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_MENBER_DETAIL))
                    .addParams("json",
                            "{\"key\":\"\",\"msnid\":\"" + mUserPerson.getData().get(0).getSnid() + "\","
                                    + "\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord() + "\",\"mcard\":\"" + mMyMemberCard.getMcard()
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
                            Log.i("45789", response);
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    MemberCardService memberCardService = new Gson().fromJson(
                                            response, MemberCardService.class);
                                    for (int i = 0; i < memberCardService.getData().size(); i++) {
                                        memberCardServices.add(memberCardService.getData().get(i));
                                    }
                                    mMemberServiceAdapter.setList(memberCardServices,
                                            "[{\\\"mcard\\\":\\\"" + mMyMemberCard.getMcard()
                                                    + "\\\"}]", mHandler);
                                    mMemberServiceAdapter.notifyDataSetChanged();
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

    private void showYyDialog(final int position) {
//        if ("1".equals(mUserStore.getUserInfo().getData().get(0).getHaspaypwd())) {
            timePickerView.show();
            timePickerView.setOnTimeSelectListener(
                    new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date) {
                          //  showPdDialog(date, position);
                            sendDd(date, position);
                        }
                    });
//        } else {
//            Toast.makeText(
//                    MemberServiceActivity.this,
//                    "请先设置支付密码",
//                    Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(
//                    MemberServiceActivity.this,
//                    SafeCenterActivity.class);
//            startActivity(intent);
//        }
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

//    private void showPdDialog(final Date date, final int position) {
//        final AlertDialog dialog_pwd = new AlertDialog.Builder(mContext,
//                R.style.DialogTheme).create();
//        dialog_pwd.show();
//
//        Window window = dialog_pwd.getWindow();
//        window.setContentView(R.layout.dialog_pwd);
//        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.mystyle);  //添加动画
//        WindowManager m = getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
//        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
//        p.height = CommonUtils.dip2px(mContext, 381);
//        window.setAttributes(p);
//
//        final PasswordView pwdView = (PasswordView) dialog_pwd.findViewById(R.id.pwd_view);
//
//        pwdView.getPwdNameTextView().setText("输入支付密码");
//        //添加密码输入完成的响应
//        pwdView.setOnFinishInput(new OnPasswordInputFinish() {
//            @Override
//            public void inputFinish() {
//
//                if (AiYiJiaApplication.isNetworkAvailable(MemberServiceActivity.this)) {
//                    OkHttpUtils.post()
//                            .url(UrlUtils.PAY_SET_ORDER_URL)
//                            .addParams("json", "{\"key\":\"\",\"msnid\":\""
//                                    + mUserStore.getUserInfo().getData().get(0).getSnid()
//                                    + "\",\"pwd\":\"" + mUserStore.getUserInfo().getData().get(
//                                    0).getPwd() + "\",\"ordertype\":\"" + "MEMBERORDERTYPE003"
//                                    + "\",\"memberaddrid\":\"" + ""
//                                    + "\",\"smallmoneyuse\":\"" + "" + "\",\"servicetime\":\""
//                                    + getTime(date) + "\",\"membermoneybagusemoney\":\"" + ""
//                                    + "\",\"serviceaddrtype\":\"" + servicetype
//                                    + "\",\"serviceaddrdetail\":\"" + serviceaddrdetail
//                                    + "\",\"servicebooksnid\":\"" + ""
//                                    + "\",\"memberticketdetail\":\"" + ""
//                                    + "\",\"membercarddetail\":\"" + "[{\\\"mcard\\\":\\\""
//                                    + mMyMemberCard.getMcard() + "\\\"}]"
//                                    + "\",\"orderdetail\":\"[{\\\"pid\\\":\\\""
//                                    + memberCardServices.get(position).getServicematid()
//                                    + "\\\",\\\"shopid\\\":\\\""
//                                    + mUserStore.getUserInfo().getData().get(0).getRegshopid()
//                                    + "\\\",\\\"num\\\":\\\"" + 1 + "\\\"}]\"}")
//                            .build()
//                            .execute(new StringCallback() {
//                                @Override
//                                public void onError(Call call, Exception e, int id) {
//
//                                }
//
//                                @Override
//                                public void onResponse(String response, int id) {
//                                    Check check = new Gson().fromJson(response, Check.class);
//                                    switch (check.getErr()) {
//                                        case 0:
//                                            new AlertDialog.Builder(
//                                                    MemberServiceActivity.this).setMessage(
//                                                    "预约成功").setPositiveButton("确定",
//                                                    new DialogInterface
//                                                            .OnClickListener() {
//
//                                                        @Override
//                                                        public void onClick(
//                                                                DialogInterface dialog,
//                                                                int which) {
//                                                            finish();
//                                                        }
//                                                    }).setNegativeButton("查看",
//                                                    new DialogInterface.OnClickListener() {
//
//                                                        @Override
//                                                        public void onClick(
//                                                                DialogInterface dialog,
//                                                                int which) {
//                                                            finish();
//                                                            Intent intent = new Intent(
//                                                                    MemberServiceActivity.this,
//                                                                    MyServiceActivity.class);
//                                                            startActivity(intent);
//                                                        }
//                                                    }).show();
//                                            mHandler.sendEmptyMessage(1);
//                                            break;
//                                        default:
//                                            Toast.makeText(MemberServiceActivity.this,
//                                                    check.getMsg(),
//                                                    Toast.LENGTH_SHORT).show();
//                                            break;
//                                    }
//                                }
//                            });
//                } else {
//                    AiYiJiaApplication.toast(MemberServiceActivity.this, R.string.TheNetIsUnAble);
//                }
//            }
//        });
//
//
//        pwdView.getCancelImageView().setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            protected void onNoDoubleClick(View v) {
//                dialog_pwd.dismiss();
//            }
//        });
//
//        pwdView.getForgetTextView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MemberServiceActivity.this,
//                        RetrievePayPwdActivity.class);
//                startActivity(intent);
//            }
//        });
//        dialog_pwd.show();
//    }
   /*发送订单*/
   private void sendDd(Date date, int position){
       if (CommonUtils.isNetworkAvailable(mContext)) {
           mProgressHub.show();
           OkHttpUtils.post()
                   .url(WebUtils.getRequestUrl(WebUtils.SEND_DD))
                   .addParams("json", "{\"key\":\"\",\"msnid\":\""
                           + mUserPerson.getData().get(0).getSnid()
                           + "\",\"pwd\":\"" +mUserPerson.getData().get(0).getPassWord()+ "\",\"ordertype\":\"" + "MEMBERORDERTYPE003"
                           + "\",\"memberaddrid\":\"" + ""
                           + "\",\"smallmoneyuse\":\"" + "" + "\",\"servicetime\":\""
                           + getTime(date) + "\",\"membermoneybagusemoney\":\"" + ""
                           + "\",\"serviceaddrtype\":\"" + servicetype
                           + "\",\"serviceaddrdetail\":\"" + serviceaddrdetail
                           + "\",\"servicebooksnid\":\"" + ""
                           + "\",\"memberticketdetail\":\"" + ""
                           + "\",\"membercarddetail\":\"" + "[{\\\"mcard\\\":\\\""
                           + mMyMemberCard.getMcard() + "\\\"}]"
                           + "\",\"orderdetail\":\"[{\\\"pid\\\":\\\""
                           + memberCardServices.get(position).getServicematid()
                           + "\\\",\\\"shopid\\\":\\\""
                           + mUserPerson.getData().get(0).getRegshopid()
                           + "\\\",\\\"num\\\":\\\"" + 1 + "\\\"}]\"}")
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
                                   showToast(check.getMsg());
                                   mHandler.sendEmptyMessage(1);
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
