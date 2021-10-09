package com.csii.meter.console.converter;

import com.csii.meter.console.domain.SysConfig;
import com.csii.meter.console.dto.SysConfigDTO;
import com.csii.meter.console.request.SysConfigQueryRequest;
import com.csii.meter.console.request.SysConfigRequest;
import com.csii.meter.console.vo.SysConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysConfigConverter {
    SysConfigConverter INSTANCE = Mappers.getMapper(SysConfigConverter.class);
    SysConfigDTO request2dto(SysConfigQueryRequest source);
    SysConfigDTO request2dto(SysConfigRequest source);
    SysConfig dto2domain(SysConfigDTO source);
    SysConfigVO domain2vo(SysConfig source);
    List<SysConfigVO> domain2vo(List<SysConfig> source);
}
