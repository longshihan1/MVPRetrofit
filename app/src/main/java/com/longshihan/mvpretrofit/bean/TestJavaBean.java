package com.longshihan.mvpretrofit.bean;

/**
 * @author Administrator
 * @time 2016-10-29 0029 下午 05:09
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class TestJavaBean {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestJavaBean(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
