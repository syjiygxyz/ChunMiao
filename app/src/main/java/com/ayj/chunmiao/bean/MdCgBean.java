package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/28.
 */
public class MdCgBean {

    /**
     * err : 0
     * msg :
     * total : 10
     * data : [{"status":"SHOPSTOCKINSTATUS001","snid":"20170325101116000234","shopid":"001",
     * "btime":null,"etime":null,"totalmoney":"178.00","paytype":null,"tradeno":null,
     * "createuserid":"10275","requestdate":"2017-03-26 00:00:00.0","createdate":"2017-03-25
     * 10:11:16","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋",
     * "shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"",
     * "logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,
     * "shtotalmoney":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},
     * {"status":"SHOPSTOCKINSTATUS001","snid":"20170316085852000216","shopid":"001",
     * "btime":null,"etime":null,"totalmoney":"80.00","paytype":null,"tradeno":null,
     * "createuserid":"10275","requestdate":"2017-03-17 00:00:00.0","createdate":"2017-03-16
     * 08:58:52","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋",
     * "shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"",
     * "logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,
     * "shtotalmoney":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},
     * {"status":"SHOPSTOCKINSTATUS002","snid":"20170315190141000215","shopid":"001",
     * "btime":null,"etime":null,"totalmoney":"0.01","paytype":"PAYTYPE004",
     * "tradeno":"2017031521001004450295774718","createuserid":"10275","requestdate":"2017-03-16
     * 00:00:00.0","createdate":"2017-03-15 19:01:41","statusshow":"已付款待发货",
     * "logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,
     * "logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,
     * "shdate":null,"paytypeshow":"支付宝支付","fhtotalmoney":null,"shtotalmoney":null,"imgurl":null,
     * "imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS002",
     * "snid":"20170302092703000194","shopid":"001","btime":null,"etime":null,"totalmoney":"0
     * .01","paytype":"PAYTYPE004","tradeno":"2017030321001004450277141673",
     * "createuserid":"10275","requestdate":"2017-03-03 00:00:00.0","createdate":"2017-03-02
     * 09:27:03","statusshow":"已付款待发货","logisticsuserid":null,"createuseridshow":"洋洋",
     * "shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"",
     * "logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"支付宝支付",
     * "fhtotalmoney":null,"shtotalmoney":null,"imgurl":null,"imgurlshow":"",
     * "imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS001","snid":"20170306171750000200",
     * "shopid":"001","btime":null,"etime":null,"totalmoney":"0.01","paytype":null,
     * "tradeno":null,"createuserid":"10275","requestdate":"2017-03-07 00:00:00.0",
     * "createdate":"2017-03-06 17:17:50","statusshow":"门店已提交待付款","logisticsuserid":null,
     * "createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,
     * "logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,
     * "paytypeshow":"","fhtotalmoney":null,"shtotalmoney":null,"imgurl":null,"imgurlshow":"",
     * "imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS001","snid":"20170306170311000199",
     * "shopid":"001","btime":null,"etime":null,"totalmoney":"0.01","paytype":null,
     * "tradeno":null,"createuserid":"10275","requestdate":"2017-03-07 00:00:00.0",
     * "createdate":"2017-03-06 17:03:11","statusshow":"门店已提交待付款","logisticsuserid":null,
     * "createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,"logisticsorderid":null,
     * "logisticscompshow":"","logisticsuseridshow":"","fhdate":null,"shdate":null,
     * "paytypeshow":"","fhtotalmoney":null,"shtotalmoney":null,"imgurl":null,"imgurlshow":"",
     * "imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS002","snid":"20170303091921000196",
     * "shopid":"001","btime":null,"etime":null,"totalmoney":"0.01","paytype":"PAYTYPE004",
     * "tradeno":"2017030321001004450277122349","createuserid":"10275","requestdate":"2017-03-04
     * 00:00:00.0","createdate":"2017-03-03 09:19:21","statusshow":"已付款待发货",
     * "logisticsuserid":null,"createuseridshow":"洋洋","shopidshow":null,"logisticscomp":null,
     * "logisticsorderid":null,"logisticscompshow":"","logisticsuseridshow":"","fhdate":null,
     * "shdate":null,"paytypeshow":"支付宝支付","fhtotalmoney":null,"shtotalmoney":null,"imgurl":null,
     * "imgurlshow":"","imgurlcompressshow":""},{"status":"SHOPSTOCKINSTATUS004",
     * "snid":"20170304144411000197","shopid":"001","btime":null,"etime":null,"totalmoney":"0
     * .01","paytype":"PAYTYPE004","tradeno":"2017030421001004450279089066",
     * "createuserid":"10275","requestdate":"2017-03-05 00:00:00.0","createdate":"2017-03-04
     * 14:44:11","statusshow":"确认收货","logisticsuserid":null,"createuseridshow":"洋洋",
     * "shopidshow":null,"logisticscomp":"LOGISTICSCOMP001","logisticsorderid":"111",
     * "logisticscompshow":"顺丰快递","logisticsuseridshow":"","fhdate":"2017-03-04 19:06:07",
     * "shdate":"2017-03-06 09:10:12","paytypeshow":"支付宝支付","fhtotalmoney":"0.01",
     * "shtotalmoney":"0.01","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},
     * {"status":"SHOPSTOCKINSTATUS001","snid":"20170405085733000235","shopid":"001",
     * "btime":null,"etime":null,"totalmoney":"7500.00","paytype":null,"tradeno":null,
     * "createuserid":"10275","requestdate":"2017-04-06 00:00:00.0","createdate":"2017-04-05
     * 08:57:33","statusshow":"门店已提交待付款","logisticsuserid":null,"createuseridshow":"洋洋",
     * "shopidshow":null,"logisticscomp":null,"logisticsorderid":null,"logisticscompshow":"",
     * "logisticsuseridshow":"","fhdate":null,"shdate":null,"paytypeshow":"","fhtotalmoney":null,
     * "shtotalmoney":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},
     * {"status":"SHOPSTOCKINSTATUS004","snid":"20170316090128000217","shopid":"001",
     * "btime":null,"etime":null,"totalmoney":"0.02","paytype":"PAYTYPE004",
     * "tradeno":"2017031621001004450296288807","createuserid":"10275","requestdate":"2017-03-17
     * 00:00:00.0","createdate":"2017-03-16 09:01:28","statusshow":"确认收货","logisticsuserid":null,
     * "createuseridshow":"洋洋","shopidshow":null,"logisticscomp":"LOGISTICSCOMP001",
     * "logisticsorderid":"956868","logisticscompshow":"顺丰快递","logisticsuseridshow":"",
     * "fhdate":"2017-03-16 09:03:05","shdate":"2017-03-16 09:04:43","paytypeshow":"支付宝支付",
     * "fhtotalmoney":"0.02","shtotalmoney":"0.02","imgurl":null,"imgurlshow":"",
     * "imgurlcompressshow":""}]
*/
    private int err;
    private String msg;
    private String total;
    /**
     * status : SHOPSTOCKINSTATUS001
     * snid : 20170325101116000234
     * shopid : 001
     * btime : null
     * etime : null
     * totalmoney : 178.00
     * paytype : null
     * tradeno : null
     * createuserid : 10275
     * requestdate : 2017-03-26 00:00:00.0
     * createdate : 2017-03-25 10:11:16
     * statusshow : 门店已提交待付款
     * logisticsuserid : null
     * createuseridshow : 洋洋
     * shopidshow : null
     * logisticscomp : null
     * logisticsorderid : null
     * logisticscompshow :
     * logisticsuseridshow :
     * fhdate : null
     * shdate : null
     * paytypeshow :
     * fhtotalmoney : null
     * shtotalmoney : null
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
        private String status;
        private String snid;
        private String shopid;
        private Object btime;
        private Object etime;
        private String totalmoney;
        private Object paytype;
        private Object tradeno;
        private String createuserid;
        private String requestdate;
        private String createdate;
        private String statusshow;
        private Object logisticsuserid;
        private String createuseridshow;
        private Object shopidshow;
        private Object logisticscomp;
        private Object logisticsorderid;
        private String logisticscompshow;
        private String logisticsuseridshow;
        private Object fhdate;
        private Object shdate;
        private String paytypeshow;
        private Object fhtotalmoney;
        private Object shtotalmoney;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
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

        public Object getPaytype() {
            return paytype;
        }

        public void setPaytype(Object paytype) {
            this.paytype = paytype;
        }

        public Object getTradeno() {
            return tradeno;
        }

        public void setTradeno(Object tradeno) {
            this.tradeno = tradeno;
        }

        public String getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(String createuserid) {
            this.createuserid = createuserid;
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

        public Object getShopidshow() {
            return shopidshow;
        }

        public void setShopidshow(Object shopidshow) {
            this.shopidshow = shopidshow;
        }

        public Object getLogisticscomp() {
            return logisticscomp;
        }

        public void setLogisticscomp(Object logisticscomp) {
            this.logisticscomp = logisticscomp;
        }

        public Object getLogisticsorderid() {
            return logisticsorderid;
        }

        public void setLogisticsorderid(Object logisticsorderid) {
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

        public Object getFhdate() {
            return fhdate;
        }

        public void setFhdate(Object fhdate) {
            this.fhdate = fhdate;
        }

        public Object getShdate() {
            return shdate;
        }

        public void setShdate(Object shdate) {
            this.shdate = shdate;
        }

        public String getPaytypeshow() {
            return paytypeshow;
        }

        public void setPaytypeshow(String paytypeshow) {
            this.paytypeshow = paytypeshow;
        }

        public Object getFhtotalmoney() {
            return fhtotalmoney;
        }

        public void setFhtotalmoney(Object fhtotalmoney) {
            this.fhtotalmoney = fhtotalmoney;
        }

        public Object getShtotalmoney() {
            return shtotalmoney;
        }

        public void setShtotalmoney(Object shtotalmoney) {
            this.shtotalmoney = shtotalmoney;
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
