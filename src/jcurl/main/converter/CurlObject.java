package jcurl.main.converter;

import java.net.URL;
import java.util.Map;

public class CurlObject {

	private URL url;
	private Map<String, String> headers;
	private String httpMethod;
	private String data;

	CurlObject() {
		httpMethod = "GET";
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
