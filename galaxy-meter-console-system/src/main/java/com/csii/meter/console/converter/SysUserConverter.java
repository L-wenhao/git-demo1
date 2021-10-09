package com.csii.meter.console.converter;

import com.csii.meter.console.domain.SysUser;
import com.csii.meter.console.dto.SysUserDTO;
import com.csii.meter.console.request.SysUserQueryRequest;
import com.csii.meter.console.request.SysUserRequest;
import com.csii.meter.console.vo.SysUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserConverter {
    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);
    SysUserDTO request2dto(SysUserQueryRequest source);
    SysUserDTO request2dto(SysUserRequest source);
    SysUser dto2domain(SysUserDTO source);
    SysUserVO domain2vo(SysUser source);
    List<SysUserVO> domain2vo(List<SysUser> source);


}
