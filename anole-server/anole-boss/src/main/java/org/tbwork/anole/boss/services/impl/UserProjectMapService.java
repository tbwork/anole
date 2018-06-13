package org.tbwork.anole.boss.services.impl;

import java.util.List;

import org.anole.infrastructure.dao.AnoleUserProjectMapMapper;
import org.anole.infrastructure.example.AnoleUserProjectMapExample;
import org.anole.infrastructure.model.AnoleUserProjectMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.tbwork.anole.boss.services.IUserProjectMapService;

@Service
public class UserProjectMapService implements IUserProjectMapService {
 
	@Autowired
	private AnoleUserProjectMapMapper anoleUserProjectMapMapper;
	
	@Override
	public Integer selectRole(String operator, String project, String env) {
		AnoleUserProjectMapExample aupme = new AnoleUserProjectMapExample();
		aupme.createCriteria().andUsernameEqualTo(operator).andProjectEqualTo(project).andEnvEqualTo(env);
		List<AnoleUserProjectMap> maps = anoleUserProjectMapMapper.selectByExample(aupme);
		if(maps != null && !maps.isEmpty()) {
			maps.get(0).getRole().intValue();
		}
		return null;
	}
}
