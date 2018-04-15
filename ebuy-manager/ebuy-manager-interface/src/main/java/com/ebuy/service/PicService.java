package com.ebuy.service;

import com.ebuy.pojo.InputStreamBeanImpl;

import java.io.InputStream;

/**
 * Created by admin on 2017/12/24.
 */
public interface PicService {

    String uploadFile(InputStreamBeanImpl inputStream, String fileName);

    /**
     * 用字节数据进行文件上传，避免IO流的序列化问题
     * @param bytes
     * @param fileName
     * @return
     */
    String uploadFile(byte[] bytes, String fileName);
}
