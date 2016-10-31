package com.longshihan.mvpretrofit.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.MainPageAdapter;
import com.longshihan.mvpretrofit.base.BaseActivity;
import com.longshihan.mvpretrofit.fragment.CsdnAndroidFragment;
import com.longshihan.mvpretrofit.fragment.CsdnDesignFragment;
import com.longshihan.mvpretrofit.fragment.CsdnNewsFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

public class CsdnNewsActivity extends BaseActivity {
    @BindView(R.id.csdn_imageview)
    ImageView mCsdnImageview;
    @BindView(R.id.csdn_toolbar)
    Toolbar mCsdnToolbar;
    @BindView(R.id.csdn_tab)
    TabLayout mCsdnTab;
    @BindView(R.id.csdn_toolbar_layout)
    CollapsingToolbarLayout mCsdnToolbarLayout;
    @BindView(R.id.csdn_bar)
    AppBarLayout mCsdnBar;
    @BindView(R.id.csdn_viewpager)
    ViewPager mCsdnViewpager;

    private String url = "http://mobile.csdn.net/";

    @Override
    public int getLayoutId() {
        return R.layout.activity_csdn_news;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        setSupportActionBar(mCsdnToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCsdnToolbarLayout.setTitleEnabled(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //parseHtml();
                // parseEpub();
            }
        }).start();
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        CsdnAndroidFragment fragment1 = new CsdnAndroidFragment();
        CsdnNewsFragment fragment2 = new CsdnNewsFragment();
        CsdnDesignFragment fragment3 = new CsdnDesignFragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        MainPageAdapter adapterTest = new MainPageAdapter(list, getSupportFragmentManager());
        mCsdnViewpager.setAdapter(adapterTest);
        mCsdnTab.setupWithViewPager(mCsdnViewpager);
    }

    private void parseEpub() {

    }

    private void parseHtml() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div.unit");
            for (Element ele : elements) {
                String title = ele.getElementsByTag("h1").first().text();
                String href = ele.getElementsByTag("h1").first()
                        .getElementsByTag("a").attr("href");
                Log.d("info", title + ":" + href);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
