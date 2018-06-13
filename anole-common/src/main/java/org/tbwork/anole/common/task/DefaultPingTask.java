package org.tbwork.anole.common.task;

import org.tbwork.anole.common.interfaces.IAnoleClient;
import org.tbwork.anole.common.message.c_2_s.PingMessage;


/**
 * Basic ping task with plain ping message, which means 
 * no extra custom data is attached in the message.
 */
public class DefaultPingTask extends PingTask{

	public DefaultPingTask(IAnoleClient client) {
		super(client); 
	}

	@Override
	protected PingMessage getPingMessage() { 
		return new PingMessage();
	}

}
