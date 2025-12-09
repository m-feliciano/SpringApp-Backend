package com.feliciano.store.services.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String string) {
		super(string);
	}

	public DataIntegrityException(String string, Throwable cause) {
		super(string, cause);
	}
}
