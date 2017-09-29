package com.example.root.androidrssfeed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 29/9/17.
 */

public class NewsAdapter extends BaseAdapter {
    Context context;
    ArrayList<NewsItem> newsItemList;

    public NewsAdapter(Context context, ArrayList<NewsItem> newsItemList) {
        this.context = context;
        this.newsItemList = newsItemList;
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public NewsItem getItem(int i) {
        return newsItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = View.inflate(context, R.layout.news_list_layout,null);
        }
        NewsItem currentNews = newsItemList.get(i);
        ImageView imv = (ImageView) view.findViewById(R.id.img_1);
        TextView txt = (TextView) view.findViewById(R.id.text_title);
        TextView des = (TextView) view.findViewById(R.id.text_des);
        Picasso.with(context).load(currentNews.getImage()).placeholder(R.drawable.placeholder).into(imv);
        txt.setText(currentNews.getTitle());
        des.setText(currentNews.getInfo());
        return view;
    }
}
