package com.longshihan.mvpretrofit.listener;

/**
 * @author Administrator
 * @time 2016-10-30 0030 上午 11:20
 * @des adater的添加数据接口
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface AdapterAdd<T> {
    void AddAll(T t);

    void AddList(T t);
}
