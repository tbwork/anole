package org.tbwork.anole.gui.domain.config.impl;

import org.anole.infrastructure.dao.AnoleConfigMapper;
import org.anole.infrastructure.dao.extend.AnoleConfigExtMapper;
import org.anole.infrastructure.dao.extend.AnoleConfigItemExtMapper;
import org.anole.infrastructure.dao.manual.AnoleConfigComplexMapper;
import org.anole.infrastructure.example.AnoleConfigExample;
import org.anole.infrastructure.example.AnoleConfigItemExample;
import org.anole.infrastructure.model.AnoleConfig;
import org.anole.infrastructure.model.AnoleConfigItem;
import org.anole.infrastructure.model.custom.AnoleConfigDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tbwork.anole.common.model.ConfigModifyDTO;
import org.tbwork.anole.gui.domain.cache.Cache;
import org.tbwork.anole.gui.domain.config.IConfigSearchService;
import org.tbwork.anole.gui.domain.config.IConfigService;
import org.tbwork.anole.gui.domain.model.ConfigBrief;
import org.tbwork.anole.gui.domain.model.ConfigInfo;
import org.tbwork.anole.gui.domain.model.demand.AddConfigDemand;
import org.tbwork.anole.gui.domain.model.demand.DeleteConfigDemand;
import org.tbwork.anole.gui.domain.model.demand.GetConfigsByProjectAndEnvDemand;
import org.tbwork.anole.gui.domain.model.demand.ModifyConfigDemand;
import org.tbwork.anole.gui.domain.model.result.DeleteConfigResult;
import org.tbwork.anole.gui.domain.permission.IPermissionService;
import org.tbwork.anole.gui.domain.user.IUserService;
import org.tbwork.anole.gui.domain.util.CacheKeys;
import org.tbwork.anole.gui.domain.util.CommonTools;
import org.tbwork.anole.publisher.core.AnolePublisher;
import org.tbwork.anole.publisher.model.ConfigChangeRequest;
import org.tbwork.anole.publisher.model.ConfigChangeResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigService implements IConfigService {


	@Autowired
	private AnoleConfigItemExtMapper anoleConfigItemExtMapper;


	@Autowired
	private AnoleConfigMapper anoleConfigMapper;
	
	@Autowired
	private AnoleConfigComplexMapper anoleConfigComplexMapper;

	@Autowired
	private AnoleConfigExtMapper anoleConfigExtMapper;

	@Autowired
	private IUserService us;
	
	@Autowired
	private IPermissionService pers;
	 
	@Autowired
	private IConfigSearchService configSearchService;
	
	@Autowired 
	@Qualifier("localCache")
	private Cache lc ;
	 
	
	@Override
	public List<ConfigBrief> getConfigsByProjectAndEnv(GetConfigsByProjectAndEnvDemand demand) { 
		List<ConfigBrief> result = new ArrayList<ConfigBrief>();
		String cacheKey = CacheKeys.buildConfigsForProjectKey(demand.getProject(), demand.getEnv());
		List<ConfigBrief> cachedData = lc.get(cacheKey);
		if(cachedData == null){
			cachedData = new ArrayList<ConfigBrief>();
			List<AnoleConfigDetail> records = anoleConfigComplexMapper.selectConfigsByProjectAndEnv(demand.getProject(), demand.getEnv());
			for(AnoleConfigDetail item : records){
				cachedData.add(convert2Config(item));
			}
			lc.set(cacheKey, cachedData, 5*1000);// five second
		} 
		int permission = pers.getUserRole(demand.getProject(), demand.getOperator(), demand.getEnv()); 
		if(permission == 0){
			for(ConfigBrief item: cachedData){
				ConfigBrief temp = new ConfigBrief(); 
				temp.setDesc(item.getDesc());
				temp.setEnv(item.getEnv());
				temp.setKey(item.getKey());
				temp.setLastModifier(item.getLastModifier());
				temp.setType(item.getType());
				temp.setValue(CommonTools.NO_RIGHT_DESCRIPTION);
				result.add(temp.shiledValue()); 
			} 
		}
		else
			result = cachedData;
		
		return result;
	}

	@Override
	public ConfigBrief addConfig(AddConfigDemand config) {
		ConfigBrief result = new ConfigBrief(); 
		config.preCheck(); 
		ConfigChangeRequest ccr = new ConfigChangeRequest();
		ConfigModifyDTO cmDto = new ConfigModifyDTO();
		cmDto.setConfigType(ConfigType.configType(config.getDestConfigType()));
		cmDto.setValue(config.getDestValue());
		cmDto.setKey(config.getKey());
		cmDto.setProject(config.getProject());
		cmDto.setTimestamp(System.currentTimeMillis());
		if(!config.isAllEnvEnabled())
			cmDto.setEnv(config.getEnv());
		else
			cmDto.setEnv("all");
		cmDto.setDescription(config.getDescription());
		cmDto.setCreateNew(true);
		ccr.setConfigChangeDTO(cmDto);
		ccr.setOperator(config.getOperator());
		ConfigChangeResponse response = AnolePublisher.add(ccr);
		if(response == null)
			throw new RuntimeException("Add failed, no response from the server.");
		if(!response.isSuccess())
			throw new RuntimeException(response.getErrorMessage());
		result.setDesc(config.getDescription());
		result.setEnv(config.getEnv()); 
		result.setKey(config.getKey());
		result.setLastModifier(config.getOperator());
		result.setType(config.getDestConfigType());
		result.setValue(config.getDestValue());
		return result;
	}

	private boolean checkExists(String key){ 
		AnoleConfigExample ace = new AnoleConfigExample();
		ace.createCriteria().andKeyEqualTo(key);
		List<AnoleConfig> anoleConfigs = anoleConfigMapper.selectByExample(ace);
		return anoleConfigs != null && !anoleConfigs.isEmpty();
	}
	
	@Override
	public ConfigBrief modifyConfig(ModifyConfigDemand config) {
		config.preCheck();
		ConfigBrief result = new ConfigBrief(); 
		ConfigChangeRequest ccr = new ConfigChangeRequest();
		ConfigModifyDTO cmDto = new ConfigModifyDTO(); 
		cmDto.setConfigType(ConfigType.configType(config.getConfigType().byteValue()));
		cmDto.setValue(config.getValue());
		cmDto.setKey(config.getKey());
		cmDto.setProject(config.getProject());
		cmDto.setTimestamp(System.currentTimeMillis());
		cmDto.setCreateNew(false);
		cmDto.setEnv(config.getEnv());
		cmDto.setDescription(config.getDescription());
		ccr.setConfigChangeDTO(cmDto);
		ccr.setOperator(config.getOperator());
		ConfigChangeResponse response = AnolePublisher.edit(ccr);
		if(response == null)
			throw new RuntimeException("Modify failed, no response from the server.");
		if(!response.isSuccess())
			throw new RuntimeException(response.getErrorMessage());
		result.setDesc(config.getDescription());
		result.setEnv(config.getEnv()); 
		result.setKey(config.getKey());
		result.setLastModifier(config.getOperator());
		result.setType(config.getConfigType());
		result.setValue(config.getValue());
		return result;
	}

	@Override
	public ConfigBrief getConfigByKeyAndEnv(String key,String env) { 
		ConfigBrief brief = null;
		AnoleConfigItemExample anoleConfigItemExample = new AnoleConfigItemExample();
		anoleConfigItemExample.createCriteria().andKeyEqualTo(key).andEnvNameEqualTo(env);
		List<AnoleConfigItem> items = anoleConfigItemExtMapper.getAnoleConfigItemsByKey(key);
		AnoleConfigExample anoleConfigExample = new AnoleConfigExample();
		anoleConfigExample.createCriteria().andKeyEqualTo(key);
		List<AnoleConfig> configs = anoleConfigMapper.selectByExample(anoleConfigExample);
		if(configs != null && !configs.isEmpty() && items!=null && !items.isEmpty()){
			AnoleConfig tempConfig = configs.get(0);
			AnoleConfigItem tempConfigItem = items.get(0);
			brief = new ConfigBrief();
			brief.setDesc(tempConfig.getDescription());
			brief.setEnv(env);
			brief.setKey(key);
			brief.setLastModifier(tempConfigItem.getLastOperator());
			brief.setType(tempConfig.getType());
			brief.setValue(tempConfigItem.getValue());
		} 
		return brief;
	}
 
	
	private ConfigBrief convert2Config(AnoleConfigDetail acd){
		ConfigBrief result = new ConfigBrief();
		result.setDesc(acd.getDescription());
		result.setEnv(acd.getEnvName());
		result.setKey(acd.getKey());
		result.setLastModifier(acd.getLastOperator());
		result.setType(acd.getType());
		result.setValue(acd.getValue());
		return result;
	}
	
	

	@Override
	public DeleteConfigResult deleteConfig(DeleteConfigDemand demand) { 
		DeleteConfigResult result = new DeleteConfigResult(); 
		try{
			if(!pers.isOwner(demand.getProject(), demand.getOperator())) 
				throw new RuntimeException("Permission denied!!");
			anoleConfigExtMapper.deleteConfigByKey(demand.getKey());
			result.setErrorMessage("OK");
			result.setSuccess(true);
		}
		catch(Exception e){
			result.setErrorMessage(e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public ConfigInfo getConfigInfoByKeyCacheable(String key) {
		String cacheKey = CacheKeys.buildConfigInfoCacheKey(key);
		ConfigInfo result = lc.get(cacheKey);
		if(result != null) return result;
		result = new ConfigInfo();
		AnoleConfig anoleConfig = anoleConfigExtMapper.getConfigByKey(key);
		if(anoleConfig == null)
			return null;
		result.setDesc(anoleConfig.getDescription());
		result.setProject(anoleConfig.getProject());
		result.setType((int)anoleConfig.getType());
		Map<String,String> valueMap = new HashMap<String, String>();
		List<AnoleConfigItem> anoleConfigItems = anoleConfigItemExtMapper.getAnoleConfigItemsByKey(key);
		if(anoleConfigItems!=null){
			for(AnoleConfigItem item : anoleConfigItems){
				valueMap.put(item.getEnvName(), item.getValue());
			}
		} 
		lc.set(cacheKey, result, 5*60*1000);
		return result;
	}


}
