package com.ayj.chunmiao.bean.myinformation;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/28.
 */

public class StockDetailBean {

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"matid":"WL-20161206-0000682","snid":"20170316090128000217","shoppurchaseprice":"0.01","num":"2","subtotalmoney":"0.02","detailsnid":"99","saleunitid":"2015081010341","matidshow":"艾灸/针灸调理耗材","fhsubtotalmoney":"0.02","fhnum":"2","shsubtotalmoney":"0.02","shnum":"2","saleunitidshow":"套","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * matid : WL-20161206-0000682
     * snid : 20170316090128000217
     * shoppurchaseprice : 0.01
     * num : 2
     * subtotalmoney : 0.02
     * detailsnid : 99
     * saleunitid : 2015081010341
     * matidshow : 艾灸/针灸调理耗材
     * fhsubtotalmoney : 0.02
     * fhnum : 2
     * shsubtotalmoney : 0.02
     * shnum : 2
     * saleunitidshow : 套
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
        private String snid;
        private String shoppurchaseprice;
        private String num;
        private String subtotalmoney;
        private String detailsnid;
        private String saleunitid;
        private String matidshow;
        private String fhsubtotalmoney;
        private String fhnum;
        private String shsubtotalmoney;
        private String shnum;
        private String saleunitidshow;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getSuppliershopidshow() {
            return suppliershopidshow;
        }

        public void setSuppliershopidshow(String suppliershopidshow) {
            this.suppliershopidshow = suppliershopidshow;
        }

        private String suppliershopidshow;

        public String getMatid() {
            return matid;
        }

        public void setMatid(String matid) {
            this.matid = matid;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getShoppurchaseprice() {
            return shoppurchaseprice;
        }

        public void setShoppurchaseprice(String shoppurchaseprice) {
            this.shoppurchaseprice = shoppurchaseprice;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getSubtotalmoney() {
            return subtotalmoney;
        }

        public void setSubtotalmoney(String subtotalmoney) {
            this.subtotalmoney = subtotalmoney;
        }

        public String getDetailsnid() {
            return detailsnid;
        }

        public void setDetailsnid(String detailsnid) {
            this.detailsnid = detailsnid;
        }

        public String getSaleunitid() {
            return saleunitid;
        }

        public void setSaleunitid(String saleunitid) {
            this.saleunitid = saleunitid;
        }

        public String getMatidshow() {
            return matidshow;
        }

        public void setMatidshow(String matidshow) {
            this.matidshow = matidshow;
        }

        public String getFhsubtotalmoney() {
            return fhsubtotalmoney;
        }

        public void setFhsubtotalmoney(String fhsubtotalmoney) {
            this.fhsubtotalmoney = fhsubtotalmoney;
        }

        public String getFhnum() {
            return fhnum;
        }

        public void setFhnum(String fhnum) {
            this.fhnum = fhnum;
        }

        public String getShsubtotalmoney() {
            return shsubtotalmoney;
        }

        public void setShsubtotalmoney(String shsubtotalmoney) {
            this.shsubtotalmoney = shsubtotalmoney;
        }

        public String getShnum() {
            return shnum;
        }

        public void setShnum(String shnum) {
            this.shnum = shnum;
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
