package com.longshihan.mvpretrofit.utils;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @time 2016-11-5 0005 下午 10:11
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class NetworkObservable {
    public static Observable<String> getInstance(final String url, final String method, final Map<String, Object> params) {
        return Observable.create((Observable.OnSubscribe<String>) subscriber -> initObserable(subscriber, url, method, params))
                .subscribeOn(Schedulers.io());
    }

    private static void initObserable(Subscriber<? super String> subscriber, String url, String method, Map<String, Object> params) {
        subscribeData(subscriber, NetworkHelper.getData(NetworkHelper.GET));
    }

    public static Observable<String> getInstance() {
        return Observable.create((Observable.OnSubscribe<String>)
                subscriber -> subscribeData(subscriber, NetworkHelper.getData(NetworkHelper.GET))
        ).subscribeOn(Schedulers.io());  // 被观察的对象在 io 线程进行网络请求，也可以自己新开一个线程
    }

    // 取得网络数据后，回调 subscriber 的一些函数，在界面上显示这些数据
    private static void subscribeData(Subscriber<? super String> subscriber, String data) {
        if(data == null){
            subscriber.onError(new Throwable("data 为空"));
            subscriber.onCompleted();
        }else{
            subscriber.onNext(data);
            subscriber.onCompleted();
        }
    }
}
