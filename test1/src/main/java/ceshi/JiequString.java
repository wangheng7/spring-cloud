package ceshi;

import com.alibaba.fastjson.JSONArray;

/**
 * ClassName:    JiequString
 * Package:    ceshi
 * Description:
 */
public class JiequString {
    public static void main(String[] args){
        String json = "{\"name\":\"zhangsan\",\"age\":\"20\",\"sex\":\"male\"}";
        String str = json.substring(json.indexOf("name")+7,json.indexOf("\","));
        System.out.println(str);
    }
}
