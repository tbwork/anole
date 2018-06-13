package org.tbwork.anole.worker.client.handler;
 
import org.tbwork.anole.server.basic.model.requests.RegisterParameter;
import org.tbwork.anole.server.basic.model.requests.RegisterRequest;
import org.tbwork.anole.server.basic.model.response.RegisterResult;
import org.tbwork.anole.server.basic.server.AnoleServer;
import org.tbwork.anole.server.basic.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbwork.anole.common.enums.ClientType;
import org.tbwork.anole.common.message.Message;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.message.c_2_s.worker_2_boss.RegisterResultMessage;
import org.tbwork.anole.common.message.c_2_s.worker_2_boss.W2BChangeNotifyAckMessage;
import org.tbwork.anole.common.message.s_2_c.PingAckMessage;
import org.tbwork.anole.common.message.s_2_c.boss._2_worker.B2WConfigChangeNotifyMessage;
import org.tbwork.anole.common.message.s_2_c.boss._2_worker.RegisterClientMessage;
import org.tbwork.anole.worker.client.IAnoleWorkerClient;
import org.tbwork.anole.worker.client.IConnectionMonitor;
import org.tbwork.anole.worker.client.config.WorkerClientConfig; 
import org.tbwork.anole.worker.server.lccm.SubscriberClientManagerForWorker;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler; 
 
@Sharable
public class OtherLogicHandler  extends SimpleChannelInboundHandler<Message>{

	public OtherLogicHandler(IAnoleWorkerClient workerClient, AnoleServer subscriberWorkerServer,  IConnectionMonitor lcMonitor, SubscriberClientManagerForWorker subscriberClientManagerForWorker){
		super(true);
		this.workerClient = workerClient;
		this.subscriberWorkerServer = subscriberWorkerServer; 
		this.lcMonitor = lcMonitor;
		this.subscriberClientManagerForWorker = subscriberClientManagerForWorker;
	}
	
	static Logger logger = LoggerFactory.getLogger(OtherLogicHandler.class);

	private IConnectionMonitor lcMonitor;
	
	private IAnoleWorkerClient workerClient; 
	  
	private AnoleServer subscriberWorkerServer;
	
	private SubscriberClientManagerForWorker subscriberClientManagerForWorker;
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Message msg)
			throws Exception { 
		 MessageType msgType = msg.getType(); 
		 switch(msgType)
		 {  
		 	case S2C_PING_ACK:{ 
		 		PingAckMessage paMsg = (PingAckMessage) msg; 
		 		processPingAckResponse(paMsg);
		 	} break;
		 	case S2C_REGISTER_CLIENT:{// Boss tries to register client at work server.
		 		RegisterClientMessage rcm = (RegisterClientMessage) msg;
		 		ClientType clientType = rcm.getClientType();
		 		RegisterResult registerResult =  registerSubscriber(clientType, rcm.getEnviroment()); 
		 		RegisterResultMessage registerResultMessage = getRegisterResultMessage(registerResult, clientType);
		 		workerClient.sendMessage(registerResultMessage);
		 	} break; 
		 	
		 	case S2C_CONFIG_CHANGE_NOTIFY_B_2_W:{
		 		B2WConfigChangeNotifyMessage ccnm = (B2WConfigChangeNotifyMessage) msg;
		 		subscriberClientManagerForWorker.notifyChange(ccnm.getValueChangeDTO());
		 		//send ack
		 		W2BChangeNotifyAckMessage w2BChangeNotifyAckMessage = new W2BChangeNotifyAckMessage();
		 		w2BChangeNotifyAckMessage.setKey(ccnm.getValueChangeDTO().getKey());
		 		w2BChangeNotifyAckMessage.setTimestamp(ccnm.getValueChangeDTO().getTimestamp());
		 		workerClient.sendMessage(w2BChangeNotifyAckMessage);
		 	} break;
		 	default:{ 
		 	} break; 
		 }  
	} 
	
	private RegisterResult registerSubscriber(ClientType clientType, String environment) {
		RegisterRequest rRequest = new RegisterRequest();
		rRequest.setClientType(clientType);
		RegisterParameter registerParameter = new RegisterParameter();
		registerParameter.setEnv(environment);
		rRequest.setRegisterParameter(registerParameter);
		return  subscriberClientManagerForWorker.registerClient(rRequest); 
	}
	
	private RegisterResultMessage getRegisterResultMessage(RegisterResult registerResult, ClientType clientType){
		RegisterResultMessage result = new RegisterResultMessage(); 
		result.setResultClientId(registerResult.getClientId());
		result.setResultToken(registerResult.getToken());
		result.setResultPort(subscriberWorkerServer.getPort());
		result.setResultIp(SystemUtil.getLanIp());
		result.setResultClientType(clientType); 
		return result; 
	}
	
	private void processPingAckResponse(PingAckMessage paMsg){
		int interval = paMsg.getIntervalTime();
		if(interval > 0 && interval != WorkerClientConfig.PING_INTERVAL){
			WorkerClientConfig.PING_INTERVAL = interval ;
			WorkerClientConfig.PING_DELAY = interval;
			lcMonitor.restart();
			logger.info("Synchronize PING_INTERVAL with the server, new interval is set as {} ms", WorkerClientConfig.PING_INTERVAL);
		} 
		workerClient.ackPing();
	} 
	

}
