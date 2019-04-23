package org.tbwork.anole.gui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.tbwork.anole.loader.annotion.AnoleClassPathFilter;
import org.tbwork.anole.loader.annotion.AnoleConfigLocation;
import org.tbwork.anole.loader.context.AnoleApp;
import org.tbwork.anole.loader.util.AnoleLogger;
import org.tbwork.anole.publisher.client.impl.AnolePublisherClient;
import org.tbwork.anole.server.basic.util.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = "org.tbwork.anole")
@AnoleConfigLocation()
@AnoleClassPathFilter(contains = "anole-*")
@ImportResource("classpath*:spring/spring-*.xml")
public class WebStarter {


	private static final List<String> configFiles = new ArrayList<String>();

	static{
		configFiles.add("jdbc.anole");
		configFiles.add("web.anole");
		configFiles.add("publisher.anole");
	}

	public static void main(String[] args) {
		ConfigUtil.initialize("aui", configFiles);
		AnoleApp.start(AnoleLogger.LogLevel.DEBUG);
	    AnolePublisherClient apc = AnolePublisherClient.instance();
	    apc.connect();
		SpringApplication.run(WebStarter.class, args);
	}
	 
}
