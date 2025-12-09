package com.feliciano.store.dto;

import com.feliciano.store.resources.domain.Address;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * DTO for {@link com.feliciano.store.resources.domain.Order}
 */
public record OrderDto(Integer id,
                       ClientDTO client,
                       PaymentDto payment,
                       Date date,
                       Address deliveryAddress,
                       Set<OrderItemDto> itens) implements Serializable {
}