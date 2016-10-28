package com.longshihan.mvpretrofit.model;

/**
 * @author Administrator
 * @time 2016/10/28 11:43
 * @des 新闻的model
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface INewsModel{
    /**
     * 加载数据
     *
     * @param listener 回调接口
     */
    void loadnews(NewsOnLoadListener listener);

    interface NewsOnLoadListener<T> {
        void complete(T t);
    }
}
