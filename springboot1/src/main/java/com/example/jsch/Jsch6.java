package com.example.jsch;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Jsch6 {
    public static final Log logger = LogFactory.getLog(Jsch6.class);
    public Session sshSession;
    public ChannelExec channel;
    public static String fileName;
    public static ThreadLocal<Jsch6> thread = new ThreadLocal<Jsch6>();

    public Jsch6(String host, int port, String username, String password)throws Exception{
        JSch jsch = new JSch();
        sshSession = jsch.getSession(username, host, port);
        sshSession.setPassword(password);
        sshSession.setConfig("userauth.gssapi-with-mic", "no");
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect();

        channel = (ChannelExec) sshSession.openChannel("exec");
        channel.connect();
        logger.info("连接成功!" + sshSession);
    }

    public boolean isConnected() {
        return null != channel && channel.isConnected();
    }


}
