package com.longshihan.mvpretrofit.http;

import com.longshihan.mvpretrofit.bean.JokeHttpResult;
import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;
import com.longshihan.mvpretrofit.bean.NewsBean;
import com.longshihan.mvpretrofit.server.JokeServer;
import com.longshihan.mvpretrofit.server.NewsServer;
import com.longshihan.mvpretrofit.utils.ApiException;
import com.longshihan.mvpretrofit.utils.Constants;
import com.longshihan.mvpretrofit.utils.MathCan;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Administrator
 * @time 2016/10/28 12:57
 * @des 笑话的网络入口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class JokeHttpMethods extends RetrofitUtils {


    private Retrofit retrofit;
    public static JokeServer jokeservice;
    public static NewsServer newsServer;

    //构造方法私有
    private JokeHttpMethods() {
        jokeservice = getRetrofit(Constants.joke_baseurl, Constants.joke_key).create(JokeServer
                .class);
        newsServer = getRetrofit(Constants.news_baseurl, Constants.news_key).create(NewsServer
                .class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final JokeHttpMethods INSTANCE = new JokeHttpMethods();
    }

    //获取单例
    public static JokeHttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 笑话接口
     * @param subscriber 由调用者传过来的观察者对象
     * @param page
     * @param pagesize
     */
    public void getjuhejokelist(Subscriber<JokeRecentlyBean> subscriber, int page, int pagesize) {
        Observable obsevable = jokeservice.getRecentlyJoke(page, pagesize, "desc", MathCan
                .SystimeSub4())
                .map(new HttpResultFunc<JokeRecentlyBean>());
        toSubscribe(obsevable, subscriber);
    }

    /**
     * 新闻接口
     * @param subscriber
     * @param type 分类
     */
    public void getNews(Subscriber<NewsBean> subscriber, String type) {
        Observable obsevable1 = newsServer.getRecentlyNews(type)
                .map(new HttpResultFunc<NewsBean>());
        toSubscribe(obsevable1, subscriber);
    }




    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<JokeHttpResult<T>, T> {
        @Override
        public T call(JokeHttpResult<T> httpResult) {
            if (httpResult.getError_code() != 0) {
                throw new ApiException(100);
            }
            return httpResult.getResult();
        }
    }


    //添加线程管理并订阅
    private static void toSubscribe(Observable o, Subscriber s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
