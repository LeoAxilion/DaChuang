package com.example.xinyuxin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xinyuxin.Adapters.CourseListAdapter;
import com.example.xinyuxin.Beans.CourseItemsBean;
import com.example.xinyuxin.R;
import com.example.xinyuxin.VideoActivity;

import java.util.ArrayList;


public class CourseFragment extends Fragment {

    View view = null;
    TextView newClass = null;
    TextView newNum = null;
    ListView listView = null;
    ArrayList<CourseItemsBean> list = null;
    Context context = null;
    CourseListAdapter adapter = null;
    SwipeRefreshLayout sw = null;
    int[] courselogo = {R.drawable.courselogo1,R.drawable.courselogo2,R.drawable.courselogo3,R.drawable.courselogo4};
    String[] coursetitle = {"走出心理创伤：重塑强大内心","好像抑郁了，应该怎么办？","如何克服压力和紧张，发挥出最佳水平","患了【努力焦虑症】，怎么办？"};
    double[] coursetime = {4.8,7,7,2};
    long[] courselessons = {30,50,50,10};
    int[] coursepeople = {36,28,26,21};
    long[] coursecollect = {4396,4038,2322,1655};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_course, container, false);
        initializeList();
        initialize();
        return view;
    }

    private void initializeList() {
        list = new ArrayList<>();
        for(int i =0;i<4;i++){
            CourseItemsBean c = new CourseItemsBean();
            c.courseTitle = coursetitle[i];
            c.courseLogo = courselogo[i];
            c.courseTime = coursetime[i];
            c.courseLessons = courselessons[i];
            c.coursePeople = coursepeople[i];
            c.courseCollect = coursecollect[i];
            list.add(c);
        }
    }

    private void initialize() {
        sw  = view.findViewById(R.id.course_swipeRefreshLayout);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新课程列表

                sw.setRefreshing(false);
                Toast.makeText(getContext(), "课程列表刷新完成", Toast.LENGTH_SHORT);
            }
        });
        newClass = view.findViewById(R.id.courseFragment_newTitle);
        newNum = view.findViewById(R.id.courseFragment_Num);
        listView = view.findViewById(R.id.courseFragment_courseListView);
        adapter = new CourseListAdapter(list, getContext());
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        VideoActivity.videoUrl = "https://video.myzx.cn/1ddbd6a00aee483ea672f1eb1927b4a4/963661bc9b57430581fc74c9f53e358c-ad7ac1f8753a98345163f25bbd930c07-fd.mp4";break;
                    case 1:
                        VideoActivity.videoUrl = "https://vde.jiankang.com/mingyi/liyong7/5.mp4" ;break;
                    case 2:
                        VideoActivity.videoUrl = "https://vd2.bdstatic.com/mda-iepkmaj8n8189wpa/sc/mda-iepkmaj8n8189wpa.mp4?auth_key=1561552969-0-0-2ce799fd43fc4b8c8655bf2b03b12776&amp;bcevod_channel=searchbox_feed&amp;pd=bjh&amp;abtest=all" ;break;
                    case 3:
                        VideoActivity.videoUrl = "https://vde.jiankang.com/mingyi/lijijun/19.mp4" ;break;
                    default:
                        VideoActivity.videoUrl = "http://ykugc.cp31.ott.cibntv.net/657646A87B33C71868100551A/030020010050880D390DF807DFDE898B721C0C-53F8-57EE-AD4E-267A83E8DB62.mp4?ccode=050F&duration=7035&expire=18000&psid=ce7e2d55ccbef3ce5a1cac5121dc3ee9&ups_client_netip=7337bc2e&ups_ts=1561549698&ups_userid=&utid=wibUFFXdGTsCAXOv5sOxajHp&vid=XMjEzNjg5ODMyMA%3D%3D&vkey=A93d43aac769d205fb8854048bb2083c9&sp=&bc=1" ; ;break;

                }
                Intent intent = new Intent(getContext(),VideoActivity.class);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listViewListener);
    }
}
