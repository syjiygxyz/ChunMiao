package com.ayj.chunmiao.bean;
import android.support.v7.util.AsyncListUtil;

import com.ayj.chunmiao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/5/24.
 * 通用item实体类
 */
public class UtilityItem {

    private String text;//文字

    private int resId;//资源文件

    private int type;//类型点击

    private String textId;

    private String redPoint;

    public UtilityItem() {
    }

    public UtilityItem(String text, String textId) {
        this.text = text;
        this.textId = textId;
    }

    public UtilityItem(String text, int resId, int type) {
        this.text = text;
        this.resId = resId;
        this.type = type;
    }
    public UtilityItem(String text, int resId) {
        this.text = text;
        this.resId = resId;
    }
    public UtilityItem(String text) {
        this.text = text;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRedPoint() {
        return redPoint;
    }

    public void setRedPoint(String redPoint) {
        this.redPoint = redPoint;
    }

    public static List<UtilityItem> getMainGridList(){
        List<UtilityItem> lists = new ArrayList<>();
        lists.add(new UtilityItem("库存", R.mipmap.main_bottom_1,1));
        lists.add(new UtilityItem("采购", R.mipmap.main_bottom_10,10));
        lists.add(new UtilityItem("进销存", R.mipmap.main_bottom_3,3));
        lists.add(new UtilityItem("销售记录", R.mipmap.main_bottom_2,2));
        lists.add(new UtilityItem("春苗帮助", R.mipmap.main_bottom_7,7));
        lists.add(new UtilityItem("客户圈", R.mipmap.main_bottom_5,5));
        lists.add(new UtilityItem("春苗小店", R.mipmap.main_bottom_6,6));
        lists.add(new UtilityItem("盈亏分析", R.mipmap.main_bottom_4,4));
        lists.add(new UtilityItem("养生导航", R.mipmap.main_bottom_8,8));
        lists.add(new UtilityItem("体验券", R.mipmap.main_bottom_9,9));
        lists.add(new UtilityItem("门店联销", R.mipmap.main_bottom_11,11));
        return lists;
    }
    public static List<UtilityItem> getCmHelpList(){
        List<UtilityItem> lists = new ArrayList<>();
        lists.add(new UtilityItem("预约", R.mipmap.cmbz_1,1));
        lists.add(new UtilityItem("会员卡", R.mipmap.cmbz_2,2));
        lists.add(new UtilityItem("充值", R.mipmap.cmbz_3,3));
        lists.add(new UtilityItem("套餐", R.mipmap.cmbz_4,4));
        lists.add(new UtilityItem("兑换", R.mipmap.cmbz_5,5));
        lists.add(new UtilityItem("邻家小店", R.mipmap.cmbz_6,6));
        lists.add(new UtilityItem("折扣商铺", R.mipmap.cmbz_7,7));
        lists.add(new UtilityItem("保险经纪", R.mipmap.cmbz_8,8));
        lists.add(new UtilityItem("服务券", R.mipmap.cmbz_9,9));
        lists.add(new UtilityItem("注册", R.mipmap.cmbz_10,10));
        return lists;
    }

    public static List<UtilityItem> getXslsList(){
        List<UtilityItem> lists = new ArrayList<>();
        lists.add(new UtilityItem("调理服务", R.mipmap.xsjl_1,1));
        lists.add(new UtilityItem("商铺", R.mipmap.xsjl_2,2));
        lists.add(new UtilityItem("营养吧", R.mipmap.xsjl_3,3));
        lists.add(new UtilityItem("兑换", R.mipmap.xsjl_4,4));
        return lists;
    }

    public static List<UtilityItem> getCmxdList(){
        List<UtilityItem> lists = new ArrayList<>();
        lists.add(new UtilityItem("春苗展柜", R.mipmap.cmxd_1,1));
        lists.add(new UtilityItem("折扣商铺", R.mipmap.cmxd_2,2));
        lists.add(new UtilityItem("兑换中心", R.mipmap.cmxd_3,3));
        lists.add(new UtilityItem("邻家小店", R.mipmap.cmxd_4,4));
        return lists;
    }

    public static List<UtilityItem> getInsuranceList(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("车辆保险", R.mipmap.insurance_car,1));
        list.add(new UtilityItem("北斗车载", R.mipmap.insurance_beidou,2));
        list.add(new UtilityItem("我的保险", R.mipmap.insurance_my,4));
        list.add(new UtilityItem("保险咨询", R.mipmap.insurance_advice,5));
        list.add(new UtilityItem("车辆信息", R.mipmap.insurance_car_information,3));
        list.add(new UtilityItem("报价反馈", R.mipmap.insurance_feedback,6));
        return list;
    }

    public static List<UtilityItem> getInsuranceCompnayList(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("中国人保", R.mipmap.bx_rb,3));
        list.add(new UtilityItem("太平洋保险", R.mipmap.bx_tp,2));
        list.add(new UtilityItem("天安保险", R.mipmap.bx_ta,4));
        list.add(new UtilityItem("中联金安", R.mipmap.bx_zl,1));
        return list;
    }

    public static List<UtilityItem> getCgList(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("现货架", R.mipmap.cg_1));
        list.add(new UtilityItem("耗材", R.mipmap.cg_3));
        list.add(new UtilityItem("促销券", R.mipmap.cg_4));
        list.add(new UtilityItem("爱e币", R.mipmap.cg_5));
        //list.add(new UtilityItem("金币", R.mipmap.cg_6));
        return list;
    }
    public static List<UtilityItem> getKhqMain(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("保健预约", R.mipmap.jk_1,1));
        list.add(new UtilityItem("保险服务", R.mipmap.jk_2,2));
        list.add(new UtilityItem("邻家小店", R.mipmap.jk_3,3));
        list.add(new UtilityItem("折扣商铺", R.mipmap.jk_4,4));
        list.add(new UtilityItem("春苗兑换", R.mipmap.jk_5,5));
        list.add(new UtilityItem("充值加入", R.mipmap.jk_6,6));
        list.add(new UtilityItem("春苗热柜", R.mipmap.jk_7,7));
        list.add(new UtilityItem("春苗分享", R.mipmap.jk_8,8));
        list.add(new UtilityItem("招商加盟", R.mipmap.jk_9,9));
        list.add(new UtilityItem("营养吧", R.mipmap.jk_10,10));
        return list;
    }
    public static List<UtilityItem> getLxList(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("自销", R.mipmap.lxzx));
        list.add(new UtilityItem("量贩分摊", R.mipmap.lxlfft));
        list.add(new UtilityItem("量贩采购", R.mipmap.lxlfcg));
        return  list;
    }
    /*门店中心菜单*/
    public static List<UtilityItem> getShopCenterList(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("门店订单",R.mipmap.grzx_mddd,1));
        list.add(new UtilityItem("店长打赏",R.mipmap.grzx_mdds,2));
        list.add(new UtilityItem("联销审核",R.mipmap.grzx_lxsh,3));
        list.add(new UtilityItem("保证金",R.mipmap.grzx_bzj,4));
        list.add(new UtilityItem("联销单",R.mipmap.grzx_lxd,5));
        list.add(new UtilityItem("物卷",R.mipmap.grzx_wujuan,6));
        list.add(new UtilityItem("账号安全",R.mipmap.grzx_zhaq,7));
        return list;
    }
    /*门店订单分类*/
    public static List<UtilityItem> getShopStockOrder(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("现货架订单"));
        list.add(new UtilityItem("促销订单"));
        list.add(new UtilityItem("门店耗材订单"));
        list.add(new UtilityItem("联销单采购销"));
        list.add(new UtilityItem("联销单分摊销"));
        //list.add(new UtilityItem("门店积分订单"));
        return list;
    }
    /*支付方式*/
    public static List<UtilityItem> getPayWayList(){
        List<UtilityItem> list = new ArrayList<>();
        list.add(new UtilityItem("支付宝",R.mipmap.zhifufangshi_zhifubao));
        list.add(new UtilityItem("微信",R.mipmap.zhifufangshi_weixin));
        list.add(new UtilityItem("小金库",R.mipmap.grzx_lxd));
        return list;
    }

    public static List<UtilityItem> dhGridList(){
        List<UtilityItem> lists = new ArrayList<>();
        lists.add(new UtilityItem("爱e币兑换", R.mipmap.cmdh_2,2));
        lists.add(new UtilityItem("金币兑换", R.mipmap.cmdh_1,1));
        lists.add(new UtilityItem("物卷兑换", R.mipmap.cmdh_3,3));

        return lists;
    }

}
