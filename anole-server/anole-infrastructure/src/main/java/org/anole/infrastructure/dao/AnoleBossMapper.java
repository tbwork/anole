/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleBossExample;
import org.anole.infrastructure.model.AnoleBoss;
import org.apache.ibatis.annotations.Param;

public interface AnoleBossMapper {
    int countByExample(AnoleBossExample example);

    int deleteByExample(AnoleBossExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnoleBoss record);

    int insertSelective(AnoleBoss record);

    List<AnoleBoss> selectByExample(AnoleBossExample example);

    AnoleBoss selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnoleBoss record, @Param("example") AnoleBossExample example);

    int updateByExample(@Param("record") AnoleBoss record, @Param("example") AnoleBossExample example);

    int updateByPrimaryKeySelective(AnoleBoss record);

    int updateByPrimaryKey(AnoleBoss record);
}