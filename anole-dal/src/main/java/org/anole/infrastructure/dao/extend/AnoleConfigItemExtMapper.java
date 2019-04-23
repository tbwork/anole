package org.anole.infrastructure.dao.extend;

import org.anole.infrastructure.dao.AnoleConfigItemMapper;
import org.anole.infrastructure.example.AnoleConfigItemExample;
import org.anole.infrastructure.model.AnoleConfigItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: anole-server
 * @description: Ext for AnoleConfigItemMapper
 * @author: tommy.tb
 * @create: 2019-03-31 16:39
 **/
@Component
public class AnoleConfigItemExtMapper {

    @Autowired
    private AnoleConfigItemMapper anoleConfigItemMapper;


    public List<AnoleConfigItem> getAnoleConfigItemsByKey(String key){
        AnoleConfigItemExample anoleConfigItemExample = new AnoleConfigItemExample();
        anoleConfigItemExample.createCriteria().andKeyEqualTo(key);
        return anoleConfigItemMapper.selectByExample(anoleConfigItemExample);
    }

}
