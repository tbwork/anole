/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleUserProjectMapExample;
import org.anole.infrastructure.model.AnoleUserProjectMap;
import org.apache.ibatis.annotations.Param;

public interface AnoleUserProjectMapMapper {
    int countByExample(AnoleUserProjectMapExample example);

    int deleteByExample(AnoleUserProjectMapExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnoleUserProjectMap record);

    int insertSelective(AnoleUserProjectMap record);

    List<AnoleUserProjectMap> selectByExample(AnoleUserProjectMapExample example);

    AnoleUserProjectMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnoleUserProjectMap record, @Param("example") AnoleUserProjectMapExample example);

    int updateByExample(@Param("record") AnoleUserProjectMap record, @Param("example") AnoleUserProjectMapExample example);

    int updateByPrimaryKeySelective(AnoleUserProjectMap record);

    int updateByPrimaryKey(AnoleUserProjectMap record);
}