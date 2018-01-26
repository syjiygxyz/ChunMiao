package com.ayj.chunmiao.adapter.shopping;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayj.chunmiao.R;

import java.util.List;

/**
 * Created by zht-pc-09 on 2016/8/2.
 */
public class GuigeGridViewAdapter extends BaseAdapter {
    int count;
    List<String> text;
    Context context;
    private int clickTemp = 0;

    //标识选择的Item
    public void setSeclection(int position) {
        clickTemp = position;
    }

    public GuigeGridViewAdapter(Context context, int count, List<String> text) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.count = count;
        this.text = text;
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
                    R.layout.item_guige, null);
            holder = new ViewHolder();
            holder.btn_guige_item = (TextView) convertView.findViewById(R.id.btn_guige_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.btn_guige_item.setText(text.get(position));

        if (clickTemp == position) {
            holder.btn_guige_item.setBackgroundResource(R.drawable.bg_guize_select);
            holder.btn_guige_item.setTextColor(0xffFFFFFF);
        } else {
            holder.btn_guige_item.setBackgroundResource(R.drawable.bg_guize);
            holder.btn_guige_item.setTextColor(0xff000000);
        }

        return convertView;
    }

    class ViewHolder {
        private TextView btn_guige_item;
    }
}
