package com.ayj.chunmiao.adapter.main;

import android.view.View;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.UtilityItem;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/5/24.
 * 通用的gridview适配器
 */
public class CommonGridAdapter extends BaseQuickAdapter<UtilityItem,BaseViewHolder> {

    public CommonGridAdapter(int layoutResId,List<UtilityItem> lists){
        super(layoutResId,lists);
    }

    @Override
    protected void convert(BaseViewHolder helper, UtilityItem item) {
            helper.setText(R.id.tv,item.getText());
            helper.setImageResource(R.id.iv,item.getResId());
        if (null == item.getRedPoint() && null != helper.getView(R.id.red)){
            helper.setVisible(R.id.red,false);
        }else if ("show".equals(item.getRedPoint())&& null != helper.getView(R.id.red)){
            helper.setVisible(R.id.red,true);
        }else if (null != helper.getView(R.id.red)){
            helper.setVisible(R.id.red,false);
        }

    }
}
