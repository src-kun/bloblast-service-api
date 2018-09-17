package com.bean;

public class SettingsBean extends TemplateBean {

	public boolean launch_now;
	private int folder_id;
	private String[] emails;

	public SettingsBean() {
		super();
	}

	public SettingsBean(boolean launch_now, int folder_id, String[] emails) {
		super();
		this.launch_now = launch_now;
		this.folder_id = folder_id;
		this.emails = emails;
	}

	public boolean isLaunch_now() {
		return launch_now;
	}

	public void setLaunch_now(boolean launch_now) {
		this.launch_now = launch_now;
	}

	public int getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(int folder_id) {
		this.folder_id = folder_id;
	}

	public String[] getEmails() {
		return emails;
	}

	public void setEmails(String[] emails) {
		this.emails = emails;
	}

}
