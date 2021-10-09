package com.csii.meter.console.converter;

import com.csii.meter.console.domain.SysMenu;
import com.csii.meter.console.dto.SysMenuDTO;
import com.csii.meter.console.request.SysMenuRequest;
import com.csii.meter.console.vo.SysMenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysMenuConverter {
    SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);
    SysMenuDTO request2dto(SysMenuRequest source);
    SysMenuVO domain2vo(SysMenu source);
    SysMenu dto2domain(SysMenuDTO source);
    List<SysMenuVO> domain2vo(List<SysMenu> source);


}
