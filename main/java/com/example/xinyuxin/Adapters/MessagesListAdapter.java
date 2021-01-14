package com.example.xinyuxin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinyuxin.Beans.ArticleItemsBean;
import com.example.xinyuxin.Beans.MessagesItemsBean;
import com.example.xinyuxin.R;

import java.util.ArrayList;

public class MessagesListAdapter extends BaseAdapter {
    ArrayList<MessagesItemsBean> list = null;
    Context context = null;
    LayoutInflater mInflater = null;

    public MessagesListAdapter(ArrayList<MessagesItemsBean> list, Context context){
        MessagesListAdapter.this.list = list;
        MessagesListAdapter.this.context = context;
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
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate (R.layout.messages_item, null);
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.messages_item_img);
            viewHolder.name = (TextView) convertView.findViewById(R.id.messages_item_name);
            viewHolder.content = (TextView) convertView.findViewById(R.id.messages_item_content);
            viewHolder.time = (TextView) convertView.findViewById(R.id.messages_item_time);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessagesItemsBean bean=list.get(position);
//        viewHolder.imgView;
        viewHolder.name.setText(bean.name);
        viewHolder.content.setText(bean.content);
        viewHolder.time.setText(bean.time);

        return convertView;
    }

    public class ViewHolder{
        ImageView imgView;
        TextView name;
        TextView content;
        TextView time;
    }
}
