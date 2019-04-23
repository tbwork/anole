package org.tbwork.anole.boss.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component; 
import org.tbwork.anole.boss.lccm.impl.PublisherClientManagerForBoss; 
import org.tbwork.anole.boss.lccm.impl.WorkerClientManagerForBoss;
import org.tbwork.anole.server.basic.StaticConfiguration;

@Component("changeNotifyJob") 
public class ChangeNotifyJob {
 
	@Autowired
	private PublisherClientManagerForBoss pcm;
	
	@Autowired
	private WorkerClientManagerForBoss wcm;
	
	@Scheduled(fixedDelay = StaticConfiguration.CHANGE_NOTIFY_INTERVAL)
	public void run(){ 
		wcm.notifyAllChanges();
	}
}
