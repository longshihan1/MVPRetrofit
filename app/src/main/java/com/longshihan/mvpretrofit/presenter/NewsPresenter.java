package com.longshihan.mvpretrofit.presenter;

import com.longshihan.mvpretrofit.base.BasePresenter;
import com.longshihan.mvpretrofit.model.INewsModel;
import com.longshihan.mvpretrofit.model.INewsModelImpl;
import com.longshihan.mvpretrofit.view.INewsView;

/**
 * @author Administrator
 * @time 2016/10/28 11:43
 * @des MainActivity的persenter
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class NewsPresenter extends BasePresenter<INewsView> {

    private String type;

    public NewsPresenter(String type) {
        this.type = type;
    }

    INewsModel mIMainModel = new INewsModelImpl(type);

    public void fetch() {
        //加载进度条
        getView().showLoading();
        if (mIMainModel != null) {
            mIMainModel.loadnews(new INewsModel.NewsOnLoadListener() {
                @Override
                public void complete(Object o) {
                    //得到数据，并给view显示
                    getView().showNews(o);
                }
            });
        }
    }

}
