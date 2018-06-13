package org.tbwork.anole.worker.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbwork.anole.common.message.Message;
import org.tbwork.anole.common.message.MessageType;
import org.tbwork.anole.common.message.c_2_s.CommonAuthenticationMessage;
import org.tbwork.anole.common.message.s_2_c.AuthPassWithTokenMessage;
import org.tbwork.anole.loader.context.Anole;
import org.tbwork.anole.worker.client.IAnoleWorkerClient;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil; 

@Sharable
public class AuthenticationHandler extends  SimpleChannelInboundHandler<Message>  {

	static final Logger logger = LoggerFactory.getLogger(AuthenticationHandler.class); 
	  
	private IAnoleWorkerClient workerClient;
	
	public AuthenticationHandler(IAnoleWorkerClient workerClient){
		super(false);
		this.workerClient = workerClient;
	} 
 
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Message msg)
			throws Exception { 
		if(logger.isDebugEnabled())
		     logger.debug("New message received (type = {})", msg.getType());
		MessageType msgType=  msg.getType(); 
        switch (msgType){
	        case S2C_AUTH_FIRST:{ //Please login first. 
	        	ctx.writeAndFlush( getAuthInfo() );
		      	ReferenceCountUtil.release(msg);
	        } break;
	        case S2C_AUTH_FAIL_CLOSE:{
		      	logger.error("[:(] Username or password is invalid, please check them and try again."); 
		      	ctx.close(); // close the connection and wait for next trial
		      	ReferenceCountUtil.release(msg);
			} break;
		 	case S2C_AUTH_PASS:{ 
		 		logger.debug ("[:)] Login successfully."); 
		 		int clientId = ((AuthPassWithTokenMessage)msg).getClientId();
		 		int token = ((AuthPassWithTokenMessage)msg).getToken();
		 		workerClient.saveToken(clientId, token);
		 		ReferenceCountUtil.release(msg);
		 	} break;
		 	case S2C_MATCH_FAIL:{
		 		logger.error("[:(] Connection is disabled and cleared by the server, automatically connect immediately.");
		 		workerClient.reconnect();
		 	} break;
	        default:{ 
	        } break;
        }
        // pass to next
        ctx.fireChannelRead(msg); 
		
	}
	
	private CommonAuthenticationMessage getAuthInfo(){
		CommonAuthenticationMessage authBody=new CommonAuthenticationMessage();
    	authBody.setUsername(Anole.getProperty("worker.username", "tommy.tang"));
    	authBody.setPassword(Anole.getProperty("worker.password", "123456")); 
    	return authBody;
	}
    
}
