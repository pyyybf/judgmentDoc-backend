package com.panyue.judgmentdoc.bl.blImpl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.panyue.judgmentdoc.bl.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
@Service
public class OssServiceImpl implements OssService {

    @Value("${aliyun.oss.endpoint}")
    private String ENDPOINT;
    @Value("${aliyun.oss.access-key-id}")
    private String ACCESS_KEY_ID;
    @Value("${aliyun.oss.access-key-secret}")
    private String ACCESS_KEY_SECRET;
    @Value("${aliyun.oss.bucket-name}")
    private String BUCKET_NAME;
    @Value("${aliyun.oss.directory.root}")
    private String DIRECTORY_ROOT;
    @Value("${aliyun.oss.prefix}")
    private String PREFIX;

    @Override
    public String upload(MultipartFile multipartFile, String fileName) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            InputStream multipartFileInputStream = multipartFile.getInputStream();
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, DIRECTORY_ROOT + fileName, multipartFileInputStream);
            ossClient.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();  //关闭流
        }
        return PREFIX + DIRECTORY_ROOT + fileName;
    }

    @Override
    public void delete(String fileName) {
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        ossClient.deleteObject(BUCKET_NAME, DIRECTORY_ROOT + fileName);
        ossClient.shutdown();
    }

}
