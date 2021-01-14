package com.example.xinyuxin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinyuxin.Beans.CourseItemsBean;
import com.example.xinyuxin.Beans.MessagesItemsBean;
import com.example.xinyuxin.R;

import java.util.ArrayList;

public class CourseListAdapter extends BaseAdapter {
    ArrayList<CourseItemsBean> list = null;
    Context context = null;
    LayoutInflater mInflater = null;

    public CourseListAdapter(ArrayList<CourseItemsBean> list, Context context){
        CourseListAdapter.this.list = list;
        CourseListAdapter.this.context = context;
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
        CourseListAdapter.ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new CourseListAdapter.ViewHolder();
            convertView = mInflater.inflate (R.layout.course_item, null);
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.course_item_courseLogo);
            viewHolder.title = (TextView) convertView.findViewById(R.id.course_item_courseTitle);
            viewHolder.people = (TextView) convertView.findViewById(R.id.course_item_coursePeople);
            viewHolder.time = (TextView) convertView.findViewById(R.id.course_item_courseTime);
            viewHolder.lessons = (TextView) convertView.findViewById(R.id.course_item_courseLessons);
            viewHolder.collects = (TextView) convertView.findViewById(R.id.course_item_couseCollect);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (CourseListAdapter.ViewHolder) convertView.getTag();
        }
        CourseItemsBean bean=list.get(position);
        viewHolder.imgView.setBackgroundResource(bean.courseLogo);
        viewHolder.title.setText(bean.courseTitle);
        viewHolder.people.setText(String.valueOf(bean.coursePeople)+"万人");
        viewHolder.time.setText(String.valueOf(bean.courseTime)+"小时");
        viewHolder.lessons.setText(String.valueOf(bean.courseLessons)+"讲");
        viewHolder.collects.setText(String.valueOf(bean.courseCollect)+"人收藏");

        return convertView;
    }

    public class ViewHolder{
        ImageView imgView;
        TextView title;
        TextView time;
        TextView people;
        TextView lessons;
        TextView collects;
    }
}
