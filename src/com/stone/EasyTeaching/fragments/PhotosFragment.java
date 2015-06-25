package com.stone.EasyTeaching.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.loopj.android.http.AsyncHttpClient;
import com.stone.EasyTeaching.ETApplication;
import com.stone.EasyTeaching.R;
import com.stone.EasyTeaching.adapters.AlbumAdapter;
import com.stone.EasyTeaching.events.BaseEvents;
import com.stone.EasyTeaching.models.AlbumModel;
import de.greenrobot.event.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hangli2 on 2015/6/19.
 */
public class PhotosFragment extends Fragment{
    /**上下文*/
    private Context context;
    /**APP*/
    private ETApplication app;
    /**AsyncHttpClient*/
    private AsyncHttpClient client;
    /**当前页面视图*/
    private View fragmentView;
    /*相册列表*/
    private GridView gridView;
    /*相册适配器*/
    private AlbumAdapter albumAdapter;

    private List<AlbumModel> albumModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        app = (ETApplication) context.getApplicationContext();
        client = app.getClient();
        EventBus.getDefault().register(this);
        fragmentView = inflater.inflate(R.layout.photos_fregment,container,false);
        init();
        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void init(){
        gridView = (GridView) fragmentView.findViewById(R.id.album_grid);


        albumModelList = new ArrayList<AlbumModel>();
        albumModelList.add(new AlbumModel("http://c.hiphotos.baidu.com/image/w%3D310/sign=43ad230a6509c93d07f208f6af3cf8bb/9f510fb30f2442a7538994a1d343ad4bd113022a.jpg"));
        albumModelList.add(new AlbumModel("http://c.hiphotos.baidu.com/image/w%3D310/sign=43ad230a6509c93d07f208f6af3cf8bb/9f510fb30f2442a7538994a1d343ad4bd113022a.jpg"));
        albumModelList.add(new AlbumModel("http://c.hiphotos.baidu.com/image/w%3D310/sign=43ad230a6509c93d07f208f6af3cf8bb/9f510fb30f2442a7538994a1d343ad4bd113022a.jpg"));
        albumAdapter = new AlbumAdapter(context,albumModelList);

        gridView.setAdapter(albumAdapter);
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
