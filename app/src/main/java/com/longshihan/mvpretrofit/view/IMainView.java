package com.longshihan.mvpretrofit.view;

import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;

/**
 * @author Administrator
 * @time 2016/10/28 11:06
 * @des MainActivity的数据接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public interface IMainView {
    /**
     * 显示进度条
     */
    void showLoading();

    /**
     * 显示笑话列表
     *
     * @param mjokebean
     */
    void showJokes(JokeRecentlyBean mjokebean);
}
