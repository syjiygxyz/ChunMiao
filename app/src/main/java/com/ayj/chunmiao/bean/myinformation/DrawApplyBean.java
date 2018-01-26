package com.ayj.chunmiao.bean.myinformation;

import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

public class DrawApplyBean {

    /**
     * err : 0
     * msg : 获取成功
     * total : 6
     * data : [{"shopid":"001","snid":null,"createuserid":null,"audituserid":"10387","auditstatus":"SHBZ002","auditstatusshow":"审核通过","withdrawnum":"1111700","createtime":"2017-08-23 09:41:04.0","auditcomment":"url:rootpath+","audittime":"2017-08-18 09:41:04.0","audituseridshow":"郝鹏宇","bankid":null,"bagtype":null,"bankno":null,"withdrawtype":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":"001","snid":null,"createuserid":null,"audituserid":"10387","auditstatus":"SHBZ002","auditstatusshow":"审核通过","withdrawnum":"1111900","createtime":"2017-08-18 09:41:04.0","auditcomment":"支付支付支付","audittime":"2017-08-18 09:07:57.0","audituseridshow":"郝鹏宇","bankid":null,"bagtype":null,"bankno":null,"withdrawtype":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":"001","snid":null,"createuserid":null,"audituserid":null,"auditstatus":"SHBZ001","auditstatusshow":"待审核","withdrawnum":"1","createtime":"2017-09-21 17:13:09.0","auditcomment":null,"audittime":null,"audituseridshow":"","bankid":null,"bagtype":null,"bankno":"123456","withdrawtype":"PAYTYPE004","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":"001","snid":null,"createuserid":null,"audituserid":null,"auditstatus":"SHBZ001","auditstatusshow":"待审核","withdrawnum":"1","createtime":"2017-09-22 16:05:46.0","auditcomment":null,"audittime":null,"audituseridshow":"","bankid":null,"bagtype":null,"bankno":"1111111","withdrawtype":"PAYTYPE004","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":"001","snid":null,"createuserid":null,"audituserid":null,"auditstatus":"SHBZ001","auditstatusshow":"待审核","withdrawnum":"1","createtime":"2017-09-22 16:06:41.0","auditcomment":null,"audittime":null,"audituseridshow":"","bankid":null,"bagtype":null,"bankno":"1111111","withdrawtype":"PAYTYPE004","imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"shopid":"001","snid":null,"createuserid":null,"audituserid":null,"auditstatus":"SHBZ001","auditstatusshow":"待审核","withdrawnum":"1","createtime":"2017-09-22 16:06:51.0","auditcomment":null,"audittime":null,"audituseridshow":"","bankid":null,"bagtype":null,"bankno":"1111111","withdrawtype":"PAYTYPE004","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
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
        /**
         * shopid : 001
         * snid : null
         * createuserid : null
         * audituserid : 10387
         * auditstatus : SHBZ002
         * auditstatusshow : 审核通过
         * withdrawnum : 1111700
         * createtime : 2017-08-23 09:41:04.0
         * auditcomment : url:rootpath+
         * audittime : 2017-08-18 09:41:04.0
         * audituseridshow : 郝鹏宇
         * bankid : null
         * bagtype : null
         * bankno : null
         * withdrawtype : null
         * imgurl : null
         * imgurlshow :
         * imgurlcompressshow :
         */

        private String shopid;
        private Object snid;
        private Object createuserid;
        private String audituserid;
        private String auditstatus;
        private String auditstatusshow;
        private String withdrawnum;
        private String createtime;
        private String auditcomment;
        private String audittime;
        private String audituseridshow;
        private Object bankid;
        private Object bagtype;
        private Object bankno;
        private Object withdrawtype;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public Object getSnid() {
            return snid;
        }

        public void setSnid(Object snid) {
            this.snid = snid;
        }

        public Object getCreateuserid() {
            return createuserid;
        }

        public void setCreateuserid(Object createuserid) {
            this.createuserid = createuserid;
        }

        public String getAudituserid() {
            return audituserid;
        }

        public void setAudituserid(String audituserid) {
            this.audituserid = audituserid;
        }

        public String getAuditstatus() {
            return auditstatus;
        }

        public void setAuditstatus(String auditstatus) {
            this.auditstatus = auditstatus;
        }

        public String getAuditstatusshow() {
            return auditstatusshow;
        }

        public void setAuditstatusshow(String auditstatusshow) {
            this.auditstatusshow = auditstatusshow;
        }

        public String getWithdrawnum() {
            return withdrawnum;
        }

        public void setWithdrawnum(String withdrawnum) {
            this.withdrawnum = withdrawnum;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getAuditcomment() {
            return auditcomment;
        }

        public void setAuditcomment(String auditcomment) {
            this.auditcomment = auditcomment;
        }

        public String getAudittime() {
            return audittime;
        }

        public void setAudittime(String audittime) {
            this.audittime = audittime;
        }

        public String getAudituseridshow() {
            return audituseridshow;
        }

        public void setAudituseridshow(String audituseridshow) {
            this.audituseridshow = audituseridshow;
        }

        public Object getBankid() {
            return bankid;
        }

        public void setBankid(Object bankid) {
            this.bankid = bankid;
        }

        public Object getBagtype() {
            return bagtype;
        }

        public void setBagtype(Object bagtype) {
            this.bagtype = bagtype;
        }

        public Object getBankno() {
            return bankno;
        }

        public void setBankno(Object bankno) {
            this.bankno = bankno;
        }

        public Object getWithdrawtype() {
            return withdrawtype;
        }

        public void setWithdrawtype(Object withdrawtype) {
            this.withdrawtype = withdrawtype;
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
