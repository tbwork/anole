package org.tbwork.anole.server.basic.cache.impl.local.job;

import org.tbwork.anole.server.basic.StaticConfiguration;
import org.tbwork.anole.server.basic.cache.impl.local.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component; 
@Component("localCacheRecycle") 
public class LocalCacheSpaceRecycle {

	@Autowired
	private LocalCache localCache;
	
	@Scheduled(fixedDelay = StaticConfiguration.CACHE_RECYCLE_INTERVAL)
	public void run(){
		localCache.recycle();
	}
}
