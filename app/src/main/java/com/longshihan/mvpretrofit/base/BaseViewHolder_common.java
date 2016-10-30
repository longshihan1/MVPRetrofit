package com.longshihan.mvpretrofit.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * @author Administrator
 * @time 2016-10-29 0029 上午 11:33
 * @des Base RecyclerViewholder封装
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class BaseViewHolder_common<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder_common(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(T t);


}
