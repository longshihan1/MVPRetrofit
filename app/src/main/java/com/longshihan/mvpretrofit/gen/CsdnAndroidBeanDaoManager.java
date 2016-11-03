package com.longshihan.mvpretrofit.gen;

import com.longshihan.mvpretrofit.App;

/**
 * @author Administrator
 * @time 2016/11/3 16:29
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnAndroidBeanDaoManager {
    private static CsdnAndroidBeanDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public CsdnAndroidBeanDaoManager() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getInstance(),
                "notes-db", null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static CsdnAndroidBeanDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new CsdnAndroidBeanDaoManager();
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
