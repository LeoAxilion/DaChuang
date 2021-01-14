package com.example.xinyuxin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.security.PrivateKey;

public class ArticleViewerActivity extends BaseActivity {
private WebView webView = null;
private  String baseUrl="http://120.79.69.218/androidapi/index/message/see?id=";
Button turnBack = null;
//*************************
    //xml需要有个返回按钮
    //********************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_viewer);
        getSupportActionBar().hide();
        turnBack = findViewById(R.id.articleViewer_turnback);
        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        int i = getIntent().getIntExtra("id", -1);
        baseUrl += i;
//        baseUrl="www.baidu.com";
        //******************
        //需要获取id
        //baseUrl+=id;

        webView = (WebView)this.findViewById(R.id.webView);
        webView.loadUrl(baseUrl);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==event.KEYCODE_BACK)
        {
            if(webView.canGoBack())
            {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
