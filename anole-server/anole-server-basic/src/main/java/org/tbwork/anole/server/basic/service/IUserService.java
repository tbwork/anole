package org.tbwork.anole.server.basic.service;

import org.tbwork.anole.common.enums.ClientType;

public interface IUserService {

	public boolean verify(String username, String password, ClientType clientType) ;
	
}
