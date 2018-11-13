package com.template.base.image;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface IImageLoaderStrategy {
    void init(Context context);

    void show(Context context, String url, ImageView imageView);

    void show(Context context, String url, ImageView imageView, OnLoadingListener listener);

    void show(Context context, String url, ImageView imageView, ImageLoaderOptions options);

    void show(Context context, String url, ImageView imageView, ImageLoaderOptions options, OnLoadingListener listener);

    void show(Context context, @DrawableRes int resId, ImageView imageView);

    void show(Context context, @DrawableRes int resId, ImageView imageView, ImageLoaderOptions options);

    void show(Activity activity, String url, ImageView imageView);

    void show(Activity activity, String url, ImageView imageView, OnLoadingListener listener);

    void show(Activity activity, String url, ImageView imageView, ImageLoaderOptions options);

    void show(Activity activity, String url, ImageView imageView, ImageLoaderOptions options, OnLoadingListener listener);

    void show(Activity activity, @DrawableRes int resId, ImageView imageView);

    void show(Activity activity, @DrawableRes int resId, ImageView imageView, ImageLoaderOptions options);

    void show(Fragment fragment, String url, ImageView imageView);

    void show(Fragment fragment, String url, ImageView imageView, OnLoadingListener listener);

    void show(Fragment fragment, String url, ImageView imageView, ImageLoaderOptions options);

    void show(Fragment fragment, String url, ImageView imageView, ImageLoaderOptions options, OnLoadingListener listener);

    void show(Fragment fragment, @DrawableRes int resId, ImageView imageView);

    void show(Fragment fragment, @DrawableRes int resId, ImageView imageView, ImageLoaderOptions options);

    void clearCache(Context context);
}
