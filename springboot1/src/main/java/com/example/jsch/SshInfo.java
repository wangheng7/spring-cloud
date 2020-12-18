package com.example.jsch;

public class SshInfo {
    String host = null;
    Integer port = 22;
    String user = null;
    String key = null;
    String passPhrase = null;
    public SshInfo( String host,
                    Integer port,
                    String user,
                    String key,
                    String passPhrase ){
        super();
        this.host = host;
        this.port = port;
        this.user = user;
        this.key = key;
        this.passPhrase = passPhrase;
    }
    public String getHost(){
        return host;
    }
    public Integer getPort(){
        return port;
    }
    public String getUser(){
        return user;
    }
    public String getKey(){
        return key;
    }
    public String getPassPhrase(){
        return passPhrase;
    }
}
