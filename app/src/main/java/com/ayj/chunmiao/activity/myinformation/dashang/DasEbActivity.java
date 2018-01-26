package com.ayj.chunmiao.activity.myinformation.dashang;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.common.CustomActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.fragment.myinfo.DasEbHisFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by zht-pc-04 on 2017/8/31.
 */

public class DasEbActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_eb_num)
    EditText etEbNum;
    @BindView(R.id.et_message)
    EditText etMessage;
    @BindView(R.id.btn_shang)
    Button btnShang;

    @Override
    public int getLayoutId() {
        return R.layout.activity_das_eb;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("打赏爱e币");
        etEbNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())){
                    btnShang.setBackgroundResource(R.mipmap.shang_icon2);
                    btnShang.setEnabled(true);
                }else{
                    btnShang.setEnabled(false);
                    btnShang.setBackgroundResource(R.mipmap.shang_icon);
                }
            }
        });
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.btn_shang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_shang:
                new ShareAction(this)
                        .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .addButton("umeng_sharebutton_custom","umeng_sharebutton_custom","kehuquan_icon","kehuquan_icon")
                        .setShareboardclickCallback(shareBoardlistener)
                        .open();
                break;
        }
    }
    private ShareBoardlistener shareBoardlistener = new  ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            if (share_media==null){
                //根据key来区分自定义按钮的类型，并进行对应的操作
                if (snsPlatform.mKeyword.equals("umeng_sharebutton_custom")){
                    Intent intent = new Intent(mContext, CustomActivity.class);
                    intent.putExtra("type","13");
                    startActivityForResult(intent,1);
                }

            }
            else {//社交平台的分享行为
                shareToMember(null,share_media);
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && null != data){
            //showToast(data.getStringExtra("mobile"));
            shareToMember(data.getStringExtra("mobile"),null);
        }

        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void shareToMember(final String mobile,final SHARE_MEDIA share_media) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try {
                object.put("key","");
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("tomembermobile",mobile);
                object.put("awardtype","SHOPAWARDTYPE001");
                object.put("awardnum",etEbNum.getText().toString().trim());
                object.put("formessage",etMessage.getText().toString().trim() == null ? "爱生活,惠生活":etMessage.getText().toString().trim());
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_AWARD))
                    .addParams("json",String.valueOf(object))
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
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0 :
                                    if (share_media != null && mobile == null) {
                                       CommonUtils.shareToWeiXin(mContext,DasEbActivity.this,share_media,check.getData().toString());
                                    }
                                    finish();
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        }else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
