/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleEnvironmentExample;
import org.anole.infrastructure.model.AnoleEnvironment;
import org.apache.ibatis.annotations.Param;

public interface AnoleEnvironmentMapper {
    int countByExample(AnoleEnvironmentExample example);

    int deleteByExample(AnoleEnvironmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnoleEnvironment record);

    int insertSelective(AnoleEnvironment record);

    List<AnoleEnvironment> selectByExample(AnoleEnvironmentExample example);

    AnoleEnvironment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnoleEnvironment record, @Param("example") AnoleEnvironmentExample example);

    int updateByExample(@Param("record") AnoleEnvironment record, @Param("example") AnoleEnvironmentExample example);

    int updateByPrimaryKeySelective(AnoleEnvironment record);

    int updateByPrimaryKey(AnoleEnvironment record);
}