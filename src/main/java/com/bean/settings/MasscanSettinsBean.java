package com.bean.settings;

import java.util.ArrayList;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.bean.TemplateBean;

public class MasscanSettinsBean extends TemplateBean {

	@JSONField(serialize = false)
	public String key = "masscan";
	@JSONField(serialize = false)
	public String uri = "/scan/masscan";
	@JSONField(serialize = false)
	public String method = "POST";

	private String ports;
	private Integer rate;
	private ArrayList<String> options;

	public MasscanSettinsBean() {
		super();
	}

	public MasscanSettinsBean(String name, String description, String targets) {
		super(name, description, targets);
	}

	public MasscanSettinsBean(String ports, Integer rate, ArrayList<String> options) {
		super();
		this.ports = ports;
		this.rate = rate;
		this.options = options;
	}

	@SuppressWarnings("unchecked")
	public MasscanSettinsBean(Map<String, Map<String, Object>> templates, TemplateBean sc) {
		super(sc.getName(), sc.getDescription(), sc.getTargets());
		this.rate = (Integer) templates.get(key).get("rate");
		this.ports = (String) templates.get(key).get("ports");
		this.options = (ArrayList<String>) templates.get(key).get("options");
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
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
