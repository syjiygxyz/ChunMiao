package com.ayj.chunmiao;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.ayj.chunmiao.bean.UserBean;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zht-pc-09 on 2017/6/21.
 */
public class AyjSwApplication extends Application {

    public static AyjSwApplication app;//当前application上下文对象


    /**
     * 影响网络请求的链接地址与日志打印
     */
    public static final boolean IS_DEBUG = false;
    /**
     * 缓存类
     */
    private ACache aCache;

    /**
     * 用户信息实体
     */
    private UserBean userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        init();
    }

    private void init() {
        //网络请求
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        PlatformConfig.setWeixin("wx17e0ee25e2f0f31e","ecb8deba92916c28a2c1c425f425677c");
        //初始化缓存帮助类
        aCache = CommonUtils.getAcache();
     //   initUserInfo();
        UMShareAPI.get(this);
    }

    /**
     * 获取当前上下文对象
     *
     * @return
     */
    public static AyjSwApplication getContext() {
        return app;
    }

    /**
     * 初始化获取用户信息
     */
    private void initUserInfo() {
        userInfo = (UserBean) aCache.getAsObject(ACache.USER_INFO_KEY);
    }

    public UserBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserBean userInfo) {
        this.userInfo = userInfo;
    }

    public static AyjSwApplication getsInstance() {
        return app;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersionName() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

