package com.longshihan.mvpretrofit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseFragmentPresenter;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.presenter.CsdnAnPresenter;
import com.longshihan.mvpretrofit.view.IView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CsdnAndroidFragment extends BaseFragmentPresenter<IView, CsdnAnPresenter> implements
        IView<List<CsdnAndroidBean>> {


    public CsdnAndroidFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_csdn_android;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mPresenter.fetch();
    }

    @Override
    protected CsdnAnPresenter createPresenter() {
        return new CsdnAnPresenter(mContext);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNews(List<CsdnAndroidBean> csdnAndroidBeen) {

    }

}
