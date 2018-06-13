package org.tbwork.anole.worker.server.job;

import org.tbwork.anole.server.basic.StaticConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tbwork.anole.worker.server.lccm.SubscriberClientManagerForWorker; 
/**
 * Additional mechanism to guarantee all configuration changes 
 * would be notified to all subscribers.
 */
@Component("changeNotifyJob") 
public class ChangeNotifyJob {

	@Autowired
	private SubscriberClientManagerForWorker scm; 
	
	@Scheduled(fixedDelay = StaticConfiguration.CHANGE_NOTIFY_INTERVAL)
	public void run(){
		scm.notifyAllChanges(); 
	}
}
