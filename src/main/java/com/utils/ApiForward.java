package com.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ApiForward {
	
	public static String RESPONSE = "response";
	public static String STATUS = "status";
	
	private String url;
	private String method;
	private String param;
	protected URL obj;
	protected HttpURLConnection connect;
	
	public ApiForward(String url, String method, String param) throws IOException {
		this.url = url;
		this.method = method;
		this.param = param;
		this.obj = new URL(this.url);
		this.connect = (HttpURLConnection) obj.openConnection();
	}

	public ApiForward(String url, String method) throws IOException {
		this.url = url;
		this.method = method;
		this.obj = new URL(this.url);
		this.connect = (HttpURLConnection) obj.openConnection();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Serializable> forward() throws IOException {
		Map m = new HashMap();
		
		connect.setRequestMethod(method);
		connect.setRequestProperty("Content-Type", "application/json");
		
		if(method.equals("POST") || method.equals("PUT") && this.param != null) {
			connect.setDoOutput(true);
			DataOutputStream write = new DataOutputStream(connect.getOutputStream());
			write.writeBytes(this.param);
			write.flush();
			write.close();
		}
			
		BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		String inputLine;
		StringBuffer res_buff = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			res_buff.append(inputLine);
		}
		in.close();
		m.put(RESPONSE, res_buff.toString());
		m.put(STATUS,  connect.getResponseCode());
		return m;
	}
	
	public static void main(String[] args) throws Exception {
		String param = "{\"name\":\"test\", \"description\":\"\", \"target\":\"172.16.80.1/24\", \"ports\":\"445\", \"option\":[]}";
		ApiForward http = new ApiForward( "http://172.16.81.252:8888/scan/nmap", "POST", param);

		Map<String, Serializable> m = http.forward();
		System.out.println(m.get(ApiForward.RESPONSE));
	}
}
