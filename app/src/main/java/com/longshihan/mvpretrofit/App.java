package com.longshihan.mvpretrofit;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.easemob.redpacketsdk.RedPacket;
import com.longshihan.mvpretrofit.gen.CsdnAndroidBeanDaoManager;
import com.longshihan.mvpretrofit.utils.DemoHelper;
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
    public static Context applicationContext;
    private static App instance;
    public static String currentUserNick = "";
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        applicationContext = this;
        /*EaseUI.getInstance().init(this,null);
        EMClient.getInstance().setDebugMode(true);*/
        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(this));
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        //red packet code : 初始化红包上下文，开启日志输出开关
        DemoHelper.getInstance().init(applicationContext);
        RedPacket.getInstance().initContext(this);
        RedPacket.getInstance().setDebugMode(true);
        initGreenDAo();

    }

    private void initGreenDAo() {
        CsdnAndroidBeanDaoManager.getInstance();
    }

    public static App getInstance() {
        return instance;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
