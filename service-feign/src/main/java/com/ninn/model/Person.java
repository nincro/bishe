package com.ninn.model;

import org.springframework.web.multipart.MultipartFile;

public class Person {
    private MultipartFile multipartFile;
    private String name;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
