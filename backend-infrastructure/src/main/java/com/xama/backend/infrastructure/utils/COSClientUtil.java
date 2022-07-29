package com.xama.backend.infrastructure.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import com.xama.backend.infrastructure.config.COSConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 腾讯云COS文件上传工具类
 */
@Slf4j
public class COSClientUtil {

    /**
     * 获取配置信息
     */
    private static COSConfig cosConfig = SpringBeanUtils.getBean(COSConfig.class);

    /**
     * 初始化用户身份信息
     */
    private static COSCredentials cred = new BasicCOSCredentials(cosConfig.getAccessKey(), cosConfig.getSecretKey());

    /**
     * 设置bucket的区域
     */
    private static ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getRegionName()));

    /**
     * 生成COS客户端
     */
    private static COSClient cosClient = new COSClient(cred, clientConfig);

    //初始化用户身份信息（secretId, secretKey）。
    private static String accessKey = cosConfig.getAccessKey();
    private static String secretKey = cosConfig.getSecretKey();
    private static String bucketName = cosConfig.getBucketName();

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String upload(MultipartFile file) throws Exception {
        String date = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
        String originalFilename = file.getOriginalFilename();
        long nextId = IdGenerator.nextId();
        String name = nextId + originalFilename.substring(originalFilename.lastIndexOf("."));
        String folderName = cosConfig.getFolderPrefix() + "/" + date + "/";
        String key = folderName + name;
        File localFile = null;
        try {
            localFile = transferToFile(file);
            String filePath = uploadFileToCOS(localFile, key);
            log.info("upload COS successful: {}", filePath);
            return filePath;
        } catch (Exception e) {
            throw new Exception("文件上传失败",e);
        } finally {
            localFile.delete();
        }
    }

    /**
     * 上传文件到COS
     *
     * @param localFile
     * @param key
     * @return
     */
    private static String uploadFileToCOS(File localFile, String key) throws InterruptedException {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), key, localFile);
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        // 传入一个threadPool, 若不传入线程池, 默认TransferManager中会生成一个单线程的线程池
        TransferManager transferManager = new TransferManager(cosClient, threadPool);
        // 返回一个异步结果Upload, 可同步的调用waitForUploadResult等待upload结束, 成功返回UploadResult, 失败抛出异常
        Upload upload = transferManager.upload(putObjectRequest);
        UploadResult uploadResult = upload.waitForUploadResult();
        transferManager.shutdownNow();
        cosClient.shutdown();
        String filePath = cosConfig.getBaseUrl() + uploadResult.getKey();
        return filePath;
    }

    /**
     * 用缓冲区来实现这个转换, 即创建临时文件
     * 使用 MultipartFile.transferTo()
     *
     * @param multipartFile
     * @return
     */
    private static File transferToFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String prefix = originalFilename.split("\\.")[0];
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = File.createTempFile(prefix, suffix);
        multipartFile.transferTo(file);
        return file;
    }


    //传入key以获取文件下载流
    public static COSObjectInputStream downloadStream(String key){
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);//根据桶和key获取文件请求
        System.out.println("文件请求："+getObjectRequest);
        COSObject cosObject = cosClient.getObject(getObjectRequest);
        COSObjectInputStream cosObjectInput = cosObject.getObjectContent();
        System.out.println("输出流："+cosObjectInput);
        return cosObjectInput;
    }

    //根据key下载文件实体
    public static ObjectMetadata downloadFile(String key){
        //自定义下载文件路径或直接填key
        File downFile = new File(key);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
        //返回文件的属性 ObjectMetadata，包含文件的自定义头和 content-type 等属性。
        return downObjectMeta;
    }

    //若要用此url查看在线文件，key必须以".png",".pdf"等后缀结尾   2020-01-04 12:02:47
    public static String generateResignedUrl(String key){
        Date expirationTime = new Date(System.currentTimeMillis() + 30L * 60L * 1000L); //半小时后过期
        System.out.println("expiratioin"+expirationTime.toString());
        URL url = cosClient.generatePresignedUrl(bucketName, key, expirationTime);      //获取url地址
        System.out.println(url);
        return url.toString();
    }
}
