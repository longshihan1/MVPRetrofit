package com.longshihan.mvpretrofit.server;

import com.longshihan.mvpretrofit.bean.JokeHttpResult;
import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Administrator
 * @time 2016/10/28 13:00
 * @des 笑话接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface JokeServer {

    /**
     * @param page     当前页数,默认1
     * @param pagesize 每次返回条数,默认1,最大20
     * @param sort     类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time     时间戳（10位），如：1418816972
     * @return
     */
    @GET("content/list.from")
    Observable<JokeHttpResult<JokeRecentlyBean>> getRecentlyJoke(@Query("page") int page, @Query
            ("pagesize") int pagesize, @Query("sort") String sort, @Query("time") long time);

}
