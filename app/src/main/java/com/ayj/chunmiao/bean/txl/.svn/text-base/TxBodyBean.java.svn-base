package com.ayj.chunmiao.bean.txl;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/30.
 * 通讯录body部分
 */
public class TxBodyBean extends BaseIndexPinyinBean {
    private TxBean.DataBean city;
    public TxBodyBean() {
    }

    public TxBodyBean(TxBean.DataBean city) {
        this.city = city;
    }

    public TxBean.DataBean getCity() {
        return city;
    }

    public TxBodyBean setCity(TxBean.DataBean city) {
        this.city = city;
        return this;
    }

    @Override
    public String getTarget() {
        return null==city.getName()?"未命名":city.getName().toString();
    }
}
