package com.feliciano.store.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.feliciano.store.resources.domain.OrderItem}
 */
public record OrderItemDto(Double discount, Integer quantity, Double price) implements Serializable {
}