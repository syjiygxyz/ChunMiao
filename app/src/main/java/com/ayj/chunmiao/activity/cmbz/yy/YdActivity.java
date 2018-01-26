package com.ayj.chunmiao.activity.cmbz.yy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.Listener.NoDoubleClickListener;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.DemoActivity;
import com.ayj.chunmiao.activity.MainActivity;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.base.NewsActivity;
import com.ayj.chunmiao.adapter.cmbz.ServiceGridViewAdapter;
import com.ayj.chunmiao.adapter.cmbz.yy.PageAdapter;
import com.ayj.chunmiao.adapter.cmbz.yy.YyYybAdapter;
import com.ayj.chunmiao.adapter.main.MainYyDialogAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.YyQdBean;
import com.ayj.chunmiao.bean.cmbz.MyMemberCard;
import com.ayj.chunmiao.bean.cmbz.ServicePriceBean;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.cmbz.UserTicket;
import com.ayj.chunmiao.bean.cmbz.VehicleBean;
import com.ayj.chunmiao.bean.cmbz.YybBean;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.NumberButton;
import com.ayj.chunmiao.view.ZoomOutPageTransformer;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 约单
* */
public class YdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.top_fl)
    FrameLayout mTopFl;
    private Button btn_yy_now;

    @BindView(R.id.listview_service)
    LinearLayout rlv;

    private ScrollView scrollView;

    public HashMap<String, Object> map;

    YyYybAdapter yyYybAdapter;
    ServiceGridViewAdapter mServiceGridViewAdapter;
    List<ServicePriceBean.DataBean> services;
    List<ServicePriceBean.DataBean> servicebean;

    private String shopid;
    private String iscm;
    private String matid = "";
    private String matidshow = "";
    private String money = "";
    private int[] nums;
    private int[] nums_left;

    private boolean hasdisease = true;
    private boolean hasselect = false;

    List<ArrayList<UserTicket.DataBean>> list = new ArrayList<>();

    List<ServicePriceBean.DataBean> serviceList = new ArrayList<>();

    RecyclerView recyclerList;

    Dialog dialog;

    List<YybBean.DataBean> listYyb = new ArrayList<>();

    List<YybBean.DataBean> listYybSnid = new ArrayList<>();

    int pdSure = 0;

    List<UtilityItem> chooseList = new ArrayList<>();

    public LayoutInflater mInflater;

    String chooseCardUrl;

    String jsonobjj;

    String addUrl;

    List<String> chooseSnid = new ArrayList<>();

    UserPerson mUserPerson;

    @Override
    public int getLayoutId() {
        return R.layout.activity_yd;
    }

    @Override
    public void initDatas() {
        mInflater = LayoutInflater.from(mContext);
        mTvTitle.setText("约单");
        initData();
        registerListener();

    }

    @Override
    public void configViews() {

    }

    private void initData() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        shopid = getIntent().getStringExtra("shopid");
        iscm = getIntent().getStringExtra("iscm");
        membercards = new ArrayList<>();
        adapter = new PageAdapter(mContext, membercards);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        getMemberCard();
        getYyb();
        getYybNum();
        getServices();
    }

    private void registerListener() {

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            float oldX = 0, newX = 0, sens = 5;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        oldX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        newX = event.getX();
                        if (Math.abs(oldX - newX) < sens) {
                            Intent intent = new Intent(mContext,
                                    CardYyActivity.class);
                            intent.putExtra("mcard", membercards.get(p));
                            startActivity(intent);
                            return true;
                        }
                        oldX = 0;
                        newX = 0;
                        break;
                }
                return false;
            }
        });
    }

    ;

    private void jumpNext(ServicePriceBean.DataBean item) {
        String h5URL = "";
        String title = "";
        if (item.getMatid().equals("100")) {//艾灸调理物料
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH016";
            title = "艾灸调理";
        } else if (item.getMatid().equals("WL-20151126-0000089")) {//声波调理物料
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH002";
            title = "声波调理";
        } else if (item.getMatid().equals("93")) {//频谱调理
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH008";
            title = "频谱调理";
        } else if (item.getMatid().equals("WL-20151126-0000094")) {//负养热调理
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH003";
            title = "负养热调理";
        } else if (item.getMatid().equals("189")) {//营养吧调理
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH004";
            title = "营养吧调理";
        } else if (item.getMatid().equals("WL-20161205-0000679")) {//富人水
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH005";
            title = "富人水调理";
        } else if (item.getMatid().equals("WL-20151126-0000088")) {//电子酒
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH001";
            title = "电子灸调理";
        } else if (item.getMatid().equals("101")) {//生物电
            h5URL = WebUtils.H5_DETAIL + "imgtype3=COMMONIMAGETYPETH015";
            title = "生物电调理";
        }
        if (item.getMatid().equals("189")) {
            /*营养吧弹出框*/
            if (0 == listYyb.size()) {
                showToast("暂无预约");
            } else {
                pdSure = 0;
                showYyDialog(listYyb);
            }
        } else {
            Intent mIntent = new Intent(mContext, NewsActivity.class);
            mIntent.putExtra("url", h5URL);
            mIntent.putExtra("title", title);
            if (item.getMatid().equals("100") || item.getMatid().equals("101")) {
                mIntent.putExtra("getMatid", "WL-20151126-0000088");
            } else {
                mIntent.putExtra("getMatid", item.getMatid());
            }
            mIntent.putExtra("type", "2");
            mIntent.putExtra("standardsaleprice", item.getStandardsaleprice());
            startActivity(mIntent);
        }

    }

    private void getServices() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.YD_LIST))
                    .addParams("json", "{\"key\":\"\",\"ps\":\"PS002\"}")
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
                                    ServicePriceBean servicePriceBean = new Gson().fromJson(
                                            response, ServicePriceBean.class);
                                    serviceList.addAll(servicePriceBean.getData());
                                    creatView(serviceList);
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

    private void getYybNum() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.YYB))
                    .addParams("json", "{\"key\":\"\",\"userid\":\"" + mUserPerson.getData().get(0).getSnid()
                            + "\","
                            + "\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()
                            + "\",\"type\":\"" + 2
                            + "\",\"pageno\":\"" + 1 + "\","
                            + "\"pagesize\":\"" + 9999 + "\"}")
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
                                    YybBean ticket = new Gson().fromJson(response,
                                            YybBean.class);
                                    listYyb.addAll(ticket.getData());
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

    private void creatView(List<ServicePriceBean.DataBean> list) {
        for (ServicePriceBean.DataBean parentFunction : list) {//获取首页整个下方栏目新闻数据
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            View view = mInflater.inflate(R.layout.yy_list_item, null);
            ImageView iv1 = (ImageView) view.findViewById(R.id.iv_title1);
            ImageView iv2 = (ImageView) view.findViewById(R.id.iv_title2);
            if ("WL-20151126-0000088".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.aijiuzuhez);
                iv2.setImageResource(R.mipmap.yuyue_aijiu);
            } else if ("WL-20151126-0000089".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.sbtlyy);
                iv2.setImageResource(R.mipmap.yuyue_chaosheng);
            } else if ("WL-20151126-0000094".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.fyrtli);
                iv2.setImageResource(R.mipmap.yuyue_hanzhen);
            } else if ("189".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.yinyangbyy);
                iv2.setImageResource(R.mipmap.yuyue_yinyangba);
            } else if ("WL-20161205-0000679".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.bxfrsyy);
                iv2.setImageResource(R.mipmap.yuyue_bxfrs);
            } else if ("93".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.pinputl);
                iv2.setImageResource(R.mipmap.pptl);
            } else if ("100".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.dianzijiutl);
                iv2.setImageResource(R.mipmap.dzjtl);
            } else if ("101".equals(parentFunction.getMatid())) {
                iv1.setImageResource(R.mipmap.swdtnan);
                iv2.setImageResource(R.mipmap.swdtnam);
            }
            view.setLayoutParams(params);
            view.setTag(parentFunction);
            rlv.addView(view);
            view.setOnClickListener(new newsOnClickListener());//设置点击事件
        }
    }

    private void getYyb() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_TYQ))
                    .addParams("json",
                            "{\"key\":\"\",\"userid\":\"" + mUserPerson.getData().get(0).getSnid()
                                    + "\","
                                    + "\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()
                                    + "\",\"type\":\"" + 2
                                    + "\",\"pageno\":\"" + 1 + "\","
                                    + "\"pagesize\":\"" + 9999 + "\"}")
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
                                    YybBean ticket = new Gson().fromJson(response,
                                            YybBean.class);
                                    listYybSnid.addAll(ticket.getData());
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

    //----------------viewpager --------------
    private ViewPager mViewPager;
    private PageAdapter adapter;
    private List<MyMemberCard.DataBean> membercards;
    private String mcard = "";
    private String mcard_p = "";
    private int p = 0;

    private void getMemberCard() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_HYK))
                    .addParams("json",
                            "{\"key\":\"\",\"msnid\":\"" +mUserPerson.getData().get(0).getSnid()
                                    + "\","
                                    + "\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()
                                    + "\",\"cardtype\":\"CARDTYPE004,CARDTYPE005,CARDTYPE006\"}")
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
                                    MyMemberCard myMemberCard = new Gson().fromJson(response,
                                            MyMemberCard.class);

                                    if (myMemberCard.getData().size() == 0) {
                                        mViewPager.setVisibility(View.GONE);
                                        mTopFl.setVisibility(View.GONE);
                                        tv_1.setText("非会员卡预约");
                                    } else {
                                        for (int i = 0; i < myMemberCard.getData().size(); i++) {
                                            membercards.add(myMemberCard.getData().get(i));
                                        }
                                        mcard_p = membercards.get(0).getMcard();
                                        mcard = "[{\\\"mcard\\\":\\\"" + membercards.get(
                                                0).getMcard()
                                                + "\\\"}]";
                                        mViewPager.setAdapter(adapter);
                                        mViewPager.addOnPageChangeListener(
                                                new ViewPager.OnPageChangeListener() {
                                                    @Override
                                                    public void onPageScrolled(int position,
                                                                               float positionOffset,
                                                                               int positionOffsetPixels) {
                                                    }

                                                    @Override
                                                    public void onPageSelected(int position) {
                                                        p = position;
                                                        mcard_p = membercards.get(
                                                                position).getMcard();
                                                        mcard = "[{\\\"mcard\\\":\\\""
                                                                + membercards.get(
                                                                position).getMcard()
                                                                + "\\\"}]";
                                                    }

                                                    @Override
                                                    public void onPageScrollStateChanged(
                                                            int state) {
                                                    }
                                                });
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

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    /*预约的dialog*/
    private void showYyDialog(List<YybBean.DataBean> list) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.yyb_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        recyclerList = (RecyclerView) dialog.findViewById(R.id.rlv);
        recyclerList.setLayoutManager(new LinearLayoutManager(mContext));
        yyYybAdapter = new YyYybAdapter(R.layout.yyb_yd_item, listYyb, new checkOnClickListener());
        recyclerList.setAdapter(yyYybAdapter);
        dialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseList.clear();
                chooseSnid.clear();
                for (int i = 0; i <listYyb.size() ; i++) {
                    if(0!=listYyb.get(i).getGetNumber()){
                        chooseList.add(new UtilityItem(listYyb.get(i).getTickettype(),listYyb.get(i).getGetNumber()));
                    }
                }
                if(chooseList.size()!=0){
                    for (int i = 0; i <chooseList.size() ; i++) {
                        for (int z = 0; z <listYybSnid.size(); z++) {
                            if(chooseList.get(i).getText().equals(listYybSnid.get(z).getTickettype())){
                                chooseSnid.add(listYybSnid.get(z).getSnid().toString());
                            }
                            if(chooseSnid.size()==chooseList.get(i).getResId()){
                                break;
                            }
                        }
                    }
                    setOrders();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    private void setOrders() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            for (int i = 0; i <chooseSnid.size(); i++) {
                if (i==0) {
                    chooseCardUrl = "{" + "\\\"snid\\\":\\\"" +chooseSnid.get(i)
                            + "}";
                } else {
                    chooseCardUrl = chooseCardUrl + "," + "{" + "\\\"snid\\\":\\\"" + chooseSnid.get(i)
                            + "}";
                }
            }
            for (int i = 0; i < chooseList.size(); i++) {
                if (i == 0) {
                    addUrl = "{" + "\\\"pid\\\":\\\"" + chooseList.get(i).getText()
                            + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(0).getRegshopid() + "\\\",\\\"num\\\":\\\""
                            + chooseList.get(i).getResId() + "\\\"" + "}";
                } else {
                    addUrl = addUrl + "," + "{" + "\\\"pid\\\":\\\"" + chooseList.get(i).getText()
                            + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(0).getRegshopid() + "\\\",\\\"num\\\":\\\""
                            + (chooseList.get(i).getResId()+"") + "\\\"" + "}";
                }
            }
            jsonobjj = "{\"key\":\"\",\"msnid\":\"" + mUserPerson.getData().get(0).getSnid()
                    + "\",\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()
                    + "\",\"paytype\":\"" + "PAYTYPE005" + "\",\"ordertype\":\"" + "MEMBERORDERTYPE003"
                    + "\",\"memberaddrid\":\"" + ""
                    + "\",\"smallmoneyuse\":\"" + ""
                    + "\",\"servicetime\":\"" + ""
                    + "\",\"membermoneybagusemoney\":\"" + "" + "\",\"serviceaddrtype\":\"" + "SERVICEADDRTYPE001"
                    + "\",\"serviceaddrdetail\":\"" + "" + "\",\"memberticketdetail\":\"" + chooseCardUrl
                    + "\",\"orderdetail\":\"" + "["+addUrl + "]"+"\"}";
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SEND_DD))
                    .addParams("json", jsonobjj)
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
                                    Toast.makeText(mContext, "购买成功",
                                            Toast.LENGTH_SHORT).show();
                                    /*成功之后需要刷新当前界面营养吧的数据*/
                                    listYybSnid.clear();
                                    listYyb.clear();
                                    getYyb();
                                    getYybNum();
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



    /**
     * Checkbox监听
     */
    private class checkOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(final View v) {
            if (v.getTag() != null) {
                CheckBox cb = (CheckBox) v;
                String position = v.getTag().toString().split(",")[1];
                if (cb.isChecked()) {
                    listYyb.get(Integer.parseInt(position)).setIsornotClick(true);
                    RelativeLayout rl = (RelativeLayout) recyclerList.getChildAt(Integer.parseInt(position));
                    NumberButton nb = (NumberButton) rl.getChildAt(2);
                    listYyb.get(Integer.parseInt(position)).setGetNumber(nb.getNumber());
                } else {
                    listYyb.get(Integer.parseInt(position)).setIsornotClick(false);

                }
                yyYybAdapter.setNewData(listYyb);
                yyYybAdapter.notifyDataSetChanged();
            }
        }
    }

    class newsOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ServicePriceBean.DataBean parentFunction = (ServicePriceBean.DataBean) v.getTag();
            jumpNext(parentFunction);
        }
    }
}
