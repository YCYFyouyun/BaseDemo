package com.template.base.image.glide;

import com.bumptech.glide.Glide;
import com.template.base.utils.SDCardUtils;
import com.template.base.utils.Utils;

public class GlideCacheUtils {
    /**
     * 获取sd卡下缓存路径
     */
    public static String getCachePath() {
        return new StringBuffer()
                .append(SDCardUtils.getExternalCachePath())
                .append("glide/").toString();
    }

    /**
     * 清除缓存
     */
    public static void clearCache() {
        try {
            final Glide glide = GlideApp.get(Utils.getContext());
            glide.clearMemory();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    glide.clearDiskCache();
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
