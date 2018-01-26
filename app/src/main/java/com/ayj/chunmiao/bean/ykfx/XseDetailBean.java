package com.ayj.chunmiao.bean.ykfx;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public class XseDetailBean {

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"createdate":"2017-07-21","itemdesc":"【血红素系列】力血康咀嚼片孕妇装（20片/盒）","itemmoney":"1780.00"}]
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
         * createdate : 2017-07-21
         * itemdesc : 【血红素系列】力血康咀嚼片孕妇装（20片/盒）
         * itemmoney : 1780.00
         */

        private String createdate;
        private String itemdesc;
        private String itemmoney;

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getItemdesc() {
            return itemdesc;
        }

        public void setItemdesc(String itemdesc) {
            this.itemdesc = itemdesc;
        }

        public String getItemmoney() {
            return null==itemmoney?"":itemmoney;
        }

        public void setItemmoney(String itemmoney) {
            this.itemmoney = itemmoney;
        }
    }
}
