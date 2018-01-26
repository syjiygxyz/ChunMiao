package com.ayj.chunmiao.fragment.czjr;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ayj.chunmiao.R;
import com.ayj.chunmiao.fragment.base.LazyFragment;
import com.ayj.chunmiao.utils.CommonUtils;
import com.ayj.chunmiao.utils.Constant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/1.
 */
public class CzAddHykFragment extends LazyFragment{

    @BindView(R.id.rlv_a_card)
    RecyclerView rlvACard;
    @BindView(R.id.rlv_b_card)
    RecyclerView rlvBCard;

    @Override
    public void fetchData() {
        rlvACard.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rlvBCard.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        CommonUtils.getCard(getActivity(), Constant.CARD_A_KL,rlvACard,"康乐会员A卡",R.mipmap.hyk_zs);
        CommonUtils.getCard(getActivity(),Constant.CARD_B_KL,rlvBCard,"康乐会员B卡",R.mipmap.hyk_zs);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cz_add_hyk;
    }
}
