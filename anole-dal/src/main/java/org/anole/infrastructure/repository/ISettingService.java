package org.anole.infrastructure.repository;

public interface ISettingService {

	public Long getServingBossId();
	
	public void reselectServingBoss();
	
	public Integer getTouchInterval();
	
	public Integer getTouchStopCount();
	
	public Integer getHeartbeatInterval();
	
}
