package com.longshihan.mvpretrofit.view;


/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 07:55
 * @des View的接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface IView<T> {
    /**
     * 显示进度条
     */
    void showLoading();

    /**
     *
     * @param
     */
    void showNews(T t);
}
