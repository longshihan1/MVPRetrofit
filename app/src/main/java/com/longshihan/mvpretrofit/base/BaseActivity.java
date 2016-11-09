package com.longshihan.mvpretrofit.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.hyphenate.easeui.controller.EaseUI;
import com.longshihan.mvpretrofit.utils.AppManager;
import com.umeng.analytics.MobclickAgent;

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
    protected InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayouut(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        AppManager.getAppManager().addActivity(this);
        initAllMembersView(savedInstanceState);
        if(!isTaskRoot()){
            Intent intent = getIntent();
            String action = intent.getAction();
            if(intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)){
                finish();
                return;
            }
        }

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initData();
    }


    /**
     * 创建视图布局动画
     *
     * @return
     */
    public abstract int getLayoutId();

    public abstract void setLayouut(Bundle savedInstanceState);
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
    protected void onStart() {
        super.onStart();
        // umeng
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        EaseUI.getInstance().getNotifier().reset();
    }

    protected void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        //解除注解
        unbinder.unbind();
    }
    /**
     * back
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
