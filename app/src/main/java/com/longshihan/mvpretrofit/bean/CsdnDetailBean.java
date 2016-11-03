package com.longshihan.mvpretrofit.bean;

import java.util.List;

/**
 * @author Administrator
 * @time 2016-11-3 0003 下午 10:09
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class CsdnDetailBean {
    String title;
    String link;
    String img;
    List<CsdnMainBean> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<CsdnMainBean> getData() {
        return data;
    }

    public void setData(List<CsdnMainBean> data) {
        this.data = data;
    }
}
