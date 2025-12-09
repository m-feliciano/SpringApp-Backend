package com.feliciano.store.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.feliciano.store.resources.domain.Payment}
 */
public record PaymentDto(String cod, Integer status) implements Serializable {
}