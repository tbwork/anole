package org.tbwork.anole.server.lccm.skeleton;

import org.springframework.context.annotation.ImportResource;
import org.tbwork.anole.server.basic.lccm.skeleton.LongConnectionClientSkeleton;
import io.netty.channel.socket.SocketChannel; 
 
@ImportResource("xxx.xml")
public class PublisherClientSkeleton extends LongConnectionClientSkeleton{ 
	public PublisherClientSkeleton(int token, SocketChannel socketChannel){
		super(token, socketChannel);
	}
	 
}
