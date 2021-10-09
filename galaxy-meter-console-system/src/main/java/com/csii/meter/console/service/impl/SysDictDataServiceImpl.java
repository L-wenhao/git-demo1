package com.csii.meter.console.service.impl;

import com.csii.meter.console.constants.Constants;
import com.csii.meter.console.converter.SysDictDataConverter;
import com.csii.meter.console.domain.SysDictData;
import com.csii.meter.console.domain.SysDictType;
import com.csii.meter.console.dto.SysDictDataDTO;
import com.csii.meter.console.exception.MeterConsoleException;
import com.csii.meter.console.mapper.SysDictDataMapper;
import com.csii.meter.console.service.SysDictDataService;
import com.csii.meter.console.service.SysDictTypeService;
import com.csii.meter.console.utils.ConvertUtils;
import com.csii.meter.console.vo.SysDictDataVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字典数据Service业务层处理
 *
 * @author liuya
 * @date 2020-09-20
 */
@Service
public class SysDictDataServiceImpl implements SysDictDataService {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Autowired
    private SysDictTypeService dictTypeService;

    /**
     * 查询字典数据
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictDataVO selectDictDataById(String id) {
        SysDictData dictData = dictDataMapper.selectDictDataById(id);
        return SysDictDataConverter.INSTANCE.domain2vo(dictData);
    }

    /**
     * 查询字典数据列表
     *
     * @param dto 字典数据
     * @return 字典数据
     */
    @Override
    public List<SysDictDataVO> selectDictDataList(SysDictDataDTO dto) {
        SysDictData dictData = SysDictDataConverter.INSTANCE.dto2domain(dto);
        return SysDictDataConverter.INSTANCE.domain2vo(dictDataMapper.selectDictDataList(dictData));
    }

    /**
     * 新增字典数据
     *
     * @param dto 字典数据
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictDataDTO dto) {
        //判断类型是否存在
        if (dictDataMapper.dataIsExist(dto.getTypeId(), dto.getDictCode()) > 0) {
            throw new MeterConsoleException("字典数据已存在");
        }
        SysDictData dictData = SysDictDataConverter.INSTANCE.dto2domain(dto);
        dictData.setSystemFlag(Integer.valueOf(Constants.NO));
        dictData.preInsert();
        return dictDataMapper.insertDictData(dictData);
    }

    /**
     * 修改字典数据
     *
     * @param dto 字典数据
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictDataDTO dto) {
        SysDictData dictData = dictDataMapper.selectDictDataById(dto.getId());
        if (Objects.isNull(dictData)) {
            throw new MeterConsoleException("字典数据不存在");
        }
        if (Objects.nonNull(dictData.getSystemFlag()) && dictData.getSystemFlag() == 1) {
            throw new MeterConsoleException("系统默认字典数据不允许修改");
        }
        //判断类型是否存在(除去本身)
        if (!dictData.getDictCode().equals(dto.getDictCode()) && dictDataMapper.dataIsExist(dto.getTypeId(), dto.getDictCode()) > 0) {
            throw new MeterConsoleException("字典数据已存在");
        }
        SysDictData newDictData = SysDictDataConverter.INSTANCE.dto2domain(dto);
        newDictData.preUpdate();
        return dictDataMapper.updateDictData(newDictData);
    }

    /**
     * 删除字典数据对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(String ids) {
        return dictDataMapper.deleteDictDataByIds(ConvertUtils.toStrArray(ids));
    }

    /**
     * 删除字典数据信息
     *
     * @param id 字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(String id) {
        return dictDataMapper.deleteDictDataById(id);
    }

    @Override
    public List<SysDictDataVO> selectDictDataListByTypes(String[] types) {
        //先查询字典类型数据
        List<SysDictType> dictTypeList = dictTypeService.selectListBytypes(Arrays.asList(types));
        if (CollectionUtils.isEmpty(dictTypeList)) {
            return new ArrayList<>();
        }
        Map<String, String> map = dictTypeList.stream().collect(Collectors.toMap(SysDictType::getId, SysDictType::getType, (k1, k2) -> k1));
        List<SysDictDataVO> dictDataVOList = SysDictDataConverter.INSTANCE.domain2vo(dictDataMapper.selectDataByType(map.keySet().toArray(new String[0])));
        for (SysDictDataVO dictDataVO : dictDataVOList) {
            dictDataVO.setType(map.get(dictDataVO.getTypeId()));
        }
        return dictDataVOList;
    }
}
