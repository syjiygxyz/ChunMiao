package com.ayj.chunmiao.bean.myinformation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/1.
 */

public class UnionShareHisBean implements Serializable{

    /**
     * err : 0
     * msg : 获取春苗分享联销平台商品信息成功
     * total : 1
     * data : [{"matid":"66","shopid":"002","userid":null,"snid":"-1","ordertype":"MEMBERORDERTYPE029","btime":null,"etime":null,"maxnumperomember":"2","ordertypeshow":"联销平台-采购销","matidshow":"佑乐元高铁低聚果糖固体饮料","remainnum":"3","logoimgurl":"Upload/2017-04-18/20170418152236081.png","shopidshow":"盛北社区服务点","logoimgurlshow":"http://test.21ga.cn:8000/qmkmimageupload/Upload/2017-04-18/20170418152236081.png","logoimgurlcompressshow":"http://test.21ga.cn:8000/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-04-18/20170418152236081.png","createtime":null,"useridshow":"","suppliershopid":null,"suppliershopidshow":"","shareTrips":null,"placeOrderNum":null,"ration":null,"sharenum":"5","usednum":"2","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * matid : 66
     * shopid : 002
     * userid : null
     * snid : -1
     * ordertype : MEMBERORDERTYPE029
     * btime : null
     * etime : null
     * maxnumperomember : 2
     * ordertypeshow : 联销平台-采购销
     * matidshow : 佑乐元高铁低聚果糖固体饮料
     * remainnum : 3
     * logoimgurl : Upload/2017-04-18/20170418152236081.png
     * shopidshow : 盛北社区服务点
     * logoimgurlshow : http://test.21ga.cn:8000/qmkmimageupload/Upload/2017-04-18/20170418152236081.png
     * logoimgurlcompressshow : http://test.21ga.cn:8000/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-04-18/20170418152236081.png
     * createtime : null
     * useridshow :
     * suppliershopid : null
     * suppliershopidshow :
     * shareTrips : null
     * placeOrderNum : null
     * ration : null
     * sharenum : 5
     * usednum : 2
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

    public static class DataBean implements Serializable{
        private String matid;
        private String shopid;
        private Object userid;
        private String snid;
        private String ordertype;
        private Object btime;
        private String etime;
        private String maxnumperomember;
        private String ordertypeshow;
        private String matidshow;
        private String remainnum;
        private String logoimgurl;
        private String shopidshow;
        private String logoimgurlshow;
        private String logoimgurlcompressshow;
        private String createtime;
        private String useridshow;
        private Object suppliershopid;
        private String suppliershopidshow;
        private Object shareTrips;
        private Object placeOrderNum;
        private Object ration;
        private String sharenum;
        private String usednum;
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

        public Object getUserid() {
            return userid;
        }

        public void setUserid(Object userid) {
            this.userid = userid;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public Object getBtime() {
            return btime;
        }

        public void setBtime(Object btime) {
            this.btime = btime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getMaxnumperomember() {
            return maxnumperomember;
        }

        public void setMaxnumperomember(String maxnumperomember) {
            this.maxnumperomember = maxnumperomember;
        }

        public String getOrdertypeshow() {
            return ordertypeshow;
        }

        public void setOrdertypeshow(String ordertypeshow) {
            this.ordertypeshow = ordertypeshow;
        }

        public String getMatidshow() {
            return matidshow;
        }

        public void setMatidshow(String matidshow) {
            this.matidshow = matidshow;
        }

        public String getRemainnum() {
            return remainnum;
        }

        public void setRemainnum(String remainnum) {
            this.remainnum = remainnum;
        }

        public String getLogoimgurl() {
            return logoimgurl;
        }

        public void setLogoimgurl(String logoimgurl) {
            this.logoimgurl = logoimgurl;
        }

        public String getShopidshow() {
            return shopidshow;
        }

        public void setShopidshow(String shopidshow) {
            this.shopidshow = shopidshow;
        }

        public String getLogoimgurlshow() {
            return logoimgurlshow;
        }

        public void setLogoimgurlshow(String logoimgurlshow) {
            this.logoimgurlshow = logoimgurlshow;
        }

        public String getLogoimgurlcompressshow() {
            return logoimgurlcompressshow;
        }

        public void setLogoimgurlcompressshow(String logoimgurlcompressshow) {
            this.logoimgurlcompressshow = logoimgurlcompressshow;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getUseridshow() {
            return useridshow;
        }

        public void setUseridshow(String useridshow) {
            this.useridshow = useridshow;
        }

        public Object getSuppliershopid() {
            return suppliershopid;
        }

        public void setSuppliershopid(Object suppliershopid) {
            this.suppliershopid = suppliershopid;
        }

        public String getSuppliershopidshow() {
            return suppliershopidshow;
        }

        public void setSuppliershopidshow(String suppliershopidshow) {
            this.suppliershopidshow = suppliershopidshow;
        }

        public Object getShareTrips() {
            return shareTrips;
        }

        public void setShareTrips(Object shareTrips) {
            this.shareTrips = shareTrips;
        }

        public Object getPlaceOrderNum() {
            return placeOrderNum;
        }

        public void setPlaceOrderNum(Object placeOrderNum) {
            this.placeOrderNum = placeOrderNum;
        }

        public Object getRation() {
            return ration;
        }

        public void setRation(Object ration) {
            this.ration = ration;
        }

        public String getSharenum() {
            return sharenum;
        }

        public void setSharenum(String sharenum) {
            this.sharenum = sharenum;
        }

        public String getUsednum() {
            return usednum;
        }

        public void setUsednum(String usednum) {
            this.usednum = usednum;
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
