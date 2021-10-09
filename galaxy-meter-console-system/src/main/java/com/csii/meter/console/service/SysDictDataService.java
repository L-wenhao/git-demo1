package com.csii.meter.console.service;

import com.csii.meter.console.dto.SysDictDataDTO;
import com.csii.meter.console.vo.SysDictDataVO;

import java.util.List;

/**
 * 字典数据Service接口
 *
 * @author liuya
 * @date 2020-09-20
 */
public interface SysDictDataService {
    /**
     * 查询字典数据
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    public SysDictDataVO selectDictDataById(String id);

    /**
     * 查询字典数据列表
     *
     * @param dto 字典数据
     * @return 字典数据集合
     */
    public List<SysDictDataVO> selectDictDataList(SysDictDataDTO dto);

    /**
     * 新增字典数据
     *
     * @param dto 字典数据
     * @return 结果
     */
    public int insertDictData(SysDictDataDTO dto);

    /**
     * 修改字典数据
     *
     * @param dto 字典数据
     * @return 结果
     */
    public int updateDictData(SysDictDataDTO dto);

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDictDataByIds(String ids);

    /**
     * 删除字典数据信息
     *
     * @param id 字典数据ID
     * @return 结果
     */
    public int deleteDictDataById(String id);

    /**
     * 根据多个类型查询字典数据列表
     *
     * @param types 类型集合
     * @return 字典数据集合
     */
    public List<SysDictDataVO> selectDictDataListByTypes(String[] types);

}
