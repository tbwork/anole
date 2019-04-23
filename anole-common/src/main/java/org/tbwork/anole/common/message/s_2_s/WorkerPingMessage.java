package org.tbwork.anole.common.message.s_2_s;

import lombok.Data;
import org.tbwork.anole.common.message.c_2_s.PingMessage;

@Data
public class WorkerPingMessage extends PingMessage {  
	
	/**
	 * The count of subscriber clients in the worker server.
	 */
	private int subscriberClientCount;
	
	
	/**
	 * The weight of the worker server.
	 */
	private int weight;
	
}
