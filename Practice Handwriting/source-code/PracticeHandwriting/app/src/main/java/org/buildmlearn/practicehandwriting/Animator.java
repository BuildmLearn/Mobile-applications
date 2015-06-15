package org.buildmlearn.practicehandwriting;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class Animator {

    private static long DEFAULT_DURATION = 500;

    private static float MIN_SCALE = 0.6f;
    private static float MAX_SCALE = 1.0f;
    private static float SCALE_PIVOT = 0.5f;

    private static int MAX_ALPHA = 1;
    private static int MIN_ALPHA = 0;

    private static float MIN_DELTA = 0.0f;
    private static float MAX_DELTA = 1.0f;

    private static float MIN_ROTATION = 0.0f;
    private static float MID_ROTATION = 180.0f;
    private static float MAX_ROTATION = 360.0f;


    public static Animation createFadeInAnimation() {
        return createFadeInAnimation(DEFAULT_DURATION);
    }

    public static Animation createFadeInAnimation(long duration) {
        Animation fadeIn = new AlphaAnimation(MIN_ALPHA, MAX_ALPHA);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(duration);
        fadeIn.setFillAfter(true);
        return fadeIn;
    }

    public static Animation createFadeOutAnimation() {
        return createFadeOutAnimation(DEFAULT_DURATION);
    }

    public static Animation createFadeOutAnimation(long duration) {
        Animation fadeOut = new AlphaAnimation(MAX_ALPHA, MIN_ALPHA);
        fadeOut.setInterpolator(new DecelerateInterpolator());
        fadeOut.setDuration(duration);
        fadeOut.setFillAfter(true);
        return fadeOut;
    }

    public static Animation createScaleUpAnimation() {
        return createScaleUpAnimation(DEFAULT_DURATION);
    }

    public static Animation createScaleUpAnimation(long duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(MIN_SCALE, MAX_SCALE, MIN_SCALE, MAX_SCALE, Animation.RELATIVE_TO_SELF, SCALE_PIVOT, Animation.RELATIVE_TO_SELF, SCALE_PIVOT);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    public static Animation createScaleDownAnimation() {
        return createScaleDownAnimation(DEFAULT_DURATION);
    }

    public static Animation createScaleDownAnimation(long duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(MAX_SCALE, MIN_SCALE, MAX_SCALE, MIN_SCALE, Animation.RELATIVE_TO_SELF, SCALE_PIVOT, Animation.RELATIVE_TO_SELF, SCALE_PIVOT);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    public static Animation createSlideInFromBottom() {
        return createSlideInFromBottom(DEFAULT_DURATION, 0);
    }

    public static Animation createSlideInFromBottom(long offset) {
        return createSlideInFromBottom(DEFAULT_DURATION,offset);
    }

    public static Animation createSlideInFromBottom(long duration, long offset) {
        Animation slide = new TranslateAnimation(MIN_DELTA,MIN_DELTA,MIN_DELTA,MAX_DELTA);
        slide.setDuration(duration);
        slide.setStartOffset(offset);
        return slide;
    }

    public static void createYFlipForwardAnimation(View view) {
        createYFlipForwardAnimation(view, DEFAULT_DURATION);
    }

    public static void createYFlipForwardAnimation(View view, long duration) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", MIN_ROTATION, MID_ROTATION);
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    public static void createXFlipForwardAnimation(View view) {
        createXFlipForwardAnimation(view, DEFAULT_DURATION);
    }

    public static void createXFlipForwardAnimation(View view, long duration) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationX", MIN_ROTATION, MID_ROTATION);
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    public static void createYFlipBackwardAnimation(View view) {
        createYFlipBackwardAnimation(view, DEFAULT_DURATION);
    }

    public static void createYFlipBackwardAnimation(View view, long duration) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", MID_ROTATION, MAX_ROTATION);
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    public static void createXFlipBackwardAnimation(View view) {
        createXFlipBackwardAnimation(view, DEFAULT_DURATION);
    }

    public static void createXFlipBackwardAnimation(View view, long duration) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationX", MID_ROTATION, MAX_ROTATION);
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }
}
