package com.stone.EasyTeaching.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.loopj.android.http.AsyncHttpClient;
import com.stone.EasyTeaching.ETApplication;
import com.stone.EasyTeaching.R;
import com.stone.EasyTeaching.events.BaseEvents;
import de.greenrobot.event.EventBus;

import java.io.IOException;

/**
 * Created by hangli2 on 2015/6/19.
 */
public class ClassActFragment extends Fragment{
    /**上下文*/
    private Context context;
    /**APP*/
    private ETApplication app;
    /**AsyncHttpClient*/
    private AsyncHttpClient client;
    /**当前页面视图*/
    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        app = (ETApplication) context.getApplicationContext();
        client = app.getClient();
        EventBus.getDefault().register(this);
        fragmentView = inflater.inflate(R.layout.class_act_fregment,container,false);
        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /************************** EventBus事件 ********************************/
    /**
     * 异步消息响应线程
     *
     * @param event
     */
    @SuppressWarnings("unchecked")
    public void onEventAsync(BaseEvents event) throws IOException {
        switch (event.getType()) {
            default:
                break;
        }
    }

    /**
     * 同步消息响应线程
     *
     * @param event
     */
    @SuppressWarnings("unchecked")
    public void onEventMainThread(BaseEvents event) {
        switch (event.getType()) {
            default:
                break;
        }
    }
}
