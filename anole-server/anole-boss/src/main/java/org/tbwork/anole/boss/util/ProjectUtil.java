package org.tbwork.anole.boss.util;

public class ProjectUtil {

	public static String getProjectName(String configKeyName){ 
		return configKeyName.substring(0, configKeyName.indexOf('.')); 
	}
	 
	
}
