package com.longshihan.mvpretrofit.server;

import com.longshihan.mvpretrofit.bean.JokeHttpResult;
import com.longshihan.mvpretrofit.bean.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Administrator
 * @time 2016/10/28 13:00
 * @des 新闻接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface NewsServer {

    /**
     * @param type 类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     * @return
     */
    @GET("index")
    Observable<JokeHttpResult<NewsBean>> getRecentlyNews(@Query("type") String type);

}
