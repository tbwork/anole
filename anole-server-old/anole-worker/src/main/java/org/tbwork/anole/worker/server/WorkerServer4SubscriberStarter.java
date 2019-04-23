package org.tbwork.anole.worker.server;
 
import org.tbwork.anole.server.basic.server.IServerStarter;
import org.tbwork.anole.server.basic.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.worker.client.impl.AnoleWorkerClient;
import org.tbwork.anole.worker.server.impl.AnoleSubscriberServerInWorker;
 

/**
 * Worker runs here.
 */ 
@Service
public class WorkerServer4SubscriberStarter  implements IServerStarter
{   
	@Autowired
	private AnoleSubscriberServerInWorker anoleSubscriberManagerWorkerServer; 
	
	private int port;
	
	/**
	 * Worker as a client to connect to the Boss server.
	 */
	@Autowired
	private AnoleWorkerClient workerClient;
	 
	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return port;
	}

	@Override
	public int run() {
		workerClient.connect();
    	int port = SystemUtil.getOneValidPort();
        anoleSubscriberManagerWorkerServer.start(port);
        return port;
	}

	@Override
	public void stop() {
		anoleSubscriberManagerWorkerServer.close();
	} 
	
	
}
