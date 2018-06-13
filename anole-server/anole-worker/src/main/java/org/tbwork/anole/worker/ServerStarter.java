package org.tbwork.anole.worker;
import org.tbwork.anole.server.basic.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext; 
import org.tbwork.anole.loader.annotion.AnoleConfigLocation;
import org.tbwork.anole.loader.context.Anole;
import org.tbwork.anole.loader.context.AnoleApp;
import org.tbwork.anole.loader.core.loader.AnoleLoader;
import org.tbwork.anole.loader.core.loader.impl.AnoleClasspathLoader;
import org.tbwork.anole.loader.util.AnoleLogger.LogLevel;
import org.tbwork.anole.worker.client.impl.AnoleWorkerClient;
import org.tbwork.anole.worker.server.impl.AnoleSubscriberManagerWorkerServer;

import com.alibaba.fastjson.parser.ParserConfig;

/**
 * Yes, Anole goes here.
 */ 
@AnoleConfigLocation()
public class ServerStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(ServerStarter.class); 
	
    @SuppressWarnings("resource")
	public static void main( String[] args ) throws InterruptedException
    {  
    	AnoleApp.start(LogLevel.INFO);
    	ApplicationContext context = new ClassPathXmlApplicationContext(
        		"spring/spring-context.xml",
        		"classpath*:spring/spring-database.xml"
        		);
    	AnoleSubscriberManagerWorkerServer anoleSubscriberManagerWorkerServer = (AnoleSubscriberManagerWorkerServer) context.getBean("subscriberWorkerServer");
    	AnoleWorkerClient workerClient = (AnoleWorkerClient) context.getBean("workerClient");   
    	logger.info("[:)] Anole worker server is starting ...");
        workerClient.connect();
    	int port = SystemUtil.getOneValidPort(); 
        anoleSubscriberManagerWorkerServer.start(port); 
    }
}
