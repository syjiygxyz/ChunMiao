package com.ayj.chunmiao.adapter.sb;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.Equipment;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/2/8.
 */
public class EquipmentGridViewAdapter extends BaseAdapter {
   //1.艾灸。2.超声。3.汗蒸。4.血糖。5.血压。6.健康秤
    int count;
    private List<Equipment.DataBean> equipments;
    Context context;
    String type;

    public EquipmentGridViewAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }


    public EquipmentGridViewAdapter(Context context, int count, List<Equipment.DataBean> equipments,String type) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.count = count;
        this.equipments = equipments;
        this.type = type;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_equipment, null);
            holder = new ViewHolder();

            holder.lr = (LinearLayout) convertView.findViewById(R.id.lr);
            holder.iv_equipment = (ImageView) convertView.findViewById(R.id.iv_equipment);
            if(type.equals("107")){
                holder.iv_equipment.setImageResource(R.mipmap.sb_one);
            }else if(type.equals("106")){
                holder.iv_equipment.setImageResource(R.mipmap.sb_two);
            }else if(type.equals("102")){
                holder.iv_equipment.setImageResource(R.mipmap.sb_three);
            }else if(type.equals("104")){
                holder.iv_equipment.setImageResource(R.mipmap.sb_four);
            }else if(type.equals("105")){
                holder.iv_equipment.setImageResource(R.mipmap.sb_five);
            }else if(type.equals("103")){
                holder.iv_equipment.setImageResource(R.mipmap.sb_six);
            }
            holder.iv_equipment_status = (ImageView) convertView.findViewById(R.id.iv_equipment_status);
            holder.tv_equipment = (TextView) convertView.findViewById(R.id.tv_equipment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_equipment.setText(equipments.get(position).getAlias());
        if (equipments.get(position).getWorkstatusshow().equals("工作中")){
            holder.iv_equipment_status.setImageResource(R.mipmap.gzzsb);
        }else {
            holder.iv_equipment_status.setImageResource(R.mipmap.kxzsb);
        }

        return convertView;
    }
    class ViewHolder {
        private ImageView iv_equipment,iv_equipment_status;
        private TextView tv_equipment;
        private LinearLayout lr;
    }
}
