package com.api.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskContronller {

	/*@Value("${base.api.url}")
	private String baseApiUrl;
	Database db = new Database();

	@RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
	public String task(@PathVariable("id") long id) throws GeneralErrorException {
		ApiBridge forword;
		try {
			forword = new ApiBridge(baseApiUrl + "/task/" + String.valueOf(id), "GET");
			forword.send();
			return forword.response;
		} catch (IOException e) {
			throw new GeneralErrorException(e.getMessage());
		}
	}

	@RequestMapping(path = "/tasks", method = RequestMethod.GET)
	public String tasks() throws GeneralErrorException {
		ApiBridge forword;
		try {
			forword = new ApiBridge(baseApiUrl + "/tasks", "GET");
			forword.send();
			return forword.response;
		} catch (IOException e) {
			throw new GeneralErrorException(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/test")
	@ResponseBody
	public String test() throws ScansException {
		// redisTemplate.opsForValue().set("key","value");
		throw new ScansException("test");
		NmapSettingsBean n = new NmapSettingsBean();
		n.setName("sdfhydry");
		//redisTemplate.opsForValue().set("test", n);
		ObjectMapper mapper = new ObjectMapper();
		//bean to string
		//String jsonInString = mapper.writeValueAsString(n);
		//System.out.println(jsonInString);
		//string to bean
		//NmapSettingsBean staff1 = mapper.readValue(jsonInString, NmapSettingsBean.class);
		//System.out.println(staff1);
		//json string to map
    	TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
    	HashMap<String,Object> o = mapper.readValue("{\"settings\": { \"launch_now\": true, \"folder_id\": 3, \"name\": \"test2\", \"description\": \"\", \"targets\": \"127.0.0.1\" }}", typeRef); 
    	System.out.println(o.get("settings").getClass());
    	//map to bean
		String json = new ObjectMapper().writeValueAsString(o.get("settings"));
    	SettingsBean s = mapper.convertValue(o.get("settings"), SettingsBean.class);
  
		return n.getName();
	}*/

}
