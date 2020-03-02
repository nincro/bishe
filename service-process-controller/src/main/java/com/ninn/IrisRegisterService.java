package com.ninn;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "service-iris-detect",fallback = UploadServiceHystric.class, configuration = IrisRegisterService.MultipartSupportConfig.class)
public interface IrisRegisterService {

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


//    @RequestMapping(value = "/matching",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String matching(@RequestPart(value = "file", required = false) MultipartFile file);
//    @RequestMapping(value = "/feature-extract",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String featureExtract(@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam(value = "name", defaultValue = "mina") String name);
//    @RequestMapping(value = "/enroll",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    String enroll(@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam(value = "name", defaultValue = "mina") String name);
    @RequestMapping(value="/iris/detect",method = RequestMethod.POST,produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String detect(@RequestParam(value = "name") String name, @RequestPart("file") MultipartFile file);
}
