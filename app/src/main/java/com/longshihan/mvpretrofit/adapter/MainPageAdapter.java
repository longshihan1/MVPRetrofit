package com.longshihan.mvpretrofit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Administrator
 * @time 2016/10/28 16:34
 * @des MainActivity的ViewPage的适配器
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class MainPageAdapter extends FragmentPagerAdapter {
    List<Fragment> mDatas;
    String[] titles = new String[]{"笑话", "新闻", "微信精选"};

    public MainPageAdapter(List<Fragment> lists, FragmentManager fm) {
        super(fm);
        this.mDatas = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
