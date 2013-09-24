package jcurl.main.session;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CurlResponse {

	private Map<String, List<String>> headers;
	private String responseMessage;
	private int responseCode;
	private Object content;
	private String cookies;
	private Date date;
	private int contentLength;
	private String contentType;

	public CurlResponse(HttpURLConnection connection) throws IOException {
		headers = connection.getHeaderFields();
		responseMessage = connection.getResponseMessage();
		cookies = connection.getHeaderField("Set-Cookie");
		responseCode = connection.getResponseCode();
		content = connection.getContent();
		date = new Date(connection.getDate());
		contentLength = connection.getContentLength();
		contentType = connection.getContentType();
	}
	
	

	@Override
	public String toString() {
		return "CurlResponse [headers=" + headers + ", responseMessage="
				+ responseMessage + ", responseCode=" + responseCode
				+ ", content=" + content + ", cookies=" + cookies + ", date="
				+ date + ", contentLength=" + contentLength + ", contentType="
				+ contentType + "]";
	}

	public Object getContent() {
		return content;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
}
