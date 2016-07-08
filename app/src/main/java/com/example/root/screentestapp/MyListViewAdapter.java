package com.example.root.screentestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 06-07-16.
 */
public class MyListViewAdapter extends BaseAdapter{

    private LayoutInflater mInflaterNews;
    List<ReportResponse> mNewsList = new ArrayList<ReportResponse>();

    public MyListViewAdapter(Context ctx){
        mInflaterNews = LayoutInflater.from(ctx);

    }

    public void setDataNews(List<ReportResponse> mNewsList){
        this.mNewsList = mNewsList;
    }

    @Override
    public int getCount() {
        return this.mNewsList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mNewsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ReportResponse data = this.mNewsList.get(i);
        if(convertView == null){
            convertView = mInflaterNews.inflate(R.layout.list_item_news,null);
            System.out.println("item created for "+i);
        }

        ((TextView)convertView.findViewById(R.id.textView2)).setText(data.title);
        ((TextView)convertView.findViewById(R.id.textView3)).setText(data.link);
        return convertView;

    }
}
