package com.longshihan.mvpretrofit.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.longshihan.mvpretrofit.BR;
import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.bean.TestJavaBean;
import com.longshihan.mvpretrofit.databinding.ActivityDataBingBinding;

import java.util.List;

public class DataBingActivity extends AppCompatActivity {
    ActivityDataBingBinding bing;
    private List<TestJavaBean> mList;
    TestJavaBean bean = new TestJavaBean("122", 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bing = DataBindingUtil.setContentView(this, R.layout.activity_data_bing);
        initView();
        initData();


    }

    private void initData() {
        bing.setEmployee(bean);
        bing.setVariable(BR.employee,bean);
    }

    private void initView() {
        for (int i = 0; i < 2; i++) {
            TestJavaBean bean = new TestJavaBean(i + 1 + ":" + i + 2, i);
            mList.add(bean);
        }
    }
}
