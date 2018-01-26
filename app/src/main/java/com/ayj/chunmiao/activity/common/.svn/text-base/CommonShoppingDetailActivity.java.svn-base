package com.ayj.chunmiao.activity.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.shopping.AddAddressActivity;
import com.ayj.chunmiao.adapter.shopping.DialogAddressAdapter;
import com.ayj.chunmiao.adapter.shopping.GuigeGridViewAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.cmbz.YybBean;
import com.ayj.chunmiao.bean.shopping.Address;
import com.ayj.chunmiao.bean.shopping.JinmaoShopDetial;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.AddAndSub.MyAddAndSubView;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


/**
 * Created by zht-pc-09 on 2017/6/8.
 * 通用的商品详情界面
 */
public class CommonShoppingDetailActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.tv_gw_count)
    TextView tvGwCount;
    @BindView(R.id.rl_right)
    RelativeLayout rlRight;
    @BindView(R.id.banner_main)
    Banner bannerMain;
    @BindView(R.id.tv_foot_zq_detail_name)
    TextView tvFootZqDetailName;
    @BindView(R.id.tv_foot_zq_detail_yuanjia)
    TextView tvFootZqDetailYuanjia;
    @BindView(R.id.tv_foot_zq_detail_yuanjian)
    TextView tvFootZqDetailYuanjian;
    @BindView(R.id.tv_foot_zq_detail_qianzhui)
    TextView tvFootZqDetailQianzhui;
    @BindView(R.id.tv_foot_zq_detail_sheyuan)
    TextView tvFootZqDetailSheyuan;
    @BindView(R.id.relativelayout_detail_zq)
    RelativeLayout relativelayoutDetailZq;
    @BindView(R.id.tv_foot_detail_price)
    TextView tvFootDetailPrice;
    @BindView(R.id.relativelayout_detail_dh)
    RelativeLayout relativelayoutDetailDh;
    @BindView(R.id.tv_foot_xd_price)
    TextView tvFootXdPrice;
    @BindView(R.id.relativelayout_detail_xd)
    RelativeLayout relativelayoutDetailXd;
    @BindView(R.id.tv_foot_zq_detail_xiaoliang)
    TextView tvFootZqDetailXiaoliang;
    @BindView(R.id.tv_foot_zq_detail_style)
    TextView tvFootZqDetailStyle;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.addandsub_shopcart)
    MyAddAndSubView addandsubShopcart;
    @BindView(R.id.relativelayout_zq_detail_style)
    RelativeLayout relativelayoutZqDetailStyle;
    @BindView(R.id.tv_foot_zq_detail_address)
    TextView tvFootZqDetailAddress;
    @BindView(R.id.tv_shop_detail_address)
    TextView tvShopDetailAddress;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.relativelayout_zq_detail_address)
    RelativeLayout relativelayoutZqDetailAddress;
    @BindView(R.id.iv_cp_detail)
    ImageView ivCpDetail;
    @BindView(R.id.btn_add_shopcart)
    Button btnAddShopcart;
    @BindView(R.id.btn_buy_now)
    Button btnBuyNow;
    @BindView(R.id.linearlayout_zq_detail)
    LinearLayout linearlayoutZqDetail;
    @BindView(R.id.button_zq_detail_dh)
    Button buttonZqDetailDh;
    @BindView(R.id.relativeLayout_zq_detail)
    RelativeLayout relativeLayoutZqDetail;

    String type;

    String ordertype;

    String matid;

    private JinmaoShopDetial jinmaoShopDetial;

    private List<String> networkImages;

    private List<String> styleids;

    private DialogAddressAdapter dialogAddressAdapter;

    private String guige = "默认款式";

    private List<String> classification_guige;

    private String styleid = null;//规格id

    private int count = 0;

    private List<Address.DataBean> addresses = new ArrayList<>();

    private int num = 1;//购买数量

    private Address.DataBean address;//收货地址

    List<YybBean.DataBean> listYyb = new ArrayList<>();

    UserPerson mUserPerson;
    private int totalNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_shopping_detail;
    }

    @Override
    public void initDatas() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        rlRight.setOnClickListener(this);
        buttonZqDetailDh.setOnClickListener(this);
        tvTitle.setText("商品详情");
        mContext = this;
        relativelayoutZqDetailStyle.setOnClickListener(this);
        relativelayoutZqDetailAddress.setOnClickListener(this);
        btnBuyNow.setOnClickListener(this);
        btnAddShopcart.setOnClickListener(this);
        btnBuyNow.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivRight.setOnClickListener(this);
        classification_guige = new ArrayList<>();
         /*初始化banner*/
        CommonUtils.setBannerAttribute(mContext, bannerMain);
         /*通过type1*/
        type = getIntent().getStringExtra("type");
        ordertype = getIntent().getStringExtra("ordertype");
        matid = getIntent().getStringExtra("matid");
        jinmaoShopDetial = new JinmaoShopDetial();
        networkImages = new ArrayList<>();
        styleids = new ArrayList<>();
         /*初始化界面*/
        if (type.equals("dhzx") || type.equals("jbdh") || type.equals("aebdh") || type.equals("wjdh")) {
             /*兑换中心*/
            tvCount.setVisibility(View.GONE);
            addandsubShopcart.setVisibility(View.GONE);
            buttonZqDetailDh.setVisibility(View.VISIBLE);
            rlRight.setVisibility(View.GONE);
            relativelayoutDetailDh.setVisibility(View.VISIBLE);
        } else if (type.equals("zkp")) {
             /*折扣铺*/
            linearlayoutZqDetail.setVisibility(View.VISIBLE);
            relativelayoutDetailZq.setVisibility(View.VISIBLE);
        } else {
             /*小店和热柜*/
            linearlayoutZqDetail.setVisibility(View.VISIBLE);
            relativelayoutDetailXd.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void configViews() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        styleid = null;
        guige = "默认款式";
        num = 1;
        networkImages.clear();
        addresses.clear();
        getSpDetails();//获取商品详情
        getAddress();//获取商品地址
        getShopcartNum();//获取购物车数量
        if(type.equals("wjdh")){
            getNumLeft();
        }
    }

    /*获取商品详情*/
    private void getSpDetails() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_DETAIL))
                    .addParams("json",
                            "{\"key\":\"\",\"ordertype\":\"" + ordertype + "\",\"matid\":\""
                                    + matid + "\"}")
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
                                    jinmaoShopDetial = new Gson().fromJson(response,
                                            JinmaoShopDetial.class);
                                    if (jinmaoShopDetial.getData().get(0).getTotalnum() == null){
                                        totalNum = 0;
                                    }else {
                                        totalNum = Integer.parseInt(jinmaoShopDetial.getData().get(0).getTotalnum());
                                    }
                                    addandsubShopcart.setMax(totalNum);
                                    addandsubShopcart.setOnPosNegListener(
                                            new MyAddAndSubView.OnPosNegListener() {
                                                @Override
                                                public void onMyPositiveListener(int count) {
                                                    num = count;
                                                }

                                                @Override
                                                public void onMyNegativeListener(int count) {
                                                    num = count;
                                                }
                                            });
                                    addandsubShopcart.setOnClickAddAndSubListener(
                                            new MyAddAndSubView.OnClickAddAndSubListener() {
                                                @Override
                                                public void clickAdd(int count) {
                                                    num = count;
                                                }

                                                @Override
                                                public void clickSub(int count) {
                                                    num = count;
                                                }
                                            });

                                    tvFootZqDetailName.setText(
                                            jinmaoShopDetial.getData().get(0).getMatidshow());//名字
                                    tvFootZqDetailYuanjian.setText(
                                            "¥" + jinmaoShopDetial.getData().get(0).getBeforeprice()
                                                    + "元");
                                    tvFootZqDetailYuanjian.getPaint().setFlags(
                                            Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                                    tvFootZqDetailSheyuan.setText(
                                            "¥" + jinmaoShopDetial.getData().get(0).getNowprice()
                                                    + "元");
                                    tvFootZqDetailXiaoliang.setText(
                                            "库存" + totalNum + "件");
                                    tvFootDetailPrice.setText(
                                            jinmaoShopDetial.getData().get(0).getNowprice()
                                                    + "个爱e币");
                                    tvFootXdPrice.setText(
                                            "¥" + jinmaoShopDetial.getData().get(0).getNowprice()
                                                    + "元");
                                    if (!TextUtils.isEmpty(jinmaoShopDetial.getData().get(
                                            0).getIntroducemediaidcompressshow())) {
                                        Picasso.with(mContext).load(
                                                jinmaoShopDetial.getData().get(
                                                        0).getIntroducemediaidshow()).placeholder(R.mipmap.banner_loading).error(
                                                R.mipmap.banner_error).into(
                                                ivCpDetail);
                                    }
                                    String[] datas = jinmaoShopDetial.getData().get(
                                            0).getImgurlshow().split(",");
                                    for (int i = 0; i < datas.length; i++) {
                                        networkImages.add(datas[i]);
                                    }
                                    bannerMain.setImages(networkImages);
                                    //banner设置方法全部调用完毕时最后调用
                                    bannerMain.start();
                                    String matstyle = "";
                                    if (!TextUtils.isEmpty(matstyle)) {
                                        String[] matstyles = matstyle.split(",");
                                        count = matstyles.length;
                                        for (int i = 0; i < matstyles.length; i++) {
                                            String[] styles = matstyles[i].split("\\|");
                                            classification_guige.add(styles[1]);
                                            styleids.add(styles[0]);
                                            styleid = styleids.get(0);
                                            guige = classification_guige.get(0);
                                        }
                                    } else {
                                        count = 1;
                                        classification_guige.add("默认款式");
                                    }
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    /**
     * 弹出选择收货地址dialog
     */
    private void showAddressDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(mContext,
                R.style.DialogTheme).create();
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_zq_address);
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.7);
        window.setAttributes(p);

        final ImageView iv_back = (ImageView) dialog.findViewById(R.id.iv_zq_address_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        final ListView lv_dialog_address = (ListView) dialog.findViewById(R.id.lv_dialog_address);
        final Button btn_dialog_addaddress = (Button) dialog.findViewById(
                R.id.btn_dialog_addaddress);
        dialogAddressAdapter = new DialogAddressAdapter(
                mContext, addresses);
        lv_dialog_address.setAdapter(dialogAddressAdapter);
        lv_dialog_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                address = addresses.get(i);
                tvShopDetailAddress.setText(
                        addresses.get(i).getProvinceid() + "\t" + addresses.get(
                                i).getCityid() + "\t" + addresses.get(i).getAreaid() + "\t"
                                + addresses.get(i).getDetailaddr());
                dialog.dismiss();
            }
        });

        btn_dialog_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //新增地址
                Intent intent = new Intent(mContext, AddAddressActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    /*获取地址*/
    private void getAddress() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_DETAIL_ADDRESS))
                    .addParams("json",
                            "{\"key\":\"\",\"userid\":\"" + mUserPerson.getData().get(0).getSnid() + "\",\"pwd\":\""
                                    + mUserPerson.getData().get(0).getPassWord() + "\"}")
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
                                    Address add = new Gson().fromJson(response, Address.class);
                                    for (int i = 0; i < add.getData().size(); i++) {
                                        addresses.add(add.getData().get(i));
                                    }
                                    if (add.getData().size() != 0) {
                                        address = add.getData().get(0);
                                        tvShopDetailAddress.setText(
                                                address.getProvinceid() + "\t"
                                                        + address.getCityid() + "\t"
                                                        + address.getAreaid() + "\t"
                                                        + address.getDetailaddr());
                                    }
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    /*获取购物车数量*/
    private void getShopcartNum() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.MENBER_GET_GWC))
                    .addParams("json",
                            "{\"key\":\"\",\"msnid\":\"" + mUserPerson.getData().get(0).getSnid()
                                    + "\",\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()
                                    + "\",\"carttype\":\"" + ordertype + "\"}")
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
                                    tvGwCount.setVisibility(View.VISIBLE);
                                    if (check.getTotal() != 0) {
                                        tvGwCount.setVisibility(View.VISIBLE);
                                        if (check.getTotal() < 99) {
                                            tvGwCount.setText(check.getTotal() + "");
                                        } else {
                                            tvGwCount.setText("99");
                                        }
                                    } else {
                                        tvGwCount.setVisibility(View.GONE);
                                    }
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativelayout_zq_detail_address:
                /*地址*/
                showAddressDialog();
                break;
            case R.id.btn_add_shopcart:
                /*加入购物车*/
                if (!tvShopDetailAddress.getText().toString().trim().equals("")) {
                    if (!jinmaoShopDetial.getData().get(0).getTotalnum().equals("0")) {
                        addShopCart(num + "", styleid);
                    } else {
                        Toast.makeText(mContext, "抱歉,该商品暂无库存",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "请选择派送地址",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_buy_now:
                /*立即购买*/
                setOrder(ordertype);
                break;
            case R.id.button_zq_detail_dh:
                /*兑换*/
                if(type.equals("jbdh")){
                    dhShopping("PAYTYPE011","MEMBERORDERTYPE025");
                }else if(type.equals("aebdh")){
                    dhShopping("PAYTYPE002","MEMBERORDERTYPE002");
                }else if(type.equals("wjdh")){
                    /*物卷兑换*/

                }


                break;
            case R.id.iv_right:
                /*点击购物车*/
                Intent intent = new Intent();
                intent.putExtra("ordertype", ordertype);
                intent.putExtra("addres", "detail");
                intent.setClass(mContext, CommonGwcActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
    /**
     * 加入购物车
     *
     * @param num     加入商品数量
     * @param styleid 加入商品款式规格
     */
    private void addShopCart(String num, String styleid) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.MENBER_XIA_GWC))
                    .addParams("json",
                            "{\"key\":\"\",\"msnid\":\"" + mUserPerson.getData().get(0).getSnid()
                                    + "\",\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()
                                    + "\",\"carttype\":\"" + ordertype + "\",\"matid\":\"" + matid
                                    + "\",\"tcid\":\"\",\"styleid\":\"" + "" + "\",\"num\":\""
                                    + num + "\",\"shopid\":\""
                                    + mUserPerson.getData().get(0).getRegshopid() + "\"}")
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
                                    CommonUtils.getSuccessDialog(mContext, "成功", "已加入购物车", new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismiss();
                                            getShopcartNum();
                                        }
                                    }).show();
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }
    /**
     * 生成订单
     */
    private void setOrder(String order_type) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("type", "spDetail");
        bundle.putSerializable("address", address);
        bundle.putString("num", num + "");
        bundle.putString("matid", matid);
        bundle.putString("money", Double.parseDouble(new DecimalFormat("#.00").format(
                Double.parseDouble(jinmaoShopDetial.getData().get(0).getNowprice())
                        * num).toString()) + "");
        bundle.putString("order_type", order_type);
        bundle.putString("img_url",jinmaoShopDetial.getData().get(
                0).getLogoimgurlshow());
        bundle.putString("shop_matid_show",jinmaoShopDetial.getData().get(0).getMatidshow());
        bundle.putString("now_price",jinmaoShopDetial.getData().get(0).getNowprice());
        bundle.putString("shop_matid",jinmaoShopDetial.getData().get(0).getMatid());
        intent.putExtras(bundle);
        intent.setClass(mContext, CommonZfActivity.class);
        startActivity(intent);
    }

    private void dhShopping(String paytype,String ordertype) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SEND_DD))
                    .addParams("json","{\"key\":\"\",\"msnid\":\""
                            +mUserPerson.getData().get(0).getSnid()
                            + "\",\"pwd\":\"" +mUserPerson.getData().get(0).getPassWord()
                            + "\",\"paytype\":\"" + paytype + "\",\"ordertype\":\"" + ordertype
                            + "\",\"memberaddrid\":\"" + address.getSnid()
                            + "\",\"smallmoneyuse\":\"" + ""
                            + "\",\"membermoneybagusemoney\":\"" + ""
                            + "\",\"servicebooksnid\":\"" + ""
                            + "\",\"memberticketdetail\":\"" + ""
                            + "\",\"membercarddetail\":\"" + ""
                            + "\",\"orderdetail\":\"" + "[" + "{" + "\\\"pid\\\":\\\"" + jinmaoShopDetial.getData().get(0).getMatid()
                            + "\\\",\\\"shopid\\\":\\\"" + mUserPerson.getData().get(0).getRegshopid()
                            + "\\\",\\\"num\\\":\\\""
                            + num + "\\\"" + "}" + "]" + "\"}")
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
                                    CommonUtils.getSuccessDialog(mContext, "成功", "兑换成功", new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismiss();
                                        }
                                    }).show();
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    private void getNumLeft() {
        listYyb.clear();
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            JSONObject obj = new JSONObject();
            try {
                obj.put("key", "");
                obj.put("userid",
                        mUserPerson.getData().get(0).getSnid());
                obj.put("pwd",mUserPerson.getData().get(
                        0).getPassWord());
                obj.put("pageno", 0);
                obj.put("pagesize", 999);
                obj.put("for_matid",matid);
                obj.put("onlinetype","ONLINETYPE001");
                obj.put("status","TICKETSTATUS001");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.GET_TYQ))
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
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    YybBean ticket = new Gson().fromJson(response,
                                            YybBean.class);
                                    listYyb.addAll(ticket.getData());
                                    break;
                                default:
                                    Toast.makeText(mContext, check.getMsg(),
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
