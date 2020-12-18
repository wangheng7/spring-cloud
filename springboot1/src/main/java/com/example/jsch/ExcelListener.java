package com.example.jsch;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.UserDo;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:    ExcelListener
 * Package:    com.example.jsch
 * Description:
 * Datetime:    2020/8/5   9:37
 * Author:   XXXXX@XX.com
 */
public class ExcelListener extends AnalysisEventListener {
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();
    private List<List<String>> excelDatas = new ArrayList<>();

    public void invoke(Object obj, AnalysisContext context) {
//        System.out.println("当前行："+context.getCurrentRowNum());
        datas.add(obj);
    }

    private List doSomething(List list) {
        return list;
    }

    public List<List<String>> getList() {
        for (Object object : datas) {
            excelDatas.add((List<String>) object);
        }
        return excelDatas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // datas.clear();//解析结束销毁不用的资源
    }
}
