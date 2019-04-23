package org.tbwork.anole.worker.client.task;

import java.util.TimerTask;

import org.tbwork.anole.server.basic.lccm.ILongConnectionClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbwork.anole.common.interfaces.IAnoleClient;
import org.tbwork.anole.common.message.c_2_s.PingMessage;
import org.tbwork.anole.common.message.c_2_s.worker_2_boss.WorkerPingMessage;
import org.tbwork.anole.common.task.PingTask;
import org.tbwork.anole.worker.client.IAnoleWorkerClient;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener; 

/**
 * The ping from work to boss.
 */
public class WorkerClientPingTask extends PingTask { 
	
	private static final Logger logger = LoggerFactory.getLogger(PingTask.class);
	
	public WorkerClientPingTask(IAnoleWorkerClient client, ILongConnectionClientManager clientManager){
		super(client); 
		this.clientManager = clientManager;
	}
	
	private ILongConnectionClientManager clientManager;

	@Override
	protected PingMessage getPingMessage() {
		WorkerPingMessage wpm = new WorkerPingMessage();
		IAnoleWorkerClient workerClient = (IAnoleWorkerClient)client;
		wpm.setWeight(workerClient.getWeight());
		wpm.setSubscriberClientCount(clientManager.getClientCount());
		return wpm;
	}
 
	 
}
