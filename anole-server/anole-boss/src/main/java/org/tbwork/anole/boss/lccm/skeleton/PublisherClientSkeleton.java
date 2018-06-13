package org.tbwork.anole.boss.lccm.skeleton;

import org.tbwork.anole.server.basic.lccm.skeleton.LongConnectionClientSkeleton;
import io.netty.channel.socket.SocketChannel; 
 
public class PublisherClientSkeleton extends LongConnectionClientSkeleton{ 
	public PublisherClientSkeleton(int token, SocketChannel socketChannel){
		super(token, socketChannel);
	}
	 
}
