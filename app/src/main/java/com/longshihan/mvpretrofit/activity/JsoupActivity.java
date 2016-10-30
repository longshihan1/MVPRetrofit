package com.longshihan.mvpretrofit.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.longshihan.mvpretrofit.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupActivity extends AppCompatActivity {
    private String html = "<html><head><title>jsou用法</title></head>" +
            "<body><p><a href='http://www.baidu.com'>这是jsoup的项目</a></p></body></html>";
    private String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

        //html,url,本地html
        Document doc = Jsoup.parse(html);
        doc.title();
        //文档中所有的a标签
        Elements eles = doc.getElementsByTag("a");
        for (Element link : eles) {
            String linkHref = link.attr("href");//解析出链接
            String text = link.text();//解析出文本
        }
        //检索功能
        Elements elements = doc.select("a[href]");//链接
        Elements elements2 = doc.select("img[src$=.png]");//图像
        Element elements3=doc.select("div.className").first();//文件块

        doc.select("div.className").attr("key","value");
        doc.select("div.className").addClass("myclass");//class=myclass

        doc.select("img").removeAttr("onclick");

        doc.select("div.className");

        //清除代码
        String htmls="";
        String safe=Jsoup.clean(htmls, Whitelist.basic());




        try {
            Document doc2 = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Document doc3 = Jsoup.connect(url).data("data", "1").timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File input = new File(Environment.getExternalStorageDirectory() + "/index.html");
        try {
            //第三个参数是图片的前缀，
            Document doc4 = Jsoup.parse(input, "utf-8", "http://baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
