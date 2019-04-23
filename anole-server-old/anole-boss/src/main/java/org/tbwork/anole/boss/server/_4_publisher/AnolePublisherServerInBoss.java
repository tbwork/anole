package org.tbwork.anole.boss.server._4_publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service; 
import org.tbwork.anole.boss.server._4_publisher.handler.AuthenticationHandler;
import org.tbwork.anole.boss.server._4_publisher.handler.ExceptionHandler;
import org.tbwork.anole.boss.server._4_publisher.handler.MainLogicHandler;
import org.tbwork.anole.boss.server._4_publisher.handler.NewConnectionHandler;
import org.tbwork.anole.server.basic.server.AnoleServer;

import com.google.common.base.Preconditions;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Anole publisher's boss server manages all publisher clients.
 * It receives and processes all write requests from publishers.
 * @author tommy.tang
 */
@Service("publisherServer")
public class AnolePublisherServerInBoss implements AnoleServer{

	private int port; 
	
	volatile boolean started;
	static final Logger logger = LoggerFactory.getLogger(AnolePublisherServerInBoss.class);
	Channel channel = null;
	EventLoopGroup bossGroup = null;
	EventLoopGroup workerGroup = null;  
	@Autowired
	@Qualifier("b4pAuthenticationHandler")
	AuthenticationHandler authenticationHandler;
	
	@Autowired
	@Qualifier("b4pMainLogicHandler")
	MainLogicHandler mainLogicHandler;
	
	@Autowired
	@Qualifier("b4pNewConnectionHandler")
	NewConnectionHandler newConnectionHandler;
	
	@Autowired
	@Qualifier("b4pExceptionHandler")
	ExceptionHandler lowLevelExceptionHandler;
	 
	@Override
	public void start(int port) {
		if(!started) //DCL-1
		{
			synchronized(AnolePublisherServerInBoss.class)
			{
				if(!started)//DCL-2
				{
					executeStart(port); 
				}
			}
		} 
	}

	@Override
	public void close() {
		if(started) //DCL-1
		{
			synchronized(AnolePublisherServerInBoss.class)
			{
				if(started)//DCL-2
				{
					executeClose(); 
				}
			}
		} 
	}

	@Override
	public int getPort() { 
		return port;
	}
	
	private void executeStart(int port){
		Preconditions.checkArgument(port>0, "port should be > 0");
		this.port = port;
		bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(  
                    		 lowLevelExceptionHandler,
                    		 new ObjectEncoder(),
                    		 newConnectionHandler, 
                    		 new ObjectDecoder(ClassResolvers.cacheDisabled(null)), 
                    		 authenticationHandler, 
                    		 mainLogicHandler
                    		 );
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)      
             .childOption(ChannelOption.SO_KEEPALIVE, true); 

             // Bind and start to accept incoming connections. 
			 ChannelFuture f = b.bind(port).sync(); 
             if(f.isSuccess())
             {    
            	 channel = f.channel();
            	 logger.info("[:)] Anole boss server for publisher started succesfully at local address (port = {}) !", port);
            	 started = true;
             }
			 
        }catch(InterruptedException e){ 
        	logger.error("[:(] Anole boss server for publisher failed to start at port {}!", port);
			e.printStackTrace();
        }  
	}
	
	private void executeClose(){ 
		try {
			channel.disconnect();
			channel.close(); 
		} catch (Exception e) {
			logger.error("[:(] Anole boss server for publisher failed to close. Inner message: {}", e.getMessage());
			e.printStackTrace();
		}finally{ 
			if(!channel.isActive())
			{
				logger.info("[:)] Anole boss server for publisher closed successfully !");		
				workerGroup.shutdownGracefully();
		        bossGroup.shutdownGracefully();
				started = false;
			}
		} 
	}

}
