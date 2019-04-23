package org.tbwork.anole.server.model;

import lombok.Data;
import org.tbwork.anole.loader.types.ConfigType;

@Data
public class ConfigValueDO { 
	private String key;
	private String env;
	private String value;
	private String lastOperator; 
	private ConfigType configType;
	
}
