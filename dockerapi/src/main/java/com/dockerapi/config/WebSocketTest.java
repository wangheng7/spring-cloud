package com.dockerapi.config;

import com.alibaba.fastjson.JSONObject;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.messages.ExecCreation;
import org.junit.Test;

import java.io.OutputStream;
import java.net.Socket;

public class WebSocketTest {

    private DockerClient dockerClient;
    //进入容器
    @Test
    public String ConnectTest()throws Exception{
        //containerId,path
        ExecCreation execCreation= dockerClient.execCreate("063e44c42b44",new String[]{"/bin/bash"},
                DockerClient.ExecCreateParam.attachStdin(), DockerClient.ExecCreateParam.attachStdout(),
                DockerClient.ExecCreateParam.attachStderr(), DockerClient.ExecCreateParam.tty(true));
        return execCreation.id();
    }

    @Test
    public void TcpTest()throws Exception{
        Socket socket=new Socket("192.168.201.101",2375);
        socket.setKeepAlive(true);
        OutputStream out = socket.getOutputStream();
        StringBuffer sb = new StringBuffer();
        //execId
        sb.append("POST /exec/"+"063e44c42b44"+"/start HTTP/1.1\r\n");
        sb.append("Host: "+"192.168.201.101"+":2375\r\n");
        sb.append("User-Agent: Docker-Client\r\n");
        sb.append("Content-Type: application/json\r\n");
        sb.append("Connection: Upgrade\r\n");
        JSONObject obj = new JSONObject();
        obj.put("Detach",false);
        obj.put("Tty",true);
        String json=obj.toJSONString();
        sb.append("Content-Length: "+json.length()+"\r\n");
        sb.append("Upgrade: tcp\r\n");
        sb.append("\r\n");
        sb.append(json);
        out.write(sb.toString().getBytes("UTF-8"));
        out.flush();
    }
}
