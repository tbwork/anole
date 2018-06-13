package org.tbwork.anole.common.interfaces;

import org.tbwork.anole.common.message.c_2_s.C2SMessage;

import io.netty.channel.ChannelFutureListener;


public interface IAnoleClient {

	/**
	 * Connect to the server.
	 */
	public void connect();
	
	/**
	 * Disconnect with the server and close application.
	 */
	public void close();

	/**
	 * Close and reconnect.
	 */
	public void reconnect();
	
	/**
	 * Send a message to the server.
	 */
	public void sendMessage(C2SMessage msg);
	
	
	/**
	 * Send message to the boss server and notify the specified listeners after sending.
	 */
	public void sendMessageWithListeners(C2SMessage msg, ChannelFutureListener ... listeners);
	
	  /**
     * Assume the ping is not received.
     */
    public void addPingCount();
    
    /**
     * Ack of Ping, indicate that the ping is received.
     */
    public void ackPing();
    
    /**
     * Whether the connection is valid.
     */
    public boolean canPing();
     
    public void setConnected(boolean connected);
    
    public boolean isConnected();
}
