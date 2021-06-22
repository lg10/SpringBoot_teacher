package com.dd.edu.easyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class EasyExcelListener extends AnalysisEventListener<DataDome> {
    @Override//一行一行的读取数据
    public void invoke(DataDome dataDome, AnalysisContext analysisContext) {
        System.out.println("******"+dataDome);
    }
    @Override//表头
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
         System.out.println("++++++"+headMap);
    }
    @Override//监听
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
