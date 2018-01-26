package com.ayj.chunmiao.adapter.cmbz.yy;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayj.chunmiao.Listener.NoDoubleClickListener;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.cmbz.MemberCardService;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/2/13.
 */
public class MemberServiceAdapter extends BaseAdapter {


    private List<MemberCardService.DataBean> memberCardServices;
    LayoutInflater inflater;
    Context context;
    String mcard;

    Handler mHandler;

    private NoDoubleClickListener listener;


    public MemberServiceAdapter(Context context,NoDoubleClickListener listener) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    public void setList(List<MemberCardService.DataBean> memberCardServices,String mcard,Handler mHandler) {
        this.memberCardServices = memberCardServices;
        this.mcard = mcard;
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        return memberCardServices == null ? 0 : memberCardServices.size();
    }

    @Override
    public Object getItem(int i) {
        return memberCardServices.get(i);
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
                    R.layout.item_memberservice, null);
            holder = new ViewHolder();

            holder.tv_memberservice_name = (TextView) convertView.findViewById(R.id.tv_memberservice_name);
            holder.tv_memberservice_yy = (TextView) convertView.findViewById(R.id.tv_memberservice_yy);
            holder.tv_memberservice_no = (TextView) convertView.findViewById(R.id.tv_memberservice_no);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_memberservice_name.setText(memberCardServices.get(position).getServicematidshow());

        if(TextUtils.isEmpty(memberCardServices.get(position).getHasusenum())){
            holder.tv_memberservice_yy.setVisibility(View.VISIBLE);
            holder.tv_memberservice_no.setVisibility(View.GONE);
        } else {
            holder.tv_memberservice_yy.setVisibility(View.GONE);
            holder.tv_memberservice_no.setVisibility(View.VISIBLE);
        }

        holder.tv_memberservice_yy.setTag(R.id.tv_memberservice_yy, position);
        holder.tv_memberservice_yy.setOnClickListener(listener);

        return convertView;
    }

    class ViewHolder {

        private TextView tv_memberservice_name,tv_memberservice_yy,tv_memberservice_no;
    }
}
