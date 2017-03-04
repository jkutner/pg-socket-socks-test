package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		//initialise static ip
		StaticIpProxy staticIpProxy = context.getBean(StaticIpProxy.class);
		if(staticIpProxy != null) {
			staticIpProxy.init();
		}
	}
}
