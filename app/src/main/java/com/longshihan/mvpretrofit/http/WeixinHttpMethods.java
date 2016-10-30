package com.longshihan.mvpretrofit.http;

import com.longshihan.mvpretrofit.bean.WeChatBean;
import com.longshihan.mvpretrofit.server.WeixinServer;
import com.longshihan.mvpretrofit.utils.Constants;

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
public class WeixinHttpMethods extends RetrofitUtils {


    private Retrofit retrofit;
    public static WeixinServer weixinServer;

    //构造方法私有
    private WeixinHttpMethods() {
        weixinServer = getWeixinRetrofit(Constants.weixin_baseurl, Constants.weixin_key)
                .create(WeixinServer.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final WeixinHttpMethods INSTANCE = new WeixinHttpMethods();
    }

    //获取单例
    public static WeixinHttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 笑话接口
     * @param subscriber 由调用者传过来的观察者对象
     * @param page
     * @param pagesize
     */
    public void getjuheweixinlist(Subscriber<WeChatBean> subscriber, int page, int pagesize) {
        Observable obsevable = weixinServer.getWechat(page, pagesize)
                .map(new ResultHttp.HttpResultFunc<WeChatBean>());
        ResultHttp.toSubscribe(obsevable, subscriber);
    }
}
