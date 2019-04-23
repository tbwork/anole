package org.tbwork.anole.worker.repository.impl;

import com.google.common.base.Preconditions;
import org.anole.infrastructure.model.AnoleConfig;
import org.anole.infrastructure.model.AnoleConfigItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.loader.types.ConfigType;
import org.tbwork.anole.server.basic.cache.Cache;
import org.tbwork.anole.server.basic.exception.ConfigNotExistsException;
import org.tbwork.anole.server.basic.model.ConfigDO;
import org.tbwork.anole.server.basic.model.ConfigValueDO;
import org.tbwork.anole.server.basic.repository.ILockRepository;
import org.tbwork.anole.server.basic.service.IConfigQueryService;
import org.tbwork.anole.worker.repository.IConfigRepository;

/**
 * Used for worker server.
 * @author tommy.tang 
 */
@Service("configRepository")
public class ConfigRepository implements IConfigRepository{

	@Autowired
	private Cache cache;
	
	@Autowired
	private ILockRepository lr;
	
	@Autowired
	private IConfigQueryService configService;

	@Override
	public ConfigValueDO retrieveConfigValueByKey(String key, String env) {
		String cacheKey = buildConfigItemCacheKey(key, env);
		ConfigValueDO cvdo = cache.get(cacheKey);
		if(cvdo != null){
			return cvdo;
		} 
		AnoleConfig anoleConfig = configService.getConfigByKey(key);
		AnoleConfigItem aci = configService.getAnoleConfigItem(key, env);
		if( anoleConfig != null && aci != null)
		{
			cvdo = dpo2dmo(aci, ConfigType.configType(anoleConfig.getType()));
			cache.set(cacheKey, cvdo); // set cache
		} 
		return cvdo;
	}

	
	@Override
	public void setConfigValue(String key, String value, String env, ConfigType configType) {
		String cacheKey = buildConfigItemCacheKey(key, env);
		ConfigValueDO cvdo = cache.get(cacheKey);
		if(cvdo != null){
			cvdo.setValue(value);
			cvdo.setConfigType(configType);  
		}  
	}
	  
	private void basicCheck(ConfigValueDO cvdo){
		Preconditions.checkNotNull (cvdo.getKey(), "You should specify a key first.");
		Preconditions.checkNotNull (cvdo.getLastOperator(), "Operator should not be null.");
		Preconditions.checkNotNull (cvdo.getEnv(), "You should sepcify a environment name.");
		Preconditions.checkArgument(!cvdo.getKey().isEmpty(), "You should specify a key first."); 
		Preconditions.checkArgument(!cvdo.getEnv().isEmpty(), "You should sepcify a environment name.");
	}
	private void addOperationCheck(ConfigValueDO cvdo){
		 //nothing special
	}
	
	private void basicCheck(ConfigDO config){
		Preconditions.checkNotNull (config.getKey(), "You should specify a key first.");
		Preconditions.checkNotNull (config.getLastOpeartor(), "Operator should not be null.");
		Preconditions.checkArgument(!config.getKey().isEmpty(), "You should specify a key first.");
		Preconditions.checkArgument(!config.getLastOpeartor().isEmpty(), "Operator should not be empty.");
	}
	private void addOperationCheck(ConfigDO config){
		Preconditions.checkNotNull (config.getCreator(), "You should specify a key first.");
		Preconditions.checkArgument (!config.getCreator().isEmpty(), "Creator should not be empty.");
	}
	
	private void configExistsCheck(String configKey){
		if(!checkConfigExists(configKey))
			throw new ConfigNotExistsException(configKey);
	}
    
	
	private String buildConfigCacheKey(String key){
		return key;
	}
	
	private String buildConfigItemCacheKey(String key, String env){
		return env + key;
	}  
	
	private boolean checkConfigExists(String configKey){
		String ckey = buildConfigCacheKey(configKey);
		if(cache.contain(ckey))
			return true;
		return false;
	}
	
	private boolean checkConfigValueExists(String configKey, String env){
		String ckey = buildConfigItemCacheKey(configKey, env);
		if(cache.contain(ckey))
			return true;
		AnoleConfigItem ac = configService.getAnoleConfigItem(configKey, env);
		if(ac != null) 
			return true;
		return false;
	} 
	 
	private AnoleConfigItem dmo2dpo(ConfigValueDO dmo){
		AnoleConfigItem dpo = new AnoleConfigItem();
		dpo.setEnvName(dmo.getEnv());
		dpo.setKey(dmo.getKey());
		dpo.setValue(dmo.getValue());
		dpo.setLastOperator(dmo.getLastOperator());  
		return dpo;
	}
	
	private ConfigValueDO dpo2dmo(AnoleConfigItem dpo, ConfigType configType){
		ConfigValueDO dmo = new ConfigValueDO();
		dmo.setEnv(dpo.getEnvName());
		dmo.setKey(dpo.getKey());
		dmo.setValue(dpo.getValue());
		dmo.setLastOperator(dpo.getLastOperator()); 
		dmo.setConfigType(configType); 
		return dmo;
	}
	
	private ConfigDO dpo2dmo(AnoleConfig dpo){
		ConfigDO dmo = new ConfigDO();  
		dmo.setConfigType(ConfigType.configType(dpo.getType()));
		dmo.setCreator(dpo.getCreator());
		dmo.setDescription(dpo.getDescription()); 
		dmo.setKey(dpo.getKey());
		dmo.setProject(dpo.getProject());
		dmo.setLastOpeartor(dpo.getLastOperator()); 
		return dmo;
	}
	
	private AnoleConfig dmo2dpo(ConfigDO cdo){
		AnoleConfig dpo = new AnoleConfig(); 
		dpo.setDescription(cdo.getDescription());
		dpo.setKey(cdo.getKey());
		dpo.setProject(cdo.getProject());
		dpo.setType(cdo.getConfigType().code()); 
		dpo.setLastOperator(cdo.getLastOpeartor()); 
		return dpo;
	}
	
}
