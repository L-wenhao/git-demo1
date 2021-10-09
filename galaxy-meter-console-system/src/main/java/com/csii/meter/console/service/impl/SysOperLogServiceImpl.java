package com.csii.meter.console.service.impl;

import com.csii.meter.console.converter.SysOperLogConverter;
import com.csii.meter.console.domain.SysOperLog;
import com.csii.meter.console.dto.SysOperLogDTO;
import com.csii.meter.console.mapper.SysOperLogMapper;
import com.csii.meter.console.service.SysOperLogService;
import com.csii.meter.console.vo.SysOperLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志Service业务层处理
 *
 * @author liuya
 * @date 2020-09-20
 */
@Service
public class SysOperLogServiceImpl implements SysOperLogService {
    @Autowired
    private SysOperLogMapper operLogMapper;

    /**
     * 查询操作日志
     *
     * @param id 操作日志ID
     * @return 操作日志
     */
    @Override
    public SysOperLogVO selectOperLogById(String id) {
        return SysOperLogConverter.INSTANCE.domain2vo(operLogMapper.selectOperLogById(id));
    }

    /**
     * 查询操作日志列表
     *
     * @param dto 操作日志
     * @return 操作日志
     */
    @Override
    public List<SysOperLogVO> selectOperLogList(SysOperLogDTO dto) {
        SysOperLog operLog = SysOperLogConverter.INSTANCE.dto2domain(dto);
        List<SysOperLog> operLogs = operLogMapper.selectOperLogList(operLog);
        return SysOperLogConverter.INSTANCE.domain2vo(operLogs);
    }
}
