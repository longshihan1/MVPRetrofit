package com.longshihan.mvpretrofit.activity;

import android.os.Bundle;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseActivity;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.bean.CsdnDetailBean;
import com.longshihan.mvpretrofit.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * tag进来的详情界面
 */
public class CsdnDetailActivity extends BaseActivity {
    private CsdnAndroidBean mCsdnAndroidBean;
    Document url_detail;
    private List<CsdnDetailBean> mList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_csdn_detail;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mCsdnAndroidBean = (CsdnAndroidBean) getIntent().getSerializableExtra(Constants.DATA);
        mList = new ArrayList<>();
        try {
            url_detail = Jsoup.connect(mCsdnAndroidBean.getLink()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {

    }
}
