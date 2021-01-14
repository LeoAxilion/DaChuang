package com.example.xinyuxin;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xinyuxin.Adapters.FunctionsPagerAdapter;
import com.example.xinyuxin.Fragments.CourseFragment;
import com.example.xinyuxin.Fragments.DataFragment;
import com.example.xinyuxin.Fragments.HomeFragment;
import com.example.xinyuxin.Fragments.IdeasFragment;
import com.example.xinyuxin.Fragments.MessagesFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunctionActivity extends BaseActivity {
    static public String phoneNumber = null;
    static public String name = null;

    public ViewPager viewPager = null;
    HomeFragment homeFragment = null;
    CourseFragment courseFragment = null;
    MessagesFragment messagesFragment = null;
    IdeasFragment ideasFragment = null;
    DataFragment dataFragment = null;

    List<Fragment> fragmentList = null;
    FunctionsPagerAdapter viewAdapter = null;

    RelativeLayout background = null;
    EditText search = null;
    FrameLayout searchBtn = null;

    Button btn1 = null;
    Button btn2 = null;
    Button btn3 = null;
    Button btn4 = null;
    Button btn5 = null;
    Button btn1_no = null;
    Button btn2_no = null;
    Button btn3_no = null;
    Button btn4_no = null;
    Button btn5_no = null;

    View mediaControl = null;
    ImageView mediaControlImg = null;
    boolean mediaFlag = false;
    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        getSupportActionBar().hide();
        initialize();
    }

    private void initialize() {
        btn1 = findViewById(R.id.funcButton1_click);
        btn2 = findViewById(R.id.funcButton2_click);
        btn3 = findViewById(R.id.funcButton3_click);
        btn4 = findViewById(R.id.funcButton4_click);
        btn5 = findViewById(R.id.funcButton5_click);
        btn1_no = findViewById(R.id.funcButton1_no);
        btn2_no = findViewById(R.id.funcButton2_no);
        btn3_no = findViewById(R.id.funcButton3_no);
        btn4_no = findViewById(R.id.funcButton4_no);
        btn5_no = findViewById(R.id.funcButton5_no);

        mediaControlImg = findViewById(R.id.function_mediaControlImg);
        mediaControl = findViewById(R.id.function_mediaControl);
        mediaControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null){
                    if(FunctionActivity.this.mediaFlag == true){
                        mediaPlayer.stop();
                        mediaControlImg.setImageResource(R.drawable.media_play);
                        FunctionActivity.this.mediaFlag = false;
                    }else{
                        mediaPlayer.start();
                        mediaControlImg.setImageResource(R.drawable.media_stop);
                        mediaFlag = true;
                    }
                }else{
                    if(FunctionActivity.this.mediaFlag == true){
                        mediaControlImg.setImageResource(R.drawable.media_play);
                        FunctionActivity.this.mediaFlag = false;
                    }else{
                        mediaControlImg.setImageResource(R.drawable.media_stop);
                        mediaFlag = true;
                    }
                }
            }
        });

        viewPager = findViewById(R.id.FuncViewPager);
        viewPager. addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    btn1.setAlpha((float)(1.0-positionOffset));
                    btn2_no.setAlpha((float)(1.0-positionOffset));
                    btn1_no.setAlpha(positionOffset);
                    btn2.setAlpha(positionOffset);
                }
                else if(position==1){
                    btn2.setAlpha((float)(1.0-positionOffset));
                    btn3_no.setAlpha((float)(1.0-positionOffset));
                    btn2_no.setAlpha(positionOffset);
                    btn3.setAlpha(positionOffset);

                }
                else if(position==2){
                    btn3.setAlpha((float)(1.0-positionOffset));
                    btn4_no.setAlpha((float)(1.0-positionOffset));
                    btn3_no.setAlpha(positionOffset);
                    btn4.setAlpha(positionOffset);
                }
                else if(position==3){
                    btn4.setAlpha((float)(1.0-positionOffset));
                    btn5_no.setAlpha((float)(1.0-positionOffset));
                    btn4_no.setAlpha(positionOffset);
                    btn5.setAlpha(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    initiallizeButton();
                    btn1.setAlpha(1);
                    btn1_no.setAlpha(0);
                } else if (position == 1) {
                    initiallizeButton();
                    btn2.setAlpha(1);
                    btn2_no.setAlpha(0);
                } else if (position == 2) {
                    initiallizeButton();
                    btn3.setAlpha(1);
                    btn3_no.setAlpha(0);
                } else if (position == 3) {
                    initiallizeButton();
                    btn4.setAlpha(1);
                    btn4_no.setAlpha(0);
                } else if (position == 4) {
                    initiallizeButton();
                    btn4.setAlpha(1);
                    btn4_no.setAlpha(0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fragmentList = new ArrayList<Fragment>();

        homeFragment = new HomeFragment();
        homeFragment.activity = this;
        courseFragment = new CourseFragment();
        messagesFragment = new MessagesFragment();
        ideasFragment = new IdeasFragment();
        ideasFragment.activity = this;
        dataFragment = new DataFragment();
        dataFragment.activity = FunctionActivity.this;

        fragmentList.add(homeFragment);
        fragmentList.add(courseFragment);
        fragmentList.add(messagesFragment);
        fragmentList.add(ideasFragment);
        fragmentList.add(dataFragment);

        viewAdapter = new FunctionsPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(viewAdapter);

        search = findViewById(R.id.function_searchText);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setFocusable(true);
                search.setFocusableInTouchMode(true);
                search.requestFocus();
                search.findFocus();
            }
        });
        searchBtn = findViewById(R.id.function_searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        background = findViewById(R.id.function_background);
        background.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(FunctionActivity.this.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search.getWindowToken(), 0);
                background.requestFocus();
                search.setFocusable(false);
                return false;
            }
        });

        btn1_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        btn2_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        btn3_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
        btn4_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
            }
        });
        btn5_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4);
            }
        });
    }

    public void initiallizeButton() {
        btn1.setAlpha(0);
        btn1_no.setAlpha(1);
        btn2.setAlpha(0);
        btn2_no.setAlpha(1);
        btn3.setAlpha(0);
        btn3_no.setAlpha(1);
        btn4.setAlpha(0);
        btn4_no.setAlpha(1);
        btn5.setAlpha(0);
        btn5_no.setAlpha(1);
    }

    public void setPage(int index, boolean flag){
        viewPager.setCurrentItem(index);
        if(flag)
            ideasFragment.tabHost.setCurrentTab(1);
        else{
            ideasFragment.tabHost.setCurrentTab(0);
        }
    }

    public boolean setMediaPlayer(String uri){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mediaPlayer = new MediaPlayer();
        mediaFlag = true;
        mediaControlImg.setImageResource(R.drawable.media_stop);

        try {
            mediaPlayer.setDataSource (uri);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        // 通过异步的方式装载媒体资源
        mediaPlayer.prepareAsync();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 装载完毕回调
                mediaPlayer.start();
                mediaControlImg.setImageResource(R.drawable.media_stop);
                mediaFlag = true;
            }
        });
        mediaPlayer.setLooping(true);
        return true;
    }

    @Override
    protected void onDestroy() {
        phoneNumber = null;
        name = null;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
