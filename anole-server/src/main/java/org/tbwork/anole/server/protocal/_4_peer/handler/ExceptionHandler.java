package org.tbwork.anole.server.protocal._4_peer.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * Deal with all exceptions.
 * @author tommy.tang
 */
@Component("b4wExceptionHandler")
@Sharable
public class ExceptionHandler extends ChannelHandlerAdapter {

	static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class); 
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { 
    	if(cause instanceof IOException) {
    		logger.warn("The connection with worker (address = {}) disconnected initiatively! ", ctx.channel().remoteAddress());
    	}
    	else {
    		cause.printStackTrace();
    	} 
        ctx.close();
    }
}