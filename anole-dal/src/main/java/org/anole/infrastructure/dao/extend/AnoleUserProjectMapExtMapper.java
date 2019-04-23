package org.anole.infrastructure.dao.extend;

import org.anole.infrastructure.dao.AnoleUserProjectMapMapper;
import org.anole.infrastructure.example.AnoleUserProjectMapExample;
import org.anole.infrastructure.model.AnoleUserProjectMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: anole-server
 * @description: Ext for AnoleUserProjectMapMapper
 * @author: tommy.tb
 * @create: 2019-03-31 16:45
 **/
@Component
public class AnoleUserProjectMapExtMapper {

    @Autowired
    private AnoleUserProjectMapMapper anoleUserProjectMapMapper;

    public Integer getRoleByProjectKeyEnv(String user, String project, String env){

        AnoleUserProjectMapExample anoleUserProjectMapExample = new AnoleUserProjectMapExample();
        anoleUserProjectMapExample.createCriteria().andProjectEqualTo(project)
                .andUsernameEqualTo(user).andEnvEqualTo(env);

        List<AnoleUserProjectMap> dbResult = anoleUserProjectMapMapper.selectByExample(anoleUserProjectMapExample);
        if (dbResult != null && dbResult.size() > 0){
            return dbResult.get(0).getRole().intValue();
        }
        return 0;
    }


}
