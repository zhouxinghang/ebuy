package com.ebuy.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by zhouxinghang on 2018/4/15.
 * 封装InputStream，实现序列化功能，
 */
public class InputStreamBeanImpl extends InputStream implements Serializable{
    public InputStreamBeanImpl() {
    }

    public InputStream input = null;

    public InputStreamBeanImpl(InputStream input)
    {
        this.input = input;
    }

    public void setInput(InputStream input)
    {
        this.input = input;
    }

    public InputStream getInput()
    {
        return input;
    }

    public int read() throws IOException
    {
        try {
            return this.input.read();
        } catch (IOException ex) {
            return 0;
        }
    }
}
