package com.ayj.chunmiao.bean.ykfx;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class YkfxMainBean {

    /**
     * err : 0
     * msg :
     * total : 1
     * data : [{"totalsalemoney":"344.23","totaltc":"41.31","totalpayout":"0.00","totalprofit":"0.00"}]
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
         * totalsalemoney : 344.23
         * totaltc : 41.31
         * totalpayout : 0.00
         * totalprofit : 0.00
         */

        private String totalsalemoney;
        private String totaltc;
        private String totalpayout;
        private String totalprofit;

        public String getTotalsalemoney() {
            return totalsalemoney;
        }

        public void setTotalsalemoney(String totalsalemoney) {
            this.totalsalemoney = totalsalemoney;
        }

        public String getTotaltc() {
            return totaltc;
        }

        public void setTotaltc(String totaltc) {
            this.totaltc = totaltc;
        }

        public String getTotalpayout() {
            return totalpayout;
        }

        public void setTotalpayout(String totalpayout) {
            this.totalpayout = totalpayout;
        }

        public String getTotalprofit() {
            return totalprofit;
        }

        public void setTotalprofit(String totalprofit) {
            this.totalprofit = totalprofit;
        }
    }
}
