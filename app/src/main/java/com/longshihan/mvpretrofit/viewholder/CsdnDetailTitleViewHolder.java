package com.longshihan.mvpretrofit.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseViewHolder_common;
import com.longshihan.mvpretrofit.bean.CsdnDetailBean;

/**
 * @author Administrator
 * @time 2016-11-6 0006 下午 05:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnDetailTitleViewHolder extends BaseViewHolder_common<CsdnDetailBean> {
    private LinearLayout mLinearLayout;
    private TextView title;

    public CsdnDetailTitleViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.csdn_tag_detail_title);
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.csdn_tag_detail_linear);
    }

    @Override
    public void bindHolder(CsdnDetailBean csdnDetailBean) {
        title.setText(csdnDetailBean.getTitle());

    }

}
