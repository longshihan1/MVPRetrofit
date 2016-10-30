package com.longshihan.mvpretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.bean.NewsBean;
import com.longshihan.mvpretrofit.viewholder.NewsItem_ViewHolder;

/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 12:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsItem_ViewHolder> {
    private Context mContext;
    private NewsBean mNewsBean;
    private LayoutInflater mLayoutInflater;

    public NewsAdapter(Context context, NewsBean newsBean) {
        mContext = context;
        mNewsBean = newsBean;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NewsItem_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsItem_ViewHolder holder = new NewsItem_ViewHolder(mLayoutInflater.inflate(R.layout
                .newsitem, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsItem_ViewHolder holder, int position) {
        holder.bindHolder(mNewsBean.getData().get(position));
    }

    @Override
    public int getItemCount() {
        return mNewsBean.getData().size();
    }

    public void addAll(NewsBean newsBean) {
        mNewsBean = newsBean;
        notifyDataSetChanged();
    }
}
