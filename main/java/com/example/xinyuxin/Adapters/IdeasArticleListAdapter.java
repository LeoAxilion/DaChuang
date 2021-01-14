package com.example.xinyuxin.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xinyuxin.Beans.ArticleItemsBean;
import com.example.xinyuxin.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class IdeasArticleListAdapter extends BaseAdapter {
    ArrayList<ArticleItemsBean> list = null;
    Context context = null;
    LayoutInflater mInflater = null;

    public IdeasArticleListAdapter(ArrayList<ArticleItemsBean> list, Context context){
        IdeasArticleListAdapter.this.list = list;
        IdeasArticleListAdapter.this.context = context;
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
            convertView = mInflater.inflate (R.layout.article_item, null);
            viewHolder.imgView = (ImageView) convertView.findViewById(R.id.articleImg);
            viewHolder.aricleTitle = (TextView) convertView.findViewById(R.id.ArticleItemTitle);
            viewHolder.num = (TextView) convertView.findViewById(R.id.ArticleNum);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ArticleItemsBean bean=list.get(position);
        viewHolder.imgView.setImageDrawable(bean.img);
        viewHolder.aricleTitle.setText(bean.title);
        viewHolder.num.setText("已产生"+Double.toString(bean.num)+"条想法");

        return convertView;
    }

    public class ViewHolder{
        ImageView imgView;
        TextView aricleTitle;
        TextView num;
    }
}
