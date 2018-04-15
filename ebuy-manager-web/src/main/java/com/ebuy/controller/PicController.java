package com.ebuy.controller;

import com.ebuy.common.pojo.UploadResult;
import com.ebuy.common.util.JsonUtils;
import com.ebuy.pojo.InputStreamBeanImpl;
import com.ebuy.service.PicService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

/**
 * Created by admin on 2017/12/24.
 */

@Controller
public class PicController {

    @Resource
    private PicService picService;


    @RequestMapping("pic/upload")
    @ResponseBody
    public UploadResult upload(MultipartFile uploadFile){
        UploadResult result = new UploadResult();
        try {
            //String url = picService.uploadFile(new InputStreamBeanImpl(uploadFile.getInputStream()), uploadFile.getOriginalFilename());
            //用byte[]传输文件，避免序列化问题
            String url = picService.uploadFile(uploadFile.getBytes(), uploadFile.getOriginalFilename());
            if(url==null){
                result.setError(1);
                result.setMessage("图片上传失败");
            }else{
                result.setError(0);
                result.setUrl(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.setError(1);
            result.setMessage("服务器异常");
        }
        return result;
    }

}
