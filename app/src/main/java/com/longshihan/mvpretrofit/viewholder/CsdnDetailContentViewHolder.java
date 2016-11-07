package com.longshihan.mvpretrofit.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.longshihan.mvpretrofit.R;

/**
 * @author Administrator
 * @time 2016-11-6 0006 下午 05:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnDetailContentViewHolder {
    public RelativeLayout mLinearLayout;
    public TextView title;
    public TextView content;
    public ImageView img;
    public TextView source;

    public CsdnDetailContentViewHolder(View itemView) {
        title = (TextView) itemView.findViewById(R.id.csdn_tag_detailcontent_title);
        mLinearLayout = (RelativeLayout) itemView.findViewById(R.id.csdn_tag_detailcontent_linear);
        content = (TextView) itemView.findViewById(R.id.csdn_tag_detailcontent_content);
        img = (ImageView) itemView.findViewById(R.id.csdn_tag_detailcontent_img);
        source = (TextView) itemView.findViewById(R.id.csdn_tag_detailcontent_source);
    }

   /* @Override
    public void bindHolder(CsdnMainBean csdnDetailBean) {
        title.setText(csdnDetailBean.getTitle());
        content.setText(csdnDetailBean.getContent());
        source.setText(csdnDetailBean.getSource());
        Glide.with(App.getInstance())
                .load(csdnDetailBean.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(img);

    }*/

}
