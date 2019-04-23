package org.tbwork.anole.boss.services;

import org.anole.infrastructure.model.AnoleConfig;
import org.anole.infrastructure.model.AnoleConfigItem;

public interface IConfigQueryService {

	public AnoleConfigItem getAnoleConfigItem(String key, String env);
	
	public AnoleConfig getConfigByKey(String key);
}
