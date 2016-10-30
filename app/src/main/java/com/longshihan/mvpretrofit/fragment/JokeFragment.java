package com.longshihan.mvpretrofit.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.JokeAdapter;
import com.longshihan.mvpretrofit.base.BaseFragmentPresenter;
import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;
import com.longshihan.mvpretrofit.presenter.MainPresenter;
import com.longshihan.mvpretrofit.view.IMainView;

import butterknife.BindView;

/**
 * jokeFragment,笑话
 */
public class JokeFragment extends BaseFragmentPresenter<IMainView, MainPresenter> implements
        IMainView {


    @BindView(R.id.joke_recyclerview)
    RecyclerView mJokeRecyclerview;

    private JokeAdapter mJokeAdapter;
    private JokeRecentlyBean mJokeRecentlyBean;

    public JokeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_joke;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mPresenter.fetch();
        mJokeRecentlyBean = new JokeRecentlyBean();

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(mContext);
    }

    @Override
    protected void initData() {
        mJokeAdapter = new JokeAdapter(mJokeRecentlyBean, mContext);
    }

    @Override
    public void showLoading() {
        Toast.makeText(mContext, "加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showJokes(JokeRecentlyBean mjokebean) {
        mJokeAdapter.addAll(mjokebean);
        mJokeRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager
                .VERTICAL, true));
        mJokeRecyclerview.setAdapter(mJokeAdapter);
    }

}
