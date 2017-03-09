package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
@Configuration
public class StaticIpConfiguration {

    static Logger log = LoggerFactory.getLogger(StaticIpConfiguration.class.getName());

    @Bean
    public StaticIpProxy staticIpProxy() throws MalformedURLException {
        final URL staticProxyUrl = new URL(System.getenv("SOCKS_URL"));

        return new StaticIpProxy(staticProxyUrl);
    }
}