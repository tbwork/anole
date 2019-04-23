package org.tbwork.anole.server.basic.service;

public interface IServerRegisterService {
	
	/**
	 * Keep living status via writing to persistence storage regularly. 
	 */
	public void touch();
     
	/**
	 * Register the server in the persistence storage.
	 */
	public void register();
	
}
