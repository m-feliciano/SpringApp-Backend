package com.feliciano.store.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String string) {
		super(string);
	}

	public ObjectNotFoundException(String string, Throwable cause) {
        super("Object not found! Id: " + string, cause);
    }

    public ObjectNotFoundException(String string, String type) {
        super("Object not found! Id: " + string + ", Type: " + type);
    }
}
