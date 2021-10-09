package com.csii.meter.console.converter;

import com.csii.meter.console.domain.SysDictData;
import com.csii.meter.console.dto.SysDictDataDTO;
import com.csii.meter.console.request.SysDictDataQueryRequest;
import com.csii.meter.console.request.SysDictDataRequest;
import com.csii.meter.console.vo.SysDictDataVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysDictDataConverter {
    SysDictDataConverter INSTANCE = Mappers.getMapper(SysDictDataConverter.class);
    SysDictDataDTO request2dto(SysDictDataQueryRequest source);
    SysDictDataDTO request2dto(SysDictDataRequest source);
    SysDictData dto2domain(SysDictDataDTO source);
    SysDictDataVO domain2vo(SysDictData source);
    List<SysDictDataVO> domain2vo(List<SysDictData> source);


}
