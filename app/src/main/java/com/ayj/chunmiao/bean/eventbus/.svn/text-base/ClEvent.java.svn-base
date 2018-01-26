package com.ayj.chunmiao.bean.eventbus;

import com.ayj.chunmiao.bean.cmbz.VehicleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/29.
 * 车辆传递的信息
 */
public class ClEvent {
    List<VehicleBean.DataBean> vehicleInfoList = new ArrayList<>();

    private String mMsg;

    public List<VehicleBean.DataBean> getVehicleInfoList() {
        return vehicleInfoList;
    }

    public void setVehicleInfoList(List<VehicleBean.DataBean> vehicleInfoList) {
        this.vehicleInfoList = vehicleInfoList;
    }

    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public ClEvent(List<VehicleBean.DataBean> vehicleInfoList, String mMsg) {
        this.vehicleInfoList = vehicleInfoList;
        this.mMsg = mMsg;
    }
}
