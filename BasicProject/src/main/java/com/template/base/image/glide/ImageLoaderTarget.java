package com.template.base.image.glide;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.socks.library.KLog;

public abstract class ImageLoaderTarget<T> extends DrawableImageViewTarget
        implements GlideOkClient.OnUIProgressListener {
    private T model;
    private boolean isDone;

    public ImageLoaderTarget(T model, ImageView view) {
        super(view);
        this.model = model;
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
        start();
    }

    @Override
    public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
        super.onResourceReady(resource, transition);
        stop(true);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        stop(false);
    }

    @Override
    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
    }

    @Override
    public void onProgress(long bytesRead, long contentLength) {
        if (isDone)
            return;

        onLoading(bytesRead, contentLength);
    }

    private void start() {
        KLog.e();
        isDone = false;
        GlideOkClient.get().addUIProgressListener(String.valueOf(model), this);
        onStarted();
    }

    private void stop(boolean success) {
        KLog.e();
        isDone = true;
        GlideOkClient.get().removeUIProgressListener(String.valueOf(model));
        onResult(success);
        this.model = null;
    }

    @Override
    public void onDestroy() {
        KLog.e();
        GlideOkClient.get().removeUIProgressListener(String.valueOf(model));
        super.onDestroy();
    }

    protected abstract void onStarted();

    protected abstract void onLoading(long bytesRead, long contentLength);

    protected abstract void onResult(boolean success);
}
