package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/14.
 */

public class UnitBean {

    /**
     * err : 0
     * msg : 获取销售单位成功
     * total : 10
     * data : [{"funccode":null,"cuserid":"10227","ctime":1489713007000,"muserid":null,"mtime":null,"unitid":"003","remark":null,"unitname":"瓶","delflag":"0","unittype":"T2","cusername":"周婷婷","musername":"","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"10227","ctime":1488787875000,"muserid":null,"mtime":null,"unitid":"002","remark":null,"unitname":"杯","delflag":"0","unittype":"T2","cusername":"周婷婷","musername":"","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"0000","ctime":1439184976000,"muserid":"10199","mtime":1488787243000,"unitid":"2015081010341","remark":null,"unitname":"套","delflag":"0","unittype":"T0","cusername":"超级管理员","musername":"周玉杰","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"0000","ctime":1439184986000,"muserid":"0000","mtime":1462365228000,"unitid":"2015081010342","remark":null,"unitname":"打","delflag":"0","unittype":"T0","cusername":"超级管理员","musername":"超级管理员","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"0000","ctime":1439184986000,"muserid":"0000","mtime":1462365206000,"unitid":"2015081010344","remark":null,"unitname":"箱","delflag":"0","unittype":"T0","cusername":"超级管理员","musername":"超级管理员","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"0000","ctime":1439184986000,"muserid":"0000","mtime":1462365156000,"unitid":"2015081010345","remark":null,"unitname":"件","delflag":"0","unittype":"T0","cusername":"超级管理员","musername":"超级管理员","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"0000","ctime":1439184986000,"muserid":"0000","mtime":1462365137000,"unitid":"2015081010346","remark":null,"unitname":"个","delflag":"0","unittype":"T0","cusername":"超级管理员","musername":"超级管理员","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"10165","ctime":1478244629000,"muserid":null,"mtime":null,"unitid":"2016110440847","remark":null,"unitname":"盒","delflag":"0","unittype":"T0","cusername":null,"musername":"","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"10165","ctime":1478508449000,"muserid":null,"mtime":null,"unitid":"2016110746102","remark":null,"unitname":"张","delflag":"0","unittype":"T3","cusername":null,"musername":"","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"funccode":null,"cuserid":"10165","ctime":1486450425000,"muserid":null,"mtime":null,"unitid":"2017020710322","remark":null,"unitname":"袋","delflag":"0","unittype":"T2","cusername":null,"musername":"","isexist":false,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * funccode : null
     * cuserid : 10227
     * ctime : 1489713007000
     * muserid : null
     * mtime : null
     * unitid : 003
     * remark : null
     * unitname : 瓶
     * delflag : 0
     * unittype : T2
     * cusername : 周婷婷
     * musername :
     * isexist : false
     * imgurl : null
     * imgurlshow :
     * imgurlcompressshow :
     */

    private List<DataBean> data;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private Object funccode;
        private String cuserid;
        private long ctime;
        private Object muserid;
        private Object mtime;
        private String unitid;
        private Object remark;
        private String unitname;
        private String delflag;
        private String unittype;
        private String cusername;
        private String musername;
        private boolean isexist;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public Object getFunccode() {
            return funccode;
        }

        public void setFunccode(Object funccode) {
            this.funccode = funccode;
        }

        public String getCuserid() {
            return cuserid;
        }

        public void setCuserid(String cuserid) {
            this.cuserid = cuserid;
        }

        public long getCtime() {
            return ctime;
        }

        public void setCtime(long ctime) {
            this.ctime = ctime;
        }

        public Object getMuserid() {
            return muserid;
        }

        public void setMuserid(Object muserid) {
            this.muserid = muserid;
        }

        public Object getMtime() {
            return mtime;
        }

        public void setMtime(Object mtime) {
            this.mtime = mtime;
        }

        public String getUnitid() {
            return unitid;
        }

        public void setUnitid(String unitid) {
            this.unitid = unitid;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public String getDelflag() {
            return delflag;
        }

        public void setDelflag(String delflag) {
            this.delflag = delflag;
        }

        public String getUnittype() {
            return unittype;
        }

        public void setUnittype(String unittype) {
            this.unittype = unittype;
        }

        public String getCusername() {
            return cusername;
        }

        public void setCusername(String cusername) {
            this.cusername = cusername;
        }

        public String getMusername() {
            return musername;
        }

        public void setMusername(String musername) {
            this.musername = musername;
        }

        public boolean isIsexist() {
            return isexist;
        }

        public void setIsexist(boolean isexist) {
            this.isexist = isexist;
        }

        public Object getImgurl() {
            return imgurl;
        }

        public void setImgurl(Object imgurl) {
            this.imgurl = imgurl;
        }

        public String getImgurlshow() {
            return imgurlshow;
        }

        public void setImgurlshow(String imgurlshow) {
            this.imgurlshow = imgurlshow;
        }

        public String getImgurlcompressshow() {
            return imgurlcompressshow;
        }

        public void setImgurlcompressshow(String imgurlcompressshow) {
            this.imgurlcompressshow = imgurlcompressshow;
        }
    }
}
