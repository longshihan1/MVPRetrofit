package com.longshihan.mvpretrofit.bean;

/**
 * @author Administrator
 * @time 2016/10/28 11:26
 * @des 聚合数据的笑话,新闻的父bean
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */
public class JokeHttpResult<T> {
    private int error_code;//返回码
    private String reason;//返回原因
    private T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
