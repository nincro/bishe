package com.ninn.service;

import com.ninn.model.Person;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "service-hi",fallback = UploadServiceHystric.class, configuration = UploadService.MultipartSupportConfig.class)
public interface UploadService {

    @Scope("prototype")
    @Primary
    @Configuration
    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;
        @Bean
        public SpringFormEncoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    String uploadImage(MultipartFile file) ;

    @RequestMapping(value = "/test",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String test(@RequestBody Person person,@RequestParam("file") MultipartFile file, @RequestParam("name") String name);
}
