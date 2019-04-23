package org.tbwork.anole.boss.server._4_subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.server.basic.server.IServerStarter;
import org.tbwork.anole.server.basic.NetUtil;
 
@Service
public class BossServer4SubscriberStarter implements IServerStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(BossServer4SubscriberStarter.class);
	
	@Autowired
	private AnoleSubscriberServerInBoss anoleSubscriberManagerBossServer;
	
	private static final Integer DEFAULT_PORTS = 55555; 
	
	private int port;
	
	@Override
	public int getPort() {
		return port;
	} 
	
	@Override
	public int run()
    {   
    	int port = NetUtil.getPort("anole.server.boss.4subscriber.port", DEFAULT_PORTS); 
    	anoleSubscriberManagerBossServer.start(port);  
    	return port;
    }

	@Override
	public void stop() {
		anoleSubscriberManagerBossServer.close();
	} 
}
