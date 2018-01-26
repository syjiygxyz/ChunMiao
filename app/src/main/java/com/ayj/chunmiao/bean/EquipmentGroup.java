package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/2/8.
 */
public class EquipmentGroup {

    /**
     * err : 0
     * msg : 获取成功
     * total : 5
     * data : [{"createtime":"2017-02-08 09:35:06.0","shopid":"3","snid":"5",
     * "workgroupname":"lalal"},{"createtime":"2017-02-08 09:36:55.0","shopid":"3","snid":"6",
     * "workgroupname":"4678"},{"createtime":"2017-01-22 08:36:53.0","shopid":"3","snid":"2",
     * "workgroupname":"??"},{"createtime":"2017-02-06 16:27:11.0","shopid":"3","snid":"3",
     * "workgroupname":"2"},{"createtime":"2017-02-06 20:48:05.0","shopid":"3","snid":"4",
     * "workgroupname":"shebei2"}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * createtime : 2017-02-08 09:35:06.0
     * shopid : 3
     * snid : 5
     * workgroupname : lalal
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

    public static class DataBean {
        private String createtime;
        private String shopid;
        private String snid;
        private String workgroupname;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getSnid() {
            return snid;
        }

        public void setSnid(String snid) {
            this.snid = snid;
        }

        public String getWorkgroupname() {
            return workgroupname;
        }

        public void setWorkgroupname(String workgroupname) {
            this.workgroupname = workgroupname;
        }
    }
}
