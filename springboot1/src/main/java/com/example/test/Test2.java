package com.example.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Test2 {

    @Test
    public void Method1(){
        Map<String,String> m1 = new HashMap<String,String>();
        m1.put("大","冠希");
        m1.put("中","阿娇");
        m1.put("小","柏芝");

        for(Map.Entry<String,String> entry : m1.entrySet()){
            String k = entry.getKey();
            String v = entry.getValue();
            if(k.equals("中")){
                System.out.println(k+":"+v);
            }

        }
    }

}
