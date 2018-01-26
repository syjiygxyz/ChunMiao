package com.ayj.chunmiao.bean.myinformation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/24.
 */

public class LxdBean implements Serializable{

    /**
     * err : 0
     * msg :
     * total : 2
     * data : [{"matid":"WL-20161206-0000682","num":"1","ordertype":"MEMBERORDERTYPE030","btime":"2017-08-26","etime":"2017-09-26","totalmoney":"0.01","ordertypeshow":"联销平台-分摊销","orderid":"20170304144411000197","matidshow":"艾灸/针灸调理耗材","standard":"QB2345","logoimgurl":"Upload/2017-05-08/20170508092423035.png","logoimgurlshow":"http://test.21ga.cn:8000/qmkmimageupload/Upload/2017-05-08/20170508092423035.png","logoimgurlcompressshow":"http://test.21ga.cn:8000/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-05-08/20170508092423035.png","suppliershopid":null,"suppliershopidshow":"","haspay":"SFPD002","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"matid":"WL-20161206-0000680","num":"1","ordertype":"MEMBERORDERTYPE030","btime":"2017-08-26","etime":"2017-09-26","totalmoney":"0.01","ordertypeshow":"联销平台-分摊销","orderid":"20170306170311000199","matidshow":"声波调理耗材","standard":"QB2345","logoimgurl":"Upload/2017-05-08/20170508092441722.png","logoimgurlshow":"http://test.21ga.cn:8000/qmkmimageupload/Upload/2017-05-08/20170508092441722.png","logoimgurlcompressshow":"http://test.21ga.cn:8000/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-05-08/20170508092441722.png","suppliershopid":null,"suppliershopidshow":"","haspay":"SFPD002","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * matid : WL-20161206-0000682
     * num : 1
     * ordertype : MEMBERORDERTYPE030
     * btime : 2017-08-26
     * etime : 2017-09-26
     * totalmoney : 0.01
     * ordertypeshow : 联销平台-分摊销
     * orderid : 20170304144411000197
     * matidshow : 艾灸/针灸调理耗材
     * standard : QB2345
     * logoimgurl : Upload/2017-05-08/20170508092423035.png
     * logoimgurlshow : http://test.21ga.cn:8000/qmkmimageupload/Upload/2017-05-08/20170508092423035.png
     * logoimgurlcompressshow : http://test.21ga.cn:8000/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-05-08/20170508092423035.png
     * suppliershopid : null
     * suppliershopidshow :
     * haspay : SFPD002
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
        private String num;
        private String ordertype;
        private String btime;
        private String etime;
        private String totalmoney;
        private String ordertypeshow;
        private String orderid;
        private String matidshow;
        private String standard;
        private String logoimgurl;
        private String logoimgurlshow;
        private String logoimgurlcompressshow;
        private Object suppliershopid;
        private String suppliershopidshow;
        private String haspay;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getMatid() {
            return matid;
        }

        public void setMatid(String matid) {
            this.matid = matid;
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

        public String getBtime() {
            return btime;
        }

        public void setBtime(String btime) {
            this.btime = btime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getTotalmoney() {
            return totalmoney;
        }

        public void setTotalmoney(String totalmoney) {
            this.totalmoney = totalmoney;
        }

        public String getOrdertypeshow() {
            return ordertypeshow;
        }

        public void setOrdertypeshow(String ordertypeshow) {
            this.ordertypeshow = ordertypeshow;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
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

        public String getLogoimgurl() {
            return logoimgurl;
        }

        public void setLogoimgurl(String logoimgurl) {
            this.logoimgurl = logoimgurl;
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

        public String getHaspay() {
            return haspay;
        }

        public void setHaspay(String haspay) {
            this.haspay = haspay;
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
