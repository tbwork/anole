package org.tbwork.anole.worker.client;

import org.tbwork.anole.common.interfaces.IAnoleClient;
import org.tbwork.anole.worker.model.BossInfo;


/**
 * For workers to communicate with the boss server.
 */
public interface IAnoleWorkerClient extends IAnoleClient{
  
	/**
	 * Save authentication information.
	 */
	public void saveToken(int clientId, int token);
	  
    /**
     * Get the weight of worker.
     */
    public int getWeight();
    
    public BossInfo getTargetBoss();
}
