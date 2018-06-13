package org.tbwork.anole.common.task;

import java.util.TimerTask; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbwork.anole.common.interfaces.IAnoleClient;
import org.tbwork.anole.common.message.c_2_s.PingMessage;  
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener; 

/**
 * The general PingTask used by all types of Anole clients
 * (work client, subscriber, publisher) to keep the connection
 * with server alive.
 */
public abstract class PingTask extends TimerTask {

	private static final Logger logger = LoggerFactory.getLogger(PingTask.class);
	
	public PingTask(IAnoleClient client){
		this.client = client; 
	}
	 
	protected IAnoleClient client; 
	
	@Override
	public void run() { 
		try{ 
			 ping(); 
		}
		catch(Exception e){
			logger.error("Ping failed. Details: {}", e.getMessage());
		} 
	}

    protected abstract PingMessage getPingMessage();
	
	private void ping(){   
		if(!client.canPing())
		    client.setConnected(false); 
		if(!client.isConnected())
			client.connect();
		PingMessage pingMessage = getPingMessage(); 
		if(logger.isInfoEnabled()){
			client.sendMessageWithListeners(pingMessage, 
				new ChannelFutureListener(){  
				    @Override
					public void operationComplete(ChannelFuture future) throws Exception {
						logger.debug("[:)] Ping message is sent successfully.");
					} 
				}
			);
		}
		else
			client.sendMessage(pingMessage); 
		client.addPingCount();
	}
}
