package com.dd.config.CustomException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//生产有参构造
@NoArgsConstructor//生产无参构造
public class CustomException extends RuntimeException{
    public int code;//状态码
    public  String msg;//错误信息

}
