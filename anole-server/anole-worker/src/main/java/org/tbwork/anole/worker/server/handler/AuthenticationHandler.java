package org.tbwork.anole.worker.server.handler;

import org.tbwork.anole.server.basic.lccm.ILongConnectionClientManager;
import org.tbwork.anole.server.basic.model.requests.ValidateRequest;
import org.tbwork.anole.server.basic.server.util.ChannelHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.message.c_2_s.C2SMessage;
import org.tbwork.anole.common.message.s_2_c.MatchFailAndCloseMessage;
import org.tbwork.anole.worker.server.lccm.SubscriberClientManagerForWorker;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;

@Component("w4sAuthenticationHandler")
@Sharable
public class AuthenticationHandler extends SimpleChannelInboundHandler<C2SMessage> {

	@Autowired
	@Qualifier("subscriberClientManager")
	private SubscriberClientManagerForWorker scm4w;

	static final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class);
	
	public AuthenticationHandler(){
		super(false);
	}
	
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    } 

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, C2SMessage msg)
			throws Exception { 
		 if(logger.isDebugEnabled())
		     logger.debug("New message received (type = {}, clientId = {})", msg.getType(), msg.getClientId());
		 C2SMessage message = msg;
    	 MessageType msgType = message.getType(); 
    	 int clientId = message.getClientId();
    	 int token = message.getToken();
	     // message must need be validated (identification) before further process.
	     if(!scm4w.validate(new ValidateRequest(clientId, token)))
	     {
			   MatchFailAndCloseMessage mfcMsg = new MatchFailAndCloseMessage(); 
			   ChannelHelper.sendAndClose(ctx, mfcMsg);
	     }
	     else{
	    	 scm4w.fillInformation(clientId, (SocketChannel)ctx.channel());
	     }
		 // Passed the identification validation, go on processing logical staff.
         ctx.fireChannelRead(msg);  
	}
 
}