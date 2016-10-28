package com.longshihan.mvpretrofit.view;

import com.longshihan.mvpretrofit.bean.NewsBean;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * @author Administrator
 * @time 2016/10/28 11:06
 * @des 新闻的数据接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface INewsView<T> {
    /**
     * 显示进度条
     */
    void showLoading();

    /**
     * 显示笑话列表
     *
     * @param mbean
     */
    void showNews(T t);
}
