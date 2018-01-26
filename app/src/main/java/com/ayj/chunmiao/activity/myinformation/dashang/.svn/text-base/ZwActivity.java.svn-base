package com.ayj.chunmiao.activity.myinformation.dashang;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.common.CustomActivity;
import com.ayj.chunmiao.adapter.myinformation.DaswAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.MdClBean;
import com.ayj.chunmiao.bean.myinformation.CxjBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.AddAndSub.MyCustomDialog;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

public class ZwActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    private int pageNo = 1;
    private int pageSize = 12;
    private List<CxjBean.DataBean> list = new ArrayList<>();
    private DaswAdapter adapter;
    private MyCustomDialog dialog;
    private int num;
    private String matId,imagUrl,matIdShow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zw;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("打赏物");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getStockList();
            }
        });
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                list.clear();
                pageNo = 1;
                getStockList();
            }
        });
        getStockList();
    }

    private void getStockList() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("ordertype", Constant.CG_CX);
                object.put("pageno",pageNo);
                object.put("pagesize",pageSize);
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CL_URL))
                    .addParams("json",String.valueOf(object))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            recyclerView.onLoadMoreComplete();
                            pcfRefresh.onRefreshComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            recyclerView.onLoadMoreComplete();
                            pcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    CxjBean cxjBean = new Gson().fromJson(response,CxjBean.class);
                                    list.addAll(cxjBean.getData());
                                    if (null == adapter){
                                        adapter = new DaswAdapter(R.layout.item_dashangwu,list);
                                        recyclerView.setAdapter(adapter);
                                    }else {
                                        adapter.setNewData(list);
                                    }
                                    adapter.setOnDsClickListener(new DaswAdapter.OnDsClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            matId = list.get(pos).getMatid();
                                            matIdShow = list.get(pos).getMatidshow();
                                            imagUrl = list.get(pos).getImgurlcompressshow();
                                            showCustomDialog(pos);
                                        }
                                    });
                                    if (cxjBean.getData().size() < pageSize){
                                        recyclerView.setHasLoadMore(false);
                                    }else{
                                        recyclerView.setHasLoadMore(true);
                                    }
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

    private void showCustomDialog(int pos) {
        num = 1;
        dialog = new MyCustomDialog(mContext,1,list.get(pos).getNum() == null ? 1 :Integer.parseInt(list.get(pos).getNum()));
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = dialog.getCount();
                shareTheTicket();
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void shareTheTicket() {
        new ShareAction(this)
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .addButton("umeng_sharebutton_custom","umeng_sharebutton_custom","kehuquan_icon","kehuquan_icon")
                .setShareboardclickCallback(shareBoardlistener)
                .open();

    }
    private ShareBoardlistener shareBoardlistener = new  ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            //UMImage umImage = new UMImage(this,);
            if (share_media==null){
                //根据key来区分自定义按钮的类型，并进行对应的操作
                if (snsPlatform.mKeyword.equals("umeng_sharebutton_custom")){
                    Intent intent = new Intent(mContext, CustomActivity.class);
                    intent.putExtra("type","13");
                    startActivityForResult(intent,1);
                }

            }
            else {//社交平台的分享行为
                shareToMember(null,share_media);
            }
        }
    };

    @Override
    public void configViews() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data){
            if (requestCode == 1){
                //showToast(data.getStringExtra("mobile"));
                shareToMember(data.getStringExtra("mobile"),null);
            }
        }
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void shareToMember(final String mobile,final SHARE_MEDIA share_media) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("tomembermobile",mobile);
                object.put("awardtype","SHOPAWARDTYPE002");
                object.put("awardticketmatid",matId);
                object.put("awardnum",num);
                object.put("validdays","30");
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_AWARD))
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
                                    if (share_media != null && mobile == null){
                                       CommonUtils.shareToWeiXin(mContext,ZwActivity.this,share_media,check.getData().toString());
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
