package com.longshihan.mvpretrofit.presenter;

import android.content.Context;

import com.longshihan.mvpretrofit.base.BasePresenter;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.model.ICsdnMainModelImpl;
import com.longshihan.mvpretrofit.model.IModel;
import com.longshihan.mvpretrofit.view.IView;

import java.util.List;

/**
 * @author Administrator
 * @time 2016/11/2 13:27
 * @des Csdn主页的present
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnMainPresent extends BasePresenter<IView>{
    private IModel mModel;
    private Context mContext;

    public CsdnMainPresent(Context context) {
        mModel = new ICsdnMainModelImpl(context);
    }

    public void fetch() {
        //加载进度条
        getView().showLoading();
        if (mModel != null) {
            mModel.loadData(new IModel.onLoadDataListener<List<CsdnAndroidBean>>() {
                @Override
                public void complete(List<CsdnAndroidBean> mjokebean) {
                    //得到数据，并给view显示
                    getView().showNews(mjokebean);
                }
            });
        }
    }
}
