package com.xama.backend.api.controller;

import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.ObjectMetadata;
import com.xama.backend.infrastructure.common.Logger;
import com.xama.backend.infrastructure.utils.COSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cos")
public class COSController {

    Logger logger;

    /**
     * 腾讯云COS上传
     *
     * @param file  文件
     * @return  文件存储路径
     * @throws Exception    异常信息
     */
    @PostMapping(value = "/uploadFile")
    public String cosUpload(MultipartFile file) throws Exception {
        return COSClientUtil.upload(file);
    }

    /**
     * 腾讯云COS下载
     *
     * @param key   文件地址
     * @return  文件流
     */
    @GetMapping(value = "/downloadStream")
    public COSObjectInputStream downloadStream(String key) {
        return COSClientUtil.downloadStream(key);
    }

    /**
     * 腾讯云COS下载
     *
     * @param key  文件地址
     * @return  文件
     */
    @GetMapping(value = "/downloadFile")
    public ObjectMetadata downloadFile(String key) {
        return COSClientUtil.downloadFile(key);
    }

    @PostMapping
    public void test(){
        logger.info("日志信息");
    }

}
