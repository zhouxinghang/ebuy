package com.ebuy.pojo;

import java.io.Serializable;

/**
 * Created by zhouxinghang on 2018/5/2.
 */
public class TbUserReg implements Serializable{
    String username;
    String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
