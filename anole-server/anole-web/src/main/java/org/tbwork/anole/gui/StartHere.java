package org.tbwork.anole.gui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.tbwork.anole.loader.annotion.AnoleConfigLocation;
import org.tbwork.anole.loader.context.AnoleApp;
import org.tbwork.anole.loader.util.AnoleLogger.LogLevel;
import org.tbwork.anole.publisher.client.impl.AnolePublisherClient;

@ComponentScan(basePackages ="com.lcb.captcha.hapi")
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@SpringBootApplication
@AnoleConfigLocation()
@ImportResource("classpath*:spring/spring-*.xml")
public class StartHere {
	public static void main(String[] args) {
		AnoleApp.start(LogLevel.INFO);
	    AnolePublisherClient apc = AnolePublisherClient.instance();
	    apc.connect();
		SpringApplication.run(StartHere.class, args);
	}
	 
}
