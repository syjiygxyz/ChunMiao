package com.ayj.chunmiao.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhangpan on 2016/7/2.
 */
public class ProvinceBean {

    /**
     * 编号
     */

    private String name;

    private String id;

    public ProvinceBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public ProvinceBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //这个用来显示在PickerView上面的字符串,PickerView会通过反射获取getPickerViewText方法显示出来。
    public String getPickerViewText() {
        //这里还可以判断文字超长截断再提供显示
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
