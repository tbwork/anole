/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao;

import java.util.List;
import org.anole.infrastructure.example.AnoleWorkerExample;
import org.anole.infrastructure.model.AnoleWorker;
import org.apache.ibatis.annotations.Param;

public interface AnoleWorkerMapper {
    int countByExample(AnoleWorkerExample example);

    int deleteByExample(AnoleWorkerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnoleWorker record);

    int insertSelective(AnoleWorker record);

    List<AnoleWorker> selectByExample(AnoleWorkerExample example);

    AnoleWorker selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AnoleWorker record, @Param("example") AnoleWorkerExample example);

    int updateByExample(@Param("record") AnoleWorker record, @Param("example") AnoleWorkerExample example);

    int updateByPrimaryKeySelective(AnoleWorker record);

    int updateByPrimaryKey(AnoleWorker record);
}