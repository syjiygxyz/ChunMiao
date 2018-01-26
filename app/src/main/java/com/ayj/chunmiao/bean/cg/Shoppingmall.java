package com.ayj.chunmiao.bean.cg;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/7/25.
 */

public class Shoppingmall implements Serializable{

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"shoppurchaseprice":"0.0001","snid":"387","matid":"WL-20170302-0000721","ordertype":"MEMBERORDERTYPE020","btime":null,"etime":null,"sfyx":null,"createuserid":null,"nowprice":null,"beforeprice":null,"ecomment":null,"totalnum":null,"unitid":null,"matidshow":"【血红素系列】力血康咀嚼片孕妇装（20片/盒）","ordertypeshow":"实物货架订单","createuseridshow":"","introducemediaid":null,"introducemediaidshow":"","logoimgurl":null,"logoimgurlshow":"","logoimgurlcompressshow":"","unitidshow":"","sfyxshow":"","createtime":null,"salenum":null,"introducemediaidcompressshow":"","matclass2":"SHOPPINGMALLCLSTWO002","matclass2show":"功能保健","matclass1":"SHOPPINGMALLCLSONE003","matclass1show":"其它","ishot":null,"ishotshow":"","ismuston":null,"ismustonshow":"","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * shoppurchaseprice : 0.0001
     * snid : 387
     * matid : WL-20170302-0000721
     * ordertype : MEMBERORDERTYPE020
     * btime : null
     * etime : null
     * sfyx : null
     * createuserid : null
     * nowprice : null
     * beforeprice : null
     * ecomment : null
     * totalnum : null
     * unitid : null
     * matidshow : 【血红素系列】力血康咀嚼片孕妇装（20片/盒）
     * ordertypeshow : 实物货架订单
     * createuseridshow :
     * introducemediaid : null
     * introducemediaidshow :
     * logoimgurl : null
     * logoimgurlshow :
     * logoimgurlcompressshow :
     * unitidshow :
     * sfyxshow :
     * createtime : null
     * salenum : null
     * introducemediaidcompressshow :
     * matclass2 : SHOPPINGMALLCLSTWO002
     * matclass2show : 功能保健
     * matclass1 : SHOPPINGMALLCLSONE003
     * matclass1show : 其它
     * ishot : null
     * ishotshow :
     * ismuston : null
     * ismustonshow :
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
        private String shoppurchaseprice;
        private String snid;
        private String matid;
        private String ordertype;
        private Object btime;
        private Object etime;
        private Object sfyx;
        private Object createuserid;
        private String nowprice;
        private Object beforeprice;
        private Object ecomment;
        private String totalnum;
        private Object unitid;
        private String matidshow;
        private String ordertypeshow;
        private String createuseridshow;
        private Object introducemediaid;
        private String introducemediaidshow;
        private Object logoimgurl;
        private String logoimgurlshow;
        private String logoimgurlcompressshow;
        private String unitidshow;
        private String sfyxshow;
        private Object createtime;
        private Object salenum;
        private String introducemediaidcompressshow;
        private String matclass2;
        private String matclass2show;
        private String matclass1;
        private String matclass1show;
        private Object ishot;
        private String ishotshow;
        private Object ismuston;
        private String ismustonshow;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        private String num;

        public String getShoppurchaseprice() {
            return shoppurchaseprice;
        }

        public void setShoppurchaseprice(String shoppurchaseprice) {
            this.shoppurchaseprice = shoppurchaseprice;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getMatid() {
            return matid;
        }

        public void setMatid(String matid) {
            this.matid = matid;
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

        public Object getEtime() {
            return etime;
        }

        public void setEtime(Object etime) {
            this.etime = etime;
        }

        public Object getSfyx() {
            return sfyx;
        }

        public void setSfyx(Object sfyx) {
            this.sfyx = sfyx;
        }

        public Object getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(Object createuserid) {
            this.createuserid = createuserid;
        }

        public String getNowprice() {
            return nowprice;
        }

        public void setNowprice(String nowprice) {
            this.nowprice = nowprice;
        }

        public Object getBeforeprice() {
            return beforeprice;
        }

        public void setBeforeprice(Object beforeprice) {
            this.beforeprice = beforeprice;
        }

        public Object getEcomment() {
            return ecomment;
        }

        public void setEcomment(Object ecomment) {
            this.ecomment = ecomment;
        }

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public Object getUnitid() {
            return unitid;
        }

        public void setUnitid(Object unitid) {
            this.unitid = unitid;
        }

        public String getMatidshow() {
            return matidshow;
        }

        public void setMatidshow(String matidshow) {
            this.matidshow = matidshow;
        }

        public String getOrdertypeshow() {
            return ordertypeshow;
        }

        public void setOrdertypeshow(String ordertypeshow) {
            this.ordertypeshow = ordertypeshow;
        }

        public String getCreateuseridshow() {
            return createuseridshow;
        }

        public void setCreateuseridshow(String createuseridshow) {
            this.createuseridshow = createuseridshow;
        }

        public Object getIntroducemediaid() {
            return introducemediaid;
        }

        public void setIntroducemediaid(Object introducemediaid) {
            this.introducemediaid = introducemediaid;
        }

        public String getIntroducemediaidshow() {
            return introducemediaidshow;
        }

        public void setIntroducemediaidshow(String introducemediaidshow) {
            this.introducemediaidshow = introducemediaidshow;
        }

        public Object getLogoimgurl() {
            return logoimgurl;
        }

        public void setLogoimgurl(Object logoimgurl) {
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

        public String getUnitidshow() {
            return unitidshow;
        }

        public void setUnitidshow(String unitidshow) {
            this.unitidshow = unitidshow;
        }

        public String getSfyxshow() {
            return sfyxshow;
        }

        public void setSfyxshow(String sfyxshow) {
            this.sfyxshow = sfyxshow;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public Object getSalenum() {
            return salenum;
        }

        public void setSalenum(Object salenum) {
            this.salenum = salenum;
        }

        public String getIntroducemediaidcompressshow() {
            return introducemediaidcompressshow;
        }

        public void setIntroducemediaidcompressshow(String introducemediaidcompressshow) {
            this.introducemediaidcompressshow = introducemediaidcompressshow;
        }

        public String getMatclass2() {
            return matclass2;
        }

        public void setMatclass2(String matclass2) {
            this.matclass2 = matclass2;
        }

        public String getMatclass2show() {
            return matclass2show;
        }

        public void setMatclass2show(String matclass2show) {
            this.matclass2show = matclass2show;
        }

        public String getMatclass1() {
            return matclass1;
        }

        public void setMatclass1(String matclass1) {
            this.matclass1 = matclass1;
        }

        public String getMatclass1show() {
            return matclass1show;
        }

        public void setMatclass1show(String matclass1show) {
            this.matclass1show = matclass1show;
        }

        public Object getIshot() {
            return ishot;
        }

        public void setIshot(Object ishot) {
            this.ishot = ishot;
        }

        public String getIshotshow() {
            return ishotshow;
        }

        public void setIshotshow(String ishotshow) {
            this.ishotshow = ishotshow;
        }

        public Object getIsmuston() {
            return ismuston;
        }

        public void setIsmuston(Object ismuston) {
            this.ismuston = ismuston;
        }

        public String getIsmustonshow() {
            return ismustonshow;
        }

        public void setIsmustonshow(String ismustonshow) {
            this.ismustonshow = ismustonshow;
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
