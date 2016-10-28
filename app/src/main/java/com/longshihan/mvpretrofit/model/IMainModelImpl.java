package com.longshihan.mvpretrofit.model;

import android.util.Log;

import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;
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
public class IMainModelImpl implements IMainModel {
    private Subscriber<JokeRecentlyBean> subscriber;
    // private SubscriberOnNextListener getTopMovieOnNext;


    @Override
    public void loadjoke(final jokeOnLoadListener listener) {
        //网络加载数据
        subscriber = new Subscriber<JokeRecentlyBean>() {

            @Override
            public void onCompleted() {
                Log.e("IMainModelImpl", "获取到数据");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("IMainModelImpl", e.toString());
            }

            @Override
            public void onNext(JokeRecentlyBean mjokebean) {
                if (listener != null) {
                    listener.complete(mjokebean);
                }
            }
        };
        JokeHttpMethods.getInstance().getjuhejokelist(subscriber, 1, 20);
      /*  getTopMovieOnNext = new SubscriberOnNextListener<JokeRecentlyBean>() {
            @Override
            public void onNext(JokeRecentlyBean subjects) {
                if (listener != null) {
                    listener.complete(subjects);
                }
            }
        };
        JokeHttpMethods.getInstance().getjuhejokelist(
                new ProgressSubscriber<JokeRecentlyBean>(getTopMovieOnNext,RetrofitActivity.this)
                ,2,10);*/
    }
}
