package com.ayj.chunmiao.activity.common;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.cz.CzActivity;
import com.ayj.chunmiao.activity.cmbz.fwq.FwqListActivity;
import com.ayj.chunmiao.activity.cmbz.hyk.HykMainActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.InsuranceMainActivity;
import com.ayj.chunmiao.activity.cmbz.tc.TcMainActivity;
import com.ayj.chunmiao.activity.cmbz.yy.YdActivity;
import com.ayj.chunmiao.activity.dzc.DzcActivity;
import com.ayj.chunmiao.activity.khq.KhqActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.MainYyDialogAdapter;
import com.ayj.chunmiao.adapter.txl.TxlAdapter;
import com.ayj.chunmiao.adapter.txl.TxlHeadAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UserBean;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.bean.txl.TxBean;
import com.ayj.chunmiao.bean.txl.TxBodyBean;
import com.ayj.chunmiao.bean.txl.TxHeadBean;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.ValidationUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.utils.txl.HeaderRecyclerAndFooterWrapperAdapter;
import com.ayj.chunmiao.utils.txl.ViewHolder;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import com.mcxtzhang.indexlib.IndexBar.widget.IndexBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 类似通讯录的通用界面
* type1....预约.2通讯录 .3.服务券4热柜5折扣商铺6兑换中心7邻家小店8保险9会员卡10套餐购买11客户圈12充值
* */
public class CustomActivity extends BaseActivity {

    private OperatePopupWindow pop;// 弹出的popwindow

    List<UtilityItem> itemList = new ArrayList<>();

    public LayoutInflater mInflater;

    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.indexBar)
    IndexBar mIndexBar;
    @BindView(R.id.tvSideBarHint)
    TextView mTvSideBarHint;
    //设置给InexBar、ItemDecoration的完整数据集
    List<TxBean.DataBean> totalList = new ArrayList<>();//所有的数据
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_right)
    ImageView mIvRight;

    //主体部分数据源（城市数据）
    private List<TxBodyBean> mBodyDatas = new ArrayList<>();

    private TxHeadBean mHeadDatas;

    private LinearLayoutManager mManager;

    private List<BaseIndexPinyinBean> mSourceDatas = new ArrayList<>();

    TxlAdapter txlAdapter;

    TxlHeadAdapter mTxlHeadAdapter;

    private HeaderRecyclerAndFooterWrapperAdapter mHeaderAdapter;

    private SuspensionDecoration mDecoration;

    Dialog dialog;

    SweetAlertDialog confirmDialog;

    @BindView(R.id.container_layout)
    LinearLayout container_layout;

    @BindView(R.id.tv_right)
    TextView tv_right;
    private String mobile,name;


    @Override
    protected void onStart() {
        super.onStart();
        if ("2".equals(getIntent().getStringExtra("type"))) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom;
    }

    @Override
    public void initDatas() {
        mInflater = LayoutInflater.from(mContext);

    }

    public static void startActivty(Context cxt, String type) {
        Intent mIntent = new Intent(cxt, CustomActivity.class);
        mIntent.putExtra("type", type);
        cxt.startActivity(mIntent);
    }

    @Override
    public void configViews() {
        getDate(2);
    }

    private void getDate(final int i) {
        totalList.clear();
        mBodyDatas.clear();
        mSourceDatas.clear();
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post().
                    url(WebUtils.getRequestUrl(WebUtils.TX_URL))
                    .addParams("json", String.valueOf(obj))
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
                                    TxBean txBean = new Gson().fromJson(response, TxBean.class);
                                    totalList.addAll(txBean.getData());
                                    List<TxBean.DataBean> tsList = new ArrayList<>();//特殊体验用户
                                    for (int i = 0; i < totalList.size(); i++) {
                                        if (Constant.TS_HY.equals(
                                                totalList.get(i).getMembertype())) {
                                            /*特殊体验会员*/
                                            tsList.add(totalList.get(i));
                                        } else {
                                            /*一般会员*/
                                            mBodyDatas.add(new TxBodyBean(totalList.get(i)));
                                        }
                                    }
                                    mHeadDatas = new TxHeadBean(tsList, "特殊体验用户", "特");
                                    mSourceDatas.add(mHeadDatas);
                                    mSourceDatas.addAll(mBodyDatas);
                                    initView(mBodyDatas, mHeadDatas, mSourceDatas, i);
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

    /*2为初始化，1为刷新*/
    private void initView(List<TxBodyBean> bodyDatas, final TxHeadBean headDatas,
            List<BaseIndexPinyinBean> sourceDatas, int i) {
        if (2 == i) {
            mRv.setLayoutManager(mManager = new LinearLayoutManager(mContext));
            txlAdapter = new TxlAdapter(mContext, R.layout.tx_item, bodyDatas);
            mHeaderAdapter = new HeaderRecyclerAndFooterWrapperAdapter(txlAdapter) {
                @Override
                protected void onBindHeaderHolder(ViewHolder holder, int headerPos, int layoutId,
                        Object o) {
                    switch (layoutId) {
                        case R.layout.txl_head:
                            final TxHeadBean txHead = (TxHeadBean) o;
                            RecyclerView recyclerView = holder.getView(R.id.rvCity);
                            recyclerView.setLayoutManager(
                                    mManager = new LinearLayoutManager(mContext));
                            mTxlHeadAdapter = new TxlHeadAdapter(R.layout.tx_item,
                                    txHead.getCityList());
                            recyclerView.setAdapter(mTxlHeadAdapter);
                            recyclerView.addOnItemTouchListener(new OnItemClickListener() {
                                @Override
                                public void onSimpleItemClick(BaseQuickAdapter adapter, View view,
                                        int position) {
                                        /*头部点击事件*/
                                    //2为送体验券
                                    if ("2".equals(getIntent().getStringExtra("type"))) {
                                        addData(2);
                                        pop = new OperatePopupWindow(mContext,
                                                "请选择想对" + txHead.getCityList().get(
                                                        position).getName()
                                                        + "进行的操作", itemList,
                                                new OnItemClickListener() {
                                                    @Override
                                                    public void onSimpleItemClick(
                                                            BaseQuickAdapter adapter,
                                                            View view, int position) {
                                            /*送券*/
                                                        giveTyq(2);
                                                        pop.dismiss();
                                                    }
                                                });
                                        pop.showAtLocation(container_layout, Gravity.BOTTOM
                                                | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        pop.update();
                                    } else if ("13".equals(getIntent().getStringExtra("type"))){
                                        showConfirmDialog(position,"head");
                                    }else {
                                        showPassWordDialog(mHeadDatas.getCityList().get(
                                                position).getSnid(), position, "head");
                                    }
                                }
                            });
                            break;
                        default:
                            break;
                    }
                }
            };
            mHeaderAdapter.setHeaderView(0, R.layout.txl_head, headDatas);
            mRv.setAdapter(mHeaderAdapter);
            /*下面列表点击*/
            txlAdapter.setOnItemClickListener(new com.ayj.chunmiao.utils.txl.OnItemClickListener() {
                @Override
                public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                    //2为送体验券
                    if ("2".equals(getIntent().getStringExtra("type"))) {
                        addData(1);
                        pop = new OperatePopupWindow(mContext,
                                "请选择想对" + mBodyDatas.get(position).getCity().getName()
                                        + "进行的操作", itemList, new OnItemClickListener() {
                            @Override
                            public void onSimpleItemClick(BaseQuickAdapter adapter,
                                    View view, int position) {

                                if (position == 0) {
                                /*送券*/
                                    giveTyq(1);
                                } else if (position == 1) {
                                /*设为体验用户*/
                                    setTyMenber();
                                }
                                pop.dismiss();
                            }
                        });
                        pop.showAtLocation(container_layout, Gravity.BOTTOM
                                | Gravity.CENTER_HORIZONTAL, 0, 0);
                        pop.update();
                    } else if ("13".equals(getIntent().getStringExtra("type"))){
                            showConfirmDialog(position,"list");
                    }else{
                        showPassWordDialog(mBodyDatas.get(position).getCity().getSnid(), position,
                                "list");
                    }
                }

                @Override
                public boolean onItemLongClick(ViewGroup parent, View view, Object o,
                        int position) {
                    return false;
                }
            });
            mRv.addItemDecoration(mDecoration = new SuspensionDecoration(this, sourceDatas)
                    .setmTitleHeight(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35,
                                    getResources().getDisplayMetrics()))
                    .setColorTitleBg(0xffefefef)
                    .setTitleFontSize(
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16,
                                    getResources().getDisplayMetrics()))
                    .setColorTitleFont(mContext.getResources().getColor(android.R.color.black))
                    .setHeaderViewCount(0));
            mRv.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.VERTICAL));
            //使用indexBar
            mIndexBar.setVisibility(View.VISIBLE);
            mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                    .setNeedRealIndex(true)//设置需要真实的索引
                    .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                    .setHeaderViewCount(0);
            mIndexBar.getDataHelper().sortSourceDatas(bodyDatas);
            txlAdapter.setDatas(bodyDatas);
            mIndexBar.setmSourceDatas(sourceDatas)//设置数据
                    .invalidate();
            mDecoration.setmDatas(sourceDatas);
        } else {
            mIndexBar.getDataHelper().sortSourceDatas(bodyDatas);
            mHeaderAdapter.notifyDataSetChanged();
            mIndexBar.invalidate();
        }

    }

    private void showConfirmDialog(int position,String type) {
        if (type.equals("head")){
                name = null == mHeadDatas.getCityList().get(position).getName()? "未命名" : mHeadDatas.getCityList().get(position).getName().toString();
                mobile = null== mHeadDatas.getCityList().get(position).getMobile() ? mHeadDatas.getCityList().get(position).getIdcard().toString()
                        : mHeadDatas.getCityList().get(position).getMobile().toString();
        }else if (type.equals("list")){
                name = null == mBodyDatas.get(position).getCity().getName() ? "未命名" :mBodyDatas.get(position).getCity().getName().toString();
                mobile = null == mBodyDatas.get(position).getCity().getMobile() ? mBodyDatas.get(position).getCity().getIdcard().toString()
                        :mBodyDatas.get(position).getCity().getMobile().toString();
        }
       confirmDialog =  CommonUtils.getConfirmDialog(mContext, "提示", "确认分享给"+name+"吗?", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("mobile",mobile);
                if (null != getIntent().getSerializableExtra("thing")){
                    bundle.putSerializable("thing",(Serializable) getIntent().getSerializableExtra("thing"));
                }
                intent.putExtras(bundle);
                setResult(1,intent);
                sweetAlertDialog.dismiss();
                finish();
            }
        });
        confirmDialog.show();
    }

    @OnClick({R.id.iv_back, R.id.iv_right, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showAddMenberDialog();
                break;
            case R.id.tv_right:
                /*代注册*/
                startActivity(new Intent(mContext, DzcActivity.class));
                break;
        }
    }

    /*添加用户*/
    private void showAddMenberDialog() {
        dialog = new Dialog(mContext, R.style.base_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_new_menber);
        final EditText et_phone = (EditText) dialog.findViewById(R.id.et_phone);
        final EditText et_password = (EditText) dialog.findViewById(R.id.et_password);
        Button cancel_button = (Button) dialog.findViewById(R.id.cancel_button);
        Button confirm_button = (Button) dialog.findViewById(R.id.confirm_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_password.getText().toString())) {
                    showToast("密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(et_phone.getText().toString())) {
                    showToast("手机号不能为空");
                    return;
                }
                if (!ValidationUtils.checkTelPhone(et_phone.getText().toString())) {
                    showToast("请填写正确的手机号");
                    return;
                }
                if (CommonUtils.isNetworkAvailable(mContext)) {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("key", "");
                        obj.put("mobile", et_phone.getText().toString().trim());
                        obj.put("pwd",
                                MD5Utils.getMD5String(et_password.getText().toString().trim()));
                        obj.put("shopid",
                                AyjSwApplication.getsInstance().getUserInfo().getData().get(
                                        0).getShopid());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mProgressHub.show();
                    OkHttpUtils.post().
                            url(WebUtils.getRequestUrl(WebUtils.ADD_MENBER_URL))
                            .addParams("json", String.valueOf(obj))
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
                                            getDate(1);
                                            dialog.dismiss();
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
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    //申请体验用户什么的
    private void addData(int type) {
        itemList.clear();
        if (type == 1) {
            //非体验用户
            UtilityItem item = new UtilityItem();
            item.setText("送券");
            itemList.add(item);
            UtilityItem item1 = new UtilityItem();
            item1.setText("设置体验用户");
            itemList.add(item1);
        } else if (type == 2) {
            //体验用户
            UtilityItem item = new UtilityItem();
            item.setText("券");
            itemList.add(item);
        }
    }

    //设置体验用户
    private void setTyMenber() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("msnid", mBodyDatas.get(0).getCity().getSnid());
                obj.put("membertype", Constant.TS_HY);
                obj.put("applycomment", "特殊体验会员");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post().
                    url(WebUtils.getRequestUrl(WebUtils.SQ_TY))
                    .addParams("json", String.valueOf(obj))
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
                                    //输入密码框
                                    showPassWordDialog("申请成功，请等待审核", 0, "");
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

    //显示输入密码的输入框
    private void showPassWordDialog(final String text, final int i, final String type) {
        dialog = new Dialog(mContext, R.style.base_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //定义一个dialog,并将自己定义的样式加入进去
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.sb_yz_passw_dialog, null);
        dialog.setContentView(dialogView); //将窗口布局加入到dialog中
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(true); //设置点击空白处不会消失
        final EditText et = (EditText) dialog.findViewById(R.id.et);
        Button cancel_button = (Button) dialog.findViewById(R.id.cancel_button);
        Button confirm_button = (Button) dialog.findViewById(R.id.confirm_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().toString().trim().equals("")) {
                    Toast.makeText(mContext, "请输入密码", Toast.LENGTH_SHORT).show();
                } else {
                    //体验券
                    if ("2".equals(getIntent().getStringExtra("type"))) {
                        if (AyjSwApplication.getsInstance().getUserInfo().getData().get(
                                0).getPassWord().
                                equals(MD5Utils.getMD5String(et.getText().toString().trim()))) {
                            showToast(text);
                            dialog.dismiss();
                        } else {
                            showToast("密码错误请重试");
                            et.setText("");
                        }
                    } else {
                        //除开通讯录的其他界面点击过去
                        if (CommonUtils.isNetworkAvailable(mContext)) {
                            JSONObject obj = new JSONObject();
                            try {
                                obj.put("key", "");
                                if ("head".equals(type)) {
                                    //头部
                                    obj.put("mobile", null == mHeadDatas.getCityList().get(
                                            i).getMobile() ? mHeadDatas.getCityList().get(
                                            i).getIdcard().toString()
                                            : mHeadDatas.getCityList().get(
                                                    i).getMobile().toString());
                                } else if ("list".equals(type)) {
                                    //list部分
                                    obj.put("mobile", null == mBodyDatas.get(
                                            i).getCity().getMobile() ? mBodyDatas.get(
                                            i).getCity().getIdcard().toString() : mBodyDatas.get(
                                            i).getCity().getMobile().toString());
                                }
                                obj.put("pwd",
                                        MD5Utils.getMD5String(et.getText().toString().trim()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            mProgressHub.show();
                            OkHttpUtils.post().
                                    url(WebUtils.getRequestUrl(WebUtils.GET_ZH_MA))
                                    .addParams("json", String.valueOf(obj))
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
                                            Check check = new Gson().fromJson(response,
                                                    Check.class);
                                            switch (check.getErr()) {
                                                case 0:
                                                    UserPerson userPerson = new Gson().fromJson(
                                                            response, UserPerson.class);
                                                    userPerson.getData().get(0).setPassWord(
                                                            MD5Utils.getMD5String(
                                                                    et.getText().toString()
                                                                            .trim()));
                                                    aCache.put(ACache.USER_PERSON_INFO_KEY,
                                                            userPerson);
                                                    //1为预约  3服务券
                                                    if ("1".equals(
                                                            getIntent().getStringExtra("type"))) {
                                                        startActivity(new Intent(mContext,
                                                                YdActivity.class));
                                                    }else if("3".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        startActivity(new Intent(mContext,
                                                                FwqListActivity.class));
                                                        /*春苗展柜*/
                                                    }else if("4".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        CommonSpListActivity.startActivity(mContext, "no_hot", Constant.ORDERTYPE_CMRG2, Constant.MAT_CLASS1_CMRG, "cmrg");
                                                        //折扣商铺
                                                    }else if("5".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        CommonSpListActivity.startActivity(mContext, "have_hot", Constant.ORDERTYPE_ZKSP, Constant.MAT_CLASS1_LJXW, "zkp");
                                                        //兑换中心
                                                    }else if("6".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        startActivity(new Intent(mContext, DhActivity.class));
                                                  //      CommonSpListActivity.startActivity(mContext,"no_hot",Constant.ORDERTYPE_DH,Constant.MAT_CLASS1_LJXW,"dhzx");
                                                        //邻家小店
                                                    }else if("7".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        CommonSpListActivity.startActivity(mContext, "have_hot", Constant.ORDERTYPE_LJXW, Constant.MAT_CLASS1_LJXW, "ljxd");
                                                        //保险
                                                    }else if("8".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        startActivity(new Intent(mContext,
                                                                InsuranceMainActivity.class));
                                                        //会员卡
                                                    }else if("9".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        startActivity(new Intent(mContext,
                                                                HykMainActivity.class));
                                                        //套餐购买
                                                    }else if("10".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        startActivity(new Intent(mContext,
                                                                TcMainActivity.class));
                                                    }else if("11".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        startActivity(new Intent(mContext,
                                                                KhqActivity.class));
                                                    }else if("12".equals(
                                                            getIntent().getStringExtra("type"))){
                                                        startActivity(new Intent(mContext,
                                                                CzActivity.class));
                                                    }

                                                    dialog.dismiss();
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

                }
            }

        });
        dialog.show();
    }

    //送体验券
    private void giveTyq(int i) {
        /*1为特殊体验用户送券2为普通的*/
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                 /*1为特殊体验用户送券2为普通的*/
                if (1 == i) {
                    obj.put("msnid", mBodyDatas.get(0).getCity().getSnid());
                } else {
                    obj.put("msnid", mHeadDatas.getCityList().get(0).getSnid());
                }
                obj.put("matid", Constant.TYQ);
                obj.put("givenum", "1");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post().
                    url(WebUtils.getRequestUrl(WebUtils.SEND_QUE))
                    .addParams("json", String.valueOf(obj))
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
                                    //输入密码框
                                    showPassWordDialog("赠送成功", 0, "");
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

    @Subscribe //在ui线程执行
    public void onEventMainThread(FirstEvent event) {
        if ("CustomActivityClick".equals(event.getMsg())) {
            getDate(1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if ("2".equals(getIntent().getStringExtra("type"))) {
            EventBus.getDefault().unregister(this);//反注册EventBus
        }
    }

}
