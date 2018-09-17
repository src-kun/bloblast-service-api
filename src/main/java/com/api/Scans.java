package com.api;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.bean.ScanResponseBean;
import com.bean.ScansBean;
import com.bean.TemplateBean;
import com.db.Database;
import com.exception.AleadyStopException;
import com.exception.AlreadyRunningException;
import com.exception.GeneralErrorException;
import com.exception.NotRunningException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.ApiBridge;

@Component
public class Scans implements IScans {

	public final int READY = 0;
	public final int RUNNING = 1;
	public final int COMPLETE = 2;
	public final int STOP = 3;

	private Templates templates;
	private long id;

	@Autowired
	private Database db;

	@Value("${base.api.url}")
	public String baseApiUrl;

	protected ObjectMapper mapper = new ObjectMapper();

	public Scans() {
		super();
	}

	public Scans(Templates templates) {
		this.templates = templates;
	}

	protected long createTask(TemplateBean templateBean) throws JsonParseException, JsonMappingException, IOException {
		ApiBridge bridge = new ApiBridge(baseApiUrl + templateBean.getUri(), templateBean.getMethod(),
				JSON.toJSONString(templateBean));
		bridge.setContentType("application/json");
		bridge.send();
		HashMap<String, Long> map = mapper.readValue(bridge.response, new TypeReference<HashMap<String, Long>>() {
		});
		return map.get("id");
	}

	protected void stopTask(long id) {
		ApiBridge bridge = new ApiBridge(baseApiUrl + "/stop/" + String.valueOf(id), "GET");
		bridge.send();
	}

	protected int status(long id) throws JsonParseException, JsonMappingException, IOException {
		ApiBridge bridge = new ApiBridge(baseApiUrl + "/status/" + String.valueOf(id), "GET");
		bridge.send();
		HashMap<String, Integer> map = mapper.readValue(bridge.response.replace("'", "\""), new TypeReference<HashMap<String, Integer>>() {
		});
		return map.get("status");
	}

	/**
	 * TODO: 目前调用所有api无任何关联，后面需要将api关联起来，如nmap api扫描识别出的端口，在使用loginBrute api爆破
	 * 
	 * @return
	 */
	@Override
	public void save() {
		ScansBean scansBean = new ScansBean();
		scansBean.scanResponse = new ScanResponseBean[templates.templatesObj.length];
		scansBean.templates = templates.templatesObj;
		scansBean.setSettings(templates.settings);
		id = db.saveScan(scansBean);
	}

	@Override
	public void launch() {
		ScansBean scansBean = getScansBean();
		if (scansBean.status == RUNNING) {
			throw new AlreadyRunningException();
		}
		try {
			for (int i = 0; i < scansBean.templates.length; i++) {
				TemplateBean templateBean = scansBean.templates[i];
				scansBean.scanResponse[i] = new ScanResponseBean(createTask(templateBean), templateBean.getKey());
			}
		} catch (IOException e) {
			throw new GeneralErrorException(e.getMessage());
		}
		scansBean.status = RUNNING;
		db.updateScan(scansBean);
	}

	@Override
	public int status() {
		ScansBean scansBean = getScansBean();
		int status = STOP;
		try {
			for (int i = 0; i < scansBean.templates.length; i++) {
				status = status(scansBean.getScanId());
				if(status == RUNNING) {
					break;
				}
			}
			return status;
		} catch (IOException e) {
			throw new GeneralErrorException(e.getMessage());
		}
	}

	@Override
	public void stop() {
		ScansBean scansBean = getScansBean();
		if (scansBean.status == READY) {
			throw new NotRunningException();
		} else if (scansBean.status == STOP) {
			throw new AleadyStopException();
		}
		for (ScanResponseBean srb : scansBean.getScanResponse()) {
			stopTask(srb.getId());
		}
		scansBean.status = STOP;
		db.updateScan(scansBean);
	}

	@Override
	public void tasks() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
	}

	@Override
	public void trash() {
		// TODO Auto-generated method stub
	}

	public ScansBean getScansBean() {
		return db.getScanByScanId(id);
	}

	public Templates getTemplates() {
		return templates;
	}

	public void setTemplates(Templates templates) {
		this.templates = templates;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
