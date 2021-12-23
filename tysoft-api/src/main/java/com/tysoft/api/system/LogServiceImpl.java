package com.tysoft.api.system;

import com.tysoft.entity.system.LogModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日志实现类
 * @author hxx
 * @Date 2021/12/22
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Override
    public LogModel saveOrUpDateLog(LogModel logModel) {
        System.out.println("成功进入日志保存");
        return null;
    }
}
