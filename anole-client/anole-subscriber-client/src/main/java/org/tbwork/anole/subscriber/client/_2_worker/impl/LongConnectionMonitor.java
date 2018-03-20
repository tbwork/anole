package org.tbwork.anole.subscriber.client._2_worker.impl;

import java.util.Timer;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbwork.anole.common.message.c_2_s.PingMessage;
import org.tbwork.anole.subscriber.client._2_worker.ConnectionMonitor;
import org.tbwork.anole.subscriber.client._2_worker.PingTask;
import org.tbwork.anole.subscriber.util.GlobalConfig; 

public class LongConnectionMonitor implements ConnectionMonitor{

	private static final Logger logger = LoggerFactory.getLogger(LongConnectionMonitor.class);
	private static final LongConnectionMonitor lcMonitor = new LongConnectionMonitor();
	private AnoleSubscriberClient client = AnoleSubscriberClient.instance();
	private Timer timer = null;
	public volatile int unreceived_count = 0;
	private LongConnectionMonitor() { }
	
	public static LongConnectionMonitor instance(){
		return lcMonitor;
	}
	
	@Override
	public void start() { 
		timer = new Timer();
		timer.schedule(new PingTask(), GlobalConfig.PING_DELAY, GlobalConfig.PING_INTERVAL);
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
