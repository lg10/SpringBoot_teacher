package com.dd.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String updloadFileAvatar(MultipartFile file);

    String delFile(String FileName);
}
