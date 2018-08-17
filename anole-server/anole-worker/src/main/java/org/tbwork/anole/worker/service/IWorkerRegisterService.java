package org.tbwork.anole.worker.service;

import java.util.List;

import org.tbwork.anole.server.basic.service.IServerRegisterService;
import org.tbwork.anole.worker.model.BossInfo;

public interface IWorkerRegisterService extends IServerRegisterService{
  
	public List<BossInfo> getValidBossServers();
	
}
