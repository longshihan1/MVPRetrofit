package com.longshihan.mvpretrofit.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.longshihan.mvpretrofit.R;

/**
 * @author Administrator
 * @time 2016/10/28 17:16
 * @des Joke的Viewholder
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class JokeItem_Viewholder extends RecyclerView.ViewHolder {
   public TextView mJokeTime;
   public TextView mJokeContent;

    public JokeItem_Viewholder(View itemView) {
        super(itemView);
        mJokeTime = (TextView) itemView.findViewById(R.id.joke_time);
        mJokeContent = (TextView) itemView.findViewById(R.id.joke_content);
    }
}
