package org.anole.infrastructure.repository.impl;

import org.anole.infrastructure.dao.AnoleBossMapper;
import org.anole.infrastructure.dao.AnoleSysSettingMapper;
import org.anole.infrastructure.example.AnoleSysSettingExample;
import org.anole.infrastructure.model.AnoleSysSetting;
import org.anole.infrastructure.repository.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingService implements ISettingService {

	@Autowired
	private AnoleSysSettingMapper anoleSysSettingMapper;

	@Autowired
	private AnoleBossMapper anoleBossMapper;
	
	@Override
	public Long getServingBossId() { 
		return getAnoleSysSetting().getServingBoss();
	}

	@Override
	public void reselectServingBoss() {
		Long servingBossId = getServingBossId();
	}

	private AnoleSysSetting getAnoleSysSetting() {
		AnoleSysSettingExample asse = new AnoleSysSettingExample();
    	List<AnoleSysSetting> settings = anoleSysSettingMapper.selectByExample(asse);
    	if(settings == null || settings.isEmpty()) {
    		throw new RuntimeException("Please intialize the basic settings first. (Notice: This is usually caused by deleting or modifing anole's table data manually, if so, please backup the configuration data and reinstall the Anole.)");
    	}
		return settings.get(0);
	}
	
	@Override
	public Integer getTouchInterval() { 
		return getAnoleSysSetting().getTouchInterval();
	}

	@Override
	public Integer getTouchStopCount() { 
		return getAnoleSysSetting().getTouchStopCount();
	}

	@Override
	public Integer getHeartbeatInterval() { 
		return getAnoleSysSetting().getHeartBeatInterval();
	}

	
}
