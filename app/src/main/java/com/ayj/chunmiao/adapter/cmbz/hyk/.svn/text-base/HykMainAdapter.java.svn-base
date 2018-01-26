package com.ayj.chunmiao.adapter.cmbz.hyk;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.MemberCard;
import com.ayj.chunmiao.utils.Constant;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public class HykMainAdapter extends BaseQuickAdapter<MemberCard.DataBean,BaseViewHolder> {

    public HykMainAdapter(int layout, List<MemberCard.DataBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberCard.DataBean item) {
        helper.setText(R.id.tv_package_name,item.getMatidshow());
        helper.setText(R.id.tv_package_price,"¥" + item.getNowprice());
        if("康乐A卡(福利卡)".equals(item.getMatclassshow())){
            helper.setBackgroundRes(R.id.linearLayout_package,R.mipmap.klhyak);
        } else if("康乐B卡(会员卡)".equals(item.getMatclassshow())) {
            helper.setBackgroundRes(R.id.linearLayout_package,R.mipmap.klhybka);
        }
    }
}
