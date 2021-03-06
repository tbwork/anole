package org.tbwork.anole.common.message.c_2_s.subscriber;

import lombok.Data;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.message.c_2_s.C2SMessage;
@Data
public class GetConfigMessage extends C2SMessage {

	private String key;
	private String env; 
	public GetConfigMessage(String key, String env){
		super(MessageType.C2S_GET_CONFIG);
		this.key = key;
		this.env = env;
	}
	
}
