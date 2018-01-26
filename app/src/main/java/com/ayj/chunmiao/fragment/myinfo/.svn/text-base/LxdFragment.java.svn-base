package com.ayj.chunmiao.fragment.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.ayj.chunmiao.AyjSwApplication;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.common.CustomActivity;
import com.ayj.chunmiao.activity.myinformation.dashang.ZwActivity;
import com.ayj.chunmiao.activity.myinformation.lxd.LxdActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.myinformation.LxdAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.myinformation.LxdBean;
import com.ayj.chunmiao.fragment.base.BaseFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.AddAndSub.MyCustomDialog;
import com.ayj.chunmiao.view.PayWayPopupWindow;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.sina.util.Utility;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by zht-pc-04 on 2017/8/23.
 */

public class LxdFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private PayWayPopupWindow popupWindow;
    private PassWordDialog dialog;
    private List<LxdBean.DataBean> list = new ArrayList<>();
    private LxdAdapter adapter;
    private MyCustomDialog customDialog;
    private String type;
    private int num;
    private LxdActivity activity;
    private int position;

    public static LxdFragment newInstance(String type){
        LxdFragment fragment = new LxdFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void initDatas() {
        if (null != getArguments()){
            type = getArguments().getString("type");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void configViews() {
        getUnionList();
    }

    private void getUnionList() {
        if (CommonUtils.isNetworkAvailable(getActivity())){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("ordertype",type);
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_UNION_SALE))
                    .addParams("json",String.valueOf(object))
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
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    LxdBean lxdBean = new Gson().fromJson(response,LxdBean.class);
                                    list.addAll(lxdBean.getData());
                                    if (null == adapter){
                                        adapter = new LxdAdapter(R.layout.item_lxd,list);
                                        recyclerView.setAdapter(adapter);
                                    }else{
                                        adapter.setNewData(list);
                                    }
                                    adapter.setOnConfirmClinckListener(new LxdAdapter.OnConfirmClickListener() {
                                        @Override
                                        public void onConfirmClickListener(int pos) {
                                            position = pos;
                                            showPayWindow();
                                        }

                                        @Override
                                        public void onShareClickListener(int pos) {
                                            position = pos;
                                            showCustomDialog();
                                        }
                                    });
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else{
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void showPayWindow() {
        if (null != list.get(position).getTotalmoney()){
            popupWindow = new PayWayPopupWindow(getActivity(), "选择支付方式","小金库", CommonUtils.douFormat(list.get(position).getTotalmoney()), UtilityItem.getPayWayList(), new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                    switch (position){
                        case 0:
                            activity = (LxdActivity) getActivity();
                            activity.paybyali(CommonUtils.douFormat(list.get(position).getTotalmoney()),list.get(position).getOrderid(),list.get(position).getMatidshow(), Constant.CG);
                            popupWindow.dismiss();
                            break;
                        case 1:
                            CommonUtils.paybywx(getActivity(),list.get(position).getOrderid(),Constant.CG);
                            popupWindow.dismiss();
                            break;
                        case 2:
                            showPaypwdDialog(position);
                            popupWindow.dismiss();
                            break;
                    }
                }
            },null,3);
            popupWindow.showAtLocation(recyclerView, Gravity.BOTTOM,0,0);
            popupWindow.update();
        }

    }

    private void showPaypwdDialog(final int pos) {
        dialog = new PassWordDialog(getActivity());
        dialog.setCompleteListener(new DialogCompleteListener() {
            @Override
            public void dialogCompleteListener(String money, String pwd) {
                activity = (LxdActivity) getActivity();
                activity.validateShopPayPwd(list.get(pos).getOrderid(),pwd,Constant.CG,null);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showCustomDialog() {
        num = 1;
        customDialog = new MyCustomDialog(getActivity(),1,Integer.parseInt(list.get(position).getNum()));
        customDialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = customDialog.getCount();
                showShareWindow(position);
                customDialog.dismiss();
            }
        });
        customDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }

    private void showShareWindow(final int pos) {
        new ShareAction(getActivity())
                .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                .addButton("umeng_sharebutton_custom","umeng_sharebutton_custom","kehuquan_icon","kehuquan_icon")
                .setShareboardclickCallback(new  ShareBoardlistener() {

                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        //根据key来区分自定义按钮的类型，并进行对应的操作
                        if (snsPlatform.mKeyword.equals("umeng_sharebutton_custom")){
                            Intent intent = new Intent(getActivity(), CustomActivity.class);
                            intent.putExtra("type","13");
                            intent.putExtra("thing",(Serializable) list.get(pos));
                            startActivityForResult(intent,1);
                        }else {
                            shareToMember(null,share_media);
                        }
                    }
                })
                .open();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lxd;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data){
            if (requestCode == 1){
               // showToast(data.getStringExtra("mobile"));
                shareToMember(data.getStringExtra("mobile"),null);
            }
        }
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }
    private void shareToMember(final String mobile, final SHARE_MEDIA share_media) {
        if (CommonUtils.isNetworkAvailable(getActivity())){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("tomembermobile",mobile);
                object.put("ordertype",list.get(position).getOrdertype());
                object.put("matid",list.get(position).getMatid());
                object.put("maxnumperomember","1");
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHARE_UNION_SALE))
                    .addParams("json",String.valueOf(object))
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
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0 :
                                    if (share_media != null && mobile == null) {
                                        CommonUtils.shareToWeiXin(getContext(),getActivity(),share_media,check.getData().toString());
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
}
