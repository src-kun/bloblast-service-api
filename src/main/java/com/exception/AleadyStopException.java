package com.exception;

public class AleadyStopException extends RuntimeException {
	private static final long serialVersionUID = -5519743897907627214L;

	public AleadyStopException() {
		super("Already stop.");
		this.printStackTrace();
	}
}