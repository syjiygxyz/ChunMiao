package com.ayj.chunmiao.adapter.cg;

import android.view.View;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdClBean;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class XhjAdapter extends BaseQuickAdapter<Shoppingmall.DataBean, BaseViewHolder> {

    AddButtonOnClickListener onAddButtonClickListener;

    public XhjAdapter(int layout,List<Shoppingmall.DataBean> lists) {
        super(layout, lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, Shoppingmall.DataBean item) {
        helper.setText(R.id.tv_content, item.getMatidshow());
        helper.setText(R.id.tv_price, "￥" + item.getShoppurchaseprice());
        if (null == item.getTotalnum()) {
            helper.setText(R.id.tv_num, "存量0");
        }
        helper.setText(R.id.tv_num, "存量" + item.getTotalnum());
        if ("".equals(item.getLogoimgurlcompressshow())) {
            helper.setImageResource(R.id.iv_foot, R.mipmap.jiazaishibia);
        } else {
            Picasso.with(mContext).load(item.getLogoimgurlcompressshow()).placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView) helper.getView(R.id.iv_foot));

        }
        helper.getView(R.id.iv_add).setTag(helper.getLayoutPosition());
        helper.getView(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClickListener.onAddButtonClickListener(v, (Integer) v.getTag());
            }
        });
    }
        public void setAddButtonOnClickListener (AddButtonOnClickListener onAddButtonClickListener){
                this.onAddButtonClickListener = onAddButtonClickListener;
    }
        public interface AddButtonOnClickListener {
            //点击事件
            void onAddButtonClickListener(View view, int position);
        }



        /*helper.setText(R.id.tv_name, item.getMatidshow());
        helper.setText(R.id.tv_cl, item.getNum());
        helper.setText(R.id.tv_dw, item.getSaleunitidshow());
        helper.setText(R.id.tv_gg, item.getStandard());
        if (helper.getPosition() % 2 == 0) {
            *//*偶数*//*
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.main_dialog));
        } else {
            *//*基数*//*
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.white));

        }*/

}

