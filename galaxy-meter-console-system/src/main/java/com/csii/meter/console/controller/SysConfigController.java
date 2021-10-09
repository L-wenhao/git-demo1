package com.csii.meter.console.controller;

import com.csii.meter.console.annotation.Log;
import com.csii.meter.console.converter.SysConfigConverter;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.dto.SysConfigDTO;
import com.csii.meter.console.enums.OperatorType;
import com.csii.meter.console.request.SysConfigQueryRequest;
import com.csii.meter.console.request.SysConfigRequest;
import com.csii.meter.console.service.SysConfigService;
import com.csii.meter.console.utils.ParamsUtils;
import com.csii.meter.console.vo.SysConfigVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统配置Controller
 *
 * @author liuya
 * @date 2020-09-20
 */
@RestController
@RequestMapping("/system/config")
@Api(tags = "系统管理-系统参数")
public class SysConfigController extends BaseController {

    @Autowired
    private SysConfigService systemConfigService;

    /**
     * 查询系统配置列表
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询系统配置列表", notes = "分页查询系统配置列表")
    public Result<PageInfo<SysConfigVO>> list(@RequestBody SysConfigQueryRequest request) {
        startPage(request);
        SysConfigDTO dto = SysConfigConverter.INSTANCE.request2dto(request);
        List<SysConfigVO> list = systemConfigService.selectConfigList(dto);
        return Result.success(getPageInfo(list));
    }

    /**
     * 新增保存系统配置
     */
    @Log(businessType = "系统配置", operatorType = OperatorType.INSERT)
    @ApiOperation(value = "新增系统配置", notes = "新增系统配置")
    @PostMapping("/add")
    public Result<Void> addSave(@RequestBody SysConfigRequest request) {
        ParamsUtils.checkParamsIsNull(request.getName(), "参数名称不能为空");
        ParamsUtils.checkParamsIsNull(request.getConfigKey(), "参数键名不能为空");
        ParamsUtils.checkParamsIsNull(request.getConfigValue(), "参数键值不能为空");
        SysConfigDTO dto = SysConfigConverter.INSTANCE.request2dto(request);
        return toAjax(systemConfigService.insertConfig(dto));
    }

    /**
     * 修改保存系统配置
     */
    @Log(businessType = "系统配置", operatorType = OperatorType.UPDATE)
    @PostMapping("/edit")
    @ApiOperation(value = "编辑系统配置", notes = "编辑系统配置")
    public Result<Void> editSave(@RequestBody SysConfigRequest request) {
        ParamsUtils.checkParamsIsNull(request.getId(), "ID不能为空");
        ParamsUtils.checkParamsIsNull(request.getName(), "参数名称不能为空");
        ParamsUtils.checkParamsIsNull(request.getConfigKey(), "参数键名不能为空");
        ParamsUtils.checkParamsIsNull(request.getConfigValue(), "参数键值不能为空");
        SysConfigDTO dto = SysConfigConverter.INSTANCE.request2dto(request);
        return toAjax(systemConfigService.updateConfig(dto));
    }

    /**
     * 删除系统配置
     */
    @Log(businessType = "系统配置", operatorType = OperatorType.DELETE)
    @PostMapping("/remove")
    @ApiOperation(value = "根据id删除系统配置", notes = "多个ID用‘,’分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id集合", required = true, dataType = "String", paramType = "query", example = "1,2,3"),
    })
    public Result<Void> remove(@RequestParam(value = "ids")String ids) {
        ParamsUtils.checkParamsIsNull(ids, "ID不能为空");
        return toAjax(systemConfigService.deleteConfigByIds(ids));
    }

}
