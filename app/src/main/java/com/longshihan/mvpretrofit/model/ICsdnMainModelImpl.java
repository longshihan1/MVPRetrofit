package com.longshihan.mvpretrofit.model;

import android.content.Context;

import com.longshihan.mvpretrofit.bean.CsdnAndroidBean;
import com.longshihan.mvpretrofit.gen.CsdnAndroidBeanDaoManager;
import com.longshihan.mvpretrofit.http.CsdnAnHttpMethods;
import com.longshihan.mvpretrofit.subscribers.ProgressSubscriber;
import com.longshihan.mvpretrofit.subscribers.SubscriberOnNextListener;
import com.longshihan.mvpretrofit.utils.CharacterParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @time 2016/11/2 13:29
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class ICsdnMainModelImpl implements IModel {
    private SubscriberOnNextListener getTopMovieOnNext;
    private List<CsdnAndroidBean> mList;
    private Context mContext;
    private CharacterParser characterParser;

    public ICsdnMainModelImpl(Context context) {
        mList = new ArrayList<>();
        characterParser = CharacterParser.getInstance();
        this.mContext = context;
    }

    @Override
    public void loadData(final IModel.onLoadDataListener listener) {
        mList = CsdnAndroidBeanDaoManager.getInstance().getSession().loadAll(CsdnAndroidBean.class);

        if (mList.size() == 0) {
            getTopMovieOnNext = new SubscriberOnNextListener<String>() {
                @Override
                public void onNext(String subjects) {//获取网页内容
                    //处理数据
                    List<CsdnAndroidBean> lists = DoingData(subjects);
                    if (listener != null) {
                        saveNoteLists(lists);
                        listener.complete(lists);
                    }
                }

            };
            CsdnAnHttpMethods.getInstance().getMainjsouplist(new ProgressSubscriber<String>
                    (getTopMovieOnNext, mContext));
        } else {
            if (listener != null) {
                listener.complete(mList);
            }
        }
    }

    private List<CsdnAndroidBean> DoingData(String subjects) {
        List<CsdnAndroidBean> list = new ArrayList<>();
        Document doc = Jsoup.parse(subjects);
        Elements elements = doc.select("li");//li元素的集合A-Z
        for (Element ele : elements) {
            Elements line2 = ele.select("div.overflow");
            Elements aeles = line2.select("a");//a元素的集合
            for (Element aele : aeles) {
                CsdnAndroidBean mbean = new CsdnAndroidBean();
                mbean.setLink(aele.attr("href"));
                mbean.setTitle(aele.text());
                mbean.setTag(aele.tagName());
                String pinyin = characterParser.getSelling(aele.text());
                String sortString = pinyin.substring(0, 1).toUpperCase();
                if (sortString.matches("[A-Z]")) {
                    mbean.setSortLetters(sortString.toUpperCase());
                } else {
                    mbean.setSortLetters("#");
                }
                list.add(mbean);
            }
        }
        return list;
    }

    /**
     * 批量插入或修改用户信息
     *
     * @param list 用户信息列表
     */
    public void saveNoteLists(final List<CsdnAndroidBean> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        CsdnAndroidBeanDaoManager.getInstance().getSession().runInTx(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < list.size(); i++) {
                    CsdnAndroidBean user = list.get(i);
                    CsdnAndroidBeanDaoManager.getInstance().getSession().getCsdnAndroidBeanDao()
                            .insertOrReplace(user);
                }
            }
        });

    }
}
