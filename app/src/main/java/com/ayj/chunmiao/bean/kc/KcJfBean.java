package com.ayj.chunmiao.bean.kc;

/**
 * Created by zht-pc-04 on 2017/8/16.
 */

public class KcJfBean {

    /**
     * err : 0
     * msg :
     * total : 4
     * data : {"inventoryBalance":"-1096","PlatformThroughput":"4","storeTurnoverOnLine":"-500","storeTurnoverLine":"0"}
     */

    private int err;
    private String msg;
    private String total;
    /**
     * inventoryBalance : -1096
     * PlatformThroughput : 4
     * storeTurnoverOnLine : -500
     * storeTurnoverLine : 0
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String inventoryBalance;
        private String platformThroughput;
        private String storeTurnoverOnLine;
        private String storeTurnoverLine;


        public String getStoreTurnover() {
            return storeTurnover;
        }

        public void setStoreTurnover(String storeTurnover) {
            this.storeTurnover = storeTurnover;
        }

        private String storeTurnover;

        public String getInventoryBalance() {
            return inventoryBalance;
        }

        public void setInventoryBalance(String inventoryBalance) {
            this.inventoryBalance = inventoryBalance;
        }

        public String getPlatformThroughput() {
            return platformThroughput;
        }

        public void setPlatformThroughput(String PlatformThroughput) {
            this.platformThroughput = PlatformThroughput;
        }

        public String getStoreTurnoverOnLine() {
            return storeTurnoverOnLine;
        }

        public void setStoreTurnoverOnLine(String storeTurnoverOnLine) {
            this.storeTurnoverOnLine = storeTurnoverOnLine;
        }

        public String getStoreTurnoverLine() {
            return storeTurnoverLine;
        }

        public void setStoreTurnoverLine(String storeTurnoverLine) {
            this.storeTurnoverLine = storeTurnoverLine;
        }
    }
}
