package com.ayj.chunmiao.utils;

import com.ayj.chunmiao.AyjSwApplication;

/**
 * Created by zht-pc-09 on 2017/6/21.
 */
public class WebUtils {

    /**
     * 将请求的url拼接上主机地址
     *
     * @param url 请求的url
     * @return
     */
    public static String getRequestUrl(String url) {
        if (AyjSwApplication.IS_DEBUG) {
            return HOST.concat(url);
        }
        return ZS_HOST.concat(url);
    }
    /**
     * 链接的主机名称
     */
    public static final String HOST = "http://crm.21ga.cn:9000/qmkm/myws/";

    public static final String IMGAE_UPLOAD = "http://crm.21ga.cn:9000/qmkmimageupload/UploadFile.aspx";

    public static final String FX_URL_IMG = "http://crm.21ga.cn:8000/qmkmimageupload/Upload/2017-08-31/20170831184917231.png";

    public static final String HOST_TWO = "http://crm.21ga.cn:9000/qmkmimageupload/UploadFile.aspx";

    public static  final String ZS_HOST = "http://crm.21ga.cn:9000/qmkm/myws/";

    public static  final String BD_HOST = "http://192.168.1.112:8080/qmkm/myws/";
    /*
    * 登录
    * */
    public static final String LOGN_URL = "getShopownerInfoByMobileAndPwd.action";

    /*
    * 穴位导航
    * */
    public static final String DH_SM = "http://crm.21ga.cn:9000/wbeH5/Health/guideList.html";
    /*分享链接*/
    public static final String SHARE_URL = "http://crm.21ga.cn:9000/wbeH5/coupon/reward/reward.html?snid=";
    /*
    * 春苗所在春苗店的预约服务情况
    * */
    public static final String YY_URL = "getShopServiceBookListByUserid.action";
    /*
    * 协助签到
    * */
    public static final String QD_URL = "setServiceBookSignin.action";

    //获取门店设备的工作组一级目录
    public static final String SB_GL_URL = "getEqWorkGroupList.action";

    //获取门店设备的工作组二级目录
    public static final String SB_GL_TWO_URL = "getEqListByWorkGroupId.action";

    //修改设备工作组名称
    public static final String CHANGE_SB_URL = "updateEqWorkgroupRef.action";

    //春苗员工信息
    public static final String CM_MENBER_URL = "getEmployeeByShopid.action";

    //预约信息
    public static final String XX_YY = "getShopEmployeeWorkRecordList.action";

    //冻结账号
    public static final String DJ_YG_URL = "setFreezeShopEmployeeAccount.action";

    //修改密码
    public static final String XG_URL = "updateUserpwd.action";

    //门店管理 进货
    public static final String MDGL_URL = "getShopStockInList.action";

    //门店管理 销售
    public static final String XS_URL = "getShopMatUseList.action";

    //门店管理 存量
    public static final String CL_URL = "getShopStockList.action";

    //类似通讯录功能
    public static final String TX_URL = "getShopMemberByUseridAndPwd.action";

    //添加联系人,设置门店与会员关系，即客户圈绑定关系
    public static final String ADD_MENBER_URL = "setShopMember.action";

    //申请体验用户
    public static final String SQ_TY = "setMemberTypeApply.action";

    //送券
    public static final String SEND_QUE = "setShopFreeTicketToMember.action";

    //获取验证码
    public static final String HQ_YZ = "sendSms.action";

    //手机号注册
    public static final String ZC_URL = "setMemberRegAndCheckSms.action";

    //协议
    public static final String XY_URL = "getAgreementBySnid.action";

    //身份证注册
    public static final String SFZ_URL = "setMemberRegForShopHelpReg.action";

    //会员卡
    public static final String GET_HYK = "getMemberCardList.action";

    //约单列表,物料
    public static final String YD_LIST = "getMatinfoListByPS.action";

    //获取体验券
    public static final String GET_TYQ = "getMemberTicketList.action";

    //获取营养吧
    public static final String YYB = "getMembershipCoupons.action";

    //通讯录获取密码
    public static final String GET_ZH_MA = "getMemberInfoByMobileAndPwd.action";

    public static final String GET_MENBER_DETAIL = "getMemberCardUseScopeList.action";//获取会员卡详情

    public static final String SEND_DD = "setMemberComsume.action";//会员下订单

    public static final String XSLU_URL = "getShopOrderListByUseridAndPwd.action";//销售流水

    public static final String XD_EJ_URL = "getShoppingmallClassTwo.action";//获取商城二级分类

    public static final String SP_DETAIL_URL = "getShoppingmallList.action";//获取商城分类内商品,服务券

    public static final String FWQ_URL = "getMemberTicketList.action";//获取会员券

    public static final String ZFB_URL = "getAliPaySign.action";//支付宝签名

    public static final String ZFB_TZ = "notify_url_alipay.action";//支付宝通知

    public static final String ZFB_BX = "notify_url_alipay_for_insuranceorder.action";//保险下单支付宝通知

    public static final String ZFB_CG = "notify_url_alipay_for_shopstockin.action";//采购下单支付宝通知

    public static final String ZFB_LX = "notify_url_alipay_for_unionsalematdeposit.action";//联销下单支付宝通知

    public static final String WX_XD = "setMemberPayType.action";//微信下单\更改会员订单支付方式

    public static final String CG_PAY_TYPE = "setShopStockOrderPayType.action";//采购修改支付方式

    public static final String LX_MAT_PAY_TYPE = "setUnionSaleMatDepositPayType.action";//联销保证金修改支付方式

    public static final String BX_PAY_TYPE = "setInsuranceOrderPayType.action";//会员保险修改支付方式

    public static final String SHOP_DETAIL="getMatinfoFromShoppingmallByMatid.action";//获取商品详情

    public static final String SHOP_DETAIL_ADDRESS ="getMemberAddrList.action";//查询收货地址

    public static final String MENBER_GET_GWC ="getMemberShoppingCartList.action";//会员获取购物车

    public static final String MENBER_XDD ="setEmoneyExchangeOrder.action";//会员兑换下订单

    public static final String GET_VEHICLE_INFO_URL = "getMemberCarList.action";//获取会员车辆信息

    public static final String MENBER_XIA_GWC ="setMemberShoppingCart.action";//下购物车

    public static final String MENBER_XG_GWC ="updateShopShoppingCartNum.action";//修改购物车

    public static final String MENBER_DEL_GWC = "deleteMemberShoppingCart.action";//会员删除购物车

    public static final String GET_DQ = "getAreaListByPid.action";//地区信息

    public static final String ADD_NEW_ADDRESS = "setMemberAddr.action";//新增收货地址

    public static final String GET_INSURANCE_PRICE = "getInsuranceorderCarQuoteList.action";//保险报价反馈

    public static final String YZ_MA = "validateMemberPayPwd.action";//验证会员支付密码

    public static final String ZH_ZF = "getMemberAccountInfo.action";//获取账户支付信息

    public static final String DH_QB = "setMemberEMoneyToSmallMoney.action";//兑换钱包

    public static final String CONFIRM_CXDD_URL = "setInsuranceOrder_car_confirm.action";//车险核保订单

    public static final String UPDATE_SENDTYPE_URL = "updateInsuranceOrdeCarBdSendtype.action";//车险比价单

    public static final String GET_SIGNEDBRAND_URL = "getInsuranceCompBrandListByBrokerid.action";//获取签约的保险公司品牌

    public static final String KL_KMZ = "getMembercardCenterListByClassidAndMatname.action";//康乐卡面值

    public static final String TC_URL = "getTccenterAndSalespromotionList.action";//获取套餐内容

    public static final String GET_FGS_URL = "getInsuranceBrokerListByInsfrom.action";//分公司

    public static final String GET_SIGNEDFGS_URL = "getInsuranceCompListByBrokeridAndInsuranceBrand.action";//获取签约的分公司

    public static final String GET_BXFA_URL = "getInsuranceSchemeListByIcsnid.action";//保险下订单

    public static final String SET_CXDD_URL = "setInsuranceOrder_car.action";//车险下订单

    public static final String POST_IMG = "qmkmimageupload/UploadFile.aspx";//上传图片

    public static final String JK_SHOP = "getShopinfoByShopid.action";//根据shopid获取shop信息

    public static final String BB_GX = "getAppverByAppsnid.action";//版本更新

    public static final String DZ_LQ_PAY = "getUseSmallMoneyDiscount.action";//零钱打折

    public static final String SP_DETAIL ="getShopinfoByShopid.action";//商品详情

    public static final String H5_DETAIL ="http://crm.21ga.cn:9000/wbeH5/serviceIntroduction/serviceIntroduction.html?";//H5界面

    public static final String CH_DETAIL ="getEmployeeByShopid2.action";//获取春苗站点详细信息

    public static final String CH_PJ ="getComsumePjByShopid.action";//获取春苗站点评论

    public static final String YKFX_URL ="getShopAccountBookTotal.action";//阴亏分析

    public static final String YKFX_XE_DETAIL ="getShopAccountBookTotalSalemoneyDetail.action";//阴亏分析总销额明细

    public static final String YKFX_TC_DETAIL ="getShopAccountBookTotalTcDetail.action";//阴亏分析总提成明细

    public static final String YKFX_KZ_DETAIL ="getShopAccountBookTotalPayoutDetail.action";//阴亏分析总开支明细

    public static final String HC_POST = "setShopMatUseInfo.action";//耗材提交

    public static final String JF_XI = "getShopEMoneyList.action";//积分信息

    public static final String JB_XI = "getShopGoldMoneyList.action";//金币信息

    public static final String KHQ_CZ = "getMemberSmallMoneyFaceCardList.action";//充值

    public static final String CL_FX = "getDictListByDicttypeid.action";//获取分类

    public static final String CL_ZL = "getCarlinesList.action";//获取车辆种类

    public static final String XZ_Cl = "setMemberCar.action";//新增会员车辆信息

    public static final String MY_BX_LIST = "getInsuranceOrderList.action";//获取会员保险订单信息

    public static final String BX_ZF = "setInsuranceOrderPayType.action";//更改保险支付方式

    public static final String CL_XX = "getAllCarlinesListGroupByBrand.action";//车辆信息

    public static final String BX_LIST ="getInsuranceCenterListByClassidAndMatnameAndBrand.action";//保险列表

    public static final String BX_POST ="getInsuranceSchemeListByIcsnid.action";//保险下订单

    public static final String BD_DETAIL ="getMatinfoFromInsurancecenterByMatid.action";//获取保险具体信息

    public static final String BX_FCX ="setInsuranceOrder.action";//获取保险具体信息非车险

    public static final String BX_MAIN_BOTTOM ="getInsuranceCenterAllListGroupByInscentertype.action";//获取保险首页底部信息

    public static final String BDY_MP4="http://test.21ga.cn:9080/qmkmimageupload/Upload/2016-12-12/20161212163847784.mp4";//北斗仪视频

    public static final String DETALE_CL="deleteMemberCar.action";//删除会员车辆信息

    public static final String XG_XL="updateMemberCar.action";//修改会员车辆信息

    public static final String CX_URL= "getAreaListByPid.action";//车险地址

    public static final String PERSON_AGENCY_APPLY_URL="setAgencyApply.action";//提交代理申请

    public static final String SET_JOIN_APPLY="setJoininApply.action";//提交加盟申请

    public static final String GET_SHOPPING_MALL_LIST="getShoppingmallListForChunmiao.action";//获取春苗进货的商城列表

    public static final String CG_XD = "setShopStockOrder.action";//采购下单

    public static final String CG_GWC = "getShopShoppingCartList.action";//采购购物车

    public static final String ADD_GWC = "setShopShoppingCart.action";//加入购物车

    public static final String DELETE_GWC ="deleteShopShoppingCart.action";//删除购物车

    public static final String JM_SH = "getJoininApplyInfoByMobile.action";//加盟审核

    public static final String YY_FWF = "getServiceFeeByShopidAndMatid.action";//服务费

    public static final String LX_SB = "setUnionSaleMaterialApply.action";//联销申报

    public static final String LX_SB_UP = "updateUnionSaleMaterialApply.action";//联销申报修改

    public static final String LX_SB_STEP2 = "setUnionSaleMaterialApply2.action";//联销二次申报

    public static final String AYB_LS  ="getMemberSmallmoneyHisList.action";//个人中心流水

    public static final String LXSB_LIST  = "getUnionSaleMaterialApplyList.action";//获取联销申报列表

    public static final String BZJ_LIST  = "getUnionSaleMaterialDepositList.action";//获取我的保证金

    public static final String UNIT_LIST  = "getDdMainUnitList.action";//获取单位列表

    public static final String JF_ORDER  = "setShopMoneyBagOrder.action";//积分、金币下单

    public static final String MONEY_CHANGE  = "getShopMoneyBagAndHisList.action";//获取门店金库、积分、金币流水

    public static final String SET_SHOP_PAY_PWD  = "setShopPayPwd.action";//设置门店支付密码

    public static final String VAL_SHOP_PAY_PWD  = "validateShopPayPwd.action";//验证门店支付密码

    public static final String UPD_SHOP_PAY_PWD  = "updateShopPayPwd.action";//修改门店支付密码

    public static final String RESET_SHOP_PAY_PWD  = "resetShopPaypwd.action";//重置门店支付密码

    public static final String SHOP_WITHDRAW  = "setShopWithdrawApply.action";//小金库提现

    public static final String SHOP_WITHDRAW_HIS = "getShopWithdrawApplyList.action";//小金库提现历史

    public static final String SHOP_BANK_INFO  = "getShopBankInfo.action";//获取门店绑定银行卡

    public static final String ABOUT_US = "getAboutus.action";//关于我们

    public static final String SHOP_MONEY_BAG  = "getShopMoneyBag.action";//获取门店钱袋

    public static final String SHOP_STOCK_ORDER  = "getShopStockInDetailList.action";//门店采购订单详情

    public static final String CANCEL_SHOP_STOCK_ORDER  = "cancleShopStockOrder.action";//取消采购订单

    public static final String RECEIVE_SHOP_STOCK_ORDER  = "setShopStockInShouhuo.action";//采购订单收货

    public static final String SHOP_TICKET  = "getShopAwardTicketUseStatusList.action";//获取门店物卷使用情况

    public static final String SHOP_AWARD  = "setShopAward.action";//打赏(物卷，积分)

    public static final String AWARD_HIS  = "getShopAwardList.action";//打赏记录

    public static final String SHOP_UNION_SALE = "getShopUnionSaleListForPayAndShare.action";//联销单列表(打赏和支付)

    public static final String SHARE_UNION_SALE = "setShopShareUnionSale.action";//分享联销单

    public static final String SHARE_UNION_HIS = "getShopShareUnionSaleList.action";//分享记录

    public static final String SUPPLIER_SHOP = "getUnionSaleSupplierShopList.action";//门店列表

    public static final String APP_NOTICE = "getAppNoticeList.action";//消息通知

    public static final String APPLY_EMP = "setShopEmployeeAccountApply.action";//员工账号申请

    public static final String EMONEY_HIS_FORM = "getShopEMoneyHisOfPlatform.action";//积分平台产生量明细

    public static final String GOlD_HIS_FORM = "getShopGoldHisOfPlatform.action";//金币平台产生量明细

    public static final String EMONEY_HIS_DH= "getShopEMoneyHisOfExchange.action";//积分兑换明细

    public static final String GOLD_HIS_DH= "getShopGoldHisOfExchange.action";//金币兑换明细

    public static final String GET_DH_MONEY = "getMemberEmoneyToSmallMoneyFaceList.action";//兑换零钱

    public static final String RESET_PWD = "resetUserpwd.action";//修改登录密码

    public static final String MSG_NUM = "getShopCenterMsgTotalCount.action";//消息数量

    public static final String UN_PAY = "getShopCenterUnionSaleMsgCount.action";//未支付消息数量
}
