package com.ayj.chunmiao.adapter.myinformation;

import android.text.TextUtils;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.myinformation.NoticeBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/2.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeBean.DataBean,BaseViewHolder> {
    public NoticeAdapter(int layoutResId, List<NoticeBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean.DataBean item) {
        helper.setText(R.id.tv_title,item.getMsgtype());
        helper.setText(R.id.tv_time,item.getNoticetime()   /*new SimpleDateFormat("MM/dd HH:mm").format(Date.parse(item.()))*/);
        helper.setText(R.id.tv_content,item.getAcomment());
        if (null == item.getNoticelogo() || TextUtils.isEmpty(item.getNoticelogo())){
            helper.setImageResource(R.id.iv_notice_logo,R.mipmap.jiazaishibia);
        } else {
            Picasso.with(mContext).load(item.getNoticelogo())
                    .placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView)helper.getView(R.id.iv_notice_logo));
        }
    }
}
