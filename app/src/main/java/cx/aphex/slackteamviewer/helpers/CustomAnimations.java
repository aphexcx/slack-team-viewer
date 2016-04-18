package cx.aphex.slackteamviewer.helpers;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewAnimationUtils;

import cx.aphex.slackteamviewer.R;


public class CustomAnimations {

    @NonNull
    public static Animator circularReveal(@NonNull View v) {
        // previously invisible view.
        // get the center for the clipping circle
        int cx = v.getWidth() / 2;
        int cy = v.getHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(v.getWidth(), v.getHeight());

        Animator anim;
        if (android.os.Build.VERSION.SDK_INT >= 21) { //ViewAnimationUtils is only API >= 21
            // create the animator for this view (the start radius is zero)
            anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
        } else { //Or fall back to a simple fade in
            anim = AnimatorInflater.loadAnimator(v.getContext(), R.animator.fade_in);
            anim.setDuration(v.getResources().getInteger(android.R.integer.config_shortAnimTime));
            anim.setTarget(v);
        }

        // make the view visible and start the animation
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                v.setVisibility(View.VISIBLE);
            }
        });
        return anim;
    }

    @NonNull
    public static Animator circularHide(@NonNull View v) {
        // previously visible view
        // get the center for the clipping circle
        int cx = v.getWidth() / 2;
        int cy = v.getHeight() / 2;

        // get the initial radius for the clipping circle
        int initialRadius = v.getWidth();

        // create the animation (the final radius is zero)
        Animator anim;
        if (android.os.Build.VERSION.SDK_INT >= 21) { //ViewAnimationUtils is only API >= 21
            anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, initialRadius, 0);
        } else { //Or fall back to a simple fade out
            anim = AnimatorInflater.loadAnimator(v.getContext(), R.animator.fade_out);
            anim.setDuration(v.getResources().getInteger(android.R.integer.config_shortAnimTime));
            anim.setTarget(v);
        }

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setVisibility(View.INVISIBLE);
            }
        });
        return anim;
    }

}

