package com.longshihan.mvpretrofit.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.longshihan.mvpretrofit.BR;
import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.bean.JokeRecentlyBean;
import com.longshihan.mvpretrofit.viewholder.JokeItem_Viewholder;

/**
 * @author Administrator
 * @time 2016/10/28 16:44
 * @des Joke的数据适配器
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeItem_Viewholder> {

    private JokeRecentlyBean bean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public JokeAdapter(JokeRecentlyBean bean, Context context) {
        this.bean = bean;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void addAll(JokeRecentlyBean mjokeBean) {
        bean = mjokeBean;
        notifyDataSetChanged();
    }

    @Override
    public JokeItem_Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.jokeitem,
                parent,
                false);
        JokeItem_Viewholder jokeItem_viewholder = new JokeItem_Viewholder(binding.getRoot());
        jokeItem_viewholder.setBinding(binding);
      /*  JokeItem_Viewholder jokeItem_viewholder = new JokeItem_Viewholder(mLayoutInflater.inflate
                (R.layout.jokeitem, parent, false));*/
        return jokeItem_viewholder;
    }

    @Override
    public void onBindViewHolder(JokeItem_Viewholder holder, int position) {
        JokeRecentlyBean.DataBean mBean = bean.getData().get(position);
        holder.getBinding().setVariable(BR.joke, mBean);
        holder.getBinding().executePendingBindings();
       /* holder.mJokeTime.setText("更新时间：" + bean.getData().get(position).getUpdatetime());
        holder.mJokeContent.setText(bean.getData().get(position).getContent());*/
    }


    @Override
    public int getItemCount() {
        return bean.getData().size();
    }

}
