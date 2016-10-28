package com.longshihan.mvpretrofit.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * @time 2016/10/28 16:15
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class BaseFragment extends Fragment {
    protected View mRootView;
    private Unbinder unbinder;

    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initAllMembersView(savedInstanceState);
        initData();
        return mRootView;

    }

    /**
     * 创建Fragment视图布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 界面起点
     *
     * @param savedInstanceState
     */
    protected abstract void initAllMembersView(Bundle savedInstanceState);

    /**
     * 数据绑定
     */
    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除注解
        unbinder.unbind();
    }
}
