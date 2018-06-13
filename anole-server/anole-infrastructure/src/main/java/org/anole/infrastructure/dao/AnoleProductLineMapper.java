/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleProductLineExample;
import org.anole.infrastructure.model.AnoleProductLine;
import org.apache.ibatis.annotations.Param;

public interface AnoleProductLineMapper {
    int countByExample(AnoleProductLineExample example);

    int deleteByExample(AnoleProductLineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnoleProductLine record);

    int insertSelective(AnoleProductLine record);

    List<AnoleProductLine> selectByExample(AnoleProductLineExample example);

    AnoleProductLine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnoleProductLine record, @Param("example") AnoleProductLineExample example);

    int updateByExample(@Param("record") AnoleProductLine record, @Param("example") AnoleProductLineExample example);

    int updateByPrimaryKeySelective(AnoleProductLine record);

    int updateByPrimaryKey(AnoleProductLine record);
}