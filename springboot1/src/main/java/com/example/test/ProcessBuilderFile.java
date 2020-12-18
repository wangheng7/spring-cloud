package com.example.test;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ProcessBuilderFile {
    @Test
    public void run(){
        URL url = ProcessBuilderFile.class.getClassLoader().getResource("started.yml");
        File file = new File(url.getFile());
        System.out.println(file.getParent());

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
        pb.directory(new File(file.getParent()));
        try {
            Process process = pb.start();
            int n = process.waitFor();
            System.out.println("n="+n);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
