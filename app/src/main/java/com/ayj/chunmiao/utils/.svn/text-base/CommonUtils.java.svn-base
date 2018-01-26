package com.ayj.chunmiao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.cmbz.hyk.HykDetailsActivity;
import com.ayj.chunmiao.activity.cmbz.tc.TcDetailsActivity;
import com.ayj.chunmiao.activity.common.CommonShoppingDetailActivity;
import com.ayj.chunmiao.activity.khq.FrsDetailsActivity;
import com.ayj.chunmiao.activity.khq.yyb.YybActivity;
import com.ayj.chunmiao.activity.myinformation.dashang.ZwActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.cmbz.hyk.HykMainAdapter;
import com.ayj.chunmiao.adapter.cmbz.tc.TcMainAdapter;
import com.ayj.chunmiao.adapter.khq.MainWaterAdapter;
import com.ayj.chunmiao.adapter.khq.SpListAdapter;
import com.ayj.chunmiao.adapter.khq.YybAdapter;
import com.ayj.chunmiao.adapter.tab.TabStyleAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.MemberCard;
import com.ayj.chunmiao.bean.ProvinceBean;
import com.ayj.chunmiao.bean.cmbz.PackageContent;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.bean.khq.YybBean;
import com.ayj.chunmiao.utils.AliPay.GetOrderInfo;
import com.ayj.chunmiao.view.ProgressHUD;
import com.ayj.chunmiao.view.magicindicator.MagicIndicator;
import com.ayj.chunmiao.view.magicindicator.ViewPagerHelper;
import com.ayj.chunmiao.view.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.ayj.chunmiao.view.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/16.
 */
public class CommonUtils {

    private static String webUrl;

    /**
     * 创建加载进度框
     *
     * @param cxt        内容上下文
     * @param message    内容
     * @param cancelable 是否可以取消
     */
    public static ProgressHUD createProgressDialog(Context cxt, String message,
                                                   boolean cancelable) {
        return ProgressHUD.show(cxt, message, true, cancelable, null);
    }

    /**
     * 设置banner的一些属性
     *
     * @param context 上下文对象
     * @param banner  轮播控件
     */
    public static void setBannerAttribute(Context context, Banner banner) {
        if (null != banner) {
            int width = CommonUtils.getScreenWidth(context);
            int bannerHeigth = (width * 9) / 16;
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) banner.getLayoutParams();
            if (params == null) {
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        bannerHeigth);
                banner.setLayoutParams(params);
            } else {
                params.height = bannerHeigth;
                banner.setLayoutParams(params);
            }
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setIndicatorGravity(BannerConfig.CENTER);
            banner.setImageLoader(new GlideImageLoader());
        }
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取确认框
     *
     * @param context         上下文对象
     * @param contentText     内容文字
     * @param confirmListener 确认键监听
     */
    public static SweetAlertDialog getConfirmDialog(Context context, String 签到, String contentText,
                                                    SweetAlertDialog.OnSweetClickListener confirmListener) {
        SweetAlertDialog confirmDialog = new SweetAlertDialog(context);
        confirmDialog.setTitleText("提示");//设置标题
        confirmDialog.setContentText(contentText);//内容文字
        confirmDialog.setConfirmText("确认");
        confirmDialog.setCancelText("取消");
        confirmDialog.setConfirmClickListener(confirmListener);//确认监听
        confirmDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        return confirmDialog;
    }

    public static SweetAlertDialog getConfirmDialog(Context context, String titleText,
                                                    String contentText, String confirmText, String cancelText,
                                                    SweetAlertDialog.OnSweetClickListener confirmListener) {
        SweetAlertDialog confirmDialog = new SweetAlertDialog(context);
        confirmDialog.setTitleText(titleText);//设置标题
        confirmDialog.setContentText(contentText);//内容文字
        confirmDialog.setConfirmText(confirmText);
        confirmDialog.setCancelText(cancelText);
        confirmDialog.setConfirmClickListener(confirmListener);//确认监听
        confirmDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        return confirmDialog;
    }

    public static SweetAlertDialog getConfirmDialog(Context context, String titleText,
                                                    String contentText, String confirmText, String cancelText,
                                                    SweetAlertDialog.OnSweetClickListener confirmListener,
                                                    SweetAlertDialog.OnSweetClickListener cancleListener) {
        SweetAlertDialog confirmDialog = new SweetAlertDialog(context);
        confirmDialog.setTitleText(titleText);//设置标题
        confirmDialog.setContentText(contentText);//内容文字
        confirmDialog.setConfirmText(confirmText);
        confirmDialog.setCancelText(cancelText);
        confirmDialog.setConfirmClickListener(confirmListener);//确认监听
        confirmDialog.setCancelClickListener(cancleListener); //取消监听
        return confirmDialog;
    }

    public static SweetAlertDialog getOnlyConfirmDialog(Context context, String titleText,
                                                        String contentText, String confirmText,
                                                        SweetAlertDialog.OnSweetClickListener confirmListener) {
        SweetAlertDialog confirmDialog = new SweetAlertDialog(context);
        confirmDialog.setTitleText(titleText);//设置标题
        confirmDialog.setContentText(contentText);//内容文字
        confirmDialog.setConfirmText(confirmText);
        confirmDialog.setCancelable(true);
        confirmDialog.setCanceledOnTouchOutside(true);
        confirmDialog.setConfirmClickListener(confirmListener);//确认监听
        confirmDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                System.exit(0);
            }
        });
        return confirmDialog;
    }

    /**
     * 获取success dialog
     *
     * @param context     上下文对象
     * @param contentText 内容文字
     */
    public static SweetAlertDialog getSuccessDialog(Context context, String title,
                                                    String contentText, SweetAlertDialog.OnSweetClickListener confirmClickListener) {
        SweetAlertDialog successDialog = new SweetAlertDialog(context,
                SweetAlertDialog.SUCCESS_TYPE);
        successDialog.setTitleText(title);
        successDialog.setContentText(contentText);
        successDialog.setConfirmText("确认");
        //设置确认监听
        successDialog.setConfirmClickListener(confirmClickListener);

        return successDialog;
    }


    /**
     * 获取error dialog
     *
     * @param context     上下文对象
     * @param contentText 内容
     */
    public static SweetAlertDialog getErrorDialog(Context context, String contentText) {
        SweetAlertDialog errorDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
        errorDialog.setTitleText("提示");
        errorDialog.setContentText(contentText);
        errorDialog.setConfirmText("确认");
        errorDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        });
        return errorDialog;
    }

    /**
     * 获取aCache
     */
    public static ACache getAcache() {
        String cacheStr = "/data/data/com.ayj.chunmiao/shared_prefs/";
        return ACache.get(new File(cacheStr), 1000 * 1000 * 50, Integer.MAX_VALUE);
    }

    /**
     * 检查当前网络是否可用的方法
     *
     * @param context 传入一个上下文对象
     * @return 可用返回true 不可用返回false
     */
    public static boolean isNetworkAvailable(Context context) {
        /**
         * 获取手机所有的连接对象（包括wifi，net等连接的管理）
         */
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取networkinfo对象
            NetworkInfo[] netWorkinfo = connectivityManager.getAllNetworkInfo();
            if (netWorkinfo != null && netWorkinfo.length > 0) {
                for (int i = 0; i < netWorkinfo.length; i++) {
                    /**
                     * 判断已有的网络对象是否处于连接状态 连接状态则返回true 否则返回false
                     */
                    if (netWorkinfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void initMagicIndicator(MagicIndicator tp_ly, List<String> titles,
                                          ViewPager ly_pages, Context mContext) {
        tp_ly.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
//        commonNavigator7.setAdjustMode(true);
//        commonNavigator7.setSkimOver(true);
        commonNavigator7.setScrollPivotX(0.65f);
        TabStyleAdapter tsAdapter = new TabStyleAdapter(titles, ly_pages,
                LinePagerIndicator.MODE_EXACTLY);
        commonNavigator7.setAdapter(tsAdapter);
        tp_ly.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(tp_ly, ly_pages);
    }

    public static void initJfMagicIndicator(MagicIndicator tp_ly, List<String> titles,
                                            ViewPager ly_pages, Context mContext) {
        tp_ly.setBackgroundColor(Color.parseColor("#ffffff"));
        CommonNavigator commonNavigator7 = new CommonNavigator(mContext);
        commonNavigator7.setAdjustMode(true);
        commonNavigator7.setSkimOver(true);
        commonNavigator7.setScrollPivotX(0.65f);
        TabStyleAdapter tsAdapter = new TabStyleAdapter(titles, ly_pages,
                LinePagerIndicator.MODE_EXACTLY);
        commonNavigator7.setAdapter(tsAdapter);
        tp_ly.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(tp_ly, ly_pages);
    }

    /**
     * dip转化成px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /*
    * String 转为calendar，自由的设置日期格式
    * */
    public static Calendar StringToCalendar(String str, String dateStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateStyle);
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    //可根据需要自行截取数据显示date转化为string
    public static String getTime(Date date, String dateStyle) {
        SimpleDateFormat format = new SimpleDateFormat(dateStyle);
        return format.format(date);
    }


    public static void showToast(Context mContext, String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context mContext, int content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }

    public static void paybywx(final Context mContext, String snid,int payFor) {
        final IWXAPI api = WXAPIFactory.createWXAPI(mContext, "wx17e0ee25e2f0f31e");
        //api.registerApp(Constant.APP_ID);
        switch (payFor){
            case 0:
                webUrl = WebUtils.getRequestUrl(WebUtils.WX_XD);
                break;
            case 1:
                webUrl = WebUtils.getRequestUrl(WebUtils.BX_PAY_TYPE);
                break;
            case 2:
                webUrl = WebUtils.getRequestUrl(WebUtils.CG_PAY_TYPE);
                break;
            case 3:
                webUrl = WebUtils.getRequestUrl(WebUtils.LX_MAT_PAY_TYPE);
                break;
            case 4:
                webUrl = WebUtils.getRequestUrl(WebUtils.CG_PAY_TYPE);
                break;
        }
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            if (payFor == 0 || payFor == 1){
                try {
                    obj.put("key","");
                    obj.put("msnid", ((UserPerson)getAcache().getAsObject(ACache.USER_PERSON_INFO_KEY)).getData().get(0).getSnid());
                    obj.put("pwd",((UserPerson)getAcache().getAsObject(ACache.USER_PERSON_INFO_KEY)).getData().get(0).getPassWord());
                    obj.put("snid",snid);
                    obj.put("paytype","PAYTYPE006");
                    obj.put("for_app","2");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    obj.put("key","");
                    obj.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                    obj.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                    obj.put("orderid",snid);
                    obj.put("paytype","PAYTYPE006");
                    obj.put("for_app","2");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            OkHttpUtils.post()
                    .url(webUrl)
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            CommonUtils.showToast(mContext,R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if ("0".equals(response.split(":")[1].split(",")[0])) {
                                PayReq req = new PayReq();
                                req.appId = "wx17e0ee25e2f0f31e";
                                req.partnerId = "1489257572";
                                req.prepayId = response.split(":")[6].split(",")[0];
                                req.nonceStr = response.split(":")[7].split(",")[0];
                                req.timeStamp = response.split(":")[8].substring(0,
                                        response.split(":")[8].length() - 1);
                                req.packageValue = "Sign=WXPay";
                                req.sign = response.split(":")[5].split(",")[0];
                                req.extData = "app data"; // optional
                                api.sendReq(req);
                            } else {
                                CommonUtils.showToast(mContext, response.split("\"")[1]);
                            }
                        }
                    });
        } else {
            CommonUtils.showToast(mContext, R.string.TheNetIsUnAble);
        }
    }

    /**小金库支付*/
    public static void  payByJk(final Context context, String orderId, int payFor, final Handler handler){
        if (CommonUtils.isNetworkAvailable(context)){
            switch (payFor){
                case 0:
                    webUrl = WebUtils.getRequestUrl(WebUtils.WX_XD);
                    break;
                case 1:
                    webUrl = WebUtils.getRequestUrl(WebUtils.BX_PAY_TYPE);
                    break;
                case 2:
                    webUrl = WebUtils.getRequestUrl(WebUtils.CG_PAY_TYPE);
                    break;
                case 3:
                    webUrl = WebUtils.getRequestUrl(WebUtils.LX_MAT_PAY_TYPE);
                    break;
                case 4:
                    webUrl = WebUtils.getRequestUrl(WebUtils.CG_PAY_TYPE);
                    break;
            }
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("orderid",orderId);
                object.put("paytype","PAYTYPE012");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(webUrl)
                    .addParams("json",String.valueOf(object))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            CommonUtils.showToast(context,R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    CommonUtils.showToast(context,"支付成功!");
                                    if (null != handler){
                                        handler.sendEmptyMessage(0);
                                    }
                                    EventBus.getDefault().post(
                                            new FirstEvent("WxZf"));
                                    break;
                                default:
                                    CommonUtils.showToast(context,check.getMsg());
                                    break;
                            }
                        }
                    });
        }else {
            CommonUtils.showToast(context,R.string.TheNetIsUnAble);
        }
    }

    //  省份
    private static ArrayList<ProvinceBean> provinceBeanList = new ArrayList<>();
    //  城市
    private static ArrayList<String> cities;
    private static ArrayList<List<String>> cityList = new ArrayList<>();
    //  区/县
    private static ArrayList<String> district;
    private static ArrayList<List<String>> districts;
    private static ArrayList<List<List<String>>> districtList = new ArrayList<>();

    private static TextView textView;//文本控件

    /**
     * 获取选择城市控件
     */
    public static void getDqPickerView(Context context, TextView tv) {
        textView = tv;
        //  获取json数据
        String province_data_json = GetJsonDataUtil.getJson(context, "province_data.json");
        //  解析json数据
        parseJson(province_data_json);
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context,
                new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String city = provinceBeanList.get(options1).getPickerViewText();
                        String address;
                        //  如果是直辖市或者特别行政区只设置市和区/县
                        if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city)
                                || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                            address = provinceBeanList.get(options1).getPickerViewText()
                                    + " " + "直辖市"
                                    + " " +
                                    districtList.get(options1).get(options2).get(options3);
                        } else {
                            address = provinceBeanList.get(options1).getPickerViewText()
                                    + " " + cityList.get(options1).get(options2)
                                    + " " + districtList.get(options1).get(options2).get(options3);
                        }
                        //设置
                        textView.setText(address);
                    }
                })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(provinceBeanList, cityList, districtList);//三级选择器
        pvOptions.show();
    }

    //  解析json填充集合
    private static void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
                provinceBeanList.add(new ProvinceBean(provinceName));
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    /*会员获取卡*/
    public static void getCard(final Context context, String classids, final RecyclerView rlv, final String str, final int img){
        JSONObject obj = new JSONObject();
        try {
            obj.put("key","");
            obj.put("classid",classids);
            obj.put("startrow","1");
            obj.put("endrow","99");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final ProgressHUD mProgressHub = CommonUtils.createProgressDialog(context, "正在加载中...", false);
        if (mProgressHub != null){
            mProgressHub.show();
        }
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.KL_KMZ))
                .addParams("json",String.valueOf(obj))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mProgressHub.dismiss();
                        CommonUtils.showToast(context,R.string.TheNetIsException);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        mProgressHub.dismiss();
                        Check check = new Gson().fromJson(response,Check.class);
                        switch (check.getErr()){
                            case 0:
                                LayoutInflater mInflater =LayoutInflater.from(context);
                                View view = mInflater.inflate(R.layout.common_hyk_head, null);
                                TextView tv = (TextView) view.findViewById(R.id.tv);
                                tv.setText(str);
                                Drawable drawable = context.getResources().getDrawable(img);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
                                tv.setCompoundDrawables(drawable, null, null, null);
                                MemberCard memberCard = new Gson().fromJson(response,MemberCard.class);
                                final List<MemberCard.DataBean>list = new ArrayList<MemberCard.DataBean>();
                                list.addAll(memberCard.getData());
                                if(str.equals("康乐会员B卡")||str.equals("康乐会员A卡")){
                                    HykMainAdapter adapter = new HykMainAdapter(R.layout.item_membercard,list);
                                    adapter.setHeaderView(view,0);
                                    rlv.setAdapter(adapter);
                                }else if(str.equals("百姓富人水")){
                                    MainWaterAdapter adapter = new MainWaterAdapter(R.layout.item_package,list);
                                    adapter.setHeaderView(view,0);
                                    rlv.setAdapter(adapter);
                                }
                                rlv.addOnItemTouchListener(new OnItemClickListener() {
                                    @Override
                                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(str.equals("康乐会员B卡")){
                                            HykDetailsActivity.jumpActivity(context,list.get(position),str);
                                        }if(str.equals("康乐会员A卡")){
                                            HykDetailsActivity.jumpActivity(context,list.get(position),str);
                                        }if(str.equals("百姓富人水")){
                                            FrsDetailsActivity.jumpActivity(context,list.get(position));
                                        }
                                    }
                                });
                                break;
                            default:
                                CommonUtils.showToast(context,check.getMsg());
                                break;
                        }
                    }
                });
    }

    /*获取健康包*/
    public static void getTc(final Context context,final RecyclerView rlv,final String str, final int img) {
        final ProgressHUD mProgressHub = CommonUtils.createProgressDialog(context, "正在加载中...", false);
        if (mProgressHub != null){
            mProgressHub.show();
        }
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.TC_URL))
                .addParams("json","")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mProgressHub.dismiss();
                        CommonUtils.showToast(context,R.string.TheNetIsException);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mProgressHub.dismiss();
                        Check check = new Gson().fromJson(response, Check.class);
                        switch (check.getErr()) {
                            case 0:
                                final PackageContent packageContent1 = new Gson().fromJson(response, PackageContent.class);
                                LayoutInflater mInflater =LayoutInflater.from(context);
                                View view = mInflater.inflate(R.layout.common_hyk_head, null);
                                TextView tv = (TextView) view.findViewById(R.id.tv);
                                tv.setText(str);
                                Drawable drawable = context.getResources().getDrawable(img);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
                                tv.setCompoundDrawables(drawable, null, null, null);
                                final List<PackageContent.DataBean>list = new ArrayList<PackageContent.DataBean>();
                                list.addAll(packageContent1.getData());
                                TcMainAdapter adapter = new TcMainAdapter(R.layout.item_package,list);
                                adapter.setHeaderView(view,0);
                                rlv.setAdapter(adapter);
                                rlv.addOnItemTouchListener(new OnItemClickListener() {
                                    @Override
                                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        Intent intent = new Intent();
                                        intent.putExtra("package", packageContent1.getData().get(position));
                                        intent.setClass(context, TcDetailsActivity.class);
                                        context.startActivity(intent);
                                    }
                                });
                                break;
                            default:
                                CommonUtils.showToast(context,check.getMsg());
                                break;
                        }
                    }
                });
    }
    /*分享*/
    public static void  shareToWeiXin(final Context context, Activity activity, SHARE_MEDIA share_media, String data){
        UMImage image = new UMImage(context, WebUtils.FX_URL_IMG);
        UMWeb web = new UMWeb(WebUtils.SHARE_URL + data);
        web.setThumb(image);
        web.setTitle("领取成功后下载app即可使用");
        new ShareAction(activity)
                .setPlatform(share_media)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        CommonUtils.showToast(context,"分享成功");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        CommonUtils.showToast(context,"分享失败");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        CommonUtils.showToast(context,"取消分享");
                    }
                })
                .share();
    }
    /*客户端首页下面的东西*/
    public static void getShoppingList(final Context context,final RecyclerView rlv,final String str, final int img,String ordertype
                 ,String matclass1,String matclass2){
        final ProgressHUD mProgressHub = CommonUtils.createProgressDialog(context, "正在加载中...", false);
        if (mProgressHub != null){
            mProgressHub.show();
        }
        JSONObject obj = new JSONObject();
        try {
            obj.put("key", "");
            obj.put("ordertype", ordertype);
            obj.put("matclass1", matclass1);
            obj.put("matclass2", matclass2);
            obj.put("matname", "");
            obj.put("startrow", 1);
            obj.put("endrow", 99);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.SP_DETAIL_URL))
                .addParams("json",String.valueOf(obj))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mProgressHub.dismiss();
                        CommonUtils.showToast(context,R.string.TheNetIsException);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mProgressHub.dismiss();
                        Check check = new Gson().fromJson(response, Check.class);
                        switch (check.getErr()) {
                            case 0:
                                LayoutInflater mInflater =LayoutInflater.from(context);
                                View view = mInflater.inflate(R.layout.common_hyk_head, null);
                                TextView tv = (TextView) view.findViewById(R.id.tv);
                                tv.setText(str);
                                Drawable drawable = context.getResources().getDrawable(img);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
                                tv.setCompoundDrawables(drawable, null, null, null);
                                final FootZqShop footZqShop = new Gson().fromJson(response, FootZqShop.class);
                                final List<FootZqShop.DataBean>list = new ArrayList<FootZqShop.DataBean>();
                                list.addAll(footZqShop.getData());
                                 if(str.equals("营养吧")){
                                    YybAdapter adapter = new YybAdapter(R.layout.common_grid_item,list);
                                    adapter.setHeaderView(view,0);
                                    rlv.setAdapter(adapter);
                                }else{
                                    SpListAdapter adapter = new SpListAdapter(R.layout.item_shopping,list);
                                    adapter.setHeaderView(view,0);
                                    rlv.setAdapter(adapter);
                                }
                                rlv.addOnItemTouchListener(new OnItemClickListener() {
                                    @Override
                                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        if(str.equals("营养吧")){
                                            YybActivity.jumpActivity(context,list.get(position));
                                        }else{
                                            Intent intent = new Intent();
                                            intent.putExtra("type","");
                                            intent.putExtra("ordertype",Constant.ORDERTYPE_CMRG);
                                            intent.putExtra("matid", list.get(position).getMatid());
                                            intent.setClass(context, CommonShoppingDetailActivity.class);
                                            context.startActivity(intent);
                                        }
                                    }
                                });
                                break;
                            default:
                                CommonUtils.showToast(context,check.getMsg());
                                break;
                        }
                    }
                });
    }
    /*获取文件格式*/
    public static String getType(String s){
        String[] ss = s.split("\\.");//表示用.去切割字符串
        return "."+ss[ss.length-1];//用.连接最后一个字符串
    }
    /*保留小数点后两位*/
    public static String douFormat(String s){
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(Double.parseDouble(s));
    }
    public static boolean isDateFormat(String d){
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateFormat.parse(d);
            return true;
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }
    }
}
