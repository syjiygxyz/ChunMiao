package com.ayj.chunmiao.adapter.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.shopping.Address;

import java.util.List;

/**
 * Created by zht-pc-09 on 2016/7/30.
 */
public class DialogAddressAdapter extends BaseAdapter {
    private List<Address.DataBean> address;
    LayoutInflater inflater;
    Context context;

    public DialogAddressAdapter(Context context,List<Address.DataBean> address) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.address = address;
    }

    public void setList(List<Address.DataBean> address) {
        this.address = address;
    }
    @Override
    public int getCount() {
        return address == null ? 0 : address.size();
    }

    @Override
    public Object getItem(int i) {
        return address.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_address_dialog, null);
            holder = new ViewHolder();

            holder.tv_dialog_address_name = (TextView)convertView.findViewById(R.id.tv_dialog_address_name);
            holder.tv_dialog_address_phone = (TextView)convertView.findViewById(R.id.tv_dialog_address_phone);
            holder.tv_dialog_address_detail = (TextView)convertView.findViewById(R.id.tv_dialog_address_detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_dialog_address_name.setText(address.get(position).getReceivename());
        holder.tv_dialog_address_phone.setText(address.get(position).getMobile());
        holder.tv_dialog_address_detail.setText(address.get(position).getProvinceid()+"\t"+address.get(position).getCityid()+"\t"+address.get(position).getAreaid()+"\t"+address.get(position).getDetailaddr());
        return convertView;
    }
    class ViewHolder{
        TextView tv_dialog_address_name ; //姓名
        TextView tv_dialog_address_phone ; //联系方式
        TextView tv_dialog_address_detail ; //详细地址
    }
}
