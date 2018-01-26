package com.ayj.chunmiao.adapter.kc;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.adapter.base.BaseQuickAdapter;
import com.ayj.chunmiao.adapter.base.BaseViewHolder;
import com.ayj.chunmiao.bean.MdXsBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/21.
 */
public class HcPostAdapter extends BaseQuickAdapter<MdXsBean.DataBean,BaseViewHolder> {

    private ChooseOnItemClickListener onItemClickListener;
    private Map<Integer, Integer> map = new HashMap<>();

    public Map<Integer, String> getChooseMap() {
        return chooseMap;
    }

    public void setChooseMap(Map<Integer, String> chooseMap) {
        this.chooseMap = chooseMap;
    }

    private Map<Integer, String> chooseMap = new HashMap<>();
    private EditText etView;
    private TextView tvChoose;

    public HcPostAdapter(int layout, List<MdXsBean.DataBean> data) {
        super(layout, data);
        initMap();
    }

    @Override
    protected void convert(final BaseViewHolder helper, MdXsBean.DataBean item) {
        helper.setText(R.id.tv_name,item.getMatidshow());

        if (helper.getLayoutPosition() % 2 == 0) {
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.main_dialog));
        } else {
            helper.getView(R.id.ll).setBackgroundColor(
                    mContext.getResources().getColor(R.color.white));
        }


        helper.getView(R.id.tv_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClickListener(v,helper.getLayoutPosition());
            }
        });

        etView = helper.getView(R.id.et_view);
        etView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty())
                    return;
                map.put(helper.getLayoutPosition(),Integer.valueOf(s.toString()));
            }
        });
        tvChoose = helper.getView(R.id.tv_choose);
        tvChoose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty())
                    return;
                chooseMap.put(helper.getLayoutPosition(),s.toString());
            }
        });
        etView.setText(String.valueOf(map.get(helper.getLayoutPosition())));
        tvChoose.setText(chooseMap.get(helper.getLayoutPosition()));
    }
    public void setOnitemClickListener(ChooseOnItemClickListener onitemClickListener){
        this.onItemClickListener = onitemClickListener;
    }
    public interface ChooseOnItemClickListener{
        void onItemClickListener(View v,int position);
    }
    private void initMap() {
        for(int i=0 ; i<mData.size() ; i++){
            map.put(i,0);
            chooseMap.put(i,"请选择");
        }
    }
    public Map getNumMap(){
        return map;
    }
}
