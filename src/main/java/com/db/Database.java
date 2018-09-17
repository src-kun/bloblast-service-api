package com.db;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bean.ScansBean;
import com.utils.MD5Utils;

@Component
public class Database {
	
	@Autowired
	Redis redis;
	
	public String generateKey() {
		return MD5Utils.stringToMD5(String.valueOf(System.currentTimeMillis()));
	}
	
	public String generateUUid() {
		  return UUID.randomUUID().toString().toLowerCase();
	}
	
	/**
	 * save scan
	 * @param scans 
	 * @return
	 */
	public long saveScan(ScansBean scans) {
		String uuid = generateUUid();
		long scanId = redis.push("scans", uuid);
		scans.setUuid(uuid);
		scans.setScanId(scanId);
		redis.set(uuid, scans);
		return scanId;
	}
	
	public void updateScan(ScansBean scans) {
		redis.set(scans.getUuid(), scans);
	}
	
	public ScansBean getScanByScanId(long scanId) {
		String uuid = (String) redis.index("scans", -scanId);
		return (ScansBean) redis.get(uuid);
	}

}
