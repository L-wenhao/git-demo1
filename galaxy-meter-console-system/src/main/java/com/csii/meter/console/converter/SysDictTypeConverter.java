package com.csii.meter.console.converter;

import com.csii.meter.console.domain.SysDictType;
import com.csii.meter.console.dto.SysDictTypeDTO;
import com.csii.meter.console.request.SysDictTypeQueryRequest;
import com.csii.meter.console.request.SysDictTypeRequest;
import com.csii.meter.console.vo.SysDictTypeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictTypeConverter {
    SysDictTypeConverter INSTANCE = Mappers.getMapper(SysDictTypeConverter.class);
    SysDictTypeDTO request2dto(SysDictTypeQueryRequest source);
    SysDictTypeDTO request2dto(SysDictTypeRequest source);
    SysDictType dto2domain(SysDictTypeDTO source);
    SysDictTypeVO domain2vo(SysDictType source);
    List<SysDictTypeVO> domain2vo(List<SysDictType> source);

}
