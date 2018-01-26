package com.ayj.chunmiao.adapter.khq;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class KhqHeadDetailAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public KhqHeadDetailAdapter(int layout, List<String>data) {
        super(layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Picasso.with(mContext).load(item).placeholder(R.mipmap.banner_loading).error(R.mipmap.banner_error).into(
                (ImageView) helper.getView(R.id.iv));
    }
}
