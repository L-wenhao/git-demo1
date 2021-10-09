package com.csii.meter.console.converter;

import com.csii.meter.console.domain.SysOperLog;
import com.csii.meter.console.dto.SysOperLogDTO;
import com.csii.meter.console.request.SysOperLogQueryRequest;
import com.csii.meter.console.vo.SysOperLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysOperLogConverter {
    SysOperLogConverter INSTANCE = Mappers.getMapper(SysOperLogConverter.class);
    SysOperLogDTO request2dto(SysOperLogQueryRequest source);
    SysOperLogVO domain2vo(SysOperLog source);
    SysOperLog dto2domain(SysOperLogDTO source);
    List<SysOperLogVO> domain2vo(List<SysOperLog> source);

}
