package com.ayj.chunmiao.adapter.cmbz.bx;

import android.view.View;
import android.widget.CheckBox;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.VehicleBean;

import java.util.List;

/**
 * 车辆信息Adapter
 */

public class VehicleInfoAdapter extends BaseQuickAdapter<VehicleBean.DataBean,BaseViewHolder> {

    private View.OnClickListener xgOnClickListener;

    private View.OnClickListener scOnClickListener;

    private View.OnClickListener checkOnClickListener;

    String type;

    public VehicleInfoAdapter(String type,List<VehicleBean.DataBean> data,View.OnClickListener xgOnClickListener, View.OnClickListener scOnClickListener,View.OnClickListener checkOnClickListener){
        super(R.layout.item_vehicle_info,data);
        this.xgOnClickListener = xgOnClickListener;
        this.scOnClickListener = scOnClickListener;
        this.checkOnClickListener = checkOnClickListener;
        this.type = type;
    }
    @Override
    protected void convert(BaseViewHolder helper, VehicleBean.DataBean item) {
        helper.setText(R.id.vehicle_brand_value,item.getCarbrandshow()+item.getCarlines());
        helper.setText(R.id.vehicle_idnumber_value,item.getIdcard());
        helper.setText(R.id.vehicle_mobile_value,item.getCarno());
        helper.setText(R.id.vehicle_motor_value,item.getEngineno());
        helper.setText(R.id.vehicle_name_value,item.getName());
        helper.setText(R.id.vehicle_recognition_value,item.getVin());
        helper.setText(R.id.vehicle_carnb,"车辆"+item.getSnid());
        helper.setText(R.id.tv_phone,"车主手机号:"+item.getMobile());
        //修改
        if (null != scOnClickListener) {
            helper.getView(R.id.iv_xg).setTag(item.getSnid());
            helper.getView(R.id.iv_xg).setOnClickListener(scOnClickListener);
        }
        //删除
        if (null != xgOnClickListener) {
            helper.getView(R.id.iv_sc).setTag(item);
            helper.getView(R.id.iv_sc).setOnClickListener(xgOnClickListener);
        }
        if("0".equals(type)){
            helper.setVisible(R.id.cb,true);
            helper.getView(R.id.cb).setTag(helper.getPosition());
            helper.getView(R.id.cb).setOnClickListener(checkOnClickListener);
            CheckBox cb = helper.getView(R.id.cb);
            if(item.isnoCheck()){
                cb.setChecked(true);
            }else{
                cb.setChecked(false);
            }
        }else{
            helper.setVisible(R.id.cb,false);
        }
    }








   /* private List<VehicleBean.DataBean> list;
    LayoutInflater inflater;
    Context context;
    private Handler mHandler;

    public VehicleInfoAdapter(Context context,Handler mHandler) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.mHandler = mHandler;
    }
    public void setList (List<VehicleBean.DataBean> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_vehicle_info,null);
            viewHolder = new ViewHolder();
            viewHolder.vehicle_name = (TextView) convertView.findViewById(R.id.vehicle_name_value);
            viewHolder.vehicle_carnb = (TextView) convertView.findViewById(R.id.vehicle_carnb);
            viewHolder.vehicle_brand = (TextView) convertView.findViewById(R.id.vehicle_brand_value);
            viewHolder.vehicle_motor = (TextView) convertView.findViewById(R.id.vehicle_motor_value);
            viewHolder.vehicle_recognition = (TextView) convertView.findViewById(R.id.vehicle_recognition_value);
            viewHolder.vehicle_mobile = (TextView) convertView.findViewById(R.id.vehicle_mobile_value);
            viewHolder.vehicle_idnumber = (TextView) convertView.findViewById(R.id.vehicle_idnumber_value);

            convertView.setTag(viewHolder);
        }else{
               viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.vehicle_carnb.setText(list.get(position).getCarno());
        viewHolder.vehicle_name.setText(list.get(position).getName());
        viewHolder.vehicle_brand.setText(list.get(position).getCarbrand());
        viewHolder.vehicle_idnumber.setText(list.get(position).getIdcard());
        viewHolder.vehicle_motor.setText(list.get(position).getEngineno());
        viewHolder.vehicle_mobile.setText(list.get(position).getMobile());
        viewHolder.vehicle_recognition.setText(list.get(position).getVin());
        return convertView;
    }
    class ViewHolder{
        private TextView vehicle_carnb,
                vehicle_name,
                vehicle_brand,
                vehicle_motor,
                vehicle_recognition,
                vehicle_mobile,
                vehicle_idnumber;
    }*/
}
