package com.ayj.chunmiao.adapter.cmbz.bx;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.cmbz.InsuranceBrokerBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zht-pc-04 on 2017/7/19.
 */

public class InsuranceBrokerAdapater extends RecyclerView.Adapter<InsuranceBrokerAdapater.ViewHolder>implements View.OnClickListener {

    private Map<Integer, Boolean> map = new HashMap<>();//存储checkBox状态
    private List<InsuranceBrokerBean.DataBean> data;
    private Context mContext;
    private RecyclerViewOnItemClickListener onItemClickListener;


    public InsuranceBrokerAdapater(List<InsuranceBrokerBean.DataBean> data, Context context) {
        this.data = data;
        this.mContext = context;
        initMap(data);
    }
    private void initMap(List<InsuranceBrokerBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            map.put(i, false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_insurance_broker,parent,false);
        ViewHolder viewHolder = new ViewHolder(root);
        root.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.title.setText(data.get(position).getName());
        holder.root.setTag(position);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                map.put(position,isChecked);
            }
        });
        if(map.get(position) == null){
            map.put(position,false);
        }
        holder.checkBox.setChecked(map.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setSelectItem(int position) {
        //对当前状态取反
        if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        notifyItemChanged(position);
    }

    public Map<Integer, Boolean> getMap() {
        return map;
    }
    /*设置item监听*/
    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClickListener(v,(Integer) v.getTag());
    }

    //接口回调设置点击事件
    public interface RecyclerViewOnItemClickListener {
        //点击事件
        void onItemClickListener(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private CheckBox checkBox;
        private View root;

        public ViewHolder(View root) {
            super(root);
            this.root = root;
            title = (TextView) root.findViewById(R.id.tv_company_name);
            checkBox = (CheckBox) root.findViewById(R.id.cb_broker);
        }
    }
}
