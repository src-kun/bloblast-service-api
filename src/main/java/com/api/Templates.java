package com.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bean.SettingsBean;
import com.bean.TemplateBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.ClassUtil;

@Component
public class Templates {

	/**
	 * json & string & bean conver
	 */
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * All template class
	 */
	public static final List<Class<?>> templatesClasses = ClassUtil.getClasses("com.bean.settings");

	/**
	 * All template Bean arrays
	 */
	public TemplateBean[] templatesObj;

	public String payload;
	
	/**
	 * Will be used templates map
	 */
	public Map<String, Map<String, TemplateBean>> templatesMap;
	
	/**
	 * scans settings
	 */
	public SettingsBean settings;

	
	public Templates() {
		super();
	}

	public Templates(String payload) throws JsonParseException, JsonMappingException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException{
		this.payload = payload;
		this.paser();
	}
	
	@SuppressWarnings("unchecked")
	protected void paser()  throws InstantiationException, IllegalAccessException, IllegalArgumentException,
	InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException,
	JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> payload_map = mapper.readValue(this.payload,
				new TypeReference<HashMap<String, Object>>() {
				});
		this.settings = mapper.convertValue(payload_map.get("settings"), SettingsBean.class);
		this.templatesMap = (Map<String, Map<String, TemplateBean>>) payload_map.get("templates");
		this.templatesObj = new TemplateBean[templatesClasses.size()];
		for (int i = 0; i < templatesClasses.size(); i++) {
			templatesObj[i] = (TemplateBean) Class.forName(templatesClasses.get(i).getName())
					.getConstructor(Map.class, TemplateBean.class).newInstance(this.templatesMap, this.settings);
		}
	}

	public Object[] getTemplatesObj() {
		return templatesObj;
	}

	public Map<String, Map<String, TemplateBean>> getTemplatesMap() {
		return templatesMap;
	}

	public SettingsBean getSettings() {
		return settings;
	}

	public void setPayload(String payload) throws JsonParseException, JsonMappingException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
		this.payload = payload;
		this.paser();
	}

	public String getPayload() {
		return payload;
	}
	
	

}
