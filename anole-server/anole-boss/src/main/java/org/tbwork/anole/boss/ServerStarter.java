package org.tbwork.anole.boss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tbwork.anole.boss.server._4_publisher.BossServer4PublisherStarter;
import org.tbwork.anole.boss.server._4_subscriber.BossServer4SubscriberStarter;
import org.tbwork.anole.boss.server._4_worker.BossServer4WorkerStarter;
import org.tbwork.anole.loader.annotion.AnoleConfigLocation;
import org.tbwork.anole.loader.context.Anole;
import org.tbwork.anole.loader.context.AnoleApp;
import org.tbwork.anole.loader.util.AnoleLogger.LogLevel;

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
    	
    	BossServer4WorkerStarter bossServer4WorkerStarter = (BossServer4WorkerStarter) context.getBean("bossServer4WorkerStarter");
    	BossServer4SubscriberStarter bossServer4SubscriberStarter = (BossServer4SubscriberStarter) context.getBean("bossServer4SubscriberStarter");
    	BossServer4PublisherStarter bossServer4PublisherStarter = (BossServer4PublisherStarter) context.getBean("bossServer4PublisherStarter");
 
    	String serverName =  Anole.getProperty("serverName");
        if(serverName == null || serverName.isEmpty())
        	serverName = Anole.getProperty("anole.server.type");
        if(serverName == null || serverName.isEmpty()){
        	serverName = "worker";
        	logger.warn("Could not find any specified server type. 'worker' will be used.");
        }
        logger.info("[:)] Anole boss server is starting...");
    	bossServer4WorkerStarter.run();
    	bossServer4SubscriberStarter.run();
    	bossServer4PublisherStarter.run(); 
    }
}
