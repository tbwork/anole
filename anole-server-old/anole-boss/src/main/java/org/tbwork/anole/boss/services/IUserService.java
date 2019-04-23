package org.tbwork.anole.boss.services;

import org.tbwork.anole.common.enums.ClientType;

public interface IUserService {

	public boolean verify(String username, String password, ClientType clientType) ;
	
}
