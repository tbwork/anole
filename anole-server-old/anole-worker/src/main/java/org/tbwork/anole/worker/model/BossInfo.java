package org.tbwork.anole.worker.model;

import lombok.Data;

@Data
public class BossInfo {

	private Long id;
	private String address;
	private Integer port;
	
	public BossInfo(Long id, String address, Integer port) {
		this.address = address;
		this.port = port;
	}
	
	/**
	 * E.g. 127.0.0.1:8080
	 */
	public BossInfo(Long id, String fullAddress) {
		String [] parts = fullAddress.split(":");
		this.address = parts[0];
		this.port = Integer.valueOf(parts[1]); 
	}
}
