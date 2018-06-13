package org.tbwork.anole.server.basic.lccm.skeleton;
 
import org.tbwork.anole.server.basic.StaticConfiguration;

import io.netty.channel.socket.SocketChannel;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * . Skeleton means the fake instance
 * of client existing in server application.
 * @author tommy.tang
 */
@Data
public class LongConnectionClientSkeleton {

	/**
	 * The unique identity code of one client 
	 * which is assigned by the client manager.
	 */
	int token;
	/**
	 * The socket channel of connection between
	 * the server and the client.
	 */
	SocketChannel socketChannel; 
	
	@Getter(AccessLevel.NONE)@Setter(AccessLevel.NONE)  
	int ping_promise_count; 
	
	public LongConnectionClientSkeleton(){}
	
	public LongConnectionClientSkeleton(int token, SocketChannel socketChannel){
		this.token = token;
		this.socketChannel = socketChannel;
		this.ping_promise_count = 0; 
	}
	
	public int addPingPromise()
	{
		return ++ ping_promise_count;
	}
	
	public int ackPingPromise()
	{
		return ping_promise_count >0 ? -- ping_promise_count : 0;
	}
	 
	public boolean maxPromiseCount()
	{
		return ping_promise_count >= StaticConfiguration.MAX_PROMISE_COUNT ;
	} 
	
	
}
