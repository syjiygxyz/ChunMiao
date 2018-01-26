package com.ayj.chunmiao.adapter.khq;

import android.text.TextUtils;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.khq.ShopDetail;
import com.ayj.chunmiao.bean.khq.ShopPjBean;
import com.ayj.chunmiao.view.ratingBar.ProperRatingBar;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class FwdPlAdapter extends BaseQuickAdapter<ShopPjBean.DataBean,BaseViewHolder> {


    public FwdPlAdapter(int layout, List<ShopPjBean.DataBean> data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopPjBean.DataBean item) {
        if (TextUtils.isEmpty(item.getNickname()) || "SFPD001".equals(item.getSfnm())) {
            helper.setText(R.id.name, "匿名");
        } else {
            helper.setText(R.id.name, item.getNickname());
        }
        helper.setText(R.id.createdate, item.getCreatedate());
        helper.setText(R.id.fuwu,item.getFk());
        helper.setText(R.id.pjjl, item.getPsidshow());
        ProperRatingBar prBar = helper.getView(R.id.lowerRatingBar);
        prBar.setRating(Integer.parseInt(item.getPf()));
        helper.setText(R.id.pf,item.getPf());
    }
}
