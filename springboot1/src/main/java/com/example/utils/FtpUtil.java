package com.example.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;

/**
 * ClassName:    FtpUtil
 * Package:    com.example.utils
 * Description:
 * Datetime:    2020/8/7   10:17
 * Author:   XXXXX@XX.com
 */
public class FtpUtil {
    private static final Logger log = LoggerFactory.getLogger(FtpUtil.class);
    private static FTPClient ftpClient;

    public FtpUtil() {
    }

    public static boolean logon(String ip, int port, int timeout, String userName, String passwd) {
        ftpClient = new FTPClient();
        boolean flag = false;

        try {
            ftpClient.connect(ip, port);
            ftpClient.login(userName, passwd);
            ftpClient.setDefaultTimeout(timeout * 1000);
            ftpClient.setConnectTimeout(timeout * 1000);
            ftpClient.setDataTimeout(timeout * 1000);
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(2);
            flag = true;
        } catch (SocketException var7) {
            log.error("FTP链接失败", var7);
            flag = false;
        } catch (IOException var8) {
            log.error("FTP链接失败", var8);
            flag = false;
        }

        return flag;
    }

    public static void logout() {
        try {
            ftpClient.logout();
        } catch (IOException var9) {
            log.error("退出ftp失败", var9);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException var8) {
                    ;
                }
            }

        }

    }

    public static boolean uploadFile(String path, String fileName, String localFileFullPath) {
        File uploadFile = new File(localFileFullPath);
        boolean flag = true;

        try {
            changeWorkingDirectory(path);
            InputStream inputStream = new FileInputStream(uploadFile);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(2);
            flag = ftpClient.storeFile(fileName, inputStream);
            inputStream.close();
            if (!exist(path, fileName)) {
                flag = false;
            }
        } catch (IOException var9) {
            log.error("文件上传FTP失败", var9);
            flag = false;
        } finally {
            logout();
        }

        return flag;
    }

    public static boolean downloadFile(String path, String fileName, String localFileFullPath) {
        try {
            changeWorkingDirectory(path);
            FTPFile[] fs = ftpClient.listFiles();
            FTPFile[] var17 = fs;
            int var5 = fs.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                FTPFile ff = var17[var6];
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localFileFullPath);
                    if (!localFile.exists()) {
                        localFile.mkdirs();
                    }

                    File localFileZip = new File(localFileFullPath + File.separator + ff.getName());
                    OutputStream os = new FileOutputStream(localFileZip);
                    ftpClient.retrieveFile(ff.getName(), os);
                    ftpClient.deleteFile(fileName);
                    os.close();
                    boolean var11;
                    if (!localFile.exists()) {
                        var11 = false;
                        return var11;
                    }

                    var11 = true;
                    return var11;
                }
            }

            return false;
        } catch (IOException var15) {
            log.error("文件下载失败", var15);
            boolean var4 = false;
            return var4;
        } finally {
            logout();
        }
    }

    public static InputStream getRemoteFileStream(String path, String fileName) {
        InputStream in = null;

        InputStream var4;
        try {
            changeWorkingDirectory(path);
            FTPFile[] fs = ftpClient.listFiles();
            FTPFile[] var13 = fs;
            int var5 = fs.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                FTPFile ff = var13[var6];
                if (ff.getName().equals(fileName)) {
                    in = ftpClient.retrieveFileStream(ff.getName());
                    return in;
                }
            }

            return in;
        } catch (IOException var11) {
            log.error("文件流获取失败", var11);
            var4 = in;
        } finally {
            logout();
        }

        return var4;
    }

    public static boolean exist(String path, String name) throws IOException {
        boolean flag = false;
        changeWorkingDirectory(path);
        String[] fileNames = ftpClient.listNames();
        String[] var4 = fileNames;
        int var5 = fileNames.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            String fileName = var4[var6];
            if (fileName.equalsIgnoreCase(name)) {
                flag = true;
                break;
            }
        }

        return flag;
    }

    public static boolean changeWorkingDirectory(String pathName) throws IOException {
        createDirecroty(ftpClient, pathName);
        return ftpClient.changeWorkingDirectory(pathName);
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

    public static boolean writeContent2Shell(String fileDir, String fileName, String content) {
        boolean flag = true;
        String filePath = fileDir + File.separator + fileName + ".sh";
        if (log.isDebugEnabled()) {
            log.debug("filePath:" + filePath);
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePath);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return flag;
    }

    public static void main(String[] args) {
        logon("192.168.187.205", 21, 30, "root", "up@iam");
        writeContent2Shell("D:\\", "test", "ls dir");
    }
}
