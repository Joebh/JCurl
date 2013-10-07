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

	private String cookies;
	private Date date;
	private int contentLength;
	private String contentType;

	public CurlResponse(HttpURLConnection connection, CurlObject curlObject) throws IOException {
		headers = connection.getHeaderFields();
		responseMessage = connection.getResponseMessage();
		cookies = connection.getHeaderField("Set-Cookie");
		responseCode = connection.getResponseCode();

		// convert input stream to string
		BufferedReader br = null;
		if (curlObject.isCompressed() && "gzip".equals(connection.getContentEncoding())) {
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
		return "CurlResponse [content=" + content + ", headers=" + headers
				+ ", responseMessage=" + responseMessage + ", responseCode="
				+ responseCode + ", cookies=" + cookies + ", date=" + date
				+ ", contentLength=" + contentLength + ", contentType="
				+ contentType + "]";
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
