package com.bean.settings;

import java.util.ArrayList;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.bean.TemplateBean;

public class NmapSettingsBean extends TemplateBean {

	@JSONField(serialize = false)
	public String key = "nmap";
	@JSONField(serialize = false)
	public String uri = "/scan/nmap";
	@JSONField(serialize = false)
	public String method = "POST";

	private String ports;
	private ArrayList<String> options;

	public NmapSettingsBean() {
		super();
	}

	public NmapSettingsBean(String name, String description, String targets) {
		super(name, description, targets);
	}

	public NmapSettingsBean(String ports, ArrayList<String> options) {
		super();
		this.ports = ports;
		this.options = options;
	}

	@SuppressWarnings("unchecked")
	public NmapSettingsBean(Map<String, Map<String, Object>> templates, TemplateBean sc) {
		super(sc.getName(), sc.getDescription(), sc.getTargets());
		this.ports = (String) templates.get(key).get("ports");
		this.options = (ArrayList<String>) templates.get(key).get("options");
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
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
