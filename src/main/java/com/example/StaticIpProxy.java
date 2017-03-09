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
        String userInfo = this.proxyUrl.getUserInfo();
        if (userInfo != null && !userInfo.isEmpty()) {
            final String user = userInfo.substring(0, userInfo.indexOf(':'));
            final String password = userInfo.substring(userInfo.indexOf(':') + 1);

            System.out.println("Setting SOCKS auth: " + user + ":" + password);
            Authenticator.setDefault(new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password.toCharArray());
                }
            });
        }

        System.out.println("Using SOCKS proxy: " + proxyUrl.getHost() + ":" + String.valueOf(proxyUrl.getPort()));
        System.setProperty("socksProxyHost", proxyUrl.getHost());
        System.setProperty("socksProxyPort", String.valueOf(proxyUrl.getPort()));
    }
}
