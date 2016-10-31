package com.longshihan.mvpretrofit.server;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Administrator
 * @time 2016-10-31 0031 下午 10:01
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface CsdnAnServer {
    @GET("android/news")
    Observable<String> gettagNews();

}
