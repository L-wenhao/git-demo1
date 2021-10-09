package com.csii.meter.console.mapper;

import com.csii.meter.console.domain.SysOperLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作日志Mapper接口
 *
 * @author liuya
 * @date 2020-09-20
 */
@Repository
public interface SysOperLogMapper {
    /**
     * 查询操作日志
     *
     * @param id 操作日志ID
     * @return 操作日志
     */
    public SysOperLog selectOperLogById(String id);

    /**
     * 查询操作日志列表
     *
     * @param operLog 操作日志
     * @return 操作日志集合
     */
    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

}
