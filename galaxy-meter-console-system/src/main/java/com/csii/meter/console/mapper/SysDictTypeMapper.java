package com.csii.meter.console.mapper;

import com.csii.meter.console.domain.SysDictType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典类型Mapper接口
 *
 * @author liuya
 * @date 2020-09-20
 */
@Repository
public interface SysDictTypeMapper {
    /**
     * 查询字典类型
     *
     * @param id 字典类型ID
     * @return 字典类型
     */
    public SysDictType selectDictTypeById(String id);

    /**
     * 查询字典类型列表
     *
     * @param dictType 字典类型
     * @return 字典类型集合
     */
    public List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 新增字典类型
     *
     * @param dictType 字典类型
     * @return 结果
     */
    public int insertDictType(SysDictType dictType);

    /**
     * 修改字典类型
     *
     * @param dictType 字典类型
     * @return 结果
     */
    public int updateDictType(SysDictType dictType);

    /**
     * 删除字典类型
     *
     * @param id 字典类型ID
     * @return 结果
     */
    public int deleteDictTypeById(String id);

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDictTypeByIds(String[] ids);

    /**
     * 类型是否存在
     * @param type
     * @return
     */
    int typeIsExist(@Param("type") String type);

    /**
     * 根据类型type查找字典类型集合
     * @param types
     * @return
     */
    List<SysDictType> selectListBytypes(List<String> types);
}
