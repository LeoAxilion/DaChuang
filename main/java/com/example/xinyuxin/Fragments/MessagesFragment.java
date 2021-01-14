package com.example.xinyuxin.Fragments;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.xinyuxin.Adapters.MessagesListAdapter;
import com.example.xinyuxin.Beans.MessagesItemsBean;
import com.example.xinyuxin.R;

import java.util.ArrayList;

public class MessagesFragment extends Fragment {

    ArrayList<MessagesItemsBean> list1 = null;
    ArrayList<MessagesItemsBean> list2 = null;
    View view = null;
    public Context context = null;
    TabHost tabHost = null;
    ListView listView1 = null;
    ListView listView2 = null;
    MessagesListAdapter adapter1 = null;
    MessagesListAdapter adapter2 = null;

    SwipeRefreshLayout sw1 = null;
    SwipeRefreshLayout sw2 = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_messages, container, false);
        initializeList();
        initialize();
        return view;
    }

    private void initializeList(){
        list1 = new ArrayList<MessagesItemsBean>();
        //list1.add(new MessagesItemsBean());
        list2 = new ArrayList<MessagesItemsBean>();
        list2.add(new MessagesItemsBean());
    }

    private void initialize() {
        tabHost = view.findViewById(R.id.messages_tabHost);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("通知" , null).setContent(R.id.messages_tabHost_tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("留言" , null).setContent(R.id.messages_tabHost_tab2));

        listView1 = view.findViewById(R.id.messages_tabHost_fragment1_listView);
        adapter1 = new MessagesListAdapter(list1, getContext());
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        listView2 = view.findViewById(R.id.messages_tabHost_fragment2_listView);
        adapter2 = new MessagesListAdapter(list2, getContext());
        listView2.setAdapter(adapter2);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        sw1 = view.findViewById(R.id.messages_swipeRefreshLayout1);
        sw1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新通知列表

                sw1.setRefreshing(false);
                Toast.makeText(getContext(), "通知列表刷新完成", Toast.LENGTH_SHORT).show();
            }
        });
        sw2 = view.findViewById(R.id.messages_swipeRefreshLayout2);
        sw2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新留言列表

                sw2.setRefreshing(false);
                Toast.makeText(getContext(), "留言列表刷新完成", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
