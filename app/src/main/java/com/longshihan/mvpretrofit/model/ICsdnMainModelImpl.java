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
 * @time 2016/11/2 13:29
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class ICsdnMainModelImpl  implements IModel{
    private SubscriberOnNextListener getTopMovieOnNext;

    private Context mContext;

    public ICsdnMainModelImpl(Context context) {

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
        CsdnAnHttpMethods.getInstance().getMainjsouplist(new ProgressSubscriber<String>
                (getTopMovieOnNext, mContext));
    }

    private List<CsdnAndroidBean> DoingData(String subjects) {
        List<CsdnAndroidBean> list = new ArrayList<>();
        Document doc = Jsoup.parse(subjects);
        Elements elements = doc.select("li");//li元素的集合A-Z
        for (Element ele : elements) {
            Elements line2 = ele.select("div.overflow");
            Elements aeles=line2.select("a");//a元素的集合
            for (Element aele:aeles) {
                CsdnAndroidBean mbean = new CsdnAndroidBean();
            	mbean.setLink(aele.attr("href"));
                mbean.setTitle(aele.text());
                mbean.setTag(aele.tagName());
                list.add(mbean);
            }
        }
        return list;
    }
}
