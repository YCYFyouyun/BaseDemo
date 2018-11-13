package com.template.base.image.glide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.template.base.image.IImageLoaderStrategy;
import com.template.base.image.ImageLoaderOptions;
import com.template.base.image.OnLoadingListener;
import com.template.base.image.constants.ImageDiskCacheType;
import com.template.base.image.constants.ImageScaleType;

public class GlideImageLoader implements IImageLoaderStrategy {
    @Override
    public void init(Context context) {
        //nothing to do
    }

    @Override
    public void show(Context context, String url, ImageView imageView) {
        show(context, url, imageView, ImageLoaderOptions.getDefault(), null);
    }

    @Override
    public void show(Context context, String url, ImageView imageView, OnLoadingListener listener) {
        show(context, url, imageView, ImageLoaderOptions.getDefault(), listener);
    }

    @Override
    public void show(Context context, String url, ImageView imageView, ImageLoaderOptions options) {
        show(context, url, imageView, options, null);
    }

    @Override
    public void show(Context context, String url, ImageView imageView, ImageLoaderOptions options, OnLoadingListener listener) {
        if (options.isAsGif())
            load(GlideApp.with(context.getApplicationContext()).asGif().load(url),
                    options, imageView, url, listener);
        else
            load(GlideApp.with(context.getApplicationContext()).asDrawable().load(url),
                    options, imageView, url, listener);
    }

    @Override
    public void show(Context context, @DrawableRes int resId, ImageView imageView) {
        show(context, resId, imageView, ImageLoaderOptions.getDefault());
    }

    @Override
    public void show(Context context, @DrawableRes int resId, ImageView imageView, ImageLoaderOptions options) {
        if (options.isAsGif())
            load(GlideApp.with(context).asGif().load(resId),
                    options, imageView, null, null);
        else
            load(GlideApp.with(context).asDrawable().load(resId),
                    options, imageView, null, null);
    }

    @Override
    public void show(Activity activity, String url, ImageView imageView) {
        show(activity, url, imageView, ImageLoaderOptions.getDefault(), null);
    }

    @Override
    public void show(Activity activity, String url, ImageView imageView, OnLoadingListener listener) {
        show(activity, url, imageView, ImageLoaderOptions.getDefault(), listener);
    }

    @Override
    public void show(Activity activity, String url, ImageView imageView, ImageLoaderOptions options) {
        show(activity, url, imageView, options, null);
    }

    @Override
    public void show(Activity activity, String url, ImageView imageView, ImageLoaderOptions options, OnLoadingListener listener) {
        if (options.isAsGif())
            load(GlideApp.with(activity).asGif().load(url),
                    options, imageView, url, listener);
        else
            load(GlideApp.with(activity).asDrawable().load(url),
                    options, imageView, url, listener);
    }

    @Override
    public void show(Activity activity, @DrawableRes int resId, ImageView imageView) {
        show(activity, resId, imageView, ImageLoaderOptions.getDefault());
    }

    @Override
    public void show(Activity activity, @DrawableRes int resId, ImageView imageView, ImageLoaderOptions options) {
        if (options.isAsGif())
            load(GlideApp.with(activity).asGif().load(resId),
                    options, imageView, null, null);
        else
            load(GlideApp.with(activity).asDrawable().load(resId),
                    options, imageView, null, null);
    }

    @Override
    public void show(Fragment fragment, String url, ImageView imageView) {
        show(fragment, url, imageView, ImageLoaderOptions.getDefault(), null);
    }

    @Override
    public void show(Fragment fragment, String url, ImageView imageView, OnLoadingListener listener) {
        show(fragment, url, imageView, ImageLoaderOptions.getDefault(), listener);
    }

    @Override
    public void show(Fragment fragment, String url, ImageView imageView, ImageLoaderOptions options) {
        show(fragment, url, imageView, options, null);
    }

    @Override
    public void show(Fragment fragment, String url, ImageView imageView, ImageLoaderOptions options, OnLoadingListener listener) {
        if (options.isAsGif())
            load(GlideApp.with(fragment).asGif().load(url), options, imageView, url, listener);
        else
            load(GlideApp.with(fragment).asDrawable().load(url), options, imageView, url, listener);
    }

    @Override
    public void show(Fragment fragment, @DrawableRes int resId, ImageView imageView) {
        show(fragment, resId, imageView, ImageLoaderOptions.getDefault());
    }

    @Override
    public void show(Fragment fragment, @DrawableRes int resId, ImageView imageView, ImageLoaderOptions options) {
        if (options.isAsGif())
            load(GlideApp.with(fragment).asGif().load(resId), options, imageView, null, null);
        else
            load(GlideApp.with(fragment).asDrawable().load(resId), options, imageView, null, null);
    }

    private void load(GlideRequest glideRequest, ImageLoaderOptions options,
                      ImageView imageView, String key, OnLoadingListener listener) {
        //设置占位图
        if (options.getPlaceHolder() != -1)
            glideRequest.placeholder(options.getPlaceHolder());
        //设置错误图
        if (options.getErrorHolder() != -1)
            glideRequest.error(options.getErrorHolder());
        //设置宽高
        if (options.getWidth() != -1 && options.getHeight() != -1)
            glideRequest.override(options.getWidth(), options.getHeight());
        //设置显示动画
        if (!options.isCrossFade())
            glideRequest.dontAnimate();
        else
            glideRequest.transition(DrawableTransitionOptions.withCrossFade(options.getCrossFadeDuration()));
        //设置缩放类型
        if (options.getScaleType() == ImageScaleType.CENTER_CROP)
            glideRequest.centerCrop();
        else if (options.getScaleType() == ImageScaleType.FIT_CENTER)
            glideRequest.fitCenter();
        else if (options.getScaleType() == ImageScaleType.CENTER_INSIDE)
            glideRequest.centerInside();
        //设置硬盘缓存策略
        if (options.getDiskCacheType() == ImageDiskCacheType.DEFAULT)
            glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        else if (options.getDiskCacheType() == ImageDiskCacheType.ALL)
            glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
        else if (options.getDiskCacheType() == ImageDiskCacheType.DATA)
            glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA);
        else if (options.getDiskCacheType() == ImageDiskCacheType.RESOURCE)
            glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        else if (options.getDiskCacheType() == ImageDiskCacheType.NONE)
            glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
        //设置是否需要内存缓存
        glideRequest.skipMemoryCache(options.isSkipMemoryCache());

        //设置缩略比例
        if (options.getThumbRate() != 0f)
            glideRequest.thumbnail(options.getThumbRate());

        if (listener != null) {
            InnerProgressTarget target = new InnerProgressTarget(key, imageView, listener);
            glideRequest.into(target);
        } else {
            glideRequest.into(imageView);
        }
    }

    @Override
    public void clearCache(Context context) {
        GlideCacheUtils.clearCache();
    }

    private class InnerProgressTarget extends ImageLoaderTarget<String> {
        private OnLoadingListener mListener;

        public InnerProgressTarget(String model, ImageView target, OnLoadingListener mListener) {
            super(model, target);
            this.mListener = mListener;
        }

        @Override
        protected void onStarted() {
            if (mListener != null)
                mListener.onStarted();
        }

        @Override
        protected void onLoading(long bytesRead, long contentLength) {
            if (mListener != null)
                mListener.onLoading(bytesRead, contentLength, (int) (100 * bytesRead / contentLength));
        }

        @Override
        protected void onResult(boolean success) {
            if (mListener != null)
                mListener.onResult(success);
        }
    }
}
