package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/26.
 */
public class DhMoneyBean {

    /**
     * err : 0
     * msg : 获取积分兑换零钱面值列表信息成功
     * total : 5
     * data : [{"orderno":"1","paramname":"1000爱e币","dictid":"MEMBEREMONEYTOSMALLMONEYFACE004","dicttypeid":"MEMBEREMONEYTOSMALLMONEYFACE","paramremark":"100","canmodify":"1","delflag":"0","parentid":null,"mark":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"orderno":"2","paramname":"2000爱e币","dictid":"MEMBEREMONEYTOSMALLMONEYFACE001","dicttypeid":"MEMBEREMONEYTOSMALLMONEYFACE","paramremark":"200","canmodify":"1","delflag":"0","parentid":null,"mark":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"orderno":"3","paramname":"4000爱e币","dictid":"MEMBEREMONEYTOSMALLMONEYFACE005","dicttypeid":"MEMBEREMONEYTOSMALLMONEYFACE","paramremark":"400","canmodify":"1","delflag":"0","parentid":null,"mark":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"orderno":"3","paramname":"10000爱e币","dictid":"MEMBEREMONEYTOSMALLMONEYFACE003","dicttypeid":"MEMBEREMONEYTOSMALLMONEYFACE","paramremark":"1000","canmodify":"1","delflag":"0","parentid":null,"mark":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""},{"orderno":"4","paramname":"5000爱e币","dictid":"MEMBEREMONEYTOSMALLMONEYFACE002","dicttypeid":"MEMBEREMONEYTOSMALLMONEYFACE","paramremark":"500","canmodify":"1","delflag":"0","parentid":null,"mark":null,"imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
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
         * orderno : 1
         * paramname : 1000爱e币
         * dictid : MEMBEREMONEYTOSMALLMONEYFACE004
         * dicttypeid : MEMBEREMONEYTOSMALLMONEYFACE
         * paramremark : 100
         * canmodify : 1
         * delflag : 0
         * parentid : null
         * mark : null
         * imgurl : null
         * imgurlshow :
         * imgurlcompressshow :
         */

        private String orderno;
        private String paramname;
        private String dictid;
        private String dicttypeid;
        private String paramremark;
        private String canmodify;
        private String delflag;
        private Object parentid;
        private Object mark;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

        public String getOrderno() {
            return orderno;
        }

        public void setOrderno(String orderno) {
            this.orderno = orderno;
        }

        public String getParamname() {
            return paramname;
        }

        public void setParamname(String paramname) {
            this.paramname = paramname;
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

        public String getParamremark() {
            return paramremark;
        }

        public void setParamremark(String paramremark) {
            this.paramremark = paramremark;
        }

        public String getCanmodify() {
            return canmodify;
        }

        public void setCanmodify(String canmodify) {
            this.canmodify = canmodify;
        }

        public String getDelflag() {
            return delflag;
        }

        public void setDelflag(String delflag) {
            this.delflag = delflag;
        }

        public Object getParentid() {
            return parentid;
        }

        public void setParentid(Object parentid) {
            this.parentid = parentid;
        }

        public Object getMark() {
            return mark;
        }

        public void setMark(Object mark) {
            this.mark = mark;
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
