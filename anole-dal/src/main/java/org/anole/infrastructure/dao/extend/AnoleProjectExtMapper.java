package org.anole.infrastructure.dao.extend;

import org.anole.infrastructure.dao.AnoleProjectMapper;
import org.anole.infrastructure.example.AnoleProjectExample;
import org.anole.infrastructure.model.AnoleProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: anole-server
 * @description: Ext for AnoleProjectMapper
 * @author: tommy.tb
 * @create: 2019-03-31 15:38
 **/
@Component
public class AnoleProjectExtMapper {


    @Autowired
    private AnoleProjectMapper anoleProjectMapper;

    public AnoleProject getProjectByName(String project){
        AnoleProjectExample anoleProjectExample = new AnoleProjectExample();
        anoleProjectExample.createCriteria().andNameEqualTo(project);
        List<AnoleProject> projects = anoleProjectMapper.selectByExample(anoleProjectExample);
        if(projects!=null && projects.size() > 0){
            return projects.get(0);
        }
        return null;
    }


    public Integer countProjectByName(String project){
        AnoleProjectExample anoleProjectExample = new AnoleProjectExample();
        anoleProjectExample.createCriteria().andNameEqualTo(project);
        return anoleProjectMapper.countByExample(anoleProjectExample);
    }

}
