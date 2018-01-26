package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2016/8/19.
 */
public class AreaInfo {

    /**
     * err : 0
     * msg :
     * total : 35
     * data : [{"aid":"48","childCount":"0","createdate":"2015-11-30 10:00:44.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"深圳市","orderno":50,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"49","childCount":"0","createdate":"2015-11-30 10:00:44.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"天津市","orderno":28,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"11","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"北京市","orderno":1,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"13","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"河北省","orderno":3,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"14","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"山西省","orderno":4,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"15","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"内蒙古自治区","orderno":5,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"21","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"辽宁省","orderno":6,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"22","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"吉林省","orderno":7,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"23","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"黑龙江省","orderno":8,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0},{"aid":"31","childCount":"0","createdate":"2015-11-30 10:00:43.0","createman":"0000","currentColomu":null,"currentpage":null,"delflag":"0","did":"","dname":"","endrow":18,"isParent":null,"isRoot":null,"memo":null,"modifydate":null,"modifyman":null,"name":"上海市","orderno":9,"pagermethod":null,"pagesize":null,"parentname":null,"pid":"0001","sortChoose":"desc","startrow":0}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * aid : 48
     * childCount : 0
     * createdate : 2015-11-30 10:00:44.0
     * createman : 0000
     * currentColomu : null
     * currentpage : null
     * delflag : 0
     * did :
     * dname :
     * endrow : 18
     * isParent : null
     * isRoot : null
     * memo : null
     * modifydate : null
     * modifyman : null
     * name : 深圳市
     * orderno : 50
     * pagermethod : null
     * pagesize : null
     * parentname : null
     * pid : 0001
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
        private String aid;
        private String childCount;
        private String createdate;
        private String createman;
        private Object currentColomu;
        private Object currentpage;
        private String delflag;
        private String did;
        private String dname;
        private int endrow;
        private Object isParent;
        private Object isRoot;
        private Object memo;
        private Object modifydate;
        private Object modifyman;
        private String name;
        private int orderno;
        private Object pagermethod;
        private Object pagesize;
        private Object parentname;
        private String pid;
        private String sortChoose;
        private int startrow;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getChildCount() {
            return childCount;
        }

        public void setChildCount(String childCount) {
            this.childCount = childCount;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getCreateman() {
            return createman;
        }

        public void setCreateman(String createman) {
            this.createman = createman;
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

        public String getDelflag() {
            return delflag;
        }

        public void setDelflag(String delflag) {
            this.delflag = delflag;
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

        public Object getIsParent() {
            return isParent;
        }

        public void setIsParent(Object isParent) {
            this.isParent = isParent;
        }

        public Object getIsRoot() {
            return isRoot;
        }

        public void setIsRoot(Object isRoot) {
            this.isRoot = isRoot;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public Object getModifydate() {
            return modifydate;
        }

        public void setModifydate(Object modifydate) {
            this.modifydate = modifydate;
        }

        public Object getModifyman() {
            return modifyman;
        }

        public void setModifyman(Object modifyman) {
            this.modifyman = modifyman;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrderno() {
            return orderno;
        }

        public void setOrderno(int orderno) {
            this.orderno = orderno;
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

        public Object getParentname() {
            return parentname;
        }

        public void setParentname(Object parentname) {
            this.parentname = parentname;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
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
