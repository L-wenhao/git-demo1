package com.csii.meter.console.service.impl;

import com.csii.meter.console.constants.Constants;
import com.csii.meter.console.converter.SysDictTypeConverter;
import com.csii.meter.console.domain.SysDictType;
import com.csii.meter.console.dto.SysDictTypeDTO;
import com.csii.meter.console.exception.MeterConsoleException;
import com.csii.meter.console.mapper.SysDictTypeMapper;
import com.csii.meter.console.service.SysDictTypeService;
import com.csii.meter.console.utils.ConvertUtils;
import com.csii.meter.console.vo.SysDictTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 字典类型Service业务层处理
 *
 * @author liuya
 * @date 2020-09-20
 */
@Slf4j
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    /**
     * 查询字典类型
     *
     * @param id 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictTypeVO selectDictTypeById(String id) {
        SysDictType dictType = dictTypeMapper.selectDictTypeById(id);
        return SysDictTypeConverter.INSTANCE.domain2vo(dictType);
    }

    /**
     * 查询字典类型列表
     *
     * @param dto 字典类型
     * @return 字典类型
     */
    @Override
    public List<SysDictTypeVO> selectDictTypeList(SysDictTypeDTO dto) {
        SysDictType dictType = SysDictTypeConverter.INSTANCE.dto2domain(dto);
        return SysDictTypeConverter.INSTANCE.domain2vo(dictTypeMapper.selectDictTypeList(dictType));
    }

    /**
     * 新增字典类型
     *
     * @param dto 字典类型
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictTypeDTO dto) {
        //判断类型是否存在
        if(dictTypeMapper.typeIsExist(dto.getType()) > 0){
            throw new MeterConsoleException("字典类型已存在");
        }
        SysDictType dictType = SysDictTypeConverter.INSTANCE.dto2domain(dto);
        dictType.setSystemFlag(Integer.valueOf(Constants.NO));
        dictType.preInsert();
        return dictTypeMapper.insertDictType(dictType);
    }

    /**
     * 修改字典类型
     *
     * @param dto 字典类型
     * @return 结果
     */
    @Override
    public int updateDictType(SysDictTypeDTO dto) {
        SysDictType dictType = dictTypeMapper.selectDictTypeById(dto.getId());
        if(Objects.isNull(dictType)){
            throw new MeterConsoleException("字典类型不存在");
        }
        if(Objects.nonNull(dictType.getSystemFlag()) && dictType.getSystemFlag() == 1){
            throw new MeterConsoleException("系统默认字典类型不允许修改");
        }
        //判断类型是否存在
        if(!dto.getType().equals(dictType.getType()) && dictTypeMapper.typeIsExist(dto.getType()) > 0){
            throw new MeterConsoleException("字典类型已存在");
        }
        SysDictType newDictType = SysDictTypeConverter.INSTANCE.dto2domain(dto);
        newDictType.preUpdate();
        return dictTypeMapper.updateDictType(newDictType);
    }

    /**
     * 删除字典类型对象
     *
     * @param id 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeByIds(String id) {
        SysDictType dictType = dictTypeMapper.selectDictTypeById(id);
        if(Objects.isNull(dictType)){
            throw new MeterConsoleException("字典类型不存在");
        }
        if(dictType.getSystemFlag() == 1){
            throw new MeterConsoleException("系统默认字典类型不允许修改");
        }
        return dictTypeMapper.deleteDictTypeByIds(ConvertUtils.toStrArray(id));
    }

    /**
     * 删除字典类型信息
     *
     * @param id 字典类型ID
     * @return 结果
     */
    @Override
    public int deleteDictTypeById(String id) {
        return dictTypeMapper.deleteDictTypeById(id);
    }

    /**
     * 根据类型查询
     * @param types
     * @return
     */
    @Override
    public List<SysDictType> selectListBytypes(List<String> types) {
        return dictTypeMapper.selectListBytypes(types);
    }

}
