package com.ayj.chunmiao.bean.shopping;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-09 on 2016/7/30.
 */
public class Address implements Serializable {

    public static final long serialVersionUID = -1982978541338666696L;

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"areaid":"Area_00006","areaidshow":null,"cityid":"City_00006","cityidshow":null,"createtime":"2016-07-30 13:11:22","currentColomu":null,"currentpage":null,"detailaddr":"就","did":"","dname":"","endrow":18,"isdefaultaddr":"SFPD002","isdefaultaddrshow":"否","mobile":"15062249050","msnid":"20160729173500000103","pagermethod":null,"pagesize":null,"postcode":"325489","provinceid":"Province_00006","provinceidshow":null,"receivename":"12","snid":"135","sortChoose":"desc","startrow":0,"tel":null}]
     */

    private int err;
    private String msg;
    private int total;
    /**
     * areaid : Area_00006
     * areaidshow : null
     * cityid : City_00006
     * cityidshow : null
     * createtime : 2016-07-30 13:11:22
     * currentColomu : null
     * currentpage : null
     * detailaddr : 就
     * did :
     * dname :
     * endrow : 18
     * isdefaultaddr : SFPD002
     * isdefaultaddrshow : 否
     * mobile : 15062249050
     * msnid : 20160729173500000103
     * pagermethod : null
     * pagesize : null
     * postcode : 325489
     * provinceid : Province_00006
     * provinceidshow : null
     * receivename : 12
     * snid : 135
     * sortChoose : desc
     * startrow : 0
     * tel : null
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private String areaid;
        private Object areaidshow;
        private String cityid;
        private Object cityidshow;
        private String createtime;
        private Object currentColomu;
        private Object currentpage;
        private String detailaddr;
        private String did;
        private String dname;
        private int endrow;
        private String isdefaultaddr;
        private String isdefaultaddrshow;
        private String mobile;
        private String msnid;
        private Object pagermethod;
        private Object pagesize;
        private String postcode;
        private String provinceid;
        private Object provinceidshow;
        private String receivename;
        private String snid;
        private String sortChoose;
        private int startrow;
        private Object tel;

        public String getAreaid() {
            return areaid;
        }

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public Object getAreaidshow() {
            return areaidshow;
        }

        public void setAreaidshow(Object areaidshow) {
            this.areaidshow = areaidshow;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public Object getCityidshow() {
            return cityidshow;
        }

        public void setCityidshow(Object cityidshow) {
            this.cityidshow = cityidshow;
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

        public String getDetailaddr() {
            return detailaddr;
        }

        public void setDetailaddr(String detailaddr) {
            this.detailaddr = detailaddr;
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

        public String getIsdefaultaddr() {
            return isdefaultaddr;
        }

        public void setIsdefaultaddr(String isdefaultaddr) {
            this.isdefaultaddr = isdefaultaddr;
        }

        public String getIsdefaultaddrshow() {
            return isdefaultaddrshow;
        }

        public void setIsdefaultaddrshow(String isdefaultaddrshow) {
            this.isdefaultaddrshow = isdefaultaddrshow;
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

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public Object getProvinceidshow() {
            return provinceidshow;
        }

        public void setProvinceidshow(Object provinceidshow) {
            this.provinceidshow = provinceidshow;
        }

        public String getReceivename() {
            return receivename;
        }

        public void setReceivename(String receivename) {
            this.receivename = receivename;
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

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "areaid='" + areaid + '\'' +
                    ", areaidshow=" + areaidshow +
                    ", cityid='" + cityid + '\'' +
                    ", cityidshow=" + cityidshow +
                    ", createtime='" + createtime + '\'' +
                    ", currentColomu=" + currentColomu +
                    ", currentpage=" + currentpage +
                    ", detailaddr='" + detailaddr + '\'' +
                    ", did='" + did + '\'' +
                    ", dname='" + dname + '\'' +
                    ", endrow=" + endrow +
                    ", isdefaultaddr='" + isdefaultaddr + '\'' +
                    ", isdefaultaddrshow='" + isdefaultaddrshow + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", msnid='" + msnid + '\'' +
                    ", pagermethod=" + pagermethod +
                    ", pagesize=" + pagesize +
                    ", postcode='" + postcode + '\'' +
                    ", provinceid='" + provinceid + '\'' +
                    ", provinceidshow=" + provinceidshow +
                    ", receivename='" + receivename + '\'' +
                    ", snid='" + snid + '\'' +
                    ", sortChoose='" + sortChoose + '\'' +
                    ", startrow=" + startrow +
                    ", tel=" + tel +
                    '}';
        }
    }


}
