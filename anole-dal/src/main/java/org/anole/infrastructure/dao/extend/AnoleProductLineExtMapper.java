package org.anole.infrastructure.dao.extend;

import org.anole.infrastructure.dao.AnoleProductLineMapper;
import org.anole.infrastructure.example.AnoleProductLineExample;
import org.anole.infrastructure.model.AnoleProductLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: anole-server
 * @description: Ext for AnoleProductLineMapper
 * @author: tommy.tb
 * @create: 2019-03-31 16:55
 **/
@Component
public class AnoleProductLineExtMapper {


    @Autowired
    private AnoleProductLineMapper anoleProductLineMapper;

    public AnoleProductLine getProductLineByName(String name){
        AnoleProductLineExample anoleProductLineExample = new AnoleProductLineExample();
        anoleProductLineExample.createCriteria().andNameEqualTo(name);
        List<AnoleProductLine> dbResults = anoleProductLineMapper.selectByExample(anoleProductLineExample);
        if(dbResults!=null && dbResults.size()>0){
          return  dbResults.get(0);
        }
        return null;
    }


}
