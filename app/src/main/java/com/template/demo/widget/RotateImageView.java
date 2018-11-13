package com.template.demo.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;

public class RotateImageView extends AppCompatImageView {

    private PausableAnim anim; // 动画效果

    public RotateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        resumeAnim();
    }

    private void initAnim() { // 初始化动画效果
        anim = new PausableAnim(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); // 绕控件中心点旋转
        anim.setInterpolator(new LinearInterpolator()); // 匀速旋转
        anim.setRepeatCount(Animation.INFINITE); // 无限重复
        anim.setDuration(2000); // 每圈耗时2秒
        setAnimation(anim); // 将Animation设置到控件上
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility != View.VISIBLE) {
            clearAnimation(); // 为解决bug添加，隐藏控件前，需clearAnimation
            cancelAnim(); // 将anim置空
        }
        super.setVisibility(visibility);
    }

    /**
     * 继续动画
     */
    public void resumeAnim() {
        if (anim == null) {
            initAnim();
        }
        anim.resume();
    }

    /**
     * 暂停动画
     */
    public void pauseAnim() {
        if (anim != null) {
            anim.pause();
        }
    }

    /**
     * 清除动画
     */
    public void cancelAnim() {
        if (anim != null) {
            anim.cancel();
            anim = null;
        }
    }


    /**
     * 可暂停的动画效果
     */
    class PausableAnim extends RotateAnimation {

        private long mElapsedAtPause = 0;
        private boolean mPaused = false;

        /**
         * 参数详见：
         * <p/>
         * https://developer.android.com/reference/android/view/animation/RotateAnimation.html#RotateAnimation(float,%20float,%20int,%20float,%20int,%20float)
         */
        public PausableAnim(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
            super(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
        }

        @Override
        public boolean getTransformation(long currentTime, Transformation outTransformation) {
            if (mPaused && mElapsedAtPause == 0) {
                mElapsedAtPause = currentTime - getStartTime();
            }
            if (mPaused)
                setStartTime(currentTime - mElapsedAtPause);
            return super.getTransformation(currentTime, outTransformation);
        }

        public void pause() {
            mElapsedAtPause = 0;
            mPaused = true;
        }

        public void resume() {
            mPaused = false;
        }

    }

}