package com.ultrapower.framework.configuration.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @description: 转换工具类
 **/
public class TransferUtils {

    private static final String ENCODING = "utf-8";

    public static void main(String[] args) {
        Properties properties = TransferUtils.yml2Properties("C:\\Users\\LXS\\AppData\\Local\\Temp\\config-repo-1258918207342783624\\dev\\sms.yml");
        System.out.println(properties);
        TransferUtils.properties2Yaml("C:\\Users\\LXS\\AppData\\Local\\Temp\\config-repo-1258918207342783624\\dev\\sms.properties","");
    }

    public static Properties yml2Properties(String path) {
        final String DOT = ".";
        Properties properties = new Properties();
//        List<String> lines = new LinkedList<>();
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLParser parser = yamlFactory.createParser(
                    new InputStreamReader(new FileInputStream(path), Charset.forName(ENCODING)));

            String key = "";
            String value = null;
            JsonToken token = parser.nextToken();
            while (token != null) {
                if (JsonToken.START_OBJECT.equals(token)) {
                    // do nothing
                } else if (JsonToken.FIELD_NAME.equals(token)) {
                    if (key.length() > 0) {
                        key = key + DOT;
                    }
                    key = key + parser.getCurrentName();

                    token = parser.nextToken();
                    if (JsonToken.START_OBJECT.equals(token)) {
                        continue;
                    }
                    value = parser.getText();
                    properties.put(key, value);

                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = key.substring(0, dotOffset);
                    }
                    value = null;
                } else if (JsonToken.END_OBJECT.equals(token)) {
                    int dotOffset = key.lastIndexOf(DOT);
                    if (dotOffset > 0) {
                        key = key.substring(0, dotOffset);
                    } else {
                        key = "";
//                        lines.add("");
                    }
                }
                token = parser.nextToken();
            }
            parser.close();
            return properties;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将properties文件转换为yaml文件
     * @param propPath
     * @param yamlPath
     */
    public static void properties2Yaml(String propPath,String yamlPath) {
        JsonParser parser = null;
        JavaPropsFactory factory = new JavaPropsFactory();
        try {
            parser = factory.createParser(new InputStreamReader(new FileInputStream(propPath), Charset.forName(ENCODING)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLGenerator generator = yamlFactory.createGenerator(new OutputStreamWriter(new FileOutputStream(yamlPath), Charset.forName(ENCODING)));

            JsonToken token = parser.nextToken();

            while (token != null) {
                if (JsonToken.START_OBJECT.equals(token)) {
                    generator.writeStartObject();
//                } else if (JsonToken.START_ARRAY.equals(token)) {
//                    generator.writeArrayFieldStart(parser.getText());
                } else if (JsonToken.FIELD_NAME.equals(token)) {
                    generator.writeFieldName(parser.getCurrentName());
                } else if (JsonToken.VALUE_STRING.equals(token)) {
                    generator.writeString(parser.getText());
                } else if (JsonToken.END_ARRAY.equals(token)) {
                    generator.writeEndArray();
                } else if (JsonToken.END_OBJECT.equals(token)) {
                    generator.writeEndObject();
                }
                token = parser.nextToken();
            }
            parser.close();
            generator.flush();
            generator.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
