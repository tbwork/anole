/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleConfigItemExample;
import org.anole.infrastructure.model.AnoleConfigItem;
import org.apache.ibatis.annotations.Param;

public interface AnoleConfigItemMapper {
    int countByExample(AnoleConfigItemExample example);

    int deleteByExample(AnoleConfigItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnoleConfigItem record);

    int insertSelective(AnoleConfigItem record);

    List<AnoleConfigItem> selectByExampleWithBLOBs(AnoleConfigItemExample example);

    List<AnoleConfigItem> selectByExample(AnoleConfigItemExample example);

    AnoleConfigItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnoleConfigItem record, @Param("example") AnoleConfigItemExample example);

    int updateByExampleWithBLOBs(@Param("record") AnoleConfigItem record, @Param("example") AnoleConfigItemExample example);

    int updateByExample(@Param("record") AnoleConfigItem record, @Param("example") AnoleConfigItemExample example);

    int updateByPrimaryKeySelective(AnoleConfigItem record);

    int updateByPrimaryKeyWithBLOBs(AnoleConfigItem record);

    int updateByPrimaryKey(AnoleConfigItem record);
}