package org.tbwork.anole.server.basic.server;

public interface IServerStarter {

	public int getPort();
	
	public int run();
	
	public void stop();
	
}
