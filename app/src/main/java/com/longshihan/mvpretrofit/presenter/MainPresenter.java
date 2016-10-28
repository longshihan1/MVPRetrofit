package com.longshihan.mvpretrofit.presenter;

import com.longshihan.mvpretrofit.base.BasePresenter;
import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;
import com.longshihan.mvpretrofit.model.IMainModel;
import com.longshihan.mvpretrofit.model.IMainModelImpl;
import com.longshihan.mvpretrofit.view.IMainView;

/**
 * @author Administrator
 * @time 2016/10/28 11:43
 * @des MainActivity的persenter
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class MainPresenter extends BasePresenter<IMainView> {

    IMainModel mIMainModel = new IMainModelImpl();

    public void fetch() {
        //加载进度条
        getView().showLoading();
        if (mIMainModel != null) {
            mIMainModel.loadjoke(new IMainModel.jokeOnLoadListener() {
                @Override
                public void complete(JokeRecentlyBean mjokebean) {
                    //得到数据，并给view显示
                    getView().showJokes(mjokebean);
                }
            });
        }
    }

}
