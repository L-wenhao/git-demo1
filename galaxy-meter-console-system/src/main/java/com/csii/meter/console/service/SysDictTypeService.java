package com.csii.meter.console.service;

import com.csii.meter.console.domain.SysDictType;
import com.csii.meter.console.dto.SysDictTypeDTO;
import com.csii.meter.console.vo.SysDictTypeVO;

import java.util.List;

/**
 * 字典类型Service接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface SysDictTypeService {
    /**
     * 查询字典类型
     *
     * @param id 字典类型ID
     * @return 字典类型
     */
    SysDictTypeVO selectDictTypeById(String id);

    /**
     * 查询字典类型列表
     *
     * @param dto 字典类型
     * @return 字典类型集合
     */
    List<SysDictTypeVO> selectDictTypeList(SysDictTypeDTO dto);

    /**
     * 新增字典类型
     *
     * @param dto 字典类型
     * @return 结果
     */
    int insertDictType(SysDictTypeDTO dto);

    /**
     * 修改字典类型
     *
     * @param dto 字典类型
     * @return 结果
     */
    int updateDictType(SysDictTypeDTO dto);

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteDictTypeByIds(String ids);

    /**
     * 删除字典类型信息
     *
     * @param id 字典类型ID
     * @return 结果
     */
    int deleteDictTypeById(String id);

    List<SysDictType> selectListBytypes(List<String> types);
}
