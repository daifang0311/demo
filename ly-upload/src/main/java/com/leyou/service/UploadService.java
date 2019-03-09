package com.leyou.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.controller.UploadController;
import com.leyou.properties.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class UploadService {

    @Autowired
    private UploadProperties prop;

    public String uploadImage(MultipartFile file) {
        // 1、图片校验
        // 1.1 校验文件类型
        String contentType = file.getContentType();
        if (!prop.getAllowImageTypes().contains(contentType)) {
            // 文件类型错误
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }

        // 1.2 校验文件内容
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
        } catch (IOException e) {
            // 文件类型错误
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }

        // 2、生成文件路径
        File filePath = getFilePath(file);
        System.out.println("filepath"+filePath);
        // 3、保存图片
        try {
            file.transferTo(filePath);
        } catch (IOException e) {
            log.error("【文件上传】上传文件失败", e);
            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
        // 返回图片地址 http://image.leyou.com/1.jpg
        return prop.getBaseUrl() + file.getOriginalFilename();
    }

    private File getFilePath(MultipartFile file) {
        // 文件目录
        File dir = new File(prop.getBaseDir());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 生成文件路径
        return new File(dir, file.getOriginalFilename());
    }

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(UploadController.class);
//
//    // 支持的文件类型
//    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpeg");

//    @Autowired
//    @Resource
//    FastFileStorageClient storageClient;
//
//    public String uploadImage(MultipartFile file) {
//        try {
//            // 1、图片信息校验
//            // 1)校验文件类型
//            String type = file.getContentType();
//            if (!suffixes.contains(type)) {
//                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
//            }
//            // 2)校验图片内容
//            BufferedImage image = ImageIO.read(file.getInputStream());
//            if (image == null) {
//                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
//            }
//
//            // 2、将图片上传到FastDFS
//            // 2.1、获取文件后缀名
//            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
//            // 2.2、上传
//            StorePath storePath = this.storageClient.uploadFile(
//                    file.getInputStream(), file.getSize(), extension, null);
//            // 2.3、返回完整路径
//            return "http://image.leyou.com/" + storePath.getFullPath();
//        } catch (Exception e) {
//            throw new LyException(ExceptionEnum.UPLOAD_FILE_ERROR);
//        }
//    }

}
