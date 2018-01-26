package com.ayj.chunmiao.activity.cmbz.insurance.bdcz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.activity.cmbz.insurance.clxx.VehicleInfoActivity;
import com.ayj.chunmiao.activity.shopping.AddAddressActivity;
import com.ayj.chunmiao.adapter.cmbz.bx.CzyZfAdapter;
import com.ayj.chunmiao.adapter.shopping.DialogAddressAdapter;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.bx.Union;
import com.ayj.chunmiao.bean.cmbz.UserPerson;
import com.ayj.chunmiao.bean.cmbz.VehicleBean;
import com.ayj.chunmiao.bean.eventbus.ClEvent;
import com.ayj.chunmiao.bean.shopping.Address;
import com.ayj.chunmiao.utils.ACache;
import com.ayj.chunmiao.utils.AliPay.PayResult;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.NumberButton;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/*
* 车载仪支付界面
* */
public class CzyZfActivity extends BaseActivity {

    List<VehicleBean.DataBean> selectList = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_akuan)
    TextView tvAkuan;
    @BindView(R.id.total_money)
    TextView totalMoney;

    @BindView(R.id.number_button)
    NumberButton number_button;

    Union.DataBean item;

    private Address.DataBean address;//收货地址

    private DialogAddressAdapter dialogAddressAdapter;

    private List<Address.DataBean> addresses = new ArrayList<>();

    UserPerson mUserPerson;

    @BindView(R.id.tv_shop_detail_address)
    TextView tv_shop_detail_address;

    @BindView(R.id.relativelayout_zq_detail_address)
    RelativeLayout relativelayout_zq_detail_address;

    CzyZfAdapter mCzyZfAdapter;

    @BindView(R.id.rlv)
    RecyclerView rlv;

    Union union;


    private static final int SDK_PAY_FLAG = 1;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showToast("支付成功");
                        finish();
                    } else {
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showToast("支付确认中");
                        } else {
                            // 判断resultStatus 为非"9000"则代表可能支付失败
                            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            showToast("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_czy_zf;
    }

    @Override
    public void initDatas() {
        getMatid();
    }

    @Override
    public void configViews() {
        EventBus.getDefault().register(this);
        rlv.setLayoutManager(new LinearLayoutManager(mContext));
        mUserPerson = (UserPerson) aCache.getAsObject(ACache.USER_PERSON_INFO_KEY);
        number_button.setOnWarnListener(new NumberButton.OnWarnListener() {
            @Override
            public void onWarningForInventory(int inventory) {
                Toast.makeText(mContext, "当前库存:" + inventory, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onWarningForBuyMax(int buyMax) {
                Toast.makeText(mContext, "超过最大购买数:" + buyMax, Toast.LENGTH_SHORT).show();
            }
        });
        number_button.setOnNumChangeListener(new NumberButton.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int num) {
                totalMoney.setText(number_button.getNumber()*Float.parseFloat(item.getNowprice())+"");
            }
        });
        getAddress();
    }

    private void getMatid() {
        mProgressHub.show();
        OkHttpUtils.post()
                .url(WebUtils.getRequestUrl(WebUtils.SP_DETAIL_URL))
                .addParams("json", "{\"key\":\"\",\"ordertype\":\"MEMBERORDERTYPE019\","
                        + "\"matclass1\":\"MEMBERORDERTYPE019004\",\"matclass2\":\"\",\"matname\":\"\","
                        + "\"startrow\":\"1\",\"endrow\":\"10\"}")
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
                                union = new Gson().fromJson(response, Union.class);
                                item = union.getData().get(0);
                                tvTitle.setText("北斗车载仪购买");
                                totalMoney.setText(item.getNowprice());
                                Picasso.with(mContext).load(item.getImgurlshow()).placeholder(R.mipmap.zhanweitu).error(
                                        R.mipmap.jiazaishibia).into(ivImage);
                                break;
                            default:
                                showToast(check.getMsg());
                                break;
                        }
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
                                        tv_shop_detail_address.setText(
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



    @OnClick({R.id.iv_back, R.id.relativelayout_zq_detail_address, R.id.rl, R.id.tv_order_payali})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.relativelayout_zq_detail_address:
                /*地址*/
                showAddressDialog();
                break;
            case R.id.rl:
                /*选择车辆*/
                if(number_button.getNumber()>selectList.size()){
                    Intent mIntent = new Intent(mContext,VehicleInfoActivity.class);
                    mIntent.putExtra("type","0");
                    mIntent.putExtra("count",number_button.getNumber()-selectList.size());
                    startActivity(mIntent);
                }else{
                    showToast("请购买更多导航仪");
                }
                break;
            case R.id.tv_order_payali:
                if(selectList.size()==number_button.getNumber()){
                    String[]  cardetail = new String[selectList.size()];
                    for(int i = 0;i<selectList.size();i++){
                        cardetail[i] = "{\\\"carsnid\\\":\\\"" + selectList.get(i).getSnid() + "\\\"}";
                    }
                    String obj = "{\"key\":\"\",\"msnid\":\""
                            + mUserPerson.getData().get(0).getSnid()
                            + "\",\"pwd\":\"" + mUserPerson.getData().get(0).getPassWord()+ "\",\"ordertype\":\"" +"MEMBERORDERTYPE019"+ "\",\"memberaddrid\":\"" + address.getSnid()
                            + "\",\"cardetail\":\"" + Arrays.toString(cardetail) + "\",\"smallmoneyuse\":\"" + "" + "\",\"membermoneybagusemoney\":\"" + ""
                            + "\",\"servicebooksnid\":\"" + "" + "\",\"memberticketdetail\":\""+ ""+ "\",\"membercarddetail\":\""+""
                            + "\",\"orderdetail\":\"[{\\\"pid\\\":\\\"" + union.getData().get(0).getMatid()+ "\\\",\\\"shopid\\\":\\\"" + ""  + "\\\",\\\"num\\\":\\\"" + number_button.getNumber() + "\\\"}]\"}";
                    postZf(mContext,obj,"2", Constant.HY,totalMoney.getText().toString(),"北斗车载仪");
                }else{
                    showToast("请购买与车辆相同数量的导航仪");
                }
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
                address = addresses.get(i);
                tv_shop_detail_address.setText(
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
    @Subscribe
    public void onEventMainThread(ClEvent event) {
        if("czyZf".equals(event.getmMsg())){
            selectList.addAll(event.getVehicleInfoList());
            if(null==mCzyZfAdapter){
                mCzyZfAdapter = new CzyZfAdapter(R.layout.czy_zf_item,selectList,new scClickListener());
                rlv.setAdapter(mCzyZfAdapter);
            }else{
                mCzyZfAdapter.setNewData(selectList);
                mCzyZfAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 删除监听
     */
    private class scClickListener implements View.OnClickListener {

        @Override
        public void onClick(final View v) {
            if (v.getTag() != null) {
                selectList.remove(Integer.parseInt(v.getTag()+""));
                mCzyZfAdapter.setNewData(selectList);
                mCzyZfAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void postZfb(final String payInfo) {
        super.postZfb(payInfo);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构建PayTask对象
                PayTask alipay = new PayTask((Activity) mContext);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
