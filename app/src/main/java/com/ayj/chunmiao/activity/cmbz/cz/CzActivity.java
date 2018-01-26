package com.ayj.chunmiao.activity.cmbz.cz;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.hyk.HykZfActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.cz.CzAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.SmallmoneyFace;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 充值
* */
public class CzActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    @BindView(R.id.bt_zf)
    TextView mBtZf;
    CzAdapter czAdapter;

    List<SmallmoneyFace.DataBean>list = new ArrayList<>();

    SmallmoneyFace.DataBean item;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cz;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("充值");
        mRlv.setLayoutManager(new GridLayoutManager(mContext, 3));
        getSmallmoneyFace();
        mRlv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i <list.size() ; i++) {
                    list.get(i).setTrue(false);
                }
                list.get(position).setTrue(true);
                czAdapter.setNewData(list);
                czAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.bt_zf})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_zf:
                for (int i = 0; i <list.size() ; i++) {
                    if(list.get(i).getTrue()){
                        item = list.get(i);
                    }
                }
                if(null==item){
                    showToast("请选择充值金额");
                }else{
                    HykZfActivity.jumpActivity(mContext,"充值",item.getFacevalue(),
                            "", Constant.CZ_DH,"1","","","");
                }

                break;
        }
    }

    private void getSmallmoneyFace(){

        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.KHQ_CZ))
                .addParams("json","{\"key\":\"\"}")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showToast(R.string.TheNetIsException);
                    }
                    @Override
                    public void onResponse(String response, int id) {

                        Check check = new Gson().fromJson(response, Check.class);
                        switch (check.getErr()){
                            case 0:
                                SmallmoneyFace smallmoneyFace  = new Gson().fromJson(response,SmallmoneyFace.class);
                                list.addAll(smallmoneyFace.getData());
                                for (int i = 0; i <list.size() ; i++) {
                                    list.get(i).setTrue(false);
                                }
                                if(null==czAdapter){
                                    czAdapter = new CzAdapter(R.layout.cz_item,list);
                                    mRlv.setAdapter(czAdapter);
                                }else{
                                    czAdapter.setNewData(list);
                                }
                                break;
                            default:
                                break;
                        }

                    }
                });
    }
}
