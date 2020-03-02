package com.ninn.service;

import com.ninn.config.FeignMultipartSupportConfig;
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

@FeignClient(value = "service-iris-feature-extract",fallback = UploadServiceHystric.class, configuration = FeignMultipartSupportConfig.class)
public interface IrisFeatureExtractService {

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


//    @RequestMapping(value = "/feature-extract",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String featureExtract( MultipartFile file, String name);

    @RequestMapping(value = "/feature-extract",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String featureExtract(@RequestPart(value = "file") MultipartFile file);

}
