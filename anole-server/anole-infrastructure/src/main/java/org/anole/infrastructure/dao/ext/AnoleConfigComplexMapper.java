/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao.ext;

import java.util.List;
import org.anole.infrastructure.example.AnoleConfigExample;
import org.anole.infrastructure.model.AnoleConfig;
import org.anole.infrastructure.model.custom.AnoleConfigDetail;
import org.apache.ibatis.annotations.Param;

public interface AnoleConfigComplexMapper {
    
    List<AnoleConfigDetail> selectConfigsByProjectAndEnv(String project, String env);
}