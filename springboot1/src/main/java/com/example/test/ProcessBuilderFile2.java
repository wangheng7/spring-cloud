package com.example.test;

import org.junit.Test;

public class ProcessBuilderFile2 {

    @Test
    public void execute(){
        String cmd = "\""+"/etc/ansible"+ "\" \"";
        System.out.println(cmd);
        String cmd1="ansible-playbook";
        String cmd2="test.yml";
        String [] exec = {cmd1,cmd2,cmd};
        ProcessBuilder pb = new ProcessBuilder(exec);

    }

}
