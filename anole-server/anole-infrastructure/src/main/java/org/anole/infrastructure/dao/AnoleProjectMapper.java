/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleProjectExample;
import org.anole.infrastructure.model.AnoleProject;
import org.apache.ibatis.annotations.Param;

public interface AnoleProjectMapper {
    int countByExample(AnoleProjectExample example);

    int deleteByExample(AnoleProjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnoleProject record);

    int insertSelective(AnoleProject record);

    List<AnoleProject> selectByExample(AnoleProjectExample example);

    AnoleProject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnoleProject record, @Param("example") AnoleProjectExample example);

    int updateByExample(@Param("record") AnoleProject record, @Param("example") AnoleProjectExample example);

    int updateByPrimaryKeySelective(AnoleProject record);

    int updateByPrimaryKey(AnoleProject record);
}