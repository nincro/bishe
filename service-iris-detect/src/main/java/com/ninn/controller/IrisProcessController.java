package com.ninn.controller;

import com.ninn.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/iris")
public class IrisProcessController {

    @PostMapping("/test")
    public ResponseEntity<String> test(@RequestParam(value = "name") String name, @RequestPart("file") MultipartFile file) {
        System.out.println("/iris/test");
        return ResponseEntity.ok("SUCCESS");
    }

    @Autowired
    UploadService uploadService;

    @PostMapping("/detect")
    public ResponseEntity<String> detect(@RequestParam(value = "name") String name, @RequestPart("file") MultipartFile file) {
        System.out.println("IrisProcessController:/iris/detect");
        uploadService.uploadImage(file);
        return ResponseEntity.ok("SUCCESS");
    }
}
