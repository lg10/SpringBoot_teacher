package com.dd.edu.easyExcel;

import com.alibaba.excel.EasyExcel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class TestApplication {
    public static void main(String[] args) {
     //文件名
        String fileName = "F:\\werite.xlsx";
        //写数据

//        EasyExcel.write(fileName, DataDome.class).sheet("学生列表").doWrite(getList());


        //读数据
        EasyExcel.read(fileName,DataDome.class,new EasyExcelListener()).sheet().doRead();



    }

    private static List<DataDome> getList() {
        List<DataDome> domeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DataDome dataDome = new DataDome();
            dataDome.setSname("lucy" + i);
            dataDome.setSno(i);
            domeList.add(dataDome);
        }
        return domeList;

    }
}





