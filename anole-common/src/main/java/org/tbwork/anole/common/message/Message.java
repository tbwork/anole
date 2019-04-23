package org.tbwork.anole.common.message;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Message<T> implements Serializable {
	
	/**
	 * Type of Message. 
	 */ 
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private MessageType msgType;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private T body;

	public Message(MessageType msgType){
		this.msgType = msgType;
	}

	public Message(MessageType msgType, T body){
		this.msgType = msgType;
		this.body = body;
	}
	
	/**
	 * Every custom Message should have this method.
	 */
	public MessageType getType()
	{
		return msgType;
	}


	public T getBody(){
		return body;
	}
	
}
