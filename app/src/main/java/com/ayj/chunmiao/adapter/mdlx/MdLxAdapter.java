package com.ayj.chunmiao.adapter.mdlx;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/1.
 */

public class MdLxAdapter extends BaseQuickAdapter<Shoppingmall.DataBean,BaseViewHolder> {

    RecyclerOnItemClickListener addClickListener;

    public MdLxAdapter(int layoutResId, List<Shoppingmall.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Shoppingmall.DataBean item) {
        helper.setText(R.id.tv_lxsc_content,item.getMatidshow());
        helper.setText(R.id.tv_lxsc_price,"￥"+item.getShoppurchaseprice());
        helper.getView(R.id.iv_add_shopcart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClickListener.addClickListener(helper.getLayoutPosition());
            }
        });
        helper.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClickListener.onItemClickListener(helper.getLayoutPosition());
            }
        });
        if (null == item.getLogoimgurlshow() || TextUtils.isEmpty(item.getLogoimgurlshow())){
            helper.setImageResource(R.id.iv_lxsc_img,R.mipmap.jiazaishibia);
        }else{
            Picasso.with(mContext).load(item.getLogoimgurlshow())
                    .placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView) helper.getView(R.id.iv_lxsc_img));
        }
    }

    public void setAddClickListener(RecyclerOnItemClickListener addClickListener){
        this.addClickListener = addClickListener;
    }
    /*图片点击事件回调接口*/
    public interface RecyclerOnItemClickListener{
        void addClickListener(int position);
        void onItemClickListener(int position);
    }
}
