package com.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.Scans;
import com.api.Templates;
import com.bean.ScanTaskInfoBean;
import com.bean.settings.NmapSettingsBean;
import com.exception.GeneralErrorException;

@RestController
@RequestMapping("scans")
public class ScansController {
	
	@Autowired
	private Scans scans;
	@Autowired
	Templates templates;
	/*{"settings": {"launch_now": false,"folder_id": 3,"name": "test2","description":"","targets":"172.16.80.125","emails": []},"templates": {"nmap":{"ports":"22,443-2000","options":[]},"masscan":{"ports":"22,443-2000","rate":1000,"options":[]},"WebPathBrute":{"wordlist":"e5242135a6402b5de0e92a59890f4d7b"}, "dnsBrute":{"wordlist":""}, "loginBrute":{"wordlist":""}}}*/
	@ResponseStatus(code=HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.PUT)
	public Map<String, ScanTaskInfoBean> scans(@RequestBody String payload) {
		try {
			templates.setPayload(payload);
		} catch (InvocationTargetException e) {
			throw new GeneralErrorException("Invocation payload exception.");
		} catch (Exception e) {
			throw new GeneralErrorException("Payload exception.");
		}

		scans.setTemplates(templates);
		scans.save();
		if(templates.settings.isLaunch_now() == true) {
			scans.launch();
		}
		
		Map<String, ScanTaskInfoBean> scan = new HashMap<>();
		scan.put("scan", new ScanTaskInfoBean(scans.getScansBean()));
		return scan;
	}
	
	@RequestMapping(value = "{id}/launch", method = RequestMethod.POST)
	public Map<String, ScanTaskInfoBean> launch(@PathVariable String id) {
		try {
			scans.setId(Long.valueOf(id));
		} catch (Exception e) {
			throw new GeneralErrorException(e.getMessage());
		}
		scans.launch();
		Map<String, ScanTaskInfoBean> scan = new HashMap<>();
		scan.put("scan", new ScanTaskInfoBean(scans.getScansBean()));
		return scan;
	}
	
	@RequestMapping(value = "{id}/stop", method = RequestMethod.POST)
	public void stop(@PathVariable String id) {
		try {
			scans.setId(Long.valueOf(id));
		} catch (Exception e) {
			throw new GeneralErrorException(e.getMessage());
		}
		scans.stop();
	}
	
	@RequestMapping(value = "{id}/status", method = RequestMethod.POST)
	public Map<String, Integer> status(@PathVariable String id) {
		try {
			scans.setId(Long.valueOf(id));
		} catch (Exception e) {
			throw new GeneralErrorException(e.getMessage());
		}
		Map<String, Integer> status = new HashMap<>();
		status.put("status", scans.status());
		return status;
	}

	@RequestMapping("/set")
	@ResponseBody
	public NmapSettingsBean set() {
		// redisTemplate.opsForValue().set("key","value");
		throw new GeneralErrorException("test");
		//n.setName("sdfhydry");
		// System.out.println(redis.range("tasks", 0, -1));
		// redisTemplate.opsForValue().set("test", n);
		//throw new GeneralErrorException("");
		//return n;
	}

}