package com.example;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 *
 */
public class StaticIpProxy {

    private final URL proxyUrl;

    public StaticIpProxy(URL proxyUrl) {
        this.proxyUrl = proxyUrl;
    }

    public void init() {
        String userInfo = proxyUrl.getUserInfo();
        String user = userInfo.substring(0, userInfo.indexOf(':'));
        String password = userInfo.substring(userInfo.indexOf(':') + 1);

        // http://docs.oracle.com/javase/7/docs/technotes/guides/net/proxies.html
        System.setProperty("socksProxyHost", proxyUrl.getHost());
        System.setProperty("socksProxyPort", Integer.toString(1080));

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password.toCharArray());
            }
        });
    }
}
