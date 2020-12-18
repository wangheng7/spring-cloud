package com.example.jsch;

import com.jcraft.jsch.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Jsch5 {
    public static final Log logger = LogFactory.getLog(Jsch5.class);

    static JSch jsch = new JSch();
    static Session session;
    static String host = "192.168.201.161";
    static Integer port = 22;
    static String user = "root";
    static String password = "123456";

    static {
        try {
            /**
             * Session的作用是实现与远端服务器的交互，负责建立TCP连接，发送数据导远端和接收远端发来的数据。
             * Session与远端建立连接后，会建立一个前台线程，该线程负责接收远端发来的数据，并根据消息类型，执行处理函数
             */
            session = jsch.getSession(user, host, port);
            session.setPassword(password);

//            Properties config = new Properties();
//            config.put("StrictHostKeyChecking", "no");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setTimeout(1000);
//            session.setConfig(config);
            session.connect();
            System.out.println("连接seesion 成功");
        } catch (JSchException e) {
            e.printStackTrace();
            System.out.println("连接seesion 失败" + e.getMessage());
        }
    }


    public void getMessage() throws JSchException, IOException {
        System.out.println("SessionUserName:" + session.getUserName());
        System.out.println("SessionUserPort:" + session.getPort());
        // 执行相关命令
        BufferedReader reader = null;
        Channel channel = null;
        System.out.println("开始执行命令行");
        channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand("ansible-playbook test.yml");
        channel.connect();
        logger.info("连接成功!"+session);
        InputStream in = channel.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));

        String buf = null;
        while ((buf = reader.readLine()) != null) {
            System.out.println(buf);
        }
        reader.close();
    }

    public static void main(String[] args) throws JSchException, IOException {
        new Jsch5().getMessage();
    }

}
