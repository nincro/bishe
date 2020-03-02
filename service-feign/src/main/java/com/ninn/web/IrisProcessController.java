package com.ninn.web;


import com.ninn.service.IrisFeatureExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
public class IrisProcessController  {

    @Autowired
    IrisFeatureExtractService irisFeatureExtractService;

    @PostMapping("/iris-register")
    public String irisRegister(@RequestPart("file") MultipartFile file, @RequestParam("user_id") String  user_id){
        System.out.println("service-feign.IrisProcessController.irisRegister");

        // 将上传的文件进行特征抽取， 获得抽取完的特征矩阵
        String ret = irisFeatureExtractService.featureExtract(file);
        return ret;
    }

}
