package com.ayj.chunmiao.bean.shopping;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zht-pc-09 on 2016/8/4.
 */
public class JinmaoShopDetial implements Serializable {

    public static final long serialVersionUID = -1988638541338666696L;


    /**
     * err : 0
     * msg :
     * total : 2
     * data : [{"snid":"301","ordertype":"MEMBERORDERTYPE019","btime":"2017-05-08",
     * "etime":"2019-05-09","sfyx":"SFYX001","nowprice":"298.00","beforeprice":"298.00",
     * "matid":"98","ecomment":null,"totalnum":"8","unitid":"2016110440847","createuserid":null,
     * "matidshow":"医用冷敷贴","createuseridshow":"",
     * "introducemediaid":"Upload/2017-04-27/20170427185508425.jpg",
     * "introducemediaidshow":"http://test.21ga
     * .cn:9080/qmkmimageupload/Upload/2017-04-27/20170427185508425.jpg",
     * "logoimgurl":"Upload/2017-05-06/20170506141017378.png","logoimgurlshow":"http://test.21ga
     * .cn:9080/qmkmimageupload/Upload/2017-05-06/20170506141017378.png",
     * "logoimgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-05-06/20170506141017378.png","unitidshow":"盒",
     * "sfyxshow":"有效","createtime":"2017-05-08 08:46:26","salenum":"11",
     * "ordertypeshow":"公司统一商城订单","introducemediaidcompressshow":"http://test.21ga
     * .cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-27/20170427185508425.jpg",
     * "matclass2":"SHOPPINGMALLCLSTWO022","matclass2show":"美妆",
     * "matclass1":"SHOPPINGMALLCLSONE002","matclass1show":"春苗展柜","ishot":null,"ishotshow":"",
     * "ismuston":null,"ismustonshow":"","imgurl":"Upload/2017-04-26/20170426144036753.jpg,
     * Upload/2017-04-26/20170426144059050.jpg,Upload/2017-04-26/20170426144126253.jpg",
     * "imgurlshow":"http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144036753
     * .jpg,http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144059050.jpg,
     * http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144126253.jpg",
     * "imgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144036753.jpg,http://test.21ga
     * .cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144059050.jpg,http://test.21ga
     * .cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144126253.jpg"},{"snid":"299",
     * "ordertype":"MEMBERORDERTYPE019","btime":"2017-05-05","etime":"2019-05-05",
     * "sfyx":"SFYX001","nowprice":"298.00","beforeprice":"298.00","matid":"98","ecomment":null,
     * "totalnum":"8","unitid":"2016110440847","createuserid":null,"matidshow":"医用冷敷贴",
     * "createuseridshow":"","introducemediaid":"Upload/2017-04-27/20170427185508425.jpg",
     * "introducemediaidshow":"http://test.21ga
     * .cn:9080/qmkmimageupload/Upload/2017-04-27/20170427185508425.jpg",
     * "logoimgurl":"Upload/2017-05-06/20170506141017378.png","logoimgurlshow":"http://test.21ga
     * .cn:9080/qmkmimageupload/Upload/2017-05-06/20170506141017378.png",
     * "logoimgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-05-06/20170506141017378.png","unitidshow":"盒",
     * "sfyxshow":"有效","createtime":"2017-05-05 15:05:41","salenum":"11",
     * "ordertypeshow":"公司统一商城订单","introducemediaidcompressshow":"http://test.21ga
     * .cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-27/20170427185508425.jpg",
     * "matclass2":"SHOPPINGMALLCLSTWO022","matclass2show":"美妆",
     * "matclass1":"SHOPPINGMALLCLSONE001","matclass1show":"春苗共享","ishot":null,"ishotshow":"",
     * "ismuston":null,"ismustonshow":"","imgurl":"Upload/2017-04-26/20170426144036753.jpg,
     * Upload/2017-04-26/20170426144059050.jpg,Upload/2017-04-26/20170426144126253.jpg",
     * "imgurlshow":"http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144036753
     * .jpg,http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144059050.jpg,
     * http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144126253.jpg",
     * "imgurlcompressshow":"http://test.21ga.cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144036753.jpg,http://test.21ga
     * .cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144059050.jpg,http://test.21ga
     * .cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144126253.jpg"}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * snid : 301
     * ordertype : MEMBERORDERTYPE019
     * btime : 2017-05-08
     * etime : 2019-05-09
     * sfyx : SFYX001
     * nowprice : 298.00
     * beforeprice : 298.00
     * matid : 98
     * ecomment : null
     * totalnum : 8
     * unitid : 2016110440847
     * createuserid : null
     * matidshow : 医用冷敷贴
     * createuseridshow :
     * introducemediaid : Upload/2017-04-27/20170427185508425.jpg
     * introducemediaidshow : http://test.21ga
     * .cn:9080/qmkmimageupload/Upload/2017-04-27/20170427185508425.jpg
     * logoimgurl : Upload/2017-05-06/20170506141017378.png
     * logoimgurlshow : http://test.21ga
     * .cn:9080/qmkmimageupload/Upload/2017-05-06/20170506141017378.png
     * logoimgurlcompressshow : http://test.21ga.cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-05-06/20170506141017378.png
     * unitidshow : 盒
     * sfyxshow : 有效
     * createtime : 2017-05-08 08:46:26
     * salenum : 11
     * ordertypeshow : 公司统一商城订单
     * introducemediaidcompressshow : http://test.21ga.cn:9080/qmkmimageupload/CompressImages
     * .aspx?isformalsys=1&filename=Upload/2017-04-27/20170427185508425.jpg
     * matclass2 : SHOPPINGMALLCLSTWO022
     * matclass2show : 美妆
     * matclass1 : SHOPPINGMALLCLSONE002
     * matclass1show : 春苗展柜
     * ishot : null
     * ishotshow :
     * ismuston : null
     * ismustonshow :
     * imgurl : Upload/2017-04-26/20170426144036753.jpg,Upload/2017-04-26/20170426144059050.jpg,
     * Upload/2017-04-26/20170426144126253.jpg
     * imgurlshow : http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144036753.jpg,http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144059050.jpg,http://test.21ga.cn:9080/qmkmimageupload/Upload/2017-04-26/20170426144126253.jpg
     * imgurlcompressshow : http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144036753.jpg,http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144059050.jpg,http://test.21ga.cn:9080/qmkmimageupload/CompressImages.aspx?isformalsys=1&filename=Upload/2017-04-26/20170426144126253.jpg
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

    public static class DataBean implements Serializable{
        private String snid;
        private String ordertype;
        private String btime;
        private String etime;
        private String sfyx;
        private String nowprice;
        private String beforeprice;
        private String matid;
        private Object ecomment;
        private String totalnum;
        private String unitid;
        private Object createuserid;
        private String matidshow;
        private String createuseridshow;
        private String introducemediaid;
        private String introducemediaidshow;
        private String logoimgurl;
        private String logoimgurlshow;
        private String logoimgurlcompressshow;
        private String unitidshow;
        private String sfyxshow;
        private String createtime;
        private String salenum;
        private String ordertypeshow;
        private String introducemediaidcompressshow;
        private String matclass2;
        private String matclass2show;
        private String matclass1;
        private String matclass1show;
        private Object ishot;
        private String ishotshow;
        private Object ismuston;
        private String ismustonshow;
        private String imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getBtime() {
            return btime;
        }

        public void setBtime(String btime) {
            this.btime = btime;
        }

        public String getEtime() {
            return etime;
        }

        public void setEtime(String etime) {
            this.etime = etime;
        }

        public String getSfyx() {
            return sfyx;
        }

        public void setSfyx(String sfyx) {
            this.sfyx = sfyx;
        }

        public String getNowprice() {
            return nowprice;
        }

        public void setNowprice(String nowprice) {
            this.nowprice = nowprice;
        }

        public String getBeforeprice() {
            return beforeprice;
        }

        public void setBeforeprice(String beforeprice) {
            this.beforeprice = beforeprice;
        }

        public String getMatid() {
            return matid;
        }

        public void setMatid(String matid) {
            this.matid = matid;
        }

        public Object getEcomment() {
            return ecomment;
        }

        public void setEcomment(Object ecomment) {
            this.ecomment = ecomment;
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

        public Object getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(Object createuserid) {
            this.createuserid = createuserid;
        }

        public String getMatidshow() {
            return matidshow;
        }

        public void setMatidshow(String matidshow) {
            this.matidshow = matidshow;
        }

        public String getCreateuseridshow() {
            return createuseridshow;
        }

        public void setCreateuseridshow(String createuseridshow) {
            this.createuseridshow = createuseridshow;
        }

        public String getIntroducemediaid() {
            return introducemediaid;
        }

        public void setIntroducemediaid(String introducemediaid) {
            this.introducemediaid = introducemediaid;
        }

        public String getIntroducemediaidshow() {
            return introducemediaidshow;
        }

        public void setIntroducemediaidshow(String introducemediaidshow) {
            this.introducemediaidshow = introducemediaidshow;
        }

        public String getLogoimgurl() {
            return logoimgurl;
        }

        public void setLogoimgurl(String logoimgurl) {
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

        public String getUnitidshow() {
            return unitidshow;
        }

        public void setUnitidshow(String unitidshow) {
            this.unitidshow = unitidshow;
        }

        public String getSfyxshow() {
            return sfyxshow;
        }

        public void setSfyxshow(String sfyxshow) {
            this.sfyxshow = sfyxshow;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getSalenum() {
            return salenum;
        }

        public void setSalenum(String salenum) {
            this.salenum = salenum;
        }

        public String getOrdertypeshow() {
            return ordertypeshow;
        }

        public void setOrdertypeshow(String ordertypeshow) {
            this.ordertypeshow = ordertypeshow;
        }

        public String getIntroducemediaidcompressshow() {
            return introducemediaidcompressshow;
        }

        public void setIntroducemediaidcompressshow(String introducemediaidcompressshow) {
            this.introducemediaidcompressshow = introducemediaidcompressshow;
        }

        public String getMatclass2() {
            return matclass2;
        }

        public void setMatclass2(String matclass2) {
            this.matclass2 = matclass2;
        }

        public String getMatclass2show() {
            return matclass2show;
        }

        public void setMatclass2show(String matclass2show) {
            this.matclass2show = matclass2show;
        }

        public String getMatclass1() {
            return matclass1;
        }

        public void setMatclass1(String matclass1) {
            this.matclass1 = matclass1;
        }

        public String getMatclass1show() {
            return matclass1show;
        }

        public void setMatclass1show(String matclass1show) {
            this.matclass1show = matclass1show;
        }

        public Object getIshot() {
            return ishot;
        }

        public void setIshot(Object ishot) {
            this.ishot = ishot;
        }

        public String getIshotshow() {
            return ishotshow;
        }

        public void setIshotshow(String ishotshow) {
            this.ishotshow = ishotshow;
        }

        public Object getIsmuston() {
            return ismuston;
        }

        public void setIsmuston(Object ismuston) {
            this.ismuston = ismuston;
        }

        public String getIsmustonshow() {
            return ismustonshow;
        }

        public void setIsmustonshow(String ismustonshow) {
            this.ismustonshow = ismustonshow;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
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
