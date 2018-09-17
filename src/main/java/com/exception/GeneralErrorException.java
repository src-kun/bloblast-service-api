package com.exception;

public class GeneralErrorException extends RuntimeException {
	private static final long serialVersionUID = -5519743897907627214L;

	public GeneralErrorException(String message) {
		super(message);
		this.printStackTrace();
	}
}
