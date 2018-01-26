package com.ayj.chunmiao.fragment;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.sb.EquipmentGroupAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.EquipmentGroup;
import com.ayj.chunmiao.fragment.base.BaseFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2017/6/24.
 * 设备管理
 */
public class SbMangerFragment extends BaseFragment {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.lv_equipment_group)
    ListView lv_equipment_group;

    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcf_refresh;


    private List<EquipmentGroup.DataBean> mEquipmentGroups;
    private EquipmentGroupAdapter mEquipmentGroupAdapter;

    private android.os.Handler handler = new android.os.Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (CommonUtils.isNetworkAvailable(getActivity())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            getDict("1");
                        }
                    }).start();
                } else {
                    showToast(R.string.TheNetIsUnAble);
                }
            }
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sb_manger;
    }

    @Override
    public void initDatas() {

        mIvBack.setVisibility(View.GONE);
        mIvRight.setVisibility(View.GONE);
        mTvTitle.setText("我的工作组");
        pcf_refresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getDict("2");
            }
        });
    }

    @Override
    public void configViews() {
        getDict("2");
    }

    /*获取设备一级目录*/
    private void getDict(final String type) {
        mEquipmentGroups = new ArrayList<>();
        mEquipmentGroupAdapter = new EquipmentGroupAdapter(getActivity());
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(type.equals("2")){
                mProgressHub.show();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SB_GL_URL))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            if(type.equals("2")){
                                mProgressHub.dismiss();
                                pcf_refresh.onRefreshComplete();
                                showToast(R.string.TheNetIsException);
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if(type.equals("2")){
                                mProgressHub.dismiss();
                                pcf_refresh.onRefreshComplete();
                            }
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    EquipmentGroup equipmentGroup = new Gson().fromJson(response,EquipmentGroup.class);
                                    mEquipmentGroups.addAll(equipmentGroup.getData());
                                    mEquipmentGroupAdapter.setList(mEquipmentGroups,AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid(),AyjSwApplication.getsInstance().getUserInfo().getData().get(
                                            0).getPassWord(),"1",handler);
                                    lv_equipment_group.setAdapter(mEquipmentGroupAdapter);
                                    break;
                                default:
                                    Toast.makeText(getActivity(), check.getMsg(),
                                            Toast.LENGTH_SHORT).show();

                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
}
