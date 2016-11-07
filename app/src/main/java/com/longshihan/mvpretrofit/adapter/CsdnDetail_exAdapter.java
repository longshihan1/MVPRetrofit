package com.longshihan.mvpretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.bumptech.glide.Glide;
import com.longshihan.mvpretrofit.App;
import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.bean.CsdnDetailBean;
import com.longshihan.mvpretrofit.viewholder.CsdnDetailContentViewHolder;
import com.longshihan.mvpretrofit.viewholder.CsdnDetailTitleViewHolder;

import java.util.List;

/**
 * @author Administrator
 * @time 2016/11/7 11:39
 * @des tag分类详情的adapter，
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnDetail_exAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<CsdnDetailBean> mList;
    private TitleCLick mTitleCLick;

    public CsdnDetail_exAdapter(Context context, List<CsdnDetailBean> list) {
        this.context = context;
        mList = list;
    }

    public void setTitleCLick(TitleCLick titleCLick) {
        mTitleCLick = titleCLick;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mList.get(i).getData().size();
    }

    @Override
    public Object getGroup(int i) {
        return mList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mList.get(i).getData().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        CsdnDetailTitleViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.csdn_detail_tltlelayout,
                    null);
            holder = new CsdnDetailTitleViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CsdnDetailTitleViewHolder) convertView.getTag();
        }
        holder.title.setText("分类主题：" + mList.get(i).getTitle());
        holder.mLinearLayout.setOnClickListener(view -> mTitleCLick.onClick(mList.get(i).getLink
                ()));
        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        CsdnDetailContentViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.csdn_detail_contentlayout,
                    null);
            holder = new CsdnDetailContentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CsdnDetailContentViewHolder) convertView.getTag();
        }
        holder.title.setText(mList.get(i).getData().get(i1).getTitle());
        holder.content.setText(mList.get(i).getData().get(i1).getContent());
        holder.source.setText(mList.get(i).getData().get(i1).getSource());
        Glide.with(App.getInstance())
                .load(mList.get(i).getData().get(i1).getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.img);
        holder.mLinearLayout.setOnClickListener(view -> mTitleCLick.onClick(mList.get(i).getData
                ().get(i1).getLink()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public interface TitleCLick {
        void onClick(String s);
    }
}
