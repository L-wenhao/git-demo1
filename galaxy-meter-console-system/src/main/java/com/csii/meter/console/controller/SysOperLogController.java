package com.csii.meter.console.controller;

import com.csii.meter.console.converter.SysOperLogConverter;
import com.csii.meter.console.datamodel.Result;
import com.csii.meter.console.dto.SysOperLogDTO;
import com.csii.meter.console.request.SysOperLogQueryRequest;
import com.csii.meter.console.service.SysOperLogService;
import com.csii.meter.console.vo.SysOperLogVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志Controller
 *
 * @author liuya
 * @date 2020-09-20
 */
@RestController
@RequestMapping("/system/log")
@Api(tags = "系统管理-操作日志查询")
public class SysOperLogController extends BaseController {
    @Autowired
    private SysOperLogService operLogService;

    /**
     * 查询操作日志列表
     */
    @RequiresPermissions("admin:log:list")
    @PostMapping("/list")
    @ApiOperation(value = "分页查询日志列表", notes = "分页查询日志列表")
    public Result<PageInfo<SysOperLogVO>> list(@RequestBody SysOperLogQueryRequest request) {
        startPage(request);
        SysOperLogDTO dto = SysOperLogConverter.INSTANCE.request2dto(request);
        List<SysOperLogVO> list = operLogService.selectOperLogList(dto);
        return Result.success(getPageInfo(list));
    }

    @PostMapping("/detail")
    @ApiOperation(value = "查询日志详情", notes = "查询日志详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "日志ID", required = true, dataType = "String", paramType = "query", example = "1234"),
    })
    public Result<SysOperLogVO> detail(@RequestParam String id) {
        return Result.success(operLogService.selectOperLogById(id));
    }

}
