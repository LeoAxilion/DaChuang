package com.example.xinyuxin;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


/*使用方法: 在启动该Activity前先调用静态的chooseURI(String)方法，参数为本地视频存储位置或网络视频URI*/

public class VideoActivity extends BaseActivity {

    private VideoView videoView;
    public static String videoUrl;
    Button turnBack = null;

    static public void chooseURI(String uri){
        videoUrl = uri;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().hide();
        initialize();
        if(videoUrl.equals("")){
            videoUrl = "http://ykugc.cp31.ott.cibntv.net/657646A87B33C71868100551A/030020010050880D390DF807DFDE898B721C0C-53F8-57EE-AD4E-267A83E8DB62.mp4?ccode=050F&duration=7035&expire=18000&psid=ce7e2d55ccbef3ce5a1cac5121dc3ee9&ups_client_netip=7337bc2e&ups_ts=1561549698&ups_userid=&utid=wibUFFXdGTsCAXOv5sOxajHp&vid=XMjEzNjg5ODMyMA%3D%3D&vkey=A93d43aac769d205fb8854048bb2083c9&sp=&bc=1";
        }
        //本地的视频  需要在手机SD卡根目录添加一个 fl1234.mp4 视频
//        videoUrl = Environment.getExternalStorageDirectory().getPath()+"/fl1234.mp4" ;
        //网络视频
//        String videoUrl2 =  ;

        Uri uri = Uri.parse( videoUrl );
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener( new MyPlayerOnCompletionListener());
        //设置视频路径
        videoView.setVideoURI(uri);
        //开始播放视频
        videoView.start();
    }

    private void initialize() {
        turnBack = findViewById(R.id.video_turnback);
        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        videoView = (VideoView)this.findViewById(R.id.videoView );
    }

    private class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( VideoActivity.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
