package com.ninn.web;

import com.ninn.model.Person;
import com.ninn.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadImageController {


    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam(name="file") MultipartFile file){
        System.out.println("UploadImageController:upload");
        return uploadService.uploadImage(file);
    }

    @PostMapping("/test")
    public String test(@RequestBody Person person, @RequestParam("file") MultipartFile file, @RequestParam(value = "name")String name){
        System.out.println("UploadImageController:upload");
        return uploadService.test(person, file, "null");
    }

    @PostMapping("/test1")
    public String test(@RequestParam(value = "name")String name, @RequestPart("file") MultipartFile file){
        System.out.println("UploadImageController:upload");
        return "SUCCESS";
    }
}
