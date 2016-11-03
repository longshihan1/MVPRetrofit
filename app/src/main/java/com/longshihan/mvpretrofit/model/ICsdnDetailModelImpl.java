package com.longshihan.mvpretrofit.model;

import android.content.Context;

import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.http.CsdnAnHttpMethods;
import com.longshihan.mvpretrofit.subscribers.ProgressSubscriber;
import com.longshihan.mvpretrofit.subscribers.SubscriberOnNextListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @time 2016-11-3 0003 下午 09:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class ICsdnDetailModelImpl implements IModel {
    private SubscriberOnNextListener getTopMovieOnNext;

    private Context mContext;


    public ICsdnDetailModelImpl(Context context) {
        this.mContext = context;

    }

    @Override
    public void loadData(final IModel.onLoadDataListener listener) {
        getTopMovieOnNext = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String subjects) {//获取网页内容
                //处理数据
                List<CsdnAndroidBean> lists = DoingData(subjects);

                if (listener != null) {
                    listener.complete(lists);
                }
            }
        };
        CsdnAnHttpMethods.getInstance().getjsouplist(new ProgressSubscriber<String>
                (getTopMovieOnNext, mContext));

    }

    private List<CsdnAndroidBean> DoingData(String subjects) {
        List<CsdnAndroidBean> list = new ArrayList<>();
        Document doc = Jsoup.parse(subjects);
        Elements elements = doc.select("li.no_content");
        for (Element ele : elements) {
            CsdnAndroidBean mbean = new CsdnAndroidBean();
            Elements line2 = ele.select("div.line_list");
            mbean.setTitle(line2.select("a.tit_list").text());
            mbean.setLink(line2.select("a.tit_list").attr("href"));
            Elements line3 = ele.select("div.dwon_words");
            mbean.setImage(line3.select("a.user_p").first().getElementsByTag("img").attr("src"));
            mbean.setSource(line3.select("span.tag_source").get(2).getElementsByTag("a").text());
            list.add(mbean);
        }
        return list;
    }
}
