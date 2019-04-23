package org.tbwork.anole.worker.client.impl;

import org.anole.client.basic.monitor.impl.LongConnectionMonitor;
import org.tbwork.anole.server.basic.lccm.ILongConnectionClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbwork.anole.common.task.PingTask;
import org.tbwork.anole.worker.client.IAnoleWorkerClient;
import org.tbwork.anole.worker.client.config.WorkerClientConfig;
import org.tbwork.anole.worker.client.task.WorkerClientPingTask; 

@Component("workerClientLongConnectionMonitor") 
public class WokerClientLongConnectionMonitor extends LongConnectionMonitor{
	 
	@Autowired
	@Qualifier("worker")
	private IAnoleWorkerClient anoleWorkerClient;
	
	@Autowired
	@Qualifier("subscriberClientManager")
	private ILongConnectionClientManager clientManager;
	
	public WokerClientLongConnectionMonitor() {
		super(WorkerClientConfig.PING_DELAY, WorkerClientConfig.PING_INTERVAL); 
	}

	@Override
	protected PingTask getPingTask() { 
		WorkerClientPingTask wcpt = new WorkerClientPingTask(anoleWorkerClient, clientManager);
		return wcpt;
	}
 
	
}
