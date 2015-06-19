package com.stone.EasyTeaching.view;

import android.app.Activity;
import android.os.Bundle;
import com.loopj.android.http.AsyncHttpClient;
import com.stone.EasyTeaching.ETApplication;
import com.stone.EasyTeaching.R;

/**
 * Created by hangli2 on 2015/6/19.
 */
public class RegisterActivity extends Activity {
    /**APP*/
    private ETApplication app;
    /**
     * 异步请求Client
     */
    private AsyncHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        app = (ETApplication) getApplication();
        client = app.getClient();
    }
}
