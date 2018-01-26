package com.ayj.chunmiao.activity.khq.yyb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.common.CommonZfActivity;
import com.ayj.chunmiao.activity.shopping.AddAddressActivity;
import com.ayj.chunmiao.adapter.shopping.DialogAddressAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.shopping.Address;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.NumberButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
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

/*
* 营养吧
* */
public class YybActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.number_button)
    NumberButton numberButton;
    @BindView(R.id.tv_gm)
    TextView tvGm;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_sp_name)
    TextView tv_sp_name;

    UserPerson mUserPerson;

    private DialogAddressAdapter dialogAddressAdapter;

    private List<Address.DataBean> addresses = new ArrayList<>();

    FootZqShop.DataBean items;

    public static void jumpActivity(Context context, FootZqShop.DataBean item){
        Intent intent = new Intent(context,YybActivity.class);
        intent.putExtra("info", (Serializable) item);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_yyb;
    }

    @Override
    public void initDatas() {
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        tvTitle.setText("商品详情");
        items = (FootZqShop.DataBean) getIntent().getSerializableExtra("info");
    }

    @Override
    public void configViews() {
        getAddress();
        int width = CommonUtils.getScreenWidth(mContext);
        int bannerHeigth = (width * 9) / 16;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv.getLayoutParams();
        if (params == null) {
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    bannerHeigth);
            iv.setLayoutParams(params);
        } else {
            params.height = bannerHeigth;
            iv.setLayoutParams(params);
        }
        Picasso.with(mContext).load(
                items.getImgurlshow()).placeholder(R.mipmap.banner_loading).error(
                R.mipmap.banner_error).into(
                iv);
        tv_sp_name.setText(items.getMatidshow());
        tvPrice.setText("单价:   ¥"+items.getNowprice());
        if(null==items.getEcomment()){
            tvDetail.setText("");
        }else{
            tvDetail.setText(Html.fromHtml(items.getEcomment()));
        }
    }




    @OnClick({R.id.iv_back, R.id.tv_gm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_gm:
                showAddressDialog();
                break;
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
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("type", "spDetail");
                bundle.putSerializable("address", addresses.get(i));
                bundle.putString("num", numberButton.getNumber()+"");
                bundle.putString("matid", items.getMatid());
                bundle.putString("money", Double.parseDouble(new DecimalFormat("#0.00").format(
                        Double.parseDouble(items.getNowprice())
                                * numberButton.getNumber()).toString()) + "");
                bundle.putString("order_type",Constant.YYB_ZF);
                bundle.putString("img_url",items.getLogoimgurlshow());
                bundle.putString("shop_matid_show",items.getMatidshow());
                bundle.putString("now_price",items.getNowprice());
                bundle.putString("shop_matid",items.getMatid());
                intent.putExtras(bundle);
                intent.setClass(mContext, CommonZfActivity.class);
                startActivity(intent);
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
        addresses.clear();
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
