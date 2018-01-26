package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
public class SmallmoneyFace {

    /**
     * err : 0
     * msg :
     * total : 7
     * data : [{"shopid":null,"snid":null,"createuserid":null,"facevalue":"100.00","leveltype":null,"createuseridshow":"","shopidshow":null,"createtime":null,"leveltypeshow":"VIP1","discount":"80","giftemoney":"50","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":null,"snid":null,"createuserid":null,"facevalue":"200.00","leveltype":null,"createuseridshow":"","shopidshow":null,"createtime":null,"leveltypeshow":"VIP1","discount":"80","giftemoney":"100","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":null,"snid":null,"createuserid":null,"facevalue":"400.00","leveltype":null,"createuseridshow":"","shopidshow":null,"createtime":null,"leveltypeshow":"VIP1","discount":"80","giftemoney":"200","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":null,"snid":null,"createuserid":null,"facevalue":"800.00","leveltype":null,"createuseridshow":"","shopidshow":null,"createtime":null,"leveltypeshow":"VIP2","discount":"75","giftemoney":"500","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":null,"snid":null,"createuserid":null,"facevalue":"2500.00","leveltype":null,"createuseridshow":"","shopidshow":null,"createtime":null,"leveltypeshow":"VIP3","discount":"70","giftemoney":"1800","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":null,"snid":null,"createuserid":null,"facevalue":"5000.00","leveltype":null,"createuseridshow":"","shopidshow":null,"createtime":null,"leveltypeshow":"VIP4","discount":"60","giftemoney":"4500","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":null,"snid":null,"createuserid":null,"facevalue":"20000.00","leveltype":null,"createuseridshow":"","shopidshow":null,"createtime":null,"leveltypeshow":"VIP5","discount":"50","giftemoney":"20000","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
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
        /**
         * shopid : null
         * snid : null
         * createuserid : null
         * facevalue : 100.00
         * leveltype : null
         * createuseridshow :
         * shopidshow : null
         * createtime : null
         * leveltypeshow : VIP1
         * discount : 80
         * giftemoney : 50
         * imgurl : null
         * imgurlshow :
         * imgurlcompressshow :
         */

        private Object shopid;
        private Object snid;
        private Object createuserid;
        private String facevalue;
        private Object leveltype;
        private String createuseridshow;
        private Object shopidshow;
        private Object createtime;
        private String leveltypeshow;
        private String discount;
        private String giftemoney;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;
        private Boolean isTrue;

        public Boolean getTrue() {
            return isTrue;
        }

        public void setTrue(Boolean aTrue) {
            isTrue = aTrue;
        }

        public Object getShopid() {
            return shopid;
        }

        public void setShopid(Object shopid) {
            this.shopid = shopid;
        }

        public Object getSnid() {
            return snid;
        }

        public void setSnid(Object snid) {
            this.snid = snid;
        }

        public Object getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(Object createuserid) {
            this.createuserid = createuserid;
        }

        public String getFacevalue() {
            return facevalue;
        }

        public void setFacevalue(String facevalue) {
            this.facevalue = facevalue;
        }

        public Object getLeveltype() {
            return leveltype;
        }

        public void setLeveltype(Object leveltype) {
            this.leveltype = leveltype;
        }

        public String getCreateuseridshow() {
            return createuseridshow;
        }

        public void setCreateuseridshow(String createuseridshow) {
            this.createuseridshow = createuseridshow;
        }

        public Object getShopidshow() {
            return shopidshow;
        }

        public void setShopidshow(Object shopidshow) {
            this.shopidshow = shopidshow;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public String getLeveltypeshow() {
            return leveltypeshow;
        }

        public void setLeveltypeshow(String leveltypeshow) {
            this.leveltypeshow = leveltypeshow;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getGiftemoney() {
            return giftemoney;
        }

        public void setGiftemoney(String giftemoney) {
            this.giftemoney = giftemoney;
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
