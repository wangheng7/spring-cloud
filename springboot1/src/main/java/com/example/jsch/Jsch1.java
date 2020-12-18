package com.example.jsch;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

/*
* exec方式连接
* */
//@RunWith(Parameterized.class)
public class Jsch1 {
    public static final Log logger = LogFactory.getLog(Jsch1.class);

    private String ipAddress;
    private String username;
    private String password;
    private static final int PORT = 22;
    private Vector<String> stdout;


    public Jsch1(final String ipAddress, final String username, final String password) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
        stdout = new Vector<String>();
    }

    public int execute(String command){
        int returnCode = 0;
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
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
//            channel.setInputStream(null);
            BufferedReader input = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            channel.connect();
            System.out.println("The remote command is:"+command);
            String line;
            while ((line = input.readLine()) != null) {
                stdout.add(line);
            }
            input.close();

            if (channel.isClosed()) {
                returnCode = channel.getExitStatus();
            }
            channel.disconnect();
            session.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return returnCode;
    }

    public Vector<String> getStandardOutput() {
        return stdout;
    }


    public static void main(final String [] args) {
        Jsch1 sshExecutor = new Jsch1("192.168.201.161", "root", "123456");
        String cmd = "cd /"+";"+"cd etc/ansible"+";"+"ansible-playbook stop.yml";
        sshExecutor.execute(cmd);
        Vector<String> stdout = sshExecutor.getStandardOutput();
        for (String str : stdout) {
            System.out.println(str);
        }
    }

}
