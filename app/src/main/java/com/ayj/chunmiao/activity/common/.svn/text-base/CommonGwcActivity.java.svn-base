package com.ayj.chunmiao.activity.common;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.shopping.AddAddressActivity;
import com.ayj.chunmiao.adapter.shopping.DialogAddressAdapter;
import com.ayj.chunmiao.adapter.shopping.ShopCartAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.shopping.Address;
import com.ayj.chunmiao.bean.shopping.ShopCart;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.SwipMenuListView.SwipeMenu;
import com.ayj.chunmiao.view.SwipMenuListView.SwipeMenuCreator;
import com.ayj.chunmiao.view.SwipMenuListView.SwipeMenuItem;
import com.ayj.chunmiao.view.SwipMenuListView.SwipeMenuListView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/*购物车*/
public class CommonGwcActivity extends BaseActivity {
    SwipeMenuListView lv;

    UserPerson mUserPerson;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private String ordertype;

    ShopCartAdapter commonGwListAdapter;

    Button btn_shopcart_buy;

    TextView tv_shopcart_totalprice;

    int num;

    private List<Address.DataBean> addresses = new ArrayList<>();

    private List<ShopCart.DataBean> list = new ArrayList<>();

    private Double totalMoney = 0.00;

    private DialogAddressAdapter dialogAddressAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_common_gwc;
    }

    @Override
    public void initDatas() {
        mTvTitle.setText("购物车");
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        ordertype = getIntent().getStringExtra("ordertype");
        lv = (SwipeMenuListView) findViewById(R.id.lv);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
                deleteItem.setBackground(R.color.red);
                deleteItem.setWidth(CommonUtils.dip2px(mContext, 80));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(deleteItem);
            }
        };
        lv.setMenuCreator(creator);
        btn_shopcart_buy = (Button) findViewById(R.id.btn_shopcart_buy);
        tv_shopcart_totalprice = (TextView) findViewById(R.id.tv_shopcart_totalprice);
        /*兑换*/
        btn_shopcart_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 0) {
                    showToast("还未有商品加入购物车");
                } else {
                    showAddressDialog();
                }
            }
        });
        lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        delGw(position);
                        break;

                }
                return false;
            }
        });
    }

    @Override
    public void configViews() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        addresses.clear();
        list.clear();
        totalMoney = 0.00;
        getGwcList();
        getAddress();

    }

    /*获取购物车列表*/
    private void getGwcList() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
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
                            mProgressHub.dismiss();
                            showToast(R.string.TheNetIsException);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            mProgressHub.dismiss();
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    ShopCart shopCart = new Gson().fromJson(response,
                                            ShopCart.class);
                                    list = shopCart.getData();
                                    if (commonGwListAdapter == null) {
                                        commonGwListAdapter = new ShopCartAdapter(mContext,
                                                shopCart.getData());
                                        lv.setAdapter(commonGwListAdapter);
                                    } else {
                                        commonGwListAdapter.setList(shopCart.getData());
                                        commonGwListAdapter.notifyDataSetChanged();
                                    }
                                    for (int i = 0; i < shopCart.getData().size(); i++) {
                                        totalMoney +=  Double.parseDouble(new DecimalFormat("0.00").format(
                                                Double.parseDouble(shopCart.getData().get(i).getPrice_now())
                                                        * Integer.parseInt(shopCart.getData().get(i).getNum())).toString());
                                        num = num + Integer.parseInt(
                                                shopCart.getData().get(i).getNum());
                                    }
                                    tv_shopcart_totalprice.setText(new DecimalFormat("0.00").format(totalMoney));
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
                /*跳转到支付界面*/
                setOrder(i);
                dialog.dismiss();
            }
        });

        btn_dialog_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AddAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    /*获取地址*/
    private void getAddress() {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SHOP_DETAIL_ADDRESS))
                    .addParams("json",
                            "{\"key\":\"\",\"userid\":\"" + mUserPerson.getData().get(
                                    0).getSnid() + "\",\"pwd\":\""
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
    private void setOrder(int position) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("type", "gwc");
        bundle.putSerializable("list", (Serializable) list);
        bundle.putSerializable("address", addresses.get(position));
        bundle.putString("money", tv_shopcart_totalprice.getText().toString());
        bundle.putString("order_type", ordertype);
        bundle.putString("num", num + "");
        bundle.putString("matid", list.get(0).getMatid());
        bundle.putString("shop_matid_show",list.get(0).getMatidshow());
        intent.putExtras(bundle);
        intent.setClass(mContext, CommonZfActivity.class);
        startActivity(intent);
    }

    /*删除购物车功能*/
    private void delGw(final int wz) {
        if (CommonUtils.isNetworkAvailable(mContext)) {
            mProgressHub.show();
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.MENBER_DEL_GWC))
                    .addParams("json",
                            "{\"key\":\"\",\"userid\":\"" + mUserPerson.getData().get(
                                    0).getSnid()
                                    + "\",\"pwd\":\"" + mUserPerson.getData().get(
                                    0).getPassWord() + "\",\"snid\":\"" + list.get(
                                    wz).getSnid() + "\"}")
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
                                    Toast.makeText(mContext, "删除成功",
                                            Toast.LENGTH_SHORT).show();
//                                    if ("SHOPCARTTYPE001".equals(list.get(0).getCarttype())
//                                            || "SHOPCARTTYPE004".equals(
//                                            list.get(0).getCarttype())) {
                                    totalMoney = 0.00;
                                    list.remove(wz);
                                    for (int i = 0; i < list.size(); i++) {
                                        if (!TextUtils.isEmpty(
                                                list.get(i).getPrice_now())) {
                                            totalMoney += Double.parseDouble(
                                                    list.get(i).getPrice_now())
                                                    * Integer.parseInt(
                                                    list.get(i).getNum());
                                        }
                                    }
                                    tv_shopcart_totalprice.setText(
                                            Double.parseDouble(new DecimalFormat(
                                                    "0.00").format(totalMoney).toString())
                                                    + "");
                                    commonGwListAdapter.setList(list);
                                    commonGwListAdapter.notifyDataSetChanged();
                                    break;
                                default:
                                    Toast.makeText(mContext, "删除失败",
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            showToast(R.string.TheNetIsUnAble);
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
