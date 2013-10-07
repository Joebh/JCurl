package jcurl.main.session;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jcurl.main.converter.CurlConverter;
import jcurl.main.converter.CurlObject;

public class JCurlSession {

	/**
	 * Logger
	 */
	private Logger log = Logger.getLogger(JCurlSession.class.getName());

	/**
	 * Current cookies
	 */
	private Map<String, String> currentCookies;

	/**
	 * The current response object
	 */
	private CurlResponse curlResponse;

	/**
	 * The timeout of each curl call
	 */
	private int timeout = 0;

	/**
	 * Create a new JCurlSession instance
	 * 
	 */
	public JCurlSession() {
		currentCookies = new HashMap<String, String>();
	}

	public JCurlSession(int timeout) {
		this.timeout = timeout;
		currentCookies = new HashMap<String, String>();
	}

	/**
	 * Input a curl string that would run in command line and return the
	 * response object
	 * 
	 * @param curlString
	 * @throws IOException
	 */
	public CurlResponse callCurl(String curlString) {
		log.fine(MessageFormat.format("Calling curl string {0}", curlString));

		log.fine("Converting curl string to curl object");
		// convert string to curl object
		CurlObject curlObject = CurlConverter.convertCurl(curlString);

		log.fine("Done converting, creating connection now");

		// create the connection
		URL url = curlObject.getUrl();
		OutputStream os = null;
		try {
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			// set http method
			connection.setRequestMethod(curlObject.getHttpMethod());

			connection.setDoOutput(true);

			log.fine("Connection created, adding headers now");

			// iterate over headers and add to request properties
			Map<String, String> headers = curlObject.getHeaders();
			for (String key : headers.keySet()) {
				connection.setRequestProperty(key, headers.get(key));
			}

			// from previous response object get cookies
			String cookieString = convertCookiesToString(currentCookies);

			log.fine(MessageFormat
					.format("Done adding headers, now adding cookies {0}",
							cookieString));

			// if there are previous cookies set the header
			if (cookieString != null) {
				connection.setRequestProperty("Cookie", cookieString);
			}

			connection.setConnectTimeout(timeout);

			log.fine("Trying to connect to url");

			// connect to the url
			connection.connect();

			log.fine("Done connection to url, getting output stream");

			os = connection.getOutputStream();

			log.fine("Writing bytes to output stream");

			if (curlObject.getData() != null && !curlObject.getData().isEmpty()) {
				os.write(curlObject.getData().getBytes());
			}

			log.fine("Done writing bytes, creating response object");

			curlResponse = new CurlResponse(connection, curlObject);

			log.fine(MessageFormat.format("Done creating response \n{0}\n",
					curlResponse));

			log.fine("Adding response cookies to current cookies");
			addCookies(curlResponse.getCookies());

			connection.disconnect();

			return curlResponse;
		} catch (IOException e) {
			log.log(Level.SEVERE, "", e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					log.log(Level.SEVERE, "", e);
				}
			}
		}
		return null;
	}

	public void addCookie(String key, String value) {
		currentCookies.put(key, value);
	}

	/**
	 * Method that takes a cookie string and adds or overwrite if cookie exists
	 * 
	 * @param cookieString
	 */
	public void addCookies(String cookieString) {
		String[] cookies = cookieString.split(";");
		Pattern pattern = Pattern.compile("([^=]*)=(.*)");
		String key, value;
		// iterate over every cookie
		for (String cookie : cookies) {
			// split cookie on =
			Matcher m = pattern.matcher(cookie);
			if (m.find()) {
				key = m.group(1).trim();
				value = m.group(2).trim();
				addCookie(key, value);
			}
		}
	}

	@Override
	public String toString() {
		return "JCurlSession [currentCookies=" + currentCookies
				+ ", curlResponse=" + curlResponse + ", timeout=" + timeout
				+ "]";
	}

	private String convertCookiesToString(Map<String, String> cookies) {
		if (cookies.isEmpty()) {
			return "";
		}
		StringBuilder cookieString = new StringBuilder();

		for (String key : cookies.keySet()) {
			cookieString.append(key).append("=").append(cookies.get(key))
					.append(";");
		}
		cookieString.deleteCharAt(cookieString.length() - 1);

		return cookieString.toString();
	}

	/**
	 * Clean up the curl session, should be called after done using a
	 * JCurlSession object
	 * 
	 * @throws Throwable
	 */
	public void close() throws Throwable {
		this.finalize();
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
