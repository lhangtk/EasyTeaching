package com.stone.EasyTeaching.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.loopj.android.http.AsyncHttpClient;
import com.stone.EasyTeaching.ETApplication;
import com.stone.EasyTeaching.R;
import com.stone.EasyTeaching.fragments.ClassActFragment;
import com.stone.EasyTeaching.fragments.ContactsFragment;
import com.stone.EasyTeaching.fragments.PhotosFragment;

/**
 * Created by hangli2 on 2015/6/19.
 */
public class MainActivity extends Activity implements View.OnClickListener{
    /**APP*/
    private ETApplication app;
    /**
     * 异步请求Client
     */
    private AsyncHttpClient client;
    /*班级活动按钮*/
    private Button btnClassAct;
    /**通讯录按钮*/
    private Button btnContacts;
    /**相册按钮*/
    private Button btnPhotos;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;
    /**
     * 保存当前fragment的Index
     */
    private int fragmentIndex = 0;
/**班级活动页面*/
    private ClassActFragment classActFragment = null;
/**通讯录页面*/
    private ContactsFragment contactsFragment = null;
    /**相册页面*/
    private PhotosFragment photosFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        app = (ETApplication) getApplication();
        client = app.getClient();
        init();
        fragmentManager = this.getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 绑定UI
     */
    private void init(){
        btnClassAct = (Button) findViewById(R.id.main_class_act);
        btnContacts = (Button) findViewById(R.id.main_contacts);
        btnPhotos = (Button) findViewById(R.id.main_photos);

        btnClassAct.setOnClickListener(this);
        btnContacts.setOnClickListener(this);
        btnPhotos.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_class_act:
                setTabSelection(0);
                break;
            case R.id.main_contacts:
                setTabSelection(1);
                break;
            case R.id.main_photos:
                setTabSelection(2);
                break;
        }
    }

    /**
     * 索引视图
     * @param index
     */
    public void setTabSelection(int index){
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        this.fragmentIndex = index;
        switch (index){
            case 0://班级活动
                btnClassAct.setEnabled(false);
                if (classActFragment == null) {
                    // 为空，则创建一个并添加到界面上
                    classActFragment = new ClassActFragment();
                    transaction.add(R.id.main_content, classActFragment);
                } else {
                    // 不为空，则直接将它显示出来
                    transaction.show(classActFragment);
                }
                break;
            case 1://通讯录
                btnContacts.setEnabled(false);
                if (contactsFragment == null) {
                    // 为空，则创建一个并添加到界面上
                    contactsFragment = new ContactsFragment();
                    transaction.add(R.id.main_content, contactsFragment);
                } else {
                    // 不为空，则直接将它显示出来
                    transaction.show(contactsFragment);
                }
                break;
            case 2://相册
                btnPhotos.setEnabled(false);
                if (photosFragment == null) {
                    // 为空，则创建一个并添加到界面上
                    photosFragment = new PhotosFragment();
                    transaction.add(R.id.main_content, photosFragment);
                } else {
                    // 不为空，则直接将它显示出来
                    transaction.show(photosFragment);
                }
                break;
        }
        //提交展示
        transaction.commit();
    }

    /**
     * 清空选中状态
     */
    public void clearSelection(){
        btnClassAct.setEnabled(true);
        btnContacts.setEnabled(true);
        btnPhotos.setEnabled(true);
    }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (classActFragment != null) {
            transaction.hide(classActFragment);
        }
        if (contactsFragment != null) {
            transaction.hide(contactsFragment);
        }
        if (photosFragment != null) {
            transaction.hide(photosFragment);
        }
    }
}
