package com.example.utils;

import com.jcraft.jsch.*;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static com.example.utils.FtpUtil.logout;

/**
 * ClassName:    SftpUtil
 * Package:    com.example.utils
 * Description:
 * Datetime:    2020/8/5   14:11
 * Author:   XXXXX@XX.com
 */
public class SftpUtil {
    private static final Logger log = LoggerFactory.getLogger(SftpUtil.class);
    public static Session session = null;

    public static Channel channel = null;

    public SftpUtil() {
    }

    public static void login(String ip, int port, int timeout, String userName, String passwd) throws Exception {
        log.info("通过sftp方式登录>----------------------");
        JSch jsch = new JSch();

        try {
            if (port <= 0) {
                session = jsch.getSession(userName, ip);
            } else {
                session = jsch.getSession(userName, ip, port);
            }

            if (session == null) {
                throw new Exception("session is null");
            }

            session.setPassword(passwd);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(timeout);
        } catch (Exception var7) {
            log.error("通过sftp方式登录失败！--------------------", var7);
            throw new Exception("Login Sftp Failed");
        }

        log.info("通过sftp方式登录成功>----------------------");
    }

    public static InputStream getRemoteFileStream(String userName,String password,String ip,int port,String path, String fileName) {
        JSch jsch = new JSch();
        InputStream is = null;

        ChannelSftp sftp = null;
        try {
            session = jsch.getSession(userName,ip,port);
            if (session == null) {
                throw new Exception("session is null");
            }
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp)channel;

            is = sftp.get(path.concat(fileName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return is;
    }



    private static boolean createDirecroty(FTPClient ftp, String remote) throws IOException {
        boolean success = true;
        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);

        // 如果远程目录不存在，则递归创建远程服务器目录
        ftp.changeWorkingDirectory("/");

        if (!directory.equalsIgnoreCase("/")
                && !ftp.changeWorkingDirectory(directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            while (true) {
                String subDirectory = new String(remote.substring(start, end));
                if (!ftp.changeWorkingDirectory(subDirectory)) {
                    if (ftp.makeDirectory(subDirectory)) {
                        ftp.changeWorkingDirectory(subDirectory);
                    } else {
                        success = false;
                        return success;
                    }
                }
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }

    public static boolean upLoadFile(Session session, String sftpPath, String totalPath, String localPath) {
        boolean flag = true;
        Channel channel = null;

        try {
            channel = session.openChannel("sftp");
            channel.connect(10000000);
            ChannelSftp sftp = (ChannelSftp)channel;

            try {
                sftp.cd(sftpPath);
            } catch (SftpException var12) {
                log.error("sftp.cd exception，目录不存在", var12);
                sftp.mkdir(sftpPath);
                sftp.cd(sftpPath);
            }

            File file = new File(localPath);
            copyFile(sftp, file, sftp.pwd(), totalPath);
        } catch (Exception var13) {
            log.error("文件上传SFTP失败！", var13);
            flag = false;
        } finally {
            session.disconnect();
            channel.disconnect();
        }

        return flag;
    }

    public static void copyFile(ChannelSftp sftp, File file, String pwd, String totalPath) {
        if (file.isDirectory()) {
            File[] list = file.listFiles();

            try {
                try {
                    String fileName = file.getName();
                    sftp.cd(pwd);
                    log.info("正在创建目录:" + sftp.pwd() + "/" + fileName);
                    sftp.mkdir(fileName);
                    log.info("目录创建成功:" + sftp.pwd() + "/" + fileName);
                } catch (Exception var26) {
                    log.error("目录创建失败>-----------", var26);
                }

                pwd = pwd + "/" + file.getName();

                try {
                    sftp.cd(file.getName());
                } catch (SftpException var25) {
                    log.error("转到目录失败>-----------", var25);
                }
            } catch (Exception var27) {
                log.error("转到目录失败>-----------", var27);
            }

            for(int i = 0; i < list.length; ++i) {
                copyFile(sftp, list[i], pwd, totalPath);
            }
        } else {
            try {
                sftp.cd(pwd);
            } catch (SftpException var24) {
                log.error("转到目录失败>-----------", var24);
            }

            log.info("复制文件:" + file.getAbsolutePath());
            InputStream instream = null;
            OutputStream outstream = null;

            try {
                instream = new FileInputStream(file);
                outstream = sftp.put(totalPath, 0);
                byte[] b = new byte[1024];

                int n;
                try {
                    while((n = instream.read(b)) != -1) {
                        outstream.write(b, 0, n);
                    }
                } catch (IOException var28) {
                    log.error("write exception >-----------", var28);
                }
            } catch (SftpException var29) {
                log.info("通过sftp上传文件失败！！！！！！！！！！", var29);
            } catch (IOException var30) {
                log.info("读取文件流失败！！！！！！！！！！", var30);
            } finally {
                try {
                    outstream.flush();
                    outstream.close();
                    instream.close();
                } catch (Exception var23) {
                    log.info("关闭sftp连接异常！！！！！！！！！！", var23);
                }

            }
        }

    }

    public static boolean writeContent2Shell(String fileDir, String fileName, String content) {
        boolean flag = false;
        String filePath = fileDir + "/" + fileName + ".sh";
        if (log.isDebugEnabled()) {
            log.debug("filePath:" + filePath);
        }

        FileOutputStream fos = null;

        try {
            Channel channel = null;
            channel = session.openChannel("sftp");
            channel.connect(10000000);
            ChannelSftp sftp = (ChannelSftp)channel;
            String localPath = System.getProperty("user.dir");
            String localFilePath = localPath + File.separator + "tmp" + File.separator + fileName + ".sh";
            fos = new FileOutputStream(localFilePath);
            fos.write(content.getBytes());
            fos.close();
            upLoadFile(session, fileDir, filePath, localFilePath);
            deleteFile(localFilePath);
            flag = true;
        } catch (FileNotFoundException var10) {
            flag = false;
            var10.printStackTrace();
        } catch (IOException var11) {
            flag = false;
            var11.printStackTrace();
        } catch (JSchException var12) {
            flag = false;
            var12.printStackTrace();
        }

        return flag;
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        login("192.168.187.161", 22, 30000, "root", "up@iam");
        writeContent2Shell("/home/shellmanage", "test20190605", "ls dir");
    }
}
