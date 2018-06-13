package org.tbwork.anole.server.basic.service.impl;
 
import org.tbwork.anole.server.basic.service.IUserService;
import org.tbwork.anole.server.basic.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tbwork.anole.common.enums.ClientType;

import java.util.List;

import org.anole.infrastructure.dao.*;
import org.anole.infrastructure.example.AnoleUserExample;
import org.anole.infrastructure.model.AnoleUser;

@Service("userService")
public class UserService implements IUserService{

	@Autowired
	private AnoleUserMapper anoleUserDao;
	
	@Override
	public boolean verify(String username, String password, ClientType clientType) {
		AnoleUser anoleUser = getUserByName(username);
		if(anoleUser != null){
			String pwdMd5 = anoleUser.getPassword();
			String inputPwdMd5 = StringUtil.md5(password); 
			return pwdMd5.equals(inputPwdMd5);
		}
		return false;
	}

	
	private AnoleUser getUserByName(String username) {
		AnoleUserExample aue = new AnoleUserExample();
		aue.createCriteria().andUsernameEqualTo(username);
		List<AnoleUser> results = anoleUserDao.selectByExample(aue);
		if(results == null || results.isEmpty())
			return null;
		return results.get(0);
	}
}
