package com.example.jsch;

import com.jcraft.jsch.*;
import org.junit.Test;

import java.io.*;

public class Jsch2 implements Closeable {
    static long interval = 1000L;
    static int timeout = 3000;
    private SshInfo sshInfo = null;
    private JSch jsch = null;
    private Session session = null;

    private Jsch2( SshInfo info ) throws Exception{
        sshInfo = info;
        jsch = new JSch();
        jsch.addIdentity( sshInfo.getKey() );
        session = jsch.getSession(  sshInfo.getUser(),
                sshInfo.getHost(),
                sshInfo.getPort() );
        UserInfo ui = new MyUserInfo();
        session.setUserInfo(ui);
        session.connect();
    }

    public long shell( String cmd, String outputFileName ) throws Exception{
        long start = System.currentTimeMillis();
        ChannelShell channelShell = (ChannelShell)session.openChannel( "exec" );
        PipedInputStream pipeIn = new PipedInputStream();
        PipedOutputStream pipeOut = new PipedOutputStream( pipeIn );
        FileOutputStream fileOut = new FileOutputStream( outputFileName );
        channelShell.setInputStream( pipeIn );
        channelShell.setOutputStream( fileOut );
        channelShell.connect( timeout );
        pipeOut.write( cmd.getBytes() );
        Thread.sleep( interval );
        pipeOut.close();
        pipeIn.close();
        fileOut.close();
        channelShell.disconnect();
        return System.currentTimeMillis() - start;
    }

    public int exec( String cmd )
            throws Exception{
        ChannelExec channelExec = (ChannelExec)session.openChannel( "exec" );
        channelExec.setCommand( cmd );
        channelExec.setInputStream( null );
        channelExec.setErrStream( System.err );
        InputStream in = channelExec.getInputStream();
        channelExec.connect();
        int res = -1;
        StringBuffer buf = new StringBuffer( 1024 );
        byte[] tmp = new byte[ 1024 ];
        while ( true ) {
            while ( in.available() > 0 ) {
                int i = in.read( tmp, 0, 1024 );
                if ( i < 0 ) break;
                buf.append( new String( tmp, 0, i ) );
            }
            if ( channelExec.isClosed() ) {
                res = channelExec.getExitStatus();
//                System.out.println( format( "Exit-status: %d", res ) );
                break;
            }
//            Wand.waitA( 100 );
        }
        System.out.println( buf.toString() );
        channelExec.disconnect();
        return res;
    }

    public static Jsch2 newInstance()throws Exception{
        String host = "localhost";
        Integer port = 22;
        String user = "hadoop";
        String key = "./id_dsa";
        String passPhrase = "";
        SshInfo i = new SshInfo( host, port, user, key, passPhrase );
        return new Jsch2( i );
    }
    public Session getSession(){
        return session;
    }
    public void close() throws IOException{
        getSession().disconnect();
    }



}
