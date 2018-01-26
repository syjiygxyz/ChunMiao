package com.ayj.chunmiao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/1/3.
 */
public class MemberCard implements Serializable {

    public static final long serialVersionUID = -1989768541338666696L;

    /**
     * err : 0
     * msg :
     * total : 2
     * data : [{"beforeprice":"100","btime":"2016-12-30 00:00:00","cardproperty":null,
     * "cardpropertyshow":"","createtime":"2016-12-30 16:05:11","currentColomu":null,
     * "currentpage":null,"did":"","dname":"","ecomment":"1000","endrow":18,"etime":"2017-12-31
     * 00:00:00","imgurl":null,"matclass":"CARDTYPE004","matclassshow":"康乐会员卡",
     * "matid":"WL-20161230-0000688","matidshow":"康乐会员月卡","matstyle":null,"nowprice":"100",
     * "pagermethod":null,"pagesize":null,"sfyx":"SFYX001","sfyxshow":"有效","snid":"9",
     * "sortChoose":"desc","startrow":0,"totalnum":"1000","unitid":"2016110746102",
     * "unitidshow":"张","uselimitnum":"10","validdays":"30"},{"beforeprice":"3000",
     * "btime":"2016-12-30 00:00:00","cardproperty":null,"cardpropertyshow":"",
     * "createtime":"2016-12-30 16:07:13","currentColomu":null,"currentpage":null,"did":"",
     * "dname":"","ecomment":"1000","endrow":18,"etime":"2017-12-31 00:00:00","imgurl":null,
     * "matclass":"CARDTYPE004","matclassshow":"康乐会员卡","matid":"WL-20161230-0000689",
     * "matidshow":"康乐会员年卡","matstyle":null,"nowprice":"3000","pagermethod":null,"pagesize":null,
     * "sfyx":"SFYX001","sfyxshow":"有效","snid":"10","sortChoose":"desc","startrow":0,
     * "totalnum":"1000","unitid":"2016110746102","unitidshow":"张","uselimitnum":"10",
     * "validdays":"365"}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * beforeprice : 100
     * btime : 2016-12-30 00:00:00
     * cardproperty : null
     * cardpropertyshow :
     * createtime : 2016-12-30 16:05:11
     * currentColomu : null
     * currentpage : null
     * did :
     * dname :
     * ecomment : 1000
     * endrow : 18
     * etime : 2017-12-31 00:00:00
     * imgurl : null
     * matclass : CARDTYPE004
     * matclassshow : 康乐会员卡
     * matid : WL-20161230-0000688
     * matidshow : 康乐会员月卡
     * matstyle : null
     * nowprice : 100
     * pagermethod : null
     * pagesize : null
     * sfyx : SFYX001
     * sfyxshow : 有效
     * snid : 9
     * sortChoose : desc
     * startrow : 0
     * totalnum : 1000
     * unitid : 2016110746102
     * unitidshow : 张
     * uselimitnum : 10
     * validdays : 30
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

    public static class DataBean implements Serializable {
        private String beforeprice;
        private String btime;
        private Object cardproperty;
        private String cardpropertyshow;
        private String createtime;
        private Object currentColomu;
        private Object currentpage;
        private String did;
        private String dname;
        private String ecomment;
        private int endrow;
        private String etime;
        private Object imgurl;
        private String matclass;
        private String matclassshow;
        private String matid;
        private String matidshow;
        private Object matstyle;
        private String nowprice;
        private Object pagermethod;
        private Object pagesize;
        private String sfyx;
        private String sfyxshow;
        private String snid;
        private String sortChoose;
        private int startrow;
        private String totalnum;
        private String unitid;
        private String unitidshow;
        private String uselimitnum;
        private String validdays;

        public String getBeforeprice() {
            return beforeprice;
        }

        public void setBeforeprice(String beforeprice) {
            this.beforeprice = beforeprice;
        }

        public String getBtime() {
            return btime;
        }

        public void setBtime(String btime) {
            this.btime = btime;
        }

        public Object getCardproperty() {
            return cardproperty;
        }

        public void setCardproperty(Object cardproperty) {
            this.cardproperty = cardproperty;
        }

        public String getCardpropertyshow() {
            return cardpropertyshow;
        }

        public void setCardpropertyshow(String cardpropertyshow) {
            this.cardpropertyshow = cardpropertyshow;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getCurrentColomu() {
            return currentColomu;
        }

        public void setCurrentColomu(Object currentColomu) {
            this.currentColomu = currentColomu;
        }

        public Object getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(Object currentpage) {
            this.currentpage = currentpage;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getDname() {
            return dname;
        }

        public void setDname(String dname) {
            this.dname = dname;
        }

        public String getEcomment() {
            return ecomment;
        }

        public void setEcomment(String ecomment) {
            this.ecomment = ecomment;
        }

        public int getEndrow() {
            return endrow;
        }

        public void setEndrow(int endrow) {
            this.endrow = endrow;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public Object getImgurl() {
            return imgurl;
        }

        public void setImgurl(Object imgurl) {
            this.imgurl = imgurl;
        }

        public String getMatclass() {
            return matclass;
        }

        public void setMatclass(String matclass) {
            this.matclass = matclass;
        }

        public String getMatclassshow() {
            return matclassshow;
        }

        public void setMatclassshow(String matclassshow) {
            this.matclassshow = matclassshow;
        }

        public String getMatid() {
            return matid;
        }

        public void setMatid(String matid) {
            this.matid = matid;
        }

        public String getMatidshow() {
            return matidshow;
        }

        public void setMatidshow(String matidshow) {
            this.matidshow = matidshow;
        }

        public Object getMatstyle() {
            return matstyle;
        }

        public void setMatstyle(Object matstyle) {
            this.matstyle = matstyle;
        }

        public String getNowprice() {
            return nowprice;
        }

        public void setNowprice(String nowprice) {
            this.nowprice = nowprice;
        }

        public Object getPagermethod() {
            return pagermethod;
        }

        public void setPagermethod(Object pagermethod) {
            this.pagermethod = pagermethod;
        }

        public Object getPagesize() {
            return pagesize;
        }

        public void setPagesize(Object pagesize) {
            this.pagesize = pagesize;
        }

        public String getSfyx() {
            return sfyx;
        }

        public void setSfyx(String sfyx) {
            this.sfyx = sfyx;
        }

        public String getSfyxshow() {
            return sfyxshow;
        }

        public void setSfyxshow(String sfyxshow) {
            this.sfyxshow = sfyxshow;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getSortChoose() {
            return sortChoose;
        }

        public void setSortChoose(String sortChoose) {
            this.sortChoose = sortChoose;
        }

        public int getStartrow() {
            return startrow;
        }

        public void setStartrow(int startrow) {
            this.startrow = startrow;
        }

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public String getUnitid() {
            return unitid;
        }

        public void setUnitid(String unitid) {
            this.unitid = unitid;
        }

        public String getUnitidshow() {
            return unitidshow;
        }

        public void setUnitidshow(String unitidshow) {
            this.unitidshow = unitidshow;
        }

        public String getUselimitnum() {
            return uselimitnum;
        }

        public void setUselimitnum(String uselimitnum) {
            this.uselimitnum = uselimitnum;
        }

        public String getValiddays() {
            return validdays;
        }

        public void setValiddays(String validdays) {
            this.validdays = validdays;
        }
    }
}
