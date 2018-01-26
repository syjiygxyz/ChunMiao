package com.ayj.chunmiao.activity.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.cmbz.yy.YyDetailsActivity;
import com.ayj.chunmiao.bean.eventbus.FirstEvent;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubView;
import com.ayj.chunmiao.view.NumberButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import im.delight.android.webview.AdvancedWebView;

/*
* 新闻界面,1为套餐2为约单
* */
public class NewsActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.wv_news)
    AdvancedWebView wv_news;

    private static NewsActivity newsAct;

    @BindView(R.id.linearLayout_service)
    LinearLayout linearLayout_service;

    private String position;
    private int[] nums;

    private NumberButton add_service;
    private Button btn_service;

    int yyCount;


    public static void jumpActivity(Context context, String url, String title, String type, String position) {
        Intent mIntent = new Intent(context, NewsActivity.class);
        mIntent.putExtra("url", url);
        mIntent.putExtra("title", title);
        mIntent.putExtra("type", type);
        mIntent.putExtra("position", position);
        context.startActivity(mIntent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initDatas() {
        initSettings();
        add_service = (NumberButton) findViewById(R.id.number_button);
        btn_service = (Button) findViewById(R.id.btn_service);
        mTvTitle.setText(getIntent().getStringExtra("title"));
        if ("2".equals(getIntent().getStringExtra("type"))) {
            linearLayout_service.setVisibility(View.VISIBLE);
        }
        registerListener();
    }

    @Override
    public void configViews() {
        EventBus.getDefault().register(this);
        wv_news.loadUrl(getIntent().getStringExtra("url"));
        wv_news.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }


    private void initSettings() {
        WebSettings ws = wv_news.getSettings();
        ws.setJavaScriptEnabled(true);// 设置支持js
        //  wv_news.addJavascriptInterface(new openApp(), "jsObj");
        ws.setDefaultTextEncodingName("utf-8");
        // 设置可以支持缩放
        ws.setSupportZoom(true);
        // 设置出现缩放工具
        ws.setBuiltInZoomControls(true);
        //设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
//        ws.setUseWideViewPort(true);
        //设置默认加载的可视范围是大视野范围
//        ws.setLoadWithOverviewMode(true);
        //自适应屏幕
//        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv_news.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!NewsActivity.this.isFinishing() && mProgressHub != null) {
                    mProgressHub.show();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.loadUrl("file:///android_asset/refresh/index.html");
            }
        });
        wv_news.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    mProgressHub.dismiss();
                }
            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*文件上传*/
        wv_news.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wv_news.loadData("<a></a>", "text/html", "utf-8");
        if (mProgressHub != null && mProgressHub.isShowing()) {
            mProgressHub.dismiss();
        }
        newsAct = null;
        if (wv_news != null) {
            wv_news = null;
        }
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (wv_news.canGoBack()) {
                wv_news.goBack();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
  * 获取实例
  * */
    public static NewsActivity getInstance() {
        return newsAct;
    }

    /*
    * 重新加载url
    * */
    public void loadUrl(String url) {
        if (wv_news != null)
            wv_news.loadUrl(url);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wv_news.onResume();
    }


    private void registerListener() {
        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, YyDetailsActivity.class);
                mIntent.putExtra("title", getIntent().getStringExtra("title"));
                mIntent.putExtra("getMatid", getIntent().getStringExtra("getMatid"));
                mIntent.putExtra("standardsaleprice", getIntent().getStringExtra("standardsaleprice"));
                mIntent.putExtra("num", add_service.getNumber());
                startActivity(mIntent);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(FirstEvent event) {
        if (event.getMsg().equals("YYDetail")) {
            finish();
        }
    }
}
