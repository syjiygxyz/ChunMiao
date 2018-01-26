package com.ayj.chunmiao.activity.myinformation.lxd;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.myinformation.UnionShareHisBean;
import com.ayj.chunmiao.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnionShareDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_share_time)
    TextView tvShareTime;
    @BindView(R.id.tv_etime)
    TextView tvEtime;
    @BindView(R.id.tv_sharetimes)
    TextView tvSharetimes;
    @BindView(R.id.tv_xiadan_renci)
    TextView tvXiadanRenci;
    @BindView(R.id.tv_yuliang)
    TextView tvYuliang;


    private UnionShareHisBean.DataBean info;

    @Override
    public int getLayoutId() {
        return R.layout.activity_union_share_detail;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("分享详情");
        info = (UnionShareHisBean.DataBean)getIntent().getSerializableExtra("info");
        tvOrderId.setText("订单编号:"+info.getSnid());
        tvShareTime.setText("分享时间:"+info.getCreatetime());
        tvEtime.setText("截止有效期:"+info.getEtime());
        tvSharetimes.setText("分享人次:"+info.getSharenum());
        tvXiadanRenci.setText("已下单人次:"+info.getUsednum());
        tvYuliang.setText("余量:");
    }

    @Override
    public void configViews() {
        getinfo();
    }

    private void getinfo() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            //mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
