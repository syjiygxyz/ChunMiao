package com.ayj.chunmiao.activity.cmbz.insurance.bdcz;

import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.XyBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

/*
* 北斗车载
* */
public class BdczMainActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard jcVideo;
    @BindView(R.id.tv_js)
    TextView tvJs;
    @BindView(R.id.tv_xy)
    TextView tvXy;
    @BindView(R.id.tv_gm)
    TextView tvGm;
    @BindView(R.id.iv_js)
    ImageView ivJs;
    @BindView(R.id.tv_info)
    TextView tvInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_bdcz_main;
    }

    @Override
    public void initDatas() {
        getXy();
        jcVideo.setUp(WebUtils.BDY_MP4
                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
        jcVideo.thumbImageView.setImageResource(R.mipmap.bdy_img);
        ivJs.setImageResource(R.mipmap.spxqczy);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void configViews() {

    }


    private void getXy() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.XY_URL))
                    .addParams("json", "{\"key\":\"\",\"snid\":\"33\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    XyBean xyBean = new Gson().fromJson(response, XyBean.class);
                                    tvInfo.setText(Html.fromHtml(xyBean.getData().get(0).getAcomment()));
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_js, R.id.tv_xy, R.id.tv_gm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_js:
                tvJs.setBackgroundResource(R.drawable.cz_background_theme);
                tvJs.setTextColor(Color.parseColor("#ffffff"));
                tvXy.setBackgroundResource(R.drawable.cz_back_ground);
                tvXy.setTextColor(Color.parseColor("#2e5250"));
                ivJs.setVisibility(View.VISIBLE);
                tvInfo.setVisibility(View.GONE);
                break;
            case R.id.tv_xy:
                tvXy.setBackgroundResource(R.drawable.cz_background_theme);
                tvXy.setTextColor(Color.parseColor("#ffffff"));
                tvJs.setBackgroundResource(R.drawable.cz_back_ground);
                tvJs.setTextColor(Color.parseColor("#2e5250"));
                ivJs.setVisibility(View.GONE);
                tvInfo.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_gm:
                /*跳转购买界面*/
                startActivity(new Intent(mContext,CzyZfActivity.class));
                break;

        }
    }
}
