package com.bean;

public class ScanResponseBean {

	private long id;
	private String templateKey;

	public ScanResponseBean() {
		super();
	}

	public ScanResponseBean(long id, String templateKey) {
		super();
		this.id = id;
		this.templateKey = templateKey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

}
