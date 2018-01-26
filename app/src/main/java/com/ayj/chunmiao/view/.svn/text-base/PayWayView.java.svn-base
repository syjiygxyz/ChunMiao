package com.ayj.chunmiao.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;

/**
 * Created by zht-pc-04 on 2017/8/17.
 */

public class PayWayView extends RelativeLayout implements View.OnClickListener {

    private Context mContext;
    private CheckBox cbXjk,cbZfb,cbWx;
    private Button confirmBtn;
    private TextView tvTotalMoney;
    public static int ZHIFUBAO = 1;
    public static int WEIXIN = 2;
    public static int XIAOJINKU = 3;
    private int payWay = 3;

    PayWayOnclickListener payWayOnClickListener;

    public PayWayView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }


    public PayWayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }
    public void setTotalMoney(String tm){
        tvTotalMoney.setText(tm);
    }
    private void initView() {
        View view = View.inflate(mContext, R.layout.pay_way_view,null);
        cbWx = (CheckBox)view.findViewById(R.id.cb_wx);
        cbXjk = (CheckBox)view.findViewById(R.id.cb_xjk);
        cbZfb = (CheckBox)view.findViewById(R.id.cb_zfb);
        tvTotalMoney = (TextView)view.findViewById(R.id.tv_totalmoney);
        confirmBtn = (Button)view.findViewById(R.id.confirm_button);
        cbXjk.setChecked(true);
        addView(view);
        cbZfb.setOnClickListener(this);
        cbWx.setOnClickListener(this);
        cbXjk.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cb_xjk:
                cbXjk.setChecked(true);
                cbZfb.setChecked(false);
                cbWx.setChecked(false);
                payWay = XIAOJINKU;
                break;
            case R.id.cb_zfb:
                cbXjk.setChecked(false);
                cbZfb.setChecked(true);
                cbWx.setChecked(false);
                payWay = ZHIFUBAO;
                break;
            case R.id.cb_wx:
                cbXjk.setChecked(false);
                cbZfb.setChecked(false);
                cbWx.setChecked(true);
                payWay = WEIXIN;
                break;
            case R.id.confirm_button:
                payWayOnClickListener.payWayOnclickListener(payWay);
                break;
        }
    }
    public void setPayWayOnclickListener(PayWayOnclickListener payWayOnClickListener){
        this.payWayOnClickListener = payWayOnClickListener;
    }
    public interface PayWayOnclickListener{
        void payWayOnclickListener(int payWay);
    }
}
