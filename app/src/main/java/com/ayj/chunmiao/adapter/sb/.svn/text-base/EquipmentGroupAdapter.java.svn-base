package com.ayj.chunmiao.adapter.sb;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.AyjSwApplication;
import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.Equipment;
import com.ayj.chunmiao.bean.EquipmentGroup;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.MD5Utils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.MyGridview;
import com.ayj.chunmiao.view.ProgressHUD;
import com.ayj.chunmiao.view.sweetalert.SweetAlertDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2017/2/8.
 */
public class EquipmentGroupAdapter extends BaseAdapter {

    private List<EquipmentGroup.DataBean> equipmentGroups;
    LayoutInflater inflater;
    Context context;
    private Handler mHandler;

    private String userid;
    private String shop_pwd;
    private String type;
    private String name = "";

    private DisplayMetrics dm;
    private int NUM = 4; // 每行显示个数
    private int LIEWIDTH;//每列宽度

    private EquipmentGridViewAdapter mEquipmentGridViewAdapter;

    public EquipmentGroupAdapter(Context context) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
        mEquipmentGridViewAdapter = new EquipmentGridViewAdapter(context);
        getScreenDen();
        LIEWIDTH = dm.widthPixels / NUM;
    }

    public void setList(List<EquipmentGroup.DataBean> equipmentGroups, String userid,
            String shop_pwd, String type, Handler mHandler) {
        this.equipmentGroups = equipmentGroups;
        this.userid = userid;
        this.shop_pwd = shop_pwd;
        this.type = type;
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        return equipmentGroups == null ? 0 : equipmentGroups.size();
    }

    @Override
    public Object getItem(int i) {
        return equipmentGroups.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_equipment_group, null);
            holder = new ViewHolder();

            holder.linearlayout_equipment_group = (LinearLayout) convertView.findViewById(
                    R.id.linearlayout_equipment_group);
            holder.tv_equipment_group = (TextView) convertView.findViewById(
                    R.id.tv_equipment_group);
            holder.iv_equipment_group = (ImageView) convertView.findViewById(
                    R.id.iv_equipment_group);
            holder.gridView_equipment_group = (MyGridview) convertView.findViewById(
                    R.id.gridView_equipment_group);
            holder.mEquipments = new ArrayList<>();

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_equipment_group.setText(equipmentGroups.get(position).getWorkgroupname());
        if (CommonUtils.isNetworkAvailable(context)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SB_GL_TWO_URL))
                    .addParams("json", "{\"key\":\"\",\"userid\":\"" + userid + "\","
                            + "\"pwd\":\"" + shop_pwd + "\",\"workgroupid\":\""
                            + equipmentGroups.get(position).getSnid() + "\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(context,R.string.TheNetIsException,Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onResponse(String response, int id) {

                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    holder.mEquipments = new ArrayList<>();
                                    mEquipmentGridViewAdapter = new EquipmentGridViewAdapter(
                                            context);
                                    Equipment equipments = new Gson().fromJson(response,
                                            Equipment.class);

                                    for (int i = 0; i < equipments.getData().size(); i++) {
                                        holder.mEquipments.add(equipments.getData().get(i));
                                    }

                                    mEquipmentGridViewAdapter = new EquipmentGridViewAdapter(
                                            context, equipments.getData().size(),
                                            holder.mEquipments,equipmentGroups.get(position).getSnid());
                                    holder.gridView_equipment_group.setAdapter(
                                            mEquipmentGridViewAdapter);
                                    holder.gridView_equipment_group.setColumnWidth(
                                            dm.widthPixels / NUM);
                                    holder.gridView_equipment_group.setStretchMode(
                                            GridView.NO_STRETCH);
                                    holder.gridView_equipment_group.setNumColumns(NUM);

                                    holder.gridView_equipment_group.setOnItemClickListener(
                                            new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent,
                                                        View view, final int position, long id) {
                                                    final Dialog dialog = new Dialog(context,
                                                            R.style.base_dialog);
                                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                    //定义一个dialog,并将自己定义的样式加入进去
                                                    //窗口布局
                                                    View dialogView = LayoutInflater.from(
                                                            context).inflate(
                                                            R.layout.sb_yz_passw_dialog, null);
                                                    dialog.setContentView(
                                                            dialogView); //将窗口布局加入到dialog中
                                                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                                    dialog.setCanceledOnTouchOutside(
                                                            true); //设置点击空白处不会消失
                                                    final EditText et =
                                                            (EditText) dialog.findViewById(R.id.et);
                                                    Button cancel_button =
                                                            (Button) dialog.findViewById(
                                                                    R.id.cancel_button);
                                                    Button confirm_button =
                                                            (Button) dialog.findViewById(
                                                                    R.id.confirm_button);
                                                    cancel_button.setOnClickListener(
                                                            new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    dialog.dismiss();
                                                                }
                                                            });
                                                    confirm_button.setOnClickListener(
                                                            new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    if (et.getText().toString()
                                                                            .trim().equals(

                                                                            "")) {
                                                                        Toast.makeText(context,
                                                                                "请输入密码",
                                                                                Toast.LENGTH_SHORT).show();

                                                                    } else {
                                                                        if (AyjSwApplication
                                                                                .getsInstance()
                                                                                .getUserInfo()
                                                                                .getData().get(
                                                                                        0)
                                                                                .getPassWord()
                                                                                .equals(
                                                                                        MD5Utils.getMD5String(et.getText()
                                                                                                .toString
                                                                                                        ().trim()))) {
                                                                            dialog.dismiss();
                                                                            showDialog(
                                                                                    holder.mEquipments.get(
                                                                                            position).getImei());
                                                                        } else {
                                                                            Toast.makeText(context,
                                                                                    "密码错误请重试",
                                                                                    Toast.LENGTH_SHORT).show();
                                                                            et.setText("");
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                    dialog.show();
                                                }

                                            });
                                    break;
                                default:
                                    Toast.makeText(context, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        }
        return convertView;
    }

    class ViewHolder {
        LinearLayout linearlayout_equipment_group;
        TextView tv_equipment_group;
        ImageView iv_equipment_group;
        MyGridview gridView_equipment_group;
        List<Equipment.DataBean> mEquipments;
        Boolean isSelected = true;
    }

    private int getNum(int size) {
        int num = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < size; i += 4) {
            num += 1;
        }

        return num;
    }

    private void changeWorkGroup(String workgroupid, String imei, final Dialog dialog) {
        if (CommonUtils.isNetworkAvailable(context)) {
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.CHANGE_SB_URL))
                    .addParams("json", "{\"key\":\"\",\"userid\":\"" + userid + "\","
                            + "\"pwd\":\"" + shop_pwd + "\",\"workgroupid\":\"" + workgroupid
                            + "\","
                            + "\"imei\":\"" + imei + "\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(context,R.string.TheNetIsException,Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response, Check.class);
                            switch (check.getErr()) {
                                case 0:
                                    SweetAlertDialog confirmDialog = CommonUtils.getSuccessDialog(
                                            context, "提示",
                                            "修改成功",
                                            new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(
                                                        SweetAlertDialog sweetAlertDialog) {
                                                    mHandler.sendEmptyMessage(1);
                                                    //隐藏dialog
                                                    sweetAlertDialog.dismiss();
                                                }
                                            });
                                    //显示dialog
                                    confirmDialog.show();
                                    break;
                                default:
                                    Toast.makeText(context, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        } else {
            Toast.makeText(context, "网络异常请重试", Toast.LENGTH_SHORT);
        }
    }

    private void getScreenDen() {

        dm = context.getResources().getDisplayMetrics();
    }

    private void showDialog(final String imei) {
        final Dialog dialog = new Dialog(context,
                R.style.base_dialog);    //定义一个dialog,并将自己定义的样式加入进去
        //窗口布局
        View dialogView = LayoutInflater.from(context).inflate(R.layout.choose_work_group, null);
        dialog.setContentView(dialogView); //将窗口布局加入到dialog中
        dialog.setCanceledOnTouchOutside(true); //设置点击空白处不会消失
        final ListView sc = (ListView) dialogView.findViewById(R.id.lv);

        String[] groupnames = new String[equipmentGroups.size()];
        final String[] groupids = new String[equipmentGroups.size()];
        for (int i = 0; i < groupnames.length; i++) {
            groupnames[i] = equipmentGroups.get(i).getWorkgroupname();
            groupids[i] = equipmentGroups.get(i).getSnid();
        }

        sc.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, groupnames));

        sc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                changeWorkGroup(groupids[pos], imei, dialog);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
