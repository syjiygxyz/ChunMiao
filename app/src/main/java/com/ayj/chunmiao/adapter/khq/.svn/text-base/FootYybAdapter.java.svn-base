package com.ayj.chunmiao.adapter.khq;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.bean.Check;
import com.ayj.chunmiao.bean.Classification;
import com.ayj.chunmiao.bean.FootZqShop;
import com.ayj.chunmiao.bean.ScTitleBean;
import com.ayj.chunmiao.bean.khq.FootYyb;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.WebUtils;
import com.ayj.chunmiao.view.MyGridview;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by zht-pc-09 on 2016/8/4.
 */
public class FootYybAdapter extends BaseAdapter {
    private List<ScTitleBean.DataBean> classification_yyb;
    LayoutInflater inflater;
    Context context;
    String shopid;
    String iscm;

    private DisplayMetrics dm;
    private int NUM = 4; // 每行显示个数
    private int LIEWIDTH;//每列宽度

    private YybGridViewAdapter mYybGridViewAdapter;

    public FootYybAdapter(Context context) {
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);
        this.context = context;
        mYybGridViewAdapter = new YybGridViewAdapter(context);
        getScreenDen();
        LIEWIDTH = dm.widthPixels / NUM;
    }

    public void setList(List<ScTitleBean.DataBean> classification_yyb, String shopid, String iscm) {
        this.classification_yyb = classification_yyb;
        this.shopid = shopid;
        this.iscm = iscm;
    }
    @Override
    public int getCount() {
        return classification_yyb == null ? 0 : classification_yyb.size();
    }

    @Override
    public Object getItem(int i) {
        return classification_yyb.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_yyb, null);
            holder = new ViewHolder();

            holder.tv_yyb_item = (TextView)convertView.findViewById(R.id.tv_yyb_item);
            holder.gridView_yyb = (MyGridview) convertView.findViewById(R.id.gridView_yyb);
            holder.footYybBeans = new ArrayList<>();

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_yyb_item.setText(classification_yyb.get(position).getParamname());

        if(CommonUtils.isNetworkAvailable(context)){
            OkHttpUtils.post()
                    .url(WebUtils.getRequestUrl(WebUtils.SP_DETAIL_URL))
                    .addParams("json","{\"key\":\"\",\"ordertype\":\"MEMBERORDERTYPE013\","
                            +"\"matclass1\":\""+classification_yyb.get(position).getDictid()+"\",\"matname\":\"\","
                            + "\"startrow\":\"1\",\"endrow\":\"99\"}")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(context,R.string.TheNetIsException,Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Check check = new Gson().fromJson(response,Check.class);
                            switch (check.getErr()){
                                case 0:
                                    holder.footYybBeans = new ArrayList<>();
                                    mYybGridViewAdapter = new YybGridViewAdapter(context);
                                    FootZqShop footYyb = new Gson().fromJson(response,FootZqShop.class);
                                    for(int i = 0;i<footYyb.getData().size();i++) {
                                        if (footYyb.getData().get(i).getMatclass1().equals(classification_yyb.get(position).getDictid())){
                                            holder.footYybBeans.add(footYyb.getData().get(i));
                                        }
                                    }
                                    mYybGridViewAdapter = new YybGridViewAdapter(context,footYyb.getData().size(),holder.footYybBeans,shopid,iscm);
                                    holder.gridView_yyb.setAdapter(mYybGridViewAdapter);
                                    holder.gridView_yyb.setColumnWidth(dm.widthPixels / NUM);
                                    holder.gridView_yyb.setStretchMode(GridView.NO_STRETCH);
                                    holder.gridView_yyb.setNumColumns(NUM);
                                    break;
                                default:
                                    Toast.makeText(context, check.getMsg(),
                                            Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
        }

        holder.tv_yyb_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.isSelected){
                    holder.gridView_yyb.setVisibility(View.VISIBLE);
                    holder.tv_yyb_item.setBackgroundResource(R.mipmap.cz_xiangshang);
                } else {
                    holder.gridView_yyb.setVisibility(View.GONE);
                    holder.tv_yyb_item.setBackgroundResource(R.mipmap.cz_xiangxia);
                }
                holder.isSelected = !holder.isSelected;
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv_yyb_item;
        MyGridview gridView_yyb;
        List<FootZqShop.DataBean> footYybBeans;
        Boolean isSelected = true;
    }

    private int getNum(int size){
        int num = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < size; i += 4) {
            num += 1;
        }

        return num;
    }

    private void getScreenDen() {
        dm = context.getResources().getDisplayMetrics();
    }

}
