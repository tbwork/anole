package org.tbwork.anole.worker.client.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbwork.anole.worker.client.IAnoleWorkerClient;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise; 
/**
 * Deal with all exceptions.
 * @author tommy.tang
 */   
@Sharable
public class ExceptionHandler extends ChannelHandlerAdapter {

	static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);  
	
	private IAnoleWorkerClient workerClient;
	
	public ExceptionHandler(IAnoleWorkerClient workerClient){
		this.workerClient = workerClient;
	}
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
    	if(cause instanceof IOException) { 
    		logger.warn("The Anole server (address = {}) disconnected initiatively! ", ctx.channel().remoteAddress());
    	}
    	else {
    		cause.printStackTrace();
    	} 
        ctx.close();
    }
    
    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        ctx.disconnect(promise); 
    }
}