package com.longshihan.mvpretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;

import java.util.List;

/**
 * @author J 适配器
 */
public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<CsdnAndroidBean> list = null;
    private Context mContext;


    public SortAdapter(Context mContext, List<CsdnAndroidBean> list) {
        this.mContext = mContext;
        this.list = list;
    }



    public void updateListView(List<CsdnAndroidBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final CsdnAndroidBean mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_user_item_name);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }
        viewHolder.tvLetter.setText(mContent.getSortLetters());
        viewHolder.tvTitle.setText(mContent.getTitle());
        return view;

    }

    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
    }

    /**
     * 得到首字母的ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    public String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}