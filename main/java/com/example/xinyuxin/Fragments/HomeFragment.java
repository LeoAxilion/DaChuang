package com.example.xinyuxin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xinyuxin.Adapters.HomeArticleListAdapter;
import com.example.xinyuxin.ArticleViewerActivity;
import com.example.xinyuxin.Entity.MessageInfo;
import com.example.xinyuxin.Entity.ReturnType;
import com.example.xinyuxin.FunctionActivity;
import com.example.xinyuxin.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jorge.circlelibrary.ImageCycleView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;

public class HomeFragment extends Fragment {

    Random random = null;
    View view = null;
    int[] Ideaslogo = {R.drawable.fmlogo1,R.drawable.fmlogo2,R.drawable.fmlogo3,R.drawable.fmlogo4};
    public Context context = null;
    public FunctionActivity activity = null;
    public SwipeRefreshLayout sw = null;
    public TextView refresh = null;
    private String baseUrl_getArticleList="http://120.79.69.218/androidapi/index/message/list/" ;//每次baseurl+数字就是请求一个数据串；
//    private  String baseUrl_getArticle ="http://120.79.69.218/androidapi/index/message/see?id=";//http://120.79.69.218/androidapi/index/message/see?id=12
    private ArrayList<MessageInfo> messageInfosList = null;
    private ListView article_listview ;
    private HomeArticleListAdapter adapter = null;
    View funcBtn1 = null;
    View funcBtn2 = null;
    View funcBtn3 = null;
    View funcBtn4 = null;
    int viewPagerImgResource[] = new int[]{R.drawable.home_view_pager1,R.drawable.home_view_pager2,R.drawable.home_view_pager3,R.drawable.home_view_pager4,R.drawable.home_view_pager5,R.drawable.home_view_pager6,R.drawable.home_view_pager7,R.drawable.home_view_pager8,R.drawable.home_view_pager9};
    int randomIndex1 = 0;
    int randomIndex2 = 0;

    //轮播图
    private ImageCycleView imageCycleView = null;
    ArrayList<String> imageDescList;
    ArrayList<String> urlList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initialize();
        initializeList();
        return view;
    }

    private void initialize() {
        random = new Random();

        sw = view.findViewById(R.id.home_refresh);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                messageInfosList.clear();
                initializeList();
                initCirclePagerView(imageDescList, urlList);
                sw.setRefreshing(false);
                Toast.makeText(getContext(), "主页刷新完成", Toast.LENGTH_SHORT).show();
            }
        });
        funcBtn1 = view.findViewById(R.id.home_FuncBtn1);
        funcBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.this.activity.setPage(3, false);
            }
        });
        funcBtn2 = view.findViewById(R.id.home_FuncBtn2);
        funcBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.this.activity.setPage(2,false);
            }
        });
        funcBtn3 = view.findViewById(R.id.home_FuncBtn3);
        funcBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.this.activity.setPage(1,false);
            }
        });
        funcBtn4 = view.findViewById(R.id.home_FuncBtn4);
        funcBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.this.activity.setPage(3,true);
            }
        });
        messageInfosList = new ArrayList<>();
        article_listview = view.findViewById(R.id.home_listView);
        article_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int articleID = messageInfosList.get(position).getId();
                Intent intent = new Intent(HomeFragment.this.getContext(), ArticleViewerActivity.class);
                intent.putExtra("id", articleID);
                startActivity(intent);
            }
        });
        refresh = view.findViewById(R.id.home_listView_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageInfosList.clear();
                initializeList();
                Toast.makeText(getContext(), "文章列表刷新完成", Toast.LENGTH_SHORT).show();
            }
        });

        //初始化轮播图
        /** 找到轮播控件*/
        imageCycleView= (ImageCycleView) view.findViewById(R.id.home_cycleView);
        // 选择切换类型
        //ImageCycleView.CYCLE_T 有三种类型 ,效果如上图所示
        //CYCLE_VIEW_NORMAL  CYCLE_VIEW_THREE_SCALE   CYCLE_VIEW_ZOOM_IN   可以随意选择
        imageCycleView.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_ZOOM_IN);
        /**装在数据的集合  文字描述*/
        imageDescList=new ArrayList<>();
        /**装在数据的集合  图片地址*/
        urlList=new ArrayList<>();

        initCirclePagerView(imageDescList, urlList);
    }

    private void initializeListView(){
        if(adapter == null){
            adapter = new HomeArticleListAdapter(messageInfosList, HomeFragment.this.getContext());
            article_listview.setAdapter(adapter);
        }
        else{
            adapter = new HomeArticleListAdapter(messageInfosList, HomeFragment.this.getContext());
            article_listview.setAdapter(adapter);
        }
        Log.d("loginfo2", "onResponse: "+messageInfosList.size());
        article_listview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dipTopx(getContext(),messageInfosList.size()*55-2)));
        adapter.notifyDataSetChanged();
    }

    public void initializeList() {
        randomIndex1 = random.nextInt()%8;
        if(randomIndex1<=0){
            randomIndex1 += 8;
        }
        OkHttpUtils.get()//这是一个封装的静态方法，用来请求网站的，封装的很好
                .url(baseUrl_getArticleList + randomIndex1).build().execute(new StringCallback() {//每次baseurl+数字就是请求一个数据串；
            //http://120.79.69.218/androidapi/index/message/list/1
            /*示例：在浏览器里输入上面的连接（URL），会显示这个东西，我们就是要获取这个东西，这是一
            //个哈希表，可以看到"data"里面还嵌套一个哈希表
            {"code":200,"message":"","data":{"pagenum":1,"list":[{"id":13,"tittle":"qingshaonian jiankang ","abstracts":"<p>j,sdbcuejgkiawdhhvi lhcsk&nbsp;</p><p>4d</p><p>sigjdilsdjmv d</p><p><br data-mce-bogus=\"1\"></p><p>ed lerkh q /</p><p>w</p><p>slkdhfjksdhf</p><p>&nbsp;kljfksdhjflkdh lj&nbsp;</p><p><br data-mce-bogus=\"1\"></p><p>dmls;jglskd</p>"},{"id":12,"tittle":"阿达","abstracts":"<p>asdddddddddddddddddddddddddd</p>"}]}}


            */

            @Override
            public void onError(Call call, Exception e, int id) {//请求失败的方法，不需要你自己去判断是不是失败，
                //***************************************
                //这里需要加一个Toast，显示加载失败
                //*************************************
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson() ;
                ReturnType returnType =gson.fromJson(response, ReturnType.class) ;//用一个returnType对象捕获请求返回的数据
                if(returnType.getCode()==200){
                    JsonObject object =  returnType.getData() ;
                    JsonArray array = object.getAsJsonArray("list") ;
                    for (JsonElement item : array){//这是遍历

                        MessageInfo info = gson.fromJson(item,MessageInfo.class) ;
                        info.img = Ideaslogo[info.getId()%4];
                        //**********
                        /*
                        info.getTitle()获取标题
                        info.getAbstarts()获取摘要
                        怎么给listview传这样的item

                         */
                        //**********
                        //HomeFragment.this.messageInfos.add(info);
                        messageInfosList.add(info);
                    }

                }
                initializeListView();
                Log.d("loginfo3", "onResponse: "+messageInfosList.size());
            }
        });
    }

    public static int dipTopx(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void initCirclePagerView(ArrayList<String> imageDescList, ArrayList<String> urlList) {
        randomIndex2 = random.nextInt()%7;
        if(randomIndex2<0)
            randomIndex2 += 7;

        /**添加数据*/
        urlList.clear();
        urlList.add("1");
        urlList.add("2");
        urlList.add("3");

        //添加名称
        imageDescList.clear();
        imageDescList.add("课程推荐");
        imageDescList.add("课程推荐");
        imageDescList.add("课程推荐");
        ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                /**实现点击事件*/
            }
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                /**在此方法中，显示图片，可以用自己的图片加载库，也可以用本demo中的（Imageloader）
                ImageLoaderHelper.getInstance().loadImage(imageURL, imageView);*/
                if(imageURL == "1")
                    imageView.setBackgroundResource(viewPagerImgResource[randomIndex2]);
                    //imageView.setImageResource(R.drawable.home_view_pager1);
                else if(imageURL == "2")
                    imageView.setBackgroundResource(viewPagerImgResource[randomIndex2+1]);
                else if(imageURL == "3")
                    imageView.setBackgroundResource(viewPagerImgResource[randomIndex2+2]);
            }
        };
        /**设置数据*/
        imageCycleView.setImageResources(imageDescList,urlList, mAdCycleViewListener);
        // 是否隐藏底部
        imageCycleView.hideBottom(true);
        imageCycleView.startImageCycle();
    }
}
