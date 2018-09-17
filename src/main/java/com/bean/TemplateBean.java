package com.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class TemplateBean {

	@JSONField(serialize = false)
	public String key;
	@JSONField(serialize = false)
	public String uri;
	@JSONField(serialize = false)
	public String method;
	@JSONField(serialize = false)

	private String name;
	private String description;
	private String targets;

	public TemplateBean() {

	}

	public TemplateBean(String name, String description, String targets) {
		super();
		this.name = name;
		this.description = description;
		this.targets = targets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTargets() {
		return targets;
	}

	public void setTargets(String targets) {
		this.targets = targets;
	}

	public String getUri() {
		return uri;
	}

	public String getMethod() {
		return method;
	}

	public String getKey() {
		return key;
	}

}
