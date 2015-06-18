package com.stone.EasyTeaching;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import com.loopj.android.http.AsyncHttpClient;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.stone.EasyTeaching.models.UserInfoModel;

/**
 * Created by hangli2 on 2015/6/18.
 */
public class ETApplication extends Application {


    /**
     * AsyncHttpClient
     */
    private AsyncHttpClient client;
    private UserInfoModel userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        /** 初始化ImageLoader */
        initImageLoader(getApplicationContext());
        /** 初始化网络请求 */
        initAsyncClient();

//        /**捕捉崩溃日志*/
//        MyLog.setPath(Environment.getExternalStorageState()+"/iflytek/log/");
//        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);

    }

//    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
//        @Override
//        public void uncaughtException(Thread thread, Throwable throwable) {
//            MyLog.e("崩溃了！！",throwable.getMessage());
//        }
//    };

    /**
     * 初始化网络请求
     */
    private void initAsyncClient() {
        client = new AsyncHttpClient();
        client.setTimeout(10000);
        client.setMaxRetriesAndTimeout(3, 10000);
    }

    /**
     * 获取公用的AsyncHttpClient
     *
     * by hangli2: 必须使用getClient方式获取网络请求的client
     */
    public AsyncHttpClient getClient() {
        if (client == null) {
            initAsyncClient();
        }
        return client;
    }

    public UserInfoModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoModel userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY)
                .cacheOnDisk(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPoolSize(3)
// default
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .denyCacheImageMultipleSizesInMemory()
// .memoryCache(new LruMemoryCache((int) (6 * 1024 * 1024)))
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize((int) (2 * 1024 * 1024))
                .memoryCacheSizePercentage(13)
// default
//                .diskCache(new UnlimitedDiscCache(new File(Environment.getExternalStorageDirectory().getPath() + "/cache/")))
// default
                .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(defaultOptions).writeDebugLogs() // Remove
                .build();
// Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheOnDisk(true)
//                .cacheInMemory(false)
//                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .build();
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                context).defaultDisplayImageOptions(defaultOptions)
////                .memoryCache(new LruMemoryCache(6 * 1024 * 1024))
//                .memoryCache(new WeakMemoryCache())
////                .memoryCacheSize(5 * 1024 * 1024)
//                .memoryCacheSizePercentage(13) // default
////                .diskCacheSize(50 * 1024 * 1024)
////                .diskCacheFileCount(100)
//                .threadPoolSize(1)
//                .threadPriority(Thread.NORM_PRIORITY - 1) // default
//                .build();
//        ImageLoader.getInstance().init(config);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        client.cancelAllRequests(true);
        client = null;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        client.cancelAllRequests(true);
        client = null;
    }

    /*******************百变花样 ImageLoader Options***********************/
    /**
     * 处理本地缩略图的Option
     * 内存缓存
     * 用于相册页面
     *
     * @return
     */
    public DisplayImageOptions getLocalOptionsForThumb() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageForEmptyUri(R.drawable.imageloader_error)
//                .showStubImage(R.drawable.imageloader_loading)
//                .showImageOnFail(R.drawable.imageloader_false)
                .cacheOnDisk(false)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .considerExifParams(true)
                .resetViewBeforeLoading(true).build();
        return options;
    }

    /**
     * 处理本地原图的Option
     * 没有缓存
     * 用于相册页面
     *
     * @return
     */
    public DisplayImageOptions getLocalOptionsForOriginal() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(false)
                .cacheInMemory(false)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .resetViewBeforeLoading(true).build();
        return options;
    }


    /**
     * 处理头像的Option
     * 内存缓存 硬盘缓存
     *
     * @return
     */
    public DisplayImageOptions getOptionsForAvatar() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(false)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .build();
        return options;
    }

    /**
     * 处理缩略图的Option
     * 内存缓存 硬盘缓存
     *
     * @return
     */
    public DisplayImageOptions getOptionsForThumb() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .build();
        return options;
    }

    /**
     * 处理原图的Option
     * 硬盘缓存
     * 用于九宫格图点开的页面
     *
     * @return
     */
    public DisplayImageOptions getOptionsForOriginal() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .resetViewBeforeLoading(true)
                .build();
        return options;
    }
}

