package com.ayj.chunmiao.activity.cg;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cg.hc.HcCgActivity;
import com.ayj.chunmiao.activity.cg.xhj.XhjCgActivity;

import com.ayj.chunmiao.activity.kc.jb.JbActivity;
import com.ayj.chunmiao.activity.kc.jf.JfActivity;
import com.ayj.chunmiao.activity.kc.cxj.CxjActivity;
import com.ayj.chunmiao.activity.kc.hc.HcActivity;
import com.ayj.chunmiao.activity.kc.lx.LxActivity;
import com.ayj.chunmiao.activity.kc.xhj.KcXhjActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 1采购和2库存通用界面
* */
public class CgActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;

    List<UtilityItem> list = new ArrayList<>();

    CommonGridAdapter mCommonGridAdapter;

    String type;
    Dialog dialog;

    public static void jumpActivity(Context context, String type){
        Intent intent = new Intent(context,CgActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cg;
    }

    @Override
    public void initDatas() {
        type = getIntent().getStringExtra("type");
        list = UtilityItem.getCgList();
        if("1".equals(type)){
            mTvTitle.setText("采购");
        }else if("2".equals(type)){
            mTvTitle.setText("库存");
            list.add(new UtilityItem("金币", R.mipmap.cg_6));
            list.add(new UtilityItem("联销", R.mipmap.cg_2));
        }

        mRlv.setHasFixedSize(true);
        mRlv.setLayoutManager(new GridLayoutManager(mContext, 3));

    }

    @Override
    public void configViews() {
        mCommonGridAdapter = new CommonGridAdapter(R.layout.cmbz_item, list);
        mRlv.setAdapter(mCommonGridAdapter);
        mRlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0://现货架
                        if("1".equals(type)){
                            startActivity(new Intent(mContext, XhjCgActivity.class));
                        }else if("2".equals(type)){
                            startActivity(new Intent(mContext, KcXhjActivity.class));
                        }
                        break;
                    case 1://耗材
                        if("1".equals(type)){
                            startActivity(new Intent(mContext, HcCgActivity.class));
                        }else if("2".equals(type)){
                            startActivity(new Intent(mContext, HcActivity.class));
                        }
                        break;
                    case 2://促销券
                        startActivity(new Intent(mContext, CxjActivity.class));
                        break;
                    case 3://积分
                        if("1".equals(type)){
                            showInputDilog();
                        }else if("2".equals(type)){
                            startActivity(new Intent(mContext, JfActivity.class));
                        }
                        break;
                    case 4://金币
                        startActivity(new Intent(mContext,JbActivity.class));
                        break;
                    case 5://联销
                        startActivity(new Intent(mContext, LxActivity.class));
                        break;
                }
            }
        });
    }

    private void showInputDilog() {
        dialog = new Dialog(mContext,R.style.base_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.sb_yz_passw_dialog,null);
        dialog.setContentView(dialogView);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(true);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.title_text);
        TextView tvContent = (TextView) dialog.findViewById(R.id.content_text);
        final EditText et = (EditText) dialog.findViewById(R.id.et);
        Button cancelButton = (Button)dialog.findViewById(R.id.cancel_button);
        Button confirmButton = (Button) dialog.findViewById(R.id.confirm_button);
        tvTitle.setText("积分");
        tvContent.setText("请输入要采购的积分数量");
        et.setHint("");
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et.getText().toString().trim())){
                   /* Intent intent = new Intent(mContext, JfCzActivity.class);
                    intent.putExtra("num",et.getText().toString().trim());
                    dialog.dismiss();*/
                    setShopOrder(et.getText().toString().trim());
                }else {
                    showToast("请输入要购买的数量");
                }

            }
        });
        dialog.show();
    }

    private void setShopOrder(final String num) {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                object.put("pwd",AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getPassWord());
                object.put("ordertype","MEMBERORDERTYPE026");
                object.put("totalnum",num);
            }catch (JSONException e){
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.JF_ORDER))
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
                                case 0:
                                     Intent intent = new Intent(mContext, JfCzActivity.class);
                                    intent.putExtra("num",num);
                                    intent.putExtra("orderid",check.getData().toString());
                                    startActivity(intent);
                                    dialog.dismiss();
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


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
