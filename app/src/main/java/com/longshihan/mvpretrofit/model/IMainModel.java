package com.longshihan.mvpretrofit.model;

import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;

/**
 * @author Administrator
 * @time 2016/10/28 11:43
 * @des MainActivity的model
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface IMainModel {
    /**
     * 加载数据
     *
     * @param listener 回调接口
     */
    void loadjoke(jokeOnLoadListener listener);

    interface jokeOnLoadListener {
        void complete(JokeRecentlyBean jokebean);
    }
}
