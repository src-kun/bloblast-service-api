package com.bean.exception;

public class GeneralErrorBean {

	private String uri;
	private String message;
	private long timestamp;

	public GeneralErrorBean() {
		super();
	}

	
	public GeneralErrorBean(String uri, String message) {
		super();
		this.uri = uri;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}


	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
