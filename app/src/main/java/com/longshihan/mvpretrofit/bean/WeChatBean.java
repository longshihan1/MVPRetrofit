package com.longshihan.mvpretrofit.bean;

import java.util.List;

/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 08:48
 * @des 微信精选的javabean
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class WeChatBean extends EmptyBean {
    private int totalPage;
    private int ps;
    private int pno;

    private List<ListBean> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String firstImg;
        private String id;
        private String source;
        private String title;
        private String url;
        private String mark;

        public String getFirstImg() {
            return firstImg;
        }

        public void setFirstImg(String firstImg) {
            this.firstImg = firstImg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }
    }
}
