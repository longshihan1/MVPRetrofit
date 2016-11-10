package com.longshihan.mvpretrofit.fragment.im;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EMLog;
import com.longshihan.mvpretrofit.R;
import com.longshihan.mvpretrofit.base.BaseFragment;
import com.longshihan.mvpretrofit.bean.db.InviteMessgeDao;
import com.longshihan.mvpretrofit.bean.db.UserDao;
import com.longshihan.mvpretrofit.utils.DemoHelper;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImGroupFragment extends BaseFragment {

    protected boolean isConflict;
    @BindView(R.id.application_newfriend)
    TextView mApplicationNewfriend;
    @BindView(R.id.application_attention)
    TextView mApplicationAttention;
    @BindView(R.id.application_gongzhong)
    TextView mApplicationGongzhong;
    @BindView(R.id.im_group_expand)
    ExpandableListView mImGroupExpand;
    private Map<String, EaseUser> contactsMap;
    protected List<EaseUser> contactList;
    protected boolean hidden;
    private InviteMessgeDao inviteMessgeDao;
    private View loadingView;

    private static final String TAG = ImGroupFragment.class.getSimpleName();
    private ContactSyncListener contactSyncListener;
    private BlackListSyncListener blackListSyncListener;
    private ContactInfoSyncListener contactInfoSyncListener;

    public ImGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //to avoid crash when open app after long time stay in background after user logged into
        // another device
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_im_group;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        //add loading view
        loadingView = LayoutInflater.from(getActivity()).inflate(R.layout.em_layout_loading_data,
                null);
        //contentContainer.addView(loadingView);

        contactSyncListener = new ContactSyncListener();
        DemoHelper.getInstance().addSyncContactListener(contactSyncListener);

        blackListSyncListener = new BlackListSyncListener();
        DemoHelper.getInstance().addSyncBlackListListener(blackListSyncListener);

        contactInfoSyncListener = new ContactInfoSyncListener();
        DemoHelper.getInstance().getUserProfileManager().addSyncContactInfoListener
                (contactInfoSyncListener);

        if (DemoHelper.getInstance().isContactsSyncedWithServer()) {
            loadingView.setVisibility(View.GONE);
        } else if (DemoHelper.getInstance().isSyncingContactsWithServer()) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.hidden = hidden;
        if (!hidden) {
            refresh();
        }
    }

    // refresh ui
    public void refresh() {
        Map<String, EaseUser> m = DemoHelper.getInstance().getContactList();
        if (m instanceof Hashtable<?, ?>) {
            //noinspection unchecked
            m = (Map<String, EaseUser>) ((Hashtable<String, EaseUser>) m).clone();
        }
        setContactsMap(m);
        if (inviteMessgeDao == null) {
            inviteMessgeDao = new InviteMessgeDao(getActivity());
        }
        if (inviteMessgeDao.getUnreadMessagesCount() > 0) {
            //applicationItem.setText("新的朋友" + "..");
        } else {
            //applicationItem.setText("新的朋友");
        }

        getContactList();
        //contactListLayout.refresh();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hidden) {
            refresh();
        }
    }


    @Override
    protected void initData() {
        EMClient.getInstance().addConnectionListener(connectionListener);

        contactList = new ArrayList<EaseUser>();
        getContactList();
    }

    /**
     * 获取联系人列表和排序，将过滤掉黑名单中的用户
     */
    protected void getContactList() {
        contactList.clear();
        if (contactsMap == null) {
            return;
        }
        synchronized (this.contactsMap) {
            Iterator<Map.Entry<String, EaseUser>> iterator = contactsMap.entrySet().iterator();
            List<String> blackList = EMClient.getInstance().contactManager()
                    .getBlackListUsernames();
            while (iterator.hasNext()) {
                Map.Entry<String, EaseUser> entry = iterator.next();
                // to make it compatible with data in previous version, you can remove this check
                // if this is new app
                if (!entry.getKey().equals("item_new_friends")
                        && !entry.getKey().equals("item_groups")
                        && !entry.getKey().equals("item_chatroom")
                        && !entry.getKey().equals("item_robots")) {
                    if (!blackList.contains(entry.getKey())) {
                        //filter out users in blacklist
                        EaseUser user = entry.getValue();
                        EaseCommonUtils.setUserInitialLetter(user);
                        contactList.add(user);
                    }
                }
            }
        }
    }


    /**
     * 将用户移动到黑名单
     */
    protected void moveToBlacklist(final String username) {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        String st1 = getResources().getString(com.hyphenate.easeui.R.string
                .Is_moved_into_blacklist);
        final String st2 = getResources().getString(com.hyphenate.easeui.R.string
                .Move_into_blacklist_success);
        final String st3 = getResources().getString(com.hyphenate.easeui.R.string
                .Move_into_blacklist_failure);
        pd.setMessage(st1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(() -> {
            try {
                //move to blacklist
                EMClient.getInstance().contactManager().addUserToBlackList(username, false);
                getActivity().runOnUiThread(() -> {
                    pd.dismiss();
                    Toast.makeText(getActivity(), st2, Toast.LENGTH_SHORT).show();
                    refresh();
                });
            } catch (HyphenateException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(() -> {
                    pd.dismiss();
                    Toast.makeText(getActivity(), st3, Toast.LENGTH_SHORT).show();
                });
            }
        }).start();

    }

    EMConnectionListener connectionListener = new EMConnectionListener() {

        @Override
        public void onDisconnected(int error) {
            if (error == EMError.USER_REMOVED || error == EMError.USER_LOGIN_ANOTHER_DEVICE ||
                    error == EMError.SERVER_SERVICE_RESTRICTED) {
                isConflict = true;
            } else {
                getActivity().runOnUiThread(() -> onConnectionDisconnected());
            }
        }

        @Override
        public void onConnected() {
            getActivity().runOnUiThread(() -> onConnectionConnected());
        }
    };
    EaseContactListItemClickListener listItemClickListener;

    protected void onConnectionDisconnected() {

    }

    protected void onConnectionConnected() {

    }

    /**
     * set contacts map, key is the hyphenate id
     *
     * @param contactsMap
     */
    public void setContactsMap(Map<String, EaseUser> contactsMap) {
        this.contactsMap = contactsMap;
    }

    @OnClick({R.id.application_newfriend, R.id.application_attention, R.id.application_gongzhong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.application_newfriend:
                //申请界面
                break;
            case R.id.application_attention:
                //群组管理
                break;
            case R.id.application_gongzhong:
                //csdn
                break;
        }
    }

    public interface EaseContactListItemClickListener {
        /**
         * on click event for item in contact list
         *
         * @param user --the user of item
         */
        void onListItemClicked(EaseUser user);
    }

    /**
     * set contact list item click listener
     *
     * @param listItemClickListener
     */
    public void setContactListItemClickListener(EaseContactListItemClickListener
                                                        listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public void onDestroy() {

        EMClient.getInstance().removeConnectionListener(connectionListener);
        if (contactSyncListener != null) {
            DemoHelper.getInstance().removeSyncContactListener(contactSyncListener);
            contactSyncListener = null;
        }

        if (blackListSyncListener != null) {
            DemoHelper.getInstance().removeSyncBlackListListener(blackListSyncListener);
        }

        if (contactInfoSyncListener != null) {
            DemoHelper.getInstance().getUserProfileManager().removeSyncContactInfoListener
                    (contactInfoSyncListener);
        }
        super.onDestroy();
    }

    /**
     * delete contact
     */
    public void deleteContact(final EaseUser tobeDeleteUser) {
        String st1 = getResources().getString(R.string.deleting);
        final String st2 = getResources().getString(R.string.Delete_failed);
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage(st1);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(() -> {
            try {
                EMClient.getInstance().contactManager().deleteContact(tobeDeleteUser
                        .getUsername());
                // remove user from memory and database
                UserDao dao = new UserDao(getActivity());
                dao.deleteContact(tobeDeleteUser.getUsername());
                DemoHelper.getInstance().getContactList().remove(tobeDeleteUser.getUsername());
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        contactList.remove(tobeDeleteUser);
                        //contactListLayout.refresh();

                    }
                });
            } catch (final Exception e) {
                getActivity().runOnUiThread(() -> {
                    pd.dismiss();
                    Toast.makeText(getActivity(), st2 + e.getMessage(), Toast
                            .LENGTH_LONG).show();
                });

            }

        }).start();

    }

    class ContactSyncListener implements DemoHelper.DataSyncListener {
        @Override
        public void onSyncComplete(final boolean success) {
            EMLog.d(TAG, "on contact list sync success:" + success);
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (success) {
                                loadingView.setVisibility(View.GONE);
                                refresh();
                            } else {
                                String s1 = getResources().getString(R.string
                                        .get_failed_please_check);
                                Toast.makeText(getActivity(), s1, Toast.LENGTH_LONG).show();
                                loadingView.setVisibility(View.GONE);
                            }
                        }

                    });
                }
            });
        }
    }

    class BlackListSyncListener implements DemoHelper.DataSyncListener {

        @Override
        public void onSyncComplete(boolean success) {
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    refresh();
                }
            });
        }

    }

    class ContactInfoSyncListener implements DemoHelper.DataSyncListener {

        @Override
        public void onSyncComplete(final boolean success) {
            EMLog.d(TAG, "on contactinfo list sync success:" + success);
            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    loadingView.setVisibility(View.GONE);
                    if (success) {
                        refresh();
                    }
                }
            });
        }

    }
}
