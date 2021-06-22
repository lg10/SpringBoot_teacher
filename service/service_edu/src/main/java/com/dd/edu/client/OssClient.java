package com.dd.edu.client;


import com.dd.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-oss")
@Component
public interface OssClient {
    @GetMapping("/service_oss/fileOss/delFile/{FileName}")
   public R delFile(@PathVariable("FileName") String FileName);

}
