package com.ayj.chunmiao.activity.myinformation.anquan;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.myinformation.XgPassWordActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.passwordinputdialog.PassWordDialog;
import com.ayj.chunmiao.view.passwordinputdialog.impl.DialogCompleteListener;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class ZhangHaoAnQuanActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_changepwd)
    RelativeLayout rlChangepwd;
    @BindView(R.id.rl_setpaypwd)
    RelativeLayout rlSetpaypwd;
    @BindView(R.id.rl_changepaypwd)
    RelativeLayout rlChangepaypwd;
    private PassWordDialog passWordDialog;
    private String payPassWord = null;
    boolean same = false;


    @Override
    public int getLayoutId() {
        return R.layout.activity_zhang_hao_an_quan;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.rl_changepwd, R.id.rl_setpaypwd, R.id.rl_changepaypwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_changepwd:
                startActivity(new Intent(mContext, XgPassWordActivity.class));
                break;
            case R.id.rl_setpaypwd:
                showPwdDialog("输入支付密码");
                break;
            case R.id.rl_changepaypwd:
                showChooseDialog();
                break;
        }
    }

    private void showChooseDialog() {
        CommonUtils.getConfirmDialog(mContext, "", "是否记得旧密码", "还记得", "忘记了", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(mContext,ChangePayPwdActivity.class);
                        intent.putExtra("type","updata");
                        startActivity(intent);
                        sweetAlertDialog.dismiss();
                    }
                },
                new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(mContext,ChangePayPwdActivity.class);
                        intent.putExtra("type","reset");
                        startActivity(intent);
                        sweetAlertDialog.dismiss();
                    }
                }).show();

    }

    private void showPwdDialog(String title) {
        passWordDialog = new PassWordDialog(mContext);
        passWordDialog.setTitle(title);
        passWordDialog.setCompleteListener(new DialogCompleteListener() {
            @Override
            public void dialogCompleteListener(String money, String pwd) {
                if (payPassWord == null){
                    payPassWord = pwd;
                    showPwdDialog("再次输入");
                }else if(payPassWord.equals(pwd)){
                    payPassWord = null;
                    setPayPwd(pwd);
                }else{
                    payPassWord = null;
                    showToast("两次输入不一致,设置失败");
                }

            }
        });
        passWordDialog.show();
    }

    private void setPayPwd(String pwd) {
       if (CommonUtils.isNetworkAvailable(mContext)){
           mProgressHub.show();
           JSONObject object = new JSONObject();
           try{
               object.put("key","");
               object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
               object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
               object.put("paypwd", MD5Utils.getMD5String(pwd));
           }catch (JSONException e ){
               e.printStackTrace();
           }
           OkHttpUtils.post()
                   .url(WebUtils.getRequestUrl(WebUtils.SET_SHOP_PAY_PWD))
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
                                   break;
                               default:
                                   showToast(check.getMsg());
                                   break;
                           }
                       }
                   });
       }else{
           showToast(R.string.TheNetIsUnAble);
       }
    }
}
