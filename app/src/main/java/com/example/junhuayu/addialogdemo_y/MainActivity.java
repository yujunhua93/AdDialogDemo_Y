package com.example.junhuayu.addialogdemo_y;

import android.app.Activity;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class MainActivity extends Activity {


    SpringAnimation springAnimation;

    View view;
    View contentView;
    ViewGroup rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpringForce springForce = new SpringForce(0)
                .setStiffness(SpringForce.STIFFNESS_MEDIUM)
                .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
        springAnimation = new SpringAnimation(findViewById(R.id.tv),SpringAnimation.TRANSLATION_Y).setSpring(springForce);
        rootView = (ViewGroup) this.getWindow().getDecorView();
        view = LayoutInflater.from(this).inflate(R.layout.ad_dialog_layout,null);
        contentView = view.findViewById(R.id.content);

        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showView();
            }
        });

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDialog();
            }
        });
    }

    private void closeDialog() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rootView.removeView(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rootView.startAnimation(alphaAnimation);

    }

    private void showView() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(view,params);
        SpringAnimation signUpBtnAnimY = new SpringAnimation(contentView,SpringAnimation.TRANSLATION_Y,0);
        signUpBtnAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        signUpBtnAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
        signUpBtnAnimY.setStartVelocity(10000);
        signUpBtnAnimY.start();
    }


}
