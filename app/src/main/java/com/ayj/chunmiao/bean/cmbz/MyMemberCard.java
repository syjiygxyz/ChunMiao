package com.ayj.chunmiao.bean.cmbz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/1/5.
 */
public class MyMemberCard implements Serializable {

    public static final long serialVersionUID = -1982978532338666696L;

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"cardlevel":null,"cardlevelshow":"","cardmatid":null,"cardmatidshow":"",
     * "cardproperty":null,"cardpropertyshow":"","cardtype":"CARDTYPE004","cardtypeshow":"康乐会员卡",
     * "createdate":"2017-01-04 09:23:51","createuserid":null,"createuseridshow":"",
     * "currentColomu":null,"currentpage":null,"did":"","dname":"","endrow":18,"mcard":"21",
     * "modifydate":null,"modifyuserid":null,"modifyuseridshow":"",
     * "msnid":"20161010140300000175","msnidshow":"了8恶疾","pagermethod":null,"pagesize":null,
     * "sfyx":"SFYX001","sfyxshow":"有效","shopid":null,"sortChoose":"desc","startrow":0,
     * "totalchargemoney":"0.01","totalscore":null,"uselimitnum":"10","usemoney":null,
     * "validatetime":"2018-01-04 09:23:51.0","validdays":"365","yue":"3000"}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * cardlevel : null
     * cardlevelshow :
     * cardmatid : null
     * cardmatidshow :
     * cardproperty : null
     * cardpropertyshow :
     * cardtype : CARDTYPE004
     * cardtypeshow : 康乐会员卡
     * createdate : 2017-01-04 09:23:51
     * createuserid : null
     * createuseridshow :
     * currentColomu : null
     * currentpage : null
     * did :
     * dname :
     * endrow : 18
     * mcard : 21
     * modifydate : null
     * modifyuserid : null
     * modifyuseridshow :
     * msnid : 20161010140300000175
     * msnidshow : 了8恶疾
     * pagermethod : null
     * pagesize : null
     * sfyx : SFYX001
     * sfyxshow : 有效
     * shopid : null
     * sortChoose : desc
     * startrow : 0
     * totalchargemoney : 0.01
     * totalscore : null
     * uselimitnum : 10
     * usemoney : null
     * validatetime : 2018-01-04 09:23:51.0
     * validdays : 365
     * yue : 3000
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
        private Object cardlevel;
        private String cardlevelshow;
        private Object cardmatid;
        private String cardmatidshow;
        private Object cardproperty;
        private String cardpropertyshow;
        private String cardtype;
        private String cardtypeshow;
        private String createdate;
        private Object createuserid;
        private String createuseridshow;
        private Object currentColomu;
        private Object currentpage;
        private String did;
        private String dname;
        private int endrow;
        private String mcard;
        private Object modifydate;
        private Object modifyuserid;
        private String modifyuseridshow;
        private String msnid;
        private String msnidshow;
        private Object pagermethod;
        private Object pagesize;
        private String sfyx;
        private String sfyxshow;
        private Object shopid;
        private String sortChoose;
        private int startrow;
        private String totalchargemoney;
        private Object totalscore;
        private String uselimitnum;
        private Object usemoney;
        private String validatetime;
        private String validdays;
        private String yue;

        public Object getCardlevel() {
            return cardlevel;
        }

        public void setCardlevel(Object cardlevel) {
            this.cardlevel = cardlevel;
        }

        public String getCardlevelshow() {
            return cardlevelshow;
        }

        public void setCardlevelshow(String cardlevelshow) {
            this.cardlevelshow = cardlevelshow;
        }

        public Object getCardmatid() {
            return cardmatid;
        }

        public void setCardmatid(Object cardmatid) {
            this.cardmatid = cardmatid;
        }

        public String getCardmatidshow() {
            return cardmatidshow;
        }

        public void setCardmatidshow(String cardmatidshow) {
            this.cardmatidshow = cardmatidshow;
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

        public String getCardtype() {
            return cardtype;
        }

        public void setCardtype(String cardtype) {
            this.cardtype = cardtype;
        }

        public String getCardtypeshow() {
            return cardtypeshow;
        }

        public void setCardtypeshow(String cardtypeshow) {
            this.cardtypeshow = cardtypeshow;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public Object getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(Object createuserid) {
            this.createuserid = createuserid;
        }

        public String getCreateuseridshow() {
            return createuseridshow;
        }

        public void setCreateuseridshow(String createuseridshow) {
            this.createuseridshow = createuseridshow;
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

        public int getEndrow() {
            return endrow;
        }

        public void setEndrow(int endrow) {
            this.endrow = endrow;
        }

        public String getMcard() {
            return mcard;
        }

        public void setMcard(String mcard) {
            this.mcard = mcard;
        }

        public Object getModifydate() {
            return modifydate;
        }

        public void setModifydate(Object modifydate) {
            this.modifydate = modifydate;
        }

        public Object getModifyuserid() {
            return modifyuserid;
        }

        public void setModifyuserid(Object modifyuserid) {
            this.modifyuserid = modifyuserid;
        }

        public String getModifyuseridshow() {
            return modifyuseridshow;
        }

        public void setModifyuseridshow(String modifyuseridshow) {
            this.modifyuseridshow = modifyuseridshow;
        }

        public String getMsnid() {
            return msnid;
        }

        public void setMsnid(String msnid) {
            this.msnid = msnid;
        }

        public String getMsnidshow() {
            return msnidshow;
        }

        public void setMsnidshow(String msnidshow) {
            this.msnidshow = msnidshow;
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

        public Object getShopid() {
            return shopid;
        }

        public void setShopid(Object shopid) {
            this.shopid = shopid;
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

        public String getTotalchargemoney() {
            return totalchargemoney;
        }

        public void setTotalchargemoney(String totalchargemoney) {
            this.totalchargemoney = totalchargemoney;
        }

        public Object getTotalscore() {
            return totalscore;
        }

        public void setTotalscore(Object totalscore) {
            this.totalscore = totalscore;
        }

        public String getUselimitnum() {
            return uselimitnum;
        }

        public void setUselimitnum(String uselimitnum) {
            this.uselimitnum = uselimitnum;
        }

        public Object getUsemoney() {
            return usemoney;
        }

        public void setUsemoney(Object usemoney) {
            this.usemoney = usemoney;
        }

        public String getValidatetime() {
            return validatetime;
        }

        public void setValidatetime(String validatetime) {
            this.validatetime = validatetime;
        }

        public String getValiddays() {
            return validdays;
        }

        public void setValiddays(String validdays) {
            this.validdays = validdays;
        }

        public String getYue() {
            return yue;
        }

        public void setYue(String yue) {
            this.yue = yue;
        }
    }
}
