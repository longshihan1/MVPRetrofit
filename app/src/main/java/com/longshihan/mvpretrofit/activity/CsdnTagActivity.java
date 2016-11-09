package com.longshihan.mvpretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.SortAdapter;
import com.longshihan.mvpretrofit.base.BaseActivity;
import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.gen.CsdnAndroidBeanDao;
import com.longshihan.mvpretrofit.gen.CsdnAndroidBeanDaoManager;
import com.longshihan.mvpretrofit.utils.Constants;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import butterknife.BindView;

public class CsdnTagActivity extends BaseActivity {


    @BindView(R.id.tag_searchedit)
    EditText mTagSearchedit;
    @BindView(R.id.tag_searchlist)
    ListView mTagSearchlist;

    private SortAdapter adapter; // 排序的适配器

    @Override
    public int getLayoutId() {
        return R.layout.activity_csdn_tag;
    }

    @Override
    public void setLayouut(Bundle savedInstanceState) {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mTagSearchedit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() &&
                        KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    initListener(mTagSearchedit.getText().toString());
                }
                return false;
            }
        });
    }

    private void initListener(String s) {
        QueryBuilder<CsdnAndroidBean> qb = CsdnAndroidBeanDaoManager.getInstance()
                .getSession().queryBuilder(CsdnAndroidBean.class);
        qb.where(CsdnAndroidBeanDao.Properties.Title.like("%" + s + "%"));
        final List<CsdnAndroidBean> mlist = qb.list();
        adapter = new SortAdapter(this, mlist);
        mTagSearchlist.setAdapter(adapter);

        mTagSearchlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CsdnTagActivity.this, CsdnDetailActivity.class);
                intent.putExtra(Constants.DATA, mlist.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

}
