package com.csii.meter.console.converter;

import com.csii.meter.console.domain.SysRole;
import com.csii.meter.console.dto.SysRoleDTO;
import com.csii.meter.console.request.SysRoleQueryRequest;
import com.csii.meter.console.request.SysRoleRequest;
import com.csii.meter.console.vo.SysRoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConverter {
    SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);
    SysRoleDTO request2dto(SysRoleRequest source);
    SysRoleDTO request2dto(SysRoleQueryRequest source);
    SysRole dto2domain(SysRoleDTO source);
    SysRoleVO domain2vo(SysRole source);
    List<SysRoleVO> domain2vo(List<SysRole> source);

}
