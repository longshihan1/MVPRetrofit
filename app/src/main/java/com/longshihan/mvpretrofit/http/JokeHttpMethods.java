package com.longshihan.mvpretrofit.http;

import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;
import com.longshihan.mvpretrofit.server.JokeServer;
import com.longshihan.mvpretrofit.utils.Constants;
import com.longshihan.mvpretrofit.utils.MathCan;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;

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

    //构造方法私有
    private JokeHttpMethods() {
        jokeservice = getRetrofit(Constants.joke_baseurl, Constants.joke_key).create(JokeServer
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
                .map(new ResultHttp.HttpResultFunc<JokeRecentlyBean>());
        ResultHttp.toSubscribe(obsevable, subscriber);
    }
}
