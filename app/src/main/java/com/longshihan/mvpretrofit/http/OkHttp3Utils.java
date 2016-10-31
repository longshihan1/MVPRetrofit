package com.longshihan.mvpretrofit.http;

import android.widget.Toast;

import com.longshihan.mvpretrofit.App;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.longshihan.mvpretrofit.utils.NetUtils.isNetworkReachable;


/**
 * 类名称：OkHttp3Utils
 * 类描述：封装一个OkHttp3的获取类
 */
public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    private static File cacheDirectory = new File(App.getInstance().getApplicationContext()
            .getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);


    /**
     * 聚合数据的okhttpPost 封装接口（封装了统一的key）
     *
     * @param key
     * @param baseurl
     * @return
     */
    public static OkHttpClient getJokeHttpClient(String key) {

        // if (null == mOkHttpClient) {

        //同样okhttp3后也使用build设计模式
        mOkHttpClient = new OkHttpClient.Builder()
                //设置一个自动管理cookies的管理器
                .cookieJar(new CookiesManager())
                //添加拦截器
                .addInterceptor(new MyIntercepter(key))
                //添加网络连接器
                //.addNetworkInterceptor(new CookiesInterceptor(App.getInstance()
                // .getApplicationContext()))
                //设置请求读写的超时时间
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build();
        // }
        return mOkHttpClient;
    }


    /**
     * 聚合数据的okhttpPost 封装接口（封装了统一的key）
     *
     * @param key
     * @param baseurl
     * @return
     */
    public static OkHttpClient getJsoupHttpClient() {

       // if (null == mOkHttpClient) {
            //同样okhttp3后也使用build设计模式
            mOkHttpClient = new OkHttpClient.Builder()
                    //设置一个自动管理cookies的管理器
                    .cookieJar(new CookiesManager())
                    //添加拦截器
                    //.addInterceptor(new MyIntercepter(key))
                    //添加网络连接器
                    //.addNetworkInterceptor(new CookiesInterceptor(App.getInstance()
                    // .getApplicationContext()))
                    //设置请求读写的超时时间
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
      //  }
        return mOkHttpClient;
    }

    /**
     * 拦截器,pi
     */
    private static class MyIntercepter implements Interceptor {
        private String key;

        public MyIntercepter(String key) {
            this.key = key;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //请求定制：添加请求头
            Request.Builder requestBuilder = request.newBuilder();
            if (!isNetworkReachable(App.getInstance().getApplicationContext())) {
                Toast.makeText(App.getInstance().getApplicationContext(), "暂无网络", Toast
                        .LENGTH_SHORT).show();
                requestBuilder.cacheControl(CacheControl.FORCE_CACHE);//无网络时只从缓存中读取
            }
            if (request.method().equals("POST")) {
                //请求体定制：统一添加token参数
                if (request.body() instanceof FormBody) {
                    FormBody.Builder newFormBody = new FormBody.Builder();
                    FormBody oidFormBody = (FormBody) request.body();
                    for (int i = 0; i < oidFormBody.size(); i++) {
                        newFormBody.addEncoded(oidFormBody.encodedName(i), oidFormBody
                                .encodedValue(i));

                    }
                    newFormBody.add("key", key);
                    requestBuilder.method(request.method(), newFormBody.build());
                }
            } else if (request.method().equals("GET")) {
                HttpUrl httpUrl = request.url()
                        .newBuilder()
                        .addQueryParameter("key", key)
                        .build();
                requestBuilder.url(httpUrl);
            }

            Response response = chain.proceed(requestBuilder.build());

            if (isNetworkReachable(App.getInstance().getApplicationContext())) {
                int maxAge = 60 * 60; // 有网络时 设置缓存超时时间1个小时
                response.newBuilder()
                        .removeHeader("Pragma")//兼容性
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }

            return response;
        }
    }


    /**
     * 自动管理Cookies
     */
    private static class CookiesManager implements CookieJar {
        private final PersistentCookieStore cookieStore = new PersistentCookieStore(App
                .getInstance().getApplicationContext());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }


}
