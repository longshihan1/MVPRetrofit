package com.longshihan.mvpretrofit.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * @author Administrator
 * @time 2016-10-31 0031 下午 09:55
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
@Entity
public class CsdnAndroidBean implements Serializable {
    private String title;
    private String image;
    private String source;
    private String link;
    private String sortLetters;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private String tag;

    @Generated(hash = 1268842718)
    public CsdnAndroidBean(String title, String image, String source, String link,
            String sortLetters, String tag) {
        this.title = title;
        this.image = image;
        this.source = source;
        this.link = link;
        this.sortLetters = sortLetters;
        this.tag = tag;
    }

    @Generated(hash = 890938076)
    public CsdnAndroidBean() {
    }


    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
