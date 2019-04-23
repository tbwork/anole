package org.tbwork.anole.server.basic.enums;

public enum ServerStatus {

	STOPED(0),
	RUNNING(1),
	DISCONNECTED(99);
	
	
	private byte status;
	
	private ServerStatus(int status) {
		this.status = (byte) status;
	}
	
	public byte getStatus() {
		return status;
	}
	
}
