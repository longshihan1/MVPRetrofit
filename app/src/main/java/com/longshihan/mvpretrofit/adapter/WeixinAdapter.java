package com.longshihan.mvpretrofit.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.longshihan.mvpretrofit.App;
import com.longshihan.mvpretrofit.BR;
import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseViewHolder;
import com.longshihan.mvpretrofit.bean.WeChatBean;
import com.longshihan.mvpretrofit.databinding.WeixinitemBinding;
import com.longshihan.mvpretrofit.listener.AdapterAdd;

import java.util.List;

/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 10:51
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class WeixinAdapter extends RecyclerView.Adapter<BaseViewHolder<WeixinitemBinding>>
        implements AdapterAdd<WeChatBean> {
    private Context mContext;
    private WeChatBean mWeChatBean;
    private List<WeChatBean.ListBean> mList;
    private WeixinitemBinding binding;

    public WeixinAdapter(Context context, WeChatBean weChatBean) {
        mContext = context;
        mWeChatBean = weChatBean;
        mList = mWeChatBean.getList();
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.weixinitem,
                parent,
                false);
        BaseViewHolder holder = new BaseViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        WeChatBean.ListBean mBean = mList.get(position);
        binding = (WeixinitemBinding) holder.getBinding();
        binding.setVariable(BR.weixin, mBean);
        Glide.clear(binding.weixinImage);
        Glide.with(App.getInstance())
                .load(mBean.getFirstImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(binding.weixinImage);
        binding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public void AddAll(WeChatBean weChatBean) {
        mList = weChatBean.getList();
        notifyDataSetChanged();
    }

    @Override
    public void AddList(WeChatBean weChatBean) {
        mList.addAll(weChatBean.getList());
        notifyDataSetChanged();

    }
}
