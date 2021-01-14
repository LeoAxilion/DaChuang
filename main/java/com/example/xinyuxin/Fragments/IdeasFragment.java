package com.example.xinyuxin.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xinyuxin.Adapters.FmListAdapter;
import com.example.xinyuxin.Adapters.HomeArticleListAdapter;
import com.example.xinyuxin.Adapters.IdeasArticleListAdapter;
import com.example.xinyuxin.ArticleViewerActivity;
import com.example.xinyuxin.Beans.ArticleItemsBean;
import com.example.xinyuxin.Beans.FmItemsBean;
import com.example.xinyuxin.Entity.MessageInfo;
import com.example.xinyuxin.Entity.ReturnType;
import com.example.xinyuxin.FunctionActivity;
import com.example.xinyuxin.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;


public class IdeasFragment extends Fragment {

    Random random = null;
    int randomIndex = 0;
    View view = null;
    public TabHost tabHost = null;

    ArrayList<FmItemsBean> fmList = null;
    ListView fmListView = null;
    FmListAdapter fmAdapter = null;
    ImageView imageView = null;
    TextView newfmTitle = null;
    TextView newfmNum = null;

    private String baseUrl_getArticleList="http://120.79.69.218/androidapi/index/message/list/" ;
    ArrayList<MessageInfo> articleList = null;
    ListView articleListView = null;
    HomeArticleListAdapter articleAdapter = null;

    SwipeRefreshLayout sw1 = null;//fm列表刷新
    SwipeRefreshLayout sw2 = null;//想法列表刷新

    int[] fmLogo = {R.drawable.fmlogo1,R.drawable.fmlogo2,R.drawable.fmlogo3,R.drawable.fmlogo4};
    int[] Ideaslogo = {R.drawable.courselogo1,R.drawable.courselogo2,R.drawable.courselogo3,R.drawable.courselogo4};
    String[] fmTitle = {"当代“蜗牛人”准则：行吧，好吧，算了吧","让我们慢慢成为适合对方的人","孤独，是一个人最好的增值期","人，一旦丢了良心"};
    double[] fmPeople = {6,5.2,4.6,3.7};

    public FunctionActivity activity = null;

    public IdeasFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ideas, container, false);
        initialize();
        initializeFmList();
        initializeArticleList();
        return view;
    }

    private void initializeFmList() {
        for(int i =0;i<4;i++){
            FmItemsBean f = new FmItemsBean();
            f.fmLogo = fmLogo[i];
            f.fmPeople = fmPeople[i];
            f.fmTitle = fmTitle[i];
            fmList.add(f);
        }
        initializeFm();
    }

    private void initializeArticleList(){
        randomIndex = random.nextInt()%4;
        if(randomIndex<=0){
            randomIndex += 4;
        }
        OkHttpUtils.get().url(baseUrl_getArticleList + randomIndex).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {}

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson() ;
                ReturnType returnType =gson.fromJson(response, ReturnType.class) ;//用一个returnType对象捕获请求返回的数据
                if(returnType.getCode()==200){
                    JsonObject object =  returnType.getData() ;
                    JsonArray array = object.getAsJsonArray("list") ;
                    for (JsonElement item : array){
                        MessageInfo info = gson.fromJson(item,MessageInfo.class) ;
                        info.img = Ideaslogo[info.getId()%4];
                        articleList.add(info);
                    }
                }
                initializeArticle();
            }
        });
        randomIndex+=5;
        OkHttpUtils.get().url(baseUrl_getArticleList + randomIndex).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {}

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson() ;
                ReturnType returnType =gson.fromJson(response, ReturnType.class) ;//用一个returnType对象捕获请求返回的数据
                if(returnType.getCode()==200){
                    JsonObject object =  returnType.getData() ;
                    JsonArray array = object.getAsJsonArray("list") ;
                    for (JsonElement item : array){
                        MessageInfo info = gson.fromJson(item,MessageInfo.class) ;
                        info.img = Ideaslogo[info.getId()%4];
                        articleList.add(info);
                    }
                }
                initializeArticle();
            }
        });
        randomIndex+=5;
        OkHttpUtils.get().url(baseUrl_getArticleList + randomIndex).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {}

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson() ;
                ReturnType returnType =gson.fromJson(response, ReturnType.class) ;//用一个returnType对象捕获请求返回的数据
                if(returnType.getCode()==200){
                    JsonObject object =  returnType.getData() ;
                    JsonArray array = object.getAsJsonArray("list") ;
                    for (JsonElement item : array){
                        MessageInfo info = gson.fromJson(item,MessageInfo.class) ;
                        info.img = Ideaslogo[info.getId()%4];
                        articleList.add(info);
                    }
                }
                initializeArticle();
            }
        });
    }

    private void initializeArticle(){
        if(articleAdapter == null){
            articleAdapter = new HomeArticleListAdapter(articleList, IdeasFragment.this.getContext());
            articleListView.setAdapter(articleAdapter);
        }
        else{
            articleAdapter = new HomeArticleListAdapter(articleList, IdeasFragment.this.getContext());
            articleListView.setAdapter(articleAdapter);
        }
    }

    private void initializeFm() {
        if(articleAdapter == null){
            fmAdapter = new FmListAdapter(fmList, getContext());
            fmListView.setAdapter(fmAdapter);
        }
        else{
            fmAdapter = new FmListAdapter(fmList, getContext());
            fmListView.setAdapter(fmAdapter);
        }
    }

    private void initialize() {
        random = new Random();
        articleListView = view.findViewById(R.id.ideas_tabHost_fragment2_listView);
        fmListView = view.findViewById(R.id.ideasFragment_fmListView);
        imageView = view.findViewById(R.id.ideasFragment_newFmLogo);
        newfmTitle = view.findViewById(R.id.ideasFragment_newFmTitle);
        newfmNum = view.findViewById(R.id.ideasFragment_newFmNum);
        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int articleID = articleList.get(position).getId();
                Intent intent = new Intent(IdeasFragment.this.getContext(), ArticleViewerActivity.class);
                intent.putExtra("id", articleID);
                startActivity(intent);
            }
        });
        fmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置播放
                switch (position){
                    case 0:activity.setMediaPlayer("http://sc1.111ttt.cn:8282/2016/1/12/10/205101640205.mp3?tflag=1546606800&pin=97bb2268ae26c20fe093fd5b0f04be80");break;
                    case 1:activity.setMediaPlayer("http://sc1.111ttt.cn:8282/2018/1/03/13/396131229550.mp3?tflag=1546606800&pin=97bb2268ae26c20fe093fd5b0f04be80");break;
                    case 2:activity.setMediaPlayer("http://other.web.re01.sycdn.kuwo.cn/56d14634e5289f8509f724c3457bb5ab/5d1397ae/resource/n3/62/73/3778738808.mp3");break;
                    case 3:activity.setMediaPlayer("http://sc1.111ttt.cn:8282/2017/1/11/11/304112002593.mp3?tflag=1546606800&pin=97bb2268ae26c20fe093fd5b0f04be80");break;
                    default:
                        activity.setMediaPlayer("http://sg.sycdn.kuwo.cn/d80f8eb37d6727243b8dd3e1468a2371/5d139af1/resource/n1/32/49/4213418590.mp3");break;
                }
                imageView.setImageResource(fmLogo[position]);
                newfmTitle.setText(fmTitle[position]);
                newfmNum.setText(fmPeople[position]+"万人");
            }
        });
        fmList = new ArrayList<>();
        articleList = new ArrayList<>();
        tabHost = view.findViewById(R.id.ideas_tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("FM电台" , null).setContent(R.id.ideas_tabHost_tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("想法" , null).setContent(R.id.ideas_tabHost_tab2));

        sw1 = view.findViewById(R.id.ideas_swipeRefreshLayout1);
        sw1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新FM列表

                fmList.clear();
                initializeFmList();
                sw1.setRefreshing(false);
                Toast.makeText(getContext(), "FM列表刷新完成", Toast.LENGTH_SHORT).show();
            }
        });
        sw2 = view.findViewById(R.id.ideas_swipeRefreshLayout2);
        sw2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新想法列表

                articleList.clear();
                initializeArticleList();
                sw2.setRefreshing(false);
                Toast.makeText(getContext(), "想法列表刷新完成", Toast.LENGTH_SHORT).show();
            }
        });
    }

}