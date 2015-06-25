package com.stone.EasyTeaching.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpClient;
import com.stone.EasyTeaching.ETApplication;
import com.stone.EasyTeaching.R;
import com.stone.EasyTeaching.events.BaseEvents;
import de.greenrobot.event.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    private ViewPager mPager;// 页卡内容
    private List<View> listViews; // Tab页面列表
    private ImageView imgtable;// 动画图片
    private TextView t1, t2, t3;// 页卡头标
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = inflater.getContext();
        app = (ETApplication) context.getApplicationContext();
        client = app.getClient();
        EventBus.getDefault().register(this);
        fragmentView = inflater.inflate(R.layout.class_act_fregment,container,false);

        InitImageView();
        InitTextView();
        InitViewPager();

        return fragmentView;
    }
    /**
     * 初始化头标
     */
    private void InitTextView() {

        t1 = (TextView) fragmentView.findViewById(R.id.text1);
        t2 = (TextView) fragmentView.findViewById(R.id.text2);
        t3 = (TextView) fragmentView.findViewById(R.id.text3);

        t1.setOnClickListener(new MyOnClickListener(0));
        t2.setOnClickListener(new MyOnClickListener(1));
        t3.setOnClickListener(new MyOnClickListener(2));

        setCurrPager(0);
    }

    /**
     * 初始化ViewPager
     */
    private void InitViewPager() {
        mPager = (ViewPager) fragmentView.findViewById(R.id.class_act_viewpager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = LayoutInflater.from(context);
        listViews.add(mInflater.inflate(R.layout.class_act_notices, null));
        listViews.add(mInflater.inflate(R.layout.class_act_infos, null));
        listViews.add(mInflater.inflate(R.layout.class_act_vote, null));
        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画
     */
    private void InitImageView() {
        imgtable = (ImageView) fragmentView.findViewById(R.id.img_table_now);
        imgtable.postDelayed(new Runnable() {
            @Override
            public void run() {
                offset = imgtable.getWidth();// 计算偏移量
            }
        },200);
    }

    /**
     * ViewPager适配器
     */
    public class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }

    /**
     * 头标点击监听
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageSelected(int index) {
            Animation animation = null;
            animation = new TranslateAnimation(offset * currIndex, offset
                    * index, 0, 0);
            currIndex = index;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            imgtable.startAnimation(animation);
            setCurrPager(index);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * 设置头标状态
     */
    private void setCurrPager(int index){
        t1.setEnabled(true);
        t2.setEnabled(true);
        t3.setEnabled(true);

        switch (index){
            case 0:
                t1.setEnabled(false);
                break;
            case 1:
                t2.setEnabled(false);
                break;
            case 2:
                t3.setEnabled(false);
                break;
        }
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
