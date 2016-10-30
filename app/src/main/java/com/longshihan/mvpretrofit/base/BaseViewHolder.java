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
public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private T binding;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public T getBinding() {
        return binding;
    }

    public void setBinding(T binding) {
        this.binding = binding;
    }



}
