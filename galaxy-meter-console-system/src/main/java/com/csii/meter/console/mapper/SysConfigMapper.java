package com.csii.meter.console.mapper;

import com.csii.meter.console.domain.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统配置Mapper接口
 * @author liuya
 * @date 2020-09-20
 */
@Repository
public interface SysConfigMapper {
    /**
     * 查询系统配置
     *
     * @param id 系统配置ID
     * @return 系统配置
     */
    public SysConfig selectConfigById(String id);

    /**
     * 查询系统配置列表
     *
     * @param config 系统配置
     * @return 系统配置集合
     */
    public List<SysConfig> selectConfigList(SysConfig config);

    /**
     * 查询系统配置
     *
     * @param key 系统配置key
     * @return 系统配置
     */
    public String selectValueByKey(String key);

    /**
     * 新增系统配置
     *
     * @param config 系统配置
     * @return 结果
     */
    public int insertConfig(SysConfig config);

    /**
     * 修改系统配置
     *
     * @param config 系统配置
     * @return 结果
     */
    public int updateConfig(SysConfig config);

    /**
     * 删除系统配置
     *
     * @param id 系统配置ID
     * @return 结果
     */
    public int deleteConfigById(String id);

    /**
     * 批量删除系统配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteConfigByIds(String[] ids);
}
