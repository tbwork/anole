package org.tbwork.anole.server.exception;

public class ConfigNotExistsException extends RuntimeException {

	private static final String errorMessage = "The configuration (key = %s) is not existed, please add it first.";
	
	public ConfigNotExistsException()
    {
    	super(String.format(errorMessage,"unknown"));
    }
	
	public ConfigNotExistsException(String key)
    {
    	super(String.format(errorMessage, key));
    }
	 
}
