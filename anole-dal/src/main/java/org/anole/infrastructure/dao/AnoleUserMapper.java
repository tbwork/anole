/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleUserExample;
import org.anole.infrastructure.model.AnoleUser;
import org.apache.ibatis.annotations.Param;

public interface AnoleUserMapper {
    int countByExample(AnoleUserExample example);

    int deleteByExample(AnoleUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnoleUser record);

    int insertSelective(AnoleUser record);

    List<AnoleUser> selectByExample(AnoleUserExample example);

    AnoleUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnoleUser record, @Param("example") AnoleUserExample example);

    int updateByExample(@Param("record") AnoleUser record, @Param("example") AnoleUserExample example);

    int updateByPrimaryKeySelective(AnoleUser record);

    int updateByPrimaryKey(AnoleUser record);
}