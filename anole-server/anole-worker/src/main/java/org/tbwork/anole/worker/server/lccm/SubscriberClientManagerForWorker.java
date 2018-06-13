package org.tbwork.anole.worker.server.lccm;  
import java.util.Map.Entry;
import java.util.Set;

import org.tbwork.anole.server.basic.lccm.impl.LongConnectionClientManager;
import org.tbwork.anole.server.basic.lccm.skeleton.LongConnectionClientSkeleton;
import org.tbwork.anole.server.basic.model.requests.RegisterRequest;
import org.tbwork.anole.worker.repository.IConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.common.model.ValueChangeDTO;

import io.netty.channel.socket.SocketChannel; 

/**
 * Subscriber manager used for worker server.
 * @author Tommy.Tang
 */
@Service("subscriberClientManager")
public class SubscriberClientManagerForWorker extends LongConnectionClientManager{ 
	
	@Autowired
	private IConfigRepository cr;
	
	@Override
	protected LongConnectionClientSkeleton createClient(int token, RegisterRequest registerRequest) {
		if(registerRequest.getRegisterParameter() == null || registerRequest.getRegisterParameter().getEnv() == null){
			throw new RuntimeException("Subscriber 's runtime environment should be specified.");
		}
		return new SubscriberClientSkeleton(token, registerRequest.getSocketChannel(), registerRequest.getRegisterParameter().getEnv());
	} 
	 
	public void ackChangeNotify(int clientId, String key, long timestamp){
		SubscriberClientSkeleton sc = (SubscriberClientSkeleton)  longConnectionClientMap.get(clientId);
		if(sc == null)
			throw new RuntimeException("Cound not find subscriber client with id = "+ clientId);
		sc.ackChangeNotification(key, timestamp);
	}
	
	public void fillInformation(int clientId, SocketChannel socketChannel){
		SubscriberClientSkeleton sc = (SubscriberClientSkeleton)  longConnectionClientMap.get(clientId);
		if(sc == null)
			throw new RuntimeException("Cound not find subscriber client with id = "+ clientId);
		sc.setSocketChannel(socketChannel);
	}
	 
	public void notifyChange(ValueChangeDTO vcd){
		Set<Entry<Integer, LongConnectionClientSkeleton>> entrySet = longConnectionClientMap.entrySet();
		for(Entry<Integer, LongConnectionClientSkeleton> item : entrySet){
			SubscriberClientSkeleton sc = (SubscriberClientSkeleton)  item.getValue(); 
			cr.setConfigValue(vcd.getKey(), vcd.getValue(), vcd.getEnv(), vcd.getConfigType()); 
			sc.addNewChangeNotification(vcd);
			sc.sendChangeNotification(vcd);
		}
	}
	
	/**
	 * Send not-notified changes for each subscriber client 
	 */
	public void notifyAllChanges(){
		Set<Entry<Integer, LongConnectionClientSkeleton>> entrySet = longConnectionClientMap.entrySet();
		for(Entry<Integer, LongConnectionClientSkeleton> item : entrySet){
			SubscriberClientSkeleton sc = (SubscriberClientSkeleton)  item.getValue();
			sc.sendAllChangeNotifications();
		}
	}
	
	public void setCaredKey(int clientId, String key){
		SubscriberClientSkeleton sc = (SubscriberClientSkeleton)  longConnectionClientMap.get(clientId);
		if(sc == null)
			throw new RuntimeException("Cound not find subscriber client with id = "+ clientId); 
		sc.addCaredKey(key);
	}
}
