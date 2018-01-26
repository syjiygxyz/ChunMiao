package com.ayj.chunmiao.adapter.cmbz.bx;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.cmbz.InsurancePlanBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zht-pc-04 on 2017/7/20.
 */

public class InsurancePlanAdapater extends RecyclerView.Adapter<InsurancePlanAdapater.ViewHolder> implements View.OnClickListener{
    private List<InsurancePlanBean.DataBean> data ;
    private Map<Integer, Boolean> map = new HashMap<>();//存储checkBox状态
    private Context mContext;
    private InsuranceDetailAdapter insuranceDetailAdapter;
    private InsurancePlanAdapater.RecyclerViewOnItemClickListener onItemClickListener;



    public InsurancePlanAdapater (List<InsurancePlanBean.DataBean> data, Context context){
        this.data = data;
        this.mContext = context;
        initMap(data);
    }
    private void initMap(List<InsurancePlanBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            map.put(i, false);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_insurance_plan_main,parent,false);
        InsurancePlanAdapater.ViewHolder viewHolder = new InsurancePlanAdapater.ViewHolder(root);
        root.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getEcomment());
        holder.root.setTag(position);
       /* holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                map.put(position,isChecked);
            }
        });*/
        if(map.get(position) == null){
            map.put(position,false);
        }
        holder.checkBox.setChecked(map.get(position));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        insuranceDetailAdapter = new InsuranceDetailAdapter(data.get(position).getContentList());
        holder.recyclerView.setAdapter(insuranceDetailAdapter);
        if(holder.checkBox.isChecked()){
            holder.recyclerView.setVisibility(View.VISIBLE);
        }else{
            holder.recyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClickListener(v,(Integer) v.getTag());
    }
    public void setRecyclerViewOnItemClickListener(InsurancePlanAdapater.RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSelectItem(int position,int length) {
        //对当前状态取反
        if(map.get(position)){
            for(int i=0 ; i<length ; i++){
                map.put(i,false);
            }
        }else {
            for (int i = 0; i < data.size(); i++) {
                map.put(i, false);
            }
            map.put(position,true);
        }
        notifyDataSetChanged();

        /*if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        notifyItemChanged(position);*/
    }

    public interface RecyclerViewOnItemClickListener {
        //点击事件
        void onItemClickListener(View view, int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private CheckBox checkBox;
        private View root;
        private RecyclerView recyclerView;

        public ViewHolder(View root) {
            super(root);
            this.root = root;
            title = (TextView) root.findViewById(R.id.tv_plan);
            checkBox = (CheckBox) root.findViewById(R.id.cb_plan);
            recyclerView = (RecyclerView)root.findViewById(R.id.rlv_detail);
        }
    }
}
