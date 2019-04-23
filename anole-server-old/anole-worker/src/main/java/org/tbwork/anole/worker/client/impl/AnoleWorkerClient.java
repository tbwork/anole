package org.tbwork.anole.worker.client.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.tbwork.anole.server.basic.repository.ISettingService;
import org.tbwork.anole.server.basic.server.AnoleServer;
import org.anole.infrastructure.dao.AnoleBossMapper;
import org.anole.infrastructure.dao.AnoleSysSettingMapper;
import org.anole.infrastructure.example.AnoleBossExample;
import org.anole.infrastructure.example.AnoleSysSettingExample;
import org.anole.infrastructure.model.AnoleBoss;
import org.anole.infrastructure.model.AnoleSysSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.message.c_2_s.C2SMessage;
import org.tbwork.anole.loader.context.Anole;
import org.tbwork.anole.worker.client.IAnoleWorkerClient;
import org.tbwork.anole.worker.client.IConnectionMonitor;
import org.tbwork.anole.worker.client.config.WorkerClientConfig;
import org.tbwork.anole.worker.client.handler.AuthenticationHandler;
import org.tbwork.anole.worker.client.handler.ExceptionHandler;
import org.tbwork.anole.worker.client.handler.OtherLogicHandler;
import org.tbwork.anole.worker.exception.AuthenticationNotReadyException;
import org.tbwork.anole.worker.exception.SocketChannelNotReadyException;
import org.tbwork.anole.worker.model.BossInfo;
import org.tbwork.anole.worker.server.lccm.SubscriberClientManagerForWorker;
import org.tbwork.anole.worker.service.IWorkerRegisterService;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Work use this client to communicate with Boss server.
 */
@Data
@Service("workerClient")
public class AnoleWorkerClient implements IAnoleWorkerClient{ 
	
	@Getter(AccessLevel.NONE)@Setter(AccessLevel.NONE) 
	private volatile boolean started; 
	@Getter(AccessLevel.NONE)@Setter(AccessLevel.NONE) 
	private volatile boolean connected;
	@Getter(AccessLevel.NONE)@Setter(AccessLevel.NONE) 
	private static final Logger logger = LoggerFactory.getLogger(AnoleWorkerClient.class);
	@Getter(AccessLevel.NONE)@Setter(AccessLevel.NONE) 
	SocketChannel socketChannel = null; 
	
	@Autowired
    private IConnectionMonitor lcMonitor;  
	 
	@Autowired
	@Qualifier("subscriberWorkerServer")
	private AnoleServer subscriberWorkerServer;
	
	@Autowired
	private AnoleBossMapper anoleBossMapper;
	
	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private IWorkerRegisterService workerRegisterService;
	
	@Autowired
	private SubscriberClientManagerForWorker subscriberClientManagerForWorker;
	
	int clientId = 0; // assigned by the server
    int token = 0;    // assigned by the server 
	 
    private BossInfo targetBoss;
    
    /**
     * Used to detect disconnection
     */
    private int pingCount = 0;
    /**
     * Used to detect disconnection
     */
    private int MAX_PING_COUNT = 5; 
    
    
    //Properties
    public static enum ClientProperties{ 
    	BOSS_2_WOKRER_SERVER_ADDRESS("anole.client.worker.boss.address", "localhost:54325,localhost:54326"),  
    	;
    	private String name;
    	private String defaultValue;
    	
    	private ClientProperties(String name, String defaultValue){
    		this.name = name;
    		this.defaultValue = defaultValue;
    	}
    	
    }
     
    
    @Override
  	public void connect() {
  		if(!started || !connected) //DCL-1
  		{
  			synchronized(AnoleWorkerClient.class)
  			{
  				if(!started || !connected)//DCL-2
  				{ 
  					boolean flag = started; 
  					executeConnect(); 
  					try {
  						TimeUnit.SECONDS.sleep(2);
  					} catch (InterruptedException e) { 
  						e.printStackTrace();
  					} 
  					if(!flag )
  						lcMonitor.start();
  				}
  			}
  		} 
    }
  	
      
    @Override
  	public void close(){
  		if(!started) //DCL-1
  		{
  			synchronized(AnoleWorkerClient.class)
  			{
  				if(!started)//DCL-2
  				{
  					lcMonitor.stop();
  					executeClose(); 
  				}
  			}
  		} 
  		
  	}
  	
      @Override
  	public void sendMessage(C2SMessage msg)
  	{ 
  		sendMessageWithFuture(msg);
  	}
  	
      @Override
  	public void sendMessageWithListeners(C2SMessage msg, ChannelFutureListener ... listeners)
  	{
  		ChannelFuture f = sendMessageWithFuture(msg);
  		for(ChannelFutureListener item : listeners)
  		    f.addListener(item);  
  	} 
  	
  	 
  	private ChannelFuture sendMessageWithFuture(C2SMessage msg){ 
  		if(socketChannel != null)
  		{
  			if(!MessageType.C2S_COMMON_AUTH.equals(msg.getType()))
  				tagMessage(msg);
  			return socketChannel.writeAndFlush(msg);
  		}
  		throw new SocketChannelNotReadyException();
  	} 
  	
   
  	private void executeConnect(){
  		while(true) {
  			targetBoss = getCurrentServingBoss();
  	  		if(executeConnect(targetBoss)) { 
  					break ;
  			} 
  	  		logger.warn("Current target boss is not reachable, trying to connect the other valid bosses.");
  	  	    List<BossInfo> bosses = workerRegisterService.getValidBossServers();
  	  	    for(BossInfo boss : bosses) {
	  	  	    if(executeConnect(boss)) { 
						break ;
				} 
  	  	    }
  		}  
    }
  	
  	/** 
  	 * @param bossInfo in the form of "ip:port"
  	 */
  	private boolean executeConnect(BossInfo bossInfo)
  	{ 
  		Preconditions.checkNotNull (bossInfo, "address should be null.");  
  		boolean result  = executeConnect(bossInfo.getAddress(), bossInfo.getPort(), this);
  		if(result)
  			logger.debug("[:)] Connect to boss server ({}:{}) successfully!", bossInfo.getAddress(), bossInfo.getPort());
  		else
  			logger.warn("[:(] Connect to boss server ({}:{}) failed!", bossInfo.getAddress(), bossInfo.getPort());
  		return result;
  	}
  	
  	private boolean executeConnect(String host, int port, final IAnoleWorkerClient anoleWorkerClient)
  	{ 
  		Preconditions.checkNotNull (host  , "host should be null.");
  		Preconditions.checkArgument(port > 0, "port should be > 0"  );
          EventLoopGroup workerGroup = new NioEventLoopGroup(); 
          try {
              Bootstrap b = new Bootstrap();
              b.group(workerGroup);
              b.channel(NioSocketChannel.class);
              b.option(ChannelOption.SO_KEEPALIVE, true);
              b.handler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                      ch.pipeline().addLast(
                    		new ExceptionHandler(anoleWorkerClient),
                      		new ObjectEncoder(),
                     		    new ObjectDecoder(ClassResolvers.cacheDisabled(null)), 
                      		new AuthenticationHandler(anoleWorkerClient),  
                      		new OtherLogicHandler(anoleWorkerClient, subscriberWorkerServer, lcMonitor, subscriberClientManagerForWorker)
                      		);
                  }
              }); 
              // Start the client.
              ChannelFuture f = b.connect(host, port).sync();  
              if (f.isSuccess()) {
              	socketChannel = (SocketChannel)f.channel(); 
              	started = true;
              	connected = true;
                return true;
              }  
              return false;
          }
          catch (InterruptedException e) {  
  			return false;
  		} 
  	}
  	
  	private void executeClose()
  	{
  		try {
  			clientId = 0; //reset
  			token = 0;//reset
  			socketChannel.closeFuture().sync();
  			socketChannel = null;
  		} catch (InterruptedException e) {
  			logger.error("[:(] Anole client (clientId = {}) failed to close. Inner message: {}", clientId, e.getMessage());
  			e.printStackTrace();
  		}finally{
  			if(!socketChannel.isActive())
  			{
  				logger.info("[:)] Anole client (clientId = {}) closed successfully !", clientId);			            	
  				started = false;
  				connected = false;
  			}
  		}
  	}

  	@Override
  	public void reconnect() {
  		 this.close();
  		 this.connect();
  	}

  	@Override
  	public void saveToken(int clientId, int token) {
  		 this.clientId = clientId;
  		 this.token = token;
  	}
	
	/**
	 * Tag each message with current clientId and token before sending.
	 */
	private void tagMessage(C2SMessage msg){
		if(clientId == 0 && token == 0)
			throw new AuthenticationNotReadyException();
		msg.setClientId(clientId);
	    msg.setToken(token);
	}
	 
	private String getProperty(ClientProperties clientProperties){
    	return Anole.getProperty(clientProperties.name, clientProperties.defaultValue);
	}
	    
    private int getIntProerty(ClientProperties clientProperties){
    	return Anole.getIntProperty(clientProperties.name, Integer.valueOf(clientProperties.defaultValue));
    } 
    
    public void addPingCount(){
    	pingCount ++;
    }
    
    public void ackPing(){
    	pingCount --;
    }
    
    public boolean canPing(){
    	return pingCount <= MAX_PING_COUNT;
    }


	@Override
	public void setConnected(boolean connected) {
		this.connected = connected;
	}


	@Override
	public boolean isConnected() {
		return connected;
	}


	@Override
	public int getWeight() {
		return Anole.getIntProperty("worker.weight", WorkerClientConfig.DEFAULT_WEIGHT); 
	}


	@Override
	public BossInfo getTargetBoss() { 
		return targetBoss;
	}
    
    private BossInfo getCurrentServingBoss(){ 
    	AnoleBoss anoleBoss = anoleBossMapper.selectByPrimaryKey(settingService.getServingBossId()); 
    	return new BossInfo(anoleBoss.getId(), anoleBoss.getAddress(), anoleBoss.getPortForWorker());
    }
    
}
