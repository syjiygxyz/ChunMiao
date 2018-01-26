package com.ayj.chunmiao.adapter.cmbz.yy;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.UtilityItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public class YyPostHeadAdapter extends BaseQuickAdapter<UtilityItem,BaseViewHolder> {

    public YyPostHeadAdapter(List<UtilityItem> data) {
        super(R.layout.item_head_yy_post, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UtilityItem item) {
        helper.setText(R.id.tv,item.getText());
    }
}
