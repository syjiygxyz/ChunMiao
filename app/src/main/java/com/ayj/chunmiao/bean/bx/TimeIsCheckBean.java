package com.ayj.chunmiao.bean.bx;

/**
 * Created by Administrator on 2017/7/26.
 */
public class TimeIsCheckBean {

    private String timee;

    private Boolean isCheck;

    public String getTimee() {
        return timee;
    }

    public void setTimee(String timee) {
        this.timee = timee;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    public TimeIsCheckBean(String timee, Boolean isCheck) {
        this.timee = timee;
        this.isCheck = isCheck;
    }
}
