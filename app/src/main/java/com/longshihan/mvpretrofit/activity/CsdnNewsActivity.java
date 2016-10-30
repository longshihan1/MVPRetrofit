package com.longshihan.mvpretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.longshihan.mvpretrofit.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CsdnNewsActivity extends AppCompatActivity {
    private String url = "http://mobile.csdn.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csdn_news);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //parseHtml();
                parseEpub();
            }
        }).start();
    }

    private void parseEpub() {

    }

    private void parseHtml() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("div.unit");
            for (Element ele : elements) {
                String title = ele.getElementsByTag("h1").first().text();
                String href = ele.getElementsByTag("h1").first()
                        .getElementsByTag("a").attr("href");
                Log.d("info", title + ":" + href);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
