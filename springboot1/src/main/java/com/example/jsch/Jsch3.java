package com.example.jsch;

import com.jcraft.jsch.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/*
 * shell方式连接
 * */
public class Jsch3 {
    private Session session = null;
    //通道
    private Channel channel = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
    private boolean flag = true;

    public static void sendCmd1(){
        JSch jsch=new JSch();
        UserInfo ui=new MyUserInfo();
        try{
            Session session=jsch.getSession("root","192.168.201.161", 22);
            session.setPassword("123456");
            session.setUserInfo(ui);
            session.setTimeout(30000);
            session.connect();
            //创建SHELL
            Channel channel=(ChannelShell)session.openChannel("shell");
            OutputStream outstream = channel.getOutputStream();
            InputStream instream = channel.getInputStream();
            channel.connect(30000);
            System.out.println("session timeout" + session.getTimeout());

            byte[] data = new byte[1024];
            //读取数据
            if (instream.available() > 0){
                int nLen = instream.read(data);
                String temp = new String(data, 0, data.length, "gb2312");
                System.out.println(temp);
                if (nLen < 0){
                    System.out.println("network error.");
                }
            }
            String commd = "ansible-playbook test.yml";
            outstream.write(commd.getBytes());
            outstream.flush();

            data = new byte[1024];
            //读取数据
            if (instream.available() > 0){
                int nLen = instream.read(data);
                String temp = new String(data, 0, data.length);
                System.out.println(temp);
                if (nLen < 0){
                    System.out.println("network error.");
                }
            }
            channel.disconnect();
            session.disconnect();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(final String [] args) {
        Jsch3 sshExecutor = new Jsch3();
        sshExecutor.sendCmd1();

    }
}
