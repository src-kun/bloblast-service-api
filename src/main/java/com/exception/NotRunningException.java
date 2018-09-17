package com.exception;

public class NotRunningException  extends RuntimeException {
	private static final long serialVersionUID = -5519743897907627214L;

	public NotRunningException() {
		super("Not running.");
		this.printStackTrace();
	}
}