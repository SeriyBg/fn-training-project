package com.training.fnsrv.sshclient;

import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.training.fnsrv.utils.Collector;

public class SshClient {
    private JSch ssh;
    private Session session;
    private Channel channel;

    private String host;
    private String user;
    private String password;
    private final int PORT = 22;

    public SshClient(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
        System.out.printf("SshClient:<constructor> host: %s; user: %s; password: %s\n", host, user, password);
    }

    public void connect() {
        try {
            ssh = new JSch();
            session = ssh.getSession(user, host, PORT);
            session.setPassword(password);

            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            System.out.printf("ssh connected '%s' by '%s'\n", user, host);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exec(String cmd, Collector collector) {
        try {
            channel = session.openChannel("exec");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ((ChannelExec)channel).setCommand(cmd);
        channel.setInputStream(null);
        ((ChannelExec)channel).setErrStream(System.err);

        try {
            InputStream inputStream = channel.getInputStream();

            channel.connect();
            while (true) {
                collector.collect(inputStream);

                if (channel.isClosed()) {
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        channel.disconnect();
    }

    public void disconnect() {
        session.disconnect();
    }
}
