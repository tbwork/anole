package org.tbwork.anole.boss.services.impl;

import org.anole.infrastructure.dao.AnoleConfigItemMapper;
import org.anole.infrastructure.dao.AnoleConfigMapper;
import org.anole.infrastructure.dao.AnoleUserProjectMapMapper;
import org.anole.infrastructure.example.AnoleConfigExample;
import org.anole.infrastructure.example.AnoleConfigItemExample;
import org.anole.infrastructure.model.AnoleConfig;
import org.anole.infrastructure.model.AnoleConfigItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.boss.services.IConfigQueryService;

import java.util.List;

@Service("configService")
public class ConfigQueryService implements IConfigQueryService {

	@Autowired
	private AnoleConfigItemMapper anoleConfigItemMapper;
	@Autowired
	private AnoleUserProjectMapMapper anoleUserProjectMapMapper;
	@Autowired
	private AnoleConfigMapper anoleConfigMapper;
	
	@Override
	public AnoleConfigItem getAnoleConfigItem(String key, String env) {
		AnoleConfigItemExample acie = new AnoleConfigItemExample();
		acie.createCriteria().andKeyEqualTo(key).andEnvNameEqualTo(env);
		List<AnoleConfigItem> results = anoleConfigItemMapper.selectByExample(acie);
		if( results != null && results.isEmpty() ) {
			return null;
		}
		return results.get(0);
	}
	
	@Override
	public AnoleConfig getConfigByKey(String key) {
		AnoleConfigExample ace = new AnoleConfigExample();
		ace.createCriteria().andKeyEqualTo(key); 
		List<AnoleConfig> anoleConfigs = anoleConfigMapper.selectByExample(ace);
		if(anoleConfigs!=null && !anoleConfigs.isEmpty())
			return anoleConfigs.get(0);
		return null;
	}
	
	
	
	
}
