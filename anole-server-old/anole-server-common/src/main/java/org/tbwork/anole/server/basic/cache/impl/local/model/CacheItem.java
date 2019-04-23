package org.tbwork.anole.server.basic.cache.impl.local.model;

import lombok.Data;

@Data
public class CacheItem<T>{

	private T item;
	private long expireTime;
	private long startTime;
	
}
