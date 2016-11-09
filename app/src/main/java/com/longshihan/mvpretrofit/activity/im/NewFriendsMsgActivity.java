/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.longshihan.mvpretrofit.activity.im;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.adapter.NewFriendsMsgAdapter;
import com.longshihan.mvpretrofit.base.BaseActivity;
import com.longshihan.mvpretrofit.bean.db.InviteMessgeDao;
import com.longshihan.mvpretrofit.domain.InviteMessage;

import java.util.List;

/**
 * Application and notification
 *
 */
public class NewFriendsMsgActivity extends BaseActivity {


	@Override
	public int getLayoutId() {
		return R.layout.em_activity_new_friends_msg;
	}

	@Override
	public void setLayouut(Bundle savedInstanceState) {

	}

	@Override
	protected void initAllMembersView(Bundle savedInstanceState) {
		ListView listView = (ListView) findViewById(R.id.list);
		InviteMessgeDao dao = new InviteMessgeDao(this);
		List<InviteMessage> msgs = dao.getMessagesList();

		NewFriendsMsgAdapter adapter = new NewFriendsMsgAdapter(this, 1, msgs);
		listView.setAdapter(adapter);
		dao.saveUnreadMessageCount(0);
	}

	@Override
	protected void initData() {

	}

	public void back(View view) {
		finish();
	}
}
