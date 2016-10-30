package com.longshihan.mvpretrofit.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.WeixinAdapter;
import com.longshihan.mvpretrofit.base.BaseFragmentPresenter;
import com.longshihan.mvpretrofit.bean.WeChatBean;
import com.longshihan.mvpretrofit.presenter.WeixinPresenter;
import com.longshihan.mvpretrofit.view.IView;

import butterknife.BindView;

/**
 * weixin fragment
 */
public class WeixinFragment extends BaseFragmentPresenter<IView, WeixinPresenter> implements IView<WeChatBean> {

    WeixinPresenter mWeixinPresenter;
    WeChatBean mWeChatBean;
    @BindView(R.id.weixin_recyclerview)
    RecyclerView mWeixinRecyclerview;

    WeixinAdapter mWeixinAdapter;

    public WeixinFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_weixin;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mPresenter.fetch();
        mWeChatBean=new WeChatBean();
        mWeixinAdapter=new WeixinAdapter(mContext,mWeChatBean);
    }

    @Override
    protected WeixinPresenter createPresenter() {
        if(mWeixinPresenter==null){
            mWeixinPresenter=new WeixinPresenter(mContext,1);
        }
        return mWeixinPresenter;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNews(WeChatBean weChatBean) {
        mWeixinAdapter.AddAll(weChatBean);
        mWeixinRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false));
        mWeixinRecyclerview.setAdapter(mWeixinAdapter);
    }

}
