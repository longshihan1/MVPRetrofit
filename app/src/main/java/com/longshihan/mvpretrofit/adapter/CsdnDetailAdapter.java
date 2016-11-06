package com.longshihan.mvpretrofit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.longshihan.mvpretrofit.bean.CsdnDetailBean;

import java.util.List;

/**
 * @author Administrator
 * @time 2016-11-6 0006 上午 11:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<CsdnDetailBean> mList;

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public CsdnDetailAdapter(Context context, List<CsdnDetailBean> list) {
        this.context = context;
        mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        int size = 0;
        for (int i = 0; i < mList.size(); i++) {
            size += mList.get(i).getData().size() + 1;
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        // 异常情况处理
        if (null == mList || position < 0 || position > getItemCount()) {
            return ITEM_TYPE.ITEM1.ordinal();
        }
        int categroyFirstIndex = 0;

        for (CsdnDetailBean category : mList) {
            int size = category.getData().size();
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            if (categoryIndex == 0) {
                return ITEM_TYPE.ITEM2.ordinal();//标题
            }
            categroyFirstIndex += size;
        }
        return ITEM_TYPE.ITEM1.ordinal();//内容
    }
}
