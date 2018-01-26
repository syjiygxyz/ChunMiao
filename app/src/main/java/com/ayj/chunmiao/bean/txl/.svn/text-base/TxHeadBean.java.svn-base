package com.ayj.chunmiao.bean.txl;

import com.mcxtzhang.indexlib.IndexBar.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/30.
 * 通讯录头部
 */
public class TxHeadBean extends BaseIndexPinyinBean {
    private List<TxBean.DataBean> cityList;
    //悬停ItemDecoration显示的Tag
    private String suspensionTag;

    public TxHeadBean() {
    }

    public TxHeadBean(List<TxBean.DataBean> cityList, String suspensionTag, String indexBarTag) {
        this.cityList = cityList;
        this.suspensionTag = suspensionTag;
        this.setBaseIndexTag(indexBarTag);
    }

    public List<TxBean.DataBean> getCityList() {
        return cityList;
    }

    public TxHeadBean setCityList(List<TxBean.DataBean> cityList) {
        this.cityList = cityList;
        return this;
    }

    public TxHeadBean setSuspensionTag(String suspensionTag) {
        this.suspensionTag = suspensionTag;
        return this;
    }

    @Override
    public String getTarget() {
        return null;
    }

    @Override
    public boolean isNeedToPinyin() {
        return false;
    }

    @Override
    public String getSuspensionTag() {
        return suspensionTag;
    }


}

