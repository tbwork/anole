package org.tbwork.anole.worker.exception;

public class SocketChannelNotReadyException extends RuntimeException {
 
	public SocketChannelNotReadyException()
    {
		super("The anole client has not connected to the remote server yet, please connect first.");
    }
	 
}
