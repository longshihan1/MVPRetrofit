package com.longshihan.mvpretrofit.utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author Administrator
 * @time 2016/10/28 10:06
 * @des 管理activity的集合
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class AppManager {
    private static final String LOG_TAG = AppManager.class.getSimpleName();
    private static Stack<Activity> activityStack;
    private static Stack<Service> serviceStack;
    private static AppManager mInstance;

    public AppManager() {
    }

    public static synchronized AppManager getAppManager() {
        return mInstance == null ? (mInstance = new AppManager()) : mInstance;
    }

    public synchronized void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack();
        }

        activityStack.add(activity);
    }

    public synchronized void addService(Service service) {
        if (serviceStack == null) {
            serviceStack = new Stack();
        }

        serviceStack.add(service);
    }

    public Activity currentActivity() {
        return (Activity) activityStack.lastElement();
    }

    public void finishActivity() {
        if (!activityStack.isEmpty()) {
            Activity activity = (Activity) activityStack.lastElement();
            this.finishActivity(activity);
        }
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }

    }

    public void finishActivity(Class<?> cls) {
        ArrayList removeList = new ArrayList();
        Iterator i$ = activityStack.iterator();

        Activity activity;
        while (i$.hasNext()) {
            activity = (Activity) i$.next();
            if (activity.getClass().equals(cls)) {
                removeList.add(activity);
            }
        }

        activityStack.removeAll(removeList);
        i$ = removeList.iterator();

        while (i$.hasNext()) {
            activity = (Activity) i$.next();
            if (activity != null) {
                activity.finish();
                activity = null;
            }
        }

    }

    public void finishWithOut(Activity withOutActivity) {
        ArrayList removeList = new ArrayList();
        Iterator i$ = activityStack.iterator();

        Activity activity;
        while (i$.hasNext()) {
            activity = (Activity) i$.next();
            if (activity != withOutActivity) {
                removeList.add(activity);
            }
        }

        activityStack.removeAll(removeList);
        i$ = removeList.iterator();

        while (i$.hasNext()) {
            activity = (Activity) i$.next();
            if (activity != null) {
                activity.finish();
                activity = null;
            }
        }

    }

    public void finishAllActivity() {
        if (activityStack != null) {
            int i = 0;

            for (int size = activityStack.size(); i < size; ++i) {
                if (activityStack.get(i) != null) {
                    ((Activity) activityStack.get(i)).finish();
                }
            }

            activityStack.clear();
        }
    }

    public void finishAllService() {
        if (serviceStack != null) {
            int i = 0;

            for (int size = serviceStack.size(); i < size; ++i) {
                if (serviceStack.get(i) != null) {
                    ((Service) serviceStack.get(i)).stopSelf();
                }
            }

            serviceStack.clear();
        }
    }

    public void finishAllNotification(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context
                .getApplicationContext().getSystemService("notification");
        mNotificationManager.cancelAll();
    }

    public void redirectLogin(Context context, Class clazz) {
        ArrayList removeList = new ArrayList();
        Iterator i$ = activityStack.iterator();

        Activity activity;
        while (i$.hasNext()) {
            activity = (Activity) i$.next();
            if (!clazz.isInstance(activity)) {
                removeList.add(activity);
            }
        }

        activityStack.removeAll(removeList);
        if (activityStack.size() == 0) {
            context.startActivity(new Intent(context, clazz));
        }

        i$ = removeList.iterator();

        while (i$.hasNext()) {
            activity = (Activity) i$.next();
            if (activity != null) {
                activity.finish();
                activity = null;
            }
        }

    }

    public void AppExit(Context context) {
        try {
            this.finishAllNotification(context);
            this.finishAllActivity();
            this.finishAllService();
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception var3) {
            Log.e(LOG_TAG, var3.toString());
        }

    }
}
