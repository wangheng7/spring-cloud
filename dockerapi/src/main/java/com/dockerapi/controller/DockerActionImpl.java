package com.dockerapi.controller;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;

public class DockerActionImpl<T> implements DockerHelper.DockerQuery<T> {
    public T action(DockerClient docker) throws Exception{
        docker = new DefaultDockerClient("192.168.201.101");
        return (T) docker;
    }
}
