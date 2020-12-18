package com.example.jsch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * ClassName:    test2
 * Package:    com.example.jsch
 * Description:
 * Datetime:    2020/8/9   20:14
 * Author:   XXXXX@XX.com
 */
public class test2 {
    @Test
    public void test(){
        JSONArray arr = new JSONArray();
        for(int x=0;x<5;x++){
            JSONObject obj = new JSONObject();
            obj.put("name","姓名"+x);
            obj.put("value","张三"+x);
            System.out.println(obj);
            arr.add(obj);
        }

        System.out.println(arr);
    }
}
