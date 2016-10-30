package com.longshihan.mvpretrofit.viewholder;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.longshihan.mvpretrofit.App;
import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseViewHolder_common;
import com.longshihan.mvpretrofit.bean.NewsBean;

import java.util.Date;

/**
 * @author Administrator
 * @time 2016-10-29 0029 上午 11:38
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class NewsItem_ViewHolder extends BaseViewHolder_common<NewsBean.DataBean> {
    private ImageView mImageView;
    private TextView title;
    private TextView source;
    private TextView time;

    public NewsItem_ViewHolder(View itemView) {
        super(itemView);
        mImageView = (ImageView) itemView.findViewById(R.id.newsitem_image);
        title = (TextView) itemView.findViewById(R.id.newsitem_title);
        source = (TextView) itemView.findViewById(R.id.newsitem_source);
        time = (TextView) itemView.findViewById(R.id.newsitem_time);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void bindHolder(NewsBean.DataBean newsBean) {
        Glide.clear(mImageView);
        Glide.with(App.getInstance())
                .load(newsBean.getThumbnail_pic_s())
                .placeholder(R.mipmap.ic_launcher)
                .into(mImageView);
        title.setText(newsBean.getTitle());
        source.setText(newsBean.getAuthor_name());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date d1 = new Date(System.currentTimeMillis());
            Date d2 = sdf.parse(newsBean.getDate());
            long s = d1.getTime() - d2.getTime() + 8 * 60 * 60 * 1000;
            long hour = s / 3600000;
            long day = hour == 0 ? 0 : hour / 24;
            if ((int) day > 1) {
                time.setText(day + "天前");
            } else {
                if ((int) hour > 1) {
                    time.setText(hour + "小时前");
                } else {
                    time.setText("刚刚");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            time.setText(newsBean.getDate());
        }

    }

}
