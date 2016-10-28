package com.longshihan.mvpretrofit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseFragmentPresenter;
import com.longshihan.mvpretrofit.bean.NewsBean;
import com.longshihan.mvpretrofit.presenter.NewsPresenter;
import com.longshihan.mvpretrofit.view.INewsView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragmentPresenter<INewsView, NewsPresenter>
        implements INewsView<NewsBean> {


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mPresenter.fetch();
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter("top");
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNews(NewsBean newsBean) {
        Log.e("news", newsBean.getData().get(0).getTitle());
    }
}
