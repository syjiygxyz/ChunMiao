package com.ayj.chunmiao.activity.myinformation.lxshh;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.mdlx.lxsb.LxsbActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.myinformation.LxSbListAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.myinformation.DeclarationBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

/**联销审核**/
public class LxshActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_total_no)
    TextView tvTotalNo;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;

    private List<DeclarationBean.DataBean> list = new ArrayList<>();

    LxSbListAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lxsh;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("联销审核");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }

    @Override
    public void configViews() {
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                list.clear();
                getList();
            }
        });
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).getAuditstatus().equals(Constant.SB_FB_BTG)){
                    CommonUtils.getConfirmDialog(mContext, "失败原因", list.get(position).getAuditcomment().toString(), "重申", "取消", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            //startActivity(new Intent(mContext, LxsbActivity.class));
                            LxsbActivity.startActivity(mContext,"2");
                        }
                    }).show();
                }
                if (list.get(position).getAuditstatus().equals(Constant.SB_FB_TG) /*&& null != list.get(position).getDeposit()*/){
                    Intent intent = new Intent(mContext, LxHtActivity.class);
                    intent.putExtra("deposit",list.get(position).getDeposit());
                    intent.putExtra("snid",list.get(position).getSnid());
                    startActivity(intent);
                }
                if (list.get(position).getAuditstatus().equals(Constant.SB_FB_2BG)){
                    CommonUtils.getConfirmDialog(mContext, "失败原因", list.get(position).getAuditcomment().toString(), "重申", "取消", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            startActivity(new Intent(mContext, LxHtActivity.class));
                        }
                    }).show();
                }
                if (list.get(position).getAuditstatus().equals(Constant.SB_FB_DZF)){
                    Intent intent = new Intent(mContext, BzjZfActivity.class);
                    intent.putExtra("deposit",list.get(position).getDeposit());
                    intent.putExtra("snid",list.get(position).getSnid());
                    startActivity(intent);
                }
            }
        });
        getList();
    }

    private void getList() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.LXSB_LIST))
                    .addParams("json",String.valueOf(object))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    DeclarationBean declarationBean = new Gson().fromJson(response,DeclarationBean.class);
                                    /*for(int i=0; i<declarationBean.getData().size(); i++){
                                        if (declarationBean.getData().get(i).getAuditstatus().equals(Constant.SB_FB_SHZ)||declarationBean.getData().get(i).getAuditstatus().equals(Constant.SB_FB_TG)||declarationBean.getData().get(i).getAuditstatus().equals(Constant.SB_FB_BTG)){
                                            list.add(declarationBean.getData().get(i));
                                        }
                                    }*/
                                    list.addAll(declarationBean.getData());
                                    tvTotalNo.setText("我的联销申请("+list.size()+")");
                                    if (null == adapter){
                                        adapter = new LxSbListAdapter(R.layout.item_lx_sh,list);
                                        recyclerView.setAdapter(adapter);
                                    }else{
                                        adapter.setNewData(list);
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

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

}
