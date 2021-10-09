package com.csii.meter.console.service;

import com.csii.meter.console.dto.SysOperLogDTO;
import com.csii.meter.console.vo.SysOperLogVO;

import java.util.List;

/**
 * 操作日志Service接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface SysOperLogService {
    /**
     * 查询操作日志
     *
     * @param id 操作日志ID
     * @return 操作日志
     */
    SysOperLogVO selectOperLogById(String id);

    /**
     * 查询操作日志列表
     *
     * @param dto 操作日志
     * @return 操作日志集合
     */
    List<SysOperLogVO> selectOperLogList(SysOperLogDTO dto);

}
