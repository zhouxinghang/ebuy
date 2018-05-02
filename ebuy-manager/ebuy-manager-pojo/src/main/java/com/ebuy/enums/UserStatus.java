package com.ebuy.enums;

/**
 * Created by zhouxinghang on 2018/5/2.
 */
public enum UserStatus {

    DELETE(-1, "已删除"),
    UNACTIVE(0,"未激活"),
    ACTIVE(1, "已激活");
    private int code;
    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    UserStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
