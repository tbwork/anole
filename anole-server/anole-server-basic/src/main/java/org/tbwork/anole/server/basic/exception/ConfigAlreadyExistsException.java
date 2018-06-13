package org.tbwork.anole.server.basic.exception;

public class ConfigAlreadyExistsException extends RuntimeException {

	private static final String errorMessage = "The configuration (key = %s) is already existed, please use it directly.";
	
	public ConfigAlreadyExistsException()
    {
    	super(String.format(errorMessage,"unknown"));
    }
	
	public ConfigAlreadyExistsException(String key)
    {
    	super(String.format(errorMessage, key));
    }
	 
}
