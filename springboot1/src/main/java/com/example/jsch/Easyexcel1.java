package com.example.jsch;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.UserDo;
import com.example.utils.SftpUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:    Easyexcel1
 * Package:    com.example.jsch
 * Description:
 * Datetime:    2020/8/5   13:51
 * Author:   XXXXX@XX.com
 */
public class Easyexcel1 {
    private String ipAddress = "192.168.201.120";
    private String username = "root";
    private String password = "123456";
    private static final int PORT = 22;

    @Test
    public void readExcel(){
        if(PORT == 22){
            JSch jsch = new JSch();
            Session session = null;
            try{
                SftpUtil.login("192.168.201.120",22,10000,"root","123456");
                session = jsch.getSession("root","192.168.201.120",22);
                if (session == null) {
                    throw new Exception("session is null");
                }
                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                //使用ssh协议连接"shell","exec"
                Channel channel = session.openChannel("sftp");
                channel.connect();
                ChannelSftp sftp = (ChannelSftp)channel;
                InputStream is = sftp.get("/usr/local/users.xlsx");
//                File file = new File();
//                SftpUtil.copyFile(sftp);
                ExcelListener listener = new ExcelListener();
                ExcelReader excelReader = new ExcelReader(is, ExcelTypeEnum.XLSX, null, listener);
                excelReader.read();
                List lists = new ArrayList();
                List<List<String>> lista = listener.getList();
                for(int x=1;x<lista.size();x++){
                    JSONObject jsonObject = new JSONObject();
                    List<String> list2 = lista.get(0);
                    List<String> list3 = lista.get(x);
                    for(int y=0;y<list3.size();y++){
                        jsonObject.put(list2.get(y),list3.get(y));
                    }
                    lists.add(jsonObject);
                }
//                EasyExcelFactory.readBySax(is,null,listener);

//                EasyExcel.read(, UserDo.class,new ExcelListener()).doReadAll();

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
//                    for(int x = 0; x < wb.getNumberOfSheets(); x++){
//                        Sheet sheet = wb.getSheetAt(x);
//                        int firstRowIndex = sheet.getFirstRowNum() + 1;
//                        int lastRowIndex = sheet.getLastRowNum();
//                        for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
//                            Row row = sheet.getRow(rIndex);
//
//                            if (row != null) {
//                                int indexMin = row.getFirstCellNum();
//                                int indexMax = row.getLastCellNum();
//                                for (int i = indexMin; i < indexMax; i++) {
//                                    cell = row.getCell(i);
//                                    if (cell != null) {
//                                        System.out.println(cell.toString());
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    System.out.println(cell.toString());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
