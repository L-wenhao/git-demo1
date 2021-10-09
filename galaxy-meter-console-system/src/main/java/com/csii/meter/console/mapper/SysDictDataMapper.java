package com.csii.meter.console.mapper;

import com.csii.meter.console.domain.SysDictData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典数据Mapper接口
 *
 * @author liuya
 * @date 2020-09-20
 */
@Repository
public interface SysDictDataMapper {
    /**
     * 查询字典数据
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    public SysDictData selectDictDataById(String id);

    /**
     * 查询字典数据列表
     *
     * @param dictData 字典数据
     * @return 字典数据集合
     */
    public List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 新增字典数据
     *
     * @param dictData 字典数据
     * @return 结果
     */
    public int insertDictData(SysDictData dictData);

    /**
     * 修改字典数据
     *
     * @param dictData 字典数据
     * @return 结果
     */
    public int updateDictData(SysDictData dictData);

    /**
     * 删除字典数据
     *
     * @param id 字典数据ID
     * @return 结果
     */
    public int deleteDictDataById(String id);

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDictDataByIds(String[] ids);

    List<SysDictData> selectDataByType(String[] types);

    /**
     * 数据是否存在
     */
    int dataIsExist(@Param("typeId") String type, @Param("dictCode") String dictCode);

}
