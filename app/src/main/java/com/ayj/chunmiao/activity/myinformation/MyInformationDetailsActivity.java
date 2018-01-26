package com.ayj.chunmiao.activity.myinformation;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.UserBean;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.permission.InvokeListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 个人资料
* */
public class MyInformationDetailsActivity extends BaseActivity{


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv)
    ImageView mIv;//头像
    @BindView(R.id.tv_sexy)
    TextView mTvSexy;//性别
    @BindView(R.id.tv_date)
    TextView mTvDate;//日期
    @BindView(R.id.tv_phone)
    TextView mTvPhone;//手机号
    @BindView(R.id.tv_sm)
    TextView mTvSm;//实名认证
    @BindView(R.id.tv_address)
    TextView mTvAddress;//邮箱
    @BindView(R.id.tv_card)
    TextView mTvCard;//银行卡

    UserBean.DataBean user = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_information_details;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("个人资料");
        user = AyjSwApplication.getsInstance().getUserInfo().getData().get(0);
    }

    @Override
    public void configViews() {
        if ("".equals(user.getHeadimgurlshow())) {
            mIv.setImageResource(R.mipmap.small_loading_head);
        } else {
            Picasso.with(mContext).load(user.getHeadimgurlshow()).placeholder(R.mipmap.small_loading_head).error(
                    R.mipmap.small_loading_head).into(
                    mIv);
        }
        mTvSexy.setText(user.getSexshow());
        if(null==user.getBirthday()){
            mTvDate.setText("未填写");
        }else{
            mTvDate.setText(user.getBirthday()+"");
        }
        if(null==user.getTel()){
            mTvPhone.setText("未填写");
        }else{
            String str = user.getMobile()+"";
            mTvPhone.setText(str.substring(0,3)+"****"+str.substring(7,str.length()));
        }
        if(null==user.getName()){
            mTvSm.setText("未填写");
        }else{
            mTvSm.setText("*"+user.getName().substring(1,user.getName().length()));
        }
        if(null==user.getIpaddress()){
            mTvAddress.setText("未填写");
        }else{
            String str = user.getIpaddress()+"";
            mTvAddress.setText(str.substring(0,2)+"****"+str.substring(6,str.length()));
        }
        if(null==user.getBankcard()){
            mTvCard.setText("未填写");
        }else{
            String str = user.getBankcard()+"";
            mTvCard.setText("**** **** **** "+str.substring(str.length()-4,str.length()));
        }
    }



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


}
