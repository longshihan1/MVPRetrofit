package com.longshihan.mvpretrofit;

import android.app.Application;

import com.longshihan.mvpretrofit.gen.CsdnAndroidBeanDaoManager;
import com.longshihan.mvpretrofit.utils.Error.LocalFileHandler;
import com.squareup.leakcanary.LeakCanary;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        initGreenDAo();

    }

    private void initGreenDAo() {
        CsdnAndroidBeanDaoManager.getInstance();
    }

    public static App getInstance() {
        return instance;
    }


}
