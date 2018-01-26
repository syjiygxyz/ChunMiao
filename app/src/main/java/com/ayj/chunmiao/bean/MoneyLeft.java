package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2016/11/14.
 */
public class MoneyLeft {

    private int err;
    private String msg;
    private String total;


    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * currentColomu : null
         * currentpage : null
         * did :
         * discount : 100
         * dname :
         * endrow : 18
         * leveltype :
         * leveltypeshow :
         * modifydate : null
         * modifyuserid : null
         * modifyuseridshow :
         * msnid : 20161010140800000176
         * msnidshow : 东东
         * pagermethod : null
         * pagesize : null
         * shopid : 3
         * shopidshow : 吴中澄湖路店
         * snid : 41
         * sortChoose : desc
         * startrow : 0
         * totalmoney : 0.01
         */

        private LevelcardBean levelcard;
        private String smallmoney;
        private String use_smallmoney_discount_rate;
        private String use_smallmoney_discount_rate_sz;
        /**
         * cardlevel : null
         * cardlevelshow :
         * cardtype : null
         * cardtypeshow :
         * createdate : null
         * createuserid : null
         * createuseridshow :
         * currentColomu : null
         * currentpage : null
         * did :
         * dname :
         * endrow : 18
         * mcard : 10
         * modifydate : null
         * modifyuserid : null
         * modifyuseridshow :
         * msnid : null
         * msnidshow : null
         * pagermethod : null
         * pagesize : null
         * sfyx : null
         * sfyxshow :
         * shopid : 3
         * sortChoose : desc
         * startrow : 0
         * totalchargemoney : null
         * totalscore : null
         * usemoney : null
         * yue : 19.92
         */

        private List<CardListBean> cardList;
        /**
         * btime : 2016-10-11 00:00:00
         * createtime : 2016-10-11 17:12:52.0
         * currentColomu : null
         * currentpage : null
         * did :
         * dname :
         * endrow : 18
         * etime : 2016-12-10 00:00:00
         * getfrom : TICKETFROM001
         * getfromshow : 购买套餐赠送
         * msnid : 20161010140800000176
         * msnidshow : null
         * orderid : null
         * pagermethod : null
         * pagesize : null
         * shopid : null
         * shopidshow : null
         * snid : 6806
         * sortChoose : desc
         * startrow : 0
         * status : TICKETSTATUS001
         * statusshow : 可用
         * tickettype : WL-20160816-0000228
         * tickettypeshow : 汗蒸服务券
         * usetime : null
         */

        private List<TicketListBean> ticketList;

        public LevelcardBean getLevelcard() {
            return levelcard;
        }

        public void setLevelcard(LevelcardBean levelcard) {
            this.levelcard = levelcard;
        }

        public String getSmallmoney() {
            return smallmoney;
        }

        public void setSmallmoney(String smallmoney) {
            this.smallmoney = smallmoney;
        }

        public String getUse_smallmoney_discount_rate() {
            return use_smallmoney_discount_rate;
        }

        public void setUse_smallmoney_discount_rate(String use_smallmoney_discount_rate) {
            this.use_smallmoney_discount_rate = use_smallmoney_discount_rate;
        }

public String use_smallmoney_discount_rate_sz() {
            return use_smallmoney_discount_rate_sz;
        }

        public void use_smallmoney_discount_rate_sz(String use_smallmoney_discount_rate_sz) {
            this.use_smallmoney_discount_rate_sz = use_smallmoney_discount_rate_sz;
        }

        public String getUse_smallmoney_discount_rate_sz() {
            return use_smallmoney_discount_rate_sz;
        }

        public void setUse_smallmoney_discount_rate_sz(String use_smallmoney_discount_rate_sz) {
            this.use_smallmoney_discount_rate_sz = use_smallmoney_discount_rate_sz;
        }

        public List<CardListBean> getCardList() {
            return cardList;
        }

        public void setCardList(List<CardListBean> cardList) {
            this.cardList = cardList;
        }

        public List<TicketListBean> getTicketList() {
            return ticketList;
        }

        public void setTicketList(List<TicketListBean> ticketList) {
            this.ticketList = ticketList;
        }

        public static class LevelcardBean {
            private Object currentColomu;
            private Object currentpage;
            private String did;
            private String discount;
            private String dname;
            private int endrow;
            private String leveltype;
            private String leveltypeshow;
            private Object modifydate;
            private Object modifyuserid;
            private String modifyuseridshow;
            private String msnid;
            private String msnidshow;
            private Object pagermethod;
            private Object pagesize;
            private String shopid;
            private String shopidshow;
            private String snid;
            private String sortChoose;
            private int startrow;
            private String totalmoney;

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

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
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

            public String getLeveltype() {
                return leveltype;
            }

            public void setLeveltype(String leveltype) {
                this.leveltype = leveltype;
            }

            public String getLeveltypeshow() {
                return leveltypeshow;
            }

            public void setLeveltypeshow(String leveltypeshow) {
                this.leveltypeshow = leveltypeshow;
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

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }

            public String getShopidshow() {
                return shopidshow;
            }

            public void setShopidshow(String shopidshow) {
                this.shopidshow = shopidshow;
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

            public String getTotalmoney() {
                return totalmoney;
            }

            public void setTotalmoney(String totalmoney) {
                this.totalmoney = totalmoney;
            }
        }

        public static class CardListBean {
            private Object cardlevel;
            private String cardlevelshow;
            private Object cardtype;
            private String cardtypeshow;
            private Object createdate;
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
            private Object msnid;
            private Object msnidshow;
            private Object pagermethod;
            private Object pagesize;
            private Object sfyx;
            private String sfyxshow;
            private String shopid;
            private String sortChoose;
            private int startrow;
            private Object totalchargemoney;
            private Object totalscore;
            private Object usemoney;
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

            public Object getCardtype() {
                return cardtype;
            }

            public void setCardtype(Object cardtype) {
                this.cardtype = cardtype;
            }

            public String getCardtypeshow() {
                return cardtypeshow;
            }

            public void setCardtypeshow(String cardtypeshow) {
                this.cardtypeshow = cardtypeshow;
            }

            public Object getCreatedate() {
                return createdate;
            }

            public void setCreatedate(Object createdate) {
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

            public Object getMsnid() {
                return msnid;
            }

            public void setMsnid(Object msnid) {
                this.msnid = msnid;
            }

            public Object getMsnidshow() {
                return msnidshow;
            }

            public void setMsnidshow(Object msnidshow) {
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

            public Object getSfyx() {
                return sfyx;
            }

            public void setSfyx(Object sfyx) {
                this.sfyx = sfyx;
            }

            public String getSfyxshow() {
                return sfyxshow;
            }

            public void setSfyxshow(String sfyxshow) {
                this.sfyxshow = sfyxshow;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
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

            public Object getTotalchargemoney() {
                return totalchargemoney;
            }

            public void setTotalchargemoney(Object totalchargemoney) {
                this.totalchargemoney = totalchargemoney;
            }

            public Object getTotalscore() {
                return totalscore;
            }

            public void setTotalscore(Object totalscore) {
                this.totalscore = totalscore;
            }

            public Object getUsemoney() {
                return usemoney;
            }

            public void setUsemoney(Object usemoney) {
                this.usemoney = usemoney;
            }

            public String getYue() {
                return yue;
            }

            public void setYue(String yue) {
                this.yue = yue;
            }
        }

        public static class TicketListBean {
            private String btime;
            private String createtime;
            private Object currentColomu;
            private Object currentpage;
            private String did;
            private String dname;
            private int endrow;
            private String etime;
            private String getfrom;
            private String getfromshow;
            private String msnid;
            private Object msnidshow;
            private Object orderid;
            private Object pagermethod;
            private Object pagesize;
            private Object shopid;
            private Object shopidshow;
            private String snid;
            private String sortChoose;
            private int startrow;
            private String status;
            private String statusshow;
            private String tickettype;
            private String tickettypeshow;
            private Object usetime;

            public String getBtime() {
                return btime;
            }

            public void setBtime(String btime) {
                this.btime = btime;
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

            public String getGetfrom() {
                return getfrom;
            }

            public void setGetfrom(String getfrom) {
                this.getfrom = getfrom;
            }

            public String getGetfromshow() {
                return getfromshow;
            }

            public void setGetfromshow(String getfromshow) {
                this.getfromshow = getfromshow;
            }

            public String getMsnid() {
                return msnid;
            }

            public void setMsnid(String msnid) {
                this.msnid = msnid;
            }

            public Object getMsnidshow() {
                return msnidshow;
            }

            public void setMsnidshow(Object msnidshow) {
                this.msnidshow = msnidshow;
            }

            public Object getOrderid() {
                return orderid;
            }

            public void setOrderid(Object orderid) {
                this.orderid = orderid;
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

            public Object getShopid() {
                return shopid;
            }

            public void setShopid(Object shopid) {
                this.shopid = shopid;
            }

            public Object getShopidshow() {
                return shopidshow;
            }

            public void setShopidshow(Object shopidshow) {
                this.shopidshow = shopidshow;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStatusshow() {
                return statusshow;
            }

            public void setStatusshow(String statusshow) {
                this.statusshow = statusshow;
            }

            public String getTickettype() {
                return tickettype;
            }

            public void setTickettype(String tickettype) {
                this.tickettype = tickettype;
            }

            public String getTickettypeshow() {
                return tickettypeshow;
            }

            public void setTickettypeshow(String tickettypeshow) {
                this.tickettypeshow = tickettypeshow;
            }

            public Object getUsetime() {
                return usetime;
            }

            public void setUsetime(Object usetime) {
                this.usetime = usetime;
            }
        }
    }
}
