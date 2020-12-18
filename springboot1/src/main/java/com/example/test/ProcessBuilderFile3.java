package com.example.test;

import org.junit.Test;

import java.io.*;
import java.net.URL;

public class ProcessBuilderFile3 {
    @Test
    public void execute(){
//        String url = "E:\\chuanzhi\\ideaWorkspace2\\springboot1\\target\\classes\\started.yml";
        URL url = ProcessBuilderFile.class.getClassLoader().getResource("started.yml");
        File file = new File(url.getFile());
        String strUrl = file.getAbsolutePath();
        ProcessBuilder pb = new ProcessBuilder(strUrl,"ansible-playbook started.yml");
//        pb.redirectErrorStream(true);
        try {
            Process process = null;
            process = pb.start();
            InputStream is = process.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                System.err.println(line);
            }
            InputStream is1 = process.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));
            String line1;
            while ((line1 = br1.readLine()) != null) {
                System.out.println(line1);
            }
            int exitCode = process.waitFor();
            System.out.println(exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
