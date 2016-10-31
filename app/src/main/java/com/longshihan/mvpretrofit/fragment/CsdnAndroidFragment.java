package com.longshihan.mvpretrofit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseFragmentPresenter;
import com.longshihan.mvpretrofit.presenter.CsdnAnPresenter;
import com.longshihan.mvpretrofit.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CsdnAndroidFragment extends BaseFragmentPresenter<IView, CsdnAnPresenter> implements
        IView<String> {


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
    public void showNews(String s) {
        Log.d("tagggg", s);

    }
}
