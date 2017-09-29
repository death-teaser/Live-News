package com.example.root.androidrssfeed;

import android.content.Intent;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<NewsItem> newsItemList;
    ListView lv;
    NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsItemList = new ArrayList<>();

        lv = (ListView)findViewById(R.id.Listview_news);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest("http://indiatoday.intoday.in/rss/article.jsp?sid=120", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Request Succesfull", Toast.LENGTH_SHORT).show();
                org.jsoup.nodes.Document doc = Jsoup.parse(response);
                Elements itemElements = doc.getElementsByTag("item");
                for(int i=0;i<itemElements.size();i++){
                    Element item = itemElements.get(i);
                    String title = removedata(item.child(0).text());
                    String desc = item.child(2).text();
                    String link = Jsoup.parse(desc).body().select("a").attr("href");
                    String image = Jsoup.parse(desc).body().select("img").attr("src");
                    String info = Jsoup.parse(desc).body().text();
                    NewsItem news = new NewsItem();
                    news.setTitle(title);
                    news.setLink(link);
                    news.setImage(image);
                    news.setInfo(info);
                    newsItemList.add(news);
                    Log.i("mytag title",title);
                    Log.i("mytag image",image);
                    Log.i("mytag disc",info);
                    Log.i("mytag link",link);
                }
                Log.i("mytag", "items found: "+itemElements.size());
                Log.i("mytag", "item in newslist: "+newsItemList.size());

                adapter = new NewsAdapter(MainActivity.this,newsItemList);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        NewsItem currentNews = newsItemList.get(i);
                        Intent in = new Intent(MainActivity.this, News_detail.class);
                        in.putExtra("newsItem",currentNews);
                        startActivity(in);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }
    String removedata(String data){
        data = data.replace("<![CDATA[","");
        data = data.replace("]]>","");
        return data;
    }
}
