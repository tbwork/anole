/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package org.anole.infrastructure.dao.manual;

import org.anole.infrastructure.model.custom.AnoleConfigDetail;

import java.util.List;

public interface AnoleConfigComplexMapper {
    
    List<AnoleConfigDetail> selectConfigsByProjectAndEnv(String project, String env);
}