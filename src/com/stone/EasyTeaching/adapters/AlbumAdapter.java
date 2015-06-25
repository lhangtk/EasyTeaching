package com.stone.EasyTeaching.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.stone.EasyTeaching.R;
import com.stone.EasyTeaching.models.AlbumModel;

import java.util.List;

/**
 * Created by hangli2 on 2015/6/24.
 */
public class AlbumAdapter extends BaseAdapter {
    private Context context;
    private List<AlbumModel> albumModelList;
    private LayoutInflater inflater;

    public AlbumAdapter(Context context, List<AlbumModel> albumModelList) {
        this.context = context;
        this.albumModelList = albumModelList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return albumModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return albumModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //绑定UI
        ViewHolder viewHolder = null;
        if (view == null){
            view = inflater.inflate(R.layout.album_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.album_img);
            final ViewHolder finalViewHolder = viewHolder;
//            viewHolder.imageView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    int w = finalViewHolder.imageView.getWidth();
//                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(w,w);
//                    finalViewHolder.imageView.setLayoutParams(params);
//                }
//            },200);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //初始化数据
        AlbumModel albumModel = albumModelList.get(i);
        ImageLoader.getInstance().displayImage(albumModel.getUrl(),viewHolder.imageView);
        //定义事件
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"点啊点",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private class ViewHolder{
        ImageView imageView;
    }
}
