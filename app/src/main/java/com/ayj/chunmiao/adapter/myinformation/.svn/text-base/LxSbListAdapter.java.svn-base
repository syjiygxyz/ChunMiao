package com.ayj.chunmiao.adapter.myinformation;

import android.graphics.Color;
import android.view.View;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.DeclarationBean;
import com.ayj.chunmiao.utils.Constant;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/14.
 */

public class LxSbListAdapter extends BaseQuickAdapter<DeclarationBean.DataBean,BaseViewHolder> {
    public LxSbListAdapter(int layoutResId, List<DeclarationBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeclarationBean.DataBean item) {
        helper.setText(R.id.tv_name,item.getMatname());
        if (null != item.getCtime()){
            helper.setText(R.id.tv_date,item.getCtime().toString());
        }


        if (Constant.SB_FB_TG.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.iv_state,R.mipmap.lxsh_suc);
            helper.setTextColor(R.id.tv_state, Color.parseColor("#2c9a15"));
            helper.setText(R.id.tv_state,"初审通过");
        }
        if (Constant.SB_FB_SHZ.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.tv_state,Color.parseColor("#fe8c02"));
            helper.setText(R.id.tv_state,"待初审");
        }
        if (Constant.SB_FB_BTG.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.iv_state,R.mipmap.lxsh_fail);
            helper.setTextColor(R.id.tv_state,Color.parseColor("#FD2E2E"));
            helper.setText(R.id.tv_state,"初审不过");
        }
        if (Constant.SB_FB_DZF.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.iv_state,R.mipmap.lxsh_suc);
            helper.setTextColor(R.id.tv_state,Color.parseColor("#ff4400"));
            helper.setText(R.id.tv_state,"待支付");
        }
        if (Constant.SB_FB_D2S.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.tv_state, Color.parseColor("#fe8c02"));
            helper.setText(R.id.tv_state,"待二审");
        }
        if (Constant.SB_FB_2BG.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.iv_state,R.mipmap.lxsh_fail);
            helper.setTextColor(R.id.tv_state,Color.parseColor("#FD2E2E"));
            helper.setText(R.id.tv_state,"二审不过");
        }
        if (Constant.SB_FB_CHG.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.tv_state,Color.parseColor("#2c9a15"));
            helper.setText(R.id.tv_state,"申请成功");
        }
        if (Constant.SB_FB_TH.equals(item.getAuditstatus())){
            helper.getView(R.id.iv_state).setVisibility(View.INVISIBLE);
            helper.setTextColor(R.id.tv_state,Color.parseColor("#2c9a15"));
            helper.setText(R.id.tv_state,"已退款");
        }
    }
}
