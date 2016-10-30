package com.longshihan.mvpretrofit.http;

import com.longshihan.mvpretrofit.bean.NewsBean;
import com.longshihan.mvpretrofit.server.NewsServer;
import com.longshihan.mvpretrofit.utils.Constants;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Administrator
 * @time 2016/10/28 12:57
 * @des 新闻的网络入口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class NewsHttpMethods extends RetrofitUtils {


    private Retrofit retrofit;
    public static NewsServer newsServer;

    //构造方法私有
    private NewsHttpMethods() {
        newsServer = getNewsRetrofit(Constants.news_baseurl, Constants.news_key).create(NewsServer
                .class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final NewsHttpMethods INSTANCE = new NewsHttpMethods();
    }

    //获取单例
    public static NewsHttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 新闻接口
     *
     * @param subscriber
     * @param type       分类
     */
    public void getNews(Subscriber<NewsBean> subscriber, String type) {
        Observable obsevable1 = newsServer.getRecentlyNews(type)
                .map(new ResultHttp.HttpResultFunc<NewsBean>());
        ResultHttp.toSubscribe(obsevable1, subscriber);
    }



}
