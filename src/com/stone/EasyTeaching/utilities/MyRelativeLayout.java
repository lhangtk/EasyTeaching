package com.stone.EasyTeaching.utilities;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.widget.RelativeLayout;

/**
 * Created by hangli2 on 2015/4/13.
 */
public class MyRelativeLayout extends RelativeLayout {
    private OnSizeChangedListener listener;
    private int screenWidth; //屏幕宽度
    private int screenHeight; //屏幕高度
    private int width;
    private int height;
    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Display localDisplay = ((Activity) context).getWindowManager()
                .getDefaultDisplay();
        this.screenWidth = localDisplay.getWidth() ;
        this.screenHeight = localDisplay.getHeight();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.width = widthMeasureSpec;
        this.height = heightMeasureSpec;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (listener != null) {
            listener.onSizeChanged(w, h, oldw, oldh);
        }
        measure(this.width - w + getWidth(), this.height - h + getHeight());
    }

    public void setOnSizeChangedListener(OnSizeChangedListener listener) {
        this.listener = listener;
    }
}
