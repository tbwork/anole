package org.tbwork.anole.server.protocal.common;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.tbwork.anole.common.enums.ClientType;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.message.c_2_s.C2SMessage;
import org.tbwork.anole.common.message.c_2_s.CommonAuthenticationMessage;
import org.tbwork.anole.common.message.s_2_c.AuthFailAndCloseMessage;
import org.tbwork.anole.common.message.s_2_c.AuthPassWithTokenMessage;
import org.tbwork.anole.common.message.s_2_c.MatchFailAndCloseMessage;
import org.tbwork.anole.server.lccm.impl.WorkerClientManagerForBoss;
import org.tbwork.anole.server.model.request.RegisterRequest;
import org.tbwork.anole.server.model.request.ValidateRequest;
import org.tbwork.anole.server.model.response.RegisterResult;
import org.tbwork.anole.server.services.IUserService;

public abstract  class AuthenticationHandler extends SimpleChannelInboundHandler<C2SMessage> {

	@Autowired
	@Qualifier("workerClientManager")
	private WorkerClientManagerForBoss wcm;

	
	@Autowired
	private IUserService userService;
	
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
		 if(MessageType.C2S_COMMON_AUTH.equals(msgType)) // register worker
		 {

		 }
		 else 
		 {	
			    // other message must need be validated (identification) before further process.
			    if(!wcm.validate(new ValidateRequest(message.getClientId(), message.getToken())))
			    {
				      MatchFailAndCloseMessage mfcMsg = new MatchFailAndCloseMessage();
				      ChannelHelper.sendAndClose(ctx, mfcMsg);
			    }
		 }
		
		 // Passed the identification validation, go on processing logical staff.
         ctx.fireChannelRead(msg);  
	} 

}