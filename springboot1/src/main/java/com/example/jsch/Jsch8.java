package com.example.jsch;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Jsch8 {
    public static final Log logger = LogFactory.getLog(Jsch8.class);
    private String ipAddress;
    private String username;
    private String password;
    private static final int PORT = 22;

    public Jsch8(final String ipAddress, final String username, final String password) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
    }

    public String execute(){
        String returnCode = "";
        JSch jsch = new JSch();
        MyUserInfo myUserInfo = new MyUserInfo();
        Session session = null;
        ChannelShell channelShell = null;
        StringBuffer stringBuffer = new StringBuffer();
        try{
            //创建session连接
            session = jsch.getSession(username,ipAddress,PORT);
            if (session == null) {
                throw new Exception("session is null");
            }
            session.setPassword(password);
            session.setUserInfo(myUserInfo);
            session.setConfig("userauth.gssapi-with-mic", "no");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(60000);
            session.setServerAliveInterval(2000);
            session.connect(60000);
            System.out.println("连接seesion 成功");
            //使用ssh协议连接"shell","exec"
            channelShell = (ChannelShell)session.openChannel("shell");
            //从远程端到达的所有数据都能从这个流中读取到
            InputStream inputStream = channelShell.getInputStream();
            channelShell.connect();
            //写入该流的所有数据都将发送到远程端
            OutputStream outputStream = channelShell.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("cd /usr/local");
            printWriter.println("ansible-playbook test.yml");
            //为了结束本次交互
            printWriter.println("exit");
            //把缓冲区的数据强行输出
            printWriter.flush();

            while(true){
                if(inputStream.available() > 0){
                    byte[] data = new byte[inputStream.available()];
                    inputStream.read(data);
                    stringBuffer.append(new String(data));
                    logger.debug("读取到数据.......................");
                }else{
                    if (stringBuffer.toString().equals("")) {
                        Thread.sleep(1000);
                        logger.debug("Thread.sleep(1000)");
                    } else {
                        Thread.sleep(200);
                        logger.debug("Thread.sleep(200)");
                    }
                }
                outputStream.close();
                inputStream.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            channelShell.disconnect();
            session.disconnect();
        }
        return returnCode;
    }

    public static void main(final String [] args) {
        Jsch8 jsch8 = new Jsch8("192.168.201.161", "root", "123456");
        String str = jsch8.execute();
//        JSONObject obj = JSONObject.parseObject(str);
        System.out.print(str);
    }
}
