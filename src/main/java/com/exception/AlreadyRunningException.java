package com.exception;

public class AlreadyRunningException extends RuntimeException {
	private static final long serialVersionUID = -5519743897907627214L;

	public AlreadyRunningException() {
		super("Already running.");
		this.printStackTrace();
	}
}
