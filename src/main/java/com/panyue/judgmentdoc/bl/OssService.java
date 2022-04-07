package com.panyue.judgmentdoc.bl;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public interface OssService {

    /**
     * 上传文件到OSS
     *
     * @param multipartFile 文件
     * @param fileName      上传文件路径名称
     * @return String 文件访问链接
     */
    String upload(MultipartFile multipartFile, String fileName);

    /**
     * 删除文件
     *
     * @param fileName 文件路径名称
     */
    void delete(String fileName);

}
