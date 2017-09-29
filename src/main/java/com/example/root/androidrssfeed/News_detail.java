package com.example.root.androidrssfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class News_detail extends AppCompatActivity {
    ImageView imv;
    TextView tv1;
    TextView tv2;
    TextView tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        imv = (ImageView) findViewById(R.id.img_2);
        tv1 = (TextView) findViewById(R.id.dtitle);
        tv2 = (TextView) findViewById(R.id.ddes);
        tv4 = (TextView) findViewById(R.id.link);
        NewsItem newsItem = (NewsItem) getIntent().getSerializableExtra("newsItem");

        Picasso.with(this).load(newsItem.getImage()).placeholder(R.drawable.placeholder).into(imv);
        tv1.setText(newsItem.getTitle());
        tv2.setText(newsItem.getInfo());
//        tv4.setClickable(true);
//        tv4.setMovementMethod(LinkMovementMethod.getInstance());
        tv4.setText(newsItem.getLink());

    }
}
