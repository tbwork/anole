package org.tbwork.anole.server.basic.model;
 
import org.tbwork.anole.loader.types.ConfigType; 

import lombok.Data;
@Data
public class ConfigDO{  
	private String key; 
	private ConfigType configType;
	private String description;  
	private String creator;
	private String lastOpeartor;  
}
