package com.ebuy.common.pojo;

/**
 * Created by zhouxinghang on 2018/4/14.
 */
public class UploadResult {
    private int error;
    private String url;
    private String message;
    public int getError() {
        return error;
    }
    public void setError(int error) {
        this.error = error;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
