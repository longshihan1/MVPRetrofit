package com.longshihan.mvpretrofit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * @time 2016/10/28 11:03
 * @des 基础activity
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
       // AppManager.getAppManager().addActivity(this);
        initAllMembersView(savedInstanceState);
        initData();
    }


    /**
     * 创建视图布局动画
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
    protected void onDestroy() {
        super.onDestroy();
        //解除注解
        unbinder.unbind();
    }
}
