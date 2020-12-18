package com.example.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

/**
 * ClassName:    ExcelTest
 * Package:    com.example.jsch
 * Description:
 */
public class ExcelTest3 {

    public static void getFTPClient(String host, String userName, String password, int port){
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(host, port);
            ftpClient.login(userName, password);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                System.out.println("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            }else {
                System.out.println("FTP连接成功。");
            }
        }catch (IOException e){
            e.printStackTrace();
        }


    }



}
