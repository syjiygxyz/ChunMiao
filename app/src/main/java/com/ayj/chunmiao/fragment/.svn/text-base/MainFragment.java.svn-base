package com.ayj.chunmiao.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.NewsActivity;
import com.ayj.chunmiao.activity.cg.CgActivity;
import com.ayj.chunmiao.activity.cmbz.CmbzActivity;
import com.ayj.chunmiao.activity.cmxd.CmxdMainActivity;
import com.ayj.chunmiao.activity.common.CustomActivity;
import com.ayj.chunmiao.activity.mdlx.MdLxActivity;
import com.ayj.chunmiao.activity.mdmanger.MdManageActivity;
import com.ayj.chunmiao.activity.myinformation.MyCenterActivity;
import com.ayj.chunmiao.activity.xsjl.XsjlMainActivity;
import com.ayj.chunmiao.activity.ykfx.YkfxMainActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.adapter.main.MainYyDialogAdapter;
import com.ayj.chunmiao.adapter.main.MySheetAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.YyQdBean;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.fragment.base.BaseFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2017/6/22.
 * 首页
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView mIvBack;//返回

    @BindView(R.id.iv_right)
    ImageView mIvRight;//个人中心

    @BindView(R.id.rlv_bottom)
    RecyclerView mRlvBottom;//底下的recyclerview

    List<UtilityItem> gridLists = new ArrayList<>();//下个九个栏目

    CommonGridAdapter mCommonGridAdapter;//下面九个的适配器


    YyQdBean yyQdBean;//预约签到信息

    /*预约六个集合*/
    List<YyQdBean.DataBean> yyOne = new ArrayList<>();

    List<YyQdBean.DataBean> yyTwo = new ArrayList<>();

    List<YyQdBean.DataBean> yyThree = new ArrayList<>();

    List<YyQdBean.DataBean> yyFour = new ArrayList<>();

    List<YyQdBean.DataBean> yyFive = new ArrayList<>();

    List<YyQdBean.DataBean> yySix = new ArrayList<>();

    /*预约签到集合*/
    List<YyQdBean.DataBean> qdOne = new ArrayList<>();

    List<YyQdBean.DataBean> qdTwo = new ArrayList<>();

    List<YyQdBean.DataBean> qdThree = new ArrayList<>();

    List<YyQdBean.DataBean> qdFour = new ArrayList<>();

    List<YyQdBean.DataBean> qdFive = new ArrayList<>();

    List<YyQdBean.DataBean> qdSix = new ArrayList<>();
    @BindView(R.id.tv_1)
    TextView mTv1;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rl_1)
    RelativeLayout mRl1;
    @BindView(R.id.tv_2)
    TextView mTv2;
    @BindView(R.id.rl_2)
    RelativeLayout mRl2;
    @BindView(R.id.tv_3)
    TextView mTv3;
    @BindView(R.id.rl_3)
    RelativeLayout mRl3;
    @BindView(R.id.tv_4)
    TextView mTv4;
    @BindView(R.id.rl_4)
    RelativeLayout mRl4;
    @BindView(R.id.tv_5)
    TextView mTv5;
    @BindView(R.id.rl_5)
    RelativeLayout mRl5;
    @BindView(R.id.tv_6)
    TextView mTv6;
    @BindView(R.id.rl_6)
    RelativeLayout mRl6;
    @BindView(R.id.tv_qd_1)
    TextView mTvQd1;
    @BindView(R.id.rl_qd_1)
    RelativeLayout mRlQd1;
    @BindView(R.id.tv_qd_2)
    TextView mTvQd2;
    @BindView(R.id.rl_qd_2)
    RelativeLayout mRlQd2;
    @BindView(R.id.tv_qd_3)
    TextView mTvQd3;
    @BindView(R.id.rl_qd_3)
    RelativeLayout mRlQd3;
    @BindView(R.id.tv_qd_4)
    TextView mTvQd4;
    @BindView(R.id.rl_qd_4)
    RelativeLayout mRlQd4;
    @BindView(R.id.tv_qd_5)
    TextView mTvQd5;
    @BindView(R.id.rl_qd_5)
    RelativeLayout mRlQd5;
    @BindView(R.id.tv_qd_6)
    TextView mTvQd6;
    @BindView(R.id.rl_qd_6)
    RelativeLayout mRlQd6;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcf_refresh;

    YyQdBean.DataBean qdInfo;

    Dialog dialog;
    @BindView(R.id.tv_msg_count)
    TextView tvMsgCount;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initDatas() {
        mIvBack.setVisibility(View.GONE);
        rlRight.setVisibility(View.VISIBLE);
        gridLists = UtilityItem.getMainGridList();
        mRlvBottom.setHasFixedSize(true);
        mRlvBottom.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        getYyDate();
        pcf_refresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getYyDate();
            }
        });
        getMsgTotalNum();
    }

    private void getMsgTotalNum() {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
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
                    .url(WebUtils.getRequestUrl(WebUtils.MSG_NUM))
                    .addParams("json",String.valueOf(jsonObject))
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
                                    tvMsgCount.setVisibility(View.VISIBLE);
                                    if (check.getTotal() != 0) {
                                        tvMsgCount.setVisibility(View.VISIBLE);
                                        if (check.getTotal() < 99) {
                                            tvMsgCount.setText(check.getTotal() + "");
                                        } else {
                                            tvMsgCount.setText("99+");
                                        }
                                    } else {
                                        tvMsgCount.setVisibility(View.GONE);
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

    @Override
    public void configViews() {
        mCommonGridAdapter = new CommonGridAdapter(R.layout.common_grid_item, gridLists);
        mRlvBottom.setAdapter(mCommonGridAdapter);
        /*九个栏目的点击*/
        mRlvBottom.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                switch (gridLists.get(position).getType()) {
                    case 1:
                        /*库存*/
                        CgActivity.jumpActivity(getActivity(), "2");
                        break;
                    case 2:
                        /*销售记录*/
                        intent.setClass(getActivity(), XsjlMainActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        /*进销存*/
                        intent.setClass(getActivity(), MdManageActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        /*盈亏分析*/
                        intent.setClass(getActivity(), YkfxMainActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        /*客户圈*/
                        CustomActivity.startActivty(getActivity(), "11");
                        break;
                    case 6:
                        /*春苗小店*/
                        intent.setClass(getActivity(), CmxdMainActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        /*春苗帮助*/
                        intent.setClass(getActivity(), CmbzActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        /*养生导航*/
                        NewsActivity.jumpActivity(getActivity(), WebUtils.DH_SM, "养身导航", "", "");
                        break;
                    case 9:
                        /*送体验卷*/
                        CustomActivity.startActivty(getActivity(), "2");
                        break;
                    case 10:
                        /*采购*/
                        CgActivity.jumpActivity(getActivity(), "1");
                        break;
                    case 11:
                        /*门店联销*/
                        startActivity(new Intent(getActivity(), MdLxActivity.class));
                        break;
                }
                //  startActivity(intent);
            }
        });
    }


    /*会员预约情况*/
    private void getYyDate() {
        yyQdBean = null;
        yyOne.clear();
        yyTwo.clear();
        yyThree.clear();
        yyFour.clear();
        yyFive.clear();
        yySix.clear();
        qdOne.clear();
        qdTwo.clear();
        qdThree.clear();
        qdFour.clear();
        qdFive.clear();
        qdSix.clear();
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("status", "BOOKSTATUS001" + "," + "BOOKSTATUS002" + "," + "BOOKSTATUS003");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.YY_URL))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            pcf_refresh.onRefreshComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcf_refresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    yyQdBean = new Gson().fromJson(response, YyQdBean.class);
                                    getInfo();
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

    private void getInfo() {
        if (null != yyQdBean.getData()) {
            for (int i = 0; i < yyQdBean.getData().size(); i++) {
                if (yyQdBean.getData().get(i).getStatus().equals(Constant.ZT_YY)) {/*预约*/
                    switch (yyQdBean.getData().get(i).getPsid()) {
                        case Constant.Wl_AJTL:
                            yyOne.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_SB:
                            yyTwo.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_TP:
                            yyThree.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_FR:
                            yyFour.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_FS:
                            yyFive.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_YY:
                            yySix.add(yyQdBean.getData().get(i));
                            break;
                    }
                } else if (yyQdBean.getData().get(i).getStatus().equals(Constant.ZT_QD)) {/*签到*/
                    switch (yyQdBean.getData().get(i).getPsid()) {
                        case Constant.Wl_AJTL:
                            qdOne.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_SB:
                            qdTwo.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_TP:
                            qdThree.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_FR:
                            qdFour.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_FS:
                            qdFive.add(yyQdBean.getData().get(i));
                            break;
                        case Constant.Wl_YY:
                            qdSix.add(yyQdBean.getData().get(i));
                            break;
                    }
                }
            }
        }
        setVisit(yyOne, mTv1);
        setVisit(yyTwo, mTv2);
        setVisit(yyThree, mTv3);
        setVisit(yyFour, mTv4);
        setVisit(yyFive, mTv5);
        setVisit(yySix, mTv6);
        setVisit(qdOne, mTvQd1);
        setVisit(qdTwo, mTvQd2);
        setVisit(qdThree, mTvQd3);
        setVisit(qdFour, mTvQd4);
        setVisit(qdFive, mTvQd5);
        setVisit(qdSix, mTvQd6);

    }

    private void setVisit(List<YyQdBean.DataBean> list, TextView tv) {
        if (list.size() == 0) {
            tv.setVisibility(View.GONE);
        } else if (list.size() > 99) {
            tv.setVisibility(View.VISIBLE);
            tv.setText("...");
        } else {
            tv.setVisibility(View.VISIBLE);
            tv.setText(list.size() + "");
        }
    }

    @OnClick({R.id.rl_1, R.id.rl_2, R.id.rl_3, R.id.rl_4, R.id.rl_5, R.id.rl_6, R.id.rl_qd_1,
            R.id.rl_qd_2, R.id.rl_qd_3, R.id.rl_qd_4, R.id.rl_qd_5, R.id.rl_qd_6, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                startActivity(new Intent(getActivity(), MyCenterActivity.class));
                break;
            case R.id.rl_1:
                if (yyOne.size() == 0) {
                    showToast("艾灸调理暂无预约");
                } else {
                    showYyDialog(yyOne);
                }
                break;
            case R.id.rl_2:
                if (yyTwo.size() == 0) {
                    showToast("声波调理暂无预约");
                } else {
                    showYyDialog(yyTwo);
                }
                break;
            case R.id.rl_3:
                if (yyThree.size() == 0) {
                    showToast("频谱调理暂无预约");
                } else {
                    showYyDialog(yyThree);
                }
                break;
            case R.id.rl_4:
                if (yyFour.size() == 0) {
                    showToast("负氧热调理暂无预约");
                } else {
                    showYyDialog(yyFour);
                }
                break;
            case R.id.rl_5:
                if (yyFive.size() == 0) {
                    showToast("百姓富人水调理暂无预约");
                } else {
                    showYyDialog(yyFive);
                }
                break;
            case R.id.rl_6:
                if (yySix.size() == 0) {
                    showToast("营养吧暂无预约");
                } else {
                    showYyDialog(yySix);
                }
                break;
            case R.id.rl_qd_1:
                if (qdOne.size() == 0) {
                    showToast("艾灸调理暂无签到信息");
                } else {
                    showDialog(qdOne);
                }
                break;
            case R.id.rl_qd_2:
                if (qdTwo.size() == 0) {
                    showToast("声波调理暂无签到信息");
                } else {
                    showDialog(qdTwo);
                }
                break;
            case R.id.rl_qd_3:
                if (qdThree.size() == 0) {
                    showToast("频谱调理暂无签到信息");
                } else {
                    showDialog(qdThree);
                }
                break;
            case R.id.rl_qd_4:
                if (qdFour.size() == 0) {
                    showToast("负氧热调理暂无签到信息");
                } else {
                    showDialog(qdFour);
                }
                break;
            case R.id.rl_qd_5:
                if (qdFive.size() == 0) {
                    showToast("百姓富人水调理暂无签到信息");
                } else {
                    showDialog(qdFive);
                }
                break;
            case R.id.rl_qd_6:
                if (qdSix.size() == 0) {
                    showToast("营养吧暂无签到信息");
                } else {
                    showDialog(qdSix);
                }
                break;
        }
    }

    /*签到的dialog*/
    private void showDialog(List<YyQdBean.DataBean> list) {
        dialog = new Dialog(getActivity(), R.style.base_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.main_dialog);
        /*设置标题*/
        TextView tv = (TextView) dialog.findViewById(R.id.tv_title);
        tv.setText(list.get(0).getPsidshow());
        RecyclerView recyclerList = (RecyclerView) dialog.findViewById(R.id.rlv);
        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        MySheetAdapter adapter = new MySheetAdapter(R.layout.main_dialog_item, list);
        recyclerList.setAdapter(adapter);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    /*预约的dialog*/
    private void showYyDialog(List<YyQdBean.DataBean> list) {
        dialog = new Dialog(getActivity(), R.style.base_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.main_dialog);
        /*设置标题*/
        TextView tv = (TextView) dialog.findViewById(R.id.tv_title);
        tv.setText(list.get(0).getPsidshow());
        TextView tv_none_yon = (TextView) dialog.findViewById(R.id.tv_none_yon);
        tv_none_yon.setVisibility(View.INVISIBLE);
        RecyclerView recyclerList = (RecyclerView) dialog.findViewById(R.id.rlv);
        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        MainYyDialogAdapter adapter = new MainYyDialogAdapter(R.layout.main_dialog_item, list,
                new QdOnClickListener());
        recyclerList.setAdapter(adapter);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    /**
     * 签到监听
     */
    private class QdOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dialog.dismiss();
            qdInfo = (YyQdBean.DataBean) v.getTag();
            SweetAlertDialog confirmDialog = CommonUtils.getConfirmDialog(getActivity(), "签到",
                    "确定要给" + qdInfo.getSnid() + "用户签到吗?",
                    new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            //签到
                            qdClick(qdInfo.getSnid());
                            //隐藏dialog
                            sweetAlertDialog.dismiss();
                        }
                    });
            //显示dialog
            confirmDialog.show();
        }
    }

    /*签到*/
    private void qdClick(String snid) {
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("booksnid", snid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.QD_URL))
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
                                    SweetAlertDialog confirmDialog = CommonUtils.getSuccessDialog(
                                            getActivity(), "签到成功",
                                            "签到编号为:" + check.getData(),
                                            new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(
                                                        SweetAlertDialog sweetAlertDialog) {
                                                    getYyDate();
                                                    //隐藏dialog
                                                    sweetAlertDialog.dismiss();
                                                }
                                            });
                                    //显示dialog
                                    confirmDialog.show();
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

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("MyCenterClick")) {
            getActivity().finish();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

