package com.example.xinyuxin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinyuxin.Beans.CourseItemsBean;
import com.example.xinyuxin.Beans.FmItemsBean;
import com.example.xinyuxin.R;

import java.util.ArrayList;

public class FmListAdapter extends BaseAdapter {
    ArrayList<FmItemsBean> list = null;
    Context context = null;
    LayoutInflater mInflater = null;

    public FmListAdapter(ArrayList<FmItemsBean> list, Context context){
        FmListAdapter.this.list = (ArrayList<FmItemsBean>) list.clone();
        FmListAdapter.this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FmListAdapter.ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new FmListAdapter.ViewHolder();
            convertView = mInflater.inflate (R.layout.fm_item, null);
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.ideasFragment_fmLogo);
            viewHolder.title = (TextView) convertView.findViewById(R.id.ideasFragment_fmTitle);
            viewHolder.people = (TextView) convertView.findViewById(R.id.ideasFragment_fmNum);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (FmListAdapter.ViewHolder) convertView.getTag();
        }
        FmItemsBean bean=list.get(position);
        viewHolder.imgView.setBackgroundResource(bean.fmLogo);
        viewHolder.title.setText(bean.fmTitle);
        viewHolder.people.setText(String.valueOf(bean.fmPeople)+"万人");

        return convertView;
    }

    public class ViewHolder{
        ImageView imgView;
        TextView title;
        TextView people;
    }

    public void setList(ArrayList<FmItemsBean> list, Context context) {
        FmListAdapter.this.list = (ArrayList<FmItemsBean>) list.clone();
        FmListAdapter.this.context = context;
        mInflater = LayoutInflater.from(context);
    }
}
