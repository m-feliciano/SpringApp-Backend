package com.feliciano.store.resources.mappers;

import com.feliciano.store.dto.ClientNewDTO;
import com.feliciano.store.resources.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CategoryMapper {

    Client toEntity(ClientNewDTO objDto);
}
