package com.longshihan.mvpretrofit.utils;

/**
 * @author Administrator
 * @time 2016/10/28 14:00
 * @des 数学计算相关的类
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class MathCan {

    public static long SystimeSub4(){
        return Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0,10));
    }
}
