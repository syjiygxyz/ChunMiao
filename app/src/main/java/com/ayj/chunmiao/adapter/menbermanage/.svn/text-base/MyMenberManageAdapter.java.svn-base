package com.ayj.chunmiao.adapter.menbermanage;

import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MyStaff;
import com.ayj.chunmiao.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/26.
 * 成员管理适配器
 */
public class MyMenberManageAdapter extends BaseQuickAdapter<MyStaff.DataBean,BaseViewHolder> {

    public MyMenberManageAdapter(int layoutResId, List<MyStaff.DataBean> lists) {
        super(layoutResId, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyStaff.DataBean item) {
        helper.setText(R.id.tv,item.getName());
        if("".equals(item.getHeadimgurlshow())){
            helper.setImageResource(R.id.iv,R.mipmap.menber_loading);
        }else{
            Picasso.with(mContext).load(item.getHeadimgurlshow()).placeholder(R.mipmap.menber_loading).error(R.mipmap.menber_error).transform(new CircleTransform()).into(
                    (ImageView) helper.getView(R.id.iv));
        }

    }
}
