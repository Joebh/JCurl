package jcurl.main.session;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import jcurl.main.converter.CurlConverter;
import jcurl.main.converter.CurlObject;

public class JCurlSession {

	/**
	 * Logger
	 */
	private Logger log = Logger.getLogger(JCurlSession.class.getName());

	/**
	 * Map of all current cookies
	 */
	private String cookies;

	/**
	 * Current http url connection
	 */
	private HttpURLConnection connection;

	/**
	 * The timeout of each curl call
	 */
	private int timeout = 0;

	/**
	 * Create a new JCurlSession instance
	 * 
	 */
	public JCurlSession() {

	}

	public JCurlSession(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Input a curl string that would run in command line and return the
	 * response object
	 * 
	 * @param curlString
	 * @throws IOException
	 */
	public void callCurl(String curlString) throws IOException {
		log.info(MessageFormat.format("Calling curl string {0}", curlString));

		log.info("Converting curl string to curl object");
		// convert string to curl object
		CurlObject curlObject = CurlConverter.convertCurl(curlString);

		log.info("Done converting, creating connection now");

		// create the connection
		URL url = curlObject.getUrl();
		connection = (HttpURLConnection) url.openConnection();

		log.info("Connection created, adding headers now");

		// iterate over headers and add to request properties
		Map<String, String> headers = curlObject.getHeaders();
		for (String key : headers.keySet()) {
			connection.setRequestProperty(key, headers.get(key));
		}

		log.info(MessageFormat.format(
				"Done adding headers, now adding cookies {0}", cookies));

		// if there are previous cookies set the header
		if (cookies != null) {
			connection.setRequestProperty("Cookie", cookies);
		}

		connection.setConnectTimeout(timeout);

		log.info("Trying to connect to url");

		// connect to the url
		connection.connect();

		log.info("Done connection to url");

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

}
