package com.example.jsch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.UserDo;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    ExcelTest
 * Package:    com.example.jsch
 * Description:ftp、sftp读取excel
 */
public class ExcelTest2 {
    private String ipAddress;
    private String username;
    private String password;
    private static final int PORT = 22;

    public ExcelTest2(final String ipAddress, final String username, final String password) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
    }

    public List execute(){
        String line = "";
        String result = "";
        String str = "";
        String[] arr = null;
//        InputStream in = null;
        Cell cell = null;
        String value = "";
        JSONArray array = new JSONArray();
        StringBuffer sb = new StringBuffer();
        List list = new ArrayList();
//        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df = new DecimalFormat("#");
        if(PORT == 22){
            JSch jsch = new JSch();
            Session session = null;
            try{
                session = jsch.getSession(username,ipAddress,PORT);
                if (session == null) {
                    throw new Exception("session is null");
                }
                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                System.out.println("连接seesion 成功");
                //使用ssh协议连接"shell","exec"
                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp sftp = (ChannelSftp)channel;

                InputStream is = sftp.get("/usr/local/users.xlsx");
                XSSFWorkbook wb = new XSSFWorkbook(is);
                for(int x = 0; x < wb.getNumberOfSheets(); x++){
                    Sheet sheet = wb.getSheetAt(x);
                    int firstRowIndex = sheet.getFirstRowNum() + 1;
                    int lastRowIndex = sheet.getLastRowNum();
                    for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                        JSONObject object = new JSONObject();
                        Row row = sheet.getRow(rIndex);
                        if (row != null) {
                            int indexMin = row.getFirstCellNum();
                            int indexMax = row.getLastCellNum();
                            for (int i = indexMin; i < indexMax; i++) {
                                cell = row.getCell(i);
                                if (cell != null) {
                                    switch (cell.getCellType()) {
                                        //数值型
                                        case Cell.CELL_TYPE_NUMERIC:
                                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                                //如果是date类型则 ，获取该cell的date值
                                                Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                value = format.format(date);;
                                            }else {// 纯数字
                                                BigDecimal big=new BigDecimal(cell.getNumericCellValue());
                                                value = big.toString();
                                                //解决1234.0  去掉后面的.0
                                                if(null!=value&&!"".equals(value.trim())){
                                                    String[] item = value.split("[.]");
                                                    if(1<item.length&&"0".equals(item[1])){
                                                        value=item[0];
                                                    }
                                                }
                                            }
                                            break;
                                        //字符串类型
                                        case Cell.CELL_TYPE_STRING:
                                            value = cell.getStringCellValue().toString();
                                            break;
                                        // 公式类型
                                        case Cell.CELL_TYPE_FORMULA:
                                            //读公式计算值
                                            value = String.valueOf(cell.getNumericCellValue());
                                            if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                                                value = cell.getStringCellValue().toString();
                                            }
                                            break;
                                        // 布尔类型
                                        case Cell.CELL_TYPE_BOOLEAN:
                                            value = " "+ cell.getBooleanCellValue();
                                            break;
                                        // 空值
                                        case Cell.CELL_TYPE_BLANK:
                                            value = "";
                                            break;
                                        // 故障
                                        case Cell.CELL_TYPE_ERROR:
                                            value = "";
                                            break;
                                        default:
                                            value = cell.getStringCellValue().toString();
                                    }
                                    //                            JSONObject object = new JSONObject();
                                    object.put(sheet.getRow(0).getCell(i).getStringCellValue(),value);
                                }

                            }
                        }
                        array.add(object);
                        list = JSONObject.parseArray(array.toJSONString(),UserDo.class);
                    }
                }
//                for(int i=0;i<array.size();i++){
//                    list.add(object);
//                }
//                System.out.print("778:::"+array.toString());
                is.close();
//                sftp.exit();
//                session.disconnect();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(PORT == 21){
            FTPClient ftp = new FTPClient();
            try{
                ftp.connect(ipAddress,PORT);
                if(ftp.login(username,password)){
                    ftp.setControlEncoding("UTF-8");
                    ftp.enterLocalPassiveMode();
//                    ftp.changeWorkingDirectory("/usr/local/users.xlsx");
                    InputStream in = ftp.retrieveFileStream("/usr/local/users.xlsx");

                    XSSFWorkbook wb = new XSSFWorkbook(in);
                    for(int x = 0; x < wb.getNumberOfSheets(); x++){
                        Sheet sheet = wb.getSheetAt(x);
                        int firstRowIndex = sheet.getFirstRowNum() + 1;
                        int lastRowIndex = sheet.getLastRowNum();
                        for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                            Row row = sheet.getRow(rIndex);

                            if (row != null) {
                                int indexMin = row.getFirstCellNum();
                                int indexMax = row.getLastCellNum();
                                for (int i = indexMin; i < indexMax; i++) {
                                    cell = row.getCell(i);
                                    if (cell != null) {
                                        System.out.println(cell.toString());
                                    }
                                }
                            }
                        }
                    }

                    System.out.println(cell.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    public static void main(final String [] args) {
        ExcelTest2 excelTest = new ExcelTest2("192.168.201.120", "root", "123456");
        List list = excelTest.execute();

//        JSONArray arr = JSONArray.parseArray(str);
//        List list = new ArrayList();
//        for(int i=0;i<arr.size();i++){
//            JSONObject job = arr.getJSONObject(i);
//            list.add(job);
//        }
//        System.out.println("111:"+list.size());
//        for(int x=0;x<list.size();x++){
//            JSONObject jo = (JSONObject) list.get(x);
//            for(Map.Entry<String,Object> map : jo.entrySet()){
//                System.out.println(map.getKey()+":"+map.getValue());
//            }
//        }
        for(int x=0;x<list.size();x++){
            UserDo userDo = (UserDo) list.get(x);
//            JSONObject jo2 = JSONObject.parseObject((String) obj);
//            UserDo userDo = JSONObject.parseObject(String.valueOf(jo2),UserDo.class);
            System.out.println("oooo:"+userDo);
        }


    }

}
