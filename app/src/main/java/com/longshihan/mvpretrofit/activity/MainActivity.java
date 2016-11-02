package com.longshihan.mvpretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.MainPageAdapter;
import com.longshihan.mvpretrofit.base.BaseActivity;
import com.longshihan.mvpretrofit.fragment.JokeFragment;
import com.longshihan.mvpretrofit.fragment.NewsFragment;
import com.longshihan.mvpretrofit.fragment.WeixinFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_imageview)
    ImageView mMainImageview;
    @BindView(R.id.main_toolbar)
    Toolbar mMainToolbar;
    @BindView(R.id.main_tab)
    TabLayout mTabLayout;
    @BindView(R.id.main_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.main_bar)
    AppBarLayout mMainBar;
    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;

    private Intent mIntent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        setSupportActionBar(mMainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitleEnabled(false);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        JokeFragment fragment1 = new JokeFragment();
        NewsFragment fragment2 = new NewsFragment();
        WeixinFragment fragment3 = new WeixinFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        MainPageAdapter adapterTest = new MainPageAdapter(list, getSupportFragmentManager());
        mViewPager.setAdapter(adapterTest);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.action_juhe:
                break;
            case R.id.action_zhihu:

                break;
            case R.id.action_douban:

                break;
            case R.id.action_csdn:

              mIntent = new Intent(this, CsdnSortActivity.class);
                startActivity(mIntent);
                break;
            case R.id.action_mayun:

                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
