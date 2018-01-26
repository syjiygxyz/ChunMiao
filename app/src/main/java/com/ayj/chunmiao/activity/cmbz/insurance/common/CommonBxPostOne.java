package com.ayj.chunmiao.activity.cmbz.insurance.common;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.bx.BzfwBean;
import com.ayj.chunmiao.bean.bx.CommonBxListBean;
import com.ayj.chunmiao.view.TimePicker1.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/*
* 保险提交第一步
* */
public class CommonBxPostOne extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_deadline)
    TextView tvDeadline;
    @BindView(R.id.tv_selecttime)
    TextView tvSelecttime;
    @BindView(R.id.tv_endtime)
    TextView tvEndtime;
    @BindView(R.id.tv_ljtb1_price)
    TextView tvLjtb1Price;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.tv1)
    TextView tv1;

    String deadline;

    BzfwBean.DataBean item;

    private TimePickerView timePickerView;

    CommonBxListBean.DataBean list;

    public static void jumpActivity(CommonBxListBean.DataBean listBean, Context context, String money, BzfwBean.DataBean item, String time, String dw, String lx, String snid) {
        Intent mIntent = new Intent(context, CommonBxPostOne.class);
        mIntent.putExtra("info", item);
        mIntent.putExtra("listBean", listBean);
        mIntent.putExtra("money", money);
        mIntent.putExtra("time", time);
        mIntent.putExtra("dw", dw);
        mIntent.putExtra("snid", snid);
        mIntent.putExtra("lx", lx);
        context.startActivity(mIntent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_bx_post_one;
    }

    @Override
    public void initDatas() {
        item = (BzfwBean.DataBean) getIntent().getSerializableExtra("info");
        list = (CommonBxListBean.DataBean) getIntent().getSerializableExtra("listBean");
        tvTitle.setText("方案报价");
        deadline = getIntent().getStringExtra("time")+getIntent().getStringExtra("dw");
        tvDeadline.setText(deadline);
        tv1.setText(getIntent().getStringExtra("lx")+item.getEcomment());
        tvLjtb1Price.setText(getIntent().getStringExtra("money"));
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_selecttime, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_selecttime:
                timePickerView = new TimePickerView(mContext, TimePickerView.Type.YEAR_MONTH_DAY, 3);
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date) {
                        tvSelecttime.setText(new SimpleDateFormat("yyyy-MM-dd").format(date));
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cd = Calendar.getInstance();
                        cd.setTime(date);
                        deadline=deadline.trim();
                        String str="";
                        for(int i=0;i<deadline.length();i++){
                            //获取字符串中的数字
                            if(deadline.charAt(i)>=48 && deadline.charAt(i)<=57){
                                str+=deadline.charAt(i);
                            }
                        }
                        if (deadline.substring(deadline.length()-1).equals("月")) {
                            cd.add(Calendar.MONTH, Integer.parseInt(str));//增加月
                            Date date2 = cd.getTime();
                            tvEndtime.setText(sdf.format(date2));
                        }

                        if (deadline.substring(deadline.length()-1).equals("年")) {
                            cd.add(Calendar.YEAR, Integer.parseInt(str));//增加年
                            Date date2 = cd.getTime();
                            tvEndtime.setText(sdf.format(date2));
                        }

                        if (deadline.substring(deadline.length()-1).equals("天")) {
                            cd.add(Calendar.DAY_OF_MONTH, Integer.parseInt(str));//增加天
                            Date date2 = cd.getTime();
                            tvEndtime.setText(sdf.format(date2));
                        }

                        if (deadline.substring(deadline.length()-2).equals("季度")) {
                            cd.add(Calendar.MONTH, Integer.parseInt(str)*3);//增加季度
                            Date date2 = cd.getTime();
                            tvEndtime.setText(sdf.format(date2));
                        }
                    }
                });
                timePickerView.show();
                break;
            case R.id.tv_next:
                if(!TextUtils.isEmpty(tvSelecttime.getText().toString().toString())){
                    Intent intent = new Intent(mContext,BxLjtb2Activity.class);
                    intent.putExtra("btime", tvSelecttime.getText());
                    intent.putExtra("etime", tvEndtime.getText());
                    intent.putExtra("info",list);
                    intent.putExtra("ms",item.getEcomment());
                    intent.putExtra("money",getIntent().getStringExtra("money"));
                    intent.putExtra("snid",getIntent().getStringExtra("snid"));
                    startActivity(intent);
                }else{
                    showToast("请选择投保日期");
                }
                break;
        }
    }
}
