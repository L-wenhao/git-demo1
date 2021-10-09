package com.csii.meter.console.service;

import com.csii.meter.console.dto.SysConfigDTO;
import com.csii.meter.console.vo.SysConfigVO;

import java.util.List;

/**
 * 系统配置Service接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface SysConfigService {
    /**
     * 查询系统配置
     *
     * @param id 系统配置ID
     * @return 系统配置
     */
    public SysConfigVO selectConfigById(String id);

    /**
     * 查询系统配置
     *
     * @param key 系统配置key
     * @return 系统配置
     */
    public String selectValueByKey(String key);

    /**
     * 查询系统配置列表
     *
     * @param dto 系统配置
     * @return 系统配置集合
     */
    public List<SysConfigVO> selectConfigList(SysConfigDTO dto);

    /**
     * 新增系统配置
     *
     * @param dto 系统配置
     * @return 结果
     */
    public int insertConfig(SysConfigDTO dto);

    /**
     * 修改系统配置
     *
     * @param dto 系统配置
     * @return 结果
     */
    public int updateConfig(SysConfigDTO dto);

    /**
     * 批量删除系统配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigByIds(String ids);

    /**
     * 删除系统配置信息
     *
     * @param id 系统配置ID
     * @return 结果
     */
    public int deleteConfigById(String id);
}
