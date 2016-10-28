package com.longshihan.mvpretrofit.model;

import android.util.Log;

import com.longshihan.mvpretrofit.bean.NewsBean;
import com.longshihan.mvpretrofit.http.JokeHttpMethods;

import rx.Subscriber;

/**
 * @author Administrator
 * @time 2016/10/28 11:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class INewsModelImpl implements INewsModel {
    private Subscriber<NewsBean> subscriber;
    private String type;

    public INewsModelImpl(String type) {
        this.type=type;

    }

    @Override
    public void loadnews(final NewsOnLoadListener listener) {
        //网络加载数据
        subscriber = new Subscriber<NewsBean>() {

            @Override
            public void onCompleted() {
                Log.e("INewsModelImpl", "获取到数据");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("INewsModelImpl", e.toString());
            }

            @Override
            public void onNext(NewsBean mbean) {
                if (listener != null) {
                    listener.complete(mbean);
                }
            }
        };
        JokeHttpMethods.getInstance().getNews(subscriber,"top");
    }
}
