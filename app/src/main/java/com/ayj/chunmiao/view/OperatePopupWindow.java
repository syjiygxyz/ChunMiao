package com.ayj.chunmiao.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.main.MySheetAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.txl.MyTxlPopAdapter;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.YyQdBean;
import java.util.List;

/**
 * Created by zht-pc-09 on 2017/6/23.
 */
public class OperatePopupWindow extends PopupWindow {

    private Context mContext;

    private View mMenuView;

    /*工具及界面通用popupwindow*/
    public OperatePopupWindow(Context context, String title, List<UtilityItem> itemList,
            OnItemClickListener itemClickListener) {
        mContext = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        mMenuView = inflater.inflate(R.layout.pyq_sheet_layout, null);
        RecyclerView recyclerList = (RecyclerView) mMenuView.findViewById(R.id.my_sheet_recycler);
        recyclerList.setLayoutManager(new LinearLayoutManager(context));
        MyTxlPopAdapter adapter = new MyTxlPopAdapter(R.layout.my_sheet_recycler_item, itemList);
        recyclerList.setAdapter(adapter);
        recyclerList.addOnItemTouchListener(itemClickListener);

        ((TextView) mMenuView.findViewById(R.id.my_sheet_title)).setText(title);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
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
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height =
                        mMenuView.findViewById(R.id.my_sheet_bottom_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        // 点击弹出框外面自动消失
                        OperatePopupWindow.this.setOutsideTouchable(true);
                        // 自动获取焦点,否则EditText将无法获取焦点
                        OperatePopupWindow.this.setFocusable(true);
                        dismiss();
                    }
                }
                return true;
            }
        });
        mMenuView.findViewById(R.id.my_sheet_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OperatePopupWindow.this.dismiss();
            }
        });
    }
}