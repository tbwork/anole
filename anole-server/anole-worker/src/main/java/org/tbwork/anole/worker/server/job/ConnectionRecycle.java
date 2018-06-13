package org.tbwork.anole.worker.server.job;

import org.tbwork.anole.server.basic.StaticConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tbwork.anole.worker.server.lccm.SubscriberClientManagerForWorker; 

@Component("connectionRecycle") 
public class ConnectionRecycle {

	@Autowired
	private SubscriberClientManagerForWorker scm; 
	
	@Scheduled(fixedDelay = StaticConfiguration.PROMISE_PING_INTERVAL)
	public void run(){
		scm.promisePingAndScavenge("subscriber"); 
	}
	
}
