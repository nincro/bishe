package com.ninn.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class UploadService {
    public String uploadImage(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            System.out.println("fileName:"+fileName);
            String path = ".";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }

            String url = tempFile.getPath() + File.separator + fileName;
            System.out.println("url:"+url);
            OutputStream os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件读写错误!";
    }
}
