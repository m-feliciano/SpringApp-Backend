package com.feliciano.store.services.exceptions;

public class FileException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FileException(String string) {
		super(string);
	}

	public FileException(String string, Throwable cause) {
		super(string, cause);
	}
}
