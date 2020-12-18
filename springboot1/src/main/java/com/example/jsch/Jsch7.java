package com.example.jsch;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Vector;

public class Jsch7 {
    private String ipAddress;
    private String username;
    private String password;
    private static final int PORT = 22;

    public Jsch7(final String ipAddress, final String username, final String password) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
    }

    public String execute(){
        String returnCode = "";
        JSch jsch = new JSch();
        MyUserInfo myUserInfo = new MyUserInfo();
        try{
            //创建session连接
            Session session = jsch.getSession(username,ipAddress,PORT);
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
            ChannelShell channelShell = (ChannelShell)session.openChannel("shell");
            //从远程端到达的所有数据都能从这个流中读取到
            InputStream inputStream = channelShell.getInputStream();
            channelShell.setPty(true);
            channelShell.connect();
            //写入该流的所有数据都将发送到远程端
            OutputStream outputStream = channelShell.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("cd /etc/ansible");
            printWriter.println("ansible-playbook test.yml");
            //为了结束本次交互
            printWriter.println("exit");
            //把缓冲区的数据强行输出
            printWriter.flush();

            byte[] tmp = new byte[1024];
            while (true){
                //获取命令执行的结果
                while(inputStream.available() > 0){
                    int i = inputStream.read(tmp, 0, 1024);
                    if(i < 0){
                        break;
                    }
                    String s = new String(tmp, 0, i);
                    if(s.indexOf("--More--") >= 0){
                        outputStream.write((" ").getBytes());
                        outputStream.flush();
                    }
                    System.out.println(s);
                }
                if(channelShell.isClosed()){
                    System.out.println("exit-status: "+channelShell.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                }catch(Exception e){

                }
            }
            outputStream.close();
            inputStream.close();

            channelShell.disconnect();
            session.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnCode;
    }

    public static void main(final String [] args) {
        Jsch7 jsch7 = new Jsch7("192.168.201.161", "root", "123456");
        jsch7.execute();

    }
}
