package com.ayj.chunmiao.bean.myinformation;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/19.
 */

public class ShopBankBean {

    /**
     * err : 0
     * msg : 获取门店体现银行卡信息成功
     * total : 1
     * data : [{"shopid":"001","snid":"5","bankid":"BANK002","bankidshow":"中国建设银行","shopidshow":"朗悦湾小区服务点","createtime":"2017-08-16 17:13:44.0","bankcardtype":"BANKCARDTYPE001","bankno":"021502616510636","bindingstatus":"SFYX001","bankcardtypeshow":"储蓄卡","bindingstatusshow":"有效","imgurl":null,"imgurlshow":"","imgurlcompressshow":""}]
     */

    private int err;
    private String msg;
    private String total;
    /**
     * shopid : 001
     * snid : 5
     * bankid : BANK002
     * bankidshow : 中国建设银行
     * shopidshow : 朗悦湾小区服务点
     * createtime : 2017-08-16 17:13:44.0
     * bankcardtype : BANKCARDTYPE001
     * bankno : 021502616510636
     * bindingstatus : SFYX001
     * bankcardtypeshow : 储蓄卡
     * bindingstatusshow : 有效
     * imgurl : null
     * imgurlshow :
     * imgurlcompressshow :
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
        private String shopid;
        private String snid;
        private String bankid;
        private String bankidshow;
        private String shopidshow;
        private String createtime;
        private String bankcardtype;
        private String bankno;
        private String bindingstatus;
        private String bankcardtypeshow;
        private String bindingstatusshow;
        private Object imgurl;
        private String imgurlshow;
        private String imgurlcompressshow;

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

        public String getBankid() {
            return bankid;
        }

        public void setBankid(String bankid) {
            this.bankid = bankid;
        }

        public String getBankidshow() {
            return bankidshow;
        }

        public void setBankidshow(String bankidshow) {
            this.bankidshow = bankidshow;
        }

        public String getShopidshow() {
            return shopidshow;
        }

        public void setShopidshow(String shopidshow) {
            this.shopidshow = shopidshow;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getBankcardtype() {
            return bankcardtype;
        }

        public void setBankcardtype(String bankcardtype) {
            this.bankcardtype = bankcardtype;
        }

        public String getBankno() {
            return bankno;
        }

        public void setBankno(String bankno) {
            this.bankno = bankno;
        }

        public String getBindingstatus() {
            return bindingstatus;
        }

        public void setBindingstatus(String bindingstatus) {
            this.bindingstatus = bindingstatus;
        }

        public String getBankcardtypeshow() {
            return bankcardtypeshow;
        }

        public void setBankcardtypeshow(String bankcardtypeshow) {
            this.bankcardtypeshow = bankcardtypeshow;
        }

        public String getBindingstatusshow() {
            return bindingstatusshow;
        }

        public void setBindingstatusshow(String bindingstatusshow) {
            this.bindingstatusshow = bindingstatusshow;
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
