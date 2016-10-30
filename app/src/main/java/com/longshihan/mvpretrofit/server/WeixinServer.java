package com.longshihan.mvpretrofit.server;

import com.longshihan.mvpretrofit.bean.JokeHttpResult;
import com.longshihan.mvpretrofit.bean.WeChatBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Administrator
 * @time 2016/10/28 13:00
 * @des 微信精选接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface WeixinServer {


    /**
     * 微信精选
     * @param page 当前页数，默认1
     * @param pagesize 每页返回条数，最大100，默认20
     * @return
     */
    @GET("query")
    Observable<JokeHttpResult<WeChatBean>> getWechat(@Query("pno") int page, @Query("ps") int pagesize);

}
