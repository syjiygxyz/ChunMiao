package com.ayj.chunmiao.bean.cmbz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-04 on 2017/7/12.
 */

public class VehicleBean implements Serializable {
    public static final long serialVersionUID = -1982528541338555696L;

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"carbelong":"CARBELONG001","carbelongshow":"京","carbrand":"？￥￥è？a",
     * "carbrandshow":null,"carlines":"2","carno":"123456","createtime":"2016-12-15 17:23:13",
     * "currentColomu":null,"currentpage":null,"did":"","dname":"","endrow":18,
     * "engineno":"123456","idcard":"321283199210035155","mobile":"15062249050",
     * "msnid":"20161010140300000175","name":"？？？？￡？","pagermethod":null,"pagesize":null,
     * "snid":"6","sortChoose":"desc","startrow":0,"vin":"123456"}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * carbelong : CARBELONG001
     * carbelongshow : 京
     * carbrand : ？￥￥è？a
     * carbrandshow : null
     * carlines : 2
     * carno : 123456
     * createtime : 2016-12-15 17:23:13
     * currentColomu : null
     * currentpage : null
     * did :
     * dname :
     * endrow : 18
     * engineno : 123456
     * idcard : 321283199210035155
     * mobile : 15062249050
     * msnid : 20161010140300000175
     * name : ？？？？￡？
     * pagermethod : null
     * pagesize : null
     * snid : 6
     * sortChoose : desc
     * startrow : 0
     * vin : 123456
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
        private String carbelong;
        private String carbelongshow;
        private String carbrand;
        private String carbrandshow;
        private String carlines;
        private String carno;
        private String createtime;
        private String currentColomu;
        private String currentpage;
        private String did;
        private String dname;
        private int endrow;
        private String engineno;
        private String idcard;
        private String mobile;
        private String msnid;
        private String name;
        private String pagermethod;
        private String pagesize;
        private String snid;
        private String sortChoose;
        private int startrow;
        private String vin;
        private boolean isnoCheck;

        public boolean isnoCheck() {
            return isnoCheck;
        }

        public void setIsnoCheck(boolean isnoCheck) {
            this.isnoCheck = isnoCheck;
        }

        public String getCarbelong() {
            return carbelong;
        }

        public void setCarbelong(String carbelong) {
            this.carbelong = carbelong;
        }

        public String getCarbelongshow() {
            return carbelongshow;
        }

        public void setCarbelongshow(String carbelongshow) {
            this.carbelongshow = carbelongshow;
        }

        public String getCarbrand() {
            return carbrand;
        }

        public void setCarbrand(String carbrand) {
            this.carbrand = carbrand;
        }

        public String getCarbrandshow() {
            return carbrandshow;
        }

        public void setCarbrandshow(String carbrandshow) {
            this.carbrandshow = carbrandshow;
        }

        public String getCarlines() {
            return carlines;
        }

        public void setCarlines(String carlines) {
            this.carlines = carlines;
        }

        public String getCarno() {
            return carno;
        }

        public void setCarno(String carno) {
            this.carno = carno;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getCurrentColomu() {
            return currentColomu;
        }

        public void setCurrentColomu(String currentColomu) {
            this.currentColomu = currentColomu;
        }

        public String getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(String currentpage) {
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

        public String getEngineno() {
            return engineno;
        }

        public void setEngineno(String engineno) {
            this.engineno = engineno;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMsnid() {
            return msnid;
        }

        public void setMsnid(String msnid) {
            this.msnid = msnid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPagermethod() {
            return pagermethod;
        }

        public void setPagermethod(String pagermethod) {
            this.pagermethod = pagermethod;
        }

        public String getPagesize() {
            return pagesize;
        }

        public void setPagesize(String pagesize) {
            this.pagesize = pagesize;
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

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }
    }
}
