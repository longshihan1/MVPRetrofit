package com.longshihan.mvpretrofit.presenter;

import android.content.Context;

import com.longshihan.mvpretrofit.base.BasePresenter;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.model.ICsdnAnModelImpl;
import com.longshihan.mvpretrofit.model.IModel;
import com.longshihan.mvpretrofit.view.IView;

import java.util.List;

/**
 * @author Administrator
 * @time 2016-10-31 0031 下午 10:11
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnAnPresenter extends BasePresenter<IView> {
    private IModel mModel;
    private Context mContext;

    public CsdnAnPresenter(Context context) {
        mModel = new ICsdnAnModelImpl(context);

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
