package com.ninn;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceHystric {
    public String uploadImage(MultipartFile file) {

        return "UploadServiceHystric.uploadImage : 文件读写错误!";
    }
}
