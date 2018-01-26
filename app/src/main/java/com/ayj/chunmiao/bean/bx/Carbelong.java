package com.ayj.chunmiao.bean.bx;

import java.util.List;

/**
 * Created by zht-pc-09 on 2016/12/15.
 */
public class Carbelong {

    /**
     * err : 0
     * msg :
     * total : 2
     * data : [{"canmodify":"1","currentColomu":null,"currentpage":null,"delflag":"0",
     * "dictid":"CARBELONG001","dicttypeid":"CARBELONG","did":"0000","dname":"","endrow":18,
     * "mark":null,"orderno":"1","pagermethod":null,"pagesize":null,"paramname":"京",
     * "paramremark":null,"parentid":null,"sortChoose":"desc","startrow":0},{"canmodify":"1",
     * "currentColomu":null,"currentpage":null,"delflag":"0","dictid":"CARBELONG002",
     * "dicttypeid":"CARBELONG","did":"0000","dname":"","endrow":18,"mark":null,"orderno":"2",
     * "pagermethod":null,"pagesize":null,"paramname":"沪","paramremark":null,"parentid":null,
     * "sortChoose":"desc","startrow":0}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * canmodify : 1
     * currentColomu : null
     * currentpage : null
     * delflag : 0
     * dictid : CARBELONG001
     * dicttypeid : CARBELONG
     * did : 0000
     * dname :
     * endrow : 18
     * mark : null
     * orderno : 1
     * pagermethod : null
     * pagesize : null
     * paramname : 京
     * paramremark : null
     * parentid : null
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
        private String canmodify;
        private Object currentColomu;
        private Object currentpage;
        private String delflag;
        private String dictid;
        private String dicttypeid;
        private String did;
        private String dname;
        private int endrow;
        private Object mark;
        private String orderno;
        private Object pagermethod;
        private Object pagesize;
        private String paramname;
        private Object paramremark;
        private Object parentid;
        private String sortChoose;
        private int startrow;

        public String getCanmodify() {
            return canmodify;
        }

        public void setCanmodify(String canmodify) {
            this.canmodify = canmodify;
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

        public String getDictid() {
            return dictid;
        }

        public void setDictid(String dictid) {
            this.dictid = dictid;
        }

        public String getDicttypeid() {
            return dicttypeid;
        }

        public void setDicttypeid(String dicttypeid) {
            this.dicttypeid = dicttypeid;
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

        public Object getMark() {
            return mark;
        }

        public void setMark(Object mark) {
            this.mark = mark;
        }

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
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

        public String getParamname() {
            return paramname;
        }

        public void setParamname(String paramname) {
            this.paramname = paramname;
        }

        public Object getParamremark() {
            return paramremark;
        }

        public void setParamremark(Object paramremark) {
            this.paramremark = paramremark;
        }

        public Object getParentid() {
            return parentid;
        }

        public void setParentid(Object parentid) {
            this.parentid = parentid;
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
