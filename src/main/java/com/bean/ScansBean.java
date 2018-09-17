package com.bean;

public class ScansBean {

	private long scanId;
	private String uuid;
	public int status;
	public SettingsBean settings;
	public ScanResponseBean[] scanResponse;
	public TemplateBean[] templates;

	public ScansBean() {
		super();
	}

	public ScansBean(long scanId, String uuid, SettingsBean settings, ScanResponseBean[] scanResponse,
			TemplateBean[] templates) {
		super();
		this.scanId = scanId;
		this.uuid = uuid;
		this.settings = settings;
		this.scanResponse = scanResponse;
		this.templates = templates;
	}

	public long getScanId() {
		return scanId;
	}

	public void setScanId(long scanId) {
		this.scanId = scanId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public SettingsBean getSettings() {
		return settings;
	}

	public void setSettings(SettingsBean settings) {
		this.settings = settings;
	}

	public ScanResponseBean[] getScanResponse() {
		return scanResponse;
	}

	public void setScanResponse(ScanResponseBean[] scanResponse) {
		this.scanResponse = scanResponse;
	}

	public TemplateBean[] getTemplates() {
		return templates;
	}

	public void setTemplates(TemplateBean[] templates) {
		this.templates = templates;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
