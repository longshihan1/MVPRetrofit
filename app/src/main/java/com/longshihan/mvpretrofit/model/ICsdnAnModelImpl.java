package com.longshihan.mvpretrofit.model;

import android.content.Context;
import android.util.Log;

import com.longshihan.mvpretrofit.http.CsdnAnHttpMethods;
import com.longshihan.mvpretrofit.subscribers.ProgressSubscriber;
import com.longshihan.mvpretrofit.subscribers.SubscriberOnNextListener;

/**
 * @author Administrator
 * @time 2016-10-31 0031 下午 09:56
 * @des csdn android 分类model
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class ICsdnAnModelImpl implements IModel {
    private SubscriberOnNextListener getTopMovieOnNext;

    private Context mContext;

    public ICsdnAnModelImpl(Context context) {

        this.mContext = context;
    }

    @Override
    public void loadData(final onLoadDataListener listener) {
        getTopMovieOnNext = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String subjects) {//获取网页内容
                Log.d("tag",subjects);
                if (listener != null) {
                    listener.complete(subjects);
                }
            }
        };
        CsdnAnHttpMethods.getInstance().getjsouplist(new ProgressSubscriber<String>
                (getTopMovieOnNext, mContext));

    }
}
