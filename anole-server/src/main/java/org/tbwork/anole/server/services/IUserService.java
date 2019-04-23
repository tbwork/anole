package org.tbwork.anole.server.services;

import org.tbwork.anole.common.enums.ClientType;

public interface IUserService {

	public boolean verify(String username, String password, ClientType clientType) ;
	
}
