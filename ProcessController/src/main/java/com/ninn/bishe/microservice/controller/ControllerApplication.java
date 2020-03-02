package com.ninn.bishe.microservice.controller;

import com.ninn.bishe.microservice.controller.service.IrisRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Controller
@RequestMapping("/iris")
public class ControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllerApplication.class, args);
    }


    @Autowired
    IrisRegisterService irisRegisterService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam(value = "name")String name, @RequestPart("file") MultipartFile file){
        System.out.println("ControllerApplication:/iris/register");
        return ResponseEntity.ok(irisRegisterService.detect(name,file));
    }

}
