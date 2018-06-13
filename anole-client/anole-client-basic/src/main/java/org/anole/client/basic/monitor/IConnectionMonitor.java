package org.anole.client.basic.monitor;


/**
 * Connection monitor was used by all clients to watches the connection
 * with the server all the time. It sends ping message to the worker
 * server regularly, once it finds the connection is broken, it will
 * reconnect to an other worker server assigned by the boss server. 
 * @author tommy.tang
 */
public interface IConnectionMonitor {

	/**
	 * Start the monitor
	 */
	public void start();
	
	/**
	 * Stop the monitor
	 */
	public void stop();
	
	/**
	 * Restart the monitor
	 */
	public void restart();
	 
}
