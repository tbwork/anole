package org.tbwork.anole.server.services;

import java.util.Set;

import org.anole.infrastructure.model.AnoleConfigItem;
import org.tbwork.anole.common.model.ConfigModifyDTO;
import org.tbwork.anole.common.model.ConfigModifyResultDTO;

public interface IConfigUpdateService {

	public void updateConigItemSelective(AnoleConfigItem configItem, String operator, String project, Set<String> affectedEnvs); 

	public ConfigModifyResultDTO motifyConfig(String operator, ConfigModifyDTO ccd, Set<String> affectedEnvs);
	
}
