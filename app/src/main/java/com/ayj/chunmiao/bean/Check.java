package com.ayj.chunmiao.bean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/22.
 */
public class Check {

    /**
     * err : 0
     * msg : 短信发送成功
     * total : 0
     * data : null
     */

    private int err;
    private String msg;
    private int total;
    private Object data;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
