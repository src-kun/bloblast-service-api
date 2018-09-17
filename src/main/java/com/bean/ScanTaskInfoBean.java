package com.bean;

public class ScanTaskInfoBean extends TemplateBean {

	private int folder_id;
	private String uuid;
	private long id;
	

	public ScanTaskInfoBean() {
		super();
	}

	public ScanTaskInfoBean(ScansBean tb) {
		super(tb.getSettings().getName(), tb.getSettings().getDescription(), tb.getSettings().getTargets());
		this.folder_id = tb.getSettings().getFolder_id();
		this.uuid = tb.getUuid();
		this.id = tb.getScanId();
	}
	
	public ScanTaskInfoBean(String name, String description, String targets) {
		super(name, description, targets);
	}

	public ScanTaskInfoBean(int folder_id, String uuid, long id) {
		super();
		this.folder_id = folder_id;
		this.uuid = uuid;
		this.id = id;
	}

	public int getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
