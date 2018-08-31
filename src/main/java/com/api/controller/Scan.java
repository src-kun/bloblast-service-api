package com.api.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.scan.Nmap;
import com.exception.GeneralServiceException;
import com.utils.ApiForward;

@RestController
@RequestMapping("/scan")
public class Scan {
	@Value("${base.api.url}")
	private String baseApiUrl;
	@Resource 
    private RedisTemplate<String, Object> redisTemplate;
	
    @RequestMapping(value = "nmap", method = RequestMethod.POST)
    public String nmap(@RequestBody String payload) throws GeneralServiceException{
    	ApiForward api;
        try {
        	api = new ApiForward( baseApiUrl + "/scan/nmap", "POST", payload);
        	return (String) api.forward().get(ApiForward.RESPONSE);
		} catch (IOException e) {
			throw new GeneralServiceException(e.getMessage());
		}
    }
    
    @RequestMapping(value = "masscan", method = RequestMethod.POST)
    public String masscan(@RequestBody String payload) throws GeneralServiceException{
    	ApiForward api;
        try {
        	api = new ApiForward( baseApiUrl + "/scan/masscan", "POST", payload);
        	return (String) api.forward().get(ApiForward.RESPONSE);
		} catch (IOException e) {
			throw new GeneralServiceException(e.getMessage());
		}
    }
    
    @RequestMapping("/set")
    @ResponseBody
    public String set(){
       //redisTemplate.opsForValue().set("key","value");
       Nmap n = new Nmap();
       n.setName("sdfhydry");
       redisTemplate.opsForValue().set("test", n);
       
       return  n.getName();
    }

    
}