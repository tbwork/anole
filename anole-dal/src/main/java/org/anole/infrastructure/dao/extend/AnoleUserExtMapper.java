package org.anole.infrastructure.dao.extend;

import org.anole.infrastructure.dao.AnoleUserMapper;
import org.anole.infrastructure.example.AnoleUserExample;
import org.anole.infrastructure.model.AnoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: anole-server
 * @description: Extend for AnoleUserMapper
 * @author: tommy.tb
 * @create: 2019-03-31 17:05
 **/
@Component
public class AnoleUserExtMapper {

    @Autowired
    private AnoleUserMapper anoleUserMapper;


    public AnoleUser getByUsername(String username){
        AnoleUserExample anoleUserExample = new AnoleUserExample();
        anoleUserExample.createCriteria().andUsernameEqualTo(username);
        List<AnoleUser> dbResults = anoleUserMapper.selectByExample(anoleUserExample);
        if(dbResults!=null && dbResults.size() > 0 ){
            return dbResults.get(0);
        }
        return null;
    }
}
