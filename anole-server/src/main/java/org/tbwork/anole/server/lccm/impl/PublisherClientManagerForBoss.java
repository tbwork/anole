package org.tbwork.anole.server.lccm.impl;

import io.netty.channel.socket.SocketChannel;

import java.util.Date;
import java.util.Set;

import org.anole.infrastructure.dao.AnoleConfigItemMapper;
import org.anole.infrastructure.dao.AnoleConfigMapper;
import org.anole.infrastructure.dao.AnoleUserProjectMapMapper;
import org.anole.infrastructure.model.AnoleConfig;
import org.anole.infrastructure.model.AnoleConfigItem;
import org.anole.infrastructure.model.AnoleEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.loader.types.ConfigType;
import org.tbwork.anole.server.basic.lccm.impl.LongConnectionClientManager;
import org.tbwork.anole.server.basic.lccm.skeleton.LongConnectionClientSkeleton;
import org.tbwork.anole.server.basic.model.requests.RegisterRequest;
import org.tbwork.anole.server.basic.repository.IEnvironmentRepository;
import org.tbwork.anole.server.basic.service.IConfigQueryService;
import org.tbwork.anole.common.enums.Role;
import org.tbwork.anole.common.model.ConfigModifyResultDTO;
import org.tbwork.anole.common.model.ConfigModifyDTO; 
import org.tbwork.anole.server.lccm.skeleton.PublisherClientSkeleton;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions; 

/**
 * Publisher manager used by boss server.
 * @author tommy.tang
 */
@Service("publisherClientManager")
public class PublisherClientManagerForBoss  extends LongConnectionClientManager {

	private static final Logger logger = LoggerFactory.getLogger(PublisherClientManagerForBoss.class); 
   
	public static enum Operation{
		VIEW(1),   // never used in publisher.
		MODIFY(2), // Modifying a configuration needs at least admin right
		CREATE(2), // Creating a configuration needs at least admin right
		DELETE(3); // Deleting a configuration needs at least owner right
		private int level;
		private Operation(int level){
			this.level = level;
		}
	}
	
	@Override
	protected LongConnectionClientSkeleton createClient(int token, RegisterRequest registerRequest) { 
		return new PublisherClientSkeleton(token, registerRequest.getSocketChannel());
	} 
	
}






