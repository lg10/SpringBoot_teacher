package com.dd.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {
    @ExcelProperty(value = "一级目录",index = 0)
    private String firstLine;
    @ExcelProperty(value = "二级目录",index = 1)
    private String secondLine;
}
