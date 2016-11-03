package com.longshihan.mvpretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.SortAdapter;
import com.longshihan.mvpretrofit.base.BaseActivityPresenter;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.presenter.CsdnMainPresent;
import com.longshihan.mvpretrofit.utils.CharacterParser;
import com.longshihan.mvpretrofit.utils.PinyinComparator;
import com.longshihan.mvpretrofit.view.IView;
import com.longshihan.mvpretrofit.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

import static com.longshihan.mvpretrofit.R.id.toolbar;

public class CsdnSortActivity extends BaseActivityPresenter<IView, CsdnMainPresent> implements
        IView<List<CsdnAndroidBean>> {


    @BindView(R.id.country_lvcountry)
    ListView sortListView;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidrbar)
    SideBar sideBar;
    @BindView(toolbar)
    Toolbar mToolbar;

    private SortAdapter adapter; // 排序的适配器
    private CharacterParser characterParser;
    private List<CsdnAndroidBean> SourceDateList; // 数据
    private PinyinComparator pinyinComparator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_csdn_sort;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mPresenter.fetch();
        mToolbar = (Toolbar) findViewById(toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.csdn_tag);
        mToolbar.setOnMenuItemClickListener(onMenuItemClick);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_edit:

                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(CsdnSortActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

    @Override
    protected CsdnMainPresent createPresenter() {
        return new CsdnMainPresent(mContext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.csdn_tag, menu);
        return true;
    }

    @Override
    protected void initData() {
        SourceDateList = new ArrayList<>();
        pinyinComparator = new PinyinComparator();
        characterParser = CharacterParser.getInstance();
        sideBar.setTextView(dialog);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showNews(List<CsdnAndroidBean> csdnAndroidBeen) {
        for (int i = 0; i < csdnAndroidBeen.size(); i++) {
            String pinyin = characterParser.getSelling(csdnAndroidBeen.get(i).getTitle());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                csdnAndroidBeen.get(i).setSortLetters(sortString.toUpperCase());
            } else {
                csdnAndroidBeen.get(i).setSortLetters("#");
            }
        }
        Collections.sort(csdnAndroidBeen, pinyinComparator);
        SourceDateList = csdnAndroidBeen;
        adapter = new SortAdapter(this, csdnAndroidBeen);
        sortListView.setAdapter(adapter);
        initListener();
    }


    private void initListener() {
        /**
         * 为右边添加触摸事件
         */
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                    Toast.makeText(CsdnSortActivity.this, s, Toast.LENGTH_SHORT);
                }

            }
        });
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //点击事件，添加跳转
                Intent intent = new Intent(CsdnSortActivity.this, CsdnTagActivity.class);
                intent.putExtra("data", SourceDateList.get(position));
                startActivity(intent);


            }

        });
    }


}
