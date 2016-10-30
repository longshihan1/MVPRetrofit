package com.longshihan.mvpretrofit.model;

/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 07:56
 * @des Model的接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface IModel {
    /**
     * 加载数据
     *
     * @param listener 回调接口
     */
    void loadData(onLoadDataListener listener);

    interface onLoadDataListener<T> {
        void complete(T t);
    }
}
