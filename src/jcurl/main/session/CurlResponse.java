package jcurl.main.session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import jcurl.main.converter.CurlObject;

public class CurlResponse {

	private String content;
	private Map<String, List<String>> headers;
	private String responseMessage;
	private int responseCode;

	private List<String> cookies;
	private Date date;
	private int contentLength;
	private String contentType;

	public CurlResponse(HttpURLConnection connection, CurlObject curlObject)
			throws IOException {
		headers = connection.getHeaderFields();
		responseMessage = connection.getResponseMessage();
		cookies = headers.get("Set-Cookie");
		responseCode = connection.getResponseCode();

		// convert input stream to string
		BufferedReader br = null;
		if (curlObject.isCompressed()
				&& "gzip".equals(connection.getContentEncoding())) {
			br = new BufferedReader(new InputStreamReader((new GZIPInputStream(
					connection.getInputStream()))));
		} else {
			br = new BufferedReader(new InputStreamReader(
					(connection.getInputStream())));
		}

		StringBuilder outputBuilder = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
			outputBuilder.append(output);
		}

		content = outputBuilder.toString();
		date = new Date(connection.getDate());
		contentLength = connection.getContentLength();
		contentType = connection.getContentType();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("content : ").append(content).append("\n");
		stringBuilder.append("responseCode : ").append(responseCode)
				.append("\n");
		stringBuilder.append("responseMessage : ").append(responseMessage)
				.append("\n");
		stringBuilder.append("date : ").append(date).append("\n");
		stringBuilder.append("contentLength : ").append(contentLength)
				.append("\n");
		stringBuilder.append("contentType : ").append(contentType).append("\n");

		stringBuilder.append(" ---headers---\n");
		for (String key : headers.keySet()) {
			stringBuilder.append(key).append(" : ").append(headers.get(key))
					.append("\n");
		}
		stringBuilder.append(" ---end headers---\n");

		stringBuilder.append(" ---cookies---\n");
		if (cookies != null) {
			for (String cookie : cookies) {
				stringBuilder.append(cookie).append("\n");
			}
		}
		stringBuilder.append(" ---end cookies---\n");

		return stringBuilder.toString();
	}

	public String getContent() {
		return content;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public List<String> getCookies() {
		return cookies;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
}
