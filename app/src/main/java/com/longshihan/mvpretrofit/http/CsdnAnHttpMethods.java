package com.longshihan.mvpretrofit.http;

import com.longshihan.mvpretrofit.server.CsdnAnServer;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Administrator
 * @time 2016-10-31 0031 下午 10:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnAnHttpMethods extends RetrofitUtils {
    private Retrofit retrofit;
    public static CsdnAnServer csdnAnServer;

    //构造方法私有
    private CsdnAnHttpMethods() {
        csdnAnServer = getJsoupRetrofit()
                .create(CsdnAnServer.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final CsdnAnHttpMethods INSTANCE = new CsdnAnHttpMethods();
    }

    //获取单例
    public static CsdnAnHttpMethods getInstance() {
        return CsdnAnHttpMethods.SingletonHolder.INSTANCE;
    }

    /**
     * 网页解析接口
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param page
     * @param pagesize
     */
    public void getjsouplist(Subscriber<String> subscriber) {
        Observable obsevable = csdnAnServer.gettagNews();
        ResultHttp.toSubscribe(obsevable, subscriber);
    }
}
