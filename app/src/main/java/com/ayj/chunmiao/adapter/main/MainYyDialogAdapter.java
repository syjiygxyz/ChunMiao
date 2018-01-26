package com.ayj.chunmiao.adapter.main;

import android.util.Log;
import android.view.View;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.YyQdBean;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 * 首页预约dialog适配器
 */
public class MainYyDialogAdapter extends BaseQuickAdapter<YyQdBean.DataBean,BaseViewHolder> {

    private View.OnClickListener qdOnClickListener;

    public MainYyDialogAdapter(int layoutResId, List<YyQdBean.DataBean> lists,View.OnClickListener qdOnClickListener) {
        super(layoutResId, lists);
        this.qdOnClickListener = qdOnClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, YyQdBean.DataBean item) {
        String d = item.getCreatetime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdff = new SimpleDateFormat("HH:mm");
        Date showD = null;
        try {
            showD = sdf.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String s = sdff.format(showD);
        helper.setText(R.id.tv_dh,s);
        helper.setText(R.id.tv_name, item.getMsnidshow());
        if (helper.getPosition() % 2 == 0) {
            /*偶数*/
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.white));
        } else {
            /*基数*/
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.main_dialog));
        }
        helper.setVisible(R.id.tv_qd, true);
        helper.getView(R.id.tv_qd).setTag(item);
        helper.getView(R.id.tv_qd).setOnClickListener(qdOnClickListener);

    }
}
