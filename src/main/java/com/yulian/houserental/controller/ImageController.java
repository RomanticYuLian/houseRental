package com.yulian.houserental.controller;

import com.yulian.houserental.utils.Mess;
import com.yulian.houserental.service.impl.ImageService;
import com.yulian.houserental.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;


@RestController
@RequestMapping("/upload")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upImage")
    public Mess upLoadImage(MultipartFile file) {
        if (CheckUtil.isImage(Objects.requireNonNull(file.getOriginalFilename()))) {
            String url = imageService.uploadImage(file);
            return Mess.success().mess("图片上传成功").data("imageUrl", url);//TODO-------房屋图片上传url
        }
        return Mess.fail().mess("文件格式错误");
    }
}
