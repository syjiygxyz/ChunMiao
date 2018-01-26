package com.ayj.chunmiao.bean.myinformation;

import java.util.List;

/**
 * Created by zht-pc-09 on 2016/9/9.
 */
public class Company {

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"appname":"bf","compaddr":"吴中中润中心","compname":"苏州爱医家","compwebsite":"www.21ga
     * .cn","currentColomu":null,"currentpage":null,"did":"","dname":"","endrow":18,
     * "pagermethod":null,"pagesize":null,"sortChoose":"desc","startrow":0}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * appname : bf
     * compaddr : 吴中中润中心
     * compname : 苏州爱医家
     * compwebsite : www.21ga.cn
     * currentColomu : null
     * currentpage : null
     * did :
     * dname :
     * endrow : 18
     * pagermethod : null
     * pagesize : null
     * sortChoose : desc
     * startrow : 0
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
        private String appname;
        private String compaddr;
        private String compname;
        private String compwebsite;
        private Object currentColomu;
        private Object currentpage;
        private String did;
        private String dname;
        private int endrow;
        private Object pagermethod;
        private Object pagesize;
        private String sortChoose;
        private int startrow;

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getCompaddr() {
            return compaddr;
        }

        public void setCompaddr(String compaddr) {
            this.compaddr = compaddr;
        }

        public String getCompname() {
            return compname;
        }

        public void setCompname(String compname) {
            this.compname = compname;
        }

        public String getCompwebsite() {
            return compwebsite;
        }

        public void setCompwebsite(String compwebsite) {
            this.compwebsite = compwebsite;
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
    }
}
