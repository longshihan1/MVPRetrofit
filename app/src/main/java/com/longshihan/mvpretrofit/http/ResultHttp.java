package com.longshihan.mvpretrofit.http;

import com.longshihan.mvpretrofit.bean.JokeHttpResult;
import com.longshihan.mvpretrofit.utils.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @time 2016-10-29 0029 上午 11:13
 * @des 加入订阅和处理结果
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class ResultHttp {

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public static class HttpResultFunc<T> implements Func1<JokeHttpResult<T>, T> {
        @Override
        public T call(JokeHttpResult<T> httpResult) {
            if (httpResult.getError_code() != 0) {
                throw new ApiException(100);
            }
            return httpResult.getResult();
        }
    }


    //添加线程管理并订阅
    public static void toSubscribe(Observable o, Subscriber s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
