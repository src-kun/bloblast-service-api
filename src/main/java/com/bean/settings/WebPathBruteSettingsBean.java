package com.bean.settings;

import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.bean.TemplateBean;

public class WebPathBruteSettingsBean extends TemplateBean {

	@JSONField(serialize = false)
	public String key = "WebPathBrute";
	@JSONField(serialize = false)
	public String uri = "/brute/webpath";
	@JSONField(serialize = false)
	public String method = "POST";

	private String wordlist;

	public WebPathBruteSettingsBean(String wordlist) {
		super();
		this.wordlist = wordlist;
	}

	public WebPathBruteSettingsBean() {
		super();
	}

	public WebPathBruteSettingsBean(String name, String description, String targets) {
		super(name, description, targets);
	}

	public WebPathBruteSettingsBean(Map<String, Map<String, Object>> templates, TemplateBean sc) {
		super(sc.getName(), sc.getDescription(), sc.getTargets());
		this.wordlist = (String) templates.get(key).get("wordlist");
	}

	public String getWordlist() {
		return wordlist;
	}

	public void setWordlist(String wordlist) {
		this.wordlist = wordlist;
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
