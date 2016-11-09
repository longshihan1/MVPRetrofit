package com.longshihan.mvpretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ExpandableListView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.CsdnDetail_exAdapter;
import com.longshihan.mvpretrofit.base.BaseActivity;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.bean.CsdnDetailBean;
import com.longshihan.mvpretrofit.bean.CsdnMainBean;
import com.longshihan.mvpretrofit.utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * tag进来的详情界面
 */
public class CsdnDetailActivity extends BaseActivity implements CsdnDetail_exAdapter.TitleCLick {
    @BindView(R.id.csdn_expabd)
    ExpandableListView mCsdnExpabd;
    private CsdnAndroidBean mCsdnAndroidBean;
    Document url_detail;
    private List<CsdnDetailBean> mList;
    private String data_str;


    @Override
    public int getLayoutId() {
        return R.layout.activity_csdn_detail;
    }

    @Override
    public void setLayouut(Bundle savedInstanceState) {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mCsdnAndroidBean = (CsdnAndroidBean) getIntent().getSerializableExtra(Constants.DATA);
        mList = new ArrayList<>();
        new Thread(() -> {
            try {
                url_detail = Jsoup.connect(mCsdnAndroidBean.getLink()).get();
                Message msg = mhandler.obtainMessage();
                msg.what = 0;
                msg.obj = url_detail;
                mhandler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Document url_detail1 = (Document) msg.obj;
                Elements tag_news = url_detail1.select("div.tag_news");
                insertElements(tag_news, "a.tag_news");
                Elements tag_topic = url_detail1.select("div.tag_topic");
                insertElements(tag_topic, "a.tag_topic");
                Elements tag_blog = url_detail1.select("div.tag_blog");
                insertElements(tag_blog, "a.tag_blog");
                Elements tag_download = url_detail1.select("div.tag_download");
                insertElements(tag_download, "a.tag_download");
                initListener(mList);
            }
        }
    };


    private void insertElements(Elements tag_news, String tagNews) {
        CsdnDetailBean mBean = new CsdnDetailBean();
        mBean.setTitle(tag_news.select("p.tag_p3").select(tagNews).text());
        mBean.setLink(tag_news.select("p.tag_p3").select(tagNews).attr("href"));
        Elements eles = tag_news.select("li");
        List<CsdnMainBean> lists = new ArrayList<>();
        for (Element ele : eles) {
            CsdnMainBean mmBean = new CsdnMainBean();
            mmBean.setTitle(ele.select("div.line_list").first().select("a.tit_list").text());
            mmBean.setLink(ele.select("div.line_list").first().select("a.tit_list").attr("href"));
            mmBean.setImage(ele.select("div.line_list").select("a.user_p").first().select("img")
                    .attr("src"));
            mmBean.setSource(ele.select("div.line_list").select("a.user_name").text());
            mmBean.setContent(ele.select("div.line_list").select("span.tag_summary").text());
            lists.add(mmBean);
        }
        mBean.setData(lists);
        mList.add(mBean);
    }

    @Override
    protected void initData() {

    }

    private void initListener(List<CsdnDetailBean> list) {
        CsdnDetail_exAdapter adapter = new CsdnDetail_exAdapter(this, list);
        adapter.setTitleCLick(this);
        mCsdnExpabd.setAdapter(adapter);
        for (int i = 0; i < mList.size(); i++) {
            mCsdnExpabd.expandGroup(i);
        }
    }

    @Override
    public void onClick(String s) {
        Intent intent = new Intent(this, CsdnDetailSortActivity.class);
        intent.putExtra("url", s);
        startActivity(intent);
    }
}
