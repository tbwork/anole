package org.tbwork.anole.boss.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tbwork.anole.boss.StaticConfiguration;
import org.tbwork.anole.boss.lccm.impl.PublisherClientManagerForBoss; 
import org.tbwork.anole.boss.lccm.impl.WorkerClientManagerForBoss;

@Component("connectionRecycle") 
public class ConnectionRecycle {
 
	
	@Autowired
	private PublisherClientManagerForBoss pcm;
	
	@Autowired
	private WorkerClientManagerForBoss wcm;
	
	@Scheduled(fixedDelay = StaticConfiguration.PROMISE_PING_INTERVAL)
	public void run(){ 
		pcm.promisePingAndScavenge("publisher");
		wcm.promisePingAndScavenge("worker");
	}
	
}
