package com.ebuy.service.impl;

import com.ebuy.common.util.FtpUtil;
import com.ebuy.common.util.IDUtils;
import com.ebuy.pojo.InputStreamBeanImpl;
import com.ebuy.service.PicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Created by admin on 2017/12/24.
 */

@Service
public class PicServiceImpl implements PicService {
    @Value("${manage.host}")
    private String host;
    @Value("${manage.username}")
    private String username;
    @Value("${manage.password}")
    private String password;
    @Value("${manage.port}")
    private int port;
    @Value("${manage.basePath}")
    private String basePath;
    @Value("${manage.filePath}")
    private String filePath;
    @Override
    public String uploadFile(InputStreamBeanImpl inputStream, String fileName) {
        String name = IDUtils.getImageName() + fileName.substring(fileName.lastIndexOf("."));
        boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, name, inputStream);
        if(result) {
            //return "http://" + host + basePath + filePath + name;
            //nginx配置，根目录为图片的目录
            return "http://" + host + "/" + name;
        } else {
            return null;
        }
    }

    @Override
    public String uploadFile(byte[] bytes, String fileName) {
        String name = IDUtils.getImageName() + fileName.substring(fileName.lastIndexOf("."));
        boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, name, new ByteArrayInputStream(bytes));
        if(result) {
            //return "http://" + host + basePath + filePath + name;
            //nginx配置，根目录为图片的目录
            return "http://" + host + "/" + name;
        } else {
            return null;
        }
    }
}
