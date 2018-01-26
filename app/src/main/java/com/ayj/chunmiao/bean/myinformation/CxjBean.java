package com.ayj.chunmiao.bean.myinformation;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/31.
 */

public class CxjBean {

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"matid":"66","shopid":"001","snid":"20170729134500000367","num":"1","ordertype":"MEMBERORDERTYPE032","saleunitid":"2016110440847","ordertypeshow":"促销商品商城","onlinetype":"ONLINETYPE001","matidshow":"佑乐元高铁低聚果糖固体饮料","standard":"500","shopidshow":null,"saleunitidshow":"盒","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * matid : 66
     * shopid : 001
     * snid : 20170729134500000367
     * num : 1
     * ordertype : MEMBERORDERTYPE032
     * saleunitid : 2016110440847
     * ordertypeshow : 促销商品商城
     * onlinetype : ONLINETYPE001
     * matidshow : 佑乐元高铁低聚果糖固体饮料
     * standard : 500
     * shopidshow : null
     * saleunitidshow : 盒
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
        private String matid;
        private String shopid;
        private String snid;
        private String num;
        private String ordertype;
        private String saleunitid;
        private String ordertypeshow;
        private String onlinetype;
        private String onlinetypeshow;
        private String matidshow;
        private String standard;
        private Object shopidshow;
        private String saleunitidshow;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getMatid() {
            return matid;
        }

        public void setMatid(String matid) {
            this.matid = matid;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getSaleunitid() {
            return saleunitid;
        }

        public void setSaleunitid(String saleunitid) {
            this.saleunitid = saleunitid;
        }

        public String getOrdertypeshow() {
            return ordertypeshow;
        }

        public void setOrdertypeshow(String ordertypeshow) {
            this.ordertypeshow = ordertypeshow;
        }

        public String getOnlinetype() {
            return onlinetype;
        }

        public void setOnlinetype(String onlinetype) {
            this.onlinetype = onlinetype;
        }
        public String getOnlinetypeshow() {
            return onlinetypeshow;
        }

        public void setOnlinetypeshow(String onlinetypeshow) {
            this.onlinetypeshow = onlinetypeshow;
        }

        public String getMatidshow() {
            return matidshow;
        }

        public void setMatidshow(String matidshow) {
            this.matidshow = matidshow;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public Object getShopidshow() {
            return shopidshow;
        }

        public void setShopidshow(Object shopidshow) {
            this.shopidshow = shopidshow;
        }

        public String getSaleunitidshow() {
            return saleunitidshow;
        }

        public void setSaleunitidshow(String saleunitidshow) {
            this.saleunitidshow = saleunitidshow;
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
