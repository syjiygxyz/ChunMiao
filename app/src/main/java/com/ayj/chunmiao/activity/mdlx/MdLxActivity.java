package com.ayj.chunmiao.activity.mdlx;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.mdlx.lxsb.LxsbActivity;
import com.ayj.chunmiao.activity.myinformation.shoporder.ShopOrderActivity;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.listener.OnItemClickListener;
import com.ayj.chunmiao.adapter.mdlx.MdLxAdapter;
import com.ayj.chunmiao.adapter.txl.MyTxlPopAdapter;
import com.ayj.chunmiao.bean.AreaInfo;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.DictListBean;
import com.ayj.chunmiao.bean.ProvinceBean;
import com.ayj.chunmiao.bean.UtilityItem;
import com.ayj.chunmiao.bean.cg.Shoppingmall;
import com.ayj.chunmiao.bean.shopping.ShopBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.GetJsonDataUtil;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.AddAndSub.MyCustomDialog;
import com.ayj.chunmiao.view.OperatePopupWindow;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.loadingviewfinal.OnDefaultRefreshListener;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.PtrClassicFrameLayout;
import cn.finalteam.loadingviewfinal.PtrFrameLayout;
import cn.finalteam.loadingviewfinal.RecyclerViewFinal;
import okhttp3.Call;

/*
*门店联销
* */
public class MdLxActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sb)
    TextView tvSb;
    @BindView(R.id.pcf_refresh)
    PtrClassicFrameLayout pcfRefresh;
    @BindView(R.id.tv_mdlx_fhd)
    TextView tvMdlxFhd;
    @BindView(R.id.tv_mdlx_shijian)
    TextView tvMdlxShijian;
    @BindView(R.id.tv_fuwudian)
    TextView tvFuwudian;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.recycler_view)
    RecyclerViewFinal recyclerView;

    private OperatePopupWindow operatePopupWindow;

    private PopupWindow popupWindow;

    private MyCustomDialog dialog;

    private List<UtilityItem> shopList = new ArrayList<>();
    private List<UtilityItem> topItemList = new ArrayList<>();
    private List<ShopBean.DataBean> shopBeanList = new ArrayList<>();
    private List<Shoppingmall.DataBean>  list = new ArrayList<>();
    private List<AreaInfo.DataBean> addresList = new ArrayList<>();
    private static ArrayList<ProvinceBean> provinceBeanList = new ArrayList<>();
    //  区/县
    private static ArrayList<String> district;
    private static ArrayList<List<String>> districts;
    private static ArrayList<List<List<String>>> districtList = new ArrayList<>();
    private static ArrayList<String> cities;
    private static ArrayList<List<String>> cityList = new ArrayList<>();
    private MdLxAdapter adapter;
    private String type,mProvince, mCity ,mArea,mProvinceId, mCityId ,mAreaId,classTwo;
    private int num;
    private View mMenuView;
    private int pageNo = 1;
    private int pageSize = 20;
    private String mShopId;


    @Override
    public int getLayoutId() {
        return R.layout.activity_md_lx;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("联销量贩商城");
        ivRight.setImageResource(R.mipmap.lxsc_fenlei);
        ivRight.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    public void configViews() {
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                ++pageNo;
                getShoppingList();
            }
        });
        pcfRefresh.setOnRefreshListener(new OnDefaultRefreshListener() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo = 1;
                list.clear();
                tvMdlxFhd.setText("发货地");
                tvMdlxShijian.setText("上架时间");
                tvFuwudian.setText("服务点");
                mAreaId = null;
                mShopId = null;
                classTwo = null;
                getShoppingList();
            }
        });
        getShoppingList();
        getShopIdList();
        getClassList();
    }

    private void getClassList() {
        if (CommonUtils.isNetworkAvailable(mContext)){
            mProgressHub.show();
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("dicttypeid","MEMBERORDERTYPE029");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CL_FX))
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
                                case 0:
                                    topItemList.clear();
                                    DictListBean dictListBean = new Gson().fromJson(response,DictListBean.class);
                                    for (int i=0;i<dictListBean.getData().size();i++){
                                        topItemList.add(new UtilityItem(dictListBean.getData().get(i).getParamname()));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        }else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void getShopIdList() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("key", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.SUPPLIER_SHOP))
                .addParams("json",String.valueOf(obj))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showToast(R.string.TheNetIsException);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Check check = new Gson().fromJson(response,Check.class);
                        switch (check.getErr()){
                            case 0:
                                ShopBean shopBean = new Gson().fromJson(response,ShopBean.class);
                                shopBeanList.addAll(shopBean.getData());
                                for (int i=0; i<shopBean.getData().size(); i++){
                                    shopList.add(new UtilityItem(shopBean.getData().get(i).getShopname()));
                                }
                                break;
                            default:
                                showToast(check.getMsg());
                                break;
                        }
                    }
                });
    }

    private void getShoppingList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                obj.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                obj.put("ordertype", Constant.LX_CG);
                obj.put("matclass2",classTwo);
                obj.put("supperlierarea",mAreaId);
                obj.put("btime",tvMdlxShijian.getText().equals("上架时间")? null : tvMdlxShijian.getText());
                obj.put("supperliershopid",mShopId);
                obj.put("pageno",pageNo);
                obj.put("pagesize",pageSize);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_SHOPPING_MALL_LIST))
                    .addParams("json", String.valueOf(obj))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            recyclerView.onLoadMoreComplete();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            pcfRefresh.onRefreshComplete();
                            recyclerView.onLoadMoreComplete();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    Shoppingmall shoppingmall = new Gson().fromJson(response, Shoppingmall.class);
                                    list.addAll(shoppingmall.getData());
                                    if (null == adapter) {
                                        adapter = new MdLxAdapter(R.layout.item_lx_shop, list);
                                        recyclerView.setAdapter(adapter);
                                    } else {
                                        adapter.setNewData(list);
                                    }
                                    adapter.setAddClickListener(new MdLxAdapter.RecyclerOnItemClickListener() {
                                        @Override
                                        public void addClickListener(int position) {
                                            showCustomDialog(position);
                                        }

                                        @Override
                                        public void onItemClickListener(int position) {
                                            Intent intent = new Intent(mContext,MdLxDetailActivity.class);
                                            intent.putExtra("item",(Serializable) list.get(position));
                                            startActivity(intent);
                                        }
                                    });
                                    if (shoppingmall.getData().size()<pageSize){
                                        recyclerView.setHasLoadMore(false);
                                    }else {
                                        recyclerView.setHasLoadMore(true);
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

    private void showCustomDialog(final int pos) {
        dialog = new MyCustomDialog(mContext,1,Integer.parseInt(list.get(pos).getTotalnum()));
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = dialog.getCount();
                dialog.dismiss();
                setShopOrder(pos);
            }
        });
        dialog.show();
    }

    private void setShopOrder(final int pos) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject jsonObject = new JSONObject();
            JSONObject detail = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            try {
                detail.put("matid",list.get(pos).getMatid());
                detail.put("num",num);
                jsonArray.put(detail);
                jsonObject.put("key", "");
                jsonObject.put("userid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getUserid());
                jsonObject.put("pwd", AyjSwApplication.getsInstance().getUserInfo().getData().get(
                        0).getPassWord());
                jsonObject.put("ordertype", list.get(pos).getOrdertype());
                jsonObject.put("detail", jsonArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CG_XD))
                    .addParams("json", String.valueOf(jsonObject))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    showConfirmDialog();
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

    private void showConfirmDialog() {
       SweetAlertDialog confirmDialog =  CommonUtils.getConfirmDialog(mContext, "提示", "成功加入采购单", "查看采购单", "再看看", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                startActivity(new Intent(mContext, ShopOrderActivity.class));
                sweetAlertDialog.dismiss();
            }
        });
        confirmDialog.show();
    }

    @OnClick({R.id.iv_back, R.id.iv_right, R.id.tv_mdlx_fhd, R.id.tv_mdlx_shijian, R.id.tv_fuwudian, R.id.tv_sb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showClassPop();
                break;
            case R.id.tv_mdlx_fhd:
                //CommonUtils.getDqPickerView(mContext, tvMdlxFhd);
                showAddrPickerView();
                break;
            case R.id.tv_mdlx_shijian:
                showTimePicker();
                break;
            case R.id.tv_fuwudian:
                showpop();
                break;
            case R.id.tv_sb:
                startActivity(new Intent(mContext, LxsbActivity.class));
                break;
        }
    }

    private void showAddrPickerView() {
        String province_data_json = GetJsonDataUtil.getJson(mContext, "province_data.json");
        //  解析json数据
        parseJson(province_data_json);
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(mContext,
                new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String city = provinceBeanList.get(options1).getPickerViewText();
                        String address;
                        //  如果是直辖市或者特别行政区只设置市和区/县
                        if ("北京市".equals(city) || "上海市".equals(city) || "天津市".equals(city)
                                || "重庆市".equals(city) || "澳门".equals(city) || "香港".equals(city)) {
                            address = provinceBeanList.get(options1).getPickerViewText()
                                    + " " + "直辖市"
                                    + " " +
                                    districtList.get(options1).get(options2).get(options3);
                        } else {
                            address = provinceBeanList.get(options1).getPickerViewText()
                                    + " " + cityList.get(options1).get(options2)
                                    + " " + districtList.get(options1).get(options2).get(options3);
                        }
                        //设置
                        tvMdlxFhd.setText(address);
                        StringTokenizer st = new StringTokenizer(address);
                        mProvince = st.nextToken();
                        mCity = st.nextToken();
                        mArea = st.nextToken();
                        list.clear();
                        pageNo = 1;
                        getProvinceId();
                    }
                })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(provinceBeanList, cityList, districtList);//三级选择器
        pvOptions.show();
    }
    //  解析json填充集合
    private static void parseJson(String str) {
        try {
            //  获取json中的数组
            JSONArray jsonArray = new JSONArray(str);
            //  遍历数据组
            for (int i = 0; i < jsonArray.length(); i++) {
                //  获取省份的对象
                JSONObject provinceObject = jsonArray.optJSONObject(i);
                //  获取省份名称放入集合
                String provinceName = provinceObject.getString("name");
                provinceBeanList.add(new ProvinceBean(provinceName));
                //  获取城市数组
                JSONArray cityArray = provinceObject.optJSONArray("city");
                cities = new ArrayList<>();//   声明存放城市的集合
                districts = new ArrayList<>();//声明存放区县集合的集合
                //  遍历城市数组
                for (int j = 0; j < cityArray.length(); j++) {
                    //  获取城市对象
                    JSONObject cityObject = cityArray.optJSONObject(j);
                    //  将城市放入集合
                    String cityName = cityObject.optString("name");
                    cities.add(cityName);
                    district = new ArrayList<>();// 声明存放区县的集合
                    //  获取区县的数组
                    JSONArray areaArray = cityObject.optJSONArray("area");
                    //  遍历区县数组，获取到区县名称并放入集合
                    for (int k = 0; k < areaArray.length(); k++) {
                        String areaName = areaArray.getString(k);
                        district.add(areaName);
                    }
                    //  将区县的集合放入集合
                    districts.add(district);
                }
                //  将存放区县集合的集合放入集合
                districtList.add(districts);
                //  将存放城市的集合放入集合
                cityList.add(cities);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showClassPop() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mMenuView = inflater.inflate(R.layout.lx_class_sheet, null);
        RecyclerView recyclerView = (RecyclerView) mMenuView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setAdapter(new MyTxlPopAdapter(R.layout.item_lx_class, topItemList));
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //showToast(topItemList.get(position).getText());
                classTwo = topItemList.get(position).getText();
                pageNo = 1;
                list.clear();
                getShoppingList();
                popupWindow.dismiss();
            }
        });
        popupWindow = new PopupWindow(mMenuView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xb0000000));

        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height =
                        mMenuView.findViewById(R.id.recycler_view).getBottom();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y > height) {
                        // 点击弹出框外面自动消失
                        popupWindow.setOutsideTouchable(true);
                        // 自动获取焦点,否则EditText将无法获取焦点
                        popupWindow.setFocusable(true);
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        popupWindow.showAsDropDown(ivRight);
        popupWindow.update();
    }

    private void showpop() {
        operatePopupWindow = new OperatePopupWindow(mContext, "选择服务点", shopList, new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                tvFuwudian.setText(shopList.get(position).getText());
                mShopId = shopBeanList.get(position).getSid();
                operatePopupWindow.dismiss();
                list.clear();
                pageNo = 1;
                getShoppingList();
            }
        });
        operatePopupWindow.showAtLocation(recyclerView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        operatePopupWindow.update();
    }

    private void showTimePicker() {
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2016, 0, 1);
        TimePickerView tp = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvMdlxShijian.setText(CommonUtils.getTime(date, "yyyy-MM-dd"));
                list.clear();
                pageNo = 1;
                getShoppingList();
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setCancelText("取消").setDate(startDate).setRangDate(endDate, startDate).gravity(Gravity.TOP).build();
        tp.show(tvMdlxShijian);
    }

    private void getProvinceId() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("pid","0001");
            }catch (JSONException e){
                e.printStackTrace();
            }

            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
                    .addParams("json",String.valueOf(object))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    AreaInfo areainfo_pro = new Gson().fromJson(response, AreaInfo.class);
                                    addresList.addAll(areainfo_pro.getData());
                                    for (int i=0;i<addresList.size();i++){
                                        if (mProvince.equals(addresList.get(i).getName())){
                                            mProvinceId = addresList.get(i).getAid();
                                        }
                                    }
                                    getCityId();
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

    private void getCityId() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("pid",mProvinceId);
            }catch (JSONException e){
                e.printStackTrace();
            }

            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
                    .addParams("json",String.valueOf(object))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    AreaInfo areainfo_pro = new Gson().fromJson(response, AreaInfo.class);
                                    addresList.addAll(areainfo_pro.getData());
                                    for (int i=0;i<addresList.size();i++){
                                        if (mCity.equals(addresList.get(i).getName())){
                                            mCityId = addresList.get(i).getAid();
                                        }
                                    }
                                    getAreaId();
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void getAreaId() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            JSONObject object = new JSONObject();
            try{
                object.put("key","");
                object.put("pid",mCityId);
            }catch (JSONException e){
                e.printStackTrace();
            }

            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CX_URL))
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
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    AreaInfo areainfo_pro = new Gson().fromJson(response, AreaInfo.class);
                                    addresList.addAll(areainfo_pro.getData());
                                    for (int i=0;i<addresList.size();i++){
                                        if (mArea.equals(addresList.get(i).getName())){
                                            mAreaId = addresList.get(i).getAid();
                                        }
                                    }
                                    getShoppingList();

                                    break;
                                default:
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
}
