package com.example.xinyuxin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinyuxin.Beans.ArticleItemsBean;
import com.example.xinyuxin.Beans.FmItemsBean;
import com.example.xinyuxin.Beans.MessagesItemsBean;
import com.example.xinyuxin.Entity.MessageInfo;
import com.example.xinyuxin.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeArticleListAdapter extends BaseAdapter {
    public ArrayList<MessageInfo> list = null;
    Context context = null;
    LayoutInflater mInflater = null;

    public HomeArticleListAdapter(ArrayList<MessageInfo> list, Context context){
        HomeArticleListAdapter.this.list = list;
        HomeArticleListAdapter.this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setList(ArrayList<MessageInfo> list){
        HomeArticleListAdapter.this.list = (ArrayList<MessageInfo>) list.clone();
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
            convertView = mInflater.inflate (R.layout.home_article_item, null);
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.homeArticleImg);
            viewHolder.aricleTitle = (TextView) convertView.findViewById(R.id.homeArticleTitle);
            viewHolder.articleAbstract = (TextView) convertView.findViewById(R.id.homeArticleAbstracts);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        MessageInfo bean=list.get(position);
        viewHolder.imgView.setBackgroundResource(bean.img);
        viewHolder.aricleTitle.setText(bean.getTittle());
        viewHolder.articleAbstract.setText("已产生"+bean.getAbstracts()+"条想法");

        return convertView;
    }

    public class ViewHolder{
        ImageView imgView;
        TextView aricleTitle;
        TextView articleAbstract;
    }
}
