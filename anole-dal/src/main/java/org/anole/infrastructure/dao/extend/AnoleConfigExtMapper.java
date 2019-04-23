package org.anole.infrastructure.dao.extend;

import org.anole.infrastructure.dao.AnoleConfigMapper;
import org.anole.infrastructure.example.AnoleConfigExample;
import org.anole.infrastructure.model.AnoleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: anole-server
 * @description: Ext for AnoleConfigMapper
 * @author: tommy.tb
 * @create: 2019-03-31 15:43
 **/
@Component
public class AnoleConfigExtMapper {

    @Autowired
    private AnoleConfigMapper anoleConfigMapper;


    public void deleteConfigByKey(String key){
        AnoleConfigExample anoleConfigExample = new AnoleConfigExample();
        anoleConfigExample.createCriteria().andKeyEqualTo(key);
        anoleConfigMapper.deleteByExample(anoleConfigExample);
    }

    public AnoleConfig getConfigByKey(String key){
        AnoleConfigExample anoleConfigExample = new AnoleConfigExample();
        anoleConfigExample.createCriteria().andKeyEqualTo(key);
        List<AnoleConfig> results = anoleConfigMapper.selectByExample(anoleConfigExample);
        if(results != null && results.size() > 0){
            return results.get(0);
        }
        return null;
    }

}
