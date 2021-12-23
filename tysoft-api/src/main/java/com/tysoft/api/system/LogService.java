package com.tysoft.api.system;

import com.tysoft.entity.system.LogModel;

/**
 * 日志服务
 * @author hxx
 * @Date 2021/12/22
 */
public interface LogService {

    /**
     * 更新或者保存日志
     * @param logModel
     * @return
     */
    LogModel saveOrUpDateLog(LogModel logModel);
}
