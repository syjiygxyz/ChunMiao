package com.ayj.chunmiao.activity.ykfx;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.ykfx.YkfxMainBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.CustomPopWindow;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 盈亏分析
* */
public class YkfxMainActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_head)
    TextView tvRightHead;
    @BindView(R.id.tv_date)
    TextView mTvDate;//时间控件
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.rl_3)
    RelativeLayout rl3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.rl_4)
    RelativeLayout rl4;

    boolean[] isHaveDay;

    private CustomPopWindow mCustomPopWindow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ykfx_main;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("盈亏分析");
        tvRightHead.setVisibility(View.VISIBLE);
        tvRightHead.setText("日报");
        mTvDate.setText(CommonUtils.getTime(new Date(System.currentTimeMillis()), "yyyy-MM-dd"));
        getList();
    }

    @Override
    public void configViews() {

    }

    private void getList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                if ("日报".equals(tvRightHead.getText().toString())) {
                    obj.put("etime", mTvDate.getText().toString());
                    obj.put("btime", mTvDate.getText().toString());
                } else if ("月报".equals(tvRightHead.getText().toString())) {
                    obj.put("etime", CommonUtils.getLastDayOfMonth(Integer.parseInt(mTvDate.getText().toString().split("-")[0]), Integer.parseInt(mTvDate.getText().toString().split("-")[1])));
                    obj.put("btime", mTvDate.getText().toString() + "-01");
                } else if ("年报".equals(tvRightHead.getText().toString())) {
                    obj.put("etime",mTvDate.getText().toString()+"-12" + "-31");
                    obj.put("btime", mTvDate.getText().toString()+"-01" + "-01");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.YKFX_URL))
                    .addParams("json", String.valueOf(obj))
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
                                    YkfxMainBean ykfxMainBean = new Gson().fromJson(response, YkfxMainBean.class);
                                    if(null==ykfxMainBean.getData().get(0).getTotalsalemoney()){
                                        tv1.setText("¥"+0.00);
                                    }else{
                                        tv1.setText("¥"+ykfxMainBean.getData().get(0).getTotalsalemoney());
                                    }
                                    if(null==ykfxMainBean.getData().get(0).getTotalpayout()){
                                        tv2.setText("¥"+0.00);
                                    }else{
                                        tv2.setText("¥"+ykfxMainBean.getData().get(0).getTotalpayout());
                                    }
                                    if(null==ykfxMainBean.getData().get(0).getTotaltc()){
                                        tv3.setText("¥"+0.00);
                                    }else{
                                        tv3.setText("¥"+ykfxMainBean.getData().get(0).getTotaltc());
                                    }
                                    if(null==ykfxMainBean.getData().get(0).getTotalprofit()){
                                        tv4.setText("¥"+0.00);
                                    }else{
                                        tv4.setText("¥"+ykfxMainBean.getData().get(0).getTotalprofit());
                                    }
                                    break;
                                default:
                                    showToast(check.getMsg());
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @OnClick({R.id.iv_back, R.id.rl, R.id.rl_1, R.id.rl_2, R.id.rl_3, R.id.rl_4, R.id.tv_right_head})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.rl:
                /*调节时间*/
                if ("日报".equals(tvRightHead.getText().toString())) {
                    showDayPickView("yyyy-MM-dd");
                } else if ("月报".equals(tvRightHead.getText().toString())) {
                    showDayPickView("yyyy-MM");
                } else if ("年报".equals(tvRightHead.getText().toString())) {
                    showDayPickView("yyyy");
                }
                break;
            case R.id.tv_right_head:
                /**
                 * 显示PopupWindow 同时背景变暗
                 */
                View contentView = LayoutInflater.from(this).inflate(R.layout.pop_menu, null);
                //处理popWindow 显示内容
                handleLogic(contentView);
                //创建并显示popWindow
                mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .setAnimationStyle(R.style.CustomPopWindowStyle)
                        .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                            }
                        })
                        .create()
                        .showAsDropDown(tvRightHead, 0, 20);
                break;
            case R.id.rl_1:
                //销售额
                if("日报".equals(tvRightHead.getText().toString())){
                    YkfxDetailActivity.jumpActivity(mContext,"总销售额明细","实付金额",WebUtils.YKFX_XE_DETAIL,mTvDate.getText().toString(),mTvDate.getText().toString());
                }else if ("月报".equals(tvRightHead.getText().toString())){
                    YkfxDetailActivity.jumpActivity(mContext,"总销售额明细","实付金额",WebUtils.YKFX_XE_DETAIL,mTvDate.getText().toString() + "-01",CommonUtils.getLastDayOfMonth(Integer.parseInt(mTvDate.getText().toString().split("-")[0]), Integer.parseInt(mTvDate.getText().toString().split("-")[1])));
                }else if ("年报".equals(tvRightHead.getText().toString())) {
                    YkfxDetailActivity.jumpActivity(mContext,"总销售额明细","实付金额",WebUtils.YKFX_XE_DETAIL,mTvDate.getText().toString()+"-01" + "-01",mTvDate.getText().toString()+"-12" + "-31");
                }
                break;
            case R.id.rl_2:
                //成本
                if("日报".equals(tvRightHead.getText().toString())){
                    YkfxDetailActivity.jumpActivity(mContext,"总成本明细","成本",WebUtils.YKFX_KZ_DETAIL,mTvDate.getText().toString(),mTvDate.getText().toString());
                }else if ("月报".equals(tvRightHead.getText().toString())){
                    YkfxDetailActivity.jumpActivity(mContext,"总成本明细","成本",WebUtils.YKFX_KZ_DETAIL,mTvDate.getText().toString() + "-01",CommonUtils.getLastDayOfMonth(Integer.parseInt(mTvDate.getText().toString().split("-")[0]), Integer.parseInt(mTvDate.getText().toString().split("-")[1])));
                }else if ("年报".equals(tvRightHead.getText().toString())) {
                    YkfxDetailActivity.jumpActivity(mContext,"总成本明细","成本",WebUtils.YKFX_KZ_DETAIL,mTvDate.getText().toString()+"-01" + "-01",mTvDate.getText().toString()+"-12" + "-31");
                }
                break;
            case R.id.rl_3:
                //提成
                if("日报".equals(tvRightHead.getText().toString())){
                    YkfxDetailActivity.jumpActivity(mContext,"总提成明细","提成",WebUtils.YKFX_TC_DETAIL,mTvDate.getText().toString(),mTvDate.getText().toString());
                }else if ("月报".equals(tvRightHead.getText().toString())){
                    YkfxDetailActivity.jumpActivity(mContext,"总提成明细","提成",WebUtils.YKFX_TC_DETAIL,mTvDate.getText().toString() + "-01",CommonUtils.getLastDayOfMonth(Integer.parseInt(mTvDate.getText().toString().split("-")[0]), Integer.parseInt(mTvDate.getText().toString().split("-")[1])));
                }else if ("年报".equals(tvRightHead.getText().toString())) {
                    YkfxDetailActivity.jumpActivity(mContext,"总提成明细","提成",WebUtils.YKFX_TC_DETAIL,mTvDate.getText().toString()+"-01" + "-01",mTvDate.getText().toString()+"-12" + "-31");
                }
                break;
            case R.id.rl_4:
                //利润
                showToast("敬请期待");
                break;
        }
    }

    /*显示年月日*/
    private void showDayPickView(final String dateStyle) {
        if ("日报".equals(tvRightHead.getText().toString())) {
            /*年月*/
            isHaveDay = new boolean[]{true, true, true, false, false, false};
        } else if ("月报".equals(tvRightHead.getText().toString())) {
            /*年月日*/
            isHaveDay = new boolean[]{true, true, false, false, false, false};
        } else if ("年报".equals(tvRightHead.getText().toString())) {
            /*年月日*/
            isHaveDay = new boolean[]{true, false, false, false, false, false};
        }
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH));
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvDate.setText(CommonUtils.getTime(date, dateStyle));
                getList();
            }
        }).setType(isHaveDay).setCancelText("取消").setDate(CommonUtils.StringToCalendar(mTvDate.getText().toString(), dateStyle)).setRangDate(startDate, endDate).build();
        ;//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                switch (v.getId()){
                    case R.id.tv_day:
                        tvRightHead.setText("日报");
                        mTvDate.setText(CommonUtils.getTime(new Date(System.currentTimeMillis()),"yyyy-MM-dd"));
                        break;
                    case R.id.tv_month:
                        tvRightHead.setText("月报");
                        mTvDate.setText(CommonUtils.getTime(new Date(System.currentTimeMillis()),"yyyy-MM"));
                        break;
                    case R.id.tv_year:
                        tvRightHead.setText("年报");
                        mTvDate.setText(CommonUtils.getTime(new Date(System.currentTimeMillis()),"yyyy"));
                        break;
                }

            }
        };
        contentView.findViewById(R.id.tv_day).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_month).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_year).setOnClickListener(listener);
    }

}
