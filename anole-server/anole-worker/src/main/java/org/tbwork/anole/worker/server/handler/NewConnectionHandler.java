package org.tbwork.anole.worker.server.handler;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@Component("w4sNewConnectionHandler")
@Sharable
public class NewConnectionHandler extends ChannelHandlerAdapter {

	static final Logger logger = LoggerFactory.getLogger(NewConnectionHandler.class);
	
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        if(logger.isDebugEnabled())
	        	logger.debug("[:)] A new connection is established, remote address : '{}'", ctx.channel().remoteAddress());
        ctx.fireChannelActive();
    }
    
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}