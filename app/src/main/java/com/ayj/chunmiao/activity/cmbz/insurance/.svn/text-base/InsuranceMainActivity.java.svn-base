package com.ayj.chunmiao.activity.cmbz.insurance;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.bdcz.BdczMainActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.bjfk.InsuranceFeedbackActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.clbx.InsuranceCarActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.clxx.VehicleInfoActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.common.CommonBxListActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.common.CommonViewPagerActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.myBx.MyBxListActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.main.CommonGridAdapter;
import com.ayj.chunmiao.bean.UtilityItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/*保险*/
public class InsuranceMainActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_head)
    TextView tvRightHead;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.insurance_main_item)
    RecyclerView insuranceMainItem;
    @BindView(R.id.insurance_main_company)
    RecyclerView insuranceMainCompany;
    @BindView(R.id.car_insurance)
    RelativeLayout carInsurance;
    @BindView(R.id.family_insurance)
    RelativeLayout familyInsurance;
    @BindView(R.id.accident_insurance)
    RelativeLayout accidentInsurance;
    @BindView(R.id.property_insurance)
    RelativeLayout propertyInsurance;

    List<UtilityItem> itemsList = new ArrayList<>();
    List<UtilityItem> companyList = new ArrayList<>();

    CommonGridAdapter insuranceItemGridAdapter;
    CommonGridAdapter companyGridAdapter;

    @Override
    public void configViews() {
        insuranceItemGridAdapter = new CommonGridAdapter(R.layout.item_insurance_main, itemsList);
        insuranceMainItem.setAdapter(insuranceItemGridAdapter);
        insuranceMainItem.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (itemsList.get(position).getType()) {
                    case 1:
                        //购买车险
                        startActivity(new Intent(mContext,InsuranceCarActivity.class));
                        break;
                    case 2:
                        //北斗车载
                        startActivity(new Intent(mContext,BdczMainActivity.class));
                        break;
                    case 3:
                        //车辆信息
                        Intent mIntent = new Intent(mContext,VehicleInfoActivity.class);
                        mIntent.putExtra("type","1");
                        startActivity(mIntent);
                        break;
                    case 4:
                        //我的保险
                        startActivity(new Intent(mContext,
                                MyBxListActivity.class));
                        break;
                    case 5:
                        //保险咨询
                        startActivity(new Intent(mContext,InsuranceAdviceActivity.class));
                        break;
                    case 6:
                        //报价反馈
                        startActivity(new Intent(mContext,InsuranceFeedbackActivity.class));
                        break;
                }
            }
        });
        companyGridAdapter = new CommonGridAdapter(R.layout.item_company_insurance_main, companyList);
        insuranceMainCompany.setAdapter(companyGridAdapter);
        insuranceMainCompany.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (companyList.get(position).getType()) {
                    case 1:
                        //中联金安
                        CommonViewPagerActivity.startActivity(mContext,"太平洋保险","INSURANCEBRAND003");
                        break;
                    case 2:
                        //太平洋
                        CommonViewPagerActivity.startActivity(mContext,"太平洋保险","INSURANCEBRAND003");
                        break;
                    case 3:
                        //中国人保
                        CommonViewPagerActivity.startActivity(mContext,"中国人保","INSURANCEBRAND002");
                        break;
                    case 4:
                        //天安保险
                        showToast("敬请期待");
                        break;
                }
            }
        });

    }

    @Override
    public void initDatas() {
        tvTitle.setText("保险经纪");
        itemsList = UtilityItem.getInsuranceList();
        companyList = UtilityItem.getInsuranceCompnayList();
        insuranceMainItem.setHasFixedSize(true);
        insuranceMainItem.setLayoutManager(new GridLayoutManager(mContext, 3));
        insuranceMainCompany.setHasFixedSize(true);
        insuranceMainCompany.setLayoutManager(new GridLayoutManager(mContext, 4));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_insurance_main;
    }

    @OnClick({R.id.iv_back, R.id.car_insurance, R.id.family_insurance, R.id.accident_insurance, R.id.property_insurance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.car_insurance:
                //旅游险
                CommonBxListActivity.jumpActivity(mContext,"旅游险","INSURANCECLSONE001","","","");
                break;
            case R.id.family_insurance:
                //家庭险
                CommonBxListActivity.jumpActivity(mContext,"家庭险","INSURANCECLSONE006","","","");
                break;
            case R.id.accident_insurance:
                //意外险
                CommonBxListActivity.jumpActivity(mContext,"意外险","INSURANCECLSONE002","","","");
                break;
            case R.id.property_insurance:
                //财产险
                CommonBxListActivity.jumpActivity(mContext,"财产险","INSURANCECLSONE007","","","");
                break;
        }
    }
}
