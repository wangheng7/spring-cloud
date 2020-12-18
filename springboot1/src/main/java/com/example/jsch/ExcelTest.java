package com.example.jsch;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:    ExcelTest
 * Package:    com.example.jsch
 * Description:
 */
public class ExcelTest {

    public void execute(String path){
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        BufferedInputStream bis = null;
        InputStream input = null;
        try {
            url = new URL(path);
//            httpURLConnection.setRequestProperty("Content-Type", "application/json");
//            httpURLConnection.setRequestProperty("charset", "utf-8");
//            httpURLConnection.setRequestMethod("POST");
            httpURLConnection = (HttpURLConnection)url.openConnection();
            int code = httpURLConnection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK) {
//                bis = new BufferedInputStream(httpURLConnection.getInputStream());
                input = httpURLConnection.getInputStream();
                Workbook wb = null;
                if (url.getPath().endsWith("xls")) {
                    wb = new HSSFWorkbook(input);
                } else if (url.getPath().endsWith("xlsx")) {
                    wb = new XSSFWorkbook(input);
                } else {
                    System.out.println("文件格式错误,请检查文件格式！");
                }

                Sheet sheet = wb.getSheetAt(0);
                int firstRowIndex = sheet.getFirstRowNum() + 1;
                int lastRowIndex = sheet.getLastRowNum();
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    Row row = sheet.getRow(rIndex);

                    if (row != null) {
                        int indexMin = row.getFirstCellNum();
                        int indexMax = row.getLastCellNum();
                        for (int i = indexMin; i < indexMax; i++) {
                            Cell cell = row.getCell(i);
                            if (cell != null) {
                                System.out.println(cell.toString());
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Workbook getWorkbook(String path) throws Exception{
        String type = path.substring(path.lastIndexOf(".") + 1);
        Workbook wb;
        //根据文件后缀（xls/xlsx）进行判断
        InputStream input = new URL(path).openStream();
        if ("xls".equals(type)) {

            //文件流对象
            wb = new HSSFWorkbook(input);
        } else if ("xlsx".equals(type)) {
            wb = new XSSFWorkbook(input);
        } else {
            throw new Exception("文件类型错误");
        }
        return wb;
    }


    public static void main(final String [] args) {
        ExcelTest excelTest = new ExcelTest();
        excelTest.execute("http://192.168.201.120:22/usr/local/users.xlsx");
    }

}
