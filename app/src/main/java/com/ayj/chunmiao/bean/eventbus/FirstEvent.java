package com.ayj.chunmiao.bean.eventbus;

/**
 * Created by zht-pc-09 on 2017/6/26.
 */
public class FirstEvent {

    private String mMsg;
    public FirstEvent(String msg) {
        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
