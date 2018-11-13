package com.template.base.image;

import com.template.base.image.glide.GlideImageLoader;

public final class ImageLoader {
    private ImageLoader() {
    }

    static {
        LOADER = new GlideImageLoader();
    }

    private static final IImageLoaderStrategy LOADER;

    public static IImageLoaderStrategy getStrategy() {
        return LOADER;
    }

}