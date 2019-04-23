package org.tbwork.anole.server.basic.lccm.impl;

import io.netty.channel.socket.SocketChannel;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.tbwork.anole.server.basic.StaticConfiguration;
import org.tbwork.anole.server.basic.lccm.ILongConnectionClientManager;
import org.tbwork.anole.server.basic.lccm.skeleton.LongConnectionClientSkeleton;
import org.tbwork.anole.server.basic.model.requests.RegisterParameter;
import org.tbwork.anole.server.basic.model.requests.RegisterRequest;
import org.tbwork.anole.server.basic.model.requests.UnregisterRequest;
import org.tbwork.anole.server.basic.model.requests.ValidateRequest;
import org.tbwork.anole.server.basic.model.response.RegisterResult;
import org.tbwork.anole.server.basic.server.util.ChannelHelper;
import org.tbwork.anole.server.basic.server.util.ClientInfoGenerator;
import org.tbwork.anole.server.basic.server.util.ClientInfoGenerator.ClientInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tbwork.anole.common.enums.ClientType;
import org.tbwork.anole.common.message.s_2_c.PingAckMessage; 

/**
 * LongConnectionClientManager provides basic implementations of 
 * ILongConnectionClientManager to manage long-connection clients.
 * This manager maintains a registry table (actually a map) which  
 * contains information of all long-connection Anole clients.
 * The long-connection clients do not need to worry about abuse
 * of connection due to the following features:<br>
 * <p><b>1.</b> A heart beat mechanism is used to detect the bad
 * connections via periodically receiving ping message from clients.
 * <p><b>2.</b> A scavenger thread periodically clean bad clients whose
 * connection with server is disconnected ( valid = <b>false</b> or
 * no_response_count > MAX_NO_RESPONSE_COUNT ).
 * @author Tommy.Tang
 */
public abstract class LongConnectionClientManager implements ILongConnectionClientManager{

	private static final Logger logger = LoggerFactory.getLogger(LongConnectionClientManager.class);
	
	public Map<Integer, LongConnectionClientSkeleton> longConnectionClientMap = new ConcurrentHashMap<Integer, LongConnectionClientSkeleton>(); 
	 
	private int validClientCount;
	
	@Override
	public boolean validate(ValidateRequest request) { 
		LongConnectionClientSkeleton client = longConnectionClientMap.get(request.getClientId());
		if(client != null && request.getToken() == client.getToken())
	       return true; 
		return false;
	}
 
	/**
	 * Create a client using input registerRequest.
	 */
	protected abstract LongConnectionClientSkeleton createClient(int token, RegisterRequest registerRequest);
	 
	@Override
	public RegisterResult registerClient(RegisterRequest request) {  
		RegisterParameter rp = request.getRegisterParameter();  
		ClientInfo clientInfo =  ClientInfoGenerator.generate(request.getClientType());  
		LongConnectionClientSkeleton client = createClient(clientInfo.getToken(), request);
		longConnectionClientMap.put(clientInfo.getClientId(), client); 
		validClientCount ++;
		return new RegisterResult(clientInfo.getToken(), clientInfo.getClientId(), true);  
	}
	
	@Override
	public void unregisterClient(UnregisterRequest request) { 
		unRegisterClient(request.getClientId());
	}
	
	private void unRegisterClient(int clientId){
		validClientCount --;
		longConnectionClientMap.remove(clientId); 
	}

	@Override
	public void ackPing(int clientId){
		LongConnectionClientSkeleton client = longConnectionClientMap.get(clientId);
		if(client != null){
			client.ackPingPromise();
			PingAckMessage pam = new PingAckMessage(); 
			pam.setIntervalTime(StaticConfiguration.PROMISE_PING_INTERVAL); 
			ChannelHelper.sendMessage(client, pam);
		} 
	}
	
	@Override
	public void promisePingAndScavenge(String clientName){ 
		synchronized(longConnectionClientMap){
				logger.debug("[:)] Start to add ping promise and sweep bad {} clients.", clientName); 
				Set<Entry<Integer,LongConnectionClientSkeleton>> entrySet = longConnectionClientMap.entrySet();
				int totalCnt = entrySet.size();
				int badCnt = 0;
				for(Entry<Integer,LongConnectionClientSkeleton> item: entrySet)
				{
					LongConnectionClientSkeleton client = item.getValue();
					if(client.maxPromiseCount()){
						unRegisterClient(item.getKey());
						badCnt ++;
					}
					else
						item.getValue().addPingPromise(); 
				} 
				logger.debug("[:)] Adding ping promise and sweep bad {} clients done successfully! Scavenger report: total count of clients:{}, count of alive clients:{}, count of bad clients:{}", clientName, totalCnt, totalCnt-badCnt, badCnt);
		} 
	}
	
	@Override
	public int getClientCount() { 
		return validClientCount;
	}
	 
}
