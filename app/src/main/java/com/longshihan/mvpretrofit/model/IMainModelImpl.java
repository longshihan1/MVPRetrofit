package com.longshihan.mvpretrofit.model;

import android.content.Context;

import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;
import com.longshihan.mvpretrofit.http.JokeHttpMethods;
import com.longshihan.mvpretrofit.subscribers.ProgressSubscriber;
import com.longshihan.mvpretrofit.subscribers.SubscriberOnNextListener;

/**
 * @author Administrator
 * @time 2016/10/28 11:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class IMainModelImpl implements IMainModel {
    private Context mContext;

    public IMainModelImpl(Context context) {
        mContext = context;
    }

    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    public void loadjoke(final jokeOnLoadListener listener) {
        getTopMovieOnNext = new SubscriberOnNextListener<JokeRecentlyBean>() {
            @Override
            public void onNext(JokeRecentlyBean subjects) {
                if (listener != null) {
                    listener.complete(subjects);
                }
            }
        };
        JokeHttpMethods.getInstance().getjuhejokelist(
                new ProgressSubscriber<JokeRecentlyBean>(getTopMovieOnNext, mContext), 2, 10);
    }
}
