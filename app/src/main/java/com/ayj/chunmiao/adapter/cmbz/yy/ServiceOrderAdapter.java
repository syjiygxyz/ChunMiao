package com.ayj.chunmiao.adapter.cmbz.yy;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;

/**
 * Created by Administrator on 2017/8/5.
 */
public class ServiceOrderAdapter extends BaseAdapter {
    private String matid;
    private int[] nums;
    private Integer[] nums_left;
    private Handler mHandler;
    LayoutInflater inflater;
    Context context;

    private boolean[] checked;

    public ServiceOrderAdapter(Context context) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setList(String matid,int[] nums,Integer[] nums_left,Handler mHandler) {
        this.matid = matid;
        this.nums = nums;
        this.nums_left = nums_left;
        this.mHandler = mHandler;
        checked  = new boolean[nums.length];

    }
    @Override
    public int getCount() {
        return matid == null ? 0 : matid.split(",").length;
    }

    @Override
    public Object getItem(int i) {
        return matid.split(",")[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            if(position == 0 || position%2 == 0) {
                convertView = inflater.inflate(R.layout.item_service_order, null);
            } else  {
                convertView = inflater.inflate(R.layout.item_service_order2, null);
            }
            holder = new ViewHolder();
            holder.iv_service_order = (ImageView) convertView.findViewById(R.id.iv_service_order);
            holder.tv_service_order_hascount = (TextView)convertView.findViewById(R.id.tv_service_order_hascount);
            holder.tv_service_order_sfcount = (TextView)convertView.findViewById(R.id.tv_service_order_sfcount);
            holder.cb_service_order = (CheckBox) convertView.findViewById(R.id.cb_service_order);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_service_order_sfcount.setText(nums[position]+"");

        if("WL-20151126-0000088".equals(matid.split(",")[position])||"100".equals(matid.split(",")[position])||"101".equals(matid.split(",")[position])){
            holder.iv_service_order.setBackgroundResource(R.mipmap.fuwuquanjiu);
        } else if("WL-20151126-0000089".equals(matid.split(",")[position])){
            holder.iv_service_order.setBackgroundResource(R.mipmap.fuwuquansheng);
        } else if("WL-20151126-0000094".equals(matid.split(",")[position])){
            holder.iv_service_order.setBackgroundResource(R.mipmap.fuwuquanhan);
        } else if("WL-20151126-0000095".equals(matid.split(",")[position])){
            holder.iv_service_order.setBackgroundResource(R.mipmap.fuwuquanba);
        } else if("WL-20161205-0000679".equals(matid.split(",")[position])){
            holder.iv_service_order.setBackgroundResource(R.mipmap.fuwuquanshui);
        }else if("93".equals(matid.split(",")[position])){
            holder.iv_service_order.setBackgroundResource(R.mipmap.fuwuquanpin);
        }

        holder.tv_service_order_hascount.setText(nums_left[position].toString());

        holder.cb_service_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (nums_left[position] == 0) {
                        Toast.makeText(context, "抱歉，您尚未拥有该券",
                                Toast.LENGTH_SHORT).show();
                        holder.cb_service_order.setChecked(false);
                        checked[position] = false;
                    }
            }
        });

        holder.cb_service_order.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                        checked[position] = b;
                        Message message = new Message();
                        message.what = 1;
                        message.obj = checked;
                        mHandler.sendMessage(message);

                    }
                });

        holder.cb_service_order.setChecked(checked[position]);

        return convertView;
    }
    class ViewHolder{

        private ImageView iv_service_order;
        private TextView tv_service_order_hascount,tv_service_order_sfcount;
        private CheckBox cb_service_order;
    }
}


