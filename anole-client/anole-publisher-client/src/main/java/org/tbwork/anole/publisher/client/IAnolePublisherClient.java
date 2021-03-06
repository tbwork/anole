package org.tbwork.anole.publisher.client;

import org.tbwork.anole.common.message.c_2_s.C2SMessage;

import io.netty.channel.ChannelFutureListener;

public interface IAnolePublisherClient {

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
	 * Send message to the server and notify specified listeners after sending.
	 */
	public void sendMessageWithListeners(C2SMessage msg, ChannelFutureListener ... listeners);
	 
	/**
	 * Save authentication information.
	 */
	public void saveToken(int clientId, int token);
	
	
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
    
    public Boolean isConnected();
    
    public void notifyConnectOver(boolean connected);
}
