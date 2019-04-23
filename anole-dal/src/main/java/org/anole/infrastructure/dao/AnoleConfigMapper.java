/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleConfigExample;
import org.anole.infrastructure.model.AnoleConfig;
import org.apache.ibatis.annotations.Param;

public interface AnoleConfigMapper {
    int countByExample(AnoleConfigExample example);

    int deleteByExample(AnoleConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnoleConfig record);

    int insertSelective(AnoleConfig record);

    List<AnoleConfig> selectByExample(AnoleConfigExample example);

    AnoleConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnoleConfig record, @Param("example") AnoleConfigExample example);

    int updateByExample(@Param("record") AnoleConfig record, @Param("example") AnoleConfigExample example);

    int updateByPrimaryKeySelective(AnoleConfig record);

    int updateByPrimaryKey(AnoleConfig record);
}