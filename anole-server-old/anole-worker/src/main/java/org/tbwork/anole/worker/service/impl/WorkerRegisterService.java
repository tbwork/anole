package org.tbwork.anole.worker.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.anole.infrastructure.dao.AnoleBossMapper;
import org.anole.infrastructure.dao.AnoleWorkerMapper;
import org.anole.infrastructure.example.AnoleBossExample;
import org.anole.infrastructure.example.AnoleWorkerExample;
import org.anole.infrastructure.model.AnoleBoss;
import org.anole.infrastructure.model.AnoleWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tbwork.anole.server.basic.enums.ServerStatus;
import org.tbwork.anole.server.basic.server.AnoleServer;
import org.tbwork.anole.server.basic.NetUtil;
import org.tbwork.anole.worker.client.IAnoleWorkerClient;
import org.tbwork.anole.worker.model.BossInfo;
import org.tbwork.anole.worker.service.IWorkerRegisterService;

@Service
public class WorkerRegisterService implements IWorkerRegisterService {

	private static Long workerId = 0L; 
	
	@Autowired
	private AnoleWorkerMapper anoleWorkerMapper;
	
	@Autowired
	private AnoleBossMapper anoleBossMapper;
	
	@Autowired
	@Qualifier("workerServer")
	private AnoleServer workerServer;
	
	@Autowired
	private IAnoleWorkerClient anoleWorkerClient;
	
	@Override
	public void touch() {
		AnoleWorker aw = anoleWorkerMapper.selectByPrimaryKey(workerId);
		if(aw!=null) {
			aw.setTouch(System.currentTimeMillis());
			anoleWorkerMapper.updateByPrimaryKey(aw);
		}
	}

	@Override
	public void register() {
		String address = NetUtil.getLocalAddress();
		Integer port = workerServer.getPort();
		AnoleWorker anoleWorker = getByAddressAndPort(address, port);
		if(anoleWorker == null) {
			anoleWorker = new AnoleWorker();
			anoleWorker.setAddress(address); 
			anoleWorker.setCreateTime(new Date());
			anoleWorker.setPort(port);
			anoleWorker.setStatus(ServerStatus.RUNNING.getStatus());
			anoleWorker.setTouch(System.currentTimeMillis());
			anoleWorker.setTargetBoss(anoleWorkerClient.getTargetBoss().getId());
			anoleWorkerMapper.insert(anoleWorker);
			workerId = anoleWorker.getId();
		}
		else {  
			workerId = anoleWorker.getId();
			anoleWorker.setCreateTime(new Date()); 
			anoleWorker.setStatus(ServerStatus.RUNNING.getStatus());
			anoleWorker.setTouch(System.currentTimeMillis());
			anoleWorker.setTargetBoss(anoleWorkerClient.getTargetBoss().getId());
			anoleWorkerMapper.updateByPrimaryKeySelective(anoleWorker);
		}
	}

	@Override
	public List<BossInfo> getValidBossServers() {
		List<BossInfo> result = new ArrayList<BossInfo>();
		AnoleBossExample abe = new AnoleBossExample();
		abe.createCriteria().andStatusGreaterThan((byte)0);
		List<AnoleBoss> bosses = anoleBossMapper.selectByExample(abe);
		Collections.sort(bosses, new Comparator<AnoleBoss>() { 
			@Override
			public int compare(AnoleBoss o1, AnoleBoss o2) { 
				return Long.compare(o1.getId(), o2.getId());
			}  
		});
		for(AnoleBoss boss : bosses) {
			result.add(new BossInfo(boss.getId(), boss.getAddress(), boss.getPortForWorker()));
		}
		return result; 
	}

	
	private AnoleWorker getByAddressAndPort(String address, int port) {
		AnoleWorkerExample awe = new AnoleWorkerExample();
		awe.createCriteria().andAddressEqualTo(address).andPortEqualTo(port);
		List<AnoleWorker> anoleWorkers = anoleWorkerMapper.selectByExample(awe);
		if(anoleWorkers!=null && !anoleWorkers.isEmpty()) {
			return anoleWorkers.get(0);
		}
		return null;
	}
	
	
}
