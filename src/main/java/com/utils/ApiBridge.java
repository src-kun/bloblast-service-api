package com.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.exception.GeneralErrorException;

public class ApiBridge {

	public String response;
	public int status;

	private String url;
	private String method;
	private String param;
	private String contentType;

	private URL obj;
	protected HttpURLConnection connect;

	public ApiBridge() {
		super();
	}

	public ApiBridge(String url, String method, String param) {
		try {
			this.url = url;
			this.method = method;
			this.param = param;
			this.obj = new URL(this.url);
			this.connect = (HttpURLConnection) obj.openConnection();
		} catch (IOException e) {
			throw new GeneralErrorException(e.getMessage());
		}
	}

	public ApiBridge(String url, String method) {
		this.url = url;
		this.method = method;
		try {
			this.obj = new URL(this.url);
			this.connect = (HttpURLConnection) obj.openConnection();
		} catch (IOException e) {
			throw new GeneralErrorException(e.getMessage());
		}

	}

	/**
	 * request api
	 * 
	 * @throws IOException
	 */
	public void send() {
		try {
			String inputLine;
			BufferedReader in;
			StringBuffer res_buff = new StringBuffer();
			connect.setRequestMethod(method);
			if (contentType != null) {
				connect.setRequestProperty("Content-Type", contentType);
			}

			// request api
			if (method.equals("POST") || method.equals("PUT") && this.param != null) {
				connect.setDoOutput(true);
				DataOutputStream write = new DataOutputStream(connect.getOutputStream());
				write.writeBytes(this.param);
				write.flush();
				write.close();
			}

			// get response
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				res_buff.append(inputLine);
			}
			in.close();

			response = res_buff.toString();
			status = connect.getResponseCode();
		} catch (IOException e) {
			throw new GeneralErrorException(e.getMessage());
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public static void main(String[] args) throws Exception {
		String param = "{\"name\":\"test\", \"description\":\"\", \"target\":\"172.16.80.1/24\", \"ports\":\"445\", \"option\":[]}";
		ApiBridge forword = new ApiBridge("http://172.16.81.252:8888/scan/nmap", "POST", param);
		forword.send();
		System.out.println(forword.status);
	}

}
