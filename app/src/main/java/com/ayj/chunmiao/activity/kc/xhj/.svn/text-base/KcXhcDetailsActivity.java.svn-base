package com.ayj.chunmiao.activity.kc.xhj;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.activity.base.BaseActivity;
import com.ayj.chunmiao.adapter.main.MainYyDialogAdapter;
import com.ayj.chunmiao.bean.MdClBean;
import com.ayj.chunmiao.bean.YyQdBean;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Prints;
import com.lvrenyang.io.BTPrinting;
import com.lvrenyang.io.IOCallBack;
import com.lvrenyang.io.Label;
import com.lvrenyang.io.Pos;
import com.squareup.picasso.Picasso;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 库存details界面
* */
public class KcXhcDetailsActivity extends BaseActivity implements IOCallBack {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_sp_name)
    TextView tvSpName;
    @BindView(R.id.tv_foot_xd_count)
    TextView tvFootXdCount;
    @BindView(R.id.tv_foot_dw)
    TextView tvFootDw;
    @BindView(R.id.tv_foot_xd_price)
    TextView tvFootXdPrice;
    @BindView(R.id.iv_ewm)
    ImageView iv_ewm;
    @BindView(R.id.tv_login_ok)
    TextView tv_login_ok;
    MdClBean.DataBean item;

    String ewmStr;
    @BindView(R.id.tv_right_head)
    TextView tvRightHead;

    Pos mPos = new Pos();
    BTPrinting mBt = new BTPrinting();

    @BindView(R.id.linearlayoutdevices)
    LinearLayout linearlayoutdevices;
    Label mLabel = new Label();

    private BroadcastReceiver broadcastReceiver = null;
    private IntentFilter intentFilter = null;

    ExecutorService es = Executors.newScheduledThreadPool(30);

    public static void startActivity(Context context, MdClBean.DataBean item, String ordertype) {
        Intent intent = new Intent(context, KcXhcDetailsActivity.class);
        intent.putExtra("info", item);
        intent.putExtra("ordertype", ordertype);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_kc_xhc_details;
    }

    @Override
    public void initDatas() {
        tvTitle.setText("详情");
        tvRightHead.setVisibility(View.VISIBLE);
        tvRightHead.setText("搜索设备");
        tv_login_ok.setEnabled(false);
        /* 启动蓝牙 */
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (null != adapter) {
            if (!adapter.isEnabled()) {
                if (!adapter.enable()) {
                    finish();
                    return;
                }
            }
        }
        mLabel.Set(mBt);
        mBt.SetCallBack(this);
        initBroadcast();
    }

    @Override
    public void configViews() {
        item = (MdClBean.DataBean) getIntent().getSerializableExtra("info");
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
        if ("".equals(item.getImgurlcompressshow())) {
            iv.setImageResource(R.mipmap.banner_error);
        } else {
            Picasso.with(mContext).load(
                    item.getImgurlcompressshow()).placeholder(R.mipmap.banner_loading).error(
                    R.mipmap.banner_error).into(
                    iv);
        }
        tvSpName.setText(item.getMatidshow());
        tvFootXdCount.setText("存量:" + item.getNum());
        tvFootDw.setText("单位:" + item.getSaleunitidshow());
        tvFootXdPrice.setText("规格:" + item.getStandard());
        JSONObject obj = new JSONObject();
        try {
            obj.put("code", "1001");
            obj.put("shopid", AyjSwApplication.getsInstance().getUserInfo().getData().get(0).getShopid());
            obj.put("ordertype", getIntent().getStringExtra("ordertype"));
            obj.put("matid", item.getMatid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ewmStr = String.valueOf(obj);
        iv_ewm.setImageBitmap(CodeUtils.createImage(ewmStr, 200, 200, null));
    }

    @OnClick({R.id.iv_back, R.id.tv_right_head, R.id.tv_login_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_login_ok:
                /*打印*/
                if (tv_login_ok.isEnabled()) {
                    tv_login_ok.setEnabled(false);
                    es.submit(new TaskPrint(mLabel));
                } else {
                    showToast("请先搜索设备再进行打印");
                }
                break;
            case R.id.tv_right_head:
                /*搜索二维码*/
//                myOpertion = new BluetoothOperation(mContext, mHandler);
//                myOpertion.chooseDevice();
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                if (null == adapter) {
                    finish();
                    break;
                }

                if (!adapter.isEnabled()) {
                    if (adapter.enable()) {
                        while (!adapter.isEnabled())
                            ;
                        // Log.v(TAG, "Enable BluetoothAdapter");
                    } else {
                        finish();
                        break;
                    }
                }
                adapter.cancelDiscovery();
                linearlayoutdevices.removeAllViews();
                adapter.startDiscovery();
                mProgressHub.show();
                break;
        }
    }

    private void initBroadcast() {
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub
                String action = intent.getAction();
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    if (device == null)
                        return;
                    final String address = device.getAddress();
                    String name = device.getName();
                    if (name == null)
                        name = "BT";
                    else if (name.equals(address))
                        name = "BT";
                    Button button = new Button(context);

                    BluetoothClass btClass = device.getBluetoothClass();
                    int nClass = 0;
                    if (btClass.hasService(BluetoothClass.Service.AUDIO))
                        nClass |= BluetoothClass.Service.AUDIO;
                    else if (btClass.hasService(BluetoothClass.Service.CAPTURE))
                        nClass |= BluetoothClass.Service.CAPTURE;
                    else if (btClass.hasService(BluetoothClass.Service.INFORMATION))
                        nClass |= BluetoothClass.Service.INFORMATION;
                    else if (btClass.hasService(BluetoothClass.Service.LIMITED_DISCOVERABILITY))
                        nClass |= BluetoothClass.Service.LIMITED_DISCOVERABILITY;
                    else if (btClass.hasService(BluetoothClass.Service.NETWORKING))
                        nClass |= BluetoothClass.Service.NETWORKING;
                    else if (btClass.hasService(BluetoothClass.Service.OBJECT_TRANSFER))
                        nClass |= BluetoothClass.Service.OBJECT_TRANSFER;
                    else if (btClass.hasService(BluetoothClass.Service.POSITIONING))
                        nClass |= BluetoothClass.Service.POSITIONING;
                    else if (btClass.hasService(BluetoothClass.Service.RENDER))
                        nClass |= BluetoothClass.Service.RENDER;
                    else if (btClass.hasService(BluetoothClass.Service.TELEPHONY))
                        nClass |= BluetoothClass.Service.TELEPHONY;

                    nClass |= btClass.getDeviceClass();

                    String strClass = String.format("%06X", nClass);

                    button.setText(name + ": " + address + "(" + strClass + ")");

                    for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
                        Button btn = (Button) linearlayoutdevices.getChildAt(i);
                        if (btn.getText().equals(button.getText())) {
                            return;
                        }
                    }

                    button.setGravity(android.view.Gravity.CENTER_VERTICAL
                            | Gravity.LEFT);
                    button.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View arg0) {
                            // TODO Auto-generated method stub
                            Toast.makeText(mContext, "正在连接中...", Toast.LENGTH_SHORT).show();
                            tvRightHead.setEnabled(false);
                            linearlayoutdevices.setEnabled(false);
                            for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
                                Button btn = (Button) linearlayoutdevices.getChildAt(i);
                                btn.setEnabled(false);
                            }
                            tv_login_ok.setEnabled(false);
                            es.submit(new TaskOpen(mBt, address, mContext));
                        }
                    });
                    button.getBackground().setAlpha(100);
                    linearlayoutdevices.addView(button);
                } else if (BluetoothAdapter.ACTION_DISCOVERY_STARTED
                        .equals(action)) {
                    mProgressHub.dismiss();
                } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
                        .equals(action)) {
                    mProgressHub.dismiss();
                }

            }

        };
        intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void uninitBroadcast() {
        if (broadcastReceiver != null)
            unregisterReceiver(broadcastReceiver);
    }

    public class TaskOpen implements Runnable {
        BTPrinting bt = null;
        String address = null;
        Context context = null;

        public TaskOpen(BTPrinting bt, String address, Context context) {
            this.bt = bt;
            this.address = address;
            this.context = context;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            bt.Open(address, context);
        }
    }

    @Override
    public void OnOpen() {
        // TODO Auto-generated method stub
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                tv_login_ok.setEnabled(true);
                tvRightHead.setEnabled(false);
                linearlayoutdevices.setEnabled(false);
                for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
                    Button btn = (Button) linearlayoutdevices.getChildAt(i);
                    btn.setEnabled(false);
                }
                Toast.makeText(mContext, "连接成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OnOpenFailed() {
        // TODO Auto-generated method stub
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                tv_login_ok.setEnabled(false);
                tvRightHead.setEnabled(true);
                linearlayoutdevices.setEnabled(true);
                for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
                    Button btn = (Button) linearlayoutdevices.getChildAt(i);
                    btn.setEnabled(true);
                }
                Toast.makeText(mContext, "连接失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OnClose() {
        // TODO Auto-generated method stub
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                tv_login_ok.setEnabled(false);
                tvRightHead.setEnabled(true);
                linearlayoutdevices.setEnabled(true);
                for (int i = 0; i < linearlayoutdevices.getChildCount(); ++i) {
                    Button btn = (Button) linearlayoutdevices.getChildAt(i);
                    btn.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uninitBroadcast();
        es.submit(new TaskClose(mBt));
    }

    public class TaskClose implements Runnable {
        BTPrinting bt = null;

        public TaskClose(BTPrinting bt) {
            this.bt = bt;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            bt.Close();
        }

    }

    public class TaskPrint implements Runnable {
        Label label = null;

        public TaskPrint(Label label) {
            this.label = label;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub

            final boolean bPrintResult = Prints.PrintTicket(getApplicationContext(), label, 384, 400, 1,item.getMatidshow(),"规格"+item.getStandard()+" "+"价格"+item.getNowprice() ,ewmStr);
            final boolean bIsOpened = label.GetIO().IsOpened();

            KcXhcDetailsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Toast.makeText(mContext, bPrintResult ? "打印成功" : "打印失败", Toast.LENGTH_SHORT).show();
                    tv_login_ok.setEnabled(bIsOpened);
                }
            });
        }
    }
}
//    public class TaskPrint implements Runnable
//    {
//        Pos pos = null;
//
//        public TaskPrint(Pos pos)
//        {
//            this.pos = pos;
//        }
//
//        @Override
//        public void run() {
//            // TODO Auto-generated method stub
//
//           // final boolean bPrintResult = Prints.PrintTicket(getApplicationContext(), pos, 384, false, false, false, 1, 1,0,false);
//            final boolean bPrintResult = Prints.PrintTicket(getApplicationContext(),label,AppStart.nPrintWidth,AppStart.nPrintHeight, AppStart.nPrintCount);
//            final boolean bIsOpened = pos.GetIO().IsOpened();
//
//            KcXhcDetailsActivity.this.runOnUiThread(new Runnable(){
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    Toast.makeText(mContext, bPrintResult ?  "打印成功": "打印失败", Toast.LENGTH_SHORT).show();
//                    tv_login_ok.setEnabled(bIsOpened);
//                }
//            });
//
//        }
//    }
//}
