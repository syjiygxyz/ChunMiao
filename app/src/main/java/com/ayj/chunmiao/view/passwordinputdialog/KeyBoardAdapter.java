package com.ayj.chunmiao.view.passwordinputdialog;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fizzer on 2017/4/18.
 * Email: doraemonmqq@sina.com
 */

public class KeyBoardAdapter extends BaseAdapter {

    List<String> list = new ArrayList<>();
    Context mContext;

    public KeyBoardAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.key_board_item_view, null);
        TextView textView = (TextView) convertView.findViewById(R.id.tvItemView);
        ImageView ivDel = (ImageView) convertView.findViewById(R.id.ivDel);
        RelativeLayout content = (RelativeLayout) convertView.findViewById(R.id.Content);
        textView.setText(list.get(position));

        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 100);
        convertView.setLayoutParams(lp);

        if (position == 9 || position == 11) {
            textView.setBackgroundColor(Color.parseColor("#D5D8DB"));
        }
        if(position == 11){
            textView.setVisibility(View.GONE);
            ivDel.setVisibility(View.VISIBLE);
            content.setBackgroundResource(R.drawable.keyboard_del_item_selector);
        }
        return convertView;
    }
}
