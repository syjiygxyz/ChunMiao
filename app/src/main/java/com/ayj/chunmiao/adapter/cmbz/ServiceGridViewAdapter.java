package com.ayj.chunmiao.adapter.cmbz;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.Listener.NoDoubleClickListener;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.cmbz.ServicePriceBean;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zht-pc-09 on 2016/10/25.
 */
public class ServiceGridViewAdapter extends BaseAdapter {

    List<ServicePriceBean.DataBean> services;

    Context context;
    private Handler mHandler;
    LayoutInflater inflater;
    private int[] nums;
    private int[] nums_left;
    HashMap<Integer, Boolean> state = new HashMap<Integer,Boolean>();

    private NoDoubleClickListener listener;

    public ServiceGridViewAdapter(Context context,NoDoubleClickListener listener) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    public void setList(List<ServicePriceBean.DataBean> services,int[] nums,int[] nums_left,Handler mHandler) {
        this.mHandler = mHandler;
        this.services = services;
        this.nums = nums;
        this.nums_left = nums_left;
    }

    public void setNums(int[] nums){
        this.nums = nums;
    }

    @Override
    public int getCount() {
        return services == null ? 0:services.size();
    }

    @Override
    public Object getItem(int i) {
        return services.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_service_gridview, null);
            holder = new ViewHolder();

            holder.iv_service_icon = (ImageView) convertView.findViewById(R.id.iv_service_icon);
            holder.iv_service_icon1 = (ImageView) convertView.findViewById(R.id.iv_service_icon1);
            holder.tv_service_gridview = (TextView) convertView.findViewById(R.id.tv_service_gridview);

            holder.cb_service = (CheckBox) convertView.findViewById(R.id.cb_service);
            holder.addsub_service = (MyAddAndSubView) convertView.findViewById(R.id.addsub_service);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.cb_service.setChecked(state.get(position) == null? false : true);

        if("WL-20151126-0000088".equals(services.get(position).getMatid())){
            holder.iv_service_icon.setBackgroundResource(R.mipmap.aijiuzuhez);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.yuyue_aijiu);
        } else if("WL-20151126-0000089".equals(services.get(position).getMatid())){
            holder.iv_service_icon.setBackgroundResource(R.mipmap.sbtlyy);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.yuyue_chaosheng);
        } else if("WL-20151126-0000094".equals(services.get(position).getMatid())){
            holder.iv_service_icon.setBackgroundResource(R.mipmap.fyrtli);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.yuyue_hanzhen);
        } else if("189".equals(services.get(position).getMatid())){
            holder.iv_service_icon.setBackgroundResource(R.mipmap.yinyangbyy);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.yuyue_yinyangba);
        } else if("WL-20161205-0000679".equals(services.get(position).getMatid())){
            holder.iv_service_icon.setBackgroundResource(R.mipmap.bxfrsyy);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.yuyue_bxfrs);
        }else if("93".equals(services.get(position).getMatid())) {
            holder.iv_service_icon.setBackgroundResource(R.mipmap.pinputl);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.pptl);
        }else if("100".equals(services.get(position).getMatid())){
            holder.iv_service_icon.setBackgroundResource(R.mipmap.dianzijiutl);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.dzjtl);
        } else if("101".equals(services.get(position).getMatid())){
            holder.iv_service_icon.setBackgroundResource(R.mipmap.swdtnan);
            holder.iv_service_icon1.setBackgroundResource(R.mipmap.swdtnam);
        }

        holder.iv_service_icon.setTag(position);
        holder.iv_service_icon1.setTag(position);
        holder.addsub_service.setTag(position);
        holder.tv_service_gridview.setTag(position);
        holder.iv_service_icon.setOnClickListener(listener);
        holder.iv_service_icon1.setOnClickListener(listener);
        holder.tv_service_gridview.setOnClickListener(listener);
//        holder.cb_service.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if ("WL-20151126-0000094".equals(services.get(position).getMatid())
//                        || "189".equals(services.get(position).getMatid())
//                        || "WL-20161205-0000679".equals(services.get(position).getMatid())){
//                    if (nums_left[position] == 0) {
//                        Toast.makeText(context, "抱歉，该服务只能使用券预约，您尚未拥有该券", Toast.LENGTH_SHORT).show();
//                        holder.cb_service.setChecked(false);
//                    }
//            }
//            }
//        });
        if("189".equals(services.get(position).getMatid())){
            holder.cb_service.setVisibility(View.GONE);
        }
        if(nums[position] != 0 ) {
            holder.addsub_service.setCount(nums[position]);
        }
        if(nums[position] == 0){
            holder.cb_service.setChecked(false);
            holder.addsub_service.setVisibility(View.GONE);
        } else {
            holder.cb_service.setChecked(true);
            holder.addsub_service.setVisibility(View.VISIBLE);
        }

        holder.cb_service.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if("189".equals(services.get(position).getMatid())){
                    holder.addsub_service.setVisibility(View.GONE);
                }else{
                    if(b){
                        holder.addsub_service.setVisibility(View.VISIBLE);
                        nums[position] = holder.addsub_service.getCount();
                        state.put(position, b);
                    } else {
                        holder.addsub_service.setVisibility(View.GONE);
                        nums[position] = 0;
                        state.remove(position);
                    }
                    Message message = new Message();
                    message.what = 1;
                    message.obj = nums;
                    mHandler.sendMessage(message);
                }
                }
        });

        holder.addsub_service.setOnPosNegListener(new MyAddAndSubView.OnPosNegListener() {
            @Override
            public void onMyPositiveListener(int count) {

//                if ("WL-20151126-0000094".equals(services.get(position).getMatid())
//                        || "189".equals(services.get(position).getMatid())
//                        || "WL-20161205-0000679".equals(services.get(position).getMatid())){
//                    if(count > nums_left[position]){
//                        count = nums_left[position];
//                        holder.addsub_service.setCount(count);
//                    }
//                }
                nums[position] = count;

                Message message = new Message();
                message.what = 2;
                message.obj = nums;
                mHandler.sendMessage(message);
            }

            @Override
            public void onMyNegativeListener(int count) {
//                if ("WL-20151126-0000094".equals(services.get(position).getMatid())
//                        || "189".equals(services.get(position).getMatid())
//                        || "WL-20161205-0000679".equals(services.get(position).getMatid())){
//                    if(count > nums_left[position]){
//                        count = nums_left[position];
//                        holder.addsub_service.setCount(count);
//                    }
//                }
                nums[position] = count;

                Message message = new Message();
                message.what = 2;
                message.obj = nums;
                mHandler.sendMessage(message);
            }
        });

        holder.addsub_service.setOnClickAddAndSubListener(
                new MyAddAndSubView.OnClickAddAndSubListener() {
                    @Override
                    public void clickAdd(int count) {
//                        if ("WL-20151126-0000094".equals(services.get(position).getMatid())
//                                || "189".equals(services.get(position).getMatid())
//                                || "WL-20161205-0000679".equals(services.get(position).getMatid())){
//                            if(count > nums_left[position]){
//                                count = nums_left[position];
//                                holder.addsub_service.setCount(count);
//                            }
//                        }
                        nums[position] = count;

                        Message message = new Message();
                        message.what = 2;
                        message.obj = nums;
                        mHandler.sendMessage(message);
                    }

                    @Override
                    public void clickSub(int count) {
//                        if ("WL-20151126-0000094".equals(services.get(position).getMatid())
//                                || "189".equals(services.get(position).getMatid())
//                                || "WL-20161205-0000679".equals(services.get(position).getMatid())){
//                            if(count > nums_left[position]){
//                                count = nums_left[position];
//                                holder.addsub_service.setCount(count);
//                            }
//                        }
                        nums[position] = count;
                        Message message = new Message();
                        message.what = 2;
                        message.obj = nums;
                        mHandler.sendMessage(message);
                    }
                });
        return convertView;
    }

    class ViewHolder {
        private ImageView iv_service_icon,iv_service_icon1;
        private CheckBox cb_service;
        private MyAddAndSubView addsub_service;
        private TextView tv_service_gridview;
    }
}