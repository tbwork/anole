package org.tbwork.anole.worker.repository;

import org.tbwork.anole.server.basic.model.ConfigValueDO;
import org.tbwork.anole.loader.types.ConfigType;

/**
 * Configuration repository used in local memory for worker.
 * @author Tommy.Tang
 */
public interface IConfigRepository {

	/**
	 * Retrieve configuration by specified key.
	 */
	public ConfigValueDO retrieveConfigValueByKey(String key, String env);
	
	/**
	 * Set a value for certain configuration of certain environment. 
	 */
	public void setConfigValue(String key, String value, String env, ConfigType configType);  
	
}
