package org.tbwork.anole.worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbwork.anole.loader.annotion.AnoleConfigLocation;
import org.tbwork.anole.loader.context.AnoleApp;
import org.tbwork.anole.loader.util.AnoleLogger.LogLevel;
import org.tbwork.anole.server.basic.IAppStarter;
import org.tbwork.anole.worker.server.WorkerServer4SubscriberStarter;

import java.util.ArrayList;
import java.util.List;

/**
 * Yes, Anole goes here.
 */ 
@AnoleConfigLocation()
public class WorkerStarter implements IAppStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(WorkerStarter.class);
	
	private static WorkerServer4SubscriberStarter workerServer4SubscriberStarter;

	private static final List<String> configFiles = new ArrayList<String>();

	static{
		configFiles.add("jdbc.anole");
		configFiles.add("server.anole");
	}

    @SuppressWarnings("resource")
	public static void main( String[] args ) throws InterruptedException
    {
    	AnoleApp.start(LogLevel.INFO);
    	ApplicationContext context = new ClassPathXmlApplicationContext(
        		"spring/spring-context.xml",
        		"classpath*:spring/spring-database.xml"
        		);
    	workerServer4SubscriberStarter = (WorkerServer4SubscriberStarter) context.getBean("workerServer4SubscriberStarter");
    	logger.info("[:)] Anole worker server is starting ...");
    	int port = workerServer4SubscriberStarter.run(); 
    }

	@Override
	public void stop() {
		workerServer4SubscriberStarter.stop();
		logger.info("[:)] Anole worker server shuted down gracefully.");
	}
}
