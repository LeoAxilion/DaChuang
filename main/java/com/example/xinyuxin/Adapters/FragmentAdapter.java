package com.example.xinyuxin.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Xiao Lin on 2018/8/23.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mList;


    public FragmentAdapter(FragmentManager fm, List<Fragment> list){
        super(fm);
        mList = list;
    }


    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
