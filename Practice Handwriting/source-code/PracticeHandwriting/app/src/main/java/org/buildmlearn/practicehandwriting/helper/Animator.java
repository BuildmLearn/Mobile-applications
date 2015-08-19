package org.buildmlearn.practicehandwriting.helper;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Helper class to provide animations
 */
public class Animator {

    /**
     * The default duration of the animation that shall be used if nothing is specified
     */
    private static long DEFAULT_DURATION = 500;

    /**
     * The middle value that a view can be scaled to
     */
    private static float MID_SCALE = 0.6f;

    /**
     * The maximum value that a view can be scaled to
     */
    private static float MAX_SCALE = 1.0f;

    /**
     * The maximum value of alpha for a fading animation
     */
    private static int MAX_ALPHA = 1;

    /**
     * The minimum value of alpha for a fading animation
     */
    private static int MIN_ALPHA = 0;


    private static float MID_ROTATION = 180.0f;


    /**
     * Function to create a fade in animation with the default duration
     * @return the fade in animation
     */
    public static Animation createFadeInAnimation() {
        return createFadeInAnimation(DEFAULT_DURATION);
    }

    /**
     * Function to create a fade in animation
     * @param duration the duration in milliseconds for which the animation would last
     * @return the fade in animation
     */
    public static Animation createFadeInAnimation(long duration) {
        Animation fadeIn = new AlphaAnimation(MIN_ALPHA, MAX_ALPHA);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(duration);
        fadeIn.setFillAfter(true);
        return fadeIn;
    }

    /**
     * Function to create a fade out animation with the default duration
     * @return the fade out animation
     */
    public static Animation createFadeOutAnimation() {
        return createFadeOutAnimation(DEFAULT_DURATION);
    }

    /**
     * Function to create a fade out animation
     * @param duration the duration in milliseconds for which the animation would last
     * @return the fade out animation
     */
    public static Animation createFadeOutAnimation(long duration) {
        Animation fadeOut = new AlphaAnimation(MAX_ALPHA, MIN_ALPHA);
        fadeOut.setInterpolator(new DecelerateInterpolator());
        fadeOut.setDuration(duration);
        fadeOut.setFillAfter(true);
        return fadeOut;
    }

    /**
     * Function to create a scale up animation with the default duration from 50% to 100%
     * @return the scale up animation
     */
    public static Animation createScaleUpAnimation() {
        return createScaleUpAnimation(DEFAULT_DURATION);
    }

    /**
     * Function to create a scale up animation from 50% to 100%
     * @param duration the duration in milliseconds for which the animation would last
     * @return the scale up animation
     */
    public static Animation createScaleUpAnimation(long duration) {
        return createScaleAnimation(MID_SCALE, MAX_SCALE, duration);
    }

    /**
     * Function to create a scale up animation with the default duration from 0% to 100%
     * @return the scale up animation
     */
    public static Animation createScaleUpCompleteAnimation() {
        /*
      The minimum value that a view can be scaled to
     */
        float MIN_SCALE = 0.0f;
        return createScaleAnimation(MIN_SCALE, MAX_SCALE, DEFAULT_DURATION);
    }

    /**
     * Function to create a fade out animation
     * @param from the scale to start from
     * @param to the scale to end at
     * @param duration the duration in milliseconds for which the animation would last
     * @return the fade out animation
     */
    public static Animation createScaleAnimation(float from, float to, long duration) {
        /*
      The pivot value used for scaling relative to a view
     */
        float SCALE_PIVOT = 0.5f;
        ScaleAnimation scaleAnimation = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, SCALE_PIVOT, Animation.RELATIVE_TO_SELF, SCALE_PIVOT);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    /**
     * Function to create a scale down animation with the default duration from 100% to 50%
     * @return the scale down animation
     */
    public static Animation createScaleDownAnimation() {
        return createScaleDownAnimation(DEFAULT_DURATION);
    }

    /**
     * Function to create a scale up animation from 100% to 50%
     * @param duration the duration in milliseconds for which the animation would last
     * @return the scale down animation
     */
    public static Animation createScaleDownAnimation(long duration) {
        return createScaleAnimation(MAX_SCALE, MID_SCALE, duration);
    }

    /**
     * Function to create a sliding animation with the default duration from bottom
     * @return the slide animation
     */
    public static Animation createSlideInFromBottom() {
        return createSlideInFromBottom(DEFAULT_DURATION, 0);
    }

    /**
     * Function to create a sliding animation from bottom
     * @param duration the duration in milliseconds for which the animation would last
     * @param offset the time offset in milliseconds after which the animation would start
     * @return the slide animation
     */
    public static Animation createSlideInFromBottom(long duration, long offset) {
        float MIN_DELTA = 0.0f;
        float MAX_DELTA = 1.0f;
        Animation slide = new TranslateAnimation(MIN_DELTA, MIN_DELTA, MIN_DELTA, MAX_DELTA);
        slide.setDuration(duration);
        slide.setStartOffset(offset);
        return slide;
    }

    /**
     * Function to create a flip animation along the Y axis by 180 degrees with the default duration
     * @param view the view that shall be flipped
     */
    public static void createYFlipForwardAnimation(View view) {
        createYFlipForwardAnimation(view, DEFAULT_DURATION);
    }

    /**
     * Function to create a flip animation along the Y axis by 180 degrees
     * @param view the view that shall be flipped
     * @param duration the duration in milliseconds for which the animation would last
     */
    public static void createYFlipForwardAnimation(View view, long duration) {
        float MIN_ROTATION = 0.0f;
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", MIN_ROTATION, MID_ROTATION);
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }

    /**
     * Function to create a flip animation along the Y axis by -180 degrees with the default duration
     * @param view the view that shall be flipped
     */
    public static void createYFlipBackwardAnimation(View view) {
        createYFlipBackwardAnimation(view, DEFAULT_DURATION);
    }

    /**
     *  Function to create a flip animation along the Y axis by -180 degrees
     * @param view the view that shall be flipped
     * @param duration the duration in milliseconds for which the animation would last
     */
    public static void createYFlipBackwardAnimation(View view, long duration) {
        float MAX_ROTATION = 360.0f;
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotationY", MID_ROTATION, MAX_ROTATION);
        animation.setDuration(duration);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }
}
