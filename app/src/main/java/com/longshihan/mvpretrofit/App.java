package com.longshihan.mvpretrofit;

import android.app.Application;

import com.longshihan.mvpretrofit.utils.Error.LocalFileHandler;

/**
 * @author Administrator
 * @time 2016/10/28 9:54
 * @des Application 关联 程序异常处理，图片处理。
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
    }


    public static App getInstance() {
        return instance;
    }


}
