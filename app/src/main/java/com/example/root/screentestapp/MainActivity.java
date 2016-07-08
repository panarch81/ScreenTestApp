package com.example.root.screentestapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_get_last_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doInBackground();
            }
        });
    }

    private void doInBackground(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                backToMainThreadwithResponse(fetchNewsReport());
//                mResponse = fetchNewsReport();
            }
        });
        t.start();
    }

    private void backToMainThreadwithResponse(final NewsResponse response){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                updateListView(response);
            }
        });

    }

    private NewsResponse fetchNewsReport() {
        try {

            //usar URL encodeada!!
            //http://rss2json.com/api.json?rss_url=http%3A%2F%2Ffeeds.feedburner.com%2Fsoychilecl-todas

            //URL url = new URL("http://rss2json.com/api.json?rss_url=http%3A%2F%2Ffeeds.feedburner.com%2Fsoychilecl-todas");
            URL url = new URL("http://feeds.feedburner.com/soychilecl-todas");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            } else {
                System.out.println("Please enter an HTTP URL.");
                return null;
            }
            String urlString = "";
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }

            System.out.println(urlString);
            //convert XML Rss feed to Json
            urlString = Utils.convertXMLNewsRssFeedStringToJson(urlString);


            //convert string to json using jackson

            return (NewsResponse)Utils.fromJson(urlString,NewsResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateListView(NewsResponse response){
        // draw the items
        ListView listview = (ListView)findViewById(R.id.listView);
        MyListViewAdapter adapter = new MyListViewAdapter(this);

        listview.setAdapter(adapter);
        adapter.setDataNews(response.item);
    }

}
