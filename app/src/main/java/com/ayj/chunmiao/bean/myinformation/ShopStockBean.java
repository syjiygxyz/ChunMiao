package com.ayj.chunmiao.bean.myinformation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/26.
 */

public class ShopStockBean implements Serializable{

    /**
     * err : 0
     * msg :
     * total : 12
     * data : [{"status":"SHOPSTOCKINSTATUS004","shopid":"001","snid":"20170316090128000217","ordertype":null,"btime":null,"etime":null,"totalmoney":"0.02","createuserid":"10275","ordertypeshow":"","sendtype":null,"paytype":"PAYTYPE004","tradeno":"2017031621001004450296288807","requestdate":"2017-03-17 00:00:00.0","createdate":"2017-03-16 09:01:28","statusshow":"确认收货","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":"LOGISTICSCOMP001","logisticsorderid":"956868","logisticscompshow":"顺丰快递","logisticsuseridshow":"","fhdate":"2017-03-16 09:03:05","shdate":"2017-03-16 09:04:43","paytypeshow":"支付宝支付","fhtotalmoney":"0.02","shtotalmoney":"0.02","sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS001","shopid":"001","snid":"20170405085733000235","ordertype":null,"btime":null,"etime":null,"totalmoney":"7500.00","createuserid":"10275","ordertypeshow":"","sendtype":null,"paytype":null,"tradeno":null,"requestdate":"2017-04-06 00:00:00.0","createdate":"2017-04-05 08:57:33","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,"shtotalmoney":null,"sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS004","shopid":"001","snid":"20170304144411000197","ordertype":null,"btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"","sendtype":null,"paytype":"PAYTYPE004","tradeno":"2017030421001004450279089066","requestdate":"2017-03-05 00:00:00.0","createdate":"2017-03-04 14:44:11","statusshow":"确认收货","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":"LOGISTICSCOMP001","logisticsorderid":"111","logisticscompshow":"顺丰快递","logisticsuseridshow":"","fhdate":"2017-03-04 19:06:07","shdate":"2017-03-06 09:10:12","paytypeshow":"支付宝支付","fhtotalmoney":"0.01","shtotalmoney":"0.01","sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS002","shopid":"001","snid":"20170303091921000196","ordertype":null,"btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"","sendtype":null,"paytype":"PAYTYPE004","tradeno":"2017030321001004450277122349","requestdate":"2017-03-04 00:00:00.0","createdate":"2017-03-03 09:19:21","statusshow":"已付款待发货","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"支付宝支付","fhtotalmoney":null,"shtotalmoney":null,"sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS001","shopid":"001","snid":"20170306170311000199","ordertype":null,"btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"","sendtype":null,"paytype":null,"tradeno":null,"requestdate":"2017-03-07 00:00:00.0","createdate":"2017-03-06 17:03:11","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,"shtotalmoney":null,"sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS001","shopid":"001","snid":"20170306171750000200","ordertype":"MEMBERORDERTYPE024","btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"耗材商城","sendtype":null,"paytype":null,"tradeno":null,"requestdate":"2017-03-07 00:00:00.0","createdate":"2017-03-06 17:17:50","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,"shtotalmoney":null,"sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS002","shopid":"001","snid":"20170302092703000194","ordertype":"MEMBERORDERTYPE024","btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"耗材商城","sendtype":null,"paytype":"PAYTYPE004","tradeno":"2017030321001004450277141673","requestdate":"2017-03-03 00:00:00.0","createdate":"2017-03-02 09:27:03","statusshow":"已付款待发货","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"支付宝支付","fhtotalmoney":null,"shtotalmoney":null,"sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS004","shopid":"001","snid":"20170315190141000215","ordertype":"MEMBERORDERTYPE021","btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"体验货架订单","sendtype":null,"paytype":"PAYTYPE004","tradeno":"2017031521001004450295774718","requestdate":"2017-03-16 00:00:00.0","createdate":"2017-03-15 19:01:41","statusshow":"确认收货","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":"LOGISTICSCOMP001","logisticsorderid":"222","logisticscompshow":"顺丰快递","logisticsuseridshow":"","fhdate":"2017-07-25 15:47:28","shdate":"2017-07-25 15:47:44","paytypeshow":"支付宝支付","fhtotalmoney":"0.01","shtotalmoney":"0.01","sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS001","shopid":"001","snid":"20170316085852000216","ordertype":"MEMBERORDERTYPE021","btime":null,"etime":null,"totalmoney":"80.00","createuserid":"10275","ordertypeshow":"体验货架订单","sendtype":null,"paytype":null,"tradeno":null,"requestdate":"2017-03-17 00:00:00.0","createdate":"2017-03-16 08:58:52","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,"shtotalmoney":null,"sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS001","shopid":"001","snid":"20170325101116000234","ordertype":"MEMBERORDERTYPE020","btime":null,"etime":null,"totalmoney":"178.00","createuserid":"10275","ordertypeshow":"实物货架订单","sendtype":null,"paytype":null,"tradeno":null,"requestdate":"2017-03-26 00:00:00.0","createdate":"2017-03-25 10:11:16","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,"shtotalmoney":null,"sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS004","shopid":"001","snid":"20170724184736000281","ordertype":"MEMBERORDERTYPE020","btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"实物货架订单","sendtype":null,"paytype":"PAYTYPE004","tradeno":"2017072421001004450220893740","requestdate":"2017-07-25 00:00:00.0","createdate":"2017-07-24 18:47:36","statusshow":"确认收货","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":"LOGISTICSCOMP001","logisticsorderid":"125646848","logisticscompshow":"顺丰快递","logisticsuseridshow":"","fhdate":"2017-07-24 18:53:55","shdate":"2017-07-24 18:57:54","paytypeshow":"支付宝支付","fhtotalmoney":"0.01","shtotalmoney":"0.01","sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS004","shopid":"001","snid":"20170728144050000299","ordertype":"MEMBERORDERTYPE024","btime":null,"etime":null,"totalmoney":"0.01","createuserid":"10275","ordertypeshow":"耗材商城","sendtype":null,"paytype":"PAYTYPE004","tradeno":"2017072821001004450227540413","requestdate":"2017-07-29 00:00:00.0","createdate":"2017-07-28 14:40:50","statusshow":"确认收货","logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":"LOGISTICSCOMP001","logisticsorderid":"111111","logisticscompshow":"顺丰快递","logisticsuseridshow":"","fhdate":"2017-07-28 14:43:36","shdate":"2017-07-28 16:26:03","paytypeshow":"支付宝支付","fhtotalmoney":"0.01","shtotalmoney":"0.01","sendtypeshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * status : SHOPSTOCKINSTATUS004
     * shopid : 001
     * snid : 20170316090128000217
     * ordertype : null
     * btime : null
     * etime : null
     * totalmoney : 0.02
     * createuserid : 10275
     * ordertypeshow :
     * sendtype : null
     * paytype : PAYTYPE004
     * tradeno : 2017031621001004450296288807
     * requestdate : 2017-03-17 00:00:00.0
     * createdate : 2017-03-16 09:01:28
     * statusshow : 确认收货
     * logisticsuserid : null
     * createuseridshow : 洋洋
     * shopidshow : null
     * logisticscomp : LOGISTICSCOMP001
     * logisticsorderid : 956868
     * logisticscompshow : 顺丰快递
     * logisticsuseridshow :
     * fhdate : 2017-03-16 09:03:05
     * shdate : 2017-03-16 09:04:43
     * paytypeshow : 支付宝支付
     * fhtotalmoney : 0.02
     * shtotalmoney : 0.02
     * sendtypeshow :
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
        private String status;
        private String shopid;
        private String snid;
        private Object ordertype;
        private Object btime;
        private Object etime;
        private String totalmoney;
        private String createuserid;
        private String ordertypeshow;
        private Object sendtype;
        private String paytype;
        private String tradeno;
        private String requestdate;
        private String createdate;
        private String statusshow;
        private Object logisticsuserid;
        private String createuseridshow;
        private String shopidshow;
        private String logisticscomp;
        private String logisticsorderid;
        private String logisticscompshow;
        private String logisticsuseridshow;
        private String fhdate;
        private String shdate;
        private String paytypeshow;
        private String fhtotalmoney;
        private String shtotalmoney;
        private String sendtypeshow;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public Object getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(Object ordertype) {
            this.ordertype = ordertype;
        }

        public Object getBtime() {
            return btime;
        }

        public void setBtime(Object btime) {
            this.btime = btime;
        }

        public Object getEtime() {
            return etime;
        }

        public void setEtime(Object etime) {
            this.etime = etime;
        }

        public String getTotalmoney() {
            return totalmoney;
        }

        public void setTotalmoney(String totalmoney) {
            this.totalmoney = totalmoney;
        }

        public String getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(String createuserid) {
            this.createuserid = createuserid;
        }

        public String getOrdertypeshow() {
            return ordertypeshow;
        }

        public void setOrdertypeshow(String ordertypeshow) {
            this.ordertypeshow = ordertypeshow;
        }

        public Object getSendtype() {
            return sendtype;
        }

        public void setSendtype(Object sendtype) {
            this.sendtype = sendtype;
        }

        public String getPaytype() {
            return paytype;
        }

        public void setPaytype(String paytype) {
            this.paytype = paytype;
        }

        public String getTradeno() {
            return tradeno;
        }

        public void setTradeno(String tradeno) {
            this.tradeno = tradeno;
        }

        public String getRequestdate() {
            return requestdate;
        }

        public void setRequestdate(String requestdate) {
            this.requestdate = requestdate;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getStatusshow() {
            return statusshow;
        }

        public void setStatusshow(String statusshow) {
            this.statusshow = statusshow;
        }

        public Object getLogisticsuserid() {
            return logisticsuserid;
        }

        public void setLogisticsuserid(Object logisticsuserid) {
            this.logisticsuserid = logisticsuserid;
        }

        public String getCreateuseridshow() {
            return createuseridshow;
        }

        public void setCreateuseridshow(String createuseridshow) {
            this.createuseridshow = createuseridshow;
        }

        public String getShopidshow() {
            return shopidshow;
        }

        public void setShopidshow(String shopidshow) {
            this.shopidshow = shopidshow;
        }

        public String getLogisticscomp() {
            return logisticscomp;
        }

        public void setLogisticscomp(String logisticscomp) {
            this.logisticscomp = logisticscomp;
        }

        public String getLogisticsorderid() {
            return logisticsorderid;
        }

        public void setLogisticsorderid(String logisticsorderid) {
            this.logisticsorderid = logisticsorderid;
        }

        public String getLogisticscompshow() {
            return logisticscompshow;
        }

        public void setLogisticscompshow(String logisticscompshow) {
            this.logisticscompshow = logisticscompshow;
        }

        public String getLogisticsuseridshow() {
            return logisticsuseridshow;
        }

        public void setLogisticsuseridshow(String logisticsuseridshow) {
            this.logisticsuseridshow = logisticsuseridshow;
        }

        public String getFhdate() {
            return fhdate;
        }

        public void setFhdate(String fhdate) {
            this.fhdate = fhdate;
        }

        public String getShdate() {
            return shdate;
        }

        public void setShdate(String shdate) {
            this.shdate = shdate;
        }

        public String getPaytypeshow() {
            return paytypeshow;
        }

        public void setPaytypeshow(String paytypeshow) {
            this.paytypeshow = paytypeshow;
        }

        public String getFhtotalmoney() {
            return fhtotalmoney;
        }

        public void setFhtotalmoney(String fhtotalmoney) {
            this.fhtotalmoney = fhtotalmoney;
        }

        public String getShtotalmoney() {
            return shtotalmoney;
        }

        public void setShtotalmoney(String shtotalmoney) {
            this.shtotalmoney = shtotalmoney;
        }

        public String getSendtypeshow() {
            return sendtypeshow;
        }

        public void setSendtypeshow(String sendtypeshow) {
            this.sendtypeshow = sendtypeshow;
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
