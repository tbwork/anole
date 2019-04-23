package org.tbwork.anole.server.model;

import lombok.Data;
import org.tbwork.anole.loader.types.ConfigType;

@Data
public class ConfigDO{  
	private String key; 
	private String project;
	private ConfigType configType;
	private String description;  
	private String creator;
	private String lastOpeartor;  
}
