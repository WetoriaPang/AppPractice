package com.jeffrey.appsdk.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.jeffrey.appsdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by pangjiaqi on 2017/4/17.
 *
 * @function 初始化UniverImageLaoder, 并用来加载图片（图片框架的封装）
 */

public class ImageLoaderManager {

    private static final int THREAD_COUNT = 4;//最多可以有多少线程
    private static final int PROPRITY = 2;//加载图片的优先级
    private static final int CONNECTION_TIME_OUT = 5 * 1000;//连接的超时时间
    private static final int READ_TIME_OUT = 30 * 1000;//读取的超时时间
    private static final int DISK_CACHE_SIZE = 50 * 1024;//最多缓存的多少图片

    private static ImageLoader mImageLoader = null;
    private static ImageLoaderManager mInstance = null;

    public static ImageLoaderManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ImageLoaderManager.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 单例模式的私有构造方法
     *
     * @param context
     */
    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(THREAD_COUNT)//配置图片下载的最大数量
                .threadPriority(Thread.NORM_PRIORITY - PROPRITY)
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套尺寸的图片到内存中
                .memoryCache(new WeakMemoryCache())//使用弱引用内存缓存
                .diskCacheSize(DISK_CACHE_SIZE)//分配硬盘缓存的大小
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用MD5命名文件
                .tasksProcessingOrder(QueueProcessingType.LIFO)//图片的下载顺序
                .defaultDisplayImageOptions(getDefultOptions())//默认的图片加载Options
                .imageDownloader(new BaseImageDownloader(context, CONNECTION_TIME_OUT,
                        READ_TIME_OUT))//设置图片下载器
                .writeDebugLogs()//debug环境下输出日志
                .build();

        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();
    }

    /**
     * 实现默认的options,可设置图片的缓存策略，编解码方式等
     *
     * @return
     */
    public DisplayImageOptions getDefultOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)//图片地址为空时显示的图片
                .showImageOnFail(R.drawable.xadsdk_img_error)//图片下载失败时显示的图片
                .considerExifParams(true)//是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .cacheInMemory(true)//设置图片可以缓存在内存中
                .cacheOnDisk(true)//设置图片可以缓存在硬盘
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .decodingOptions(new BitmapFactory.Options())//设置解码配置，系统默认
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();
        return options;
    }

    /**
     * 加载图片API
     *
     * @param imageView
     * @param url
     * @param options
     * @param listener
     */
    public void displayImage(String url, ImageView imageView, DisplayImageOptions options,
                             ImageLoadingListener listener) {
        if (mImageLoader == null) {
            mImageLoader.displayImage(url, imageView, options, listener);
        }
    }

    public void displayImage(String url, ImageView imageView, ImageLoadingListener listener) {
        displayImage(url, imageView, null, listener);
    }

    public void displayImage(String url, ImageView imageView) {
        displayImage(url, imageView, null, null);
    }


}
