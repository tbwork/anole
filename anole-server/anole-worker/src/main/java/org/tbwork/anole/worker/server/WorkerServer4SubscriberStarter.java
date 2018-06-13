package org.tbwork.anole.worker.server;
 
import org.tbwork.anole.server.basic.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.tbwork.anole.worker.client.impl.AnoleWorkerClient;
import org.tbwork.anole.worker.server.impl.AnoleSubscriberManagerWorkerServer;
 

/**
 * Worker runs here.
 */ 
@Service
public class WorkerServer4SubscriberStarter
{  
	private final static Logger logger = LoggerFactory.getLogger(WorkerServer4SubscriberStarter.class); 
	
	@Autowired
	private AnoleSubscriberManagerWorkerServer anoleSubscriberManagerWorkerServer; 
	
	/**
	 * Worker as a client to connect to the Boss server.
	 */
	@Autowired
	private AnoleWorkerClient workerClient;
	
	public void run()
    {  
		workerClient.connect();
    	int port = SystemUtil.getOneValidPort();
        anoleSubscriberManagerWorkerServer.start(port);
    } 
}
