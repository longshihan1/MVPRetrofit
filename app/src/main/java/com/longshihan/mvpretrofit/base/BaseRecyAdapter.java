package com.longshihan.mvpretrofit.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * @author Administrator
 * @time 2016-10-30 0030 上午 11:10
 * @des Based的适配器
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public abstract class BaseRecyAdapter<V> extends RecyclerView.Adapter<BaseViewHolder> {
    private V v;
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    protected abstract void AddAll(V v);

   protected abstract void AddList(V v);
}
