package org.tbwork.anole.server.basic.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tbwork.anole.server.basic.IAppStarter;
import org.tbwork.anole.server.basic.StaticConfiguration;
import org.tbwork.anole.server.basic.repository.ISettingService;
import org.tbwork.anole.server.basic.server.AnoleServer;
import org.tbwork.anole.server.basic.service.IServerRegisterService;

/**
 * 在线保持JOB
 * 失联超过一定次数（数据库配置）就自行关闭。
 */
@Component
public class TouchKeepJob {
	
	@Autowired
	private IServerRegisterService registerService;

	@Autowired
	private ISettingService settingService;
	
	@Autowired 
	private IAppStarter appStarter;
	
	private Integer untouchCount = 0;
	
	@Scheduled(fixedDelay = StaticConfiguration.SERVER_TOUCH_INTERVAL)
	public void run(){  
		try {
			registerService.touch();
		}
		catch(Exception e) {
			untouchCount ++;
		}
		if(untouchCount > settingService.getTouchStopCount()) {
			appStarter.stop();
		}
	}
	
	
}
