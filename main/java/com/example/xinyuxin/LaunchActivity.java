package com.example.xinyuxin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.example.xinyuxin.Config.Config;

public class LaunchActivity extends BaseActivity {
    SharedPreferences sharedPreferences=null;
    SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ActionBar actionBar = getSupportActionBar();
        if( actionBar !=null){
            actionBar.hide();
        }
        Config.cookieConfig(getApplicationContext());
        final ImageView image = (ImageView) findViewById(R.id.welcome);
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(1000);
        animationSet.addAnimation(alphaAnimation);
        image.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                image.setBackgroundResource(R.drawable.logo);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(LaunchActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
