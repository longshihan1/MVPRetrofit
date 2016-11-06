package com.longshihan.mvpretrofit.utils;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jinliangshan on 2016/6/7.
 */
public class NetworkHelper {
    public static final String GET = "GET";
    public static final String URL = "http://publicobject.com/helloworld.txt";

    public static String getData(String method) {
        if(method.equals(GET))
            return getData(getRequestByGET());
        return null;
    }

    private static String getData(Request request) {
        String str = null;
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            if(response!=null && response.isSuccessful())
                str = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
            str = "getData: IOException";
        }finally {
            System.out.println(str);
            return str;
        }
    }

    public static Request getRequestByGET(){
        return new Request.Builder()
            .url(URL)
            .get()
            .build();
    }
}
