package org.tbwork.anole.boss.server._4_publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.server.basic.server.IServerStarter;
import org.tbwork.anole.server.basic.NetUtil;
 
@Service
public class BossServer4PublisherStarter implements IServerStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(BossServer4PublisherStarter.class);
	
	@Autowired
	private AnolePublisherServerInBoss anolePublisherManagerBossServer;
	 
	private static final Integer DEFAULT_PORTS = 55556; 
	private int port;
	
	@Override
	public int getPort() {
		return port;
	} 
	
	@Override
	public int run()
    {  
    	int port = NetUtil.getPort("anole.server.boss.4publisher.port", 55556); 
    	anolePublisherManagerBossServer.start(port);  
    	return port;
    }

	@Override
	public void stop() {
		anolePublisherManagerBossServer.close();
	}
     
}
