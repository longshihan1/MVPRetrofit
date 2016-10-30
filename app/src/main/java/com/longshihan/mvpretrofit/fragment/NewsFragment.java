package com.longshihan.mvpretrofit.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.NewsAdapter;
import com.longshihan.mvpretrofit.base.BaseFragmentPresenter;
import com.longshihan.mvpretrofit.bean.NewsBean;
import com.longshihan.mvpretrofit.presenter.NewsPresenter;
import com.longshihan.mvpretrofit.view.INewsView;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragmentPresenter<INewsView, NewsPresenter>
        implements INewsView<NewsBean> {

    private NewsPresenter mNewsPresenter;

    @BindView(R.id.news_recyclerview)
    RecyclerView mNewsRecyclerview;
    @BindView(R.id.news_floatbutton)
    FloatingActionButton mNewsFloatbutton;

    private NewsAdapter mNewsAdapter;
    private NewsBean mNewsBean;

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
        mNewsBean = new NewsBean();
    }

    @Override
    protected NewsPresenter createPresenter() {
        if (mNewsPresenter == null) {
            mNewsPresenter = new NewsPresenter("top");
        }
        return mNewsPresenter;
    }

    @Override
    protected void initData() {
        mNewsAdapter = new NewsAdapter(mContext, mNewsBean);
        mNewsFloatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNewsPresenter = new NewsPresenter("yule");
                mPresenter.fetch();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNews(NewsBean newsBean) {
        mNewsAdapter.addAll(newsBean);
        mNewsRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, false));
        mNewsRecyclerview.setAdapter(mNewsAdapter);
    }

}
