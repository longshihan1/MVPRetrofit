package com.longshihan.mvpretrofit.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Administrator
 * @time 2016/10/28 17:16
 * @des Joke的Viewholder
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class JokeItem_Viewholder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public JokeItem_Viewholder(View itemView) {
        super(itemView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.binding = binding;
    }
}
