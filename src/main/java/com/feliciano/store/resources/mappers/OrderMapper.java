package com.feliciano.store.resources.mappers;

import com.feliciano.store.dto.OrderDto;
import com.feliciano.store.resources.domain.Order;
import com.feliciano.store.resources.domain.enums.PaymentStatus;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderMapper {
    OrderDto toResponse(Order obj);

    default Integer map(PaymentStatus value) {
        return value != null ? value.getCod() : null;
    }
}
