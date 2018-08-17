package org.tbwork.anole.boss.services.impl;

import java.util.Date;
import java.util.List;

import org.anole.infrastructure.dao.AnoleBossMapper;
import org.anole.infrastructure.example.AnoleBossExample;
import org.anole.infrastructure.model.AnoleBoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.boss.AppStarter;
import org.tbwork.anole.boss.server._4_publisher.AnolePublisherServerInBoss;
import org.tbwork.anole.boss.server._4_subscriber.AnoleSubscriberServerInBoss;
import org.tbwork.anole.boss.server._4_worker.AnoleWorkerServerInBoss;
import org.tbwork.anole.boss.services.IBossRegisterService;
import org.tbwork.anole.loader.context.Anole;
import org.tbwork.anole.server.basic.StaticConfiguration;
import org.tbwork.anole.server.basic.enums.ServerStatus;
import org.tbwork.anole.server.basic.util.NetUtil;
import org.tbwork.anole.server.basic.util.StringUtil;

@Service
public class BossRegisterService implements IBossRegisterService {

	private static Long bossId = 0L;
	 
	@Autowired
	private AnoleBossMapper anoleBossMapper;
	
	@Autowired
	private AnolePublisherServerInBoss publisherServer;
	
	@Autowired
	private AnoleSubscriberServerInBoss subscriberServer;
	
	@Autowired
	private AnoleWorkerServerInBoss workerServer;
	
	@Override
	public void touch() {
		AnoleBoss ab = anoleBossMapper.selectByPrimaryKey(bossId);
		if( ab != null) {
			ab.setTouch(System.currentTimeMillis());
			anoleBossMapper.updateByPrimaryKey(ab);
		}
	}

	@Override
	public void register() {
		String alias = AppStarter.getAlias();
		AnoleBoss anoleBoss = getByAlias(alias);
		String address = NetUtil.getLocalAddress();
		Integer portForWorker = workerServer.getPort();
		Integer portForSubscriber = subscriberServer.getPort();
		Integer portForPublisher = publisherServer.getPort(); 
		if(anoleBoss == null) {
			AnoleBoss ab = new AnoleBoss();
			ab.setAddress(address);
			ab.setCreateTime(new Date());
			ab.setPortForPublisher(portForPublisher);
			ab.setPortForSubscriber(portForSubscriber);
			ab.setPortForWorker(portForWorker);
			ab.setStatus(ServerStatus.RUNNING.getStatus()); 
			ab.setAlias(AppStarter.getAlias());
			ab.setTouch(System.currentTimeMillis());
			ab.setUpdateTime(new Date());
			ab.setWeight(Anole.getIntProperty("anole.server.boss.weight", StaticConfiguration.BOSS_DEFAULT_WEIGHT));
			anoleBossMapper.insert(ab);
			bossId = ab.getId();
		}
		else {
			anoleBoss.setAddress(address);
			anoleBoss.setPortForPublisher(portForPublisher);
			anoleBoss.setPortForSubscriber(portForSubscriber);
			anoleBoss.setPortForWorker(portForWorker);
			anoleBoss.setUpdateTime(new Date());
			anoleBoss.setTouch(System.currentTimeMillis());
			anoleBoss.setWeight(Anole.getIntProperty("anole.server.boss.weight", StaticConfiguration.BOSS_DEFAULT_WEIGHT));
			anoleBoss.setStatus(ServerStatus.RUNNING.getStatus());
			anoleBossMapper.updateByPrimaryKeySelective(anoleBoss);
		} 
	}
	
	private AnoleBoss getByAlias(String alias) {
		AnoleBossExample abe = new AnoleBossExample();
		abe.createCriteria().andAliasEqualTo(alias);
		List<AnoleBoss> anoleBosses = anoleBossMapper.selectByExample(abe);
		if(anoleBosses == null && anoleBosses.isEmpty()) {
			return null;
		}
		return anoleBosses.get(0);
	} 
	
}
