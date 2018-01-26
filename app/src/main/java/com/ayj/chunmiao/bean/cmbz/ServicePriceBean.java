package com.ayj.chunmiao.bean.cmbz;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-04 on 2016/9/29.
 */
public class ServicePriceBean implements Serializable {

    public static final long serialVersionUID = -1982978541338666967L;
    /**
     * err : 0
     * msg :
     * total : 4
     * data : [{"alias":"艾灸调理","brand":"BRANDS008","brdcls":"BRDCLS001","bunitid":"2015081010341","bunitname":null,"clsfy":null,"ctime":"2016-07-21T11:41:43","currentColomu":null,"currentpage":null,"cuserid":"0000","cusername":null,"delflag":"0","did":"0000","dname":"情满康美总公司","endrow":18,"funccode":null,"imgurl":"2016-08-18/0000_20160818164743000296.jpg","isbatchm":null,"isexist":false,"matid":"WL-20151126-0000088","matname":"艾灸调理","mdetail":"tertiary","mtime":null,"muserid":null,"musername":null,"pagermethod":null,"pagesize":null,"ps":"PS002","psshow":"服务","sname":"艾灸调理","sort":"SORTS005","sortChoose":"desc","standard":"人","standardsaleprice":"20","startrow":0,"status":null,"sunitid":"2015081010341","sunitname":null},{"alias":"超声理疗","brand":"BRANDS021","brdcls":"BRDCLS002","bunitid":"2015081010341","bunitname":null,"clsfy":null,"ctime":"2016-04-25T16:22:43","currentColomu":null,"currentpage":null,"cuserid":"0000","cusername":null,"delflag":"0","did":"0000","dname":"情满康美总公司","endrow":18,"funccode":null,"imgurl":null,"isbatchm":null,"isexist":false,"matid":"WL-20151126-0000089","matname":"超声理疗","mdetail":"超声理疗","mtime":null,"muserid":null,"musername":null,"pagermethod":null,"pagesize":null,"ps":"PS002","psshow":"服务","sname":"超声理疗","sort":"SORTS010","sortChoose":"desc","standard":"QB-2323","standardsaleprice":"20","startrow":0,"status":null,"sunitid":"2015081010341","sunitname":null},{"alias":"负离子汗蒸","brand":"BRANDS021","brdcls":"BRDCLS002","bunitid":"2015081010341","bunitname":null,"clsfy":null,"ctime":"2016-04-25T16:22:43","currentColomu":null,"currentpage":null,"cuserid":"0000","cusername":null,"delflag":"0","did":"0000","dname":"情满康美总公司","endrow":18,"funccode":null,"imgurl":null,"isbatchm":null,"isexist":false,"matid":"WL-20151126-0000094","matname":"负离子汗蒸","mdetail":"负离子汗蒸","mtime":null,"muserid":null,"musername":null,"pagermethod":null,"pagesize":null,"ps":"PS002","psshow":"服务","sname":"负离子汗蒸","sort":"SORTS010","sortChoose":"desc","standard":"QB-2323","standardsaleprice":"20","startrow":0,"status":null,"sunitid":"2015081010341","sunitname":null},{"alias":"营养吧","brand":"BRANDS021","brdcls":"BRDCLS002","bunitid":"2015081010341","bunitname":null,"clsfy":null,"ctime":"2016-04-25T16:22:43","currentColomu":null,"currentpage":null,"cuserid":"0000","cusername":null,"delflag":"0","did":"0000","dname":"情满康美总公司","endrow":18,"funccode":null,"imgurl":"2016-08-16/0000_20160816132508000626.png","isbatchm":null,"isexist":false,"matid":"WL-20151126-0000095","matname":"营养吧","mdetail":"玩儿我","mtime":null,"muserid":null,"musername":null,"pagermethod":null,"pagesize":null,"ps":"PS002","psshow":"服务","sname":"营养吧","sort":"SORTS010","sortChoose":"desc","standard":"QB-2323","standardsaleprice":"105","startrow":0,"status":null,"sunitid":"2015081010341","sunitname":null}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * alias : 艾灸调理
     * brand : BRANDS008
     * brdcls : BRDCLS001
     * bunitid : 2015081010341
     * bunitname : null
     * clsfy : null
     * ctime : 2016-07-21T11:41:43
     * currentColomu : null
     * currentpage : null
     * cuserid : 0000
     * cusername : null
     * delflag : 0
     * did : 0000
     * dname : 情满康美总公司
     * endrow : 18
     * funccode : null
     * imgurl : 2016-08-18/0000_20160818164743000296.jpg
     * isbatchm : null
     * isexist : false
     * matid : WL-20151126-0000088
     * matname : 艾灸调理
     * mdetail : tertiary
     * mtime : null
     * muserid : null
     * musername : null
     * pagermethod : null
     * pagesize : null
     * ps : PS002
     * psshow : 服务
     * sname : 艾灸调理
     * sort : SORTS005
     * sortChoose : desc
     * standard : 人
     * standardsaleprice : 20
     * startrow : 0
     * status : null
     * sunitid : 2015081010341
     * sunitname : null
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
        private String alias;
        private String brand;
        private String brdcls;
        private String bunitid;
        private Object bunitname;
        private Object clsfy;
        private String ctime;
        private Object currentColomu;
        private Object currentpage;
        private String cuserid;
        private Object cusername;
        private String delflag;
        private String did;
        private String dname;
        private int endrow;
        private Object funccode;
        private String imgurl;
        private Object isbatchm;
        private boolean isexist;
        private String matid;
        private String matname;
        private String mdetail;
        private Object mtime;
        private Object muserid;
        private Object musername;
        private Object pagermethod;
        private Object pagesize;
        private String ps;
        private String psshow;
        private String sname;
        private String sort;
        private String sortChoose;
        private String standard;
        private String standardsaleprice;
        private int startrow;
        private Object status;
        private String sunitid;
        private Object sunitname;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getBrdcls() {
            return brdcls;
        }

        public void setBrdcls(String brdcls) {
            this.brdcls = brdcls;
        }

        public String getBunitid() {
            return bunitid;
        }

        public void setBunitid(String bunitid) {
            this.bunitid = bunitid;
        }

        public Object getBunitname() {
            return bunitname;
        }

        public void setBunitname(Object bunitname) {
            this.bunitname = bunitname;
        }

        public Object getClsfy() {
            return clsfy;
        }

        public void setClsfy(Object clsfy) {
            this.clsfy = clsfy;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
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

        public String getCuserid() {
            return cuserid;
        }

        public void setCuserid(String cuserid) {
            this.cuserid = cuserid;
        }

        public Object getCusername() {
            return cusername;
        }

        public void setCusername(Object cusername) {
            this.cusername = cusername;
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

        public Object getFunccode() {
            return funccode;
        }

        public void setFunccode(Object funccode) {
            this.funccode = funccode;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public Object getIsbatchm() {
            return isbatchm;
        }

        public void setIsbatchm(Object isbatchm) {
            this.isbatchm = isbatchm;
        }

        public boolean isIsexist() {
            return isexist;
        }

        public void setIsexist(boolean isexist) {
            this.isexist = isexist;
        }

        public String getMatid() {
            return matid;
        }

        public void setMatid(String matid) {
            this.matid = matid;
        }

        public String getMatname() {
            return matname;
        }

        public void setMatname(String matname) {
            this.matname = matname;
        }

        public String getMdetail() {
            return mdetail;
        }

        public void setMdetail(String mdetail) {
            this.mdetail = mdetail;
        }

        public Object getMtime() {
            return mtime;
        }

        public void setMtime(Object mtime) {
            this.mtime = mtime;
        }

        public Object getMuserid() {
            return muserid;
        }

        public void setMuserid(Object muserid) {
            this.muserid = muserid;
        }

        public Object getMusername() {
            return musername;
        }

        public void setMusername(Object musername) {
            this.musername = musername;
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

        public String getPs() {
            return ps;
        }

        public void setPs(String ps) {
            this.ps = ps;
        }

        public String getPsshow() {
            return psshow;
        }

        public void setPsshow(String psshow) {
            this.psshow = psshow;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getSortChoose() {
            return sortChoose;
        }

        public void setSortChoose(String sortChoose) {
            this.sortChoose = sortChoose;
        }

        public String getStandard() {
            return standard;
        }

        public void setStandard(String standard) {
            this.standard = standard;
        }

        public String getStandardsaleprice() {
            return standardsaleprice;
        }

        public void setStandardsaleprice(String standardsaleprice) {
            this.standardsaleprice = standardsaleprice;
        }

        public int getStartrow() {
            return startrow;
        }

        public void setStartrow(int startrow) {
            this.startrow = startrow;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getSunitid() {
            return sunitid;
        }

        public void setSunitid(String sunitid) {
            this.sunitid = sunitid;
        }

        public Object getSunitname() {
            return sunitname;
        }

        public void setSunitname(Object sunitname) {
            this.sunitname = sunitname;
        }
    }
}
