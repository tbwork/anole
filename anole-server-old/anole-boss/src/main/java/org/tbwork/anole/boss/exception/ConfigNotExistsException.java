package org.tbwork.anole.boss.exception;

import org.tbwork.anole.loader.types.ConfigType;
 
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
