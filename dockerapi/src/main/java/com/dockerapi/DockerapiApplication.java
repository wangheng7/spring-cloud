package com.dockerapi;

import com.dockerapi.controller.ContainerExecWSHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketSession;

import java.net.Socket;

@SpringBootApplication
public class DockerapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerapiApplication.class, args);
	}

	@Bean
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//获得传参
//        String ip=session.getAttributes().get("ip").toString();
//        String containerId=session.getAttributes().get("containerId").toString();
		String ip = "192.168.201.101";
		String containerId = "063e44c42b44";
		String width= "10";
		String height= "10";
		//创建bash
		ContainerExecWSHandler handler = new ContainerExecWSHandler();
		String execId = handler.createExec(ip, containerId);
		//连接bash
		Socket socket = handler.connectExec(ip, execId);
		System.out.println("000:"+socket.isConnected());
		//获得输出
		handler.getExecMessage(session, ip, containerId, socket);
		//修改tty大小
//        resizeTty(ip, width, height, execId);
	}
}
