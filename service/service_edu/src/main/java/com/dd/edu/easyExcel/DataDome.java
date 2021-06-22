package com.dd.edu.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DataDome {

    @ExcelProperty(value = "学生编号",index = 0)//表头
    private int sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;

}
