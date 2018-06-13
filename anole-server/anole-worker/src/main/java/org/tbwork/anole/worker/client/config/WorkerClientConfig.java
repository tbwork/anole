package org.tbwork.anole.worker.client.config;

public class WorkerClientConfig {
  
	public static final int RECONNECT_INTERVAL = 3; //second
	
	public static long PING_INTERVAL = 5000L ; // millisecond
	
	public static long PING_DELAY = 5000L; // millisecond
	
	public static int DEFAULT_WEIGHT = 5; // default weight
}
