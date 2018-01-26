package com.ayj.chunmiao.bean.bx;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28.
 */
public class MainBxBottom {

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"hotList":[{"snid":"61","matid":null,"btime":null,"etime":null,"sfyx":null,"matclass":"INSURANCECLSONE008","nowprice":null,"beforeprice":null,"ecomment":"行李险","totalnum":null,"schemeids":null,"bzqxstart":null,"bzqxstep":null,"bzqxmax":null,"bzqxexception":null,"bzqxunit":null,"unitid":null,"insurancebrand":null,"matidshow":null,"productfeature":null,"productdescription":null,"insuranceinformation":null,"claimservice":null,"matclassshow":"行李险","unitidshow":null,"sfyxshow":"","createtime":null,"matstyle":null,"matclass2":null,"matclass2show":"","agerequest":null,"maxcoverage":null,"minpremium":null,"fitpeople":null,"insurancebrandshow":"","bzqxunitshow":"","schemeidsshow":null,"inscentertype":null,"inscentertypeshow":"","status":null,"funccode":null,"orderno":null,"shopid":null,"sunitid":null,"shoppurchaseprice":null,"ticketusescope":null,"standardsaleprice":null,"matname":null,"ps":null,"audituserid":null,"ticketusescopeshow":null,"cuserid":null,"ctime":null,"muserid":null,"mtime":null,"auditstatus":null,"mdetail":null,"sname":null,"bunitid":null,"delflag":null,"shopidshow":"","cusername":"","musername":"","isexist":false,"clsfy":null,"brand":null,"standard":null,"isbatchm":null,"bunitname":"","sunitname":"","psshow":"","auditstatusshow":"","audituseridshow":"","audittime":null,"auditcomment":null,"clsfyshow":"","brandshow":"","introducemediaid":null,"agreementid":null,"introducemediaidshow":"","logoimgurl":null,"logoimgurlshow":"","logoimgurlcompressshow":"","agreementidshowtitle":"","agreementidshowcomment":"","isbatchmshow":"否","barcode":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""}],"selectedList":[{"snid":"64","matid":null,"btime":null,"etime":null,"sfyx":null,"matclass":"INSURANCECLSONE008","nowprice":null,"beforeprice":null,"ecomment":"定期（多次）","totalnum":null,"schemeids":null,"bzqxstart":null,"bzqxstep":null,"bzqxmax":null,"bzqxexception":null,"bzqxunit":null,"unitid":null,"insurancebrand":null,"matidshow":null,"productfeature":null,"productdescription":null,"insuranceinformation":null,"claimservice":null,"matclassshow":"行李险","unitidshow":null,"sfyxshow":"","createtime":null,"matstyle":null,"matclass2":null,"matclass2show":"","agerequest":null,"maxcoverage":null,"minpremium":null,"fitpeople":null,"insurancebrandshow":"","bzqxunitshow":"","schemeidsshow":null,"inscentertype":null,"inscentertypeshow":"","status":null,"funccode":null,"orderno":null,"shopid":null,"sunitid":null,"shoppurchaseprice":null,"ticketusescope":null,"standardsaleprice":null,"matname":null,"ps":null,"audituserid":null,"ticketusescopeshow":null,"cuserid":null,"ctime":null,"muserid":null,"mtime":null,"auditstatus":null,"mdetail":null,"sname":null,"bunitid":null,"delflag":null,"shopidshow":"","cusername":"","musername":"","isexist":false,"clsfy":null,"brand":null,"standard":null,"isbatchm":null,"bunitname":"","sunitname":"","psshow":"","auditstatusshow":"","audituseridshow":"","audittime":null,"auditcomment":null,"clsfyshow":"","brandshow":"","introducemediaid":null,"agreementid":null,"introducemediaidshow":"","logoimgurl":null,"logoimgurlshow":"","logoimgurlcompressshow":"","agreementidshowtitle":"","agreementidshowcomment":"","isbatchmshow":"否","barcode":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]}]
     */

    private int err;
    private String msg;
    private String total;
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
        private List<HotListBean> hotList;
        private List<SelectedListBean> selectedList;

        public List<HotListBean> getHotList() {
            return hotList;
        }

        public void setHotList(List<HotListBean> hotList) {
            this.hotList = hotList;
        }

        public List<SelectedListBean> getSelectedList() {
            return selectedList;
        }

        public void setSelectedList(List<SelectedListBean> selectedList) {
            this.selectedList = selectedList;
        }

        public static class HotListBean {
            /**
             * snid : 61
             * matid : null
             * btime : null
             * etime : null
             * sfyx : null
             * matclass : INSURANCECLSONE008
             * nowprice : null
             * beforeprice : null
             * ecomment : 行李险
             * totalnum : null
             * schemeids : null
             * bzqxstart : null
             * bzqxstep : null
             * bzqxmax : null
             * bzqxexception : null
             * bzqxunit : null
             * unitid : null
             * insurancebrand : null
             * matidshow : null
             * productfeature : null
             * productdescription : null
             * insuranceinformation : null
             * claimservice : null
             * matclassshow : 行李险
             * unitidshow : null
             * sfyxshow :
             * createtime : null
             * matstyle : null
             * matclass2 : null
             * matclass2show :
             * agerequest : null
             * maxcoverage : null
             * minpremium : null
             * fitpeople : null
             * insurancebrandshow :
             * bzqxunitshow :
             * schemeidsshow : null
             * inscentertype : null
             * inscentertypeshow :
             * status : null
             * funccode : null
             * orderno : null
             * shopid : null
             * sunitid : null
             * shoppurchaseprice : null
             * ticketusescope : null
             * standardsaleprice : null
             * matname : null
             * ps : null
             * audituserid : null
             * ticketusescopeshow : null
             * cuserid : null
             * ctime : null
             * muserid : null
             * mtime : null
             * auditstatus : null
             * mdetail : null
             * sname : null
             * bunitid : null
             * delflag : null
             * shopidshow :
             * cusername :
             * musername :
             * isexist : false
             * clsfy : null
             * brand : null
             * standard : null
             * isbatchm : null
             * bunitname :
             * sunitname :
             * psshow :
             * auditstatusshow :
             * audituseridshow :
             * audittime : null
             * auditcomment : null
             * clsfyshow :
             * brandshow :
             * introducemediaid : null
             * agreementid : null
             * introducemediaidshow :
             * logoimgurl : null
             * logoimgurlshow :
             * logoimgurlcompressshow :
             * agreementidshowtitle :
             * agreementidshowcomment :
             * isbatchmshow : 否
             * barcode : null
             * imgurl : null
             * imgurlshow :
             * imgurlcompressshow :
             */

            private String snid;
            private Object matid;
            private Object btime;
            private Object etime;
            private Object sfyx;
            private String matclass;
            private Object nowprice;
            private Object beforeprice;
            private String ecomment;
            private Object totalnum;
            private Object schemeids;
            private Object bzqxstart;
            private Object bzqxstep;
            private Object bzqxmax;
            private Object bzqxexception;
            private Object bzqxunit;
            private Object unitid;
            private Object insurancebrand;
            private Object matidshow;
            private Object productfeature;
            private Object productdescription;
            private Object insuranceinformation;
            private Object claimservice;
            private String matclassshow;
            private Object unitidshow;
            private String sfyxshow;
            private Object createtime;
            private Object matstyle;
            private Object matclass2;
            private String matclass2show;
            private Object agerequest;
            private Object maxcoverage;
            private Object minpremium;
            private Object fitpeople;
            private String insurancebrandshow;
            private String bzqxunitshow;
            private Object schemeidsshow;
            private Object inscentertype;
            private String inscentertypeshow;
            private Object status;
            private Object funccode;
            private Object orderno;
            private Object shopid;
            private Object sunitid;
            private Object shoppurchaseprice;
            private Object ticketusescope;
            private Object standardsaleprice;
            private Object matname;
            private Object ps;
            private Object audituserid;
            private Object ticketusescopeshow;
            private Object cuserid;
            private Object ctime;
            private Object muserid;
            private Object mtime;
            private Object auditstatus;
            private Object mdetail;
            private Object sname;
            private Object bunitid;
            private Object delflag;
            private String shopidshow;
            private String cusername;
            private String musername;
            private boolean isexist;
            private Object clsfy;
            private Object brand;
            private Object standard;
            private Object isbatchm;
            private String bunitname;
            private String sunitname;
            private String psshow;
            private String auditstatusshow;
            private String audituseridshow;
            private Object audittime;
            private Object auditcomment;
            private String clsfyshow;
            private String brandshow;
            private Object introducemediaid;
            private Object agreementid;
            private String introducemediaidshow;
            private Object logoimgurl;
            private String logoimgurlshow;
            private String logoimgurlcompressshow;
            private String agreementidshowtitle;
            private String agreementidshowcomment;
            private String isbatchmshow;
            private Object barcode;
            private Object imgurl;
            private String imgurlshow;
            private String imgurlcompressshow;

            public String getSnid() {
                return snid;
            }

            public void setSnid(String snid) {
                this.snid = snid;
            }

            public Object getMatid() {
                return matid;
            }

            public void setMatid(Object matid) {
                this.matid = matid;
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

            public String getMatclass() {
                return matclass;
            }

            public void setMatclass(String matclass) {
                this.matclass = matclass;
            }

            public Object getNowprice() {
                return nowprice;
            }

            public void setNowprice(Object nowprice) {
                this.nowprice = nowprice;
            }

            public Object getBeforeprice() {
                return beforeprice;
            }

            public void setBeforeprice(Object beforeprice) {
                this.beforeprice = beforeprice;
            }

            public String getEcomment() {
                return ecomment;
            }

            public void setEcomment(String ecomment) {
                this.ecomment = ecomment;
            }

            public Object getTotalnum() {
                return totalnum;
            }

            public void setTotalnum(Object totalnum) {
                this.totalnum = totalnum;
            }

            public Object getSchemeids() {
                return schemeids;
            }

            public void setSchemeids(Object schemeids) {
                this.schemeids = schemeids;
            }

            public Object getBzqxstart() {
                return bzqxstart;
            }

            public void setBzqxstart(Object bzqxstart) {
                this.bzqxstart = bzqxstart;
            }

            public Object getBzqxstep() {
                return bzqxstep;
            }

            public void setBzqxstep(Object bzqxstep) {
                this.bzqxstep = bzqxstep;
            }

            public Object getBzqxmax() {
                return bzqxmax;
            }

            public void setBzqxmax(Object bzqxmax) {
                this.bzqxmax = bzqxmax;
            }

            public Object getBzqxexception() {
                return bzqxexception;
            }

            public void setBzqxexception(Object bzqxexception) {
                this.bzqxexception = bzqxexception;
            }

            public Object getBzqxunit() {
                return bzqxunit;
            }

            public void setBzqxunit(Object bzqxunit) {
                this.bzqxunit = bzqxunit;
            }

            public Object getUnitid() {
                return unitid;
            }

            public void setUnitid(Object unitid) {
                this.unitid = unitid;
            }

            public Object getInsurancebrand() {
                return insurancebrand;
            }

            public void setInsurancebrand(Object insurancebrand) {
                this.insurancebrand = insurancebrand;
            }

            public Object getMatidshow() {
                return matidshow;
            }

            public void setMatidshow(Object matidshow) {
                this.matidshow = matidshow;
            }

            public Object getProductfeature() {
                return productfeature;
            }

            public void setProductfeature(Object productfeature) {
                this.productfeature = productfeature;
            }

            public Object getProductdescription() {
                return productdescription;
            }

            public void setProductdescription(Object productdescription) {
                this.productdescription = productdescription;
            }

            public Object getInsuranceinformation() {
                return insuranceinformation;
            }

            public void setInsuranceinformation(Object insuranceinformation) {
                this.insuranceinformation = insuranceinformation;
            }

            public Object getClaimservice() {
                return claimservice;
            }

            public void setClaimservice(Object claimservice) {
                this.claimservice = claimservice;
            }

            public String getMatclassshow() {
                return matclassshow;
            }

            public void setMatclassshow(String matclassshow) {
                this.matclassshow = matclassshow;
            }

            public Object getUnitidshow() {
                return unitidshow;
            }

            public void setUnitidshow(Object unitidshow) {
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

            public Object getMatstyle() {
                return matstyle;
            }

            public void setMatstyle(Object matstyle) {
                this.matstyle = matstyle;
            }

            public Object getMatclass2() {
                return matclass2;
            }

            public void setMatclass2(Object matclass2) {
                this.matclass2 = matclass2;
            }

            public String getMatclass2show() {
                return matclass2show;
            }

            public void setMatclass2show(String matclass2show) {
                this.matclass2show = matclass2show;
            }

            public Object getAgerequest() {
                return agerequest;
            }

            public void setAgerequest(Object agerequest) {
                this.agerequest = agerequest;
            }

            public Object getMaxcoverage() {
                return maxcoverage;
            }

            public void setMaxcoverage(Object maxcoverage) {
                this.maxcoverage = maxcoverage;
            }

            public Object getMinpremium() {
                return minpremium;
            }

            public void setMinpremium(Object minpremium) {
                this.minpremium = minpremium;
            }

            public Object getFitpeople() {
                return fitpeople;
            }

            public void setFitpeople(Object fitpeople) {
                this.fitpeople = fitpeople;
            }

            public String getInsurancebrandshow() {
                return insurancebrandshow;
            }

            public void setInsurancebrandshow(String insurancebrandshow) {
                this.insurancebrandshow = insurancebrandshow;
            }

            public String getBzqxunitshow() {
                return bzqxunitshow;
            }

            public void setBzqxunitshow(String bzqxunitshow) {
                this.bzqxunitshow = bzqxunitshow;
            }

            public Object getSchemeidsshow() {
                return schemeidsshow;
            }

            public void setSchemeidsshow(Object schemeidsshow) {
                this.schemeidsshow = schemeidsshow;
            }

            public Object getInscentertype() {
                return inscentertype;
            }

            public void setInscentertype(Object inscentertype) {
                this.inscentertype = inscentertype;
            }

            public String getInscentertypeshow() {
                return inscentertypeshow;
            }

            public void setInscentertypeshow(String inscentertypeshow) {
                this.inscentertypeshow = inscentertypeshow;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getFunccode() {
                return funccode;
            }

            public void setFunccode(Object funccode) {
                this.funccode = funccode;
            }

            public Object getOrderno() {
                return orderno;
            }

            public void setOrderno(Object orderno) {
                this.orderno = orderno;
            }

            public Object getShopid() {
                return shopid;
            }

            public void setShopid(Object shopid) {
                this.shopid = shopid;
            }

            public Object getSunitid() {
                return sunitid;
            }

            public void setSunitid(Object sunitid) {
                this.sunitid = sunitid;
            }

            public Object getShoppurchaseprice() {
                return shoppurchaseprice;
            }

            public void setShoppurchaseprice(Object shoppurchaseprice) {
                this.shoppurchaseprice = shoppurchaseprice;
            }

            public Object getTicketusescope() {
                return ticketusescope;
            }

            public void setTicketusescope(Object ticketusescope) {
                this.ticketusescope = ticketusescope;
            }

            public Object getStandardsaleprice() {
                return standardsaleprice;
            }

            public void setStandardsaleprice(Object standardsaleprice) {
                this.standardsaleprice = standardsaleprice;
            }

            public Object getMatname() {
                return matname;
            }

            public void setMatname(Object matname) {
                this.matname = matname;
            }

            public Object getPs() {
                return ps;
            }

            public void setPs(Object ps) {
                this.ps = ps;
            }

            public Object getAudituserid() {
                return audituserid;
            }

            public void setAudituserid(Object audituserid) {
                this.audituserid = audituserid;
            }

            public Object getTicketusescopeshow() {
                return ticketusescopeshow;
            }

            public void setTicketusescopeshow(Object ticketusescopeshow) {
                this.ticketusescopeshow = ticketusescopeshow;
            }

            public Object getCuserid() {
                return cuserid;
            }

            public void setCuserid(Object cuserid) {
                this.cuserid = cuserid;
            }

            public Object getCtime() {
                return ctime;
            }

            public void setCtime(Object ctime) {
                this.ctime = ctime;
            }

            public Object getMuserid() {
                return muserid;
            }

            public void setMuserid(Object muserid) {
                this.muserid = muserid;
            }

            public Object getMtime() {
                return mtime;
            }

            public void setMtime(Object mtime) {
                this.mtime = mtime;
            }

            public Object getAuditstatus() {
                return auditstatus;
            }

            public void setAuditstatus(Object auditstatus) {
                this.auditstatus = auditstatus;
            }

            public Object getMdetail() {
                return mdetail;
            }

            public void setMdetail(Object mdetail) {
                this.mdetail = mdetail;
            }

            public Object getSname() {
                return sname;
            }

            public void setSname(Object sname) {
                this.sname = sname;
            }

            public Object getBunitid() {
                return bunitid;
            }

            public void setBunitid(Object bunitid) {
                this.bunitid = bunitid;
            }

            public Object getDelflag() {
                return delflag;
            }

            public void setDelflag(Object delflag) {
                this.delflag = delflag;
            }

            public String getShopidshow() {
                return shopidshow;
            }

            public void setShopidshow(String shopidshow) {
                this.shopidshow = shopidshow;
            }

            public String getCusername() {
                return cusername;
            }

            public void setCusername(String cusername) {
                this.cusername = cusername;
            }

            public String getMusername() {
                return musername;
            }

            public void setMusername(String musername) {
                this.musername = musername;
            }

            public boolean isIsexist() {
                return isexist;
            }

            public void setIsexist(boolean isexist) {
                this.isexist = isexist;
            }

            public Object getClsfy() {
                return clsfy;
            }

            public void setClsfy(Object clsfy) {
                this.clsfy = clsfy;
            }

            public Object getBrand() {
                return brand;
            }

            public void setBrand(Object brand) {
                this.brand = brand;
            }

            public Object getStandard() {
                return standard;
            }

            public void setStandard(Object standard) {
                this.standard = standard;
            }

            public Object getIsbatchm() {
                return isbatchm;
            }

            public void setIsbatchm(Object isbatchm) {
                this.isbatchm = isbatchm;
            }

            public String getBunitname() {
                return bunitname;
            }

            public void setBunitname(String bunitname) {
                this.bunitname = bunitname;
            }

            public String getSunitname() {
                return sunitname;
            }

            public void setSunitname(String sunitname) {
                this.sunitname = sunitname;
            }

            public String getPsshow() {
                return psshow;
            }

            public void setPsshow(String psshow) {
                this.psshow = psshow;
            }

            public String getAuditstatusshow() {
                return auditstatusshow;
            }

            public void setAuditstatusshow(String auditstatusshow) {
                this.auditstatusshow = auditstatusshow;
            }

            public String getAudituseridshow() {
                return audituseridshow;
            }

            public void setAudituseridshow(String audituseridshow) {
                this.audituseridshow = audituseridshow;
            }

            public Object getAudittime() {
                return audittime;
            }

            public void setAudittime(Object audittime) {
                this.audittime = audittime;
            }

            public Object getAuditcomment() {
                return auditcomment;
            }

            public void setAuditcomment(Object auditcomment) {
                this.auditcomment = auditcomment;
            }

            public String getClsfyshow() {
                return clsfyshow;
            }

            public void setClsfyshow(String clsfyshow) {
                this.clsfyshow = clsfyshow;
            }

            public String getBrandshow() {
                return brandshow;
            }

            public void setBrandshow(String brandshow) {
                this.brandshow = brandshow;
            }

            public Object getIntroducemediaid() {
                return introducemediaid;
            }

            public void setIntroducemediaid(Object introducemediaid) {
                this.introducemediaid = introducemediaid;
            }

            public Object getAgreementid() {
                return agreementid;
            }

            public void setAgreementid(Object agreementid) {
                this.agreementid = agreementid;
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

            public String getAgreementidshowtitle() {
                return agreementidshowtitle;
            }

            public void setAgreementidshowtitle(String agreementidshowtitle) {
                this.agreementidshowtitle = agreementidshowtitle;
            }

            public String getAgreementidshowcomment() {
                return agreementidshowcomment;
            }

            public void setAgreementidshowcomment(String agreementidshowcomment) {
                this.agreementidshowcomment = agreementidshowcomment;
            }

            public String getIsbatchmshow() {
                return isbatchmshow;
            }

            public void setIsbatchmshow(String isbatchmshow) {
                this.isbatchmshow = isbatchmshow;
            }

            public Object getBarcode() {
                return barcode;
            }

            public void setBarcode(Object barcode) {
                this.barcode = barcode;
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

        public static class SelectedListBean {
            /**
             * snid : 64
             * matid : null
             * btime : null
             * etime : null
             * sfyx : null
             * matclass : INSURANCECLSONE008
             * nowprice : null
             * beforeprice : null
             * ecomment : 定期（多次）
             * totalnum : null
             * schemeids : null
             * bzqxstart : null
             * bzqxstep : null
             * bzqxmax : null
             * bzqxexception : null
             * bzqxunit : null
             * unitid : null
             * insurancebrand : null
             * matidshow : null
             * productfeature : null
             * productdescription : null
             * insuranceinformation : null
             * claimservice : null
             * matclassshow : 行李险
             * unitidshow : null
             * sfyxshow :
             * createtime : null
             * matstyle : null
             * matclass2 : null
             * matclass2show :
             * agerequest : null
             * maxcoverage : null
             * minpremium : null
             * fitpeople : null
             * insurancebrandshow :
             * bzqxunitshow :
             * schemeidsshow : null
             * inscentertype : null
             * inscentertypeshow :
             * status : null
             * funccode : null
             * orderno : null
             * shopid : null
             * sunitid : null
             * shoppurchaseprice : null
             * ticketusescope : null
             * standardsaleprice : null
             * matname : null
             * ps : null
             * audituserid : null
             * ticketusescopeshow : null
             * cuserid : null
             * ctime : null
             * muserid : null
             * mtime : null
             * auditstatus : null
             * mdetail : null
             * sname : null
             * bunitid : null
             * delflag : null
             * shopidshow :
             * cusername :
             * musername :
             * isexist : false
             * clsfy : null
             * brand : null
             * standard : null
             * isbatchm : null
             * bunitname :
             * sunitname :
             * psshow :
             * auditstatusshow :
             * audituseridshow :
             * audittime : null
             * auditcomment : null
             * clsfyshow :
             * brandshow :
             * introducemediaid : null
             * agreementid : null
             * introducemediaidshow :
             * logoimgurl : null
             * logoimgurlshow :
             * logoimgurlcompressshow :
             * agreementidshowtitle :
             * agreementidshowcomment :
             * isbatchmshow : 否
             * barcode : null
             * imgurl : null
             * imgurlshow :
             * imgurlcompressshow :
             */

            private String snid;
            private Object matid;
            private Object btime;
            private Object etime;
            private Object sfyx;
            private String matclass;
            private Object nowprice;
            private Object beforeprice;
            private String ecomment;
            private Object totalnum;
            private Object schemeids;
            private Object bzqxstart;
            private Object bzqxstep;
            private Object bzqxmax;
            private Object bzqxexception;
            private Object bzqxunit;
            private Object unitid;
            private Object insurancebrand;
            private Object matidshow;
            private Object productfeature;
            private Object productdescription;
            private Object insuranceinformation;
            private Object claimservice;
            private String matclassshow;
            private Object unitidshow;
            private String sfyxshow;
            private Object createtime;
            private Object matstyle;
            private Object matclass2;
            private String matclass2show;
            private Object agerequest;
            private Object maxcoverage;
            private Object minpremium;
            private Object fitpeople;
            private String insurancebrandshow;
            private String bzqxunitshow;
            private Object schemeidsshow;
            private Object inscentertype;
            private String inscentertypeshow;
            private Object status;
            private Object funccode;
            private Object orderno;
            private Object shopid;
            private Object sunitid;
            private Object shoppurchaseprice;
            private Object ticketusescope;
            private Object standardsaleprice;
            private Object matname;
            private Object ps;
            private Object audituserid;
            private Object ticketusescopeshow;
            private Object cuserid;
            private Object ctime;
            private Object muserid;
            private Object mtime;
            private Object auditstatus;
            private Object mdetail;
            private Object sname;
            private Object bunitid;
            private Object delflag;
            private String shopidshow;
            private String cusername;
            private String musername;
            private boolean isexist;
            private Object clsfy;
            private Object brand;
            private Object standard;
            private Object isbatchm;
            private String bunitname;
            private String sunitname;
            private String psshow;
            private String auditstatusshow;
            private String audituseridshow;
            private Object audittime;
            private Object auditcomment;
            private String clsfyshow;
            private String brandshow;
            private Object introducemediaid;
            private Object agreementid;
            private String introducemediaidshow;
            private Object logoimgurl;
            private String logoimgurlshow;
            private String logoimgurlcompressshow;
            private String agreementidshowtitle;
            private String agreementidshowcomment;
            private String isbatchmshow;
            private Object barcode;
            private Object imgurl;
            private String imgurlshow;
            private String imgurlcompressshow;

            public String getSnid() {
                return snid;
            }

            public void setSnid(String snid) {
                this.snid = snid;
            }

            public Object getMatid() {
                return matid;
            }

            public void setMatid(Object matid) {
                this.matid = matid;
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

            public String getMatclass() {
                return matclass;
            }

            public void setMatclass(String matclass) {
                this.matclass = matclass;
            }

            public Object getNowprice() {
                return nowprice;
            }

            public void setNowprice(Object nowprice) {
                this.nowprice = nowprice;
            }

            public Object getBeforeprice() {
                return beforeprice;
            }

            public void setBeforeprice(Object beforeprice) {
                this.beforeprice = beforeprice;
            }

            public String getEcomment() {
                return ecomment;
            }

            public void setEcomment(String ecomment) {
                this.ecomment = ecomment;
            }

            public Object getTotalnum() {
                return totalnum;
            }

            public void setTotalnum(Object totalnum) {
                this.totalnum = totalnum;
            }

            public Object getSchemeids() {
                return schemeids;
            }

            public void setSchemeids(Object schemeids) {
                this.schemeids = schemeids;
            }

            public Object getBzqxstart() {
                return bzqxstart;
            }

            public void setBzqxstart(Object bzqxstart) {
                this.bzqxstart = bzqxstart;
            }

            public Object getBzqxstep() {
                return bzqxstep;
            }

            public void setBzqxstep(Object bzqxstep) {
                this.bzqxstep = bzqxstep;
            }

            public Object getBzqxmax() {
                return bzqxmax;
            }

            public void setBzqxmax(Object bzqxmax) {
                this.bzqxmax = bzqxmax;
            }

            public Object getBzqxexception() {
                return bzqxexception;
            }

            public void setBzqxexception(Object bzqxexception) {
                this.bzqxexception = bzqxexception;
            }

            public Object getBzqxunit() {
                return bzqxunit;
            }

            public void setBzqxunit(Object bzqxunit) {
                this.bzqxunit = bzqxunit;
            }

            public Object getUnitid() {
                return unitid;
            }

            public void setUnitid(Object unitid) {
                this.unitid = unitid;
            }

            public Object getInsurancebrand() {
                return insurancebrand;
            }

            public void setInsurancebrand(Object insurancebrand) {
                this.insurancebrand = insurancebrand;
            }

            public Object getMatidshow() {
                return matidshow;
            }

            public void setMatidshow(Object matidshow) {
                this.matidshow = matidshow;
            }

            public Object getProductfeature() {
                return productfeature;
            }

            public void setProductfeature(Object productfeature) {
                this.productfeature = productfeature;
            }

            public Object getProductdescription() {
                return productdescription;
            }

            public void setProductdescription(Object productdescription) {
                this.productdescription = productdescription;
            }

            public Object getInsuranceinformation() {
                return insuranceinformation;
            }

            public void setInsuranceinformation(Object insuranceinformation) {
                this.insuranceinformation = insuranceinformation;
            }

            public Object getClaimservice() {
                return claimservice;
            }

            public void setClaimservice(Object claimservice) {
                this.claimservice = claimservice;
            }

            public String getMatclassshow() {
                return matclassshow;
            }

            public void setMatclassshow(String matclassshow) {
                this.matclassshow = matclassshow;
            }

            public Object getUnitidshow() {
                return unitidshow;
            }

            public void setUnitidshow(Object unitidshow) {
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

            public Object getMatstyle() {
                return matstyle;
            }

            public void setMatstyle(Object matstyle) {
                this.matstyle = matstyle;
            }

            public Object getMatclass2() {
                return matclass2;
            }

            public void setMatclass2(Object matclass2) {
                this.matclass2 = matclass2;
            }

            public String getMatclass2show() {
                return matclass2show;
            }

            public void setMatclass2show(String matclass2show) {
                this.matclass2show = matclass2show;
            }

            public Object getAgerequest() {
                return agerequest;
            }

            public void setAgerequest(Object agerequest) {
                this.agerequest = agerequest;
            }

            public Object getMaxcoverage() {
                return maxcoverage;
            }

            public void setMaxcoverage(Object maxcoverage) {
                this.maxcoverage = maxcoverage;
            }

            public Object getMinpremium() {
                return minpremium;
            }

            public void setMinpremium(Object minpremium) {
                this.minpremium = minpremium;
            }

            public Object getFitpeople() {
                return fitpeople;
            }

            public void setFitpeople(Object fitpeople) {
                this.fitpeople = fitpeople;
            }

            public String getInsurancebrandshow() {
                return insurancebrandshow;
            }

            public void setInsurancebrandshow(String insurancebrandshow) {
                this.insurancebrandshow = insurancebrandshow;
            }

            public String getBzqxunitshow() {
                return bzqxunitshow;
            }

            public void setBzqxunitshow(String bzqxunitshow) {
                this.bzqxunitshow = bzqxunitshow;
            }

            public Object getSchemeidsshow() {
                return schemeidsshow;
            }

            public void setSchemeidsshow(Object schemeidsshow) {
                this.schemeidsshow = schemeidsshow;
            }

            public Object getInscentertype() {
                return inscentertype;
            }

            public void setInscentertype(Object inscentertype) {
                this.inscentertype = inscentertype;
            }

            public String getInscentertypeshow() {
                return inscentertypeshow;
            }

            public void setInscentertypeshow(String inscentertypeshow) {
                this.inscentertypeshow = inscentertypeshow;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getFunccode() {
                return funccode;
            }

            public void setFunccode(Object funccode) {
                this.funccode = funccode;
            }

            public Object getOrderno() {
                return orderno;
            }

            public void setOrderno(Object orderno) {
                this.orderno = orderno;
            }

            public Object getShopid() {
                return shopid;
            }

            public void setShopid(Object shopid) {
                this.shopid = shopid;
            }

            public Object getSunitid() {
                return sunitid;
            }

            public void setSunitid(Object sunitid) {
                this.sunitid = sunitid;
            }

            public Object getShoppurchaseprice() {
                return shoppurchaseprice;
            }

            public void setShoppurchaseprice(Object shoppurchaseprice) {
                this.shoppurchaseprice = shoppurchaseprice;
            }

            public Object getTicketusescope() {
                return ticketusescope;
            }

            public void setTicketusescope(Object ticketusescope) {
                this.ticketusescope = ticketusescope;
            }

            public Object getStandardsaleprice() {
                return standardsaleprice;
            }

            public void setStandardsaleprice(Object standardsaleprice) {
                this.standardsaleprice = standardsaleprice;
            }

            public Object getMatname() {
                return matname;
            }

            public void setMatname(Object matname) {
                this.matname = matname;
            }

            public Object getPs() {
                return ps;
            }

            public void setPs(Object ps) {
                this.ps = ps;
            }

            public Object getAudituserid() {
                return audituserid;
            }

            public void setAudituserid(Object audituserid) {
                this.audituserid = audituserid;
            }

            public Object getTicketusescopeshow() {
                return ticketusescopeshow;
            }

            public void setTicketusescopeshow(Object ticketusescopeshow) {
                this.ticketusescopeshow = ticketusescopeshow;
            }

            public Object getCuserid() {
                return cuserid;
            }

            public void setCuserid(Object cuserid) {
                this.cuserid = cuserid;
            }

            public Object getCtime() {
                return ctime;
            }

            public void setCtime(Object ctime) {
                this.ctime = ctime;
            }

            public Object getMuserid() {
                return muserid;
            }

            public void setMuserid(Object muserid) {
                this.muserid = muserid;
            }

            public Object getMtime() {
                return mtime;
            }

            public void setMtime(Object mtime) {
                this.mtime = mtime;
            }

            public Object getAuditstatus() {
                return auditstatus;
            }

            public void setAuditstatus(Object auditstatus) {
                this.auditstatus = auditstatus;
            }

            public Object getMdetail() {
                return mdetail;
            }

            public void setMdetail(Object mdetail) {
                this.mdetail = mdetail;
            }

            public Object getSname() {
                return sname;
            }

            public void setSname(Object sname) {
                this.sname = sname;
            }

            public Object getBunitid() {
                return bunitid;
            }

            public void setBunitid(Object bunitid) {
                this.bunitid = bunitid;
            }

            public Object getDelflag() {
                return delflag;
            }

            public void setDelflag(Object delflag) {
                this.delflag = delflag;
            }

            public String getShopidshow() {
                return shopidshow;
            }

            public void setShopidshow(String shopidshow) {
                this.shopidshow = shopidshow;
            }

            public String getCusername() {
                return cusername;
            }

            public void setCusername(String cusername) {
                this.cusername = cusername;
            }

            public String getMusername() {
                return musername;
            }

            public void setMusername(String musername) {
                this.musername = musername;
            }

            public boolean isIsexist() {
                return isexist;
            }

            public void setIsexist(boolean isexist) {
                this.isexist = isexist;
            }

            public Object getClsfy() {
                return clsfy;
            }

            public void setClsfy(Object clsfy) {
                this.clsfy = clsfy;
            }

            public Object getBrand() {
                return brand;
            }

            public void setBrand(Object brand) {
                this.brand = brand;
            }

            public Object getStandard() {
                return standard;
            }

            public void setStandard(Object standard) {
                this.standard = standard;
            }

            public Object getIsbatchm() {
                return isbatchm;
            }

            public void setIsbatchm(Object isbatchm) {
                this.isbatchm = isbatchm;
            }

            public String getBunitname() {
                return bunitname;
            }

            public void setBunitname(String bunitname) {
                this.bunitname = bunitname;
            }

            public String getSunitname() {
                return sunitname;
            }

            public void setSunitname(String sunitname) {
                this.sunitname = sunitname;
            }

            public String getPsshow() {
                return psshow;
            }

            public void setPsshow(String psshow) {
                this.psshow = psshow;
            }

            public String getAuditstatusshow() {
                return auditstatusshow;
            }

            public void setAuditstatusshow(String auditstatusshow) {
                this.auditstatusshow = auditstatusshow;
            }

            public String getAudituseridshow() {
                return audituseridshow;
            }

            public void setAudituseridshow(String audituseridshow) {
                this.audituseridshow = audituseridshow;
            }

            public Object getAudittime() {
                return audittime;
            }

            public void setAudittime(Object audittime) {
                this.audittime = audittime;
            }

            public Object getAuditcomment() {
                return auditcomment;
            }

            public void setAuditcomment(Object auditcomment) {
                this.auditcomment = auditcomment;
            }

            public String getClsfyshow() {
                return clsfyshow;
            }

            public void setClsfyshow(String clsfyshow) {
                this.clsfyshow = clsfyshow;
            }

            public String getBrandshow() {
                return brandshow;
            }

            public void setBrandshow(String brandshow) {
                this.brandshow = brandshow;
            }

            public Object getIntroducemediaid() {
                return introducemediaid;
            }

            public void setIntroducemediaid(Object introducemediaid) {
                this.introducemediaid = introducemediaid;
            }

            public Object getAgreementid() {
                return agreementid;
            }

            public void setAgreementid(Object agreementid) {
                this.agreementid = agreementid;
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

            public String getAgreementidshowtitle() {
                return agreementidshowtitle;
            }

            public void setAgreementidshowtitle(String agreementidshowtitle) {
                this.agreementidshowtitle = agreementidshowtitle;
            }

            public String getAgreementidshowcomment() {
                return agreementidshowcomment;
            }

            public void setAgreementidshowcomment(String agreementidshowcomment) {
                this.agreementidshowcomment = agreementidshowcomment;
            }

            public String getIsbatchmshow() {
                return isbatchmshow;
            }

            public void setIsbatchmshow(String isbatchmshow) {
                this.isbatchmshow = isbatchmshow;
            }

            public Object getBarcode() {
                return barcode;
            }

            public void setBarcode(Object barcode) {
                this.barcode = barcode;
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
}
