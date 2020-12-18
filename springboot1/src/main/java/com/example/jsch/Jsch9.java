package com.example.jsch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.UserDo;
import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Jsch9 {
    public static final Log logger = LogFactory.getLog(Jsch9.class);

    private String ipAddress;
    private String username;
    private String password;
    private static final int PORT = 22;

    public Jsch9(final String ipAddress, final String username, final String password) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
    }

    public String execute(){
        String line = "";
        String result = "";
        String str = "";
        String[] arr = null;
        StringBuffer sb = new StringBuffer();
        if(PORT == 22){

            JSch jsch = new JSch();
            Session session = null;
            try{
                //创建session连接
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

                try{
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    while ((line = br.readLine()) != null){
                        sb.append(line);
                        result = new String(sb);
                        str = new String(result.getBytes("ISO8859-1"),"gbk");
                    }
                    System.out.println(str);
//                    List<UserDo> list = JSONArray.parseArray(result,UserDo.class);
//                    for(UserDo ud : list){
//                        System.out.println(ud);
//                    }
                }catch(IOException io){
                    io.getMessage();
                }
                sftp.exit();
                session.disconnect();
            }catch(Exception e){
                e.printStackTrace();
            }

        }else if(PORT == 21){
            FTPClient ftp = new FTPClient();
            try{
                ftp.connect(ipAddress,PORT);
                if(ftp.login(username,password)){
                    ftp.setControlEncoding("UTF-8");
                    ftp.enterLocalPassiveMode();
                    InputStream is = ftp.retrieveFileStream("/usr/local/test2.txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    while ((line = br.readLine()) != null){
                        System.out.println(line);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return str;
    }

    public static void main(final String [] args) {
        Jsch9 jsch9 = new Jsch9("192.168.187.161", "lcl", "up@iam");
        String str = jsch9.execute();
//        JSONObject obj = JSONObject.parseObject(str);
//        JSONArray arr = JSONArray.parseArray(str);
//        List list = new ArrayList();
//        for(int i=0;i<arr.size();i++){
//            JSONObject job = arr.getJSONObject(i);
//            list.add(job);
//            for(Map.Entry<String,Object> s : job.entrySet()){
//                System.out.println("key:"+s.getKey());
//                System.out.println("value:"+s.getValue());
//            }
//        }
        List list = JSONArray.parseArray(str,UserDo.class);
        for(int x=0;x<list.size();x++){
            UserDo userDo = (UserDo) list.get(x);
            System.out.print("oooo:"+userDo);
        }

    }
}
