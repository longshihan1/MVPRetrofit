package com.longshihan.mvpretrofit.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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

public class CsdnSortActivity extends BaseActivityPresenter<IView, CsdnMainPresent> implements
        IView<List<CsdnAndroidBean>> {


    @BindView(R.id.country_lvcountry)
    ListView sortListView;
    @BindView(R.id.top_layout)
    LinearLayout xuanfuLayout;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidrbar)
    SideBar sideBar;

    private SortAdapter adapter; // 排序的适配器

    private CharacterParser characterParser;
    private List<CsdnAndroidBean> SourceDateList; // 数据

    private PinyinComparator pinyinComparator;
    private int lastFirstVisibleItem = -1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_csdn_sort;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mPresenter.fetch();

    }



    @Override
    protected CsdnMainPresent createPresenter() {
        return new CsdnMainPresent(mContext);
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
                    Toast.makeText(CsdnSortActivity.this,s,Toast.LENGTH_SHORT);
                }

            }
        });
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //点击事件，添加跳转


            }

        });

        /**
         * 设置滚动监听， 实时跟新悬浮的字母的值
         */
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                int section = adapter.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = adapter
                        .getPositionForSection(section + 1);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                            xuanfuLayout
                                    .getLayoutParams();
                    params.topMargin = 0;
                    xuanfuLayout.setLayoutParams(params);
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {
                        int titleHeight = xuanfuLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)
                                xuanfuLayout.getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            xuanfuLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                xuanfuLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });
    }


}
