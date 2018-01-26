package com.ayj.chunmiao.bean.cmbz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/4.
 */
public class YybBean implements Serializable{

    /**
     * err : 0
     * msg :
     * total : 4
     * data : [{"shopid":null,"snid":null,"msnid":null,"btime":null,"etime":null,"tickettype":"55","ticketusescope":null,"orderid":null,"ticketlimitcondition":null,"ticketusescopeshow":null,"statusshow":"","msnidshow":null,"shopidshow":null,"createtime":null,"count1":"1","getfrom":null,"usetime":null,"tickettypeshow":"菊花茶","getfromshow":"","refundid":null,"getfromorderid":null,"ticketclass":null,"ticketclassshow":"","status":null,"imgurl":"Upload/2017-03-04/20170304114619091.png","imgurlshow":"http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-03-04/20170304114619091.png","imgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-03-04/20170304114619091.png"},{"shopid":null,"snid":null,"msnid":null,"btime":null,"etime":null,"tickettype":"56","ticketusescope":null,"orderid":null,"ticketlimitcondition":null,"ticketusescopeshow":null,"statusshow":"","msnidshow":null,"shopidshow":null,"createtime":null,"count1":"1","getfrom":null,"usetime":null,"tickettypeshow":"红枣茶","getfromshow":"","refundid":null,"getfromorderid":null,"ticketclass":null,"ticketclassshow":"","status":null,"imgurl":"Upload/2017-03-04/20170304114711388.png","imgurlshow":"http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-03-04/20170304114711388.png","imgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-03-04/20170304114711388.png"},{"shopid":null,"snid":null,"msnid":null,"btime":null,"etime":null,"tickettype":"WL-20160913-0000570","ticketusescope":null,"orderid":null,"ticketlimitcondition":null,"ticketusescopeshow":null,"statusshow":"","msnidshow":null,"shopidshow":null,"createtime":null,"count1":"6","getfrom":null,"usetime":null,"tickettypeshow":"柠檬组合","getfromshow":"","refundid":null,"getfromorderid":null,"ticketclass":null,"ticketclassshow":"","status":null,"imgurl":"Upload/2017-02-17/20170217141447811.png","imgurlshow":"http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-02-17/20170217141447811.png","imgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-02-17/20170217141447811.png"},{"shopid":null,"snid":null,"msnid":null,"btime":null,"etime":null,"tickettype":"WL-20160913-0000573","ticketusescope":null,"orderid":null,"ticketlimitcondition":null,"ticketusescopeshow":null,"statusshow":"","msnidshow":null,"shopidshow":null,"createtime":null,"count1":"12","getfrom":null,"usetime":null,"tickettypeshow":"番茄组合","getfromshow":"","refundid":null,"getfromorderid":null,"ticketclass":null,"ticketclassshow":"","status":null,"imgurl":"Upload/2017-02-17/20170217141539571.png","imgurlshow":"http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-02-17/20170217141539571.png","imgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-02-17/20170217141539571.png"}]
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

    public static class DataBean implements Serializable{
        /**
         * shopid : null
         * snid : null
         * msnid : null
         * btime : null
         * etime : null
         * tickettype : 55
         * ticketusescope : null
         * orderid : null
         * ticketlimitcondition : null
         * ticketusescopeshow : null
         * statusshow :
         * msnidshow : null
         * shopidshow : null
         * createtime : null
         * count1 : 1
         * getfrom : null
         * usetime : null
         * tickettypeshow : 菊花茶
         * getfromshow :
         * refundid : null
         * getfromorderid : null
         * ticketclass : null
         * ticketclassshow :
         * status : null
         * imgurl : Upload/2017-03-04/20170304114619091.png
         * imgurlshow : http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-03-04/20170304114619091.png
         * imgurlcompressshow : http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-03-04/20170304114619091.png
         */

        private Object shopid;
        private Object snid;
        private Object msnid;
        private Object btime;
        private Object etime;
        private String tickettype;
        private Object ticketusescope;
        private Object orderid;
        private Object ticketlimitcondition;
        private Object ticketusescopeshow;
        private String statusshow;
        private Object msnidshow;
        private Object shopidshow;
        private Object createtime;
        private String count1;
        private Object getfrom;
        private Object usetime;
        private String tickettypeshow;
        private String getfromshow;
        private Object refundid;
        private Object getfromorderid;
        private Object ticketclass;
        private String ticketclassshow;
        private Object status;
        private String imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;
        private boolean isornotClick;
        private int getNumber;

        public int getGetNumber() {
            return getNumber;
        }

        public void setGetNumber(int getNumber) {
            this.getNumber = getNumber;
        }

        public boolean isornotClick() {
            return isornotClick;
        }

        public void setIsornotClick(boolean isornotClick) {
            this.isornotClick = isornotClick;
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

        public Object getMsnid() {
            return msnid;
        }

        public void setMsnid(Object msnid) {
            this.msnid = msnid;
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

        public String getTickettype() {
            return tickettype;
        }

        public void setTickettype(String tickettype) {
            this.tickettype = tickettype;
        }

        public Object getTicketusescope() {
            return ticketusescope;
        }

        public void setTicketusescope(Object ticketusescope) {
            this.ticketusescope = ticketusescope;
        }

        public Object getOrderid() {
            return orderid;
        }

        public void setOrderid(Object orderid) {
            this.orderid = orderid;
        }

        public Object getTicketlimitcondition() {
            return ticketlimitcondition;
        }

        public void setTicketlimitcondition(Object ticketlimitcondition) {
            this.ticketlimitcondition = ticketlimitcondition;
        }

        public Object getTicketusescopeshow() {
            return ticketusescopeshow;
        }

        public void setTicketusescopeshow(Object ticketusescopeshow) {
            this.ticketusescopeshow = ticketusescopeshow;
        }

        public String getStatusshow() {
            return statusshow;
        }

        public void setStatusshow(String statusshow) {
            this.statusshow = statusshow;
        }

        public Object getMsnidshow() {
            return msnidshow;
        }

        public void setMsnidshow(Object msnidshow) {
            this.msnidshow = msnidshow;
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

        public String getCount1() {
            return count1;
        }

        public void setCount1(String count1) {
            this.count1 = count1;
        }

        public Object getGetfrom() {
            return getfrom;
        }

        public void setGetfrom(Object getfrom) {
            this.getfrom = getfrom;
        }

        public Object getUsetime() {
            return usetime;
        }

        public void setUsetime(Object usetime) {
            this.usetime = usetime;
        }

        public String getTickettypeshow() {
            return tickettypeshow;
        }

        public void setTickettypeshow(String tickettypeshow) {
            this.tickettypeshow = tickettypeshow;
        }

        public String getGetfromshow() {
            return getfromshow;
        }

        public void setGetfromshow(String getfromshow) {
            this.getfromshow = getfromshow;
        }

        public Object getRefundid() {
            return refundid;
        }

        public void setRefundid(Object refundid) {
            this.refundid = refundid;
        }

        public Object getGetfromorderid() {
            return getfromorderid;
        }

        public void setGetfromorderid(Object getfromorderid) {
            this.getfromorderid = getfromorderid;
        }

        public Object getTicketclass() {
            return ticketclass;
        }

        public void setTicketclass(Object ticketclass) {
            this.ticketclass = ticketclass;
        }

        public String getTicketclassshow() {
            return ticketclassshow;
        }

        public void setTicketclassshow(String ticketclassshow) {
            this.ticketclassshow = ticketclassshow;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
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
