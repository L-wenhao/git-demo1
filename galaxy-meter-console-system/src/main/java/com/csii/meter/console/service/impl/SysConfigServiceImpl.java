package com.csii.meter.console.service.impl;

import com.csii.meter.console.config.MeterConsoleConfig;
import com.csii.meter.console.converter.SysConfigConverter;
import com.csii.meter.console.domain.SysConfig;
import com.csii.meter.console.dto.SysConfigDTO;
import com.csii.meter.console.mapper.SysConfigMapper;
import com.csii.meter.console.service.SysConfigService;
import com.csii.meter.console.utils.ConvertUtils;
import com.csii.meter.console.vo.SysConfigVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 系统配置Service业务层处理
 *
 * @author liuya
 * @date 2020-09-20
 */
@Service
public class SysConfigServiceImpl extends MeterConsoleConfig implements SysConfigService, InitializingBean {
    @Autowired
    private SysConfigMapper systemConfigMapper;
    /**
     * 查询系统配置
     *
     * @param id 系统配置ID
     * @return 系统配置
     */
    @Override
    public SysConfigVO selectConfigById(String id) {
        return SysConfigConverter.INSTANCE.domain2vo(systemConfigMapper.selectConfigById(id));
    }

    /**
     * 查询系统配置
     *
     * @param key 系统配置key
     * @return 系统配置
     */
    @Override
    public String selectValueByKey(String key) {
        //优先查缓存
        Object value = MeterConsoleConfig.get(key, null);
        if(Objects.isNull(value)){
            value = systemConfigMapper.selectValueByKey(key);
            MeterConsoleConfig.put(key, value.toString());
        }
        return null == value ? null : value.toString();
    }

    /**
     * 查询系统配置列表
     *
     * @param dto 系统配置
     * @return 系统配置
     */
    @Override
    public List<SysConfigVO> selectConfigList(SysConfigDTO dto) {
        SysConfig config = SysConfigConverter.INSTANCE.dto2domain(dto);
        return SysConfigConverter.INSTANCE.domain2vo(systemConfigMapper.selectConfigList(config));
    }

    /**
     * 新增系统配置
     *
     * @param dto 系统配置
     * @return 结果
     */
    @Override
    public int insertConfig(SysConfigDTO dto) {
        SysConfig config = SysConfigConverter.INSTANCE.dto2domain(dto);
        config.preInsert();
        int count = systemConfigMapper.insertConfig(config);
        MeterConsoleConfig.put(config.getConfigKey(), config.getConfigValue());
        return count;
    }

    /**
     * 修改系统配置
     *
     * @param dto 系统配置
     * @return 结果
     */
    @Override
    public int updateConfig(SysConfigDTO dto) {
        SysConfig config = SysConfigConverter.INSTANCE.dto2domain(dto);
        config.preUpdate();
        int count = systemConfigMapper.updateConfig(config);
        MeterConsoleConfig.put(config.getConfigKey(), config.getConfigValue());
        return count;
    }

    /**
     * 删除系统配置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteConfigByIds(String ids) {
        int count = systemConfigMapper.deleteConfigByIds(ConvertUtils.toStrArray(ids));
        afterPropertiesSet();
        return count;
    }

    /**
     * 删除系统配置信息
     *
     * @param id 系统配置ID
     * @return 结果
     */
    @Override
    public int deleteConfigById(String id) {
        SysConfig systemConfig = systemConfigMapper.selectConfigById(id);
        if(Objects.isNull(systemConfig)){
            return 0;
        }
        int count = systemConfigMapper.deleteConfigById(id);
        MeterConsoleConfig.remove(systemConfig.getConfigKey());
        return count;
    }

    /**
     * 初始化缓存
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() {
        List<SysConfig> list = systemConfigMapper.selectConfigList(null);
        list.forEach(systemConfig -> {
            MeterConsoleConfig.put(systemConfig.getConfigKey(), systemConfig.getConfigValue());
        });
    }
}
