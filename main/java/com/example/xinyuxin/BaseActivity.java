package com.example.xinyuxin;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity {
    static List<Activity> list = new ArrayList<Activity>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.remove(this);
    }

    public static void finishAll(){
        for(Activity activity:list){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        list.clear();
    }
}
