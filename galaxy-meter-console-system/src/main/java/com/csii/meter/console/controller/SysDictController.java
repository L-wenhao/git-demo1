package com.csii.meter.console.controller;

import com.csii.meter.console.annotation.Add;
import com.csii.meter.console.annotation.Log;
import com.csii.meter.console.annotation.Update;
import com.csii.meter.console.converter.SysDictDataConverter;
import com.csii.meter.console.converter.SysDictTypeConverter;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.dto.SysDictDataDTO;
import com.csii.meter.console.dto.SysDictTypeDTO;
import com.csii.meter.console.enums.OperatorType;
import com.csii.meter.console.request.SysDictDataQueryRequest;
import com.csii.meter.console.request.SysDictDataRequest;
import com.csii.meter.console.request.SysDictTypeQueryRequest;
import com.csii.meter.console.request.SysDictTypeRequest;
import com.csii.meter.console.service.SysDictDataService;
import com.csii.meter.console.service.SysDictTypeService;
import com.csii.meter.console.utils.ConvertUtils;
import com.csii.meter.console.utils.ParamsUtils;
import com.csii.meter.console.vo.SysDictDataVO;
import com.csii.meter.console.vo.SysDictTypeVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 字典类型Controller
 *
 * @author liuya
 * @date 2020-09-20
 */
@Slf4j
@RestController
@RequestMapping("/system/dict")
@Api(tags = "系统管理-字典管理")
public class SysDictController extends BaseController {
    @Autowired
    private SysDictTypeService dictTypeService;
    @Autowired
    private SysDictDataService dictDataService;

    /**
     * 查询字典类型列表
     */
    @RequiresPermissions("admin:dictType:pageList")
    @PostMapping("/pageTypeList")
    @ApiOperation(value = "字典类型分页查询", notes = "字典类型分页查询")
    public Result<PageInfo<SysDictTypeVO>> pageTypeList(@RequestBody SysDictTypeQueryRequest request) {
        startPage(request);
        SysDictTypeDTO dictType = SysDictTypeConverter.INSTANCE.request2dto(request);
        List<SysDictTypeVO> list = dictTypeService.selectDictTypeList(dictType);
        return Result.success(getPageInfo(list));
    }

    /**
     * 查询字典数据列表
     */
    @RequiresPermissions("admin:dictData:pageList")
    @PostMapping("/pageDataList")
    @ApiOperation(value = "字典数据分页查询", notes = "字典数据分页查询")
    public Result<PageInfo<SysDictDataVO>> list(@RequestBody SysDictDataQueryRequest request) {
        ParamsUtils.checkParamsIsNull(request.getTypeId(), "字典类型id不能为空");
        startPage(request);
        SysDictDataDTO dictData = SysDictDataConverter.INSTANCE.request2dto(request);
        List<SysDictDataVO> list = dictDataService.selectDictDataList(dictData);
        return Result.success(getPageInfo(list));
    }

    /**
     * 新增保存字典类型
     */
    @RequiresPermissions("admin:dictType:add")
    @Log(businessType = "字典类型", operatorType = OperatorType.INSERT)
    @PostMapping("/addType")
    @ApiOperation(value = "新增字典类型", notes = "新增字典类型")
    public Result<Void> addType(@RequestBody @Validated(Add.class) SysDictTypeRequest request) {
        ParamsUtils.checkParamsIsNull(request.getType(), "字典类型不能为空");
        ParamsUtils.checkParamsIsNull(request.getName(), "字典类型名称不能为空");
        SysDictTypeDTO dictType = SysDictTypeConverter.INSTANCE.request2dto(request);
        dictTypeService.insertDictType(dictType);
        return Result.success();
    }

    /**
     * 新增保存字典数据
     */
    @Log(businessType = "字典数据", operatorType = OperatorType.INSERT)
    @PostMapping("/addData")
    @ApiOperation(value = "添加字典数据", notes = "添加字典数据")
    public Result<Void> addData(@RequestBody @Validated(Add.class) SysDictDataRequest request) {
        ParamsUtils.checkParamsIsNull(request.getTypeId(), "字典类型ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getDictCode(), "字典代码不能为空");
        ParamsUtils.checkParamsIsNull(request.getDictName(), "字典名称不能为空");
        SysDictDataDTO dictData = SysDictDataConverter.INSTANCE.request2dto(request);
        dictDataService.insertDictData(dictData);
        return Result.success();
    }

    /**
     * 修改保存字典类型
     */
    @RequiresPermissions("admin:dictType:update")
    @Log(businessType = "字典类型", operatorType = OperatorType.UPDATE)
    @PostMapping("/updateType")
    @ApiOperation(value = "修改字典类型", notes = "修改字典类型")
    @ResponseBody
    public Result<Void> updateType(@RequestBody @Validated(Update.class) SysDictTypeRequest request) {
        ParamsUtils.checkParamsIsNull(request.getId(), "ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getType(), "字典类型不能为空");
        ParamsUtils.checkParamsIsNull(request.getName(), "字典名称不能为空");
        SysDictTypeDTO dictType = SysDictTypeConverter.INSTANCE.request2dto(request);
        dictTypeService.updateDictType(dictType);
        return Result.success();
    }

    /**
     * 修改保存字典数据
     */
    @Log(businessType = "字典数据", operatorType = OperatorType.UPDATE)
    @PostMapping("/updateData")
    @ApiOperation(value = "修改字典数据", notes = "修改字典数据")
    public Result<Void> updateData(@RequestBody @Validated(Update.class) SysDictDataRequest request) {
        ParamsUtils.checkParamsIsNull(request.getId(), "ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getDictCode(), "字典代码不能为空");
        ParamsUtils.checkParamsIsNull(request.getDictName(), "字典名称不能为空");
        SysDictDataDTO dictData = SysDictDataConverter.INSTANCE.request2dto(request);
        dictDataService.updateDictData(dictData);
        return Result.success();
    }

    /**
     * 删除字典类型
     */
    @RequiresPermissions("admin:dictType:remove")
    @Log(businessType = "字典类型", operatorType = OperatorType.DELETE)
    @PostMapping("/removeType")
    @ApiOperation(value = "删除字典类型", notes = "多个ID用‘,’分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id集合", required = true, dataType = "String", paramType = "query", example = "1,2,3"),
    })
    public Result<Void> removeType(@RequestParam("ids") String ids) {
        ParamsUtils.checkParamsIsNull(ids, "ID不能为空");
        return toAjax(dictTypeService.deleteDictTypeByIds(ids));
    }

    /**
     * 删除字典数据
     */
    @Log(businessType = "字典数据", operatorType = OperatorType.DELETE)
    @PostMapping("/removeData")
    @ApiOperation(value = "删除字典数据", notes = "多个ID用‘,’分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "字典数据id", required = true, dataType = "String", paramType = "query", example = "1,2,3"),
    })
    public Result<Void> removeData(@RequestParam("ids") String ids) {
        ParamsUtils.checkParamsIsNull(ids, "ID不能为空");
        return toAjax(dictDataService.deleteDictDataByIds(ids));
    }


    @PostMapping("/listDictByTypeId")
    @ApiOperation(value = "根据字典类型集合查对应的字典", notes = "多个类型用‘,’分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "types", value = "类型集合", required = true, dataType = "String", paramType = "query", example = "1,2,3"),
    })
    public Result<Map<String, List<SysDictDataVO>>> listDictByType(@RequestParam("types") String types) {
        //先根据类型查找字典类型id: 获取到字典类型id集合;
        //再根据字典类型id查询获取字典数据，再设置字典类型到字典数据，
        //最后组装Map<String, List<dataDictVO>> map

        ParamsUtils.checkParamsIsNull(types, "字典类型集合不能为空");
        List<SysDictDataVO> list = dictDataService.selectDictDataListByTypes(ConvertUtils.toStrArray(types));
        if (CollectionUtils.isEmpty(list)) {
            return Result.success(new HashMap<>(0));
        }
        return Result.success(list.stream().collect(Collectors.groupingBy(SysDictDataVO::getType)));
    }

    @PostMapping("/selecDictByTypeId")
    @ApiOperation(value = "根据字典类型查询字典", notes = "根据字典类型查询字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "指令类型", required = true, dataType = "String", paramType = "query", example = "指令类型"),
            @ApiImplicitParam(name = "dictCode", value = "字典code", dataType = "String", paramType = "query", example = "字典code"),
            @ApiImplicitParam(name = "dictName", value = "字典name", dataType = "String", paramType = "query", example = "字典name"),
    })
    public Result<List<SysDictDataVO>> selectDictByType(@RequestParam("typeId") String typeId, @RequestParam(value = "dictCode", required = false) String dictCode,
                                                     @RequestParam(value = "dictName", required = false) String dictName) {
        ParamsUtils.checkParamsIsNull(typeId, "字典类型不能为空!");
        if (StringUtils.isNotEmpty(dictName)) {
            dictName = URLDecoder.decode(dictName);
        }
        SysDictDataDTO dto = SysDictDataDTO.builder().dictCode(dictCode).dictName(dictName).typeId(typeId).build();
        //注：页面相关字段采用的是模糊查询，一下api最终调用的sql需要修改和分页的不一样
        List<SysDictDataVO> list = dictDataService.selectDictDataList(dto);
        return Result.success(list);
    }

}
