package com.stamp.mcs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by kenneth on 7/24/17.
 */
@Component
@PropertySource("classpath:ftp.properties")
public class FTPConfig {

    @Value("${ftp.host}")
    private String ftpHost;

    @Value("${ftp.port}")
    private String ftpPort;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.directory}")
    private String directory;

    public String getFtpHost() {
        return ftpHost;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public String getFtpUsername() {
        return ftpUsername;
    }

    public String getPassword() {
        return password;
    }

    public String getDirectory() {
        return directory;
    }
}
