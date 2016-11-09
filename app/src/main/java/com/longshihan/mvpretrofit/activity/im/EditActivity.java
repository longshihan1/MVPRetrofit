package com.longshihan.mvpretrofit.activity.im;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseActivity;

public class EditActivity extends BaseActivity {
	private EditText editText;

	@Override
	public int getLayoutId() {
		return R.layout.em_activity_edit;
	}

	@Override
	public void setLayouut(Bundle savedInstanceState) {

	}

	@Override
	protected void initAllMembersView(Bundle savedInstanceState) {
		editText = (EditText) findViewById(R.id.edittext);
		String title = getIntent().getStringExtra("title");
		String data = getIntent().getStringExtra("data");
		if(title != null)
			((TextView)findViewById(R.id.tv_title)).setText(title);
		if(data != null)
			editText.setText(data);
		editText.setSelection(editText.length());
	}

	@Override
	protected void initData() {

	}


	public void save(View view){
		setResult(RESULT_OK,new Intent().putExtra("data", editText.getText().toString()));
		finish();
	}

	public void back(View view) {
		finish();
	}
}
