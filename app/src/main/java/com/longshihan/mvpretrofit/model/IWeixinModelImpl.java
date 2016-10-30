package com.longshihan.mvpretrofit.model;

import android.content.Context;

import com.longshihan.mvpretrofit.bean.WeChatBean;
import com.longshihan.mvpretrofit.http.WeixinHttpMethods;
import com.longshihan.mvpretrofit.subscribers.ProgressSubscriber;
import com.longshihan.mvpretrofit.subscribers.SubscriberOnNextListener;

/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 07:59
 * @des 关联网络接口的model--微信精选
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class IWeixinModelImpl implements IModel {
    private SubscriberOnNextListener getTopMovieOnNext;
    private int page;
    private Context mContext;

    public IWeixinModelImpl(Context context, int page) {
        this.page = page;
        this.mContext = context;
    }

    @Override
    public void loadData(final onLoadDataListener listener) {
        getTopMovieOnNext = new SubscriberOnNextListener<WeChatBean>() {
            @Override
            public void onNext(WeChatBean subjects) {
                if (listener != null) {
                    listener.complete(subjects);
                }
            }
        };
        WeixinHttpMethods.getInstance().getjuheweixinlist(
                new ProgressSubscriber<WeChatBean>(getTopMovieOnNext, mContext), page, 20);

    }
}
