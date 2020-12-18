package com.example.jsch;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class Jsch4 {
    private static JSch jsch = new JSch();

    public static Session createSession(String dstIp, int dstPort,
                                        final String localIp, final int localPort, String userName,
                                        String password, final int timeOut) throws JSchException {
        //jsch.setKnownHosts("/home/foo/.ssh/known_hosts");

        // A Session represents a connection to a SSH server
        Session session = jsch.getSession(userName, dstIp, dstPort);
        session.setPassword(password);

        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        // this socket factory is used to create a socket to the target host,
        // and also create the streams of this socket used by us
        session.setSocketFactory(new SocketFactory() {
            @Override
            public OutputStream getOutputStream(Socket socket)
                    throws IOException {
                return socket.getOutputStream();
            }
            @Override
            public InputStream getInputStream(Socket socket) throws IOException {
                return socket.getInputStream();
            }
            @Override
            public Socket createSocket(String host, int port)
                    throws IOException, UnknownHostException {
                Socket socket = new Socket();
                if (localIp != null) {
                    socket.bind(new InetSocketAddress(InetAddress
                            .getByName(localIp), localPort));
                }
                socket.connect(
                        new InetSocketAddress(InetAddress.getByName(host), port),
                        timeOut);
                return socket;
            }
        });
        session.connect(timeOut);
        return session;
    }

    public static String[] execShellCmdBySSH(String dstIp, int dstport,
                                             String localIp, int localPort, int timeOut, String userName,
                                             String password, String... cmds) throws Exception {
        Session session = null;
        Channel channel = null;
        InputStream is = null;
        OutputStream os = null;
        String[] result = new String[0];
        try {
            session = Jsch4.createSession(dstIp, dstport, localIp,
                    localPort, userName, password, timeOut);
            channel = session.openChannel("shell");

            // Enable agent-forwarding.
            // ((ChannelShell)channel).setAgentForwarding(true);
            // Choose the pty-type "vt102".
            // ((ChannelShell)channel).setPtyType("vt102");
            // Set environment variable "LANG" as "ja_JP.eucJP".
            // ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
            channel.connect();
            is = channel.getInputStream();
            os = channel.getOutputStream();
            result = new String[cmds.length];
            for (int i = 0; i < cmds.length; i++) {
                result[i] = sendCommand(is, os, cmds[i]);
            }
            return result;
        } catch (JSchException e) {
            if (e.getMessage().contains("Auth fail")) {
                throw new Exception("Auth error");
            } else {
                throw new Exception("Connect error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            channel.disconnect();
            session.disconnect();
        }
        return result;
    }

    private static String sendCommand(InputStream is, OutputStream os,
                                      String cmd) throws IOException {
        os.write(cmd.getBytes());
        os.flush();
        StringBuffer sb = new StringBuffer();
        int beat = 0;
        while (true) {
            if (beat > 3) {
                break;
            }
            if (is.available() > 0) {
                byte[] b = new byte[is.available()];
                is.read(b);
                sb.append(new String(b));
                beat = 0;
            } else {
                if (sb.length() > 0) {
                    beat++;
                }
                try {
                    Thread.sleep(sb.toString().trim().length() == 0 ? 1000
                            : 300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String ip = "192.168.201.161";
        int port = 22;
        String localIp = null;
        int localPort = 0;
        int timeOut = 3000;
        String userName = "root";
        String password = "123456";
        String[] cmds = new String[]{"ansible-playbook test.yml"};
        String[] result = null;
        try {
            result = execShellCmdBySSH(ip, port, localIp, localPort, timeOut,
                    userName, password, cmds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
