package com.longshihan.mvpretrofit.viewholder;

import android.view.View;
import android.widget.LinearLayout;
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
public class CsdnDetailTitleViewHolder {
    public LinearLayout mLinearLayout;
    public TextView title;

    public CsdnDetailTitleViewHolder(View itemView) {
        title = (TextView) itemView.findViewById(R.id.csdn_tag_detail_title);
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.csdn_tag_detail_linear);
    }

}
