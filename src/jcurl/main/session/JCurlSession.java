package jcurl.main.session;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import jcurl.main.converter.CurlConverter;
import jcurl.main.converter.CurlObject;

public class JCurlSession {

	/**
	 * Map of all current cookies
	 */
	private HashMap<String, String> cookies;

	/**
	 * Create a new JCurlSession instance
	 * 
	 */
	public JCurlSession() {
		cookies = new HashMap<String, String>();
	}

	/**
	 * Input a curl string that would run in command line and return the
	 * response object
	 * 
	 * @param curlString
	 * @throws IOException
	 */
	public void callCurl(String curlString) throws IOException {
		//convert string to curl object
		CurlObject curlObject = CurlConverter.convertCurl(curlString);

		
		
		
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
