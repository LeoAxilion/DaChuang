package com.example.xinyuxin;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinyuxin.Adapters.FragmentAdapter;
import com.example.xinyuxin.Adapters.ViewAdapter;
import com.example.xinyuxin.Fragments.*;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends BaseActivity {
    Button login = null;
    Button register = null;
    TextView tryUsing = null;
    List<Fragment> fragmentList = null;
//    ArrayList<View> viewList = null;
    ViewPager vp;
    FragmentManager fm = null;
    ImageView dot1 = null;
    ImageView dot2 = null;
    ImageView dot3 = null;
    ImageView dot4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        initialize();
        tryUsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FunctionActivity.name = null;
                FunctionActivity.phoneNumber = null;
                Intent intent = new Intent(WelcomeActivity.this,FunctionActivity.class);
                finishAll();
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

//        viewList.add(getLayoutInflater().inflate(R.layout.fragment_welcome_picture_fragment1, null));
//        viewList.add(getLayoutInflater().inflate(R.layout.fragment_welcome_picture_fragment2, null));
//        viewList.add(getLayoutInflater().inflate(R.layout.fragment_welcome_picture_fragment3, null));
//        viewList.add(View.inflate(this,R.layout.fragment_welcome_picture_fragment4, null));
//        vp.setAdapter(new ViewAdapter(viewList));
        fragmentList.add(new WelcomePictureFragment1());
        fragmentList.add(new WelcomePictureFragment2());
        fragmentList.add(new WelcomePictureFragment3());
        fragmentList.add(new WelcomePictureFragment4());
        vp.setAdapter(new FragmentAdapter(fm,fragmentList));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    dotInitialize();
                    WelcomeActivity.this.dot1.setBackgroundResource(R.drawable.dot_blue);
                } else if (position == 1) {
                    dotInitialize();
                    WelcomeActivity.this.dot2.setBackgroundResource(R.drawable.dot_blue);}
                 else if (position == 2) {
                    dotInitialize();
                    WelcomeActivity.this.dot3.setBackgroundResource(R.drawable.dot_blue);
                } else if (position == 3) {
                    dotInitialize();
                    WelcomeActivity.this.dot4.setBackgroundResource(R.drawable.dot_blue);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);
            }
        });
        dot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
            }
        });
        dot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(2);
            }
        });
        dot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(3);
            }
        });
    }

    public void initialize(){
        login = (Button)findViewById(R.id.welcome_login);
        register = (Button)findViewById(R.id.welcome_register);
        tryUsing = (TextView)findViewById(R.id.welcome_tryUsing);
        fragmentList = new ArrayList<Fragment>();
//        viewList = new ArrayList<View>();
        vp = (ViewPager)findViewById(R.id.welcome_viewpager);
        fm = getSupportFragmentManager();
        dot1 = (ImageView)findViewById(R.id.welcome_pageButton1);
        dot2 = (ImageView)findViewById(R.id.welcome_pageButton2);
        dot3 = (ImageView)findViewById(R.id.welcome_pageButton3);
        dot4 = (ImageView)findViewById(R.id.welcome_pageButton4);
        WelcomeActivity.this.dot1.setBackgroundResource(R.drawable.dot_blue);
    }

    public void dotInitialize(){
        WelcomeActivity.this.dot1.setBackgroundResource(R.drawable.dot_dark);
        WelcomeActivity.this.dot2.setBackgroundResource(R.drawable.dot_dark);
        WelcomeActivity.this.dot3.setBackgroundResource(R.drawable.dot_dark);
        WelcomeActivity.this.dot4.setBackgroundResource(R.drawable.dot_dark);
    }
}
