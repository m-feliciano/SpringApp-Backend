package com.feliciano.store.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
