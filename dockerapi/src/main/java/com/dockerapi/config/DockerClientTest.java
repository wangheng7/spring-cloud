package com.dockerapi.config;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DockerClientTest {

    @Test
    public void ConnectTest()throws Exception{
        final DockerClient docker = DefaultDockerClient.fromEnv().build();
        docker.startContainer("063e44c42b44");
    }
}
