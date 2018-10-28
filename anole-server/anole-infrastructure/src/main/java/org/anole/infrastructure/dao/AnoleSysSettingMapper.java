package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleSysSettingExample;
import org.anole.infrastructure.model.AnoleSysSetting;
import org.apache.ibatis.annotations.Param;

public interface AnoleSysSettingMapper {
    long countByExample(AnoleSysSettingExample example);

    int deleteByExample(AnoleSysSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnoleSysSetting record);

    int insertSelective(AnoleSysSetting record);

    List<AnoleSysSetting> selectByExample(AnoleSysSettingExample example);

    AnoleSysSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnoleSysSetting record, @Param("example") AnoleSysSettingExample example);

    int updateByExample(@Param("record") AnoleSysSetting record, @Param("example") AnoleSysSettingExample example);

    int updateByPrimaryKeySelective(AnoleSysSetting record);

    int updateByPrimaryKey(AnoleSysSetting record);
}