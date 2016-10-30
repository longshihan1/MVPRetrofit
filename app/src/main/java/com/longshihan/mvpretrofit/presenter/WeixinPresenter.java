package com.longshihan.mvpretrofit.presenter;

import android.content.Context;

import com.longshihan.mvpretrofit.base.BasePresenter;
import com.longshihan.mvpretrofit.bean.WeChatBean;
import com.longshihan.mvpretrofit.model.IModel;
import com.longshihan.mvpretrofit.model.IWeixinModelImpl;
import com.longshihan.mvpretrofit.view.IView;

/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 08:03
 * @des 需要写的Persenter---需要改18的一个model实现类和一个Javabean的实现
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class WeixinPresenter extends BasePresenter<IView> {
    private int page;
    private IModel mModel;
    private Context mContext;

    public WeixinPresenter(Context context,int page) {
        this.page = page;
        mModel = new IWeixinModelImpl(context,page);
    }


    public void fetch() {
        //加载进度条
        getView().showLoading();
        if (mModel != null) {
            mModel.loadData(new IModel.onLoadDataListener<WeChatBean>() {
                @Override
                public void complete(WeChatBean mjokebean) {
                    //得到数据，并给view显示
                    getView().showNews(mjokebean);
                }
            });
        }
    }
}
