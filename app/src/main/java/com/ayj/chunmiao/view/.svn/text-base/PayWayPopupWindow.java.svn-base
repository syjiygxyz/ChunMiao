package com.ayj.chunmiao.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.UtilityItem;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by zht-pc-04 on 2017/8/18.
 */

public class PayWayPopupWindow extends PopupWindow {

    private Context mContext;
    private View view;

    public PayWayPopupWindow(Context context, String title,String myPayWay,String money, List<UtilityItem> itemList, OnItemClickListener itemClickListener,View.OnClickListener onClickListener , int column){
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.pyq_sheet_layout,null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_sheet_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,column));
        recyclerView.setAdapter(new CommonGridAdapter(R.layout.cmbz_item,itemList));
        recyclerView.addOnItemTouchListener(itemClickListener);
        LinearLayout llMoneyShow = (LinearLayout) view.findViewById(R.id.ll_money_show);
        llMoneyShow.setVisibility(View.VISIBLE);
        TextView sheetTitle = (TextView) view.findViewById(R.id.my_sheet_title);
        TextView tvMoney = (TextView) view.findViewById(R.id.my_sheet_money);
        TextView myPay = (TextView)view.findViewById(R.id.my_sheet_pay);
        RelativeLayout rlJink = (RelativeLayout) view.findViewById(R.id.rl_jink);
        if (null != onClickListener){
            rlJink.setOnClickListener(onClickListener);
            rlJink.setVisibility(View.VISIBLE);
        }
        myPay.setText(myPayWay);
        tvMoney.setText("￥"+money);
        sheetTitle.setText(title);

        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.popwin_anim_style);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height =
                        view.findViewById(R.id.my_sheet_bottom_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        // 点击弹出框外面自动消失
                        PayWayPopupWindow.this.setOutsideTouchable(true);
                        // 自动获取焦点,否则EditText将无法获取焦点
                        PayWayPopupWindow.this.setFocusable(true);
                        dismiss();
                    }
                }
                return true;
            }
        });
        view.findViewById(R.id.my_sheet_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayWayPopupWindow.this.dismiss();
            }
        });
    }
    }
