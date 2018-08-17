package org.tbwork.anole.server.basic.repository;

public interface ISettingService {

	public Long getServingBossId();
	
	public void reselectServingBoss();
	
	public Integer getTouchInterval();
	
	public Integer getTouchStopCount();
	
	public Integer getHeartbeatInterval();
	
}
