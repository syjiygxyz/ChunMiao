package com.ayj.chunmiao.activity.cmbz.insurance.bjfk;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.InsurancePlaceOrderActivity;
import com.ayj.chunmiao.adapter.cmbz.bx.InsurancePriceListAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.InsurancePriceBean;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/24.
 * 报价明细
 */
public class InsurancePriceActivity  extends BaseActivity {

    InsurancePriceBean.DataBean dataBean;
    List<InsurancePriceBean.DataBean.OrderDetailListBean> orderdetaillist = new ArrayList<>();

    private String type, snid, status;
    private double sum; //总价
    private boolean isQuotedPrice;//是否为已报价状态
    InsurancePriceListAdapter adapter;


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_syxzj)
    TextView tvSyxzj;
    @BindView(R.id.rlv_price_list)
    RecyclerView rlvPriceList;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Dialog dialog;
    Button dialogConfirm,dialogCancle;
    TextView dialogText;

    @BindView(R.id.iv)
    ImageView iv;



    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_price;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("报价明细");
        getIntentData();
        rlvPriceList.setHasFixedSize(true);
        rlvPriceList.setLayoutManager(new LinearLayoutManager(mContext));
    }
    /*显示确认提示框*/
    private void showConfirmDialog() {
        dialog = new Dialog(mContext, R.style.base_dialog);
        dialog.setContentView(R.layout.dialog_yes_no);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(true);
        dialogConfirm = (Button) dialog.findViewById(R.id.confirm_button);
        dialogCancle = (Button) dialog.findViewById(R.id.cancel_button);
        dialogText = (TextView) dialog.findViewById(R.id.content_text);
        dialogText.setText(isQuotedPrice ? "是否提交订单" : "是否立即支付");
        dialogCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmOrder();
            }
        });
        dialog.show();
    }

    private void confirmOrder() {
        dialog.dismiss();
        if(isQuotedPrice){
            if(CommonUtils.isNetworkAvailable(mContext)){
                final UserPerson userPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
                String userPersonPwd = userPerson.getData().get(0).getPassWord();
                final String userId = userPerson.getData().get(0).getSnid();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("key", "");
                    obj.put("msnid", userId);
                    obj.put("pwd", userPersonPwd);
                    obj.put("insorderid",snid);
                    obj.put("insurancebrand",type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mProgressHub.show();
                OkHttpUtils.post()
                        .url(WebUtils.getRequestUrl(WebUtils.CONFIRM_CXDD_URL))
                        .addParams("json",String.valueOf(obj))
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
                                        Intent intent = new Intent(mContext,InsurancePlaceOrderActivity.class);
                                        intent.putExtra("total", sum + "");
                                        intent.putExtra("dataBean",(Serializable)dataBean);
                                        startActivity(intent);
                                        break;
                                    default:
                                        showToast(check.getMsg());
                                }

                            }
                        });
            }else {
                showToast(R.string.TheNetIsUnAble);
            }
        }else{
            Intent intent = new Intent(mContext,InsurancePlaceOrderActivity.class);
            intent.putExtra("total",sum+"");
            intent.putExtra("dataBean",(Serializable)dataBean);
            startActivity(intent);
        }

    }

    private void getIntentData() {
        dataBean = (InsurancePriceBean.DataBean)getIntent().getSerializableExtra("dataBean");
        orderdetaillist = dataBean.getOrderDetailList();
        type = orderdetaillist.get(0).getInsurancebrand();
        snid = dataBean.getSnid();
        status = dataBean.getStatus();
        isQuotedPrice = status.equals(status.equals(Constant.QUOTED_PRICE));
        for (InsurancePriceBean.DataBean.OrderDetailListBean order : orderdetaillist) {
            sum += Double.parseDouble(order.getPrice());
        }
        tv_name.setText(dataBean.getOrderDetailList().get(0).getInsurancebrandshow());
        if(dataBean.getOrderDetailList().get(0).getInsurancebrandshow().equals("中国人保")){
            iv.setImageResource(R.mipmap.bjxt_picc);
        }else if(dataBean.getOrderDetailList().get(0).getInsurancebrandshow().equals("永安保险")){
            iv.setImageResource(R.mipmap.bjxt_yongan);
        }else if(dataBean.getOrderDetailList().get(0).getInsurancebrandshow().equals("太平洋保险")){
            iv.setImageResource(R.mipmap.bjxt_cpic);
        }else if(dataBean.getOrderDetailList().get(0).getInsurancebrandshow().equals("天安保险")){
            iv.setImageResource(R.mipmap.bjxt_tian);
        }
        tvSyxzj.setText(dataBean.getStatusshow());
    }

    @Override
    public void configViews() {
        adapter = new InsurancePriceListAdapter(orderdetaillist);
        rlvPriceList.setAdapter(adapter);
    }


    @OnClick({R.id.iv_back, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_confirm:
                showConfirmDialog();
                break;
        }
    }
}

