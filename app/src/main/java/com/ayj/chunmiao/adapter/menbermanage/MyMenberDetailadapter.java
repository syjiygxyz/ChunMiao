package com.ayj.chunmiao.adapter.menbermanage;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.menbermanage.MyDyDetailsActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MyStaff;
import com.ayj.chunmiao.bean.MyYgDetailsBean;
import com.ayj.chunmiao.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/26.
 */
public class MyMenberDetailadapter extends BaseQuickAdapter<MyYgDetailsBean.DataBean,BaseViewHolder> {

    public MyMenberDetailadapter(int layoutResId, List<MyYgDetailsBean.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyYgDetailsBean.DataBean item) {
        helper.setText(R.id.tv_time,item.getServicetime());
        helper.setText(R.id.tv_type,item.getPsidshow());
    }
}
