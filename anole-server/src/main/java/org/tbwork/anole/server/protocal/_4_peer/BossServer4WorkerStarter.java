package org.tbwork.anole.server.protocal._4_peer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.server.basic.server.IServerStarter;
import org.tbwork.anole.server.basic.NetUtil;
 
 
@Service
public class BossServer4WorkerStarter implements IServerStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(BossServer4WorkerStarter.class);
	
	@Autowired
	private AnoleWorkerServerInBoss anoleWorkerManagerBossServer; 
	 
	private int port;
	
	@Override
	public int getPort() {
		return port;
	}
	
	@Override
	public int run()
    {   
    	port = NetUtil.getPort("anole.server.boss.4worker.port"); 
    	anoleWorkerManagerBossServer.start(port); 
    	return port;
    }

	@Override
	public void stop() { 
		anoleWorkerManagerBossServer.close();
	} 
}
