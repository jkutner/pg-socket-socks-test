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
        String enabled = System.getenv("FIXIE_ENABLED");

        if(!"true".equals(enabled)) {
            log.warn("Static IP is not enabled");
            return null;
        }

        String proxyUrl = System.getenv("FIXIE_URL");
        log.info("Connecting to static IP with proxy URL: "+proxyUrl);

        final URL staticProxyUrl = new URL(proxyUrl);

        return new StaticIpProxy(staticProxyUrl);
    }
}