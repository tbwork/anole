package org.anole.client.basic.monitor.impl;

import java.util.Timer;

import org.anole.client.basic.monitor.IConnectionMonitor;
import org.tbwork.anole.common.task.PingTask; 
 
public abstract class LongConnectionMonitor implements IConnectionMonitor{ 
	
	private Timer timer = null;   
	protected abstract PingTask getPingTask();
	
	private Long delay;
	
	private Long interval;
	
	public LongConnectionMonitor(Long delay, Long interval) {
		this.delay = delay;
		this.interval = interval;
	} 

	@Override
	public void start() { 
		timer = new Timer();
		timer.schedule(getPingTask(), delay, interval);
	}

	@Override
	public void stop() { 
		timer.cancel();
		timer = null;
	}

	@Override
	public void restart() {
		stop();
		start();
	}
	 
	
}
