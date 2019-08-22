package com.wfdb.testappb.config;

/**
 * Created by warren on 2019-08-21.
 */
public class Config {

    private static final Config INSTANCE = new Config();

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final String DEFAULT_PORT = "8080";

    private String host;
    private String port;

    private Config() {
        init();
    }

    public static synchronized Config getInstance() {
        return INSTANCE;
    }

    private void init() {
        host = DEFAULT_HOST;
        port = DEFAULT_PORT;
    }

    public String getBaseUrl() {
        return String.format("http://%s:%s/", host, port);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

}
