package com.ayj.chunmiao.bean.myinformation;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/9/2.
 */

public class NoticeBean {

    /**
     * data : [{"userid":"10275","snid":"1","acomment":"acomment","createtime":null,"appsnid":"2","msgtype":"APPNOTICEMSGTYPE001","hasnotice":"SFPD002","hasread":"SFPD001","noticetime":null,"readtime":"2017-08-28 17:30:12.0","noticelogo":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     * total : 1
     * err : 0
     */

    private int total;
    private int err;
    /**
     * userid : 10275
     * snid : 1
     * acomment : acomment
     * createtime : null
     * appsnid : 2
     * msgtype : APPNOTICEMSGTYPE001
     * hasnotice : SFPD002
     * hasread : SFPD001
     * noticetime : null
     * readtime : 2017-08-28 17:30:12.0
     * noticelogo : null
     * imgurl : null
     * imgurlshow :
     * imgurlcompressshow :
     */

    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String userid;
        private String snid;
        private String acomment;
        private String createtime;
        private String appsnid;
        private String msgtype;
        //private String msgtypeshow;
        private String hasnotice;
        private String hasread;
        private String noticetime;
        private String readtime;
        private String noticelogo;
        private String imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getAcomment() {
            return acomment;
        }

        public void setAcomment(String acomment) {
            this.acomment = acomment;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getAppsnid() {
            return appsnid;
        }

        public void setAppsnid(String appsnid) {
            this.appsnid = appsnid;
        }

        public String getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(String msgtype) {
            this.msgtype = msgtype;
        }

        public String getHasnotice() {
            return hasnotice;
        }

        public void setHasnotice(String hasnotice) {
            this.hasnotice = hasnotice;
        }

        public String getHasread() {
            return hasread;
        }

        public void setHasread(String hasread) {
            this.hasread = hasread;
        }

        public String getNoticetime() {
            return noticetime;
        }

        public void setNoticetime(String noticetime) {
            this.noticetime = noticetime;
        }

        public String getReadtime() {
            return readtime;
        }

        public void setReadtime(String readtime) {
            this.readtime = readtime;
        }

        public String getNoticelogo() {
            return noticelogo;
        }

        public void setNoticelogo(String noticelogo) {
            this.noticelogo = noticelogo;
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
       /* public String getMsgtypeshow() {
            return msgtypeshow;
        }

        public void setMsgtypeshow(String msgtypeshow) {
            this.msgtypeshow = msgtypeshow;
        }*/
    }
}
