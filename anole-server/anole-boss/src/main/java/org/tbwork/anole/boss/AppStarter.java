package org.tbwork.anole.boss;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.tbwork.anole.boss.server._4_publisher.BossServer4PublisherStarter;
import org.tbwork.anole.boss.server._4_subscriber.BossServer4SubscriberStarter;
import org.tbwork.anole.boss.server._4_worker.BossServer4WorkerStarter;
import org.tbwork.anole.boss.services.IBossRegisterService;
import org.tbwork.anole.loader.annotion.AnoleConfigLocation;
import org.tbwork.anole.loader.context.Anole;
import org.tbwork.anole.loader.context.AnoleApp;
import org.tbwork.anole.loader.util.AnoleLogger.LogLevel;
import org.tbwork.anole.server.basic.IAppStarter;
import org.tbwork.anole.server.basic.util.NetUtil;

/**
 * Yes, Anole goes here.
 */ 
@AnoleConfigLocation()
@Component
public class AppStarter implements IAppStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(AppStarter.class); 
	
	private static String alias;
	
	public static String getAlias() {
		return alias;
	}
	
	public static BossServer4WorkerStarter bossServer4WorkerStarter;
	public static BossServer4SubscriberStarter bossServer4SubscriberStarter;
	public static BossServer4PublisherStarter bossServer4PublisherStarter;
	
	
    @SuppressWarnings("resource")
	public static void main( String[] args ) throws InterruptedException
    {  
    	AnoleApp.start(LogLevel.INFO);
    	ApplicationContext context = new ClassPathXmlApplicationContext(
        		"spring/spring-context.xml",
        		"classpath*:spring/spring-database.xml"
        		); 
    	bossServer4WorkerStarter = (BossServer4WorkerStarter) context.getBean("bossServer4WorkerStarter");
    	bossServer4SubscriberStarter = (BossServer4SubscriberStarter) context.getBean("bossServer4SubscriberStarter");
    	bossServer4PublisherStarter = (BossServer4PublisherStarter) context.getBean("bossServer4PublisherStarter");
    	IBossRegisterService registerService = (IBossRegisterService) context.getBean("bossRegisterService");
        logger.info("[:)] Anole boss server is starting...");
    	int port4Worker = bossServer4WorkerStarter.run();
    	int port4Subscriber = bossServer4SubscriberStarter.run();
    	int port4Publisher = bossServer4PublisherStarter.run(); 
    	String address = NetUtil.getLocalAddress();
    	String alias = Anole.getProperty("anole.server.boss.alias"); 
		if(alias==null || alias.isEmpty()) {
			alias = String.format("{}:{}:{}", address, port4Subscriber, port4Publisher);
		}
    	registerService.register();
    }

	@Override
	public void stop() { 
		bossServer4WorkerStarter.stop();
		bossServer4SubscriberStarter.stop();
		bossServer4PublisherStarter.stop();
		logger.info("[:)] Anole boss server shuted down gracefully.");
	}
}
