package org.tbwork.anole.server.basic.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.anole.infrastructure.dao.AnoleEnvironmentMapper;
import org.anole.infrastructure.example.AnoleEnvironmentExample;
import org.anole.infrastructure.model.AnoleEnvironment;
import org.tbwork.anole.server.basic.repository.IEnvironmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("environmentRepository")
public class EnvironmentRepository implements IEnvironmentRepository{

	private List<AnoleEnvironment> envs; 
	
	@Autowired
	private AnoleEnvironmentMapper anoleEnvironmentMapper;
	
	@Override
	public List<AnoleEnvironment> getEnviroments() {
		if(envs != null){
			return envs;
		} 
		List<AnoleEnvironment> result = getAllEnvs();
		return result == null ? new ArrayList<AnoleEnvironment>() : result;
	}

	private List<AnoleEnvironment> getAllEnvs(){
		AnoleEnvironmentExample aee = new AnoleEnvironmentExample();
		return anoleEnvironmentMapper.selectByExample(aee);
	}
}
