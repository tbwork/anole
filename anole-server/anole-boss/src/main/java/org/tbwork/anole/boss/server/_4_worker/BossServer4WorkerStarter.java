package org.tbwork.anole.boss.server._4_worker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.boss.util.StringUtil;
 

/**
 * Yes, Anole goes here.
 */ 
@Service
public class BossServer4WorkerStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(BossServer4WorkerStarter.class);
	
	@Autowired
	private AnoleWorkerManagerBossServer anoleWorkerManagerBossServer;
	
	private static final String DEFAULT_PORTS = "54325,54326"; 
	 
	public void run()
    {   
    	int port = StringUtil.getPort("anole.server.boss.4worker.port", DEFAULT_PORTS); 
    	anoleWorkerManagerBossServer.start(port); 
    } 
}
