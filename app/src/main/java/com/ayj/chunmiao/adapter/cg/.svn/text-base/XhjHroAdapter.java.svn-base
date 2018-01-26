package com.ayj.chunmiao.adapter.cg;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubEditTextView;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zht-pc-04 on 2017/7/25.
 */

public class XhjHroAdapter extends BaseQuickAdapter<Shoppingmall.DataBean, BaseViewHolder> {

    private Map<Integer, Integer> map = new HashMap<>();//记录数量控件
    private Map<Integer, Boolean> isSelect = new HashMap<>();//记录CheckBox状态
    private int num;
    private MyAddAndSubEditTextView addandsub_shopcart;
    private CheckBox cb;
    private RecyclerOnItemClickListener onItemClickListener;

    public XhjHroAdapter(int layoutResId, List<Shoppingmall.DataBean> data) {
        super(layoutResId, data);
        initMap();
    }

    @Override
    protected void convert(final BaseViewHolder helper, Shoppingmall.DataBean item) {

        helper.setText(R.id.tv_content,item.getMatidshow());
        helper.setText(R.id.tv_price,"￥"+ CommonUtils.douFormat(item.getShoppurchaseprice()));
        if("".equals(item.getLogoimgurlcompressshow())){
            helper.setImageResource(R.id.iv_foot,R.mipmap.jiazaishibia);
        }else{
            Picasso.with(mContext).load(item.getLogoimgurlcompressshow()).placeholder(R.mipmap.zhanweitu).error(R.mipmap.jiazaishibia).into((ImageView)helper.getView(R.id.iv_foot));

        }
        addandsub_shopcart = helper.getView(R.id.addandsub_shopcart);
        cb = helper.getView(R.id.cb_xhj);
        addandsub_shopcart.setTag(helper.getLayoutPosition());
        cb.setTag(helper.getLayoutPosition());
        helper.getView(R.id.ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(helper.getLayoutPosition());
            }
        });

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSelect.put(helper.getLayoutPosition(),isChecked);
            }
        });

        addandsub_shopcart.setMAX_MARK(Integer.parseInt(item.getTotalnum()==null?"0":item.getTotalnum()));
        addandsub_shopcart.setOnAmountChangeListener(new MyAddAndSubEditTextView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int count) {
                map.put(helper.getLayoutPosition(),count);
            }
        });
        /*根据map的记录设置状态，防止错乱*/
        if (isSelect.get(helper.getLayoutPosition())==null){
            cb.setChecked(false);
            isSelect.put(helper.getLayoutPosition(),false);
            map.put(helper.getLayoutPosition(),1);
        }else {
            cb.setChecked(isSelect.get(helper.getLayoutPosition()));
        }
        addandsub_shopcart.setCount(map.get(helper.getLayoutPosition())==null?0:map.get(helper.getLayoutPosition()));
    }

    /*map初始化*/
    public void initMap() {
        isSelect.clear();
        map.clear();
        for(int i=0 ; i<mData.size() ; i++){
            isSelect.put(i,false);
            map.put(i,1);
        }
    }
    public Map getisSelectMap(){
        return isSelect;
    };
    public Map getNumMap(){
        return map;
    }
    public void setOnItemClickListener(RecyclerOnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    /*图片点击事件回调接口*/
    public interface RecyclerOnItemClickListener{
        void onItemClickListener(int position);
    }
}
